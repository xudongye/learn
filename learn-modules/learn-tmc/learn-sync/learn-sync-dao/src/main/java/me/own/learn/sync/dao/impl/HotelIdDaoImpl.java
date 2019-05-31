package me.own.learn.sync.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.sync.dao.HotelIdDao;
import me.own.learn.sync.po.HotelId;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/24 17:49
 */
@Repository
public class HotelIdDaoImpl extends BaseDaoImpl<HotelId> implements HotelIdDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelIdDaoImpl.class);

    @Autowired
    protected DataSource dataSource;


    @Override
    protected Class<HotelId> getEntityClass() {
        return HotelId.class;
    }

    @Override
    public void batchAddHotelIds(List<Long> hotelIds, String cityCode) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO `learn`.`hotel_id`(`hotelId`, `cityCode`, `needSync`, `syncTime`) VALUES (?, ?, ?, ?);\n");
            for (Long hotelId : hotelIds) {
                statement.setLong(1, hotelId);
                statement.setString(2, cityCode);
                statement.setBoolean(3, true);
                statement.setDate(4, new java.sql.Date(new Date().getTime()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<Long> getIdsByCityCode(String cityCode) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\thi.hotelId \n" +
                "FROM\n" +
                "\thotel_id hi\n" +
                "\tLEFT JOIN hotel_base_info hbi ON hi.hotelId = hbi.hotelId \n" +
                "WHERE\n" +
                "\thi.cityCode = :cityCode \n" +
                "\tAND ISNULL( hbi.hotelId )");
        query.setParameter("cityCode", cityCode);
        query.addScalar("hotelId", StandardBasicTypes.LONG);
        return query.list();
    }

    @Override
    public void updateNeedSync(List<Long> hotelIds) {

        Query query = getCurrentSession().createQuery("UPDATE HotelId \n" +
                "SET needSync = FALSE \n" +
                "WHERE\n" +
                "\thotelId IN :hotelIds");
        query.setParameterList("hotelIds", hotelIds);
        query.executeUpdate();
    }
}
