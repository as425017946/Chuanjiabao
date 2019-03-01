package friendgoods.vidic.com.generalframework.Bean;

/**
 * 绑定邮箱
 */
public class EmailBean {

    /**
     * state : 1
     * message : 请求成功
     * data : {"user_Email":"1783937717@qq.com"}
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
         * user_Email : 1783937717@qq.com
         */

        private String user_Email;

        public String getUser_Email() {
            return user_Email;
        }

        public void setUser_Email(String user_Email) {
            this.user_Email = user_Email;
        }
    }
}
