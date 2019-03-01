package friendgoods.vidic.com.generalframework.activity.wode;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BalanceActivity;
import friendgoods.vidic.com.generalframework.touzi.TouziAdapter;

/**
 * 银行卡明细
 */
public class MingxiMoreActivity extends BaseActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @BindView(R.id.mingxi_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.mingxi_viewpager)
    ViewPager mViewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingxi_more);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setmFragments();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("收支明细");
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
                MingxiMoreActivity.this.finish();
            }
        });
    }
    /**
     * 写入3个fragment
     */
    private void setmFragments(){
        MingxiZhuanruFragment zhuanruFragment = new MingxiZhuanruFragment();
        MingxiZhuanchuFragment zhuanchuFragment = new MingxiZhuanchuFragment();
        MingxiYueshouyiFragment yueshouyiFragment = new MingxiYueshouyiFragment();
        mFragments.add(zhuanruFragment);
        mFragments.add(zhuanchuFragment);
        mFragments.add(yueshouyiFragment);
        list.add("转入");
        list.add("转出");
        list.add("余额收益");
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(new TouziAdapter(getSupportFragmentManager(),mFragments,list));
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabLayout, 10, 10);
            }
        });

    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
