package friendgoods.vidic.com.generalframework.touzi.more;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.EmailBean;
import friendgoods.vidic.com.generalframework.Bean.GuanliAddressBean;
import friendgoods.vidic.com.generalframework.Bean.MorenAddressBean;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.wode.GuanliAdddressActivity;
import friendgoods.vidic.com.generalframework.faxian.JifenPayOrderActivity;
import friendgoods.vidic.com.generalframework.faxian.address.GuanliAdddressActivity2;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.touzi.EmailRengouActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.touzi.more.TouziFengxianMoreActivity.Names;

/**
 * 认购页面
 */
public class TouziRengouMoreActivity extends BaseActivity {
    SharedPreferencesHelper sharedPreferencesHelper;
    String objname,danjia,xiangou,shengyu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_rengou_more);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(TouziRengouMoreActivity.this,"chuanjiabao");
        setTtitle();
        fanhui();
        setRengou();
        objname = getIntent().getStringExtra("objname");
        danjia = getIntent().getStringExtra("danjia");
        xiangou = getIntent().getStringExtra("xiangou");
        shengyu = getIntent().getStringExtra("shengyu");
        Log.e("剩余",shengyu);
        setinfo();
        MyApplication.getApplication().addActivity(TouziRengouMoreActivity.this);
    }

    /**
     * 显示信息并提交
     */
    @BindView(R.id.sanjirengou_danjia)
    TextView tv_danjia;
    @BindView(R.id.sanjirengou_xiangou)
    TextView tv_xiangou;
    @BindView(R.id.sanjirengou_objname)
    TextView tv_onjname;
    @BindView(R.id.sanjirengou_shengyu)
    TextView tv_shengyu;
    @BindView(R.id.sanjirengou_jian)
    ImageView img_jian;
    @BindView(R.id.sanjirengou_jia)
    ImageView img_jia;
    @BindView(R.id.sanjirengou_payfenshu)
    TextView tv_pay;
    @BindView(R.id.sanjirengou_xianshi)
    TextView tv_xianshi;
    @BindView(R.id.sanjirengou_email)
    TextView tv_email;
    @BindView(R.id.sanjirengou_emailimg)
    ImageView img_next;
    @BindView(R.id.sanjirengou_youxiang)
    LinearLayout l_youxiang;
    @BindView(R.id.sanjirengou_dizhi)
    LinearLayout l_dizhi;
    @BindView(R.id.sanjirengou_dizhi2)
    LinearLayout l_dizhi2;
    @BindView(R.id.sanjirengou_dizhi2_name)
    TextView tv_name;
    @BindView(R.id.sanjirengou_dizhi2_phone)
    TextView tv_phone;
    @BindView(R.id.sanjirengou_dizhi2_dizhi)
    TextView tv_dihzi;
    @BindView(R.id.sanjirengou_dizhi_dizhi)
    TextView tv_dizhi1;

    int fenshu=0;

    private void setinfo() {
        tv_danjia.setText(danjia);
        tv_xiangou.setText("个人限购"+xiangou+"份");
        tv_onjname.setText("【"+Names+"】"+objname);
        tv_shengyu.setText("(库存剩余"+shengyu+"份)");
        tv_xianshi.setText("您还可添加"+shengyu+"份");

        //减号操作
        img_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("减号","22*"+fenshu);
                if (fenshu==0){
                    tv_pay.setText("0");
                }else {
                    fenshu--;
                    tv_pay.setText(fenshu+"");
                    tv_xianshi.setText("您还可添加"+(Integer.parseInt(shengyu)-Integer.parseInt(tv_pay.getText().toString()))+"份");
                }
            }
        });
        //加号操作
        img_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("加号","11*"+fenshu);
                if (Integer.parseInt(xiangou)<=Integer.parseInt(shengyu)){
                    if (fenshu>=Integer.parseInt(xiangou)){
                        tv_pay.setText(fenshu+"");
                    }else{
                        fenshu++;
                        tv_pay.setText(fenshu+"");
                        tv_xianshi.setText("您还可添加"+(Integer.parseInt(xiangou)-Integer.parseInt(tv_pay.getText().toString()))+"份");

                    }
                }else {
                    if (fenshu>=Integer.parseInt(shengyu)){
                        tv_pay.setText(fenshu+"");
                    }else{
                        fenshu++;
                        tv_pay.setText(fenshu+"");
                        tv_xianshi.setText("您还可添加"+(Integer.parseInt(shengyu)-Integer.parseInt(tv_pay.getText().toString()))+"份");

                    }
                }

            }
        });

        //点击邮箱
        l_youxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_email.getText().toString().equals("请输入邮箱")){
                    Intent intent = new Intent(TouziRengouMoreActivity.this, EmailRengouActivity.class);
                    startActivity(intent);
                }
            }
        });
