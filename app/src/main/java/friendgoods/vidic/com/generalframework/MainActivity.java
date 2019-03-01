package friendgoods.vidic.com.generalframework;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import friendgoods.vidic.com.generalframework.activity.LoginActivity;
import friendgoods.vidic.com.generalframework.activity.fragment.ClassificationHome;
import friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy;
import friendgoods.vidic.com.generalframework.activity.fragment.ShoppingCat;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    //定义fragment页面
    private ClassificationHome classificationHome;
    private ShoppingCat shoppingCat;
    private FragmentMy fragmentMy;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private LinearLayout secondLayout;
    private LinearLayout thirdLayout;
    private LinearLayout fourthLayout;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private TextView secondText;
    private TextView thirdText;
    private TextView fourthText;
    // 定义几个颜色
    private int whirt = 0xFFFFFFFF;  //背景色颜色
    private int gray = 0xFF636363;  //未选中颜色
    private int dark = 0xFF6CBF85;  //选中后的颜色
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    //获取登录进来的用户信息
    private SharedPreferencesHelper sharehelper;
    public static  String Userphone ,Userid,back="";

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fragmentManager = getSupportFragmentManager();
        sharehelper = new SharedPreferencesHelper(
                MainActivity.this, "chuanjiabao");
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡
    }

    @Override
    protected void onResume() {
        super.onResume();
//        back = sharehelper.getSharedPreference("back","").toString().trim();
        //取出存入的用户信息
        Userphone = sharehelper.getSharedPreference("userphone","").toString().trim();
        Userid = sharehelper.getSharedPreference("userid","").toString().trim();
        Log.e("首页登录 ",Userphone+Userid);
        if(back.equals("3")){
            setChioceItem(0);
        }
        Log.e("返回","back");
    }


    /**
     * 初始化页面
     */
    private void initView() {
        // 初始化底部导航栏的控件
        secondImage = (ImageView) findViewById(R.id.second_image);
        thirdImage = (ImageView) findViewById(R.id.third_image);
        fourthImage = (ImageView) findViewById(R.id.fourth_image);
        secondText = (TextView) findViewById(R.id.second_text);
        thirdText = (TextView) findViewById(R.id.third_text);
        fourthText = (TextView) findViewById(R.id.fourth_text);
        secondLayout = (LinearLayout) findViewById(R.id.second_layout);
        thirdLayout = (LinearLayout) findViewById(R.id.third_layout);
        fourthLayout = (LinearLayout) findViewById(R.id.fourth_layout);
        secondLayout.setOnClickListener(MainActivity.this);
        thirdLayout.setOnClickListener(MainActivity.this);
        fourthLayout.setOnClickListener(MainActivity.this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.second_layout:
                setChioceItem(0);
                break;
            case R.id.third_layout:
                setChioceItem(1);
                break;
            case R.id.fourth_layout:
                if (TextUtils.isEmpty(Userid)){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    setChioceItem(0);
                }else{
                    setChioceItem(2);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
// secondImage.setImageResource(R.drawable.XXXX);
                secondText.setTextColor(dark);
                secondImage.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.invest_unclick_b));
                if (classificationHome == null) {
                    classificationHome = new ClassificationHome();
                    fragmentTransaction.add(R.id.content, classificationHome);
                } else {
                    fragmentTransaction.show(classificationHome);
                }
                break;
            case 1:
// thirdImage.setImageResource(R.drawable.XXXX);
                thirdText.setTextColor(dark);
                thirdImage.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.find_click_b));
                if (shoppingCat == null) {
                    shoppingCat = new ShoppingCat();
                    fragmentTransaction.add(R.id.content, shoppingCat);
                } else {
                    fragmentTransaction.show(shoppingCat);
                }
                break;
            case 2:
// fourthImage.setImageResource(R.drawable.XXXX);
                fourthText.setTextColor(dark);
                fourthImage.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.my_unclick_b));
                if (fragmentMy == null) {
                    fragmentMy = new FragmentMy();
                    fragmentTransaction.add(R.id.content, fragmentMy);
                } else {
                    fragmentTransaction.show(fragmentMy);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
// secondImage.setImageResource(R.drawable.XXX);
        secondText.setTextColor(gray);
        secondLayout.setBackgroundColor(whirt);
        secondImage.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.invest_unclick_g));
// thirdImage.setImageResource(R.drawable.XXX);
        thirdText.setTextColor(gray);
        thirdLayout.setBackgroundColor(whirt);
        thirdImage.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.find_click_g));
// fourthImage.setImageResource(R.drawable.XXX);
        fourthText.setTextColor(gray);
        fourthLayout.setBackgroundColor(whirt);
        fourthImage.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.my_unclick_g));
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (classificationHome != null) {
            fragmentTransaction.hide(classificationHome);
        }
        if (shoppingCat != null) {
            fragmentTransaction.hide(shoppingCat);
        }
        if (fragmentMy != null) {
            fragmentTransaction.hide(fragmentMy);
        }
    }

    /**
     * 返回键触发的方法
     * */
    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtils.shortToast("再按一次退出程序");
            mBackKeyPressed = true;


            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);//延时两秒，如果超出则擦错第一次按键记录
        } else

        {
            this.finish();
            System.exit(0);
        }

    }

    //声明一个静态常量，用作退出BaseActivity的Tag
    public static final String EXIST = "exist";
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {//判断其他Activity启动本Activity时传递来的intent是否为空
            //获取intent中对应Tag的布尔值
            boolean isExist = intent.getBooleanExtra(EXIST, false);
            //如果为真则退出本Activity
            if (isExist) {
//                this.finish();
            }
        }
    }

}
