package me.own.learn.sync.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yexudong
 * @date 2019/4/24 17:44
 */
@Entity
@Table(name = "hotel_id")
public class HotelId implements Serializable {
    @Id
    private Long hotelId;
    @Column
    private Boolean needSync;
    @Temporal(TemporalType.TIMESTAMP)
    private Date syncTime;
    @Column
    private String cityCode;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Boolean getNeedSync() {
        return needSync;
    }

    public void setNeedSync(Boolean needSync) {
        this.needSync = needSync;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
