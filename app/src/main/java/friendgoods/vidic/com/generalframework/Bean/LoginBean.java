package friendgoods.vidic.com.generalframework.Bean;

public class LoginBean {


    /**
     * state : 1
     * message : 请求成功
     * data : {"user_password":"0192023a7bbd73250516f069df18b500","flag3":"0","user_phone":"18920629331","flag2":"0","registration_id":"13065ffa4e242dd2115","uuid":"999311a1f8914b0696e9d5e6d240113a"}
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
         * user_password : 0192023a7bbd73250516f069df18b500
         * flag3 : 0
         * user_phone : 18920629331
         * flag2 : 0
         * registration_id : 13065ffa4e242dd2115
         * uuid : 999311a1f8914b0696e9d5e6d240113a
         */

        private String user_password;
        private String flag3;
        private String user_phone;
        private String flag2;
        private String registration_id;
        private String uuid;
        private String user_WeChat;
        private String user_name;
        private String user_head_portrait;
        private String user_nick_name;
        private String user_six;
        private String user_Email;
        private String flag5;

        public String getFlag5() {
            return flag5;
        }

        public void setFlag5(String flag5) {
            this.flag5 = flag5;
        }

        public String getUser_WeChat() {
            return user_WeChat;
        }

        public void setUser_WeChat(String user_WeChat) {
            this.user_WeChat = user_WeChat;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_head_portrait() {
            return user_head_portrait;
        }

        public void setUser_head_portrait(String user_head_portrait) {
            this.user_head_portrait = user_head_portrait;
        }

        public String getUser_nick_name() {
            return user_nick_name;
        }

        public void setUser_nick_name(String user_nick_name) {
            this.user_nick_name = user_nick_name;
        }

        public String getUser_six() {
            return user_six;
        }

        public void setUser_six(String user_six) {
            this.user_six = user_six;
        }

        public String getUser_Email() {
            return user_Email;
        }

        public void setUser_Email(String user_Email) {
            this.user_Email = user_Email;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getFlag3() {
            return flag3;
        }

        public void setFlag3(String flag3) {
            this.flag3 = flag3;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getFlag2() {
            return flag2;
        }

        public void setFlag2(String flag2) {
            this.flag2 = flag2;
        }

        public String getRegistration_id() {
            return registration_id;
        }

        public void setRegistration_id(String registration_id) {
            this.registration_id = registration_id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
