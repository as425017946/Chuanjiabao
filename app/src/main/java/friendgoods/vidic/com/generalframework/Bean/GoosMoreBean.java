package friendgoods.vidic.com.generalframework.Bean;

public class GoosMoreBean {

    /**
     * data : {"voucher":"333,111,","name":"123","photo":"1541574748.jpg","id":12,"particulars":"1541574751.jpg","dimensions":"不知道,wu,"}
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
         * voucher : 333,111,
         * name : 123
         * photo : 1541574748.jpg
         * id : 12
         * particulars : 1541574751.jpg
         * dimensions : 不知道,wu,
         */

        private String voucher;
        private String name;
        private String photo;
        private int id;
        private String particulars;
        private String dimensions;

        public String getVoucher() {
            return voucher;
        }

        public void setVoucher(String voucher) {
            this.voucher = voucher;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParticulars() {
            return particulars;
        }

        public void setParticulars(String particulars) {
            this.particulars = particulars;
        }

        public String getDimensions() {
            return dimensions;
        }

        public void setDimensions(String dimensions) {
            this.dimensions = dimensions;
        }
    }
}
