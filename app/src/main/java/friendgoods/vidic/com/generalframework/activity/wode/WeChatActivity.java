package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_wechat;

/**
 * 绑定微信
 */
public class WeChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setbinwechat();
        edt_eit.setText(user_wechat);
        edt_eit.setSelection(edt_eit.getText().length());
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("绑定微信");
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
                WeChatActivity.this.finish();
            }
        });
    }
    /**
     * 绑定微信操作
     */
    @BindView(R.id.wechat_bind)
    TextView tv_bind;
    @BindView(R.id.wechat_eit)
    EditText edt_eit;
    private void setbinwechat(){
        tv_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UiUtils.isFastClick2()){
                    if (TextUtils.isEmpty(edt_eit.getText().toString())){
                        ToastUtils.shortToast("请输入微信号");
                    }else {
                        OkGo.post(Api.wechatupdate)
                                .tag(this)
                                .params("uuid",Userid)
                                .params("weChat",edt_eit.getText().toString())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Gson gson = new Gson();
                                        WechatBindBean wechatbean = gson.fromJson(s,WechatBindBean.class);
                                        if(wechatbean.getState()==0){
                                            //失败
                                            ToastUtils.shortToast(wechatbean.getMessage());
                                        }else{
                                            //成功后弹出提示，然后关闭
                                            ToastUtils.shortToast(wechatbean.getMessage());
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    WeChatActivity.this.finish();
                                                }
                                            },2000);
                                        }
                                    }
                                });
                    }
                }else {
                    ToastUtils.shortToast("短时间内请勿重复提交");
                }

            }
        });
    }
}
