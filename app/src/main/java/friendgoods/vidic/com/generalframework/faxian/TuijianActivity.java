package friendgoods.vidic.com.generalframework.faxian;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import friendgoods.vidic.com.generalframework.Bean.TuijianBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.GuanyuActivity;
import friendgoods.vidic.com.generalframework.adapter.TuijianAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 推荐项目
 */
public class TuijianActivity extends BaseActivity implements XListView.IXListViewListener{
    ArrayList<TuijianBean> arrayList = new ArrayList<>();
    TuijianAdapter adapter;
//    @BindView(R.id.tuijian_listview)
//    ListView lv;
    @BindView(R.id.xlistview_tuijian)
    XListView mListView;
    private Handler mHandler;
    int pageSize=5,page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        adapter = new TuijianAdapter(TuijianActivity.this,arrayList);
        mListView.setAdapter(adapter);
        setinfo();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("推荐项目");
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
                TuijianActivity.this.finish();
            }
        });
    }

    /**
     * 写入信息
     */
    private void setinfo(){
        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        OkGo.post(Api.tuijian)
                .tag(this)
                .params("page",1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("推荐项目",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        TuijianBean tuijianBean = gson.fromJson(s,TuijianBean.class);
                        if (tuijianBean.getState()==1){
                            if (tuijianBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < tuijianBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(tuijianBean);
                                }
                                if (pageSize>tuijianBean.getData().getPageInfo().getList().size()){
                                    mListView.zhanshi(false);
                                }else{
                                    mListView.zhanshi(true);
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                mListView.zhanshi(false);
                            }
                        }
                    }
                });
    }

    /**
     * 上拉加载使用
     */
    private void setinfo2(final int page2,final int pageSize2){
        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        OkGo.post(Api.tuijian)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("推荐项目",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        TuijianBean tuijianBean = gson.fromJson(s,TuijianBean.class);
                        if (tuijianBean.getState()==1){
                            if (tuijianBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < tuijianBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(tuijianBean);
                                }
                                adapter.notifyDataSetChanged();
                            }
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
