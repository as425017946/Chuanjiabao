package friendgoods.vidic.com.generalframework.touzi;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.ObjJinduBean;
import friendgoods.vidic.com.generalframework.Bean.PrivateRenBean;
import friendgoods.vidic.com.generalframework.Bean.PublicshowBean;
import friendgoods.vidic.com.generalframework.Bean.TishiBean;
import friendgoods.vidic.com.generalframework.Bean.TouziMoreBean;
import friendgoods.vidic.com.generalframework.Bean.TouziShowBean;
import friendgoods.vidic.com.generalframework.Bean.TouzirenBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.LoginActivity;
import friendgoods.vidic.com.generalframework.activity.SettingActivity;
import friendgoods.vidic.com.generalframework.adapter.GuanzhuYuyueBean;
import friendgoods.vidic.com.generalframework.adapter.PrivateshowAdapter;
import friendgoods.vidic.com.generalframework.adapter.PublicshowAdapter;
import friendgoods.vidic.com.generalframework.touzi.more.TouziFengxianMoreActivity;
import friendgoods.vidic.com.generalframework.touzi.more.TouziMorefanganFragment;
import friendgoods.vidic.com.generalframework.touzi.more.TouziMorejieshaoFragment;
import friendgoods.vidic.com.generalframework.touzi.more.TouziMorepiluFragment;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.MyScrollView;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 投资详情页面
 */
