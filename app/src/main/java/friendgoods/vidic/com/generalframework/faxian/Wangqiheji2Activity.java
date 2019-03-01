package friendgoods.vidic.com.generalframework.faxian;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WangqihejiBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.WangqihejiAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 往期合集 最新
 */
public class Wangqiheji2Activity extends BaseActivity implements XListView.IXListViewListener{
    @BindView(R.id.wangqiheji2_new)
    XListView mListView;
    private Handler mHandler;
    int pageSize=10,page=1;
    ArrayList<WangqihejiBean> arrayList = new ArrayList<>();
    WangqihejiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangqiheji2);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setinfo();
        adapter = new WangqihejiAdapter(Wangqiheji2Activity.this,arrayList);
    }

    /**
     * 写入名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("往期合集");
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
                Wangqiheji2Activity.this.finish();
            }
        });
    }

    //展示信息
    private void setinfo() {
        OkGo.post(Api.wangqiheji)
                .tag(this)
                .params("page",1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投资预告",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        WangqihejiBean wangqihejiBean = gson.fromJson(s,WangqihejiBean.class);
                        if (wangqihejiBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            if (wangqihejiBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i <wangqihejiBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(wangqihejiBean);
                                }

                                mListView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                if (pageSize>wangqihejiBean.getData().getPageInfo().getList().size()){
                                    mListView.zhanshi(false);
                                }else{
                                    mListView.zhanshi(true);
                                }
                                adapter.notifyDataSetChanged();
                            }else {

                            }


                        }else{
                            ToastUtils.shortToast(wangqihejiBean.getMessage());
                        }
                    }
                });

        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    private void setinfo2(int a,int b) {
        OkGo.post(Api.wangqiheji)
                .tag(this)
                .params("page",a)
                .params("pageSize",b)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投资预告",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        WangqihejiBean wangqihejiBean = gson.fromJson(s,WangqihejiBean.class);
                        if (wangqihejiBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            if (wangqihejiBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i <wangqihejiBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(wangqihejiBean);
                                }
                                adapter.notifyDataSetChanged();
                            }else {

                            }


                        }else{
                            ToastUtils.shortToast(wangqihejiBean.getMessage());
                        }
                    }
                });
    }
    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                pageSize = 5;
                setinfo();
                onLoad();
            }
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setinfo2(page ++,(pageSize+5));
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        //格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String time = formatter.format(curDate);
        mListView.setRefreshTime(time);
    }
}
