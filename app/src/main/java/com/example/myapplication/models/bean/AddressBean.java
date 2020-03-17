package com.example.myapplication.models.bean;

import java.io.Serializable;
import java.util.List;

public class AddressBean {

    /**
     * errno : 0
     * errmsg :
     * data : [{"id":12,"name":"张三","user_id":5,"country_id":0,"province_id":2,"city_id":37,"district_id":403,"address":"1号楼","mobile":"13111111111","is_default":1,"province_name":"北京","city_name":"北京市","district_name":"东城区","full_region":"北京北京市东城区"}]
     */

    private int errno;
    private String errmsg;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 12
         * name : 张三
         * user_id : 5
         * country_id : 0
         * province_id : 2
         * city_id : 37
         * district_id : 403
         * address : 1号楼
         * mobile : 13111111111
         * is_default : 1
         * province_name : 北京
         * city_name : 北京市
         * district_name : 东城区
         * full_region : 北京北京市东城区
         */

        private int id;
        private String name;
        private int user_id;
        private int country_id;
        private int province_id;
        private int city_id;
        private int district_id;
        private String address;
        private String mobile;
        private int is_default;
        private String province_name;
        private String city_name;
        private String district_name;
        private String full_region;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getFull_region() {
            return full_region;
        }

        public void setFull_region(String full_region) {
            this.full_region = full_region;
        }
    }
}
