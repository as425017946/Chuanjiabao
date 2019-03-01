package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;

/**
 * 钱包设置
 */
public class BalanceSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_setting);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        gettiaozhuan();
    }

    /**
     * 跳转
     */
    @BindView(R.id.qianbao_guanli)
    LinearLayout ll_guanli;
    @BindView(R.id.qianbao_zhifumima)
    LinearLayout ll_zhifumima;
    private void gettiaozhuan() {
        //分红卡管理
        ll_guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BalanceSettingActivity.this,GuanliCardActivity.class);
                startActivity(intent);
            }
        });
        //支付密码管理
        ll_zhifumima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BalanceSettingActivity.this,GuanlizhifumimaActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("钱包设置");
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
                BalanceSettingActivity.this.finish();
            }
        });
    }

}
