package friendgoods.vidic.com.generalframework.Bean;

/**
 * 总投资项目、总众筹目的地、总投资金额
 */
public class ShujupilouZonginfoBean {


    /**
     * data : {"number":23,"money":4800000,"siteNumber":21}
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
         * number : 23
         * money : 4800000.0
         * siteNumber : 21
         */

        private double number;
        private double money;
        private double siteNumber;

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public double getSiteNumber() {
            return siteNumber;
        }

        public void setSiteNumber(double siteNumber) {
            this.siteNumber = siteNumber;
        }
    }
}
