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
import friendgoods.vidic.com.generalframework.Bean.MingxiBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.MingxiAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * A simple {@link Fragment} subclass.
 */
public class MingxiZhuanchuFragment extends Fragment implements XListView.IXListViewListener{

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @BindView(R.id.zhuanchu_no)
    TextView tv_no;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mingxi_zhuanchu, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MingxiAdapter(context,arrayList,2);
        setinfo();
    }
    /**
     * 展示信息
     */
    ArrayList<MingxiBean> arrayList = new ArrayList<>();
    MingxiAdapter adapter;
    @BindView(R.id.mingxi_zhuanru)
    XListView listView;
    private Handler mHandler;

    int pageSize=5,page=1;

    private void setinfo(){
        mHandler = new Handler();
        OkGo.post(Api.mingxi)
                .tag(this)
                .params("uuid",Userid)
                .params("flag",2)
                .params("page",page)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("收支明细2",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        MingxiBean mingxiBean =  gson.fromJson(s,MingxiBean.class);
                        if (mingxiBean.getState()==1){
                            if (mingxiBean.getData().getPageInfo().getList().size()==0){
                                listView.setVisibility(View.GONE);
                                tv_no.setVisibility(View.VISIBLE);
                            }else {
                                listView.setVisibility(View.VISIBLE);
                                tv_no.setVisibility(View.GONE);
                            }
                            if (mingxiBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i <mingxiBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(mingxiBean);
                                }
                                listView.setAdapter(adapter);
                            }
                            if (pageSize>=mingxiBean.getData().getPageInfo().getTotal()){
                                listView.zhanshi(false);
                            }else{
                                listView.zhanshi(true);
                            }
                        }else{
                            ToastUtils.shortToast(mingxiBean.getMessage());
                        }
                    }
                });
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
    }

    private void setinfo2(int page2,int pageSize2){
        mHandler = new Handler();
        OkGo.post(Api.mingxi)
                .tag(this)
                .params("uuid",Userid)
                .params("flag",2)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("收支明细",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        MingxiBean mingxiBean =  gson.fromJson(s,MingxiBean.class);
                        if (mingxiBean.getState()==1){
                            for (int i = 0; i <mingxiBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(mingxiBean);
                            }
                            adapter.notifyDataSetChanged();
                            if (pageSize>=mingxiBean.getData().getPageInfo().getTotal()){
                                listView.zhanshi(false);
                            }else{
                                listView.zhanshi(true);
                            }
                        }else{
                            ToastUtils.shortToast(mingxiBean.getMessage());
                        }
                    }
                });
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
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
        listView.stopRefresh();
        listView.stopLoadMore();
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        //格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String time = formatter.format(curDate);
        listView.setRefreshTime(time);
    }
}
