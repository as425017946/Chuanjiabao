package friendgoods.vidic.com.generalframework.activity.wode;

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
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BaseActivity;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.BalanceActivity.kahao;

public class ChongzhiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
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
        ttitle.setText("余额充值");
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
                ChongzhiActivity.this.finish();
            }
        });
    }

    /**
     * 绑定数据
     */
    @BindView(R.id.chongzhi_jine)
    EditText edt_jine;
    @BindView(R.id.chongzhi_tijiao)
    Button btn_tijiao;
    private void setinfo(){
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_jine.getText())){
                    ToastUtils.shortToast("请输入要充值的金额");
                }else{
                    OkGo.post(Api.chongzhi)
                            .tag(this)
                            .params("id",Userid)
                            .params("money",edt_jine.getText().toString())
                            .params("number",kahao)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Gson gson = new Gson();
                                    WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                                    if (wx.getState()==1){
                                        ToastUtils.shortToast("充值成功");
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                ChongzhiActivity.this.finish();
                                            }
                                        },2000);
                                    }else {
                                        ToastUtils.shortToast(wx.getMessage());
                                    }
                                }
                            });
                }
            }
        });
    }
}
