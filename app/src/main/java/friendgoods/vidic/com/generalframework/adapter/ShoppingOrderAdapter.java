package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.logging.Handler;

import friendgoods.vidic.com.generalframework.Bean.ShoppingOrderBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.faxian.address.ShopOrderMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

public class ShoppingOrderAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ShoppingOrderBean> arrayList;
    public ShoppingOrderAdapter(Context context,ArrayList<ShoppingOrderBean> arrayList){
        this.context  = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.shopping_order_items,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder)view.getTag();
        final ShoppingOrderBean shoppingOrderBean = arrayList.get(i);
        Glide.with(context)
                .load(Api.ossurl+shoppingOrderBean.getData().getPageInfo().getList().get(i).getPhoto())
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img_goods);
        viewHolder.tv_time.setText(shoppingOrderBean.getData().getPageInfo().getList().get(i).getCreateTime()+"");
        // 0未发货，1已发货，2交易完成
        if (shoppingOrderBean.getData().getPageInfo().getList().get(i).getFlag1().equals("0")){
            viewHolder.tv_status.setText("未发货");
            viewHolder.btn_queren.setVisibility(View.VISIBLE);
        }else   if (shoppingOrderBean.getData().getPageInfo().getList().get(i).getFlag1().equals("1")){
            viewHolder.tv_status.setText("已发货");
            viewHolder.btn_queren.setVisibility(View.VISIBLE);
        }else  if (shoppingOrderBean.getData().getPageInfo().getList().get(i).getFlag1().equals("2")){
            viewHolder.tv_status.setText("交易完成");
            viewHolder.btn_queren.setVisibility(View.GONE);
        }
        viewHolder.tv_name.setText(shoppingOrderBean.getData().getPageInfo().getList().get(i).getVoucher_shop_name());
        viewHolder.tv_jifen.setText(shoppingOrderBean.getData().getPageInfo().getList().get(i).getVoucher_shop_voucher()+"积分");
        viewHolder.tv_taocan.setText(shoppingOrderBean.getData().getPageInfo().getList().get(i).getVoucher_shop_dimensions());
        viewHolder.tv_fenshu.setText(shoppingOrderBean.getData().getPageInfo().getList().get(i).getShop_num());
        viewHolder.tv_zongfenshu.setText("共"+shoppingOrderBean.getData().getPageInfo().getList().get(i).getShop_num()+"件商品");
        viewHolder.tv_zongjifen.setText("合计："+shoppingOrderBean.getData().getPageInfo().getList().get(i).getTotal_shop_voucher()+"积分");
        //跳转
        viewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ShopOrderMoreActivity.class);
                intent.putExtra("id",shoppingOrderBean.getData().getPageInfo().getList().get(i).getId()+"");
                context.startActivity(intent);
            }
        });
        viewHolder.btn_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.post(Api.querenshouhuo)
                        .tag(this)
                        .params("uuid",Userid)
                        .params("id",shoppingOrderBean.getData().getPageInfo().getList().get(i).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Gson gson = new Gson();
                                WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                                if (wechatBindBean.getState()==1){
                                    ToastUtils.shortToast("确认收货成功");
                                    viewHolder.btn_queren.setVisibility(View.GONE);
                                }else {
                                    viewHolder.btn_queren.setVisibility(View.VISIBLE);
                                    ToastUtils.shortToast(wechatBindBean.getMessage());
                                }
                            }
                        });
            }
        });

        return view;
    }
    public static class ViewHolder{
        TextView tv_time;
        TextView tv_status;
        TextView tv_name;
        TextView tv_jifen;
        TextView tv_taocan;
        TextView tv_fenshu;
        TextView tv_zongfenshu;
        TextView tv_zongjifen;
        ImageView img_goods;
        Button btn_more;
        Button btn_queren;
        public ViewHolder(View view){
            tv_time = (TextView) view.findViewById(R.id.shoporder_time);
            tv_status = (TextView) view.findViewById(R.id.shoporder_status);
            tv_name = (TextView) view.findViewById(R.id.shoporder_name);
            tv_jifen = (TextView)view.findViewById(R.id.shoporder_jifen);
            tv_taocan = (TextView) view.findViewById(R.id.shoporder_taocan);
            tv_fenshu = (TextView)view.findViewById(R.id.shoporder_fenshu);
            tv_zongfenshu = (TextView)view.findViewById(R.id.shoporder_zongfenshu);
            tv_zongjifen = (TextView)view.findViewById(R.id.shoporder_zongjifen);
            img_goods = (ImageView)view.findViewById(R.id.shoporder_img);
            btn_more = (Button)view.findViewById(R.id.shoporder_more);
            btn_queren = (Button)view.findViewById(R.id.shoporder_queren);
        }
    }
}
