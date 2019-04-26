package me.own.learn.sync.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/25 11:18
 */
@Entity(name = "hotel_base_info")
public class HotelBaseInfo implements Serializable {
    @Id
    private Long hotelId;
    @Column
    private String hotelName;
    @Column
    private String appearancePicUrl;
    @Column
    private String hotelEngName;
    @Column
    private String telephone;
    @Column
    private String fax;
    @Column
    private String hotelStar;
    @Column
    private String parentHotelGroup;
    @Column
    private String parentHotelGroupName;
    @Column
    private String plateID;
    @Column
    private String plateName;
    @Column
    private String praciceDate;
    @Column
    private String roomNum;
    @Lob
    private String hotelIntroduce;
    @Column
    private String fitmentDate;
    @Column
    private String checkInTime;
    @Column
    private String checkOutTime;
    @Column
    private String city;
    @Column(name = "district")
    private String distinct;
    @Column
    private String address;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAppearancePicUrl() {
        return appearancePicUrl;
    }

    public void setAppearancePicUrl(String appearancePicUrl) {
        this.appearancePicUrl = appearancePicUrl;
    }

    public String getHotelEngName() {
        return hotelEngName;
    }

    public void setHotelEngName(String hotelEngName) {
        this.hotelEngName = hotelEngName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getParentHotelGroup() {
        return parentHotelGroup;
    }

    public void setParentHotelGroup(String parentHotelGroup) {
        this.parentHotelGroup = parentHotelGroup;
    }

    public String getParentHotelGroupName() {
        return parentHotelGroupName;
    }

    public void setParentHotelGroupName(String parentHotelGroupName) {
        this.parentHotelGroupName = parentHotelGroupName;
    }

    public String getPlateID() {
        return plateID;
    }

    public void setPlateID(String plateID) {
        this.plateID = plateID;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getPraciceDate() {
        return praciceDate;
    }

    public void setPraciceDate(String praciceDate) {
        this.praciceDate = praciceDate;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getHotelIntroduce() {
        return hotelIntroduce;
    }

    public void setHotelIntroduce(String hotelIntroduce) {
        this.hotelIntroduce = hotelIntroduce;
    }

    public String getFitmentDate() {
        return fitmentDate;
    }

    public void setFitmentDate(String fitmentDate) {
        this.fitmentDate = fitmentDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
