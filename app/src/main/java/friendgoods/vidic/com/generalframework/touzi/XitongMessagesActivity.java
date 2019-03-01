package friendgoods.vidic.com.generalframework.touzi;

import android.content.Intent;
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
import friendgoods.vidic.com.generalframework.Bean.XitongBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.XitongMessageAdapter;
import friendgoods.vidic.com.generalframework.touzi.more.XitongMessagesMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 系统消息页面
 */
public class XitongMessagesActivity extends BaseActivity implements XListView.IXListViewListener{
    @BindView(R.id.xlistview_xitong)
    XListView mListView;
    private Handler mHandler;

    int pageSize=5,page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xitong_messages);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        mHandler = new Handler();
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        adapter = new XitongMessageAdapter(XitongMessagesActivity.this,arrayList);

    }

    /**
     * 展示系统消息
     */
    ArrayList<XitongBean> arrayList = new ArrayList<>();
    XitongMessageAdapter adapter;
//    @BindView(R.id.xitong_listview)
//    ListView listView;
    private void setinfo() {
        OkGo.post(Api.message_show)
                .tag(this)
                .params("page",1)
                .params("pageSize",pageSize)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("消息",s+"**");
                        arrayList.clear();
                        Gson gson = new Gson();
                        XitongBean xitongBean = gson.fromJson(s,XitongBean.class);
                        if (xitongBean.getState()==1){
//                            Log.e("数组长度",xitongBean.getData().getPageInfo().getList().size()+"");
                            if (xitongBean.getData().getPageInfo().getList().size()>pageSize){
                                mListView.zhanshi(true);
                            }else {
                                mListView.zhanshi(false);
                            }
                            for (int i = 0; i <xitongBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(xitongBean);
                            }
                            mListView.setAdapter(adapter);
                           adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }
                });
    }
    private void setinfo2(int a,int b) {
        OkGo.post(Api.message_show)
                .tag(this)
                .params("page",a)
                .params("pageSize",b)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList.clear();
                        Gson gson = new Gson();
                        XitongBean xitongBean = gson.fromJson(s,XitongBean.class);
                        if (xitongBean.getState()==1){
                            Log.e("数组长度",xitongBean.getData().getPageInfo().getList().size()+"");
//                            if (xitongBean.getData().getPageInfo().getList().size()>0){
//
//                            }
                            for (int i = 0; i <xitongBean.getData().getPageInfo().getList().size() ; i++) {
                                arrayList.add(xitongBean);
                            }
                          adapter.notifyDataSetChanged();
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
    TextView tquxiao;
    private void setTtitle(){
        ttitle.setText("系统消息");
        tquxiao.setText("管理");
    }
    /**
     * 返回
     * 管理
     */
    @BindView(R.id.touzi_fanhui)
    LinearLayout lfanhui;
    @BindView(R.id.touzi_quxiao)
    LinearLayout guanli;
    private void fanhui(){
        lfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XitongMessagesActivity.this.finish();
            }
        });
        guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XitongMessagesActivity.this, XitongMessagesMoreActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setinfo();
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
