package friendgoods.vidic.com.generalframework.faxian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.MyJifenBean;
import friendgoods.vidic.com.generalframework.Bean.ShowMinejifenBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 我的积分
 */
public class MyJifenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jifen);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        Settiaozhuan();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("我的积分");
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
                MyJifenActivity.this.finish();
            }
        });
    }

    /**
     * 积分
     * 兑商品
     */
    @BindView(R.id.my_jifen_duihuan)
    LinearLayout l_duihuan;
    @BindView(R.id.my_jifen_more)
    LinearLayout l_jifen_more;
    @BindView(R.id.myjifen_jifen)
    TextView tv_jifen;
    private void Settiaozhuan(){
        //查询我的积分
        OkGo.post(Api.myjifen)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShowMinejifenBean showMinejifenBean = gson.fromJson(s,ShowMinejifenBean.class);
                        if (showMinejifenBean.getState()==1){
                            if (showMinejifenBean.getData()!=null){
                                tv_jifen.setText(showMinejifenBean.getData().getIntegral()+"");
                            }
                        }else {
                            ToastUtils.shortToast(showMinejifenBean.getMessage());
                        }
                    }
                });
        l_jifen_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyJifenActivity.this,GetJifenActivity.class);
                startActivity(intent);
            }
        });
        l_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyJifenActivity.this,JifenShoppingmallActivity.class);
                startActivity(intent);
            }
        });
    }
}
