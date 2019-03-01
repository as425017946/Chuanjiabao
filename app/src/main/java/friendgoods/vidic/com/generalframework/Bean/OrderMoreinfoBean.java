package friendgoods.vidic.com.generalframework.Bean;

/**
 * 用于我的订单里面的详情使用
 */
public class OrderMoreinfoBean {
    /**
     * data : {"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案2","buy_money":"1000","time":"2018-12-25 14:35:59","items_status":"3","status":"1"}
     * message : 请求成功
     * state : 1
     */

    private DataBean data;
    private String message;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * funding_start_money : 1000
         * items_photo1 : 1542767942.jpg
         * number : 20181225143559184
         * items_name : 面包店
         * buy_num : 1
         * items_scheme_name : 方案2
         * buy_money : 1000
         * time : 2018-12-25 14:35:59
         * items_status : 3
         * status : 1
         */

        private String funding_start_money;
        private String items_photo1;
        private String number;
        private String items_name;
        private String buy_num;
        private String items_scheme_name;
        private String buy_money;
        private String time;
        private String items_status;
        private String status;

        public String getFunding_start_money() {
            return funding_start_money;
        }

        public void setFunding_start_money(String funding_start_money) {
            this.funding_start_money = funding_start_money;
        }

        public String getItems_photo1() {
            return items_photo1;
        }

        public void setItems_photo1(String items_photo1) {
            this.items_photo1 = items_photo1;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getItems_name() {
            return items_name;
        }

        public void setItems_name(String items_name) {
            this.items_name = items_name;
        }

        public String getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(String buy_num) {
            this.buy_num = buy_num;
        }

        public String getItems_scheme_name() {
            return items_scheme_name;
        }

        public void setItems_scheme_name(String items_scheme_name) {
            this.items_scheme_name = items_scheme_name;
        }

        public String getBuy_money() {
            return buy_money;
        }

        public void setBuy_money(String buy_money) {
            this.buy_money = buy_money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getItems_status() {
            return items_status;
        }

        public void setItems_status(String items_status) {
            this.items_status = items_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
