package friendgoods.vidic.com.generalframework.touzi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.more.XitongMessagesMoreActivity;

public class TouziPayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_pay);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(TouziPayActivity.this);
                LayoutInflater inflater = LayoutInflater.from(TouziPayActivity.this);
                final View DialogView = inflater .inflate ( R.layout.tishibutton, null);//1、自定义布局
                TextView title = (TextView) DialogView.findViewById(R.id.tishibutton_title);//自定义控件
                TextView message = (TextView) DialogView.findViewById(R.id.tishibutton_message);//自定义控件
                title.setText("提示");
                message.setText("取消后该订单将不可进行支付");
                TextView back = (TextView) DialogView.findViewById(R.id.tishibutton_back);//自定义控件
                TextView ok = (TextView) DialogView.findViewById(R.id.tishibutton_ok);//自定义控件
                builder.setView(DialogView);
                final AlertDialog dialog = builder.create();
                //点击取消
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                //点击确认
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        TouziPayActivity.this.finish();
                    }
                });
                dialog.show();




            }
        });
    }
}