//        //点击输入地址
//        l_dizhi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TouziRengouMoreActivity.this, TouziMoreAddressActivity.class);
//                startActivity(intent);
//            }
//        });

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
                                l_dizhi2.setVisibility(View.VISIBLE);
                                l_dizhi.setVisibility(View.GONE);
                                tv_dizhi1.setText("更换地址");
                                tv_name.setText("姓名："+guanlibean.getData().getAdd_name());
                                tv_phone.setText("手机号："+guanlibean.getData().getAdd_mobile());
                                String showdizhi = guanlibean.getData().getAdd_dizhi()+guanlibean.getData().getArea_diqu();
                                if (showdizhi.length()>15){
                                    tv_dihzi.setText("收货地址："+showdizhi.substring(0,15)+"...");
                                }else {
                                    tv_dihzi.setText("收货地址："+guanlibean.getData().getArea_diqu()+guanlibean.getData().getAdd_dizhi());
                                }

                            }
                        }else{
                            l_dizhi2.setVisibility(View.GONE);
                            l_dizhi.setVisibility(View.VISIBLE);
                            tv_dizhi1.setText("请输入地址");
                        }
                    }
                });

    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("认购");
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
                TouziRengouMoreActivity.this.finish();
            }
        });
    }
    /**
     * 认购按钮
     */
    @BindView(R.id.touzi_more_rengou)
    Button btn_rengou;
    private void setRengou(){
        btn_rengou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_pay.getText().toString().equals("0")){
                    ToastUtils.shortToast("请选择购买数量");
                }else if (tv_email.getText().toString().equals("请输入邮箱")){
                    ToastUtils.shortToast("请输入邮箱");
                }else if (tv_dizhi1.getText().toString().equals("请输入地址")){
                    ToastUtils.shortToast("请输入地址");
                }else{
                    Intent intent = new Intent(TouziRengouMoreActivity.this,TouziRengouMore2Activity.class);
                    intent.putExtra("sanjiobjname",objname);
                    intent.putExtra("sanjidanjia",danjia+"");
                    intent.putExtra("sanjifenshu",tv_pay.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }

    private static final String TAG = "TouziRengouMoreActivity";
    @Override
    protected void onResume() {
        super.onResume();
        //查找是否有邮箱
        OkGo.post(Api.showemail)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("是否有邮箱",s);
                        Gson gson = new Gson();
                        EmailBean emailBean = gson.fromJson(s,EmailBean.class);
                        if (emailBean.getState()==1){
                            tv_email.setText(emailBean.getData().getUser_Email()+"");
                            img_next.setVisibility(View.GONE);
                        }else{

                        }
                    }
                });

        //点击
        l_dizhi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TouziRengouMoreActivity.this,GuanliAdddressActivity2.class);
                startActivity(intent);
            }
        });
        tv_dizhi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TouziRengouMoreActivity.this,GuanliAdddressActivity2.class);
                startActivity(intent);
            }
        });
//        Log.e(TAG, "onResume: "+sharedPreferencesHelper.getSharedPreference("addressid","").toString() );
        if (sharedPreferencesHelper.getSharedPreference("addressid","").toString()!=null && sharedPreferencesHelper.getSharedPreference("addressid","").toString().equals("")){
            l_dizhi2.setVisibility(View.VISIBLE);
            l_dizhi.setVisibility(View.GONE);
            tv_dizhi1.setText("更换地址");
            tv_name.setText("姓名："+sharedPreferencesHelper.getSharedPreference("addressname","").toString());
            tv_phone.setText("手机号："+sharedPreferencesHelper.getSharedPreference("addressphone","").toString());
            String showdizhi = sharedPreferencesHelper.getSharedPreference("addressdizhi","").toString();
            if (showdizhi.length()>15){
                tv_dihzi.setText("收货地址："+showdizhi.substring(0,15)+"...");
            }else {
                tv_dihzi.setText("收货地址："+showdizhi);
            }



        }
    }

}
