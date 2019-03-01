package friendgoods.vidic.com.generalframework.touzi;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;

public class ceshi extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private ArrayList<View> mList;
    private String[] mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceshi);
        ButterKnife.bind(this);
        initData();
        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
            @Override
            public Object instantiateItem(ViewGroup container,int position){
                View view = mList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container,int position, Object object){
                container.removeView((View) object);
            }

            @Override
            public CharSequence getPageTitle(int position){
                return mTitle[position];
            }

        });

    }
    private void initData(){
        View viewpagerA = getLayoutInflater().inflate(R.layout.activity_touzinew, null);
        View viewpagerB = getLayoutInflater().inflate(R.layout.activity_touzigengduo, null);
        View viewpagerC = getLayoutInflater().inflate(R.layout.activity_touzinew, null);
        View viewpager1 = getLayoutInflater().inflate(R.layout.activity_touzinew, null);
        View viewpager2 = getLayoutInflater().inflate(R.layout.activity_touzigengduo, null);
        View viewpager3 = getLayoutInflater().inflate(R.layout.activity_touzinew, null);

        mList = new ArrayList<>();
        mList.add(viewpagerA);
        mList.add(viewpagerB);
        mList.add(viewpagerC);
        mList.add(viewpager1);
        mList.add(viewpager2);
        mList.add(viewpager3);

        mTitle = new String[]{"最新", "预告", "酒店","民宿", "公寓", "更多"};
    }
}
