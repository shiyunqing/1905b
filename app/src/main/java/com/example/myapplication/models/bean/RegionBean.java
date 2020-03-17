package com.example.myapplication.models.bean;

import java.util.List;

public class RegionBean {

    /**
     * errno : 0
     * errmsg :
     * data : [{"id":442,"parent_id":39,"name":"长安区","type":3,"agency_id":0},{"id":443,"parent_id":39,"name":"桥东区","type":3,"agency_id":0},{"id":444,"parent_id":39,"name":"桥西区","type":3,"agency_id":0},{"id":445,"parent_id":39,"name":"新华区","type":3,"agency_id":0},{"id":446,"parent_id":39,"name":"井陉矿区","type":3,"agency_id":0},{"id":447,"parent_id":39,"name":"裕华区","type":3,"agency_id":0},{"id":448,"parent_id":39,"name":"井陉县","type":3,"agency_id":0},{"id":449,"parent_id":39,"name":"正定县","type":3,"agency_id":0},{"id":450,"parent_id":39,"name":"栾城区","type":3,"agency_id":0},{"id":451,"parent_id":39,"name":"行唐县","type":3,"agency_id":0},{"id":452,"parent_id":39,"name":"灵寿县","type":3,"agency_id":0},{"id":453,"parent_id":39,"name":"高邑县","type":3,"agency_id":0},{"id":454,"parent_id":39,"name":"深泽县","type":3,"agency_id":0},{"id":455,"parent_id":39,"name":"赞皇县","type":3,"agency_id":0},{"id":456,"parent_id":39,"name":"无极县","type":3,"agency_id":0},{"id":457,"parent_id":39,"name":"平山县","type":3,"agency_id":0},{"id":458,"parent_id":39,"name":"元氏县","type":3,"agency_id":0},{"id":459,"parent_id":39,"name":"赵县","type":3,"agency_id":0},{"id":460,"parent_id":39,"name":"辛集市","type":3,"agency_id":0},{"id":461,"parent_id":39,"name":"藁城区","type":3,"agency_id":0},{"id":462,"parent_id":39,"name":"晋州市","type":3,"agency_id":0},{"id":463,"parent_id":39,"name":"新乐市","type":3,"agency_id":0},{"id":464,"parent_id":39,"name":"鹿泉区","type":3,"agency_id":0},{"id":465,"parent_id":39,"name":"其它区","type":3,"agency_id":0}]
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

    public static class DataBean {
        /**
         * id : 442
         * parent_id : 39
         * name : 长安区
         * type : 3
         * agency_id : 0
         */

        private int id;
        private int parent_id;
        private String name;
        private int type;
        private int agency_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAgency_id() {
            return agency_id;
        }

        public void setAgency_id(int agency_id) {
            this.agency_id = agency_id;
        }
    }
}