public class TouziMoreActivity extends BaseActivity{
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    public static String zhiid,zhinumber,objjieshao,touzifangan,xinxipilou,xianmguname;
//    public static String objjieshao1,objjieshao2,objjieshao3,objjieshao4,objjieshao5,objjieshao6,
//                          objjieshao7,objjieshao8,touzifangan9,number10,uudi;
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_more);
        ButterKnife.bind(this);
        fanhui();
        zhiid = getIntent().getStringExtra("objid");
        sharehelper = new SharedPreferencesHelper(TouziMoreActivity.this,"chuanjiabao");
        MyApplication.getApplication().addActivity(TouziMoreActivity.this);
        setChioceItem(0);
        onclicks();
    }

    //写入信息
    @BindView(R.id.tzmore_Img)
    ImageView imageView;
    @BindView(R.id.tzmore_name)
    TextView tv_name;
    @BindView(R.id.tzmore_state)
    TextView tv_state;
    @BindView(R.id.tzmore_xiangmuname)
    TextView tv_objname;
    @BindView(R.id.tzmore_leixing)
    TextView tv_leixing;
    @BindView(R.id.tzmore_shouyiquan)
    TextView tv_shouyiquan;
    @BindView(R.id.tzmore_address)
    TextView tv_dizhi;
    @BindView(R.id.tzmore_jianjie)
    TextView tv_jianjie;
    @BindView(R.id.tzmore_time)
    TextView tv_shengyutime;
    @BindView(R.id.tzmore_jindu)
    TextView tv_rengoujindu;
    @BindView(R.id.tzmore_progressBar)
    ProgressBar bar;
    @BindView(R.id.tzmore_rengou)
    Button btn_rengou;
    @BindView(R.id.tz_weidenglu)
    LinearLayout l_weidenglu;
    @BindView(R.id.tz_weidenglu2)
    LinearLayout l_weidenglu2;
    @BindView(R.id.touzi_more_mubiao)
    TextView tv_mubiao;
    @BindView(R.id.touzi_more_yitou)
    TextView tv_yitou;
    @BindView(R.id.touzi_more_qitou)
    TextView tv_qitou;
    @BindView(R.id.tz_weidenglu3)
    View view3;
    @BindView(R.id.tz_denglu)
    LinearLayout l_denglu;
    @BindView(R.id.tzmore_fenxiang)
    LinearLayout l_fenxiang;
    @BindView(R.id.tzmore_jinqun)
    LinearLayout l_jinqu;
    @BindView(R.id.tzmore_yuding)
    LinearLayout l_yuding;
    double zhi = 0;
    private void setInfo() {
        if (TextUtils.isEmpty(Userid)){
            l_weidenglu.setVisibility(View.VISIBLE);
            l_weidenglu2.setVisibility(View.GONE);
            l_denglu.setVisibility(View.VISIBLE);
            view3.setVisibility(View.GONE);
        }else{
            l_weidenglu.setVisibility(View.GONE);
            l_weidenglu2.setVisibility(View.VISIBLE);
            l_denglu.setVisibility(View.VISIBLE);
            view3.setVisibility(View.VISIBLE);
        }

        l_weidenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TouziMoreActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //进群加微信
        l_jinqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TouziMoreActivity.this);
                LayoutInflater inflater = LayoutInflater.from(TouziMoreActivity.this);
                final View DialogView = inflater .inflate (R.layout.jinqun, null);//1、自定义布局
                final Button btnok = (Button)DialogView.findViewById(R.id.jinqun_btn);
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();

                final ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipData clipData = ClipData.newPlainText("copy from demo", "123456");
                        mClipboardManager.setPrimaryClip(clipData);
                        ToastUtils.shortToast("复制成功！");
                        dialog.dismiss();
                    }
                });

            }
        });
        //预约
        l_yuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TouziMoreActivity.this);
                LayoutInflater inflater = LayoutInflater.from(TouziMoreActivity.this);
                final View DialogView = inflater .inflate (R.layout.yuyue, null);//1、自定义布局
                final Button btnquxiao = (Button)DialogView.findViewById(R.id.yuyue_quxiao);
                final Button btnok = (Button)DialogView.findViewById(R.id.yuyue_queding);
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
                btnquxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


        OkGo.post(Api.touzimore)
                .tag(this)
                .params("number",zhiid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投资详情",s);
                        final Gson gson = new Gson();

                        final TouziMoreBean touziMoreBean = gson.fromJson(s,TouziMoreBean.class);
                        if (touziMoreBean.getState()==1){
                            if (touziMoreBean.getData()!=null){
                                //传入项目编号
                                xinxipilou(touziMoreBean.getData().getItems_number());
                                String objjieshao1 = "",objjieshao2 = "",objjieshao3 = "",objjieshao4 = "",objjieshao5 = "",
                                        objjieshao6 = "",objjieshao7 = "",objjieshao8 = "";
//                            //项目介绍
                                //项目进展 1：建筑未施工 2：建筑施工中 3：待营业 4:营业中
                                if (touziMoreBean.getData().getItems_now().equals("1")){
                                    objjieshao1 = "建筑未施工";
                                }else  if (touziMoreBean.getData().getItems_now().equals("2")){
                                    objjieshao1 = "建筑施工中";
                                }else  if (touziMoreBean.getData().getItems_now().equals("3")){
                                    objjieshao1 = "待营业";
                                }else  if (touziMoreBean.getData().getItems_now().equals("4")){
                                    objjieshao1 = "营业中";
                                }
                                //物业所属 1:租赁 2:自持有 3:其他
                                if (touziMoreBean.getData().getItems_estate().equals("1")){
                                    objjieshao2 = "租赁";
                                }else if (touziMoreBean.getData().getItems_estate().equals("2")){
                                    objjieshao2 = "自持有";
                                }else if (touziMoreBean.getData().getItems_estate().equals("3")){
                                    objjieshao2 = "其他";
                                }
                                objjieshao3 = touziMoreBean.getData().getItems_area();
                                objjieshao4 = touziMoreBean.getData().getItems_quantity();
                                objjieshao5 = touziMoreBean.getData().getItems_unit_price();
                                objjieshao6 = touziMoreBean.getData().getItems_buy_money();
                                objjieshao7= touziMoreBean.getData().getItems_money();
                                objjieshao8 = touziMoreBean.getData().getItems_comment();
                                //传入项目介绍
                                objjieshao(objjieshao1,objjieshao2,objjieshao3,objjieshao4,objjieshao5,objjieshao6,objjieshao7,objjieshao8);
                               //传入信息投资方案
                                touziafangan(touziMoreBean.getData().getItems_earnings_introduce());


                                zhinumber = touziMoreBean.getData().getItems_number();
                                //认购按钮
                                btn_rengou.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(TextUtils.isEmpty(Userid)){
//                                        ToastUtils.shortToast("请先登录平台！");
                                            Intent intent = new Intent(TouziMoreActivity.this,LoginActivity.class);
                                            startActivity(intent);
                                        }else{
                                            if (btn_rengou.getText().toString().equals("关注")){
                                                yugao(touziMoreBean.getData().getItems_number());
                                                btn_rengou.setBackgroundResource(R.mipmap.buybutton_none);
                                                btn_rengou.setText("已关注");
                                            }else if (btn_rengou.getText().toString().equals("预约")){
                                                yuyue(touziMoreBean.getData().getItems_number());
                                                btn_rengou.setBackgroundResource(R.mipmap.buybutton_none);
                                                btn_rengou.setText("已预约");
                                            }else if(btn_rengou.getText().toString().equals("认购")){
                                                Intent intent = new Intent(TouziMoreActivity.this, TouziFengxianMoreActivity.class);
                                                intent.putExtra("number",touziMoreBean.getData().getItems_number()+"");
                                                intent.putExtra("name",touziMoreBean.getData().getItems_name());
                                                startActivity(intent);
                                            }else if (btn_rengou.getText().toString().equals("已关注")){
                                                yugao2(touziMoreBean.getData().getItems_number());
                                                btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                                btn_rengou.setText("关注");
                                            }else if (btn_rengou.getText().toString().equals("已预约")){
                                                yuyue2(touziMoreBean.getData().getItems_number());
                                                btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                                btn_rengou.setText("预约");
                                            }
                                        }
                                    }
                                });

                                //展示图片
                                String shwoimgurl = Api.ossurl + touziMoreBean.getData().getItems_photo1();
                                Glide.with(TouziMoreActivity.this)
                                        .load(shwoimgurl)
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(imageView);

                                tv_name.setText("发起人："+touziMoreBean.getData().getUser_name());
                                //转换项目状态
                                if (touziMoreBean.getData().getItems_status().equals("1")){
                                    tv_state.setText("预告中");
                                    btn_rengou.setText("关注");
                                    btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                    Log.e("是否关注了",Userid+"*"+touziMoreBean.getData().getItems_number());
                                    //判断当前用户是否关注了该项目
                                    OkGo.post(Api.isguanzhu)
                                            .tag(this)
                                            .params("uuid",Userid)
                                            .params("number",touziMoreBean.getData().getItems_number())
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Response response, Exception e) {
                                                    super.onError(call, response, e);
                                                    ToastUtils.shortToast("获取数据异常");
                                                }

                                                @Override
                                                public void onSuccess(String s, Call call, Response response) {
                                                    Log.e("是否关注了",s+"*");
                                                    Gson gson1 = new Gson();
                                                    GuanzhuYuyueBean tishiBean = gson1.fromJson(s,GuanzhuYuyueBean.class);
                                                    if (tishiBean.getState()==1){
                                                        if (tishiBean.getData()!=null){
                                                            if (tishiBean.getData().getStatus1().equals("1")){
                                                                btn_rengou.setBackgroundResource(R.mipmap.buybutton_none);
                                                                btn_rengou.setText("已关注");
                                                            }else {
                                                                btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                                                btn_rengou.setText("关注");
                                                            }
                                                        }


                                                    }
                                                }
                                            });
                                }else if (touziMoreBean.getData().getItems_status().equals("2")){
                                    tv_state.setText("预约中");
                                    btn_rengou.setText("预约");
                                    btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                    //判断当前用户是否关注了该项目
                                    OkGo.post(Api.isguanzhu)
                                            .tag(this)
                                            .params("uuid",Userid)
                                            .params("number",touziMoreBean.getData().getItems_number())
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onSuccess(String s, Call call, Response response) {
//                                                Log.e("关注？",s+"*");
                                                    Gson gson1 = new Gson();
                                                    GuanzhuYuyueBean tishiBean = gson1.fromJson(s,GuanzhuYuyueBean.class);
                                                    if (tishiBean.getState()==1){
                                                        if (tishiBean.getData()!=null){
                                                            if (tishiBean.getData().getStatus2().equals("1")){
                                                                btn_rengou.setBackgroundResource(R.mipmap.buybutton_none);
                                                                btn_rengou.setText("已预约");
                                                            }else {
                                                                btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                                                btn_rengou.setText("预约");
                                                            }
                                                        }

                                                    }
                                                }
                                            });

                                }else if (touziMoreBean.getData().getItems_status().equals("3")){
                                    tv_state.setText("认购中");
                                    btn_rengou.setText("认购");
                                    btn_rengou.setBackgroundResource(R.mipmap.buybotton);
                                    //判断当前用户是否认购了该项目
                                    OkGo.post(Api.isrengou)
                                            .tag(this)
                                            .params("uuid",Userid)
                                            .params("schemeId",touziMoreBean.getData().getId())
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onSuccess(String s, Call call, Response response) {
                                                    Log.e("是否认购了",s);
                                                    Gson gson1 = new Gson();
//                                                TishiBean tishiBean = gson1.fromJson(s,TishiBean.class);
//                                                if (tishiBean.getState()==0){
//                                                    btn_rengouok.setVisibility(View.VISIBLE);
//                                                    btn_rengou.setVisibility(View.GONE);
//                                                    btn_rengouok.setText("已认购");
//                                                }
                                                }
                                            });

                                }else if (touziMoreBean.getData().getItems_status().equals("4")){
                                    tv_state.setText("已完成");
                                    btn_rengou.setText("已完成");
                                    btn_rengou.setBackgroundResource(R.mipmap.buybutton_none);

                                }
                                tv_objname.setText(touziMoreBean.getData().getItems_name());
                                //修改标题
                                if (touziMoreBean.getData().getItems_name().length()>8){
                                    tv_title.setText(touziMoreBean.getData().getItems_name().substring(0,8)+"...");
                                }else {
                                    tv_title.setText(touziMoreBean.getData().getItems_name());
                                }



                                tv_leixing.setText(touziMoreBean.getData().getItems_type());
                                //转换类型状态
                                if (touziMoreBean.getData().getFunding_type()==null){
                                    tv_shouyiquan.setText("");
                                }else {
                                    if(touziMoreBean.getData().getFunding_type().equals("1")){
                                        tv_shouyiquan.setText("股权");
                                    }else if (touziMoreBean.getData().getFunding_type().equals("2")){
                                        tv_shouyiquan.setText("消费权");
                                    }else if (touziMoreBean.getData().getFunding_type().equals("3")){
                                        tv_shouyiquan.setText("收益权");
                                    }
                                }

                                tv_dizhi.setText(touziMoreBean.getData().getItems_site());
                                tv_jianjie.setText(touziMoreBean.getData().getItems_comment());
                                tv_shengyutime.setText("剩余时间:"+touziMoreBean.getData().getDay()+"天");
                                if (touziMoreBean.getData().getMoney()==null){
                                    tv_rengoujindu.setText("认购进度:0%");
                                }else {

                                    if (touziMoreBean.getData().getMoney().length()>3){
                                        zhi = Double.parseDouble(touziMoreBean.getData().getMoney().substring(0,3));
                                    }else {
                                        zhi = Double.parseDouble(touziMoreBean.getData().getMoney());
                                    }
                                    //动画展示进度条信息
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            int i = 0;
                                            while (i < zhi) {
                                                i++;
                                                try {
                                                    Thread.sleep(80);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                final int j = i;
                                                bar.setProgress(i);

                                            }
                                        }
                                    }.start();
                                    tv_rengoujindu.setText("认购进度:" + zhi + "%");
                                }
                                if (touziMoreBean.getData().getItems_money()==null){
                                    tv_mubiao.setText("0万元");
                                }else {
                                    if ((Double.parseDouble(touziMoreBean.getData().getItems_money())/1000)>9999){
                                        String money = (Double.parseDouble(touziMoreBean.getData().getFunding_start_money()))/100000000+"";
                                        tv_qitou.setText(money.substring(0,4)+"亿元");
                                    }else {
                                        tv_mubiao.setText((Double.parseDouble(touziMoreBean.getData().getItems_money())/1000)+"万元");
                                    }

                                }
                                if (touziMoreBean.getData().getSumMoney()==null){
                                    tv_yitou.setText("0万元");
                                } else {
                                    if ((Double.parseDouble(touziMoreBean.getData().getSumMoney())/10000)>9999){
                                        String money = (Double.parseDouble(touziMoreBean.getData().getFunding_start_money()))/100000000+"";
                                        tv_qitou.setText(money.substring(0,4)+"亿元");
                                    }else {
                                        tv_yitou.setText((Double.parseDouble(touziMoreBean.getData().getSumMoney())/10000)+"万元");
                                    }

                                }

                                if (touziMoreBean.getData().getFunding_start_money()==null){
                                    tv_qitou.setText("0万元");
                                }else {
                                    if ((Double.parseDouble(touziMoreBean.getData().getFunding_start_money()))/1000>9999){
                                        String money = (Double.parseDouble(touziMoreBean.getData().getFunding_start_money()))/100000000+"";
                                        tv_qitou.setText(money.substring(0,4)+"亿元");
                                    }else {
                                        tv_qitou.setText((Double.parseDouble(touziMoreBean.getData().getFunding_start_money()))/1000+"万元");
                                    }

                                }


                            }
                        }else{
                            ToastUtils.shortToast(touziMoreBean.getMessage());
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast("数据信息异常");
                    }
                });
    }

    private void yugao(String zhi){
        OkGo.post(Api.touziguanzhu)
                .tag(this)
                .params("uuid",Userid)
                .params("number",zhi)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("关注",s);
                        Gson gson = new Gson();
                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                        if (wx.getState()==1){


                            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(TouziMoreActivity.this);
                            LayoutInflater inflater = LayoutInflater.from(TouziMoreActivity.this);
                            final View DialogView = inflater .inflate (R.layout.jinqun2, null);//1、自定义布局
                            final Button btnok = (Button)DialogView.findViewById(R.id.jinqun_btn);
                            builder.setView(DialogView);
                            final android.support.v7.app.AlertDialog dialog = builder.create();
                            dialog.show();

                            final ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            btnok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ClipData clipData = ClipData.newPlainText("copy from demo", "123456");
                                    mClipboardManager.setPrimaryClip(clipData);
                                    ToastUtils.shortToast("复制成功！");
                                    dialog.dismiss();
                                }
                            });

