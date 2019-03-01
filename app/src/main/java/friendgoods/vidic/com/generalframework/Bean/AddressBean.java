package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class AddressBean {

    /**
     * data : [{"user_uuid":"999311a1f8914b0696e9d5e6d240113a","area_diqu":"城市北京东城区","flag":"1","add_dizhi":"测试","add_name":"孟","id":47,"add_mobile":"18920629331"},{"user_uuid":"999311a1f8914b0696e9d5e6d240113a","area_diqu":"1","flag":"0","add_dizhi":"1","add_name":"1","id":44,"add_mobile":"1"}]
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
         * user_uuid : 999311a1f8914b0696e9d5e6d240113a
         * area_diqu : 城市北京东城区
         * flag : 1
         * add_dizhi : 测试
         * add_name : 孟
         * id : 47
         * add_mobile : 18920629331
         */

        private String user_uuid;
        private String area_diqu;
        private String flag;
        private String add_dizhi;
        private String add_name;
        private int id;
        private String add_mobile;

        public String getUser_uuid() {
            return user_uuid;
        }

        public void setUser_uuid(String user_uuid) {
            this.user_uuid = user_uuid;
        }

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdd_mobile() {
            return add_mobile;
        }

        public void setAdd_mobile(String add_mobile) {
            this.add_mobile = add_mobile;
        }
    }
}
