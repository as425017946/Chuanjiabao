package friendgoods.vidic.com.generalframework.faxian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;

/**
 * 赚取积分
 */
public class GetJifenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_jifen);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        settiaozhuan();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("获取积分");
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
                GetJifenActivity.this.finish();
            }
        });
    }
    /**
     * 邀请好友
     * 购买积分
     * 积分卡兑换
     */
    @BindView(R.id.get_jifen_yaoqing)
    LinearLayout l_yaoqing;
    @BindView(R.id.get_jifen_pay)
    LinearLayout l_pay;
    @BindView(R.id.get_jifen_duihuan)
    LinearLayout l_duihuan;
    private void settiaozhuan(){
        l_yaoqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetJifenActivity.this,YaoqinghaoyouActivity.class);
                startActivity(intent);
            }
        });
        l_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetJifenActivity.this,PayjifenActivity.class);
                startActivity(intent);
            }
        });
        l_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetJifenActivity.this,JifenDuihuanActivity.class);
                startActivity(intent);
            }
        });
    }

}
