package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.MyDialog;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 建议反馈
 */
public class FeedbackActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        fanhui();
        setTtitle();
        setinfo();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("意见反馈");
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
                FeedbackActivity.this.finish();
            }
        });
    }

    /**
     * 写入信息
     */
    @BindView(R.id.feedback_yijian)
    EditText edt_jianyi;
    @BindView(R.id.feedback_tijiao)
    TextView tv_tijiao;
    private void setinfo(){
        tv_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_jianyi.getText().toString())){
                    ToastUtils.shortToast("请输入信息");
                }else{
                    MyDialog.show(FeedbackActivity.this, "是否提交建议？", new MyDialog.OnConfirmListener() {
                        @Override
                        public void onConfirmClick() {
                            OkGo.post(Api.jianyi)
                                    .tag(this)
                                    .params("uuid",Userid)
                                    .params("userIdea",edt_jianyi.getText().toString())
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            Gson gson = new Gson();
                                            WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                                            if (wx.getState()==1){
                                                ToastUtils.shortToast("信息提交成功！");
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        FeedbackActivity.this.finish();
                                                    }
                                                },1000);
                                            }
                                        }
                                    });
                        }
                    });


                }
            }
        });
    }
}