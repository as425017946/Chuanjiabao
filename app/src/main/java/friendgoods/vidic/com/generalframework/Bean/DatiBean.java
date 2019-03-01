package friendgoods.vidic.com.generalframework.Bean;

import java.util.List;

public class DatiBean {

    /**
     * data : {"PageInfo":{"endRow":15,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"answer_b":"26-35岁","topic":"您的年龄","answer_a":"25岁以下","answer_d":"46-55岁","id":1,"answer_c":"36-45岁","answer_e":"56岁以上 "},{"answer_b":"自由职业","topic":"您的职业","answer_a":"国企、公务员、事业单位","answer_d":"自营企业","id":2,"answer_c":"外企","answer_e":"其他"},{"answer_b":"1人","topic":"您的家庭情况：需要依赖您抚养、无经济来源的家庭成员人数是","answer_a":"无","answer_d":"3人","id":3,"answer_c":"2人","answer_e":"4人或4人以上"},{"answer_b":"5-20万","topic":"您的家庭收入情况","answer_a":"5万以内","answer_d":"50-100万","id":4,"answer_c":"20-50万","answer_e":"100万以上"},{"answer_b":"10%以内","topic":"被动收入在您的家庭收入中占多少比例","answer_a":"无","answer_d":"30%-50%","id":5,"answer_c":"10%-30%","answer_e":"50%以上"},{"answer_b":"网站","topic":"您是通过哪些渠道了解到传家宝平台的","answer_a":"公众号","answer_d":"好友推荐邀请","id":6,"answer_c":"线下活动","answer_e":"自行了解投资类的产品"},{"answer_b":"10%-25%","topic":"地产类投资（不包括自用住房）在您的总投资金额中占比多少","answer_a":"10%以内","answer_d":"35%-50%","id":7,"answer_c":"25%-35%","answer_e":"50%以上"},{"answer_b":"星级","topic":"如果您来投资酒店/公寓/民宿项目，除了项目的履约能力，以下您最关注的是","answer_a":"品牌效应","answer_d":"定价","id":8,"answer_c":"交通便利性","answer_e":"区位优势"},{"answer_b":"快捷酒店","topic":"近两年内，您在差旅中常选择以下哪种住宿","answer_a":"宾馆","answer_d":"中端酒店","id":9,"answer_c":"民宿或客栈","answer_e":"高级星级酒店"},{"answer_b":"有限：只把钱放银行、货币基金和其他低风险理财产品","topic":"您的投资经验情况为","answer_a":"暂无参与经验","answer_d":"丰富：我参与过股票、基金等产品的交易","id":10,"answer_c":"一般：我购买过基金、保险等理财产品","answer_e":"非常丰富：我参与过权证、期货、风险投资等高风险交易"},{"answer_b":"我只购买过余额宝类产品","topic":"您对互联网金融的投资经验为","answer_a":"暂无参与经验","answer_d":"我参与过p2p投资","id":11,"answer_c":"我参与过众筹投资","answer_e":"我参与过数字货币或其他新型投资"},{"answer_b":"10%-25%","topic":"在您的家庭总资产中，通常有多少比例用于投资或理财","answer_a":"10%以内","answer_d":"35%-50%","id":12,"answer_c":"25%-35%","answer_e":"50%以上"},{"answer_b":"1-10万","topic":"您单次认购的投资产品中，投资金额最高的一笔","answer_a":"1万以内","answer_d":"30-50万","id":13,"answer_c":"10-30万","answer_e":"50万以上"},{"answer_b":"2-3年","topic":"对于期权投资类产品，您一般可以接受的投资期限是","answer_a":"1年以内","answer_d":"4-5年","id":14,"answer_c":"3-4年","answer_e":"5年以上"},{"answer_b":"10%-25%","topic":"在投资中，您认为自己可以承受的最大投资损失是多少","answer_a":"10%以内","answer_d":"35%-50%","id":15,"answer_c":"25%-35%","answer_e":"50%以上"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":15,"startRow":1,"total":15}}
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
         * PageInfo : {"endRow":15,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"answer_b":"26-35岁","topic":"您的年龄","answer_a":"25岁以下","answer_d":"46-55岁","id":1,"answer_c":"36-45岁","answer_e":"56岁以上 "},{"answer_b":"自由职业","topic":"您的职业","answer_a":"国企、公务员、事业单位","answer_d":"自营企业","id":2,"answer_c":"外企","answer_e":"其他"},{"answer_b":"1人","topic":"您的家庭情况：需要依赖您抚养、无经济来源的家庭成员人数是","answer_a":"无","answer_d":"3人","id":3,"answer_c":"2人","answer_e":"4人或4人以上"},{"answer_b":"5-20万","topic":"您的家庭收入情况","answer_a":"5万以内","answer_d":"50-100万","id":4,"answer_c":"20-50万","answer_e":"100万以上"},{"answer_b":"10%以内","topic":"被动收入在您的家庭收入中占多少比例","answer_a":"无","answer_d":"30%-50%","id":5,"answer_c":"10%-30%","answer_e":"50%以上"},{"answer_b":"网站","topic":"您是通过哪些渠道了解到传家宝平台的","answer_a":"公众号","answer_d":"好友推荐邀请","id":6,"answer_c":"线下活动","answer_e":"自行了解投资类的产品"},{"answer_b":"10%-25%","topic":"地产类投资（不包括自用住房）在您的总投资金额中占比多少","answer_a":"10%以内","answer_d":"35%-50%","id":7,"answer_c":"25%-35%","answer_e":"50%以上"},{"answer_b":"星级","topic":"如果您来投资酒店/公寓/民宿项目，除了项目的履约能力，以下您最关注的是","answer_a":"品牌效应","answer_d":"定价","id":8,"answer_c":"交通便利性","answer_e":"区位优势"},{"answer_b":"快捷酒店","topic":"近两年内，您在差旅中常选择以下哪种住宿","answer_a":"宾馆","answer_d":"中端酒店","id":9,"answer_c":"民宿或客栈","answer_e":"高级星级酒店"},{"answer_b":"有限：只把钱放银行、货币基金和其他低风险理财产品","topic":"您的投资经验情况为","answer_a":"暂无参与经验","answer_d":"丰富：我参与过股票、基金等产品的交易","id":10,"answer_c":"一般：我购买过基金、保险等理财产品","answer_e":"非常丰富：我参与过权证、期货、风险投资等高风险交易"},{"answer_b":"我只购买过余额宝类产品","topic":"您对互联网金融的投资经验为","answer_a":"暂无参与经验","answer_d":"我参与过p2p投资","id":11,"answer_c":"我参与过众筹投资","answer_e":"我参与过数字货币或其他新型投资"},{"answer_b":"10%-25%","topic":"在您的家庭总资产中，通常有多少比例用于投资或理财","answer_a":"10%以内","answer_d":"35%-50%","id":12,"answer_c":"25%-35%","answer_e":"50%以上"},{"answer_b":"1-10万","topic":"您单次认购的投资产品中，投资金额最高的一笔","answer_a":"1万以内","answer_d":"30-50万","id":13,"answer_c":"10-30万","answer_e":"50万以上"},{"answer_b":"2-3年","topic":"对于期权投资类产品，您一般可以接受的投资期限是","answer_a":"1年以内","answer_d":"4-5年","id":14,"answer_c":"3-4年","answer_e":"5年以上"},{"answer_b":"10%-25%","topic":"在投资中，您认为自己可以承受的最大投资损失是多少","answer_a":"10%以内","answer_d":"35%-50%","id":15,"answer_c":"25%-35%","answer_e":"50%以上"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":15,"startRow":1,"total":15}
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
             * endRow : 15
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"answer_b":"26-35岁","topic":"您的年龄","answer_a":"25岁以下","answer_d":"46-55岁","id":1,"answer_c":"36-45岁","answer_e":"56岁以上 "},{"answer_b":"自由职业","topic":"您的职业","answer_a":"国企、公务员、事业单位","answer_d":"自营企业","id":2,"answer_c":"外企","answer_e":"其他"},{"answer_b":"1人","topic":"您的家庭情况：需要依赖您抚养、无经济来源的家庭成员人数是","answer_a":"无","answer_d":"3人","id":3,"answer_c":"2人","answer_e":"4人或4人以上"},{"answer_b":"5-20万","topic":"您的家庭收入情况","answer_a":"5万以内","answer_d":"50-100万","id":4,"answer_c":"20-50万","answer_e":"100万以上"},{"answer_b":"10%以内","topic":"被动收入在您的家庭收入中占多少比例","answer_a":"无","answer_d":"30%-50%","id":5,"answer_c":"10%-30%","answer_e":"50%以上"},{"answer_b":"网站","topic":"您是通过哪些渠道了解到传家宝平台的","answer_a":"公众号","answer_d":"好友推荐邀请","id":6,"answer_c":"线下活动","answer_e":"自行了解投资类的产品"},{"answer_b":"10%-25%","topic":"地产类投资（不包括自用住房）在您的总投资金额中占比多少","answer_a":"10%以内","answer_d":"35%-50%","id":7,"answer_c":"25%-35%","answer_e":"50%以上"},{"answer_b":"星级","topic":"如果您来投资酒店/公寓/民宿项目，除了项目的履约能力，以下您最关注的是","answer_a":"品牌效应","answer_d":"定价","id":8,"answer_c":"交通便利性","answer_e":"区位优势"},{"answer_b":"快捷酒店","topic":"近两年内，您在差旅中常选择以下哪种住宿","answer_a":"宾馆","answer_d":"中端酒店","id":9,"answer_c":"民宿或客栈","answer_e":"高级星级酒店"},{"answer_b":"有限：只把钱放银行、货币基金和其他低风险理财产品","topic":"您的投资经验情况为","answer_a":"暂无参与经验","answer_d":"丰富：我参与过股票、基金等产品的交易","id":10,"answer_c":"一般：我购买过基金、保险等理财产品","answer_e":"非常丰富：我参与过权证、期货、风险投资等高风险交易"},{"answer_b":"我只购买过余额宝类产品","topic":"您对互联网金融的投资经验为","answer_a":"暂无参与经验","answer_d":"我参与过p2p投资","id":11,"answer_c":"我参与过众筹投资","answer_e":"我参与过数字货币或其他新型投资"},{"answer_b":"10%-25%","topic":"在您的家庭总资产中，通常有多少比例用于投资或理财","answer_a":"10%以内","answer_d":"35%-50%","id":12,"answer_c":"25%-35%","answer_e":"50%以上"},{"answer_b":"1-10万","topic":"您单次认购的投资产品中，投资金额最高的一笔","answer_a":"1万以内","answer_d":"30-50万","id":13,"answer_c":"10-30万","answer_e":"50万以上"},{"answer_b":"2-3年","topic":"对于期权投资类产品，您一般可以接受的投资期限是","answer_a":"1年以内","answer_d":"4-5年","id":14,"answer_c":"3-4年","answer_e":"5年以上"},{"answer_b":"10%-25%","topic":"在投资中，您认为自己可以承受的最大投资损失是多少","answer_a":"10%以内","answer_d":"35%-50%","id":15,"answer_c":"25%-35%","answer_e":"50%以上"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 20
             * pages : 1
             * prePage : 0
             * size : 15
             * startRow : 1
             * total : 15
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
                 * answer_b : 26-35岁
                 * topic : 您的年龄
                 * answer_a : 25岁以下
                 * answer_d : 46-55岁
                 * id : 1
                 * answer_c : 36-45岁
                 * answer_e : 56岁以上
                 */

                private String answer_b;
                private String topic;
                private String answer_a;
                private String answer_d;
                private int id;
                private String answer_c;
                private String answer_e;

                public String getAnswer_b() {
                    return answer_b;
                }

                public void setAnswer_b(String answer_b) {
                    this.answer_b = answer_b;
                }

                public String getTopic() {
                    return topic;
                }

                public void setTopic(String topic) {
                    this.topic = topic;
                }

                public String getAnswer_a() {
                    return answer_a;
                }

                public void setAnswer_a(String answer_a) {
                    this.answer_a = answer_a;
                }

                public String getAnswer_d() {
                    return answer_d;
                }

                public void setAnswer_d(String answer_d) {
                    this.answer_d = answer_d;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAnswer_c() {
                    return answer_c;
                }

                public void setAnswer_c(String answer_c) {
                    this.answer_c = answer_c;
                }

                public String getAnswer_e() {
                    return answer_e;
                }

                public void setAnswer_e(String answer_e) {
                    this.answer_e = answer_e;
                }
            }
        }
    }
}
