package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.LoginBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.back;
import static friendgoods.vidic.com.generalframework.MyApplication.registrationID;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView loginForget,loginPassword;
    //导入封装好的sharepreference
    private SharedPreferencesHelper sharehelper;
    @BindView(R.id.zhucexieyi)
    TextView tv_zhucexieyi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        Setlogin();
        sharehelper = new SharedPreferencesHelper(
                LoginActivity.this, "chuanjiabao");
        loginPassword = findViewById(R.id.login_password);
        loginPassword.setOnClickListener(this);
        tv_zhucexieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
                LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
                final View DialogView = inflater .inflate ( R.layout.zhucetishi, null);//1、自定义布局
                TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();
                //点击取消
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    /**
     * 注册/登录
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_password:
                Intent intent = new Intent(this,PasswordActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
                break;
        }
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("验证码登录");
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
                LoginActivity.this.finish();
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

    /**
     * 登录
     */
    @BindView(R.id.et_duanxin_phone)
    EditText et_phone;
    @BindView(R.id.et_duanxin_yzm)
    EditText et_yzm;
    @BindView(R.id.btn_login_1)
    Button bt_login;
    @BindView(R.id.btn_duanxin_yzm)
    Button btn_yzm;
    private void Setlogin(){
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_phone.getText().toString())){
                    ToastUtils.shortToast("请输入手机号!");
                }else if(TextUtils.isEmpty(et_yzm.getText().toString())){
                    ToastUtils.shortToast("请输入验证码!");
                }else{
                    if(UiUtils.isCellphone(et_phone.getText().toString())==false){
                        ToastUtils.shortToast("输入的手机号有误!");
                    }else {
                        OkGo.post(Api.phonesms)
                                .tag(this)
                                .params("phone",et_phone.getText().toString())
                                .params("code",et_yzm.getText().toString())
                                .params("status","4")
                                .params("registrationId",registrationID)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        ToastUtils.shortToast("数据信息异常");
                                    }
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Log.e("测试登录",s);
                                        Gson gson = new Gson();
                                        LoginBean loginBean = gson.fromJson(s,LoginBean.class);
                                        int state = loginBean.getState();
                                        //判断手机号和密码是否符合规则，符合直接跳转
                                        if(state==0){
                                            ToastUtils.shortToast(loginBean.getMessage());
                                        }else{
                                            //存入值
//                                                Log.e("登录页面",loginBean.getData().getUser_phone());
//                                                Log.e("登录页面2",loginBean.getData().getId()+"");
                                            sharehelper.put("userphone",loginBean.getData().getUser_phone().toString().trim());
                                            sharehelper.put("userid",loginBean.getData().getUuid().toString().trim());
//                                            sharehelper.put("back","3");
                                            back = "3";
                                            sharehelper.put("flag3",loginBean.getData().getFlag3());
                                            sharehelper.put("flag5",loginBean.getData().getFlag5());
                                            LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(LoginActivity.this)
                                                    .setMessage("登录成功...")
                                                    .setCancelable(false)
                                                    .setCancelOutside(false);
                                            final LoadingDailog dialog=loadBuilder.create();
                                            dialog.show();
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    dialog.dismiss();
                                                    LoginActivity.this.finish();
                                                }
                                            }, 2000);


                                        }
                                    }
                                });
                    }
                }

            }
        });
        //获取验证码
        btn_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_phone.getText().toString())){
                    ToastUtils.shortToast("请输入手机号!");
                }else{
                    if(UiUtils.isCellphone(et_phone.getText().toString())==false){
                        ToastUtils.shortToast("输入的手机号有误!");
                    }else {
                        //网络请求获取验证码;
//                        timer.schedule(task,time,1000);

//                        handler.postDelayed(runnable, 1000);
                        timer.start();
                        if(btn_yzm.getText().equals("获取验证码") || btn_yzm.getText().equals("重新获取")){
                            OkGo.post(Api.sms)
                                    .tag(this)
                                    .params("phone",et_phone.getText().toString())
                                    .params("flag",1)
                                    .params("status",4)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        timer.cancel();
    }
}