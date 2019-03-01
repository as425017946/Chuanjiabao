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
import friendgoods.vidic.com.generalframework.Bean.ShoppingOrderBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.ShoppingOrderAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 商城 我的订单
 */
public class ShoppingOrderActivity extends BaseActivity implements XListView.IXListViewListener{
    ShoppingOrderAdapter adapter;
    ArrayList<ShoppingOrderBean> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_order);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        showorder();
        mHandler = new Handler();
        adapter = new ShoppingOrderAdapter(ShoppingOrderActivity.this,arrayList);
        mListView.setAdapter(adapter);
    }

    /**
     * 展示订单
     */
    @BindView(R.id.shoppingorder_listview)
    XListView mListView;
    private Handler mHandler;
    int page = 1,pageSize = 10;
    private void showorder() {
        OkGo.post(Api.shopOrders)
                .tag(this)
                .params("uuid",Userid)
                .params("page",page)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("商城订单",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        ShoppingOrderBean shoppingOrderBean = gson.fromJson(s,ShoppingOrderBean.class);
                        if (shoppingOrderBean.getState()==1){
                            if (shoppingOrderBean.getData()!=null){
                                for (int i = 0; i <shoppingOrderBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(shoppingOrderBean);
                                }

                                if (pageSize>=shoppingOrderBean.getData().getPageInfo().getTotal()){
                                    mListView.zhanshi(false);
                                }else{
                                    mListView.zhanshi(true);
                                }
                            }
                        }else {
                            ToastUtils.shortToast(shoppingOrderBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    private void showorder2(int page2,int pageSize2) {
        OkGo.post(Api.shopOrders)
                .tag(this)
                .params("uuid",Userid)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("商城订单",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        ShoppingOrderBean shoppingOrderBean = gson.fromJson(s,ShoppingOrderBean.class);
                        if (shoppingOrderBean.getState()==1){
                            if (shoppingOrderBean.getData()!=null){
                                for (int i = 0; i <shoppingOrderBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(shoppingOrderBean);
                                }
                                if (pageSize>=shoppingOrderBean.getData().getPageInfo().getTotal()){
                                    mListView.zhanshi(false);
                                }else{
                                    mListView.zhanshi(true);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }else {
                            ToastUtils.shortToast(shoppingOrderBean.getMessage());
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
        ttitle.setText("商城订单");
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
                ShoppingOrderActivity.this.finish();
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
                showorder();
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
                showorder2(page ++,(pageSize+5));
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
