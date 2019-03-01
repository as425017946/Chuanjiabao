package friendgoods.vidic.com.generalframework.Bean;

public class TouzirenBean {

    /**
     * data : {"items_number":"BH20181114135907838","buy_num":"2","limitation_number":"42"}
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
         * items_number : BH20181114135907838
         * buy_num : 2
         * limitation_number : 42
         */

        private String items_number;
        private String buy_num;
        private String limitation_number;

        public String getItems_number() {
            return items_number;
        }

        public void setItems_number(String items_number) {
            this.items_number = items_number;
        }

        public String getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(String buy_num) {
            this.buy_num = buy_num;
        }

        public String getLimitation_number() {
            return limitation_number;
        }

        public void setLimitation_number(String limitation_number) {
            this.limitation_number = limitation_number;
        }
    }
}
