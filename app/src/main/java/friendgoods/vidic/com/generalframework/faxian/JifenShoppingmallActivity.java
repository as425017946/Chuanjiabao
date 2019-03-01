package friendgoods.vidic.com.generalframework.faxian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.JifenShopTopImgBean;
import friendgoods.vidic.com.generalframework.Bean.JifenShowBean;
import friendgoods.vidic.com.generalframework.Bean.ShowMinejifenBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.JifenShopAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 积分商城
 */
public class JifenShoppingmallActivity extends BaseActivity {
    private static final String TAG = "JifenShoppingmallActivi";
    @BindView(R.id.showtitle)
    TextView tv_showtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_shoppingmall);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        adapter = new JifenShopAdapter(JifenShoppingmallActivity.this,arrayList);
        listview.setLayoutManager(new GridLayoutManager(this,2));
        listview.setAdapter(adapter);
//        setMore();
        setOrder();
        showtopimg();
        showinfo();
    }

    /**
     * 展示信息
     */
    JifenShopAdapter adapter;
    ArrayList<JifenShowBean> arrayList = new ArrayList<>();
    @BindView(R.id.jifen_shop_list)
    RecyclerView listview;
    private void showinfo() {
        OkGo.post(Api.jifenshopss)
                .tag(this)
                .params("page",1)
                .params("pageSize",1000)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e(TAG, "onSuccess: "+s );
                        Gson gson = new Gson();
                        JifenShowBean showBean = gson.fromJson(s,JifenShowBean.class);
                        if (showBean.getState()==1){
                           if (showBean.getData().getPageInfo().getList().size()>0){
                               tv_showtitle.setVisibility(View.GONE);
                               listview.setVisibility(View.VISIBLE);
                               for (int i = 0; i < showBean.getData().getPageInfo().getList().size() ; i++) {
                                   arrayList.add(showBean);
//                                    Log.e(TAG, "onSuccess: 循环"+i );
                               }
                               adapter.notifyDataSetChanged();
                           }else {
                               tv_showtitle.setVisibility(View.VISIBLE);
                               listview.setVisibility(View.GONE);
                           }
                        }else{
                            ToastUtils.shortToast(showBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 展示顶部图片
     */
    @BindView(R.id.top_img_show)
    ImageView img_top;
    private void showtopimg() {
        OkGo.post(Api.jifenshoptopimg)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        JifenShopTopImgBean topImgBean = gson.fromJson(s,JifenShopTopImgBean.class);
                        if (topImgBean.getState()==1){
                            if (topImgBean.getData()!=null){
                                Glide.with(JifenShoppingmallActivity.this)
                                        .load(Api.ossurl+topImgBean.getData().getPhoto())
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img_top);
                            }else {
                                Glide.with(JifenShoppingmallActivity.this)
                                        .load(Api.ossurl)
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img_top);
                            }
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
        ttitle.setText("积分商城");
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
                JifenShoppingmallActivity.this.finish();
            }
        });
    }
    /**
     * 我的积分
     * 订单
     */
    @BindView(R.id.faxian_shopping_myjifen)
    LinearLayout ljifen;
    @BindView(R.id.faxian_shopping_order)
    LinearLayout lorder;
    @BindView(R.id.jifenshop_minejifen)
    TextView tv_minejifen;
    private void setOrder(){
        OkGo.post(Api.myjifen)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShowMinejifenBean showMinejifenBean = gson.fromJson(s,ShowMinejifenBean.class);
                        if (showMinejifenBean.getState()==1){
                            if (showMinejifenBean.getData()!=null){
                                tv_minejifen.setText("积分 "+showMinejifenBean.getData().getIntegral());
                            }
                        }
                    }
                });
        ljifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JifenShoppingmallActivity.this,MyJifenActivity.class);
                startActivity(intent);
            }
        });
        lorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JifenShoppingmallActivity.this,ShoppingOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setOrder();
    }
}