//                            ToastUtils.shortToast("关注成功！");
                        }else{

                        }
                    }
                });
    }
    private void yugao2(String zhi){
        OkGo.post(Api.touziguanzhu2)
                .tag(this)
                .params("uuid",Userid)
                .params("number",zhi)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("取消关注",s);
                        Gson gson = new Gson();
                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                        if (wx.getState()==1){
                            ToastUtils.shortToast("取消关注成功！");
                        }else{

                        }
                    }
                });
    }


    private void yuyue(String zhi){
        OkGo.post(Api.touziyuyue)
                .tag(this)
                .params("number",zhi)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("预约",s);
                        Gson gson = new Gson();
                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                        if (wx.getState()==1){
                            ToastUtils.shortToast("预约成功！");
                        }else{

                        }
                    }
                });
    }
    private void yuyue2(String zhi){
        OkGo.post(Api.touziyuyue2)
                .tag(this)
                .params("number",zhi)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("预约2",s);
                        Gson gson = new Gson();
                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                        if (wx.getState()==1){
                            ToastUtils.shortToast("取消预约成功！");
                        }else{

                        }
                    }
                });
    }
    //返回按钮和修改标题
    @BindView(R.id.touzi_fanhui)
    LinearLayout ll_fanhui;
    @BindView(R.id.touzi_title)
    TextView tv_title;
    private void fanhui(){

        //点击返回按钮
        ll_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouziMoreActivity.this.finish();
            }
        });
    }

    /**
     * 项目介绍，投资方案，信息披露
     * 按钮点击变化
     */
    @BindView(R.id.private_xiangmu)
    LinearLayout liner_xiangmu;
    @BindView(R.id.private_fangan)
    LinearLayout liner_fangan;
    @BindView(R.id.private_pilu)
    LinearLayout liner_pilu;
    @BindView(R.id.private_xiangmu_tv)
    TextView tv_xiangmu;
    @BindView(R.id.private_xiangmu_view)
    View view_xiangmu;
    @BindView(R.id.private_fangan_tv)
    TextView tv_fangan;
    @BindView(R.id.private_fangan_view)
    View view_fangan;
    @BindView(R.id.private_pilu_tv)
    TextView tv_pilu;
    @BindView(R.id.private_pilu_view)
    View view_pilu;
    @BindView(R.id.touzi_more_xiangmujieshao)
    LinearLayout liner_more_xiangmu;
    @BindView(R.id.touzi_more_touzifangan)
    LinearLayout liner_more_fangan;
    @BindView(R.id.touzi_more_xinxipilou)
    LinearLayout liner_more_pilu;

    private void onclicks(){
        liner_xiangmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChioceItem(0);
            }
        });
        liner_fangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChioceItem(1);
            }
        });
        liner_pilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChioceItem(2);
            }
        });
    }
    /**
     * 项目介绍，投资方案，信息披露
     * 按钮点击变化
     */
    private void setChioceItem(int index){
        clearChioce();//先清空所有样式
        switch (index){
            case 0:
                tv_xiangmu.setTextColor(green);
                view_xiangmu.setBackgroundColor(green);
                liner_more_xiangmu.setVisibility(View.VISIBLE);
                break;
            case 1:
                tv_fangan.setTextColor(green);
                view_fangan.setBackgroundColor(green);
                liner_more_fangan.setVisibility(View.VISIBLE);
                break;
            case 2:
                tv_pilu.setTextColor(green);
                view_pilu.setBackgroundColor(green);
                liner_more_pilu.setVisibility(View.VISIBLE);
                break;

        }
    }
    /***
     * 全部都是灰色
     * 所有信息都设置成隐藏
     */
    int black = 0xFF636363;  //未选中颜色
    int green = 0xFF6CBF85;  //字体选中后的颜色
    int white = 0xFFffffff; //横线未选择的颜色
    private void clearChioce(){
        tv_xiangmu.setTextColor(black);
        view_xiangmu.setBackgroundColor(white);
        tv_fangan.setTextColor(black);
        view_fangan.setBackgroundColor(white);
        tv_pilu.setTextColor(black);
        view_pilu.setBackgroundColor(white);
        liner_more_xiangmu.setVisibility(View.GONE);
        liner_more_fangan.setVisibility(View.GONE);
        liner_more_pilu.setVisibility(View.GONE);

    }


    /**
     * 项目介绍
     */
    @BindView(R.id.more_xiangmujieshao1)
    TextView more_xiangmujieshao1;
    @BindView(R.id.more_xiangmujieshao2)
    TextView more_xiangmujieshao2;
    @BindView(R.id.more_xiangmujieshao3)
    TextView more_xiangmujieshao3;
    @BindView(R.id.more_xiangmujieshao4)
    TextView more_xiangmujieshao4;
    @BindView(R.id.more_xiangmujieshao5)
    TextView more_xiangmujieshao5;
    @BindView(R.id.more_xiangmujieshao6)
    TextView more_xiangmujieshao6;
    @BindView(R.id.more_xiangmujieshao7)
    TextView more_xiangmujieshao7;
    @BindView(R.id.more_xiangmujieshao8)
    TextView more_xiangmujieshao8;
    private void objjieshao(String objjieshao1,String objjieshao2,String objjieshao3,String objjieshao4,
                            String objjieshao5,String objjieshao6,String objjieshao7,String objjieshao8){
        more_xiangmujieshao1.setText(objjieshao1);
        more_xiangmujieshao2.setText(objjieshao2);
        more_xiangmujieshao3.setText(objjieshao3);
        more_xiangmujieshao4.setText(objjieshao4);
        more_xiangmujieshao5.setText(objjieshao5);
        more_xiangmujieshao6.setText(objjieshao6);
        more_xiangmujieshao7.setText(objjieshao7);
        more_xiangmujieshao8.setText(objjieshao8);
    }

    /**
     * 投资方案
     */
    @BindView(R.id.touzi_fangan)
    TextView tv_fangan_info;
    private void touziafangan(String touzifangan9){
        tv_fangan_info.setText(touzifangan9);
    }

    /**
     * 信息披露
     */
    PublicshowAdapter publicshowAdapter;
    ArrayList<PublicshowBean> arrayList = new ArrayList<>();
    PrivateshowAdapter privateshowAdapter;
    ArrayList<PrivateRenBean> arrayList2 = new ArrayList<>();
    @BindView(R.id.gongkai_listview)
    ListView listView;
    @BindView(R.id.gongkaiziliao)
    Button btn_gongkai;
    @BindView(R.id.touzirenkejian)
    Button btn_kejian;
    @BindView(R.id.L_gongkai)
    LinearLayout l_gongkai;
    @BindView(R.id.L_kejian)
    LinearLayout l_kejian;
    @BindView(R.id.L_kejian_no)
    LinearLayout l_no;
    @BindView(R.id.gongkai_listview2)
    ListView listView2;
    private void xinxipilou(String bianhao){
        publicshowAdapter = new PublicshowAdapter(TouziMoreActivity.this,arrayList);
        privateshowAdapter = new PrivateshowAdapter(TouziMoreActivity.this,arrayList2);

        getprivate(bianhao);
        getpublic(bianhao);
        //点击公开资料
        btn_gongkai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_gongkai.setBackgroundResource(R.mipmap.infobutton_click);
                btn_gongkai.setTextColor(Color.parseColor("#FFFFFFFF"));
                btn_kejian.setBackgroundResource(R.mipmap.infobutton_unclick);
                btn_kejian.setTextColor(Color.parseColor("#6CBF85"));
                l_gongkai.setVisibility(View.VISIBLE);
                l_kejian.setVisibility(View.GONE);
            }
        });
        //点击投资人可见
        btn_kejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kejian.setBackgroundResource(R.mipmap.infobutton_click);
                btn_kejian.setTextColor(Color.parseColor("#FFFFFFFF"));
                btn_gongkai.setBackgroundResource(R.mipmap.infobutton_unclick);
                btn_gongkai.setTextColor(Color.parseColor("#6CBF85"));
                l_kejian.setVisibility(View.VISIBLE);
                l_gongkai.setVisibility(View.GONE);

                //判断当前用户是否认购了该项目
                OkGo.post(Api.isrengou)
                        .tag(this)
                        .params("uuid",Userid)
                        .params("schemeId",zhiid)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.e("是否购买了",s);
                                Gson gson1 = new Gson();
                                TouzirenBean touzirenBean = gson1.fromJson(s,TouzirenBean.class);
                                if (touzirenBean.getState()==1){
                                    if (touzirenBean.getData()==null){
                                        listView2.setVisibility(View.GONE);
                                        l_no.setVisibility(View.VISIBLE);
                                    }else {
                                        l_no.setVisibility(View.GONE);
                                        listView2.setVisibility(View.VISIBLE);

                                    }

                                }
                            }
                        });

            }
        });
    }
    /**
     * 获取投资人的信息
     */
    private void getprivate(String number10) {
        OkGo.post(Api.touzirenshow)
                .tag(this)
                .params("number",number10)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投资人信息",s);
                        Gson gson = new Gson();
                        PrivateRenBean privateRenBean = gson.fromJson(s,PrivateRenBean.class);
                        if (privateRenBean.getState()==1){
                            if (privateRenBean.getData().size()>0){
                                for (int i = 0; i < privateRenBean.getData().size(); i++) {
                                    arrayList2.add(privateRenBean);
                                }
                                listView2.setAdapter(privateshowAdapter);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast("获取数据异常");
                    }
                });
    }

    /**
     * 获取公开资料的信息
     */
    private void getpublic(String number10) {
//        Log.e("订单编号",number10);
        OkGo.post(Api.publicshow)
                .tag(this)
                .params("number",number10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("全部信息",s);
                        Gson gson = new Gson();
                        PublicshowBean publicshowBean = gson.fromJson(s,PublicshowBean.class);
                        if (publicshowBean.getState()==1){
                            if (publicshowBean.getData().size()>0){
                                for (int i = 0; i < publicshowBean.getData().size() ; i++) {
                                    arrayList.add(publicshowBean);
                                }
                                listView.setAdapter(publicshowAdapter);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast("获取数据异常");
                    }
                });
    }

