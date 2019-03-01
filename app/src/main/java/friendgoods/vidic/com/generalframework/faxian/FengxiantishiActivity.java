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
 * 风险提示
 */
public class FengxiantishiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fengxiantishi);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        showinfo();
    }

    @BindView(R.id.fengxiantishi_info)
    TextView tv_info;
    private void showinfo() {
        tv_info.setText(R.string.fenxiantishi);
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("风险提示说明");
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
                FengxiantishiActivity.this.finish();
            }
        });
    }


}
