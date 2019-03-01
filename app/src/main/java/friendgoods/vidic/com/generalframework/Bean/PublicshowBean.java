package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class PublicshowBean {

    /**
     * data : [{"creation_time":1541506062000,"items_name":"天津津南物流","public_file_name":"ceshi.txt","public_file_url":"ceshi-1541477258.txt"}]
     * message : 请求成功
     * state : 1
     */

    private String message;
    private int state;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * creation_time : 1541506062000
         * items_name : 天津津南物流
         * public_file_name : ceshi.txt
         * public_file_url : ceshi-1541477258.txt
         */

        private String creation_time;
        private String items_name;
        private String public_file_name;
        private String public_file_url;

        public String getCreation_time() {
            return creation_time;
        }

        public void setCreation_time(String creation_time) {
            this.creation_time = creation_time;
        }

        public String getItems_name() {
            return items_name;
        }

        public void setItems_name(String items_name) {
            this.items_name = items_name;
        }

        public String getPublic_file_name() {
            return public_file_name;
        }

        public void setPublic_file_name(String public_file_name) {
            this.public_file_name = public_file_name;
        }

        public String getPublic_file_url() {
            return public_file_url;
        }

        public void setPublic_file_url(String public_file_url) {
            this.public_file_url = public_file_url;
        }
    }
}