//    /***
//     * 展示选项卡
//     */
//    private void Touzi_more_tab(){
//        TouziMorejieshaoFragment jieshao =new  TouziMorejieshaoFragment();
//        TouziMorefanganFragment fangan = new TouziMorefanganFragment();
//        TouziMorepiluFragment pilu = new TouziMorepiluFragment();
//        mFragments.add(jieshao);
//        mFragments.add(fangan);
//        mFragments.add(pilu);
//        list.add("项目介绍");
//        list.add("投资方案");
//        list.add("信息被露");
//        tz_more_tab.setupWithViewPager(tz_more_viewpager);
//        tz_more_viewpager.setAdapter(new TouziAdapter(getSupportFragmentManager(),mFragments,list));
//        tz_more_tab.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(tz_more_tab, 10, 10);
//            }
//        });
//    }
//    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        tabStrip.setAccessible(true);
//        LinearLayout llTab = null;
//        try {
//            llTab = (LinearLayout) tabStrip.get(tabs);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
//        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
//
//        for (int i = 0; i < llTab.getChildCount(); i++) {
//            View child = llTab.getChildAt(i);
//            child.setPadding(0, 0, 0, 0);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//            params.leftMargin = left;
//            params.rightMargin = right;
//            child.setLayoutParams(params);
//            child.invalidate();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Userid = sharehelper.getSharedPreference("userid","").toString().trim();

        setInfo();
    }
}
