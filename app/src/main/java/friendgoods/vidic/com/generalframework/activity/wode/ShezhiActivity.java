package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.SpleashActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 其他设置
 */
public class ShezhiActivity extends BaseActivity {
    String shezhi="0";
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        ButterKnife.bind(this);
        sharehelper = new SharedPreferencesHelper(ShezhiActivity.this,"chuanjiabao");
        setTtitle();
        fanhui();
        Setclick();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("其他设置");
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
                ShezhiActivity.this.finish();
            }
        });
    }
    /**
     * 设置密码
     * 关于
     */
    @BindView(R.id.wode_mimaguanli)
    LinearLayout lmima;
    @BindView(R.id.wode_guanyu)
    LinearLayout lguanyu;
    @BindView(R.id.st_switch)
    Switch aSwitch;
    private void Setclick(){
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shezhi = "1";
                if (b){
                    if (UiUtils.isNotificationEnabled(ShezhiActivity.this)==true){
//                        ToastUtils.shortToast("已经开启了");
                    }else {
                        UiUtils.gotoSet(ShezhiActivity.this);
                    }
                }else {
                    UiUtils.gotoSet(ShezhiActivity.this);
                }
            }
        });
        lmima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //0未设置  1 设置了密码
                if (sharehelper.getSharedPreference("flag3","").toString().equals("0")){
                    Intent intent = new Intent(ShezhiActivity.this,ShezhimimaActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ShezhiActivity.this,ShezhiMima2Activity.class);
                    startActivity(intent);
                }

            }
        });
        lguanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShezhiActivity.this, GuanyuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shezhi.equals("1")){
            if (UiUtils.isNotificationEnabled(ShezhiActivity.this)==true){
//                        ToastUtils.shortToast("已经开启了");
                aSwitch.setChecked(true);
                kaiguanbi(0);
            }else {
                aSwitch.setChecked(false);
                kaiguanbi(1);
            }
        }

    }
    /**
     * 开始关闭
     */
    private void kaiguanbi(final int zhi){
        OkGo.post(Api.kaiOrguanbi)
                .tag(this)
                .params("uuid",Userid)
                .params("flag4",zhi)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                        if (wechatBindBean.getState()==1){
//                            if(zhi==0){
//                                ToastUtils.shortToast("您已开启消息推送功能");
//                            }else {
//                                ToastUtils.shortToast("您已关闭消息推送功能");
//                            }
                        }else {
                            ToastUtils.shortToast(wechatBindBean.getMessage());
                        }
                    }
                });
    }
}
