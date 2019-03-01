package friendgoods.vidic.com.generalframework.touzi.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;

/**
 * 投资购买支付界面
 */
public class TouziOrderPayOkActivity extends AppCompatActivity {
    SharedPreferencesHelper sharedPreferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_order_pay_ok);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(TouziOrderPayOkActivity.this,"chuanjiabao");
        setTtitle();
        fanhui();
        MyApplication.getApplication().addActivity(TouziOrderPayOkActivity.this);
        showinfo();
    }

    @BindView(R.id.pay_money)
    TextView tv_paymoney;
    private void showinfo() {
        tv_paymoney.setText(getIntent().getStringExtra("money")+"元");
//        sharedPreferencesHelper.remove("fanganid");
//        sharedPreferencesHelper.remove("addressid");
//        sharedPreferencesHelper.remove("youhuiid");
//        sharedPreferencesHelper.remove("youhuimoney");
//        MyApplication.getApplication().exit();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("支付方式");
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
                TouziOrderPayOkActivity.this.finish();
            }
        });
    }
}
