package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.os.Handler;
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
import friendgoods.vidic.com.generalframework.Bean.BalanceBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.VisaActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.widget.PayPasswordDialog;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 解绑分红卡
 */
public class GuanliCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanli_card);
        ButterKnife.bind(this);
        setinfo();
        setTtitle();
        fanhui();
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("分红卡");
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
                GuanliCardActivity.this.finish();
            }
        });
    }
    @BindView(R.id.yue_shwo)
    LinearLayout btn_show;
    @BindView(R.id.balance_baname)
    TextView tv_balacename;
    @BindView(R.id.balance_card)
    TextView tv_balacecard;
    @BindView(R.id.balance_delete)
    TextView tv_delete;
    @BindView(R.id.jiechubangding)
    Button btn_jiechu;
    @BindView(R.id.yue_moren)
    ImageView img_no;
    private void setinfo() {
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
                            btn_show.setVisibility(View.VISIBLE);
                            tv_balacename.setText(balance.getData().getBank_name());
                            tv_balacecard.setText(balance.getData().getBank_card_number().substring(0,4)+"****"+balance.getData().getBank_card_number().substring(balance.getData().getBank_card_number().length()-4,balance.getData().getBank_card_number().length()));
                            btn_jiechu.setVisibility(View.VISIBLE);
                            img_no.setVisibility(View.GONE);
                        }else{
                            img_no.setVisibility(View.VISIBLE);
                            btn_show.setVisibility(View.GONE);
                            btn_jiechu.setVisibility(View.GONE);
//                            ToastUtils.shortToast("尚未绑定银行卡");
                        }
                    }
                });
        /**
         * 解除绑定
         */
        btn_jiechu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PayPasswordDialog dialog=new PayPasswordDialog(GuanliCardActivity.this,R.style.mydialog);
                dialog.setDialogClick(new PayPasswordDialog.DialogClick() {
                    @Override
                    public void doConfirm(String password) {
                        dialog.dismiss();
//                                    Toast.makeText(TixianActivity.this,password,Toast.LENGTH_LONG).show();
                        jiebang(password);
                    }
                });
                dialog.show();
            }
        });
        //没有绑定跳转
        img_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuanliCardActivity.this,VisaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jiebang(String password) {
        OkGo.post(Api.jiebang)
                .tag(this)
                .params("paymentPassword",password)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                        if (wechatBindBean.getState()==1){
                            ToastUtils.shortToast("解绑成功");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    GuanliCardActivity.this.finish();
                                }
                            },1000);
                        }else {
                            ToastUtils.shortToast(wechatBindBean.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setinfo();
    }
}
