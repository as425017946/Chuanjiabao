package friendgoods.vidic.com.generalframework.Bean;

/**
 * 投资支付完成
 */
public class TouziOkPayBean {

    /**
     * state : 1
     * message : 请求成功
     * data : {"commonParamsKey1":"commonParamsValue1","commonParamsKey2":"这里支持中文参数","itemsNumber":"111111","itemsID":"21","userID":"22","buyNumber":"1","buyMoney":"500","itemsNo":"20180905164729056","id":31}
     */

    private int state;
    private String message;
    private DataBean data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commonParamsKey1 : commonParamsValue1
         * commonParamsKey2 : 这里支持中文参数
         * itemsNumber : 111111
         * itemsID : 21
         * userID : 22
         * buyNumber : 1
         * buyMoney : 500
         * itemsNo : 20180905164729056
         * id : 31
         */

        private String commonParamsKey1;
        private String commonParamsKey2;
        private String itemsNumber;
        private String itemsID;
        private String userID;
        private String buyNumber;
        private String buyMoney;
        private String itemsNo;
        private int id;

        public String getCommonParamsKey1() {
            return commonParamsKey1;
        }

        public void setCommonParamsKey1(String commonParamsKey1) {
            this.commonParamsKey1 = commonParamsKey1;
        }

        public String getCommonParamsKey2() {
            return commonParamsKey2;
        }

        public void setCommonParamsKey2(String commonParamsKey2) {
            this.commonParamsKey2 = commonParamsKey2;
        }

        public String getItemsNumber() {
            return itemsNumber;
        }

        public void setItemsNumber(String itemsNumber) {
            this.itemsNumber = itemsNumber;
        }

        public String getItemsID() {
            return itemsID;
        }

        public void setItemsID(String itemsID) {
            this.itemsID = itemsID;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getBuyNumber() {
            return buyNumber;
        }

        public void setBuyNumber(String buyNumber) {
            this.buyNumber = buyNumber;
        }

        public String getBuyMoney() {
            return buyMoney;
        }

        public void setBuyMoney(String buyMoney) {
            this.buyMoney = buyMoney;
        }

        public String getItemsNo() {
            return itemsNo;
        }

        public void setItemsNo(String itemsNo) {
            this.itemsNo = itemsNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
