package me.own.learn.sync.constant;

import me.own.commons.base.utils.enums.EnumName;

/**
 * @author:simonye
 * @date 22:15 2019/4/16
 **/
public class SyncConstant {

    public enum Signature implements EnumName {


        queryCountryList(1, "queryCountryList"),//查询国家列表
        queryCityList(2, "queryCityList"),
        queryDistrictList(3, "queryDistrictList"),
        queryBusinessList(4, "queryBusinessList"),
        queryHotelInfo(5, "queryHotelInfo"),
        queryHotelIdList(6, "queryHotelIdList"),
        queryHotelImage(7, "queryHotelImage"),
        queryProductDetail(8, "queryProductDetail"),
        queryHotelLowestPrice(9, "queryHotelLowestPrice");

        private int code;

        private String name;

        Signature(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
