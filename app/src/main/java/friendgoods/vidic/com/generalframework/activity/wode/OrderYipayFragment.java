package friendgoods.vidic.com.generalframework.activity.wode;


import android.content.Context;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.MyOrderStateBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.MyOrderStateAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderYipayFragment extends Fragment  implements XListView.IXListViewListener {
    @BindView(R.id.xlistview_order)
    XListView mListView;
    private Handler mHandler;
    int pageSize=5,page=1;
    @BindView(R.id.showtitle)
    TextView tv_showtitle;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_order_yipay, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        adapter = new MyOrderStateAdapter(context,arrayList);
        mListView.setAdapter(adapter);
        setInfo();

    }

    /**
     * 显示信息
     */
    MyOrderStateAdapter adapter;
    ArrayList<MyOrderStateBean> arrayList = new ArrayList<>();
    private void setInfo(){
        OkGo.post(Api.orderstate)
                .tag(this)
                .params("uuid",Userid)
                .params("status",2)
                .params("page",1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("我的订单",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        MyOrderStateBean myOrderBean = gson.fromJson(s,MyOrderStateBean.class);
                        if (myOrderBean.getData().getPageInfo().getList().size()==0){
                            tv_showtitle.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                        }else {
                            tv_showtitle.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            for (int i = 0; i <myOrderBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(myOrderBean);
                            }
                        }

                        if (pageSize>myOrderBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else {
                            mListView.zhanshi(true);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
    private void setInfo2(int a,int b){
        OkGo.post(Api.orderstate)
                .tag(this)
                .params("uuid",Userid)
                .params("status",2)
                .params("page",a)
                .params("pageSize",b)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("我的订单",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        MyOrderStateBean myOrderBean = gson.fromJson(s,MyOrderStateBean.class);
                        for (int i = 0; i <myOrderBean.getData().getPageInfo().getList().size() ; i++) {
                            arrayList.add(myOrderBean);
                        }
                        adapter.notifyDataSetChanged();
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
                setInfo();
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
                setInfo2(page ++,(pageSize+5));
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
