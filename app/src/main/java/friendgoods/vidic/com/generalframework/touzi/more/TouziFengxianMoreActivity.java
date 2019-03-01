package friendgoods.vidic.com.generalframework.touzi.more;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.TouziSanjiBean;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.TouziSanjiAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 投资认购后的3级界面
 */
public class TouziFengxianMoreActivity extends BaseActivity {

    private String number;
    public static String Names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_fengxian_more);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        number = getIntent().getStringExtra("number");
        Names = getIntent().getStringExtra("name");
//        Log.e("测试的number",number);
        setInfo();
        MyApplication.getApplication().addActivity(TouziFengxianMoreActivity.this);
    }

    /**
     * 写入信息
     */
    @BindView(R.id.touzisanji_listview)
    ListView listView;
    ArrayList<TouziSanjiBean> arrayList = new ArrayList<>();
    TouziSanjiAdapter adapter;
    private void setInfo() {
        OkGo.post(Api.sanjipage)
                .tag(this)
                .params("number",number)
                .params("page",1)
                .params("pageSize",20)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("三级页面",s);
                        Gson gson = new Gson();
                        TouziSanjiBean touziSanjiBean = gson.fromJson(s,TouziSanjiBean.class);

                        if (touziSanjiBean.getState()==1){
                            if (touziSanjiBean.getData().size()>0){
                                for (int i = 0; i <touziSanjiBean.getData().size(); i++) {
                                    arrayList.add(touziSanjiBean);
                                }
                                adapter = new TouziSanjiAdapter(TouziFengxianMoreActivity.this,arrayList);
                                listView.setAdapter(adapter);
                            }else {
                                ToastUtils.shortToast(touziSanjiBean.getMessage());
                            }
                        }
                    }
                });
    }

    /**
     * 写入title名字
     * 风险提示
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView lbaocun;
    private void setTtitle(){
        ttitle.setText("投资方案");
        lbaocun.setText("风险提示");
        lbaocun.setTextColor(getResources().getColor(R.color.wholeSYTime));
        lbaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TouziFengxianMoreActivity.this);
                LayoutInflater inflater = LayoutInflater.from(TouziFengxianMoreActivity.this);
                final View DialogView = inflater .inflate ( R.layout.fengxiantishi, null);//1、自定义布局
                TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
                 builder.setView(DialogView);
                final AlertDialog dialog = builder.create();
                //点击我已知晓
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
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
                TouziFengxianMoreActivity.this.finish();
            }
        });
    }
}
