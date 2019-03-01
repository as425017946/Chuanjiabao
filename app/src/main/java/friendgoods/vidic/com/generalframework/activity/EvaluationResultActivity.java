package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.IntentUtils;

/**
 * 测评结果
 */
public class EvaluationResultActivity extends BaseActivity implements View.OnClickListener {

    private TextView resultAgain;
    private TextView resultKnow;
    private String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_result);
        ButterKnife.bind(this);
        setTtitle();
        a=getIntent().getStringExtra("ceping");
        fanhui();
        setinfo();
        resultAgain = findViewById(R.id.result_again);
        resultKnow = findViewById(R.id.result_know);
        resultAgain.setOnClickListener(this);
        resultKnow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.result_again:
                IntentUtils.startActivity(this,AppraisalActivity.class,true);
                break;
            case R.id.result_know:
                EvaluationResultActivity.this.finish();
                break;

        }
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("风险测评结果");
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
                EvaluationResultActivity.this.finish();
            }
        });
    }

    private String jinshen="谨慎型";
    private String jinsheninfo="在投资中，保证本金不受损失和保持资产的流动性是您的首要目标，不愿意承受投资风险。您可以参与多彩投的消费众筹（非投资性质，无投资风险），享受独特的生活风格体验和项目参与感，逐渐积累投资经验";
    private String wenjianxing="稳健型";
    private String wenjianxinginfo="在投资中，稳定是您首先要考虑的因素。且希望在本金稳定的基础上能有一些增值收入，可接受低程度的风险，对投资回报的要求不高。推荐您选择知名品牌的酒店收益权类项目参与";
    private String jinqu="进取型";
    private String jinquinfo="在投资中，您渴望有较高的投资收益，可以承受一定的投资波动。您有较高的收益目标，且对风险有清醒的认识。平台所有投资类项目均适合您参与";
    private String jijin="激进型";
    private String jijininfo="在投资中，您通常专注于投资的高比例增值，并愿意为此承受较大的风险。短期的投资波动并不会对您造成大的影响，追求超高的回报才是目标。平台所有类型项目均适合您参与，推荐关注商业模式创新性强、满足市场潜在需求的股权类投资项目";

    /**
     * 写入展示信息
     */
    @BindView(R.id.cepingjieguo_title)
    TextView tv_title;
    @BindView(R.id.cepingjieguo_info)
    TextView tv_info;
    private void setinfo(){
        if (a.equals("1")){
            tv_title.setText(jinshen);
            tv_info.setText(jinsheninfo);
        }else if (a.equals("2")){
            tv_title.setText(wenjianxing);
            tv_info.setText(wenjianxinginfo);
        }else if (a.equals("3")){
            tv_title.setText(jinqu);
            tv_info.setText(jinquinfo);
        }else if (a.equals("4")){
            tv_title.setText(jijin);
            tv_info.setText(jijininfo);
        }
    }

}