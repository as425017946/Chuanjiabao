package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_phone;

/**
 * 绑定手机页面
 */
public class PhoneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
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
        if (TextUtils.isEmpty(user_phone)){
            ttitle.setText("绑定手机");
        }else {
            ttitle.setText("换绑手机");
        }
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
                PhoneActivity.this.finish();
            }
        });
    }
    /**
     * 绑定手机号
     */
    @BindView(R.id.phone_phone)
    EditText edt_phone;
    @BindView(R.id.phone_yzm)
    EditText edt_yzm;
    @BindView(R.id.phone_tijiao)
    Button btn_tijiao;
    @BindView(R.id.phone_yzmbtn)
    TextView tv_yzmbtn;
    private void setinfo(){
        if (TextUtils.isEmpty(user_phone)){

        }else {
            btn_tijiao.setText("更换手机号");
            edt_phone.setEnabled(false);
            edt_phone.setText(user_phone.substring(0,3)+"****"+user_phone.substring((user_phone.length()-4),user_phone.length()));

        }
        //获取验证码
       tv_yzmbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               timer.start();
               if(tv_yzmbtn.getText().equals("获取验证码") || tv_yzmbtn.getText().equals("重新获取")){
                   OkGo.post(Api.sms)
                           .tag(this)
                           .params("phone",user_phone)
                           .params("flag",1)
                           .params("status",6)
                           .execute(new StringCallback() {
                               @Override
                               public void onSuccess(String s, Call call, Response response) {
                                   Log.e("SMS",s);
                               }
                           });
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
                    Log.e("验证码",edt_yzm.getText().toString());
                    OkGo.post(Api.phoneupdate2)
                            .tag(this)
                            .params("phone",user_phone)
                            .params("uuid",Userid)
                            .params("code",edt_yzm.getText().toString())
                            .params("status",6)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Log.e("输出结果",s);
                                    Gson gson = new Gson();
                                    WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                                    if (wechatBindBean.getState()==1){
//                                        ToastUtils.shortToast(wechatBindBean.getMessage());
                                        Intent intent  = new Intent(PhoneActivity.this,Phone2Activity.class);
                                        startActivity(intent);
                                        PhoneActivity.this.finish();

                                    }else{
                                        ToastUtils.shortToast(wechatBindBean.getMessage());
//                                        if (wechatBindBean.getMessage().equals("该用户已存在")){
//                                            PhoneActivity.this.finish();
//                                            Intent intent = new Intent(PhoneActivity.this,NewPhoneActivity.class);
//                                            startActivity(intent);
//                                        }

                                    }
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.shortToast(e+"");
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
