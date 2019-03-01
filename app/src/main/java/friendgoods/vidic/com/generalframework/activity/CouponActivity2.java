package friendgoods.vidic.com.generalframework.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import friendgoods.vidic.com.generalframework.Bean.DuihuanYouhuiquanBean;
import friendgoods.vidic.com.generalframework.Bean.ShowyouhuiquanBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.YouhuiquanAdapter2;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 代金券2
 */
public class CouponActivity2 extends BaseActivity{
    private static final String TAG = "CouponActivity2";
    YouhuiquanAdapter2 adapter;
    ArrayList<ShowyouhuiquanBean> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        adapter = new YouhuiquanAdapter2(CouponActivity2.this,arrayList);
        mListView.setAdapter(adapter);
        setTtitle();
        fanhui();
        setinfo();
//        getyouhuiquan();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("我的代金券");
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
                CouponActivity2.this.finish();
            }
        });
    }
    /**
     * 写入信息
     */
    @BindView(R.id.coupon_tijiao)
    TextView tv_tijiao;
    @BindView(R.id.coupon_xinxi)
    EditText edt_xinxi;
    @BindView(R.id.youhui_xlistview)
    XListView mListView;
    int pageSize=5,page=1;
    private Handler mHandler;

    private void setinfo(){
//        mHandler = new Handler();
        Log.e(TAG, "setinfo: "+getIntent().getStringExtra("numbers") );
        //展示优惠券
        OkGo.post(Api.showyouhui_zhuanshu)
                .tag(this)
                .params("uuid",Userid)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("number",getIntent().getStringExtra("numbers"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess:挑选优惠券 "+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        ShowyouhuiquanBean showyouhuiquanBean = gson.fromJson(s,ShowyouhuiquanBean.class);
                        if (showyouhuiquanBean.getState()==1){
                            if (showyouhuiquanBean.getData()!=null){
                                if (showyouhuiquanBean.getData().getPageInfo().getList().size()>0){
                                    for (int i = 0; i < showyouhuiquanBean.getData().getPageInfo().getList().size(); i++) {
                                        arrayList.add(showyouhuiquanBean);
                                    }
                                }else {
                                    ToastUtils.shortToast("没有可用的优惠券");
                                }


//                                if (pageSize>=showyouhuiquanBean.getData().getPageInfo().getTotal()){
//                                    mListView.zhanshi(false);
//                                }else{
//                                    mListView.zhanshi(true);
//                                }
                            }
                        }
                    }
                });
//        mListView.setPullLoadEnable(true);
//        mListView.setXListViewListener(this);
    }
//    private void setinfo2(int page2,int pageSize2){
//        mHandler = new Handler();
//        //展示优惠券
//        OkGo.post(Api.showyouhui)
//                .tag(this)
//                .params("uuid",Userid)
//                .params("page",page2)
//                .params("pageSize",pageSize2)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        arrayList.clear();
//                        Gson gson = new Gson();
//                        ShowyouhuiquanBean showyouhuiquanBean = gson.fromJson(s,ShowyouhuiquanBean.class);
//                        if (showyouhuiquanBean.getState()==1){
//                            if (showyouhuiquanBean.getData()!=null){
//                                for (int i = 0; i < showyouhuiquanBean.getData().getPageInfo().getList().size(); i++) {
//                                    arrayList.add(showyouhuiquanBean);
//                                }
//                                mListView.setAdapter(adapter);
//                                if (pageSize>=showyouhuiquanBean.getData().getPageInfo().getTotal()){
//                                    mListView.zhanshi(false);
//                                }else{
//                                    mListView.zhanshi(true);
//                                }
//                                adapter.notifyDataSetChanged();
//                            }
//                        }
//                    }
//                });
//    }
//    private void getyouhuiquan(){
//        tv_tijiao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TextUtils.isEmpty(edt_xinxi.getText().toString())){
//                    ToastUtils.shortToast("请输入兑换码");
//                }else {
//                    OkGo.post(Api.duihuanyouhuiquan)
//                            .tag(this)
//                            .params("uuid",Userid)
//                            .params("number",edt_xinxi.getText().toString())
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onSuccess(String s, Call call, Response response) {
//                                    Gson gson = new Gson();
//                                    DuihuanYouhuiquanBean duihuan = gson.fromJson(s,DuihuanYouhuiquanBean.class);
//                                    if (duihuan.getState()==0){
//                                        ToastUtils.shortToast(duihuan.getMessage());
//                                    }else {
//                                        ToastUtils.shortToast("兑换成功");
//                                        setinfo();
//                                    }
//                                }
//                            });
//                }
//            }
//        });
//    }
//    //下拉刷新
//    @Override
//    public void onRefresh() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                page = 1;
//                pageSize = 5;
//                setinfo();
//                onLoad();
//            }
//        }, 2000);
//    }
//
//    //上拉加载
//    @Override
//    public void onLoadMore() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                setinfo2(page ++,(pageSize+5));
//                adapter.notifyDataSetChanged();
//                onLoad();
//            }
//        }, 2000);
//    }
//
//    private void onLoad() {
//        mListView.stopRefresh();
//        mListView.stopLoadMore();
//        //获取当前时间
//        Date curDate = new Date(System.currentTimeMillis());
//        //格式化
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
//        String time = formatter.format(curDate);
//        mListView.setRefreshTime(time);
//    }
}