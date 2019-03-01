package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import friendgoods.vidic.com.generalframework.Bean.AfterAdminBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.AfterAdminAdapter;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 *投后管理
 */
public class AfterAdminActivity extends BaseActivity  implements XListView.IXListViewListener{
    @BindView(R.id.xlistview_touhou)
    XListView mListView;
    private Handler mHandler;

    int pageSize=5,page=1;
    @BindView(R.id.showtitle)
    TextView tv_showtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afteradmin);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        adapter = new AfterAdminAdapter(AfterAdminActivity.this,arrayList);
        setinfo();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("投后管理");
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
                AfterAdminActivity.this.finish();
            }
        });
    }

    /**
     * 写入信息
     */
    ArrayList<AfterAdminBean>  arrayList = new ArrayList<>();
    AfterAdminAdapter adapter;
//    @BindView(R.id.after_listview)
//    ListView list;

    private void setinfo(){
        OkGo.post(Api.touhouguanli)
                .tag(this)
                .params("uuid",Userid)
                .params("page",1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投后管理",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        AfterAdminBean adminBean = gson.fromJson(s,AfterAdminBean.class);
                        if (adminBean.getState()==1){
                            if (adminBean.getData().getPageInfo().getList().size()>0){
                                tv_showtitle.setVisibility(View.GONE);
                                mListView.setVisibility(View.VISIBLE);
                                for (int i = 0; i <adminBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(adminBean);
                                }
                                mListView.setAdapter(adapter);
                                if (pageSize>adminBean.getData().getPageInfo().getList().size() ){
                                    mListView.zhanshi(false);
                                }else {
                                    mListView.zhanshi(true);
                                }
                              adapter.notifyDataSetChanged();
                            }else{
                                tv_showtitle.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        }else {
                            ToastUtils.shortToast(adminBean.getMessage());
                        }
                    }
                });
    }
    private void setinfo2(int a,int b){
        OkGo.post(Api.touhouguanli)
                .tag(this)
                .params("uuid",Userid)
                .params("page",a)
                .params("pageSize",b)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("投后管理",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        AfterAdminBean adminBean = gson.fromJson(s,AfterAdminBean.class);
                        if (adminBean.getState()==1){
                            if (adminBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i <adminBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(adminBean);
                                }
                                adapter.notifyDataSetChanged();
                            }else{

                            }
                        }else {
                            ToastUtils.shortToast(adminBean.getMessage());
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