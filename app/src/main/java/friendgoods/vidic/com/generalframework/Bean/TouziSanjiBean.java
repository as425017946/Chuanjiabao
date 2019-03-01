package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

/**
 * 投资三级界面
 */
public class TouziSanjiBean {

    /**
     * data : [{"funding_start_money":"1000","surplus_num":"8","items_earnings_introduce":"回报方案回报方案","items_scheme_name":"方案1","num":"10","limitation_number":"10"},{"funding_start_money":"1000","surplus_num":"7","items_earnings_introduce":"回报方案回报方案","items_scheme_name":"方案2","num":"10","limitation_number":"10"}]
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
         * funding_start_money : 1000
         * surplus_num : 8
         * items_earnings_introduce : 回报方案回报方案
         * items_scheme_name : 方案1
         * num : 10
         * limitation_number : 10
         */

        private String funding_start_money;
        private String surplus_num;
        private String items_earnings_introduce;
        private String items_scheme_name;
        private String num;
        private String limitation_number;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFunding_start_money() {
            return funding_start_money;
        }

        public void setFunding_start_money(String funding_start_money) {
            this.funding_start_money = funding_start_money;
        }

        public String getSurplus_num() {
            return surplus_num;
        }

        public void setSurplus_num(String surplus_num) {
            this.surplus_num = surplus_num;
        }

        public String getItems_earnings_introduce() {
            return items_earnings_introduce;
        }

        public void setItems_earnings_introduce(String items_earnings_introduce) {
            this.items_earnings_introduce = items_earnings_introduce;
        }

        public String getItems_scheme_name() {
            return items_scheme_name;
        }

        public void setItems_scheme_name(String items_scheme_name) {
            this.items_scheme_name = items_scheme_name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getLimitation_number() {
            return limitation_number;
        }

        public void setLimitation_number(String limitation_number) {
            this.limitation_number = limitation_number;
        }
    }
}
