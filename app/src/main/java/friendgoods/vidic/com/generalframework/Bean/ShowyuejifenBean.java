package friendgoods.vidic.com.generalframework.Bean;

public class ShowyuejifenBean {

    /**
     * data : {"user_uuid":"999311a1f8914b0696e9d5e6d240113a","integral":30,"flag1":"1","id":35}
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
         * user_uuid : 999311a1f8914b0696e9d5e6d240113a
         * integral : 30.0
         * flag1 : 1
         * id : 35
         */

        private String user_uuid;
        private String integral;
        private String flag1;
        private int id;

        public String getAccount_balance() {
            return account_balance;
        }

        public void setAccount_balance(String account_balance) {
            this.account_balance = account_balance;
        }

        private String account_balance;

        public String getUser_uuid() {
            return user_uuid;
        }

        public void setUser_uuid(String user_uuid) {
            this.user_uuid = user_uuid;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getFlag1() {
            return flag1;
        }

        public void setFlag1(String flag1) {
            this.flag1 = flag1;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
