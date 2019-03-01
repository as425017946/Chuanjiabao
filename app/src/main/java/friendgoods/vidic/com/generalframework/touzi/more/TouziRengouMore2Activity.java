package friendgoods.vidic.com.generalframework.touzi.more;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.TishiBean;
import friendgoods.vidic.com.generalframework.Bean.TouziOkPayBean;
import friendgoods.vidic.com.generalframework.Main2Activity;
import friendgoods.vidic.com.generalframework.MainActivity;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.CouponActivity2;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.zhiid;
import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.zhinumber;
import static friendgoods.vidic.com.generalframework.touzi.more.TouziFengxianMoreActivity.Names;

public class TouziRengouMore2Activity extends BaseActivity {
    SharedPreferencesHelper sharedPreferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_rengou_more2);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(TouziRengouMore2Activity.this,"chuanjiabao");
        setTtitle();
        fanhui();
        setinfo();
        MyApplication.getApplication().addActivity(TouziRengouMore2Activity.this);
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("支付确认");
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
                TouziRengouMore2Activity.this.finish();
            }
        });
    }

    /**
     * 写入信息
     */
    @BindView(R.id.sanjiqueren_objname)
    TextView tv_objname;
    @BindView(R.id.sanjiqueren_zongjine)
    TextView tv_zongjine;
    @BindView(R.id.sanjiqueren_fenshu)
    TextView tv_fenshu;
    @BindView(R.id.sanjiqueren_shifujine)
    TextView tv_shifujine;
    @BindView(R.id.sanjiqueren_tijiao)
    Button tv_tijiao;
    @BindView(R.id.sanjiqueren_fangan)
    TextView tv_fangan;
    String objname,fenshu,danjia;
    private void setinfo(){
        objname = getIntent().getStringExtra("sanjiobjname");
        fenshu = getIntent().getStringExtra("sanjifenshu");
        danjia = getIntent().getStringExtra("sanjidanjia");
        tv_objname.setText(Names);
        tv_fangan.setText(objname);
        tv_zongjine.setText((Integer.parseInt(danjia)*Integer.parseInt(fenshu))+"元");
        tv_fenshu.setText(fenshu+"份");
        tv_shifujine.setText((Integer.parseInt(danjia)*Integer.parseInt(fenshu))+"元");

        sharedPreferencesHelper.put("ordermoney",(Integer.parseInt(danjia)*Integer.parseInt(fenshu))+"");
        //确认购买
        tv_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.post(Api.payqueren)
                        .tag(this)
                        .params("itemsNumber",zhinumber)
                        .params("itemsID",zhiid)
                        .params("uuid",Userid)
                        .params("butNum",fenshu)
                        .params("money",(Integer.parseInt(danjia)*Integer.parseInt(fenshu)))
                        .params("schemeId",sharedPreferencesHelper.getSharedPreference("fanganid","").toString())
                        .params("voucherId",sharedPreferencesHelper.getSharedPreference("addressid","").toString())
                        .params("addressId",sharedPreferencesHelper.getSharedPreference("youhuiid","").toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                if (UiUtils.isFastClick()){
//                                    Log.e("确认购买",s);
                                    Gson gson = new Gson();
                                    TouziOkPayBean okPayBean = gson.fromJson(s,TouziOkPayBean.class);
                                    if (okPayBean.getState()==1){
//                                        ToastUtils.longToast("订单创建成功！");
                                         Intent intent = new Intent(TouziRengouMore2Activity.this,TouziOrderPayOkActivity.class);
                                         intent.putExtra("money",(Integer.parseInt(danjia)*Integer.parseInt(fenshu))+"");
                                         startActivity(intent);
                                    }
                                }

                            }
                        });
            }
        });

    }

    private static final String TAG = "TouziRengouMore2Activit";
    @BindView(R.id.rengou_more_daijinquan)
    TextView tv_daijinquan;
    @BindView(R.id.rengou_more_linearyout_daijinquan)
    LinearLayout ll_daijinquan;
    @Override
    protected void onResume() {
        super.onResume();
        //点击代金券
        ll_daijinquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TouziRengouMore2Activity.this,CouponActivity2.class);
                intent.putExtra("numbers",zhinumber);
                startActivity(intent);
            }
        });
        Log.e(TAG, "onResume:优惠di "+sharedPreferencesHelper.getSharedPreference("youhuiid","").toString() );
        //显示优惠券信息
        if (!TextUtils.isEmpty(sharedPreferencesHelper.getSharedPreference("youhuiid","").toString())){
            tv_daijinquan.setText(""+sharedPreferencesHelper.getSharedPreference("youhuimoney","").toString());
            tv_zongjine.setText(((Integer.parseInt(danjia)*Integer.parseInt(fenshu))-Integer.parseInt(sharedPreferencesHelper.getSharedPreference("youhuimoney","").toString()))+"元");
            tv_shifujine.setText(((Integer.parseInt(danjia)*Integer.parseInt(fenshu))-Integer.parseInt(sharedPreferencesHelper.getSharedPreference("youhuimoney","").toString()))+"元");
        }else {
            tv_daijinquan.setText("");
        }
    }
}
