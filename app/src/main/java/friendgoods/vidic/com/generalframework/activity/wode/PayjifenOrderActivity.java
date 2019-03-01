package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.faxian.PayjifenActivity;

/**
 * 支付确认订单界面
 */
public class PayjifenOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payjifen_order);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        upinfo();
    }

    /**
     * 展示信息
     */
    @BindView(R.id.pay_order_money)
    TextView tv_moeny1;
    @BindView(R.id.pay_order_money2)
    TextView tv_money2;
    @BindView(R.id.pay_order_fenshu)
    TextView tv_fenshu;
    @BindView(R.id.pay_order_name)
    TextView tv_name;
    @BindView(R.id.pay_order_queren)
    Button btn_tijiao;
    private void upinfo() {
        tv_name.setText("积分");
        tv_fenshu.setText(getIntent().getStringExtra("fenshu"));
        tv_moeny1.setText("￥"+getIntent().getStringExtra("fenshu"));
        tv_money2.setText("￥"+getIntent().getStringExtra("fenshu"));
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayjifenOrderActivity.this,PayjifenOrderOkActivity.class);
                intent.putExtra("money",tv_moeny1.getText().toString());
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
                PayjifenOrderActivity.this.finish();
            }
        });
    }
}
