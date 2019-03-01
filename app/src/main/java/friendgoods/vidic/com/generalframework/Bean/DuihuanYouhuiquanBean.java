package friendgoods.vidic.com.generalframework.Bean;

public class DuihuanYouhuiquanBean {

    /**
     * data : {"voucher_code":"e21ffbec65a64512bf9c5382aed9af01"}
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
         * voucher_code : e21ffbec65a64512bf9c5382aed9af01
         */

        private String voucher_code;

        public String getVoucher_code() {
            return voucher_code;
        }

        public void setVoucher_code(String voucher_code) {
            this.voucher_code = voucher_code;
        }
    }
}
