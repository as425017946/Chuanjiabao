package friendgoods.vidic.com.generalframework.Bean;

public class ShujupilouDayBean {

    /**
     * data : {"time":8}
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
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        /**
         * day_list : 8
         */

        private String time;


    }
}
