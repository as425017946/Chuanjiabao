package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

/**
 * 投后管理
 */

public class AfterAdminBean {

    /**
     * data : {"PageInfo":{"endRow":5,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"items_photo1":"1541484892.jpg","funding_start_money":"1000","items_name":"天津河西烟草","number":"3","items_scheme_name":"方案22","items_now":"1","items_status":"4"},{"items_photo1":"1541490399.jpg","funding_start_money":"1000","items_name":"北京市海淀区高级娱乐","number":"4","items_scheme_name":"众筹0","items_now":"4","items_status":"4"},{"items_photo1":"1541583003.jpg","funding_start_money":"1000","items_name":"天津市经济贸易","number":"7","items_scheme_name":"众筹123","items_now":"4","items_status":"0"},{"items_photo1":"1541494424.jpg","funding_start_money":"1000","items_name":"河北仓库","number":"5","items_scheme_name":"11","items_now":"2","items_status":"0"},{"items_photo1":"1541494424.jpg","funding_start_money":"1000","items_name":"河北仓库","number":"5","items_scheme_name":"111","items_now":"2","items_status":"0"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":5,"pages":3,"prePage":0,"size":5,"startRow":1,"total":14}}
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
         * PageInfo : {"endRow":5,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"items_photo1":"1541484892.jpg","funding_start_money":"1000","items_name":"天津河西烟草","number":"3","items_scheme_name":"方案22","items_now":"1","items_status":"4"},{"items_photo1":"1541490399.jpg","funding_start_money":"1000","items_name":"北京市海淀区高级娱乐","number":"4","items_scheme_name":"众筹0","items_now":"4","items_status":"4"},{"items_photo1":"1541583003.jpg","funding_start_money":"1000","items_name":"天津市经济贸易","number":"7","items_scheme_name":"众筹123","items_now":"4","items_status":"0"},{"items_photo1":"1541494424.jpg","funding_start_money":"1000","items_name":"河北仓库","number":"5","items_scheme_name":"11","items_now":"2","items_status":"0"},{"items_photo1":"1541494424.jpg","funding_start_money":"1000","items_name":"河北仓库","number":"5","items_scheme_name":"111","items_now":"2","items_status":"0"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":5,"pages":3,"prePage":0,"size":5,"startRow":1,"total":14}
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
             * endRow : 5
             * firstPage : 1
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 1
             * list : [{"items_photo1":"1541484892.jpg","funding_start_money":"1000","items_name":"天津河西烟草","number":"3","items_scheme_name":"方案22","items_now":"1","items_status":"4"},{"items_photo1":"1541490399.jpg","funding_start_money":"1000","items_name":"北京市海淀区高级娱乐","number":"4","items_scheme_name":"众筹0","items_now":"4","items_status":"4"},{"items_photo1":"1541583003.jpg","funding_start_money":"1000","items_name":"天津市经济贸易","number":"7","items_scheme_name":"众筹123","items_now":"4","items_status":"0"},{"items_photo1":"1541494424.jpg","funding_start_money":"1000","items_name":"河北仓库","number":"5","items_scheme_name":"11","items_now":"2","items_status":"0"},{"items_photo1":"1541494424.jpg","funding_start_money":"1000","items_name":"河北仓库","number":"5","items_scheme_name":"111","items_now":"2","items_status":"0"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 5
             * pages : 3
             * prePage : 0
             * size : 5
             * startRow : 1
             * total : 14
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
                 * items_photo1 : 1541484892.jpg
                 * funding_start_money : 1000
                 * items_name : 天津河西烟草
                 * number : 3
                 * items_scheme_name : 方案22
                 * items_now : 1
                 * items_status : 4
                 */

                private String items_photo1;
                private String funding_start_money;
                private String items_name;
                private String number;
                private String items_scheme_name;
                private String items_now;
                private String items_status;
                private String buy_num;

                public String getItems_number() {
                    return items_number;
                }

                public void setItems_number(String items_number) {
                    this.items_number = items_number;
                }

                private String items_number;
                public String getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(String buy_num) {
                    this.buy_num = buy_num;
                }

                public String getItems_photo1() {
                    return items_photo1;
                }

                public void setItems_photo1(String items_photo1) {
                    this.items_photo1 = items_photo1;
                }

                public String getFunding_start_money() {
                    return funding_start_money;
                }

                public void setFunding_start_money(String funding_start_money) {
                    this.funding_start_money = funding_start_money;
                }

                public String getItems_name() {
                    return items_name;
                }

                public void setItems_name(String items_name) {
                    this.items_name = items_name;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public String getItems_scheme_name() {
                    return items_scheme_name;
                }

                public void setItems_scheme_name(String items_scheme_name) {
                    this.items_scheme_name = items_scheme_name;
                }

                public String getItems_now() {
                    return items_now;
                }

                public void setItems_now(String items_now) {
                    this.items_now = items_now;
                }

                public String getItems_status() {
                    return items_status;
                }

                public void setItems_status(String items_status) {
                    this.items_status = items_status;
                }
            }
        }
    }
}
