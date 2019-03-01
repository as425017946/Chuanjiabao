package friendgoods.vidic.com.generalframework.activity.wode;

import android.os.CountDownTimer;
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

import org.gradle.util.TextUtil;

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
 * 管理支付密码
 */
public class GuanlizhifumimaActivity extends AppCompatActivity {
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanlizhifumima);
        ButterKnife.bind(this);
        setTtitle();
        sharehelper = new SharedPreferencesHelper(GuanlizhifumimaActivity.this,"chuanjiabao");
        fanhui();
        getinfo();
    }

    /**
     * 信息上传
     */
    @BindView(R.id.zhifu_tijiao)
    Button btn_tijiao;
    @BindView(R.id.zhifu_inputsms)
    EditText edt_sms;
    @BindView(R.id.zhifu_sms)
    TextView tv_huoqusms;
    @BindView(R.id.zhifu_newpassword)
    EditText edt_newpassword;
    @BindView(R.id.zhifu_querenpassword)
    EditText edt_querenpassword;
    private void getinfo() {
        getsms();
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UiUtils.isFastClick()){
                    if (TextUtils.isEmpty(edt_sms.getText().toString())){
                        ToastUtils.shortToast("请输入验证码");
                    }else {
                        if (TextUtils.isEmpty(edt_newpassword.getText().toString())){
                            ToastUtils.shortToast("请输入支付密码");
                        }else {
                            if (TextUtils.isEmpty(edt_querenpassword.getText().toString())){
                                ToastUtils.shortToast("请输入确认支付密码");
                            }else {
                                if (!edt_newpassword.getText().toString().equals(edt_querenpassword.getText().toString())){
                                    ToastUtils.shortToast("俩次输入的支付密码不一致");
                                }else {
                                    upinfo();
                                }
                            }
                        }
                    }
                }else {
                    ToastUtils.shortToast("请勿短时间内重复提交");
                }

            }
        });
    }
    /**
     * 上传服务器
     */
    private void upinfo(){
        OkGo.post(Api.updatezhifu)
                .tag(this)
                .params("parameterPassWord",edt_newpassword.getText().toString())
                .params("newParameterPassWord",edt_querenpassword.getText().toString())
                .params("code",edt_sms.getText().toString())
                .params("state",9)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                        if (wechatBindBean.getState()==1){
                            ToastUtils.shortToast("修改成功");
                            GuanlizhifumimaActivity.this.finish();
                        }else {
                            ToastUtils.shortToast(wechatBindBean.getMessage());
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
        ttitle.setText("修改支付密码");
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
                GuanlizhifumimaActivity.this.finish();
            }
        });
    }

}
