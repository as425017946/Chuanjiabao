package friendgoods.vidic.com.generalframework.faxian;

import android.content.Intent;
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
import friendgoods.vidic.com.generalframework.Bean.ShujufenxiBean;
import friendgoods.vidic.com.generalframework.Bean.ShujupilouDayBean;
import friendgoods.vidic.com.generalframework.Bean.ShujupilouZonginfoBean;
import friendgoods.vidic.com.generalframework.Bean.ShujupilouZongjineBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.fragment.WebViewActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 数据披露
 */
public class ShujupilouActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shujupilou);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setBaogao();
        setinfo();
        showimg();
    }

    /**
     * 展示图片
     */
    @BindView(R.id.shujufenxi_top)
    ImageView img_top;
    @BindView(R.id.shujufenxi_bottom)
    ImageView img_bottom;
    private void showimg() {
        OkGo.post(Api.topphoto)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        final ShujufenxiBean shujufenxiBean = gson.fromJson(s,ShujufenxiBean.class);
                        if (shujufenxiBean.getState() ==1){
                            if (shujufenxiBean.getData()!=null){
                                Glide.with(ShujupilouActivity.this)
                                        .load(Api.ossurl+shujufenxiBean.getData().getPhoto())
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img_top);
                                if (TextUtils.isEmpty(shujufenxiBean.getData().getUrl())){

                                }else {
                                    img_top.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                              Intent intent = new Intent(ShujupilouActivity.this,WebViewActivity.class);
                                              intent.putExtra("detailUrl",shujufenxiBean.getData().getUrl());
                                              startActivity(intent);
                                        }
                                    });
                                }
                            }else {
                                ToastUtils.shortToast(shujufenxiBean.getMessage());
                            }
                        }

                    }
                });
        OkGo.post(Api.bottomphoto)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        final ShujufenxiBean shujufenxiBean = gson.fromJson(s,ShujufenxiBean.class);
                        if (shujufenxiBean.getState() ==1){
                            if (shujufenxiBean.getData()!=null){
                                Glide.with(ShujupilouActivity.this)
                                        .load(Api.ossurl+shujufenxiBean.getData().getPhoto())
                                        .placeholder(R.mipmap.projectshow)
                                        .error(R.mipmap.projectshow)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .into(img_bottom);
                                if (TextUtils.isEmpty(shujufenxiBean.getData().getUrl())){

                                }else {
                                    img_bottom.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ShujupilouActivity.this,WebViewActivity.class);
                                            intent.putExtra("detailUrl",shujufenxiBean.getData().getUrl());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }else {
                                ToastUtils.shortToast(shujufenxiBean.getMessage());
                            }
                        }

                    }
                });
    }

    /**
     * 写入信息
     */
    @BindView(R.id.shuju_time)
    TextView tv_time;
    @BindView(R.id.shuju_touziren)
    TextView tv_touziren;
    @BindView(R.id.shuju_chengjiaojine)
    TextView tv_chengjiao;
    @BindView(R.id.shuju_zonge)
    TextView tv_zonge;
    @BindView(R.id.shuju_obj)
    TextView tv_obj;
    @BindView(R.id.shuju_address)
    TextView tv_address;

    private void setinfo() {
        //历时天数
        OkGo.post(Api.shujupilouday)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("历时天数",s);
                        Gson gson = new Gson();
                        ShujupilouDayBean dayBean = gson.fromJson(s,ShujupilouDayBean.class);
                        if (dayBean.getState()==1){
                            if (dayBean.getData()!=null){
                                if (dayBean.getData().getTime()==null){
                                    tv_time.setText("");
                                }else {
                                    tv_time.setText(dayBean.getData().getTime()+"天");
                                }
                            }else {
                                tv_time.setText("");
                            }

                        }
                    }
                });
        //总投资人、成交金额
        OkGo.post(Api.shujupiloutouziren)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShujupilouZongjineBean zongjineBean = gson.fromJson(s,ShujupilouZongjineBean.class);
                        if (zongjineBean.getState()==1){
                            tv_touziren.setText(zongjineBean.getData().getUserNum()+"位");
                            if (zongjineBean.getData().getSumMoney()>9999){
                                double wan = zongjineBean.getData().getSumMoney()/10000;
                                tv_chengjiao.setText(wan+"万元");
                            }else if (zongjineBean.getData().getSumMoney()>99999999){
                                double yi = zongjineBean.getData().getSumMoney()/100000000;
                                tv_chengjiao.setText(yi+"亿元");
                            }else if (zongjineBean.getData().getSumMoney()<9999){
                                tv_chengjiao.setText(zongjineBean.getData().getSumMoney()+"元");
                            }
                        }
                    }
                });
        //总投资项目、总众筹目的地、总投资金额
        OkGo.post(Api.shujupilouzongxinxi)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShujupilouZonginfoBean zonginfoBean = gson.fromJson(s,ShujupilouZonginfoBean.class);
                        if (zonginfoBean.getState()==1){

                            if (zonginfoBean.getData().getMoney()>9999){
                                double wan = zonginfoBean.getData().getMoney()/10000;
                                tv_zonge.setText(wan+"万元");
                            }else if (zonginfoBean.getData().getMoney()>99999999){
                                double yi = zonginfoBean.getData().getMoney()/100000000;
                                tv_zonge.setText(yi+"亿元");
                            }else if (zonginfoBean.getData().getMoney()<9999){
                                tv_zonge.setText(zonginfoBean.getData().getMoney()+"元");
                            }


                            tv_obj.setText(zonginfoBean.getData().getNumber()+"个");
                            tv_address.setText(zonginfoBean.getData().getSiteNumber()+"个");
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
        ttitle.setText("数据披露");
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
                ShujupilouActivity.this.finish();
            }
        });
    }

    /**
     * 运行报告
     */
    @BindView(R.id.faxian_baogao)
    LinearLayout lbaogao;
    private void setBaogao(){
        lbaogao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShujupilouActivity.this,ShujupilouMoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
