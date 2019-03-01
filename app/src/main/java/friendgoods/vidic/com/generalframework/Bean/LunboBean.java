package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

/**
 * 轮播图
 */
public class LunboBean {


    /**
     * data : [{"items_number":"BH20181106141408241","photo":"1541484892.jpg","flag1":"1","url":"","items_status":"4"},{"items_number":"BH20181106092833630","photo":"1541569357.jpg","flag1":"2","url":"dcdc"},{"items_number":"BH20181105143121985","photo":"1541405990.png","flag1":"3","url":"","items_status":"4"},{"items_number":"BH20181106092833630","photo":"1541467987.jpg","flag1":"4","url":""},{"items_number":"BH20181106092833630","photo":"1541571303.jpg","flag1":"56","url":"56"},{"items_number":"BH20181106133639566","photo":"1541571238.jpg","flag1":"90","url":"cfd","items_status":"4"},{"items_number":"BH20181106120040751","photo":"1541477192.jpg","flag1":"98","url":"https://news.qq.com/","items_status":"4"}]
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
         * items_number : BH20181106141408241
         * photo : 1541484892.jpg
         * flag1 : 1
         * url :
         * items_status : 4
         */

        private String items_number;
        private String photo;
        private String flag1;
        private String url;
        private String items_status;

        public String getItems_number() {
            return items_number;
        }

        public void setItems_number(String items_number) {
            this.items_number = items_number;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getFlag1() {
            return flag1;
        }

        public void setFlag1(String flag1) {
            this.flag1 = flag1;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getItems_status() {
            return items_status;
        }

        public void setItems_status(String items_status) {
            this.items_status = items_status;
        }
    }
}
