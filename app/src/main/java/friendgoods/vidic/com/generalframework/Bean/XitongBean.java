package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class XitongBean {

    /**
     * data : {"PageInfo":{"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"creation_time":1541502661000,"items_number":"0888888880","items_news_content":"房产房产","items_news_title":"VVVVVVVV","items_news_url":"www.baidu.com","id":5},{"creation_time":1541602364000,"items_number":"2060000050","items_news_content":"ixmm","items_news_title":"好好哈","items_news_url":"三星索尼","id":6},{"creation_time":1541602405000,"items_number":"俄文的金额为你的我觉得","items_news_content":"kdkd","items_news_title":"能实现接口性能","items_news_url":"你参加你的节操呢","id":7},{"creation_time":1541668062000,"items_number":"BH20181106120040751","items_news_content":"asd","items_news_title":"sada","items_news_url":"http://www.baidu.com","id":8},{"creation_time":1541674598000,"items_number":"BH20181106120040751","items_news_content":"frefre","items_news_title":"feferfr","items_news_url":"http://www.baidu.com","id":9}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":5,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}}
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
         * PageInfo : {"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"creation_time":1541502661000,"items_number":"0888888880","items_news_content":"房产房产","items_news_title":"VVVVVVVV","items_news_url":"www.baidu.com","id":5},{"creation_time":1541602364000,"items_number":"2060000050","items_news_content":"ixmm","items_news_title":"好好哈","items_news_url":"三星索尼","id":6},{"creation_time":1541602405000,"items_number":"俄文的金额为你的我觉得","items_news_content":"kdkd","items_news_title":"能实现接口性能","items_news_url":"你参加你的节操呢","id":7},{"creation_time":1541668062000,"items_number":"BH20181106120040751","items_news_content":"asd","items_news_title":"sada","items_news_url":"http://www.baidu.com","id":8},{"creation_time":1541674598000,"items_number":"BH20181106120040751","items_news_content":"frefre","items_news_title":"feferfr","items_news_url":"http://www.baidu.com","id":9}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":5,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
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
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"creation_time":1541502661000,"items_number":"0888888880","items_news_content":"房产房产","items_news_title":"VVVVVVVV","items_news_url":"www.baidu.com","id":5},{"creation_time":1541602364000,"items_number":"2060000050","items_news_content":"ixmm","items_news_title":"好好哈","items_news_url":"三星索尼","id":6},{"creation_time":1541602405000,"items_number":"俄文的金额为你的我觉得","items_news_content":"kdkd","items_news_title":"能实现接口性能","items_news_url":"你参加你的节操呢","id":7},{"creation_time":1541668062000,"items_number":"BH20181106120040751","items_news_content":"asd","items_news_title":"sada","items_news_url":"http://www.baidu.com","id":8},{"creation_time":1541674598000,"items_number":"BH20181106120040751","items_news_content":"frefre","items_news_title":"feferfr","items_news_url":"http://www.baidu.com","id":9}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 5
             * pages : 1
             * prePage : 0
             * size : 5
             * startRow : 1
             * total : 5
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
                 * creation_time : 1541502661000
                 * items_number : 0888888880
                 * items_news_content : 房产房产
                 * items_news_title : VVVVVVVV
                 * items_news_url : www.baidu.com
                 * id : 5
                 */

                private String creation_time;
                private String items_number;
                private String items_news_content;
                private String items_news_title;
                private String items_news_url;
                private int id;

                public String getCreation_time() {
                    return creation_time;
                }

                public void setCreation_time(String creation_time) {
                    this.creation_time = creation_time;
                }

                public String getItems_number() {
                    return items_number;
                }

                public void setItems_number(String items_number) {
                    this.items_number = items_number;
                }

                public String getItems_news_content() {
                    return items_news_content;
                }

                public void setItems_news_content(String items_news_content) {
                    this.items_news_content = items_news_content;
                }

                public String getItems_news_title() {
                    return items_news_title;
                }

                public void setItems_news_title(String items_news_title) {
                    this.items_news_title = items_news_title;
                }

                public String getItems_news_url() {
                    return items_news_url;
                }

                public void setItems_news_url(String items_news_url) {
                    this.items_news_url = items_news_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
