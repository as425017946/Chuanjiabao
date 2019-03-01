package friendgoods.vidic.com.generalframework.Bean;

/**
 * 项目进度查询
 */
public class ObjJinduBean {

    /**
     * data : {"money":2,"nowMoney":20000}
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
         * money : 2.0
         * nowMoney : 20000.0
         */

        private String money;
        private double nowMoney;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public double getNowMoney() {
            return nowMoney;
        }

        public void setNowMoney(double nowMoney) {
            this.nowMoney = nowMoney;
        }
    }
}
