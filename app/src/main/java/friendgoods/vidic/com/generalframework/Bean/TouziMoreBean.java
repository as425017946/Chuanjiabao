package friendgoods.vidic.com.generalframework.Bean;

/**
 * 投资详情页面
 */
public class TouziMoreBean {
    /**
     * data : {"items_name":"天津津南物流","public_file_name":"ceshi.txt","user_name":"发起人名字","items_buy_money":"1000000","num":4,"private_file_url":"ceshi-1541477262.txt","items_area":"200","items_site":"天津-津南","items_comment":"补充说明","private_file_name":"ceshi.txt","funding_time":"5","id":70,"day":1796,"items_photo1":"1541477192.jpg","items_number":"BH20181106120040751","items_earnings_introduce":"回报方案","sumMoney":4200,"funding_count_money":"100000","items_now":"4","funding_type":"3","items_status":"4","buy_day_end":"2018-11-10","funding_start_money":"1000","items_money":"1000000","items_quantity":"15","items_unit_price":"200","items_type":"更多","items_estate":"2","public_file_url":"ceshi-1541477258.txt"}
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
         * items_name : 天津津南物流
         * public_file_name : ceshi.txt
         * user_name : 发起人名字
         * items_buy_money : 1000000
         * num : 4.0
         * private_file_url : ceshi-1541477262.txt
         * items_area : 200
         * items_site : 天津-津南
         * items_comment : 补充说明
         * private_file_name : ceshi.txt
         * funding_time : 5
         * id : 70
         * day : 1796
         * items_photo1 : 1541477192.jpg
         * items_number : BH20181106120040751
         * items_earnings_introduce : 回报方案
         * sumMoney : 4200.0
         * funding_count_money : 100000
         * items_now : 4
         * funding_type : 3
         * items_status : 4
         * buy_day_end : 2018-11-10
         * funding_start_money : 1000
         * items_money : 1000000
         * items_quantity : 15
         * items_unit_price : 200
         * items_type : 更多
         * items_estate : 2
         * public_file_url : ceshi-1541477258.txt
         */

        private String items_name;
        private String public_file_name;
        private String user_name;
        private String items_buy_money;
        private double num;
        private String private_file_url;
        private String items_area;
        private String items_site;
        private String items_comment;
        private String private_file_name;
        private String funding_time;
        private int id;
        private int day;
        private String items_photo1;
        private String items_number;
        private String items_earnings_introduce;

        public String getSumMoney() {
            return sumMoney;
        }

        private String sumMoney;
        private String funding_count_money;
        private String items_now;
        private String funding_type;
        private String items_status;
        private String buy_day_end;
        private String funding_start_money;
        private String items_money;
        private String items_quantity;
        private String items_unit_price;
        private String items_type;
        private String items_estate;
        private String public_file_url;

        public void setSumMoney(String sumMoney) {
            this.sumMoney = sumMoney;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        private String money;

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getItems_buy_money() {
            return items_buy_money;
        }

        public void setItems_buy_money(String items_buy_money) {
            this.items_buy_money = items_buy_money;
        }

        public double getNum() {
            return num;
        }

        public void setNum(double num) {
            this.num = num;
        }

        public String getPrivate_file_url() {
            return private_file_url;
        }

        public void setPrivate_file_url(String private_file_url) {
            this.private_file_url = private_file_url;
        }

        public String getItems_area() {
            return items_area;
        }

        public void setItems_area(String items_area) {
            this.items_area = items_area;
        }

        public String getItems_site() {
            return items_site;
        }

        public void setItems_site(String items_site) {
            this.items_site = items_site;
        }

        public String getItems_comment() {
            return items_comment;
        }

        public void setItems_comment(String items_comment) {
            this.items_comment = items_comment;
        }

        public String getPrivate_file_name() {
            return private_file_name;
        }

        public void setPrivate_file_name(String private_file_name) {
            this.private_file_name = private_file_name;
        }

        public String getFunding_time() {
            return funding_time;
        }

        public void setFunding_time(String funding_time) {
            this.funding_time = funding_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getItems_photo1() {
            return items_photo1;
        }

        public void setItems_photo1(String items_photo1) {
            this.items_photo1 = items_photo1;
        }

        public String getItems_number() {
            return items_number;
        }

        public void setItems_number(String items_number) {
            this.items_number = items_number;
        }

        public String getItems_earnings_introduce() {
            return items_earnings_introduce;
        }

        public void setItems_earnings_introduce(String items_earnings_introduce) {
            this.items_earnings_introduce = items_earnings_introduce;
        }


        public String getFunding_count_money() {
            return funding_count_money;
        }

        public void setFunding_count_money(String funding_count_money) {
            this.funding_count_money = funding_count_money;
        }

        public String getItems_now() {
            return items_now;
        }

        public void setItems_now(String items_now) {
            this.items_now = items_now;
        }

        public String getFunding_type() {
            return funding_type;
        }

        public void setFunding_type(String funding_type) {
            this.funding_type = funding_type;
        }

        public String getItems_status() {
            return items_status;
        }

        public void setItems_status(String items_status) {
            this.items_status = items_status;
        }

        public String getBuy_day_end() {
            return buy_day_end;
        }

        public void setBuy_day_end(String buy_day_end) {
            this.buy_day_end = buy_day_end;
        }

        public String getFunding_start_money() {
            return funding_start_money;
        }

        public void setFunding_start_money(String funding_start_money) {
            this.funding_start_money = funding_start_money;
        }

        public String getItems_money() {
            return items_money;
        }

        public void setItems_money(String items_money) {
            this.items_money = items_money;
        }

        public String getItems_quantity() {
            return items_quantity;
        }

        public void setItems_quantity(String items_quantity) {
            this.items_quantity = items_quantity;
        }

        public String getItems_unit_price() {
            return items_unit_price;
        }

        public void setItems_unit_price(String items_unit_price) {
            this.items_unit_price = items_unit_price;
        }

        public String getItems_type() {
            return items_type;
        }

        public void setItems_type(String items_type) {
            this.items_type = items_type;
        }

        public String getItems_estate() {
            return items_estate;
        }

        public void setItems_estate(String items_estate) {
            this.items_estate = items_estate;
        }

        public String getPublic_file_url() {
            return public_file_url;
        }

        public void setPublic_file_url(String public_file_url) {
            this.public_file_url = public_file_url;
        }
    }
}
