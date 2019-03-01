package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class MyOrderBean {

    /**
     * data : {"PageInfo":{"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案2","items_status":"3","status":"1"},{"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案1","items_status":"3","status":"1"},{"funding_start_money":"1000","items_photo1":"1541477192.jpg","number":"111","items_name":"天津津南物流","buy_num":"2","items_scheme_name":"方案一","items_status":"4","status":"1"},{"funding_start_money":"1000","items_photo1":"1541640044.jpg","number":"8","items_name":"青海西宁小吃","items_scheme_name":"方案1","items_status":"4","status":"2"},{"funding_start_money":"1000","items_photo1":"1541583003.jpg","number":"7","items_name":"天津市经济贸易","items_scheme_name":"众筹123","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"zhongc","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"xas","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"众筹1","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1541494424.jpg","number":"5","items_name":"河北仓库","items_scheme_name":"2222222","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1541494424.jpg","number":"5","items_name":"河北仓库","items_scheme_name":"222222","items_status":"0","status":"2"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":17}}
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
         * PageInfo : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案2","items_status":"3","status":"1"},{"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案1","items_status":"3","status":"1"},{"funding_start_money":"1000","items_photo1":"1541477192.jpg","number":"111","items_name":"天津津南物流","buy_num":"2","items_scheme_name":"方案一","items_status":"4","status":"1"},{"funding_start_money":"1000","items_photo1":"1541640044.jpg","number":"8","items_name":"青海西宁小吃","items_scheme_name":"方案1","items_status":"4","status":"2"},{"funding_start_money":"1000","items_photo1":"1541583003.jpg","number":"7","items_name":"天津市经济贸易","items_scheme_name":"众筹123","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"zhongc","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"xas","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"众筹1","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1541494424.jpg","number":"5","items_name":"河北仓库","items_scheme_name":"2222222","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1541494424.jpg","number":"5","items_name":"河北仓库","items_scheme_name":"222222","items_status":"0","status":"2"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":17}
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
             * endRow : 10
             * firstPage : 1
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 1
             * list : [{"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案2","items_status":"3","status":"1"},{"funding_start_money":"1000","items_photo1":"1542767942.jpg","number":"20181225143559184","items_name":"面包店","buy_num":"1","items_scheme_name":"方案1","items_status":"3","status":"1"},{"funding_start_money":"1000","items_photo1":"1541477192.jpg","number":"111","items_name":"天津津南物流","buy_num":"2","items_scheme_name":"方案一","items_status":"4","status":"1"},{"funding_start_money":"1000","items_photo1":"1541640044.jpg","number":"8","items_name":"青海西宁小吃","items_scheme_name":"方案1","items_status":"4","status":"2"},{"funding_start_money":"1000","items_photo1":"1541583003.jpg","number":"7","items_name":"天津市经济贸易","items_scheme_name":"众筹123","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"zhongc","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"xas","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1542001002.jpg","number":"6","items_name":"默斯诺","items_scheme_name":"众筹1","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1541494424.jpg","number":"5","items_name":"河北仓库","items_scheme_name":"2222222","items_status":"0","status":"2"},{"funding_start_money":"1000","items_photo1":"1541494424.jpg","number":"5","items_name":"河北仓库","items_scheme_name":"222222","items_status":"0","status":"2"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 10
             * pages : 2
             * prePage : 0
             * size : 10
             * startRow : 1
             * total : 17
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
                 * funding_start_money : 1000
                 * items_photo1 : 1542767942.jpg
                 * number : 20181225143559184
                 * items_name : 面包店
                 * buy_num : 1
                 * items_scheme_name : 方案2
                 * items_status : 3
                 * status : 1
                 */

                private String funding_start_money;
                private String items_photo1;
                private String number;
                private String items_name;
                private String buy_num;
                private String items_scheme_name;
                private String items_status;
                private String status;

                public String getFunding_start_money() {
                    return funding_start_money;
                }

                public void setFunding_start_money(String funding_start_money) {
                    this.funding_start_money = funding_start_money;
                }

                public String getItems_photo1() {
                    return items_photo1;
                }

                public void setItems_photo1(String items_photo1) {
                    this.items_photo1 = items_photo1;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public String getItems_name() {
                    return items_name;
                }

                public void setItems_name(String items_name) {
                    this.items_name = items_name;
                }

                public String getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(String buy_num) {
                    this.buy_num = buy_num;
                }

                public String getItems_scheme_name() {
                    return items_scheme_name;
                }

                public void setItems_scheme_name(String items_scheme_name) {
                    this.items_scheme_name = items_scheme_name;
                }

                public String getItems_status() {
                    return items_status;
                }

                public void setItems_status(String items_status) {
                    this.items_status = items_status;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
