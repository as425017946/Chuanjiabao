package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

/**
 *
 * 管理地址
 */
public class GuanliAddressBean {

    /**
     * data : [{"area_diqu":"北京朝阳区","flag":"1","add_dizhi":"佛莫","add_name":"孟","aID":11,"add_mobile":"18920629331"},{"area_diqu":"北京东城区","flag":"0","add_dizhi":"咯哦YY我","add_name":"孟","aID":10,"add_mobile":"18920629331"}]
     * message : 请求成功
     * state : 1
     */

    private String message;
    private int state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * area_diqu : 北京朝阳区
         * flag : 1
         * add_dizhi : 佛莫
         * add_name : 孟
         * aID : 11
         * add_mobile : 18920629331
         */

        private String area_diqu;
        private String flag;
        private String add_dizhi;
        private String add_name;
        private int aID;
        private String add_mobile;

        public String getArea_diqu() {
            return area_diqu;
        }

        public void setArea_diqu(String area_diqu) {
            this.area_diqu = area_diqu;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getAdd_dizhi() {
            return add_dizhi;
        }

        public void setAdd_dizhi(String add_dizhi) {
            this.add_dizhi = add_dizhi;
        }

        public String getAdd_name() {
            return add_name;
        }

        public void setAdd_name(String add_name) {
            this.add_name = add_name;
        }

        public int getAID() {
            return aID;
        }

        public void setAID(int aID) {
            this.aID = aID;
        }

        public String getAdd_mobile() {
            return add_mobile;
        }

        public void setAdd_mobile(String add_mobile) {
            this.add_mobile = add_mobile;
        }
    }
}
