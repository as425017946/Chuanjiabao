package friendgoods.vidic.com.generalframework.touzi.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;

public class XitonginfomoreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xitonginfomore);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setinfo();
    }

    /**
     * 写入信息
     */
    @BindView(R.id.more_title)
    TextView tv_title;
    @BindView(R.id.more_time)
    TextView tv_time;
    @BindView(R.id.more_info)
    TextView tv_info;
    @BindView(R.id.messages_more_url)
    TextView tv_url;
    private void setinfo() {
        if (getIntent().getStringExtra("title")!=null){
            tv_title.setText(getIntent().getStringExtra("title"));
        }
        if (getIntent().getStringExtra("time")!=null){
            tv_time.setText(getIntent().getStringExtra("time"));
        }
        if (getIntent().getStringExtra("info")!=null){

            tv_info.setText(getIntent().getStringExtra("info"));
        }
        if (getIntent().getStringExtra("urls")!=null){
            tv_url.setText(getIntent().getStringExtra("urls"));
        }
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("系统消息");
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
                XitonginfomoreActivity.this.finish();
            }
        });
    }


}
