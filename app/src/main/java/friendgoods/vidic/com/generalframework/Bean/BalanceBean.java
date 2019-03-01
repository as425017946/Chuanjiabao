package friendgoods.vidic.com.generalframework.Bean;

/**
 * 分红卡
 */
public class BalanceBean {

    /**
     * data : {"flag":"1","user_code":"130222199212225555","user_name":"孟","bank_card_number":"5555555555555555555","bank_name":"中国工商银行","user_phone":"18920629331","bank_region":"北京东城区"}
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
         * flag : 1
         * user_code : 130222199212225555
         * user_name : 孟
         * bank_card_number : 5555555555555555555
         * bank_name : 中国工商银行
         * user_phone : 18920629331
         * bank_region : 北京东城区
         */

        private String flag;
        private String user_code;
        private String user_name;
        private String bank_card_number;
        private String bank_name;
        private String user_phone;
        private String bank_region;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getBank_region() {
            return bank_region;
        }

        public void setBank_region(String bank_region) {
            this.bank_region = bank_region;
        }
    }
}
