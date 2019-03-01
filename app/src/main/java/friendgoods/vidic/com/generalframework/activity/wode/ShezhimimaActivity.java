package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 设置密码
 */
public class ShezhimimaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhimima);
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
                ShezhimimaActivity.this.finish();
            }
        });
    }

    /**
     * 设置密码
     */
    @BindView(R.id.shezhi_newpassword)
    EditText edt_newpassword;
    @BindView(R.id.shezhi_querenpassword)
    EditText edt_queren;
    @BindView(R.id.shezhi_tijiao)
    Button btn_tijiao;
    private void setinfo(){
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                                    ShezhimimaActivity.this.finish();
                                                }
                                            },2000);
                                            ToastUtils.longToast("密码设置成功");
                                        }else {
                                            ToastUtils.shortToast(wx.getMessage());
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}
