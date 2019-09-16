package me.own.learn.order.po;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class OrderKeyGenerator implements IdentifierGenerator, Configurable {

    private final static Logger log = LoggerFactory.getLogger(OrderKeyGenerator.class);

    public static final Map<Long,String> single = new HashMap<Long,String>();

    private static final String lock = "lock";

    @Override
    public void configure(Type arg0, Properties params, Dialect arg2)
            throws MappingException {
    }


    @Override
    public synchronized Serializable generate(SessionImplementor arg0, Object arg1)
            throws HibernateException {
        int icount = 0;
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        Connection con = arg0.connection();
        PreparedStatement lockPstmt = null;
        PreparedStatement queryPstmt = null;
        PreparedStatement insertPstmt = null;
        ResultSet rs = null;
        try {
            con.setAutoCommit(false);

            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            synchronized (lock){
                lockPstmt = con.prepareStatement("lock tables order_identifier write");
                lockPstmt.execute();

                queryPstmt = con.prepareStatement("select count(*) from order_identifier where date = ?");
                queryPstmt.setString(1, date);
                rs= queryPstmt.executeQuery();

                try {
                    if (rs.next()){
                        Long dailycount = rs.getLong(1);
                        icount = dailycount.intValue();
                    }
                } catch (SQLException sqlex){
                    log.error(sqlex.getMessage(), sqlex);
                    throw new Exception(sqlex);
                } finally {
                    rs.close();
                }

                icount++;

                insertPstmt = con.prepareStatement("insert into order_identifier (dailySequence, date) values (?,?)");;
                insertPstmt.setInt(1, icount);
                insertPstmt.setString(2, date);

                insertPstmt.executeUpdate();

                con.commit();
            }
        } catch (Exception e){
            log.error(e.getMessage(), e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }
            throw new HibernateException(e.getMessage());
        } finally {
            PreparedStatement unlockPstmt = null;
            if(lockPstmt!=null){
                try {
                    lockPstmt.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }if(queryPstmt!=null){
                try {
                    queryPstmt.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }if(insertPstmt!=null){
                try {
                    insertPstmt.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
            try {
                unlockPstmt = con.prepareStatement("unlock tables");
                unlockPstmt.execute();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }finally{
                if(unlockPstmt!=null){
                    try {
                        unlockPstmt.close();
                    } catch (SQLException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#00000");
        String result = df.format(icount);
        single.put(Thread.currentThread().getId(), date+result);
        return date+result;
    }
}
