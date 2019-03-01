package friendgoods.vidic.com.generalframework.Bean;

/**
 * 投资人可见信息
 */
public class TouziKejianBean {

    /**
     * state : 1
     * message : 请求成功
     * data : {"number":2,"items_name":"海昌天澜假日酒店","buy_time":1533994393000,"money":20000}
     */

    private int state;
    private String message;
    private DataBean data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * number : 2.0
         * items_name : 海昌天澜假日酒店
         * buy_time : 1533994393000
         * money : 20000.0
         */

        private double number;
        private String items_name;
        private String buy_time;
        private String money;

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }

        public String getItems_name() {
            return items_name;
        }

        public void setItems_name(String items_name) {
            this.items_name = items_name;
        }

        public String getBuy_time() {
            return buy_time;
        }

        public void setBuy_time(String buy_time) {
            this.buy_time = buy_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
