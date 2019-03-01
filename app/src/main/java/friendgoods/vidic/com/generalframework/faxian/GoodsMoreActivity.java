package friendgoods.vidic.com.generalframework.faxian;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.GoosMoreBean;
import friendgoods.vidic.com.generalframework.MainActivity;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 详情
 */
public class GoodsMoreActivity extends BaseActivity {
    private static final String TAG = "GoodsMoreActivity";
    SharedPreferencesHelper sharedPreferencesHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_more);
        ButterKnife.bind(this);
        MyApplication.getApplication().addActivity(GoodsMoreActivity.this);
        sharedPreferencesHelper = new SharedPreferencesHelper(GoodsMoreActivity.this,"chuanjiabao");
        sharedPreferencesHelper.put("selectid","-1");
        setTtitle();
        fanhui();
        showinfo();
    }

    /**
     * 展示信息
     */
    @BindView(R.id.goodsmore_img1)
    ImageView img1;
    @BindView(R.id.goodsmore_name)
    TextView tv_name;
    @BindView(R.id.goodsmore_jifen)
    TextView tv_jifen;
    @BindView(R.id.goodsmore_info)
    ImageView img2;
    @BindView(R.id.goodsmore_fanhui)
    LinearLayout ll_fanhui;
    @BindView(R.id.goodsmore_weixin)
    LinearLayout ll_weixin;
    @BindView(R.id.goodsmore_duihuan)
    Button btn_duihuan;
    @BindView(R.id.goodsmore_guige)
    LinearLayout ll_guige;
    @BindView(R.id.goodsmore_guigeshu)
    TextView tv_guigeshu;
    private String goodsimg;
    public static String jifens;
    String[] showjifen,taocan;
    private void showinfo() {
        OkGo.post(Api.goodsmore)
                .tag(this)
                .params("number",getIntent().getStringExtra("number"))
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: "+s );
                        Gson gson = new Gson();
                        GoosMoreBean goosMoreBean = gson.fromJson(s,GoosMoreBean.class);
                        if (goosMoreBean.getState()==1){
                            if (goosMoreBean.getData()!=null){
                                Glide.with(GoodsMoreActivity.this)
                                        .load(Api.ossurl+goosMoreBean.getData().getPhoto())
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img1);
                                //保存商品照片
                                sharedPreferencesHelper.put("goodsimgs",Api.ossurl+goosMoreBean.getData().getPhoto());
                                //保存编码
                                sharedPreferencesHelper.put("numbers",getIntent().getStringExtra("number"));

                                goodsimg = Api.ossurl+goosMoreBean.getData().getPhoto();
                                tv_name.setText(goosMoreBean.getData().getName());
                                //保存商品名字
                                sharedPreferencesHelper.put("goodsname",tv_name.getText().toString());
//                                names = goosMoreBean.getData().getName();
                                if (goosMoreBean.getData().getVoucher().contains(",")){
                                    showjifen = goosMoreBean.getData().getVoucher().split(",");
                                    tv_jifen.setText(showjifen[0]+"积分");


                                }else {
                                    tv_jifen.setText(goosMoreBean.getData().getVoucher()+"积分");
                                }
                                if (goosMoreBean.getData().getDimensions().contains(",")){
                                    taocan = goosMoreBean.getData().getDimensions().split(",");
                                }else {

                                }
                                Glide.with(GoodsMoreActivity.this)
                                        .load(Api.ossurl+goosMoreBean.getData().getParticulars())
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img2);


                            }
                        }else {
                            ToastUtils.shortToast(goosMoreBean.getMessage());
                        }

                    }
                });

        //返回按钮
        ll_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsMoreActivity.this.finish();
            }
        });
        //微信客服
        ll_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(GoodsMoreActivity.this);
                LayoutInflater inflater = LayoutInflater.from(GoodsMoreActivity.this);
                final View DialogView = inflater .inflate (R.layout.jinqun, null);//1、自定义布局
                final Button btnok = (Button)DialogView.findViewById(R.id.jinqun_btn);
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();

                final ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipData clipData = ClipData.newPlainText("copy from demo", "123456");
                        mClipboardManager.setPrimaryClip(clipData);
                        ToastUtils.shortToast("复制成功！");
                        dialog.dismiss();
                    }
                });
            }
        });
        //立即兑换
        btn_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_guigeshu.getText().toString().equals("选择商品的规格和数量")){
                    ToastUtils.shortToast("请选择套餐");
                }else {
                    //跳转页面后也要清空所保存的
                    sharedPreferencesHelper.remove("selectid");
                    sharedPreferencesHelper.remove("selecttaocan");
                    sharedPreferencesHelper.remove("selectjifen");
                    Intent intent = new Intent(GoodsMoreActivity.this,JifenPayOrderActivity.class);
//                    intent.putExtra("goodsimg",goodsimg);
//                    intent.putExtra("goodsname",names);
//                    intent.putExtra("goodsjifen",jifens);
//                    intent.putExtra("taocanname",taocanname);
//                    intent.putExtra("taocanfenshu",taocanfenshu);
//                    intent.putExtra("number",getIntent().getStringExtra("number"));
                    startActivity(intent);
                }
            }
        });
        //选择商品规格
        ll_guige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopwindow();
            }
        });
    }

    /**
     * 显示popupWindow
     */
    int fenshu=0;
