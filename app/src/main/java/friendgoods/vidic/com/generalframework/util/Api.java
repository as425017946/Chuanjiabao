package friendgoods.vidic.com.generalframework.util;

/**
 * api接口
 */
public class Api {
    //阿里云oss图片上传及显示图片地址
    public static String ossurl = "https://jiaoyuvideo.oss-cn-beijing.aliyuncs.com/";

//    public static String apiurl = "http://192.168.1.177:8080/";
    public static String apiurl = "http://39.107.70.80:8080/treasure1.0/";
    //账号密码登录或注册
    public static String login = apiurl+"appLogin/addUserByPassWord";
    //获取验证码
    public static String sms = apiurl+"common/sms";
    //手机号验证码登录
    public static String phonesms = apiurl+"appLogin/byPhone";
    //忘记密码
    public static String forgetpassword = apiurl+"userPc/wangjiUserPcPassWord";
    //微信登录
    public static String weChatlogin = apiurl+"appUser/weChat";
    //我的页面 用户 信息展示
    public static String myinfo = apiurl+"myUser/selectUser";
    //显示账户余额
    public static String showyue = apiurl+"myUser/selectAccount";
    //修改头像
    public static String headimg = apiurl+"myUser/updateUserHead";
    //修改昵称
    public static String nickname = apiurl+"myUser/updateNickName";
    //修改微信
    public static String wechatupdate = apiurl+"myUser/updateBindingWeChat";
    //修改手机
    public static String phoneupdate = apiurl+"myUser/updateBindingPhone";
    //修改手机验证
    public static String phoneupdate2 = apiurl+"myUser/updateBindingPhone1";
    //修改邮箱
    public static String emailupdate = apiurl+"myUser/updateBindingEmail";
    //新增收货地址
    public static String addAddress = apiurl+"myUser/addAddress";
    //修改收货地址
    public static String updateAddress = apiurl+"myUser/updateAddress";
    //删除收货地址
    public static String delectAddress = apiurl+"myUser/deleteAddress";
    //显示收货地址
    public static String showAddress = apiurl+"pay/selectAddressOne";
    //展示所有地址
    public static String alladdress = apiurl+"myUser/selectAddress";
    //投资人认证
    public static String touzirenzheng = apiurl+"myUser/userByDetailCodePhotoJob";
    //显示投资人认证
    public static String touzirenzheng_show = apiurl+"myUser/selectNameDetailCodeJob";
    //高级认证
    public static String gaojirenzheng = apiurl+"me/userByDetailCodePhoto";
    //显示高级认证
    public static String gaojirenzheng_show = apiurl+"me/selectByDetailCodePhoto";
    //职业字典表
    public static String zhiyetable = apiurl+"/me/jobList";
    //修改密码
    public static String updatepassword = apiurl+"myUser/updatePassWord";
    //绑定分红卡
    public static String bindfenhongcard = apiurl+"myUser/addBankCard";
    //删除分红卡
    public static String deletefenhongcard = apiurl+"myUser/deleteBankCard";
    //显示分红卡
    public static String showfenhongcard = apiurl+"myUser/selectBankCard";
    //收支明细 转入转出 操作状态，1；充值 2：提现 3：余额收益
    public static String mingxi = apiurl+"myUser/selectDetails";
    //充值
    public static String chongzhi = apiurl+"myUser/updateMinusMoney";
    //提现
    public static String tixian = apiurl+"me/userDrawMoney";
    //显示答题
    public static String shwodati = apiurl+"myUser/selectTopic";
    // 用户选择答案
    public static String usercheckd = apiurl+"myUser/addTopic";
    //保存用户选择答案后展示的类型信息
    public static String usertype = apiurl+"me/addUserType";
    //查看用户是否填写过答题
    public static String ifpingce = apiurl+"me/userType";
    //显示订单
    public static String ordershow = apiurl+"myUser/selectAllItemsOrder";
    //依据订单ID查看订单详情
    public static String Ordermore = apiurl+"myUser/selectByIdItem";
    //依据项目状态和用户ID查询是否支付，未支付，超时等
    public static String orderstate = apiurl+"myUser/selectAllItemsOrder";
    //投后管理
    public static String touhouguanli = apiurl+"myUser/selectAllItem";
    //消费权益
    public static String xiaofeiquanyi = apiurl+"myUser/selectShopRight";
    //投后管理进度
    public static String touhouguanlijindu = apiurl+"me/me/itemsPlan";
    //投诉或者建议
    public static String jianyi = apiurl+"myUser/addUserIdea";
    //展示优惠券
    public static String showyouhui = apiurl+"myUser/selectVoucher";
    //展示项目专属优惠券
    public static String showyouhui_zhuanshu = apiurl+"myUser/selectVoucherUuid";
    //设置支付密码
    public static String zhifumima = apiurl+"myUser/updatePayPassWord";
    //兑换优惠券
    public static String duihuanyouhuiquan = apiurl+"myUser/addVoucher";
    //解绑银行卡
    public static String jiebang = apiurl+"myUser/deleteBankCard";
    //修改支付密码
    public static String updatezhifu = apiurl+"myUser/updatePaymentPassWord";


