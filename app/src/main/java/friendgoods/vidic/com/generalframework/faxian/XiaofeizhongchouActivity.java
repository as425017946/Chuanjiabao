package friendgoods.vidic.com.generalframework.faxian;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.SousuoShowBean;
import friendgoods.vidic.com.generalframework.Bean.TouziYugaoBean;
import friendgoods.vidic.com.generalframework.Bean.XiaofeizhongchouBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.SousuoShowAdapter;
import friendgoods.vidic.com.generalframework.adapter.TouziYugaoAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 更多消费众筹
 */
public class XiaofeizhongchouActivity extends BaseActivity implements XListView.IXListViewListener{
    @BindView(R.id.xlistview_xiaofei)
    XListView mListView;
    private Handler mHandler;

    int pageSize=5,page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaofeizhongchou);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();

        adapter = new SousuoShowAdapter(XiaofeizhongchouActivity.this,arrayList);
        mListView.setAdapter(adapter);
        setinfo();
    }

    /**
     * 写入信息
     */
//    @BindView(R.id.xiaofeizhongchou_morelistview)
//    ListView listView;
    ArrayList<SousuoShowBean> arrayList = new ArrayList<>();
    SousuoShowAdapter adapter;
    private void setinfo() {
        mHandler = new Handler();
        OkGo.post(Api.zhongchou)
                .tag(this)
                .params("page",1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("更多消费众筹",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        SousuoShowBean yugaoBean = gson.fromJson(s,SousuoShowBean.class);
                        if (yugaoBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            for (int i = 0; i <yugaoBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(yugaoBean);
                            }
                            if (pageSize>yugaoBean.getData().getPageInfo().getList().size()){
                                mListView.zhanshi(false);
                            }else{
                                mListView.zhanshi(true);
                            }
                            adapter.notifyDataSetChanged();
                        }else{
                            ToastUtils.shortToast(yugaoBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }
    private void setinfo2(int a,int b) {
        OkGo.post(Api.zhongchou)
                .tag(this)
                .params("page",a)
                .params("pageSize",b)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("消费众筹",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        SousuoShowBean yugaoBean = gson.fromJson(s,SousuoShowBean.class);
                        if (yugaoBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            for (int i = 0; i <yugaoBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(yugaoBean);
                            }
                            adapter.notifyDataSetChanged();
                        }else{
                            ToastUtils.shortToast(yugaoBean.getMessage());
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
        ttitle.setText("消费众筹");
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
                XiaofeizhongchouActivity.this.finish();
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
