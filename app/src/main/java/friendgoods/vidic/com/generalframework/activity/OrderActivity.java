package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.OrderAllFragment;
import friendgoods.vidic.com.generalframework.activity.wode.OrderDaipayFragment;
import friendgoods.vidic.com.generalframework.activity.wode.OrderYipayFragment;
import friendgoods.vidic.com.generalframework.activity.wode.OrderYishixiaoFragment;
import friendgoods.vidic.com.generalframework.touzi.TouziAdapter;

/**
 * 我的订单
 */
public class OrderActivity extends BaseActivity {

    private ArrayList<android.support.v4.app.Fragment> mFragment = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setInitData();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("我的订单");
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
                OrderActivity.this.finish();
            }
        });
    }

    /**
     * 添加4个fragment页面
     */
    @BindView(R.id.order_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.order_viewpager)
    ViewPager mViewpager;
    private void setInitData(){
        OrderDaipayFragment dapay = new OrderDaipayFragment();
        OrderYipayFragment yipay = new OrderYipayFragment();
        OrderYishixiaoFragment yishixiao = new OrderYishixiaoFragment();
        OrderAllFragment all = new OrderAllFragment();
        mFragment.add(dapay);
        mFragment.add(yipay);
        mFragment.add(yishixiao);
        mFragment.add(all);
        list.add("待支付");
        list.add("已支付");
        list.add("已失效");
        list.add("全部");
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(new TouziAdapter(getSupportFragmentManager(),mFragment,list));
    }
}