package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class ShowyouhuiquanBean {

    /**
     * data : {"PageInfo":{"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"end_day":"2018-12-27","reduce_money":"19","user_uuid":"999311a1f8914b0696e9d5e6d240113a","items_number":"999311a1f8914b0696e9d5e6d240113a","start_day":"2018-12-11","amount_money":"20","id":513934,"voucher_number":"DJ20181211173127691","type":"1"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10000,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}}
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
         * PageInfo : {"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"end_day":"2018-12-27","reduce_money":"19","user_uuid":"999311a1f8914b0696e9d5e6d240113a","items_number":"999311a1f8914b0696e9d5e6d240113a","start_day":"2018-12-11","amount_money":"20","id":513934,"voucher_number":"DJ20181211173127691","type":"1"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10000,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}
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
             * endRow : 1
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"end_day":"2018-12-27","reduce_money":"19","user_uuid":"999311a1f8914b0696e9d5e6d240113a","items_number":"999311a1f8914b0696e9d5e6d240113a","start_day":"2018-12-11","amount_money":"20","id":513934,"voucher_number":"DJ20181211173127691","type":"1"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 10000
             * pages : 1
             * prePage : 0
             * size : 1
             * startRow : 1
             * total : 1
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
                 * end_day : 2018-12-27
                 * reduce_money : 19
                 * user_uuid : 999311a1f8914b0696e9d5e6d240113a
                 * items_number : 999311a1f8914b0696e9d5e6d240113a
                 * start_day : 2018-12-11
                 * amount_money : 20
                 * id : 513934
                 * voucher_number : DJ20181211173127691
                 * type : 1
                 */

                private String end_day;
                private String reduce_money;
                private String user_uuid;
                private String items_number;
                private String start_day;
                private String amount_money;
                private int id;
                private String voucher_number;
                private String type;

                public String getItems_name() {
                    return items_name;
                }

                public void setItems_name(String items_name) {
                    this.items_name = items_name;
                }

                private String items_name;

                public String getEnd_day() {
                    return end_day;
                }

                public void setEnd_day(String end_day) {
                    this.end_day = end_day;
                }

                public String getReduce_money() {
                    return reduce_money;
                }

                public void setReduce_money(String reduce_money) {
                    this.reduce_money = reduce_money;
                }

                public String getUser_uuid() {
                    return user_uuid;
                }

                public void setUser_uuid(String user_uuid) {
                    this.user_uuid = user_uuid;
                }

                public String getItems_number() {
                    return items_number;
                }

                public void setItems_number(String items_number) {
                    this.items_number = items_number;
                }

                public String getStart_day() {
                    return start_day;
                }

                public void setStart_day(String start_day) {
                    this.start_day = start_day;
                }

                public String getAmount_money() {
                    return amount_money;
                }

                public void setAmount_money(String amount_money) {
                    this.amount_money = amount_money;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getVoucher_number() {
                    return voucher_number;
                }

                public void setVoucher_number(String voucher_number) {
                    this.voucher_number = voucher_number;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
