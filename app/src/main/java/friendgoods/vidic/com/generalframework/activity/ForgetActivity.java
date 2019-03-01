package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import friendgoods.vidic.com.generalframework.Bean.ForgetpasswordBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ForgetActivity extends BaseActivity{

    private LinearLayout userNameLl;
    private LinearLayout userPasswordLl;
    private ImageView loginIv;
    private EditText userName;
    private EditText userPassword;
    private String[] items;
    private LinearLayout avLoadingIndicatorView;
    private TextView appraisalEvaluation;
    private Button btnYzm;
    //导入封装好的sharepreference
    private SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setChongzhi();
        sharehelper = new SharedPreferencesHelper(
                ForgetActivity.this, "chuanjiabao");
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("忘记密码");
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
                Intent intent = new Intent(ForgetActivity.this,PasswordActivity.class);
                startActivity(intent);
                ForgetActivity.this.finish();
            }
        });
    }

    /**
     * 重置密码
     */
    @BindView(R.id.btn_chognzhi)
    Button btn_chongzhi;
    @BindView(R.id.et_forget_phone)
    EditText et_phone;
    @BindView(R.id.et_forget_yzm)
    EditText et_yzm;
    @BindView(R.id.et_forget_newpassword)
    EditText et_newpassword;
    @BindView(R.id.et_forget_queren)
    EditText et_qurenpasswoed;
    @BindView(R.id.btn_forget_yzm)
    Button btn_yzm;
    private void setChongzhi(){
        btn_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_phone.getText().toString())){
                    ToastUtils.shortToast("请输入手机号!");
                }else if(TextUtils.isEmpty(et_yzm.getText().toString())){
                    ToastUtils.shortToast("请输入验证码!");
                }else if(TextUtils.isEmpty(et_newpassword.getText().toString())){
                    ToastUtils.shortToast("请输入新密码!");
                }else if(TextUtils.isEmpty(et_qurenpasswoed.getText().toString())){
                    ToastUtils.shortToast("请输入确认密码!");
                }else{
                    if (et_newpassword.getText().toString().length()<8 || et_newpassword.getText().toString().length()>20){
                       ToastUtils.shortToast("输入的新密码位数不正确！");
                    }else if(et_qurenpasswoed.getText().toString().length()<8 || et_qurenpasswoed.getText().toString().length()>20){
                        ToastUtils.shortToast("输入的确认密码位数不正确!");
                    }else{
                        //请求重置密码的接口
                        if (et_newpassword.getText().toString().equals(et_qurenpasswoed.getText().toString())){
                            //请求登录接口
                            OkGo.post(Api.forgetpassword)
                                    .tag(this)
                                    .params("phone",et_phone.getText().toString())
                                    .params("newPassWord",et_newpassword.getText().toString())
                                    .params("passWord",et_qurenpasswoed.getText().toString())
                                    .params("code",et_yzm.getText().toString())
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            Log.e("登录测试忘记密码",s);
                                            Gson gson = new Gson();
                                            ForgetpasswordBean fbean = gson.fromJson(s,ForgetpasswordBean.class);
                                            if (fbean.getState().equals("0")){
                                                ToastUtils.shortToast(fbean.getMessages());
                                            }else {
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent intent = new Intent(ForgetActivity.this,PasswordActivity.class);
                                                        startActivity(intent);
                                                        ForgetActivity.this.finish();
                                                    }
                                                }, 2000);
                                            }
                                        }
                                    });

                        }else{
                            ToastUtils.shortToast("2次输入的密码不一致!");
                        }
                    }
                }
            }
        });

        //获取验证码
        btn_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(UiUtils.isCellphone(et_phone.getText().toString())==false){
                    ToastUtils.shortToast("输入的手机号有误!");
                }else {
                    timer.start();
                    if(btn_yzm.getText().equals("获取验证码") || btn_yzm.getText().equals("重新获取")){
                        OkGo.post(Api.sms)
                                .tag(this)
                                .params("phone",et_phone.getText().toString())
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
        });

    }
    /**
     * 线程读秒
     */
    private CountDownTimer timer = new CountDownTimer(120000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            btn_yzm.setText("倒计时("+(millisUntilFinished / 1000)+")");
            btn_yzm.setEnabled(false);
        }

        @Override
        public void onFinish() {
            btn_yzm.setEnabled(true);
            btn_yzm.setText("再次获取");
        }
    };

}