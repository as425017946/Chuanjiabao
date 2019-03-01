package friendgoods.vidic.com.generalframework.faxian.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.TouziYugaoBean;
import friendgoods.vidic.com.generalframework.Bean.WangqihejiBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.TouziYugaoAdapter;
import friendgoods.vidic.com.generalframework.adapter.WangqihejiAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

public class HejiAllFragment extends Fragment implements XListView.IXListViewListener{
    @BindView(R.id.xlistview_heji)
    XListView mListView;
    private Handler mHandler;
    int pageSize=10,page=1;
    ArrayList<WangqihejiBean> arrayList = new ArrayList<>();
    WangqihejiAdapter adapter;
    private Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heji_all, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new WangqihejiAdapter(context,arrayList);
        mListView.setAdapter(adapter);
        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        setinfo();
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
