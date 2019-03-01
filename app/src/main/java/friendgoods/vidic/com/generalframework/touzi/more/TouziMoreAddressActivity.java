package friendgoods.vidic.com.generalframework.touzi.more;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.ProvinceBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.wode.NichengActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.nick_name;

/***
 * 添加地址
 */
public class TouziMoreAddressActivity extends BaseActivity {

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @BindView(R.id.address_more_jiedao)
    EditText edt_jiedao;
    @BindView(R.id.address_more_name)
    EditText edt_name;
    @BindView(R.id.address_more_phone)
    EditText edt_phone;
    @BindView(R.id.address_more_check)
    CheckBox checkBox;
    int flag=0; //用于是否设置为默认地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_more_address);
        ButterKnife.bind(this);
        getOptionData();
        setTtitle();
        fanhui();
        setinfo();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView lbaocun;
    private void setTtitle(){
        ttitle.setText("收件地址");
        lbaocun.setText("保存");
        lbaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UiUtils.isFastClick()==true){
                    if (checkBox.isChecked()){
                        flag=1;
                    }else{
                        flag=0;
                    }

                    if(TextUtils.isEmpty(edt_name.getText().toString())){
                        ToastUtils.shortToast("请输入收件人姓名");
                    }else if(TextUtils.isEmpty(edt_phone.getText().toString())){
                        ToastUtils.shortToast("请输入手机号");
                    }else if(TextUtils.isEmpty(edt_jiedao.getText().toString())){
                        ToastUtils.shortToast("请输入详情街道地址");
                    }else{
                        if (UiUtils.isCellphone(edt_phone.getText().toString())==false){
                            ToastUtils.shortToast("输入的手机号码不正确");
                        }else{
                            if (tv_show.getText().toString().equals("请选择")){
                                ToastUtils.shortToast("请选择地址");
                            }else{
//                            Log.e("测试信息","用户id"+Userid+"收件人"+edt_name.getText().toString()
//                            +"手机号"+edt_phone.getText().toString()+"城市"+tv_show.getText().toString()
//                            +"地址"+edt_jiedao.getText().toString()+"falg"+flag);
                                OkGo.post(Api.addAddress)
                                        .tag(this)
                                        .params("uuid",Userid)
                                        .params("addName",edt_name.getText().toString())
                                        .params("addMobile",edt_phone.getText().toString())
                                        .params("areaDiqu",tv_show.getText().toString())
                                        .params("addDiqu",edt_jiedao.getText().toString())
                                        .params("flag",flag)
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(String s, Call call, Response response) {
                                                Gson gson = new Gson();
                                                WechatBindBean wechatBindBean =gson.fromJson(s,WechatBindBean.class);
                                                if(wechatBindBean.getState()==1){
                                                    Handler handler = new Handler();
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            TouziMoreAddressActivity.this.finish();
                                                        }
                                                    },1000);
                                                    ToastUtils.longToast("地址添加成功！");
                                                }else {
                                                    ToastUtils.shortToast(wechatBindBean.getMessage());
                                                }
                                            }
                                        });
                            }

                        }
                    }
                }else {
                    ToastUtils.shortToast("请勿多次提交重复信息");
                }

            }
        });
    }
    /**
     * 返回
     */
    @BindView(R.id.touzi_fanhui)
    LinearLayout lfanhui;
    private void fanhui(){
        lfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouziMoreAddressActivity.this.finish();
            }
        });
    }

    /**
     * 保存地址
     */
    @BindView(R.id.address_more_xuanzeaddress)
    LinearLayout l_xuanze;
    @BindView(R.id.address_more_xuanzeaddress_show)
    TextView tv_show;
    private void setinfo(){
        l_xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                OptionsPickerView pvOptions = new OptionsPickerBuilder(TouziMoreAddressActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText()
                                + options2Items.get(options1).get(option2);
                        tv_show.setText(tx);
                    }
                }).build();
                pvOptions.setPicker(options1Items, options2Items);
                pvOptions.show();

            }
        });
    }


    private void getOptionData() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */


        //选项1
        options1Items.add(new ProvinceBean(0, "北京", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "天津", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "上海", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(3, "重庆", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(4, "河北", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(5, "山西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(6, "内蒙古", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(7, "辽宁", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(8, "吉林", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(9, "黑龙江", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(10, "江苏", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(11, "浙江", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(12, "安徽", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(13, "福建", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(14, "江西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(15, "山东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(16, "河南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(17, "湖北", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(18, "湖南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(19, "广东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(20, "广西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(21, "海南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(22, "四川", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(23, "贵州", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(24, "云南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(25, "西藏", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(26, "陕西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(27, "甘肃", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(28, "青海", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(29, "宁夏", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(30, "新疆", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(31, "香港", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(32, "澳门", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(33, "台湾", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("东城区");
        options2Items_01.add("西城区");
        options2Items_01.add("崇文区");
        options2Items_01.add("宣武区");
        options2Items_01.add("朝阳区");
        options2Items_01.add("丰台区");
        options2Items_01.add("石景山区");
        options2Items_01.add("海淀区");
        options2Items_01.add("门头沟区");
        options2Items_01.add("房山区");
        options2Items_01.add("通州区");
        options2Items_01.add("顺义区");
        options2Items_01.add("昌平区");
        options2Items_01.add("大兴区");
        options2Items_01.add("怀柔区");
        options2Items_01.add("平谷区");
        options2Items_01.add("密云县");
        options2Items_01.add("延庆县");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("和平区");
        options2Items_02.add("河东区");
        options2Items_02.add("河西区");
        options2Items_02.add("南开区");
        options2Items_02.add("河北区");
        options2Items_02.add("红桥区");
        options2Items_02.add("滨海新区");
        options2Items_02.add("东丽区");
        options2Items_02.add("西青区");
        options2Items_02.add("津南区");
        options2Items_02.add("北辰区");
        options2Items_02.add("武清区");
        options2Items_02.add("宝坻区");
        options2Items_02.add("宁河区");
        options2Items_02.add("静海区");
        options2Items_02.add("蓟县");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("黄浦区");
        options2Items_03.add("卢湾区");
        options2Items_03.add("徐汇区");
        options2Items_03.add("长宁区");
        options2Items_03.add("静安区");
        options2Items_03.add("普陀区");
        options2Items_03.add("闸北区");
        options2Items_03.add("杨浦区");
        options2Items_03.add("虹口区");
        options2Items_03.add("闵行区");
        options2Items_03.add("宝山区");
        options2Items_03.add("嘉定区");
        options2Items_03.add("浦东新区");
        options2Items_03.add("金山区");
        options2Items_03.add("松江区");
        options2Items_03.add("青浦区");
        options2Items_03.add("南汇区");
        options2Items_03.add("奉贤区");
        options2Items_03.add("崇明县");
        ArrayList<String> options2Items_04 = new ArrayList<>();
        options2Items_04.add("渝中区");
        options2Items_04.add("大渡口区");
        options2Items_04.add("江北区");
        options2Items_04.add("沙坪坝区");
        options2Items_04.add("九龙坡区");
        options2Items_04.add("南岸区");
        options2Items_04.add("北碚区");
        options2Items_04.add("万盛区");
        options2Items_04.add("双桥区");
        options2Items_04.add("渝北区");
        options2Items_04.add("巴南区");
        options2Items_04.add("万州区");
        options2Items_04.add("涪陵区");
        options2Items_04.add("黔江区");
        options2Items_04.add("长寿区");
        options2Items_04.add("江津区");
        options2Items_04.add("永川区");
        options2Items_04.add("南川区");
        ArrayList<String> options2Items_05 = new ArrayList<>();
        options2Items_05.add("石家庄");
        options2Items_05.add("唐山");
        options2Items_05.add("秦皇岛");
        options2Items_05.add("邯郸");
        options2Items_05.add("邢台");
        options2Items_05.add("保定");
        options2Items_05.add("张家口");
        options2Items_05.add("承德");
        options2Items_05.add("沧州");
        options2Items_05.add("廊坊");
        options2Items_05.add("衡水");
        ArrayList<String> options2Items_06 = new ArrayList<>();
        options2Items_06.add("太原");
        options2Items_06.add("大同");
        options2Items_06.add("阳泉");
        options2Items_06.add("长治");
        options2Items_06.add("晋城");
        options2Items_06.add("朔州");
        options2Items_06.add("晋中");
        options2Items_06.add("运城");
        options2Items_06.add("忻州");
        options2Items_06.add("临汾");
        options2Items_06.add("吕梁");
        ArrayList<String> options2Items_07 = new ArrayList<>();
        options2Items_07.add("呼和浩特");
        options2Items_07.add("包头");
        options2Items_07.add("乌海");
        options2Items_07.add("赤峰");
        options2Items_07.add("通辽");
        options2Items_07.add("鄂尔多斯");
        options2Items_07.add("呼伦贝尔");
        options2Items_07.add("巴彦淖尔");
        options2Items_07.add("乌兰察布");
        options2Items_07.add("锡林郭勒盟");
        options2Items_07.add("兴安盟");
        options2Items_07.add("阿拉善盟");
        ArrayList<String> options2Items_08 = new ArrayList<>();
        options2Items_08.add("沈阳");
        options2Items_08.add("大连");
        options2Items_08.add("金州");
        options2Items_08.add("鞍山");
        options2Items_08.add("抚顺");
        options2Items_08.add("本溪");
        options2Items_08.add("丹东");
        options2Items_08.add("锦州");
        options2Items_08.add("营口");
        options2Items_08.add("阜新");
        options2Items_08.add("辽阳");
        options2Items_08.add("盘锦");
        options2Items_08.add("铁岭");
        options2Items_08.add("朝阳");
        options2Items_08.add("葫芦岛");
        ArrayList<String> options2Items_09 = new ArrayList<>();
        options2Items_09.add("长春");
        options2Items_09.add("吉林");
        options2Items_09.add("四平");
        options2Items_09.add("辽源");
        options2Items_09.add("通化");
        options2Items_09.add("白山");
        options2Items_09.add("松原");
        options2Items_09.add("白城");
        options2Items_09.add("延边");
        ArrayList<String> options2Items_10 = new ArrayList<>();
        options2Items_10.add("哈尔滨");
        options2Items_10.add("大庆");
        options2Items_10.add("齐齐哈尔");
        options2Items_10.add("佳木斯");
        options2Items_10.add("鸡西");
        options2Items_10.add("鹤岗");
        options2Items_10.add("双鸭山");
        options2Items_10.add("牡丹江");
        options2Items_10.add("伊春");
        options2Items_10.add("七台河");
        options2Items_10.add("黑河");
        options2Items_10.add("绥化");
        options2Items_10.add("大兴安岭");
        ArrayList<String> options2Items_11 = new ArrayList<>();
        options2Items_11.add("南京");
        options2Items_11.add("镇江");
        options2Items_11.add("常州");
        options2Items_11.add("无锡");
        options2Items_11.add("苏州");
        options2Items_11.add("徐州");
        options2Items_11.add("连云港");
        options2Items_11.add("淮安");
        options2Items_11.add("盐城");
        options2Items_11.add("扬州");
        options2Items_11.add("泰州");
        options2Items_11.add("南通");
        options2Items_11.add("宿迁");
        ArrayList<String> options2Items_12 = new ArrayList<>();
        options2Items_12.add("杭州");
        options2Items_12.add("嘉兴");
        options2Items_12.add("湖州");
        options2Items_12.add("宁波");
        options2Items_12.add("金华");
        options2Items_12.add("温州");
        options2Items_12.add("丽水");
        options2Items_12.add("绍兴");
        options2Items_12.add("衢州");
        options2Items_12.add("舟山");
        options2Items_12.add("台州");
        ArrayList<String> options2Items_13 = new ArrayList<>();
        options2Items_13.add("合肥");
        options2Items_13.add("蚌埠");
        options2Items_13.add("芜湖");
        options2Items_13.add("淮南");
        options2Items_13.add("亳州");
        options2Items_13.add("阜阳");
        options2Items_13.add("淮北");
        options2Items_13.add("宿州");
        options2Items_13.add("滁州");
        options2Items_13.add("安庆");
        options2Items_13.add("巢湖");
        options2Items_13.add("马鞍山");
        options2Items_13.add("宣城");
        options2Items_13.add("黄山");
        options2Items_13.add("池州");
        options2Items_13.add("铜陵");
        ArrayList<String> options2Items_14 = new ArrayList<>();
        options2Items_14.add("福州");
        options2Items_14.add("厦门");
        options2Items_14.add("泉州");
        options2Items_14.add("三明");
        options2Items_14.add("南平");
        options2Items_14.add("漳州");
        options2Items_14.add("莆田");
        options2Items_14.add("宁德");
        options2Items_14.add("龙岩");
        ArrayList<String> options2Items_15 = new ArrayList<>();
        options2Items_15.add("南昌");
        options2Items_15.add("九江");
        options2Items_15.add("赣州");
        options2Items_15.add("吉安");
        options2Items_15.add("鹰潭");
        options2Items_15.add("上饶");
        options2Items_15.add("萍乡");
        options2Items_15.add("景德镇");
        options2Items_15.add("新余");
        options2Items_15.add("宜春");
        options2Items_15.add("抚州");
        ArrayList<String> options2Items_16 = new ArrayList<>();
        options2Items_16.add("济南");
        options2Items_16.add("青岛");
        options2Items_16.add("淄博");
        options2Items_16.add("枣庄");
        options2Items_16.add("东营");
        options2Items_16.add("烟台");
        options2Items_16.add("潍坊");
        options2Items_16.add("济宁");
        options2Items_16.add("泰安");
        options2Items_16.add("威海");
        options2Items_16.add("日照");
        options2Items_16.add("莱芜");
        options2Items_16.add("临沂");
        options2Items_16.add("德州");
        options2Items_16.add("聊城");
        options2Items_16.add("菏泽");
        options2Items_16.add("滨州");
        ArrayList<String> options2Items_17 = new ArrayList<>();
        options2Items_17.add("郑州");
        options2Items_17.add("洛阳");
        options2Items_17.add("开封");
        options2Items_17.add("漯河");
        options2Items_17.add("安阳");
        options2Items_17.add("新乡");
        options2Items_17.add("周口");
        options2Items_17.add("三门峡");
        options2Items_17.add("焦作");
        options2Items_17.add("平顶山");
        options2Items_17.add("信阳");
        options2Items_17.add("南阳");
        options2Items_17.add("鹤壁");
        options2Items_17.add("濮阳");
        options2Items_17.add("许昌");
        options2Items_17.add("商丘");
        options2Items_17.add("驻马店");
        ArrayList<String> options2Items_18 = new ArrayList<>();
        options2Items_18.add("武汉");
        options2Items_18.add("襄樊");
        options2Items_18.add("宜昌");
        options2Items_18.add("黄石");
        options2Items_18.add("鄂州");
        options2Items_18.add("随州");
        options2Items_18.add("荆州");
        options2Items_18.add("荆门");
        options2Items_18.add("十堰");
        options2Items_18.add("孝感");
        options2Items_18.add("黄冈");
        options2Items_18.add("咸宁");
        options2Items_18.add("恩施");
        ArrayList<String> options2Items_19 = new ArrayList<>();
        options2Items_19.add("长沙");
        options2Items_19.add("株洲");
        options2Items_19.add("湘潭");
        options2Items_19.add("衡阳");
        options2Items_19.add("岳阳");
        options2Items_19.add("郴州");
        options2Items_19.add("永州");
        options2Items_19.add("邵阳");
        options2Items_19.add("怀化");
        options2Items_19.add("常德");
        options2Items_19.add("益阳");
        options2Items_19.add("张家界");
        options2Items_19.add("娄底");
        options2Items_19.add("湘西");
        ArrayList<String> options2Items_20 = new ArrayList<>();
        options2Items_20.add("广州");
        options2Items_20.add("深圳");
        options2Items_20.add("汕头");
        options2Items_20.add("惠州");
        options2Items_20.add("珠海");
        options2Items_20.add("揭阳");
        options2Items_20.add("佛山");
        options2Items_20.add("河源");
        options2Items_20.add("阳江");
        options2Items_20.add("茂名");
        options2Items_20.add("湛江");
        options2Items_20.add("梅州");
        options2Items_20.add("肇庆");
        options2Items_20.add("韶关");
        options2Items_20.add("潮州");
        options2Items_20.add("东莞");
        options2Items_20.add("中山");
        options2Items_20.add("清远");
        options2Items_20.add("江门");
        options2Items_20.add("汕尾");
        options2Items_20.add("云浮");
        ArrayList<String> options2Items_21 = new ArrayList<>();
        options2Items_21.add("南宁");
        options2Items_21.add("柳州");
        options2Items_21.add("桂林");
        options2Items_21.add("梧州");
        options2Items_21.add("北海");
        options2Items_21.add("崇左");
        options2Items_21.add("来宾");
        options2Items_21.add("贺州");
        options2Items_21.add("玉林");
        options2Items_21.add("百色");
        options2Items_21.add("河池");
        options2Items_21.add("钦州");
        options2Items_21.add("防城港");
        options2Items_21.add("贵港");
        ArrayList<String> options2Items_22 = new ArrayList<>();
        options2Items_22.add("海口");
        options2Items_22.add("三亚");
        ArrayList<String> options2Items_23 = new ArrayList<>();
        options2Items_23.add("成都市");
        options2Items_23.add("自贡市");
        options2Items_23.add("攀枝花市");
        options2Items_23.add("泸州市");
        options2Items_23.add("德阳市");
        options2Items_23.add("绵阳市");
        options2Items_23.add("广元市");
        options2Items_23.add("遂宁市");
        options2Items_23.add("内江市");
        options2Items_23.add("乐山市");
        options2Items_23.add("南充市");
        options2Items_23.add("眉山市");
        options2Items_23.add("宜宾市");
        options2Items_23.add("广安市");
        options2Items_23.add("达州市");
        options2Items_23.add("雅安市");
        options2Items_23.add("巴中市");
        options2Items_23.add("资阳市");
        options2Items_23.add("阿坝");
        options2Items_23.add("甘孜");
        options2Items_23.add("凉山");
        ArrayList<String> options2Items_24 = new ArrayList<>();
        options2Items_24.add("贵阳");
        options2Items_24.add("六盘水");
        options2Items_24.add("遵义");
        options2Items_24.add("安顺");
        ArrayList<String> options2Items_25 = new ArrayList<>();
        options2Items_25.add("昆明");
        options2Items_25.add("曲靖");
        options2Items_25.add("玉溪");
        options2Items_25.add("保山");
        options2Items_25.add("昭通");
        options2Items_25.add("丽江");
        options2Items_25.add("普洱");
        options2Items_25.add("临沧");
        ArrayList<String> options2Items_26 = new ArrayList<>();
        options2Items_26.add("拉萨");
        options2Items_26.add("昌都");
        options2Items_26.add("山南");
        options2Items_26.add("日喀则");
        options2Items_26.add("那曲");
        options2Items_26.add("阿里");
        options2Items_26.add("林芝");
        ArrayList<String> options2Items_27 = new ArrayList<>();
        options2Items_27.add("西安");
        options2Items_27.add("咸阳");
        options2Items_27.add("铜川");
        options2Items_27.add("延安");
        options2Items_27.add("宝鸡");
        options2Items_27.add("渭南");
        options2Items_27.add("汉中");
        options2Items_27.add("安康");
        options2Items_27.add("商洛");
        options2Items_27.add("榆林");
        ArrayList<String> options2Items_28 = new ArrayList<>();
        options2Items_28.add("兰州");
        options2Items_28.add("天水");
        options2Items_28.add("平凉");
        options2Items_28.add("酒泉");
        options2Items_28.add("嘉峪关");
        options2Items_28.add("金昌");
        options2Items_28.add("白银");
        options2Items_28.add("武威");
        options2Items_28.add("张掖");
        options2Items_28.add("庆阳");
        options2Items_28.add("定西");
        options2Items_28.add("陇南");
        options2Items_28.add("临夏");
        options2Items_28.add("甘南");
        ArrayList<String> options2Items_29 = new ArrayList<>();
        options2Items_29.add("西宁");
        options2Items_29.add("海东");
        options2Items_29.add("海北");
        options2Items_29.add("黄南");
        options2Items_29.add("海南");
        options2Items_29.add("果洛");
        options2Items_29.add("玉树");
        options2Items_29.add("海西");
        ArrayList<String> options2Items_30 = new ArrayList<>();
        options2Items_30.add("银川");
        options2Items_30.add("石嘴山");
        options2Items_30.add("吴忠");
        options2Items_30.add("固原");
        options2Items_30.add("中卫");
        ArrayList<String> options2Items_31 = new ArrayList<>();
        options2Items_31.add("乌鲁木齐");
        options2Items_31.add("克拉玛依");
        options2Items_31.add("吐鲁番");
        options2Items_31.add("哈密");
        options2Items_31.add("昌吉");
        options2Items_31.add("博尔塔拉蒙古");
        options2Items_31.add("巴音郭楞蒙古");
        options2Items_31.add("阿克苏");
        options2Items_31.add("克孜勒苏柯尔克孜");
        options2Items_31.add("喀什");
        options2Items_31.add("和田");
        options2Items_31.add("伊犁");
        options2Items_31.add("塔城");
        options2Items_31.add("阿勒泰");
        ArrayList<String> options2Items_32 = new ArrayList<>();
        options2Items_32.add("中西区");
        options2Items_32.add("东区");
        options2Items_32.add("九龙城区");
        options2Items_32.add("观塘区");
        options2Items_32.add("南区");
        options2Items_32.add("深水埗区");
        options2Items_32.add("黄大仙区");
        options2Items_32.add("湾仔区");
        options2Items_32.add("油尖旺区");
        options2Items_32.add("离岛区");
        options2Items_32.add("葵青区");
        options2Items_32.add("北区");
        options2Items_32.add("西贡区");
        options2Items_32.add("沙田区");
        options2Items_32.add("屯门区");
        options2Items_32.add("大埔区");
        options2Items_32.add("荃湾区");
        options2Items_32.add("元朗区");
        ArrayList<String> options2Items_33 = new ArrayList<>();
        options2Items_33.add("花地玛堂区");
        options2Items_33.add("圣安多尼堂区");
        options2Items_33.add("望德堂区");
        options2Items_33.add("大堂区");
        options2Items_33.add("风顺堂区");
        options2Items_33.add("离岛");
        options2Items_33.add("氹仔");
        options2Items_33.add("路环 ");
        ArrayList<String> options2Items_34 = new ArrayList<>();
        options2Items_34.add("台北");
        options2Items_34.add("台中");
        options2Items_34.add("基隆");
        options2Items_34.add("高雄");
        options2Items_34.add("台南");
        options2Items_34.add("新竹");
        options2Items_34.add("嘉义");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
        options2Items.add(options2Items_04);
        options2Items.add(options2Items_05);
        options2Items.add(options2Items_06);
        options2Items.add(options2Items_07);
        options2Items.add(options2Items_08);
        options2Items.add(options2Items_09);
        options2Items.add(options2Items_10);
        options2Items.add(options2Items_11);
        options2Items.add(options2Items_12);
        options2Items.add(options2Items_13);
        options2Items.add(options2Items_14);
        options2Items.add(options2Items_15);
        options2Items.add(options2Items_16);
        options2Items.add(options2Items_17);
        options2Items.add(options2Items_18);
        options2Items.add(options2Items_19);
        options2Items.add(options2Items_20);
        options2Items.add(options2Items_21);
        options2Items.add(options2Items_22);
        options2Items.add(options2Items_23);
        options2Items.add(options2Items_24);
        options2Items.add(options2Items_25);
        options2Items.add(options2Items_26);
        options2Items.add(options2Items_27);
        options2Items.add(options2Items_28);
        options2Items.add(options2Items_29);
        options2Items.add(options2Items_30);
        options2Items.add(options2Items_31);
        options2Items.add(options2Items_32);
        options2Items.add(options2Items_33);
        options2Items.add(options2Items_34);
        /*--------数据源添加完毕---------*/
    }

}
