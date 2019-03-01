package friendgoods.vidic.com.generalframework.faxian.address;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.ShopOrderMoreBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.faxian.ShoppingOrderActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 商城订单详情
 */
public class ShopOrderMoreActivity extends AppCompatActivity {
    private static final String TAG = "ShopOrderMoreActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_order_more);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        showinfo();
    }

    /**
     * 展示信息
     */
    TextView tv_time;
    TextView tv_status;
    TextView tv_name;
    TextView tv_jifen;
    TextView tv_taocan;
    TextView tv_fenshu;
    TextView tv_zongfenshu;
    TextView tv_zongjifen;
    ImageView img_goods;
    @BindView(R.id.shop_order_more_bianhao)
    TextView tv_bianhao;
    @BindView(R.id.shop_order_more_creattime)
    TextView tv_creatime;
    @BindView(R.id.shop_order_more_fahuotime)
    TextView tv_fahuotime;
    @BindView(R.id.shop_order_more_chengjiaotime)
    TextView tv_chengjiaotime;
    @BindView(R.id.shop_order_more_address)
    TextView tv_address;
    private void showinfo() {
        tv_time = (TextView) findViewById(R.id.shoporder_time);
        tv_status = (TextView)findViewById(R.id.shoporder_status);
        tv_name = (TextView) findViewById(R.id.shoporder_name);
        tv_jifen = (TextView)findViewById(R.id.shoporder_jifen);
        tv_taocan = (TextView) findViewById(R.id.shoporder_taocan);
        tv_fenshu = (TextView)findViewById(R.id.shoporder_fenshu);
        tv_zongfenshu = (TextView)findViewById(R.id.shoporder_zongfenshu);
        tv_zongjifen = (TextView)findViewById(R.id.shoporder_zongjifen);
        img_goods = (ImageView)findViewById(R.id.shoporder_img);
        OkGo.post(Api.shopOrderMore)
                .tag(this)
                .params("uuid",Userid)
                .params("id",getIntent().getStringExtra("id"))
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
                        ShopOrderMoreBean shopOrderMoreBean = gson.fromJson(s,ShopOrderMoreBean.class);
                        if (shopOrderMoreBean.getState()==1){
                            if (shopOrderMoreBean.getData()!=null){
                                Glide.with(ShopOrderMoreActivity.this)
                                        .load(Api.ossurl+shopOrderMoreBean.getData().getPhoto())
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img_goods);
                                tv_time.setText(shopOrderMoreBean.getData().getCreation_time()+"");
                                // 0未发货，1已发货，2交易完成
                                if (shopOrderMoreBean.getData().getFlag1().equals("0")){
                                    tv_status.setText("未发货");
                                }else   if (shopOrderMoreBean.getData().getFlag1().equals("1")){
                                    tv_status.setText("已发货");
                                }else  if (shopOrderMoreBean.getData().getFlag1().equals("2")){
                                    tv_status.setText("交易完成");
                                }
                                tv_name.setText(shopOrderMoreBean.getData().getVoucher_shop_name());
                                tv_jifen.setText(shopOrderMoreBean.getData().getVoucher_shop_voucher()+"积分");
                                tv_taocan.setText(shopOrderMoreBean.getData().getVoucher_shop_dimensions());
                                tv_fenshu.setText(shopOrderMoreBean.getData().getShop_num());
                                tv_zongfenshu.setText("共"+shopOrderMoreBean.getData().getShop_num()+"件商品");
                                tv_zongjifen.setText("合计："+shopOrderMoreBean.getData().getTotal_shop_voucher()+"积分");
                                tv_bianhao.setText(shopOrderMoreBean.getData().getNumber()+"");
                                tv_creatime.setText(shopOrderMoreBean.getData().getCreation_time()+"");
                                tv_fahuotime.setText(shopOrderMoreBean.getData().getHair_time());
                                tv_address.setText(shopOrderMoreBean.getData().getArea_diqu()+shopOrderMoreBean.getData().getAdd_dizhi());
                            }
                        }else {
                            ToastUtils.shortToast(shopOrderMoreBean.getMessage());
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
        ttitle.setText("订单详情");
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
                ShopOrderMoreActivity.this.finish();
            }
        });
    }
}
