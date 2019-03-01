package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 *风险测评起始页
 */
public class AppraisalActivity extends BaseActivity implements View.OnClickListener {

    private TextView appraisalEvaluation;
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraisa);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        sharehelper = new SharedPreferencesHelper(AppraisalActivity.this,"chuanjiabao");
        appraisalEvaluation = findViewById(R.id.appraisal_evaluation);
        appraisalEvaluation.setOnClickListener(this);

    }

    //请求信息后查看是否已经评测过
    private int pingce=0;
//    private void setinfo() {
//        OkGo.post(Api.ifpingce)
//                .params("uuid",Userid)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Gson gson = new Gson();
//                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
//                        if (wx.getState()==1){
//                            pingce = 1 ;
//                        }else{
//                            pingce = 0;
//                        }
//                    }
//                });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appraisal_evaluation:
                if (sharehelper.getSharedPreference("flag5","").toString().equals("0")){
                    IntentUtils.startActivity(this,EvaluationActivity.class,true);
                }else {
                    ToastUtils.shortToast("您已经测评过了！");
                }
                break;

        }
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("风险测评");
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
                AppraisalActivity.this.finish();
            }
        });
    }

}