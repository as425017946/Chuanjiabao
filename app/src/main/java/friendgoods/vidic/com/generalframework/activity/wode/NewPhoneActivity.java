package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_phone;

public class NewPhoneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_phone);
        ButterKnife.bind(this);
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
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("更换手机号");
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
                Intent intent = new Intent(NewPhoneActivity.this,PhoneActivity.class);
                startActivity(intent);
                NewPhoneActivity.this.finish();
            }
        });
    }

    /**
     * 绑定手机号
     */
    @BindView(R.id.newphone_phone)
    EditText edt_phone;
    @BindView(R.id.newphone_yzm)
    EditText edt_yzm;
    @BindView(R.id.newphone_tijiao)
    Button btn_tijiao;
    @BindView(R.id.newphone_yzmbtn)
    TextView tv_yzmbtn;
    private void setinfo(){

        //获取验证码
        tv_yzmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_phone.getText().toString())){
                    ToastUtils.shortToast("请输入手机号");
                }else{
                    if(UiUtils.isCellphone(edt_phone.getText().toString())==false){
                        ToastUtils.shortToast("输入的手机号不正确");
                    }else{
                        timer.start();
                        if(tv_yzmbtn.getText().equals("获取验证码") || tv_yzmbtn.getText().equals("重新获取")){
                            OkGo.post(Api.sms)
                                    .tag(this)
                                    .params("phone",user_phone)
                                    .params("flag",1)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            Log.e("SMS",s);
                                        }
                                    });
                        }
                    }

                }

            }
        });
        //提交
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_yzm.getText().toString())){
                    ToastUtils.shortToast("请输入验证码");
                }else{
                    OkGo.post(Api.phoneupdate)
                            .tag(this)
                            .params("phone",user_phone)
                            .params("id",Userid)
                            .params("code",edt_yzm.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Gson gson = new Gson();
                                    WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                                    if (wechatBindBean.getState()==1){

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                NewPhoneActivity.this.finish();
                                                Intent intent = new Intent(NewPhoneActivity.this,NewPhoneActivity.class);
                                                startActivity(intent);
                                            }
                                        },2000);
                                        ToastUtils.longToast(wechatBindBean.getMessage());
                                    }else{
                                        ToastUtils.shortToast("该号码已存在");
                                    }
                                }
                            });
                }
            }
        });
    }
    /**
     * 线程读秒
     */
    private CountDownTimer timer = new CountDownTimer(120000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tv_yzmbtn.setText("倒计时("+(millisUntilFinished / 1000)+")");
            tv_yzmbtn.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tv_yzmbtn.setEnabled(true);
            tv_yzmbtn.setText("再次获取");
        }
    };
}
