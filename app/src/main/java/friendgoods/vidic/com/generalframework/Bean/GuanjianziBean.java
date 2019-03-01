package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class GuanjianziBean {

    /**
     * data : [{"name":"北京","id":7,"type":"2"},{"name":"成都","id":8,"type":"2"},{"name":"上海","id":9,"type":"1"},{"name":"safas","id":10,"type":"1"},{"name":"sdag","id":11,"type":"2"}]
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
         * name : 北京
         * id : 7
         * type : 2
         */

        private String name;
        private int id;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
