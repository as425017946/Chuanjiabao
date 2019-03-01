package friendgoods.vidic.com.generalframework.activity.wode;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 有密码跟换密码
 */
public class ShezhiMima2Activity extends AppCompatActivity {
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi_mima2);
        ButterKnife.bind(this);
        setTtitle();
        sharehelper = new SharedPreferencesHelper(ShezhiMima2Activity.this,"chuanjiabao");
        fanhui();
        setinfo();
    }

    /**
     * 展示信息
     */
    @BindView(R.id.shezhi_bangdingshouji)
    TextView tv_phone;
    @BindView(R.id.shezhi_inputsms)
    EditText edt_inputsms;
    @BindView(R.id.shezhi_sms)
    TextView tv_huoqusms;
    @BindView(R.id.shezhi_newpassword)
    EditText edt_newpassword;
    @BindView(R.id.shezhi_querenpassword)
    EditText edt_queren;
    @BindView(R.id.shezhi_tijiao)
    Button btn_tijiao;
    private void setinfo() {
        //展示手机号
        tv_phone.setText(sharehelper.getSharedPreference("userphone","").toString().substring(0,3)+"***"+sharehelper.getSharedPreference("userphone","").toString().substring(sharehelper.getSharedPreference("userphone","").toString().length()-4,sharehelper.getSharedPreference("userphone","").toString().length()));
        getsms();
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UiUtils.isFastClick()){
                    if (TextUtils.isEmpty(edt_inputsms.getText().toString())){
                        ToastUtils.shortToast("请输入验证码");
                    }else {
                        if (TextUtils.isEmpty(edt_newpassword.getText().toString())){
                            ToastUtils.shortToast("请输入密码");
                        }else if(TextUtils.isEmpty(edt_queren.getText().toString())){
                            ToastUtils.shortToast("请输入确认密码");
                        }else {
                            if (edt_newpassword.getText().length()<6 || edt_newpassword.getText().length()>20){
                                ToastUtils.shortToast("输入的密码过短或过长");
                            }else if(edt_queren.getText().length()<6 || edt_queren.getText().length()>20){
                                ToastUtils.shortToast("输入的确认密码过短或过长");
                            }else if(!edt_queren.getText().toString().equals(edt_newpassword.getText().toString())){
                                ToastUtils.shortToast("2次输入的密码不一致");
                            }else {
                                OkGo.post(Api.updatepassword)
                                        .tag(this)
                                        .params("uuid",Userid)
                                        .params("passWord",edt_newpassword.getText().toString())
                                        .params("newPassWord",edt_queren.getText().toString())
                                        .params("status",9)
                                        .params("code",edt_inputsms.getText().toString())
                                        .params("phone",sharehelper.getSharedPreference("userphone","").toString())
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(String s, Call call, Response response) {
                                                Gson gson = new Gson();
                                                WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                                                if (wx.getState()==1){
                                                    Handler handler = new Handler();
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            ShezhiMima2Activity.this.finish();
                                                        }
                                                    },2000);
                                                    ToastUtils.longToast("密码重置成功");
                                                }else {
                                                    ToastUtils.shortToast(wx.getMessage());
                                                }
                                            }
                                        });
                            }
                        }
                    }
                }else {
                    ToastUtils.shortToast("短时间内请勿重复提交");
                }
            }
        });

    }

    /**
     * 获取验证码
     */
    private void getsms(){
        //获取验证码
        tv_huoqusms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(sharehelper.getSharedPreference("userphone","").toString())){
                    ToastUtils.shortToast("请输入手机号!");
                }else{
                    if(UiUtils.isCellphone(sharehelper.getSharedPreference("userphone","").toString())==false){
                        ToastUtils.shortToast("输入的手机号有误!");
                    }else {
                        //网络请求获取验证码;
//                        timer.schedule(task,time,1000);

//                        handler.postDelayed(runnable, 1000);
                        timer.start();
                        if(tv_huoqusms.getText().equals("获取验证码") || tv_huoqusms.getText().equals("重新获取")){
                            OkGo.post(Api.sms)
                                    .tag(this)
                                    .params("phone",sharehelper.getSharedPreference("userphone","").toString())
                                    .params("flag",1)
                                    .params("status",9)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Response response, Exception e) {
                                            super.onError(call, response, e);
                                            ToastUtils.shortToast("短信发送失败");
                                        }
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            Log.e("短信SMS",s);
                                        }
                                    });
                        }

                    }
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
            tv_huoqusms.setText("倒计时("+(millisUntilFinished / 1000)+")");
            tv_huoqusms.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tv_huoqusms.setEnabled(true);
            tv_huoqusms.setText("再次获取");
        }
    };

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("设置密码");
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
                ShezhiMima2Activity.this.finish();
            }
        });
    }
}
