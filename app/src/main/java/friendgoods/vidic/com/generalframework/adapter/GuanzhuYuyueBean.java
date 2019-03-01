package friendgoods.vidic.com.generalframework.adapter;

public class GuanzhuYuyueBean {

    /**
     * data : {"status1":"1","status2":"0"}
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
         * status1 : 1
         * status2 : 0
         */

        private String status1;
        private String status2;

        public String getStatus1() {
            return status1;
        }

        public void setStatus1(String status1) {
            this.status1 = status1;
        }

        public String getStatus2() {
            return status2;
        }

        public void setStatus2(String status2) {
            this.status2 = status2;
        }
    }
}
