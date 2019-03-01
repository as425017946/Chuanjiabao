package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class GaojirenzhengBean {
    /**
     * data : [{"id":1,"user_job_name":"互联网 IT 电子通讯"},{"id":2,"user_job_name":"广告 传媒 文化 体育"},{"id":3,"user_job_name":"金融"},{"id":4,"user_job_name":"教育培训"},{"id":5,"user_job_name":"制药医疗"},{"id":6,"user_job_name":"交通 物流 贸易 零售"},{"id":7,"user_job_name":"专业服务"},{"id":8,"user_job_name":"房地产建筑"},{"id":9,"user_job_name":"汽车"},{"id":10,"user_job_name":"机械制造"},{"id":11,"user_job_name":"服务业"},{"id":12,"user_job_name":"能源 化工环保"},{"id":13,"user_job_name":"政府 非盈利机构"},{"id":14,"user_job_name":"其他"}]
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
         * id : 1
         * user_job_name : 互联网 IT 电子通讯
         */

        private int id;
        private String user_job_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_job_name() {
            return user_job_name;
        }

        public void setUser_job_name(String user_job_name) {
            this.user_job_name = user_job_name;
        }
    }
}
