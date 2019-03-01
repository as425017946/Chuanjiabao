package friendgoods.vidic.com.generalframework.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrivateRenBean {

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
        private String private_file_name;
        private String private_file_url;

        public String getPrivate_file_name() {
            return private_file_name;
        }

        public void setPrivate_file_name(String private_file_name) {
            this.private_file_name = private_file_name;
        }

        public String getPrivate_file_url() {
            return private_file_url;
        }

        public void setPrivate_file_url(String private_file_url) {
            this.private_file_url = private_file_url;
        }

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


    }
}
