package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class TaocanBean {

    /**
     * data : [{"name":"测试套餐方案1","introduction":"这是套餐详细说明，主要用途在于详细介绍一下套餐的基本情况，如何使用等一些消费问题，"},{"name":"测试套餐方案2","introduction":"这是套餐详细说明，主要用途在于详细介绍一下套餐的基本情况，如何使用等一些消费问题，"},{"name":"测试套餐方案3","introduction":"这是套餐详细说明，主要用途在于详细介绍一下套餐的基本情况，如何使用等一些消费问题，"},{"name":"测试套餐方案4","introduction":"这是套餐详细说明，主要用途在于详细介绍一下套餐的基本情况，如何使用等一些消费问题，"}]
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
         * name : 测试套餐方案1
         * introduction : 这是套餐详细说明，主要用途在于详细介绍一下套餐的基本情况，如何使用等一些消费问题，
         */

        private String name;
        private String introduction;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
