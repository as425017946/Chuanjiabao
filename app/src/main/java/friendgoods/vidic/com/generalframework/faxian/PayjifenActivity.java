package friendgoods.vidic.com.generalframework.faxian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.PayjifenOrderActivity;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

/**
 * 购买积分
 */
public class PayjifenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payjifen);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        upinfo();
    }

    @BindView(R.id.pay_money)
    EditText edt_money;
    @BindView(R.id.pay_goumai)
    Button btn_goumai;
    private void upinfo() {
        btn_goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_money.getText().toString())){
                    ToastUtils.shortToast("请输入购买的金额");
                }else {
                    Intent intent = new Intent(PayjifenActivity.this,PayjifenOrderActivity.class);
                    intent.putExtra("fenshu",edt_money.getText().toString());
                    startActivity(intent);
                }
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
                PayjifenActivity.this.finish();
            }
        });
    }
}
