package me.own.learn.sync.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.sync.dao.HotelBaseInfoDao;
import me.own.learn.sync.po.HotelBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/25 11:34
 */
@Repository
public class HotelBaseInfoDaoImpl extends BaseDaoImpl<HotelBaseInfo> implements HotelBaseInfoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelBaseInfoDaoImpl.class);

    @Autowired
    protected DataSource dataSource;

    @Override
    protected Class<HotelBaseInfo> getEntityClass() {
        return HotelBaseInfo.class;
    }

    @Override
    public void batchAddHotelInfo(List<HotelBaseInfo> hotelBaseInfos) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO `learn`.`hotel_base_info`(`hotelId`, `address`, `appearancePicUrl`, `checkInTime`, `checkOutTime`, `city`, `district`, `fax`, `fitmentDate`, `hotelEngName`, `hotelIntroduce`, `hotelName`, `hotelStar`, `latitude`, `longitude`, `parentHotelGroup`, `parentHotelGroupName`, `plateID`, `plateName`, `praciceDate`, `roomNum`, `telephone`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\n");
            for (HotelBaseInfo hotelBaseInfo : hotelBaseInfos) {
                statement.setLong(1, hotelBaseInfo.getHotelId());
                statement.setString(2, hotelBaseInfo.getAddress());
                statement.setString(3, hotelBaseInfo.getAppearancePicUrl());
                statement.setString(4, hotelBaseInfo.getCheckInTime());
                statement.setString(5, hotelBaseInfo.getCheckOutTime());
                statement.setString(6, hotelBaseInfo.getCity());
                statement.setString(7, hotelBaseInfo.getDistinct());
                statement.setString(8, hotelBaseInfo.getFax());
                statement.setString(9, hotelBaseInfo.getFitmentDate());
                statement.setString(10, hotelBaseInfo.getHotelEngName());
                statement.setString(11, hotelBaseInfo.getHotelIntroduce());
                statement.setString(12, hotelBaseInfo.getHotelName());
                statement.setString(13, hotelBaseInfo.getHotelStar());
                statement.setDouble(14, hotelBaseInfo.getLatitude() != null ? hotelBaseInfo.getLatitude() : 0);
                statement.setDouble(15, hotelBaseInfo.getLongitude() != null ? hotelBaseInfo.getLongitude() : 0);
                statement.setString(16, hotelBaseInfo.getParentHotelGroup());
                statement.setString(17, hotelBaseInfo.getParentHotelGroupName());
                statement.setString(18, hotelBaseInfo.getPlateID());
                statement.setString(19, hotelBaseInfo.getPlateName());
                statement.setString(20, hotelBaseInfo.getPraciceDate());
                statement.setString(21, hotelBaseInfo.getRoomNum());
                statement.setString(22, hotelBaseInfo.getTelephone());
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
}
