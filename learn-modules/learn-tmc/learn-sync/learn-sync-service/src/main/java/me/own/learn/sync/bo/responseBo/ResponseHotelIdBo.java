package me.own.learn.sync.bo.responseBo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/24 17:50
 */
public class ResponseHotelIdBo {
    private List<Long> hotelIds;
    private int totalPage;
    private int currentPage;
    private int total;

    public List<Long> getHotelIds() {
        return hotelIds;
    }

    public void setHotelIds(List<Long> hotelIds) {
        this.hotelIds = hotelIds;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
