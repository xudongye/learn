package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatMsgDao;
import me.own.learn.chat.model.Channel;
import me.own.learn.chat.po.ChatMsg;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
@Repository
public class ChatMsgDaoImpl extends BaseDaoImpl<ChatMsg> implements ChatMsgDao {

    @Override
    protected Class<ChatMsg> getEntityClass() {
        return ChatMsg.class;
    }


    @Override
    public List<Channel> getByUserId(long userId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tu.`name` AS senderName,\n" +
                "\tm.type,\n" +
                "\tm.userId AS sender,\n" +
                "\tmu.userId AS receiver,\n" +
                "\tmu.readMark,\n" +
                "\tm.sendTime,\n" +
                "\tm.content,\n" +
                "\tcount( m.userId ) AS msgCount \n" +
                "FROM\n" +
                "\tchat_msg m\n" +
                "\tLEFT JOIN chat_msg_user_relation mu ON m.msgId = mu.msgId\n" +
                "\tLEFT JOIN chat_user u ON u.id = m.userId \n" +
                "WHERE\n" +
                "\tmu.readMark = FALSE \n" +
                "\tAND mu.userId = :userId \n" +
                "GROUP BY\n" +
                "\tsender \n" +
                "ORDER BY\n" +
                "\tm.sendTime DESC");
        query.setParameter("userId", userId);
        query.addScalar("senderName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("sender", LongType.INSTANCE)
                .addScalar("receiver", LongType.INSTANCE)
                .addScalar("readMark", BooleanType.INSTANCE)
                .addScalar("sendTime", DateType.INSTANCE)
                .addScalar("content", StringType.INSTANCE)
                .addScalar("msgCount", LongType.INSTANCE);
        query.setResultTransformer(Transformers.aliasToBean(Channel.class));
        return query.list();
    }
}
