package friendgoods.vidic.com.generalframework.Bean;

public class ShopOrderMoreBean {

    /**
     * data : {"creation_time":1545650365000,"number":"SP20181224111925041","voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","voucher_shop_name":"111","photo":"1541578006.jpg","add_dizhi":"咯喔喔哦","flag1":"0","id":6,"shop_num":"2"}
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
         * creation_time : 1545650365000
         * number : SP20181224111925041
         * voucher_shop_dimensions : 没有道
         * flag : 1
         * total_shop_voucher : 888
         * voucher_shop_voucher : 444
         * voucher_shop_name : 111
         * photo : 1541578006.jpg
         * add_dizhi : 咯喔喔哦
         * flag1 : 0
         * id : 6
         * shop_num : 2
         */

        private String creation_time;
        private String number;
        private String voucher_shop_dimensions;
        private String flag;
        private String total_shop_voucher;
        private String voucher_shop_voucher;
        private String voucher_shop_name;
        private String photo;
        private String add_dizhi;
        private String flag1;
        private int id;
        private String shop_num;

        public String getArea_diqu() {
            return area_diqu;
        }

        public void setArea_diqu(String area_diqu) {
            this.area_diqu = area_diqu;
        }

        private String area_diqu;

        public String getHair_time() {
            return hair_time;
        }

        public void setHair_time(String hair_time) {
            this.hair_time = hair_time;
        }

        private String hair_time;

        public String getCreation_time() {
            return creation_time;
        }

        public void setCreation_time(String creation_time) {
            this.creation_time = creation_time;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getVoucher_shop_dimensions() {
            return voucher_shop_dimensions;
        }

        public void setVoucher_shop_dimensions(String voucher_shop_dimensions) {
            this.voucher_shop_dimensions = voucher_shop_dimensions;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getTotal_shop_voucher() {
            return total_shop_voucher;
        }

        public void setTotal_shop_voucher(String total_shop_voucher) {
            this.total_shop_voucher = total_shop_voucher;
        }

        public String getVoucher_shop_voucher() {
            return voucher_shop_voucher;
        }

        public void setVoucher_shop_voucher(String voucher_shop_voucher) {
            this.voucher_shop_voucher = voucher_shop_voucher;
        }

        public String getVoucher_shop_name() {
            return voucher_shop_name;
        }

        public void setVoucher_shop_name(String voucher_shop_name) {
            this.voucher_shop_name = voucher_shop_name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getAdd_dizhi() {
            return add_dizhi;
        }

        public void setAdd_dizhi(String add_dizhi) {
            this.add_dizhi = add_dizhi;
        }

        public String getFlag1() {
            return flag1;
        }

        public void setFlag1(String flag1) {
            this.flag1 = flag1;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShop_num() {
            return shop_num;
        }

        public void setShop_num(String shop_num) {
            this.shop_num = shop_num;
        }
    }
}
