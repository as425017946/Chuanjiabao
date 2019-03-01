package friendgoods.vidic.com.generalframework.activity.wode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.OrderMoreinfoBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

public class OrderMoreActivity extends BaseActivity {

    private String orderid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_more);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        orderid = getIntent().getStringExtra("orderid");
        Log.e("值",orderid);
        setinfo();

    }

    /**
     * 写入信息
     */
    @BindView(R.id.ordermore_img)
    ImageView img;
    @BindView(R.id.ordermore_objname)
    TextView tv_objname;
    @BindView(R.id.ordermore_fangan)
    TextView tv_fangan;
    @BindView(R.id.ordermore_dingdanstate)
    TextView tv_dingdanstate;
    @BindView(R.id.ordermore_objstate)
    TextView tv_objstate;
    @BindView(R.id.ordermore_danjia)
    TextView tv_danjia;
    @BindView(R.id.ordermore_fenshu)
    TextView tv_fenshu;
    @BindView(R.id.ordermore_dingdanbianhao)
    TextView tv_bianhao;
    @BindView(R.id.ordermore_time)
    TextView tv_time;
    private void setinfo() {
        OkGo.post(Api.Ordermore)
                .tag(this)
                .params("number",orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("详情",orderid+"*"+s);
                        Gson gson = new Gson();
                        OrderMoreinfoBean moreinfoBean = gson.fromJson(s,OrderMoreinfoBean.class);
                        if (moreinfoBean.getState()==1){
                            //先截取需要的网络图片第一张
                            String[] imgurl = moreinfoBean.getData().getItems_photo1().split(",");
                            if (TextUtils.isEmpty(imgurl[0])){

                            }else{
                                String shwoimgurl = Api.ossurl+moreinfoBean.getData().getItems_photo1();
                                Glide.with(OrderMoreActivity.this)
                                        .load(shwoimgurl)
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img);
                            }
                            if (moreinfoBean.getData().getItems_name().length()>6){
                                tv_objname.setText(moreinfoBean.getData().getItems_name().substring(0,6)+"..");
                            }else {
                                tv_objname.setText(moreinfoBean.getData().getItems_name());
                            }

                            //支付状态
                            if (moreinfoBean.getData().getStatus().equals("1")){
                                tv_dingdanstate.setText("待支付");
                            }else if (moreinfoBean.getData().getStatus().equals("2")){
                                tv_dingdanstate.setText("已支付");
                            } if (moreinfoBean.getData().getStatus().equals("3")){
                                tv_dingdanstate.setText("已失效");
                            }
                            //项目状态
                            if (moreinfoBean.getData().getStatus().equals("1")){
                                tv_objstate.setText("待支付");
                            }else if (moreinfoBean.getData().getStatus().equals("2")){
                                tv_objstate.setText("已支付");
                            } if (moreinfoBean.getData().getStatus().equals("3")){
                                tv_objstate.setText("已失效");
                            }
                            tv_danjia.setText(moreinfoBean.getData().getBuy_money()+"元");
                            tv_fenshu.setText(moreinfoBean.getData().getBuy_num()+"份");
                            tv_bianhao.setText(moreinfoBean.getData().getNumber());
                            tv_time.setText(moreinfoBean.getData().getTime()+"");
                        }else{
                            ToastUtils.shortToast(moreinfoBean.getMessage());
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
                OrderMoreActivity.this.finish();
            }
        });
    }
}
