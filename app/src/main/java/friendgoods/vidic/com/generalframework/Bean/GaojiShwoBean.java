package friendgoods.vidic.com.generalframework.Bean;

public class GaojiShwoBean {
    /**
     * data : {"flag2":"0"}
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
         * flag2 : 0
         */

        private String flag2;

        public String getFlag2() {
            return flag2;
        }

        public void setFlag2(String flag2) {
            this.flag2 = flag2;
        }
    }
}
