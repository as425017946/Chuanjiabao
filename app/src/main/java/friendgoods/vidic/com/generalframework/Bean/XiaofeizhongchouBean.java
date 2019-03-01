package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class XiaofeizhongchouBean {

    /**
     * data : {"PageInfo":{"endRow":7,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"111","items_number":"111111","items_type":"11","funding_type":"2","items_introduce":"111","ID":21,"items_site":"11","day":112,"items_status":"3","items_contacts":"111"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"东丽海岸胡","items_number":"1234567","items_type":"民宿","funding_type":"1","items_introduce":"这是项目简介","ID":6,"items_site":"天津东丽","day":112,"items_status":"3","items_contacts":"王五"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"水滴足球场","items_number":"123456","items_type":"更多","funding_type":"2","items_introduce":"这是项目简介","ID":5,"items_site":"天津和平","day":112,"items_status":"3","items_contacts":"李四"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"海昌天澜假日酒店","items_number":"12345","items_type":"酒店","funding_type":"2","items_introduce":"这是项目简介","ID":4,"items_site":"天津塘沽","day":112,"items_status":"3","items_contacts":"张三"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"滨海建国酒店","items_number":"1234","items_type":"酒店","funding_type":"2","items_introduce":"这是项目简介","ID":3,"items_site":"天津塘沽","day":112,"items_status":"3","items_contacts":"吴硕"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"222","items_number":"222222","items_type":"222","funding_type":"2","items_introduce":"22","ID":22,"items_site":"222","day":112,"items_status":"4","items_contacts":"222"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"333","items_number":"333333","items_type":"333","funding_type":"3","items_introduce":"33","ID":23,"items_site":"333","day":112,"items_status":"4","items_contacts":"333"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":7,"startRow":1,"total":7}}
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
         * PageInfo : {"endRow":7,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"111","items_number":"111111","items_type":"11","funding_type":"2","items_introduce":"111","ID":21,"items_site":"11","day":112,"items_status":"3","items_contacts":"111"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"东丽海岸胡","items_number":"1234567","items_type":"民宿","funding_type":"1","items_introduce":"这是项目简介","ID":6,"items_site":"天津东丽","day":112,"items_status":"3","items_contacts":"王五"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"水滴足球场","items_number":"123456","items_type":"更多","funding_type":"2","items_introduce":"这是项目简介","ID":5,"items_site":"天津和平","day":112,"items_status":"3","items_contacts":"李四"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"海昌天澜假日酒店","items_number":"12345","items_type":"酒店","funding_type":"2","items_introduce":"这是项目简介","ID":4,"items_site":"天津塘沽","day":112,"items_status":"3","items_contacts":"张三"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"滨海建国酒店","items_number":"1234","items_type":"酒店","funding_type":"2","items_introduce":"这是项目简介","ID":3,"items_site":"天津塘沽","day":112,"items_status":"3","items_contacts":"吴硕"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"222","items_number":"222222","items_type":"222","funding_type":"2","items_introduce":"22","ID":22,"items_site":"222","day":112,"items_status":"4","items_contacts":"222"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"333","items_number":"333333","items_type":"333","funding_type":"3","items_introduce":"33","ID":23,"items_site":"333","day":112,"items_status":"4","items_contacts":"333"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":7,"startRow":1,"total":7}
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
             * endRow : 7
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"111","items_number":"111111","items_type":"11","funding_type":"2","items_introduce":"111","ID":21,"items_site":"11","day":112,"items_status":"3","items_contacts":"111"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"东丽海岸胡","items_number":"1234567","items_type":"民宿","funding_type":"1","items_introduce":"这是项目简介","ID":6,"items_site":"天津东丽","day":112,"items_status":"3","items_contacts":"王五"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"水滴足球场","items_number":"123456","items_type":"更多","funding_type":"2","items_introduce":"这是项目简介","ID":5,"items_site":"天津和平","day":112,"items_status":"3","items_contacts":"李四"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"海昌天澜假日酒店","items_number":"12345","items_type":"酒店","funding_type":"2","items_introduce":"这是项目简介","ID":4,"items_site":"天津塘沽","day":112,"items_status":"3","items_contacts":"张三"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"滨海建国酒店","items_number":"1234","items_type":"酒店","funding_type":"2","items_introduce":"这是项目简介","ID":3,"items_site":"天津塘沽","day":112,"items_status":"3","items_contacts":"吴硕"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"222","items_number":"222222","items_type":"222","funding_type":"2","items_introduce":"22","ID":22,"items_site":"222","day":112,"items_status":"4","items_contacts":"222"},{"items_photo1":"1534815581.jpg,1534815586.jpg,1534815589.jpg,","items_name":"333","items_number":"333333","items_type":"333","funding_type":"3","items_introduce":"33","ID":23,"items_site":"333","day":112,"items_status":"4","items_contacts":"333"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 20
             * pages : 1
             * prePage : 0
             * size : 7
             * startRow : 1
             * total : 7
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
                 * items_photo1 : 1534815581.jpg,1534815586.jpg,1534815589.jpg,
                 * items_name : 111
                 * items_number : 111111
                 * items_type : 11
                 * funding_type : 2
                 * items_introduce : 111
                 * ID : 21
                 * items_site : 11
                 * day : 112
                 * items_status : 3
                 * items_contacts : 111
                 */

                private String items_photo1;
                private String items_name;
                private String items_number;
                private String items_type;
                private String funding_type;
                private String items_introduce;
                private int ID;
                private String items_site;
                private int day;
                private String items_status;
                private String items_contacts;

                public String getItems_photo1() {
                    return items_photo1;
                }

                public void setItems_photo1(String items_photo1) {
                    this.items_photo1 = items_photo1;
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

                public String getFunding_type() {
                    return funding_type;
                }

                public void setFunding_type(String funding_type) {
                    this.funding_type = funding_type;
                }

                public String getItems_introduce() {
                    return items_introduce;
                }

                public void setItems_introduce(String items_introduce) {
                    this.items_introduce = items_introduce;
                }

                public int getID() {
                    return ID;
                }

                public void setID(int ID) {
                    this.ID = ID;
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

                public String getItems_contacts() {
                    return items_contacts;
                }

                public void setItems_contacts(String items_contacts) {
                    this.items_contacts = items_contacts;
                }
            }
        }
    }
}
