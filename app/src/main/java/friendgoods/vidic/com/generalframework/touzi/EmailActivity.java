package friendgoods.vidic.com.generalframework.touzi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
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
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_email;

/**
 * 绑定邮箱
 */
public class EmailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
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
    private void setTtitle(){
        ttitle.setText("绑定邮箱");
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
                EmailActivity.this.finish();
            }
        });
    }

    /**
     * 绑定邮箱
     */
    @BindView(R.id.email_edt)
    EditText edt_email;
    @BindView(R.id.email_btn)
    Button btn_email;
    private void setinfo(){
        if (!TextUtils.isEmpty(user_email) || user_email!="null"){
//            edt_email.setText(user_email.substring(0,3)+"****"+user_email.substring(user_email.length()-5,user_email.length()));
            edt_email.setText(user_email);
            edt_email.setSelection(edt_email.getText().length());
        }
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_email.getText().toString())){
                    ToastUtils.shortToast("请输入邮箱");
                }else{
                    if (UiUtils.isEmail(edt_email.getText().toString())==true){
                        OkGo.post(Api.emailupdate)
                                .tag(this)
                                .params("uuid",Userid)
                                .params("email",edt_email.getText().toString())
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
                                                    EmailActivity.this.finish();
                                                }
                                            },2000);
                                            ToastUtils.longToast("绑定成功");
                                        }else {
                                            ToastUtils.shortToast(wechatBindBean.getMessage());
                                        }
                                    }
                                });
                    }else {
                        ToastUtils.shortToast("请输入正确的邮箱地址");
                    }

                }

            }
        });
    }
}
