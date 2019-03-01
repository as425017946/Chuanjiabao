package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class ZiandianBean {
    /**
     * data : [{"name":"酒店","id":1},{"name":"民宿","id":2},{"name":"公寓","id":3},{"name":"共享办公","id":4},{"name":"医疗健康","id":5},{"name":"休闲娱乐","id":6},{"name":"新零售","id":7},{"name":"亲子","id":8},{"name":"其他","id":9}]
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
         * name : 酒店
         * id : 1
         */

        private String name;
        private int id;

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
    }
}
