package friendgoods.vidic.com.generalframework.Bean;

public class UserInfoBean {

    /**
     * data : {"number":"2","account_balance":2000.1,"integral":0,"user_head_portrait":"www.qq.com","user_phone":"13163017396","user_nick_name":"www.baidu.com","user_Email":"17839377171@qq.com"}
     * message : 请求成功
     * state : 1
     */

    private DataBean data;
    private String message;
    private int state;

    public int getFlag3() {
        return flag3;
    }

    public void setFlag3(int flag3) {
        this.flag3 = flag3;
    }

    private int flag3;

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
        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
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

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUser_Email() {
            return user_Email;
        }

        public void setUser_Email(String user_Email) {
            this.user_Email = user_Email;
        }
        private String user_password;
        private String user_WeChat;
        private String user_name;
        private String user_head_portrait;
        private String user_phone;
        private String flag2; //用户是否进行高级认证 0 未认证 1已认证
        private String user_nick_name;
        private String user_six;
        private String uuid;
        private String user_Email;


    }
}
