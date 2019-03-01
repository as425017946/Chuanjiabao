package friendgoods.vidic.com.generalframework.Bean;

public class MyJifenBean {
    /**
     * data : {"user_uuid":"999311a1f8914b0696e9d5e6d240113a","cob_time":1544715894000,"create_time":1544183678000,"integral":30}
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
         * cob_time : 1544715894000
         * create_time : 1544183678000
         * integral : 30.0
         */

        private String user_uuid;
        private long cob_time;
        private long create_time;
        private double integral;

        public String getUser_uuid() {
            return user_uuid;
        }

        public void setUser_uuid(String user_uuid) {
            this.user_uuid = user_uuid;
        }

        public long getCob_time() {
            return cob_time;
        }

        public void setCob_time(long cob_time) {
            this.cob_time = cob_time;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }
    }
}
