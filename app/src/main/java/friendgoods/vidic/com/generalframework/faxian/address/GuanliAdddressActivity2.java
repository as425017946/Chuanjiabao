package friendgoods.vidic.com.generalframework.faxian.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import friendgoods.vidic.com.generalframework.Bean.AddressBean;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.AddressAdapter2;
import friendgoods.vidic.com.generalframework.touzi.more.TouziMoreAddressActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 收货地址
 */
public class GuanliAdddressActivity2 extends BaseActivity implements XListView.IXListViewListener {
    @BindView(R.id.xlistview_address)
    XListView mListView;
    private Handler mHandler;
    int pageSize=5,page=1;
    AddressAdapter2.CallBack cb = new AddressAdapter2.CallBack() {
        @Override
        public void click_moren() {
            setinfo();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanli_adddress);
        ButterKnife.bind(this);
        adapter = new AddressAdapter2(GuanliAdddressActivity2.this,arrayList,cb);
        MyApplication.getApplication().addActivity(GuanliAdddressActivity2.this);
        setTtitle();
        fanhui();
        setMore();
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("管理收件地址");
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
                GuanliAdddressActivity2.this.finish();
            }
        });
    }

    /**
     * 新增地址
     */
    @BindView(R.id.guanli_moreaddress)
    Button btn_more;
    private void setMore(){
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuanliAdddressActivity2.this, TouziMoreAddressActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 绑定数据
     */
    private ArrayList<AddressBean> arrayList = new ArrayList<>();
    private AddressAdapter2 adapter;

    private void setinfo(){
        mHandler = new Handler();
        OkGo.post(Api.alladdress)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("地址",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        AddressBean addressBean = gson.fromJson(s,AddressBean.class);
                        if (addressBean.getState()==1){
                            if (addressBean.getData().size()>0){
                                for (int i = 0; i < addressBean.getData().size(); i++) {
                                    arrayList.add(addressBean);
                                }
                                mListView.setAdapter(adapter);
                                mListView.zhanshi(false);
                            }
                        }



                    }
                });

        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
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
