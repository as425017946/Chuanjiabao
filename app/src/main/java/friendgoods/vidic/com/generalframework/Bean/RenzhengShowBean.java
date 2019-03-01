package friendgoods.vidic.com.generalframework.Bean;

public class RenzhengShowBean {

    /**
     * data : {"user_detail_code":"130223199212286666","user_photo_a":"1544687094810.png","user_photo_b":"1544687095163.png","user_name":"孟","flag2":"1","user_job":"互联网 it 电子 通信"}
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
         * user_detail_code : 130223199212286666
         * user_photo_a : 1544687094810.png
         * user_photo_b : 1544687095163.png
         * user_name : 孟
         * flag2 : 1
         * user_job : 互联网 it 电子 通信
         */

        private String user_detail_code;
        private String user_photo_a;
        private String user_photo_b;
        private String user_name;
        private String flag2;
        private String user_job;

        public String getUser_detail_code() {
            return user_detail_code;
        }

        public void setUser_detail_code(String user_detail_code) {
            this.user_detail_code = user_detail_code;
        }

        public String getUser_photo_a() {
            return user_photo_a;
        }

        public void setUser_photo_a(String user_photo_a) {
            this.user_photo_a = user_photo_a;
        }

        public String getUser_photo_b() {
            return user_photo_b;
        }

        public void setUser_photo_b(String user_photo_b) {
            this.user_photo_b = user_photo_b;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getFlag2() {
            return flag2;
        }

        public void setFlag2(String flag2) {
            this.flag2 = flag2;
        }

        public String getUser_job() {
            return user_job;
        }

        public void setUser_job(String user_job) {
            this.user_job = user_job;
        }
    }
}
