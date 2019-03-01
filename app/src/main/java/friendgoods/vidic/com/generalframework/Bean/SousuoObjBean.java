package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class SousuoObjBean {

    /**
     * data : {"PageInfo":{"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"items_photo1":"1541477192.jpg","items_money":"1000000","funding_start_money":"1000","items_name":"天津津南物流","items_number":"BH20181106120040751","items_type":"更多","items_site":"天津-津南","day":-40,"items_status":"4","items_comment":"补充说明"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}}
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
         * PageInfo : {"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"items_photo1":"1541477192.jpg","items_money":"1000000","funding_start_money":"1000","items_name":"天津津南物流","items_number":"BH20181106120040751","items_type":"更多","items_site":"天津-津南","day":-40,"items_status":"4","items_comment":"补充说明"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}
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
             * list : [{"items_photo1":"1541477192.jpg","items_money":"1000000","funding_start_money":"1000","items_name":"天津津南物流","items_number":"BH20181106120040751","items_type":"更多","items_site":"天津-津南","day":-40,"items_status":"4","items_comment":"补充说明"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 20
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
                 * items_photo1 : 1541477192.jpg
                 * items_money : 1000000
                 * funding_start_money : 1000
                 * items_name : 天津津南物流
                 * items_number : BH20181106120040751
                 * items_type : 更多
                 * items_site : 天津-津南
                 * day : -40
                 * items_status : 4
                 * items_comment : 补充说明
                 */

                private String items_photo1;
                private String items_money;
                private String funding_start_money;
                private String items_name;
                private String items_number;
                private String items_type;
                private String items_site;
                private int day;
                private String items_status;
                private String items_comment;

                public String getItems_photo1() {
                    return items_photo1;
                }

                public void setItems_photo1(String items_photo1) {
                    this.items_photo1 = items_photo1;
                }

                public String getItems_money() {
                    return items_money;
                }

                public void setItems_money(String items_money) {
                    this.items_money = items_money;
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

                public String getItems_number() {
                    return items_number;
                }

                public void setItems_number(String items_number) {
                    this.items_number = items_number;
                }

                public String getItems_type() {
                    return items_type;
                }

                public void setItems_type(String items_type) {
                    this.items_type = items_type;
                }

                public String getItems_site() {
                    return items_site;
                }

                public void setItems_site(String items_site) {
                    this.items_site = items_site;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public String getItems_status() {
                    return items_status;
                }

                public void setItems_status(String items_status) {
                    this.items_status = items_status;
                }

                public String getItems_comment() {
                    return items_comment;
                }

                public void setItems_comment(String items_comment) {
                    this.items_comment = items_comment;
                }
            }
        }
    }
}