//    int showcorlor = 0xFFf05151;
    int showcorlor2 = 0xFF333333; //字体未选中颜色
    int showcorlor3 = 0xFFffffff; //选中字体颜色

    @SuppressLint("ResourceAsColor")
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindowlayout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(GoodsMoreActivity.this.findViewById(R.id.goodsmore_guige), Gravity.BOTTOM, 0, 0);

        // 这里检验popWindow里的button是否可以点击
        Button tijiao = (Button) view.findViewById(R.id.pop_tijiao);
        ImageView pop_img = (ImageView)view.findViewById(R.id.pop_img);
        final TextView pop_tvjifen = (TextView)view.findViewById(R.id.pop_jifen);
        final TextView pop_tvxuanze = (TextView)view.findViewById(R.id.pop_xuanze);
//        final LinearLayout pop_linearlayou = (LinearLayout)view.findViewById(R.id.pop_linearlayout);
        final RadioGroup pop_radioGroup = (RadioGroup)view.findViewById(R.id.pop_group);
        final ImageView img_jian = (ImageView)view.findViewById(R.id.sanjirengou_jian);
        final ImageView img_jia = (ImageView)view.findViewById(R.id.sanjirengou_jia);
        final TextView tv_paynum = (TextView)view.findViewById(R.id.sanjirengou_payfenshu);

        //减号操作
        img_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fenshu==0){
                    tv_paynum.setText("0");
                }else {
                    fenshu--;
                    tv_paynum.setText(fenshu+"");
                }
            }
        });

        //加号操作
        img_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fenshu++;
                tv_paynum.setText(fenshu+"");
            }
        });

        //加载信息
        pop_tvxuanze.setText("已选择：");
        Glide.with(GoodsMoreActivity.this)
                .load(goodsimg)
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(pop_img);
        //动态添加信息

        if (taocan.length>0){
            if (showjifen.length>1){
                pop_tvjifen.setText(showjifen[0]+"~"+showjifen[showjifen.length-1]+"积分");
            }
            for (int i = 0; i <taocan.length ; i++) {
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 15, 0, 15);//设置边距
                RadioButton  button = new RadioButton(this);

                Bitmap a=null;
                button.setButtonDrawable(new BitmapDrawable(a));

                button.setTextSize(16);
                button.setText(taocan[i]);
                if (i==Integer.parseInt(sharedPreferencesHelper.getSharedPreference("selectid","").toString())-1){
                    tv_paynum.setText(""+fenshu);
                    button.setTextColor(showcorlor3);
                    button.setBackgroundResource(R.drawable.fillettextselect);
                    pop_radioGroup.addView(button,lp);
                    pop_tvjifen.setText(sharedPreferencesHelper.getSharedPreference("selectjifen","").toString()+"积分");
                    pop_tvxuanze.setText("已选择："+sharedPreferencesHelper.getSharedPreference("selecttaocan","").toString());

                }else {
                    button.setTextColor(showcorlor2);
                    button.setBackgroundResource(R.drawable.fillettext);
                    pop_radioGroup.addView(button,lp);
                }
            }
        }
        pop_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int b) {
                int childCount = radioGroup.getChildCount();
                RadioButton childAt = null;
                //将所有radioButton背景色置为未选中状态,目的是清除之前的设置
                for (int a = 0; a < childCount; a++) {
                    childAt = (RadioButton) radioGroup.getChildAt(a);
                    childAt.setTextColor(showcorlor2);
                    childAt.setBackgroundResource(R.drawable.fillettext);
                }
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(b);
                radioButton.setTextColor(showcorlor3);
                radioButton.setBackgroundResource(R.drawable.fillettextselect);
//                Log.e("点击了余数",b%childCount+"*");
//                Log.e("点击了商数",b/childCount+"*");
//                Log.e("点击了--",childCount+"*");

                //点击后选择并展示名字和积分
                //如果取余数为0说明又循环了一次了，所以要直接取子控件数量-1就好，反之取余
                if (b%childCount==0){
                    //保存点击的按钮，然后再次点击后展示上一次点击的效果
                    sharedPreferencesHelper.put("selectid",childCount+"");
                    sharedPreferencesHelper.put("selecttaocan",radioButton.getText().toString());
                    sharedPreferencesHelper.put("selectjifen",showjifen[childCount-1]);
                    pop_tvjifen.setText(showjifen[childCount-1]+"积分");
                    //                        保存数据用于下一页面使用
                    sharedPreferencesHelper.put("goodsjifen",showjifen[childCount-1]);
                    sharedPreferencesHelper.put("taocanname",radioButton.getText().toString());
                }else {
                    //保存点击的按钮，然后再次点击后展示上一次点击的效果
                    sharedPreferencesHelper.put("selectid",b%childCount+"");
                    sharedPreferencesHelper.put("selecttaocan",radioButton.getText().toString());
                    sharedPreferencesHelper.put("selectjifen",showjifen[b%childCount-1]);
                    pop_tvjifen.setText(showjifen[b%childCount-1]+"积分");
                    //                        保存数据用于下一页面使用
                    sharedPreferencesHelper.put("goodsjifen",showjifen[b%childCount-1]);
                    sharedPreferencesHelper.put("taocanname",radioButton.getText().toString());
                }
                pop_tvxuanze.setText("已选择："+radioButton.getText().toString());

            }
        });


        tijiao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (fenshu==0){
                    ToastUtils.shortToast("请选择份数");
                }else if(pop_tvxuanze.getText().toString().equals("已选择：")){
                    ToastUtils.shortToast("请选择套餐");
                }else {
                    window.dismiss();
                    sharedPreferencesHelper.put("taocanfenshu",fenshu+"");
                    tv_guigeshu.setText(pop_tvxuanze.getText().toString()+"/"+fenshu+"份");
                }

//                ToastUtils.shortToast("份数"+fenshu+"套餐类型"+pop_tvxuanze.getText().toString()
//                +"套餐积分"+pop_tvjifen.getText().toString());
            }
        });

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
//                System.out.println("popWindow消失");
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
        ttitle.setText("商品详情");
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
                //返回上一页面就要把点击存储的效果去掉
                sharedPreferencesHelper.remove("selectid");
                sharedPreferencesHelper.remove("selecttaocan");
                sharedPreferencesHelper.remove("selectjifen");
                GoodsMoreActivity.this.finish();
            }
        });
    }
}