    /**
     * 投资页面
     */
    //最新
    public static String touzinew = apiurl+"pay/selectNewItems";
    //预告
    public static String touziyugao = apiurl+"pay/selectTrailerItems";
    //酒店
    public static String touzijiudian = apiurl+"pay/selectHotelItems";
    //民宿
    public static String touziminsu = apiurl+"pay/selectPeopleItems";
    //公寓
    public static String touzigongyu = apiurl+"pay/selectApartmentItems";
    //更多
    public static String touzigengduo = apiurl+"pay/selectMoreItems";
    //依据项目ID显示项目购买进
    public static String jindu = apiurl+"find/itemsNow";
    //投资界面详情
    public static String touzimore = apiurl+"pay/selectTwoItems";
    //投资关注
    public static String touziguanzhu = apiurl+"pay/addAttention";
    //取消关注
    public static String touziguanzhu2 = apiurl+"pay/updateAttention";
    //投资预约
    public static String touziyuyue = apiurl+"pay/addAbout";
    //取消预约
    public static String touziyuyue2 = apiurl+"pay/updateAbout";
    //是否预约
    public static String isyuyue = apiurl+"pay/yuYue";
    //是否关注或者预约 status1 是否关注 0:取消关注 1:关注 ；status2 是否预约 0:取消预约 1:预约
    public static String isguanzhu = apiurl+"pay/selectAboutAttention";
    //投资三级页面
    public static String sanjipage = apiurl+"pay/selectBuy";
    //三级界面支付确认
    public static String payqueren = apiurl+"pay/addOrder";
    //三级界完成支付
    public static String paywancheng = apiurl+"pay/userPay";
    //系统消息展示
    public static String message_show = apiurl+"pay/selectInformation";
    //删除系统消息
    public static String message_delete = apiurl+"pay/deleteInformation";
    //展示邮箱
    public static String showemail = apiurl+"pay/selectByEmail";
    //项目搜索
    public static String sousuo = apiurl+"pay/selectSearchItems";
    //是认购了
    public static String isrengou = apiurl+"pay/selectBuyScheme";
    //投资人可见
    public static String kejian = apiurl+"pay/payByUser";
    //套餐
    public static String taocan = apiurl+"me/itemsPay";
    //投资人可见
    public static String touzirenshow = apiurl+"pay/selectInvestors";
    //公开资料
    public static String publicshow = apiurl+"pay/selectPublicInvestors";
    //关键字
    public static String guanjianzi = apiurl+"pay/selectKeyItems";
    //开始或关闭极光推送
    public static String kaiOrguanbi = apiurl+"appLogin/updateRegistration";
    //我的积分
    public static String myjifen = apiurl+"find/selectIntegral";


    /***
     * 发现
     */
    //轮播
    public static String lunbo = apiurl+"find/selectPhotoList";
    //推荐项目
    public static String tuijian = apiurl+"find/selectAllItems";
    //消费众筹
    public static String zhongchou = apiurl+"find/selectShopItems";
    //数据披露 历时天数
    public static String shujupilouday = apiurl+"find/nowDay";
    //数据披露 投资人
    public static String shujupiloutouziren = apiurl+"find/countByMoneyByUser";
    //数据披露 总投资项目、总众筹目的地、总投资金额
    public static String shujupilouzongxinxi = apiurl+"find/countByItemsBySite";
    //众筹项目类型
    public static String zhouchouleixing = apiurl+"itemsPc/selectItemsTypeList";
    //发起项目
    public static String faqiobj = apiurl+"find/addItems";
    //项目类型字典
    public static String zidian = apiurl+"find/itemsType";
    //往期合集
    public static String wangqiheji = apiurl+"find/selectOldItems";

    //积分商城
    public static String jifenshopss = apiurl+"find/selectShop";
    //积分商城顶部图片
    public static String jifenshoptopimg = apiurl+"find/selectShopPhoto";
    //积分商城详情
    public static String goodsmore = apiurl+"find/selectFistShop";
    //积分商城购买
    public static String payjifengoods = apiurl+"find/addShop";
    //商城订单
    public static String shopOrders = apiurl+"find/selectShopOrder";
    //商城订单详情
    public static String shopOrderMore = apiurl+"find/selectOneOrder";
    //确认收货
    public static String querenshouhuo = apiurl+"find/updateCollectGoods";
    // 展示数据披露顶部图
    public static String topphoto = apiurl+"find/topPhoto";
    //底部
    public static String bottomphoto = apiurl+"find/lowPhoto";
    //展示传家宝简介
    public static String jianjie = apiurl+"find/selectFileName";
}
