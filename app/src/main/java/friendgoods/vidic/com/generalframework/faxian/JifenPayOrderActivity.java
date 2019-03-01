package friendgoods.vidic.com.generalframework.faxian;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.MorenAddressBean;
import friendgoods.vidic.com.generalframework.Bean.ShowMinejifenBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.faxian.address.GuanliAdddressActivity2;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.faxian.GoodsMoreActivity.jifens;

/**
 * 积分商城确认订单
 */
public class JifenPayOrderActivity extends AppCompatActivity {
    private static final String TAG = "JifenPayOrderActivity";
    SharedPreferencesHelper sharedPreferencesHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_pay_order);
        ButterKnife.bind(this);
        MyApplication.getApplication().addActivity(JifenPayOrderActivity.this);
        sharedPreferencesHelper = new SharedPreferencesHelper(JifenPayOrderActivity.this,"chuanjiabao");
        setTtitle();
        fanhui();
        updaeinfo();
    }

    /**
     * 展示信息后上传
     */
    @BindView(R.id.jifen_pay_order_address_no)
    LinearLayout ll_no;
    @BindView(R.id.jifen_pay_order_address_ok)
    LinearLayout ll_ok;
    @BindView(R.id.jifen_pay_order_name)
    TextView tv_name_phone;
    @BindView(R.id.jifen_pay_order_address)
    TextView tv_address;
    @BindView(R.id.jifen_pay_order_goodsimg)
    ImageView img_goodsimg;
    @BindView(R.id.jifen_pay_order_goodsname)
    TextView tv_goodsname;
    @BindView(R.id.jifen_pay_order_goodstaocan)
    TextView tv_goodstaocan;
    @BindView(R.id.jifen_pay_order_zongjifen)
    TextView tv_zongjia;
    @BindView(R.id.jifen_pay_order_minejifen)
    TextView tv_minejifen;
    @BindView(R.id.jifen_pay_order_jifen)
    LinearLayout ll_huoqujifen;
    @BindView(R.id.jifen_pay_order_liuyan)
    EditText edt_liuyan;
    @BindView(R.id.jifen_pay_order_duihuan)
    Button btn_duihuan;
    private void updaeinfo() {
        Glide.with(JifenPayOrderActivity.this)
                .load(sharedPreferencesHelper.getSharedPreference("goodsimgs","").toString())
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img_goodsimg);
        tv_goodsname.setText(sharedPreferencesHelper.getSharedPreference("goodsname","").toString());
        tv_goodstaocan.setText(sharedPreferencesHelper.getSharedPreference("taocanname","").toString()+"      x"+sharedPreferencesHelper.getSharedPreference("taocanfenshu","").toString());
        tv_zongjia.setText((Integer.parseInt(sharedPreferencesHelper.getSharedPreference("goodsjifen","").toString())*Integer.parseInt(sharedPreferencesHelper.getSharedPreference("taocanfenshu","").toString()))+"积分");

        //查找是否有地址
        OkGo.post(Api.showAddress)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("是否有地址",s);
                        Gson gson = new Gson();
                        MorenAddressBean guanlibean = gson.fromJson(s,MorenAddressBean.class);
                        if (guanlibean.getState()==1){
                            if (guanlibean.getData()!=null){
                                sharedPreferencesHelper.put("addressid", guanlibean.getData().getId()+"");
                                ll_ok.setVisibility(View.VISIBLE);
                                ll_no.setVisibility(View.GONE);
                                tv_name_phone.setText(guanlibean.getData().getAdd_name()+"         "+guanlibean.getData().getAdd_mobile());
                                String showdizhi = guanlibean.getData().getAdd_dizhi()+guanlibean.getData().getArea_diqu();
                                if (showdizhi.length()>15){
                                    tv_address.setText("收货地址："+showdizhi.substring(0,15)+"...");
                                }else {
                                    tv_address.setText("收货地址："+guanlibean.getData().getArea_diqu()+guanlibean.getData().getAdd_dizhi());
                                }

                            }
                        }else{
                            ll_ok.setVisibility(View.GONE);
                            ll_no.setVisibility(View.VISIBLE);
                        }
                    }
                });
        //点击
        ll_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JifenPayOrderActivity.this,GuanliAdddressActivity2.class);
                startActivity(intent);
            }
        });
        ll_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JifenPayOrderActivity.this,GuanliAdddressActivity2.class);
                startActivity(intent);
            }
        });

        //获取积分
        ll_huoqujifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JifenPayOrderActivity.this,GetJifenActivity.class);
                startActivity(intent);
            }
        });

        btn_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: "+"uuid："+Userid
                +"shopName："+tv_goodsname.getText().toString()
                +"shopDimensions："+sharedPreferencesHelper.getSharedPreference("taocanname","").toString()
                +"shopVoucher"+sharedPreferencesHelper.getSharedPreference("goodsjifen","").toString()
                +"totalShopVoucher："+tv_zongjia.getText().toString()
                +"shopNum："+sharedPreferencesHelper.getSharedPreference("taocanfenshu","").toString()
                +"comment："+edt_liuyan.getText().toString()
                +"shopNumber："+sharedPreferencesHelper.getSharedPreference("numbers","").toString()
                +"addressId："+sharedPreferencesHelper.getSharedPreference("addressid","").toString());
                if (UiUtils.isFastClick()){
                    OkGo.post(Api.payjifengoods)
                            .tag(this)
                            .params("uuid",Userid)
                            .params("shopName",tv_goodsname.getText().toString())
                            .params("shopDimensions",sharedPreferencesHelper.getSharedPreference("taocanname","").toString())
                            .params("shopVoucher",sharedPreferencesHelper.getSharedPreference("goodsjifen","").toString())
                            .params("totalShopVoucher",(Integer.parseInt(sharedPreferencesHelper.getSharedPreference("goodsjifen","").toString())*Integer.parseInt(sharedPreferencesHelper.getSharedPreference("taocanfenshu","").toString())))
                            .params("shopNum",sharedPreferencesHelper.getSharedPreference("taocanfenshu","").toString())
                            .params("comment",edt_liuyan.getText().toString())
                            .params("shopNumber",sharedPreferencesHelper.getSharedPreference("numbers","").toString())
                            .params("addressId",sharedPreferencesHelper.getSharedPreference("addressid","").toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.shortToast(e+"");
                                }

                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Gson gson = new Gson();
                                    WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                                    if (wechatBindBean.getState()==1){
                                        ToastUtils.shortToast("兑换成功");
                                        sharedPreferencesHelper.remove("addressid");
                                        sharedPreferencesHelper.remove("addressname");
                                        sharedPreferencesHelper.remove("addressphone");
                                        sharedPreferencesHelper.remove("addressdizhi");
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                MyApplication.getApplication().exit();
//                                                Intent intent = new Intent(JifenPayOrderActivity.this,JifenShoppingmallActivity.class);
//                                                startActivity(intent);
                                            }
                                        },1000);
                                    }else {
                                        ToastUtils.shortToast(wechatBindBean.getMessage());
                                    }
                                }
                            });
                }else {
                    ToastUtils.shortToast("短时间内请勿重复提交");
                }
            }
        });


    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("确认订单");
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
                JifenPayOrderActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询我的积分
        OkGo.post(Api.myjifen)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("兑换商品我的积分",s);
                        Gson gson = new Gson();
                        ShowMinejifenBean showMinejifenBean = gson.fromJson(s,ShowMinejifenBean.class);
                        if (showMinejifenBean.getState()==1){
                            if (showMinejifenBean.getData()!=null){
                                tv_minejifen.setText("当前积分余额："+showMinejifenBean.getData().getIntegral()+"");
                            }
                        }else {
                            ToastUtils.shortToast(showMinejifenBean.getMessage());
                        }
                    }
                });
        if (sharedPreferencesHelper.getSharedPreference("addressid","").toString()!=null){
            ll_ok.setVisibility(View.VISIBLE);
            ll_no.setVisibility(View.GONE);
            tv_name_phone.setText(sharedPreferencesHelper.getSharedPreference("addressname","").toString()+"         "+sharedPreferencesHelper.getSharedPreference("addressphone","").toString());
            String showdizhi = sharedPreferencesHelper.getSharedPreference("addressdizhi","").toString();
            if (showdizhi.length()>15){
                tv_address.setText("收货地址："+showdizhi.substring(0,15)+"...");
            }else {
                tv_address.setText("收货地址："+showdizhi);
            }
        }
    }
}

