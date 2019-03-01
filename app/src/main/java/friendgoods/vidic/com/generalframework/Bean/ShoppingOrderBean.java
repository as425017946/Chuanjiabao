package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class ShoppingOrderBean {

    /**
     * data : {"PageInfo":{"endRow":6,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","createTime":"2018-12-24","voucher_shop_name":"111","photo":"1541578006.jpg","flag1":"0","id":6,"shop_num":"2"},{"voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","createTime":"2018-12-24","voucher_shop_name":"111","photo":"1541578006.jpg","flag1":"0","id":7,"shop_num":"2"},{"voucher_shop_dimensions":"嗯嗯道","flag":"1","total_shop_voucher":"66","voucher_shop_voucher":"66","createTime":"2018-12-24","voucher_shop_name":"erfr","photo":"1541645660.jpg","flag1":"0","id":5,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":2,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":3,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":4,"shop_num":"1"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":6,"startRow":1,"total":6}}
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
         * PageInfo : {"endRow":6,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","createTime":"2018-12-24","voucher_shop_name":"111","photo":"1541578006.jpg","flag1":"0","id":6,"shop_num":"2"},{"voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","createTime":"2018-12-24","voucher_shop_name":"111","photo":"1541578006.jpg","flag1":"0","id":7,"shop_num":"2"},{"voucher_shop_dimensions":"嗯嗯道","flag":"1","total_shop_voucher":"66","voucher_shop_voucher":"66","createTime":"2018-12-24","voucher_shop_name":"erfr","photo":"1541645660.jpg","flag1":"0","id":5,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":2,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":3,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":4,"shop_num":"1"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":6,"startRow":1,"total":6}
         */

        private PageInfoBean PageInfo;

        public PageInfoBean getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(PageInfoBean PageInfo) {
            this.PageInfo = PageInfo;
        }

        public static class PageInfoBean {
            /**
             * endRow : 6
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","createTime":"2018-12-24","voucher_shop_name":"111","photo":"1541578006.jpg","flag1":"0","id":6,"shop_num":"2"},{"voucher_shop_dimensions":"没有道","flag":"1","total_shop_voucher":"888","voucher_shop_voucher":"444","createTime":"2018-12-24","voucher_shop_name":"111","photo":"1541578006.jpg","flag1":"0","id":7,"shop_num":"2"},{"voucher_shop_dimensions":"嗯嗯道","flag":"1","total_shop_voucher":"66","voucher_shop_voucher":"66","createTime":"2018-12-24","voucher_shop_name":"erfr","photo":"1541645660.jpg","flag1":"0","id":5,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":2,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":3,"shop_num":"1"},{"voucher_shop_dimensions":"1","flag":"1","total_shop_voucher":"1","voucher_shop_voucher":"1","createTime":"2018-12-24","voucher_shop_name":"1","flag1":"0","id":4,"shop_num":"1"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 10
             * pages : 1
             * prePage : 0
             * size : 6
             * startRow : 1
             * total : 6
             */

            private int endRow;
            private int firstPage;
            private boolean hasNextPage;
            private boolean hasPreviousPage;
            private boolean isFirstPage;
            private boolean isLastPage;
            private int lastPage;
            private int navigatePages;
            private int nextPage;
            private int pageNum;
            private int pageSize;
            private int pages;
            private int prePage;
            private int size;
            private int startRow;
            private int total;
            private List<ListBean> list;
            private List<Integer> navigatepageNums;

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean {
                /**
                 * voucher_shop_dimensions : 没有道
                 * flag : 1
                 * total_shop_voucher : 888
                 * voucher_shop_voucher : 444
                 * createTime : 2018-12-24
                 * voucher_shop_name : 111
                 * photo : 1541578006.jpg
                 * flag1 : 0
                 * id : 6
                 * shop_num : 2
                 */

                private String voucher_shop_dimensions;
                private String flag;
                private String total_shop_voucher;
                private String voucher_shop_voucher;
                private String createTime;
                private String voucher_shop_name;
                private String photo;
                private String flag1;
                private int id;
                private String shop_num;

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

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
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
    }
}
