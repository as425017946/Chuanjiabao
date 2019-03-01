package friendgoods.vidic.com.generalframework.touzi;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import friendgoods.vidic.com.generalframework.Bean.TouziShowBean;
import friendgoods.vidic.com.generalframework.Bean.TouziYugaoBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.TouziYugaoAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouziYugaoFragment extends Fragment  implements XListView.IXListViewListener{
    @BindView(R.id.touzi_yugao_no)
    TextView tv_no;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_touzi_yugao, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        adapter = new TouziYugaoAdapter(context,arrayList);
        mListView.zhanshi(false);
        setinfo();
    }

    //展示信息
    ArrayList<TouziYugaoBean> arrayList = new ArrayList<>();
    TouziYugaoAdapter adapter;
//    @BindView(R.id.touzi_listviewyugao)
//    ListView listView;
//    ListView listView;
    @BindView(R.id.fabnew)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.xlistviewyugao)
    XListView mListView;
    private Handler mHandler;
    int pageSize=5,page=1;

    private void setinfo() {
        OkGo.post(Api.touziyugao)
                .tag(this)
                .params("page",1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast("数据信息异常");
                    }
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投资预告",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        TouziYugaoBean yugaoBean = gson.fromJson(s,TouziYugaoBean.class);
                        if (yugaoBean.getState()==1){
                            if (yugaoBean.getData().getPageInfo().getList().size()==0){
                                mListView.setVisibility(View.GONE);
                                tv_no.setVisibility(View.VISIBLE);
                            }else {
                                mListView.setVisibility(View.VISIBLE);
                                tv_no.setVisibility(View.GONE);
                            }
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            for (int i = 0; i <yugaoBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(yugaoBean);
                            }
                            mListView.setAdapter(adapter);
                            if (pageSize>=yugaoBean.getData().getPageInfo().getTotal()){
                                mListView.zhanshi(false);
                            }else{
                                mListView.zhanshi(true);
                            }
                        }else{
                            ToastUtils.shortToast(yugaoBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.shortToast("客服");
            }
        });
    }
    private void setinfo2(int a,int b) {
        OkGo.post(Api.touziyugao)
                .tag(this)
                .params("page",a)
                .params("pageSize",b)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("投资预告",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        TouziYugaoBean yugaoBean = gson.fromJson(s,TouziYugaoBean.class);
                        if (yugaoBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            for (int i = 0; i <yugaoBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(yugaoBean);
                            }
                            if (pageSize>=yugaoBean.getData().getPageInfo().getTotal()){
                                mListView.zhanshi(false);
                            }else{
                                mListView.zhanshi(true);
                            }
//                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }else{
                            ToastUtils.shortToast(yugaoBean.getMessage());
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
