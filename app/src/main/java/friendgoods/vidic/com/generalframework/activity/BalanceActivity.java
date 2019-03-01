package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.BalanceBean;
import friendgoods.vidic.com.generalframework.Bean.ShowyuejifenBean;
import friendgoods.vidic.com.generalframework.Bean.UserInfoBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.BalanceSettingActivity;
import friendgoods.vidic.com.generalframework.activity.wode.ChongzhiActivity;
import friendgoods.vidic.com.generalframework.activity.wode.MingxiMoreActivity;
import friendgoods.vidic.com.generalframework.activity.wode.TixianActivity;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.MyDialog;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.balance;

/**
 * 账户余额
 */
public class BalanceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        addcard();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.mine_setting2)
    ImageView img2;
    private void setTtitle(){
        ttitle.setText("账户余额");
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BalanceActivity.this,BalanceSettingActivity.class);
                startActivity(intent);
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
                BalanceActivity.this.finish();
            }
        });
    }
    /**
     * 显示余额
     * 明细
     * 充值
     * 提现
     * 添加分红卡
     */
    @BindView(R.id.yue_yueshow)
    TextView tv_yue;
    @BindView(R.id.yue_mingxi)
    TextView tv_mingxi;
    @BindView(R.id.yue_chongzhi)
    TextView tv_chongzhi;
    @BindView(R.id.yue_tixian)
    TextView tv_tixian;
    @BindView(R.id.yue_moren)
    ImageView img_more;
    @BindView(R.id.yue_shwo)
    LinearLayout btn_show;
    @BindView(R.id.balance_baname)
    TextView tv_balacename;
    @BindView(R.id.balance_card)
    TextView tv_balacecard;
    @BindView(R.id.balance_delete)
    TextView tv_delete;
    String cardid;
    public static String kahao="";
    private void addcard(){

        //删除绑定银行卡
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.show(BalanceActivity.this, "是否删除绑定？", new MyDialog.OnConfirmListener() {
                    @Override
                    public void onConfirmClick() {
                        OkGo.post(Api.deletefenhongcard)
                                .tag(this)
                                .params("id",Userid)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Gson gson = new Gson();
                                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                                        if (wx.getState()==1){
                                            ToastUtils.shortToast("分红卡已成功删除");
                                            img_more.setVisibility(View.VISIBLE);
                                            btn_show.setVisibility(View.GONE);
                                        }else{
                                            ToastUtils.shortToast(wx.getMessage());
                                        }
                                    }
                                });
                    }
                });



            }
        });



        tv_mingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(kahao)){
                    ToastUtils.shortToast("请先绑定银行卡");
                }else{
                    Intent intent = new Intent(BalanceActivity.this,MingxiMoreActivity.class);
                    startActivity(intent);
                }

            }
        });
        tv_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(kahao)){
                    ToastUtils.shortToast("请先绑定银行卡");
                }else{
                    Intent intent = new Intent(BalanceActivity.this,ChongzhiActivity.class);
                    startActivity(intent);
                }

            }
        });
        tv_tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(kahao)){
                    ToastUtils.shortToast("请先绑定银行卡");
                }else{
                    Intent intent = new Intent(BalanceActivity.this,TixianActivity.class);
                    startActivity(intent);
                }

            }
        });
        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BalanceActivity.this,VisaActivity.class);
                startActivity(intent);
            }
        });
    }

    @BindView(R.id.balance_tishi)
    TextView tv_tishi;
    @Override
    protected void onResume() {
        super.onResume();
        getyue();
        //查询是否绑定了银行卡
        OkGo.post(Api.showfenhongcard)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("分红卡",s);
                        Gson gson = new Gson();
                        BalanceBean balance = gson.fromJson(s,BalanceBean.class);
                        if (balance.getState()==1){
                            tv_tishi.setVisibility(View.GONE);
                            img_more.setVisibility(View.GONE);
                            btn_show.setVisibility(View.VISIBLE);
                            tv_balacename.setText(balance.getData().getBank_name());
                            tv_balacecard.setText(balance.getData().getBank_card_number().substring(0,4)+"****"+balance.getData().getBank_card_number().substring(balance.getData().getBank_card_number().length()-4,balance.getData().getBank_card_number().length()));
                            kahao= balance.getData().getBank_card_number();

                        }else{
                            tv_tishi.setVisibility(View.VISIBLE);
                            img_more.setVisibility(View.VISIBLE);
                            btn_show.setVisibility(View.GONE);
//                            ToastUtils.shortToast("尚未绑定银行卡");
                        }
                    }
                });

    }

    //展示账户余额和是否绑定了银行卡
    private void getyue(){

        OkGo.post(Api.showyue)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("账户显示",s);
                        Gson gson = new Gson();
                        ShowyuejifenBean showyuejifenBean = gson.fromJson(s,ShowyuejifenBean.class);
                        if (showyuejifenBean.getState()==1){
                            if (showyuejifenBean.getData()!=null){
                                //显示余额，积分
                                if (TextUtils.isEmpty(showyuejifenBean.getData().getAccount_balance())){
                                    tv_yue.setText("0.0元");
                                }else {
                                    tv_yue.setText(Integer.parseInt(showyuejifenBean.getData().getAccount_balance())+"元");
                                }

                            }
                        }
                    }
                });


    }

}