package friendgoods.vidic.com.generalframework.activity.wode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.R;

/**
 * 到支付界面
 */
public class PayjifenOrderOkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payjifen_order_ok);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        upinfo();
    }

    @BindView(R.id.pay_order_ok_money)
    TextView tv_money;
    private void upinfo() {
        tv_money.setText(getIntent().getStringExtra("money"));
    }


    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("兑换积分");
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
                PayjifenOrderOkActivity.this.finish();
            }
        });
    }

}
