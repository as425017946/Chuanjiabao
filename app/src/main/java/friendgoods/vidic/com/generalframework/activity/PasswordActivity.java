package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
public class PasswordActivity extends BaseActivity implements View.OnClickListener {

    private TextView loginCode,passwordForget;

    //导入封装好的sharepreference
    private SharedPreferencesHelper sharehelper;
    @BindView(R.id.zhucexieyi2)
    TextView tv_zhucexieyi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        loginCode = findViewById(R.id.login_code);
        passwordForget = findViewById(R.id.password_forget);
        loginCode.setOnClickListener(this);
        passwordForget.setOnClickListener(this);
        setLogin();
        sharehelper = new SharedPreferencesHelper(
                PasswordActivity.this, "chuanjiabao");
        tv_zhucexieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(PasswordActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PasswordActivity.this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_code:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                PasswordActivity.this.finish();
                break;
            case R.id.password_forget:
                Intent intent2 = new Intent(this,ForgetActivity.class);
                startActivity(intent2);
                PasswordActivity.this.finish();
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
        ttitle.setText("账号密码登录");
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
                Intent intent = new Intent(PasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                PasswordActivity.this.finish();
            }
        });
    }

    /**
     * 登录/注册验证
     */
    @BindView(R.id.et_login_phone)
    EditText et_phone;
    @BindView(R.id.et_login_password)
    EditText et_password;
    @BindView(R.id.b_login2)
    Button b_login2;
    private void setLogin(){
        b_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(et_phone.getText().toString())){
                    ToastUtils.shortToast("请输入手机号");
                }else if (TextUtils.isEmpty(et_password.getText().toString())){
                    ToastUtils.shortToast("请输入密码");
                }else{

                    if(UiUtils.isCellphone(et_phone.getText().toString())==false){
                        ToastUtils.shortToast("输入的手机号有误!");
                    }else{
                        if(et_password.getText().toString().length()<8 || et_password.getText().toString().length()>20){
                            ToastUtils.shortToast("输入的密码位数不正确!");
                        }else{
                            //登录请求接口
                            OkGo.post(Api.login)
                                    .tag(this)
                                    .params("phone",et_phone.getText().toString())
                                    .params("passWord",et_password.getText().toString())
                                    .params("registrationId",registrationID)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Response response, Exception e) {
                                            super.onError(call, response, e);
                                            ToastUtils.shortToast("数据信息异常");
                                        }
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            Log.e("登录",s);
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
//                                                sharehelper.put("back","3");
                                                back = "3";
                                                sharehelper.put("flag3",loginBean.getData().getFlag3());
                                                sharehelper.put("flag5",loginBean.getData().getFlag5());
                                                LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(PasswordActivity.this)
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
                                                        PasswordActivity.this.finish();
                                                    }
                                                }, 2000);


                                            }
                                        }
                                    });

                        }
                    }

                }

            }
        });
    }

}