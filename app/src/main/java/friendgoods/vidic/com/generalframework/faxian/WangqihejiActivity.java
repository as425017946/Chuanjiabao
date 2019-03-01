package friendgoods.vidic.com.generalframework.faxian;

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
import friendgoods.vidic.com.generalframework.faxian.fragment.HejiAllFragment;
import friendgoods.vidic.com.generalframework.faxian.fragment.HejiAllJingxuanFragment;
import friendgoods.vidic.com.generalframework.faxian.fragment.HejiChoujiFirstFragment;
import friendgoods.vidic.com.generalframework.faxian.fragment.HejiRenqiFirstFragment;
import friendgoods.vidic.com.generalframework.touzi.TouziAdapter;

public class WangqihejiActivity extends BaseActivity {

    @BindView(R.id.faxian_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.faxian_viewpager)
    ViewPager mViewpager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangqiheji);
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
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("往期合辑");
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
                WangqihejiActivity.this.finish();
            }
        });
    }
    /**
     * 写入4个fragment
     */
    private void setmFragments(){
        HejiAllFragment heji = new HejiAllFragment();
        HejiAllJingxuanFragment hejiall = new HejiAllJingxuanFragment();
        HejiChoujiFirstFragment hejifirst = new HejiChoujiFirstFragment();
        HejiRenqiFirstFragment hejirenqi = new HejiRenqiFirstFragment();
        mFragments.add(heji);
        mFragments.add(hejiall);
        mFragments.add(hejifirst);
        mFragments.add(hejirenqi);
        list.add("全部");
        list.add("筹集最快");
        list.add("人气爆款");
        list.add("全球精选");
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
