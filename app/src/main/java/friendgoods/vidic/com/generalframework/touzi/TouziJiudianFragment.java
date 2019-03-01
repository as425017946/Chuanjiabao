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
import android.widget.Toast;

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
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.TouziShowAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 投资酒店页面
 */
public class TouziJiudianFragment extends Fragment  implements XListView.IXListViewListener{
    @BindView(R.id.touzi_new_no)
    TextView tv_no;
    private Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_touzi_new, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TouziShowAdapter(context,arrayList);
        mListView.zhanshi(false);
        setinfo();

    }

    //展示信息
    ArrayList<TouziShowBean> arrayList = new ArrayList<>();
    TouziShowAdapter adapter;
    //    @BindView(R.id.touzi_listviewnew)
//    ListView listView;
    @BindView(R.id.fabnew)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.xlistview)
    XListView mListView;
    private Handler mHandler;

    int pageSize=5,page=1;

    private void setinfo() {
        mHandler = new Handler();
        OkGo.post(Api.touzijiudian)
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
//                        Log.e("投资最新",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        TouziShowBean showBean = gson.fromJson(s,TouziShowBean.class);
                        if (showBean.getState()==1){
                            if (showBean.getData().getPageInfo().getList().size()==0){
                                mListView.setVisibility(View.GONE);
                                tv_no.setVisibility(View.VISIBLE);
                            }else {
                                mListView.setVisibility(View.VISIBLE);
                                tv_no.setVisibility(View.GONE);
                            }
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            for (int i = 0; i <showBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(showBean);
                            }
                            mListView.setAdapter(adapter);
                            if (pageSize>=showBean.getData().getPageInfo().getTotal()){
                                mListView.zhanshi(false);
                            }else{
                                mListView.zhanshi(true);
                            }
                        }else{
                            ToastUtils.shortToast(showBean.getMessage());
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

    /**
     * 下拉加载使用
     */
    private void setinfo2(final int page2,final int pageSize2) {
        OkGo.post(Api.touzijiudian)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                      //  Log.e("投资最新",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        TouziShowBean showBean = gson.fromJson(s,TouziShowBean.class);
                        if (showBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            for (int i = 0; i <showBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(showBean);
                            }
                            if (pageSize>=showBean.getData().getPageInfo().getTotal()){
                                mListView.zhanshi(false);
                            }else{
                                mListView.zhanshi(true);
                            }
                            adapter.notifyDataSetChanged();
                        }else{
                            ToastUtils.shortToast(showBean.getMessage());
                        }
                    }
                });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.shortToast("客服");
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