package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.MyOrderBean;
import friendgoods.vidic.com.generalframework.Bean.MyOrderStateBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.OrderMoreActivity;
import friendgoods.vidic.com.generalframework.activity.wode.PayjifenOrderOkActivity;
import friendgoods.vidic.com.generalframework.faxian.PayjifenActivity;
import friendgoods.vidic.com.generalframework.util.Api;

public class MyOrderStateAdapter extends BaseAdapter {
    ArrayList<MyOrderStateBean> arrayList;
    Context context;
    //用于判断状态用
    int a;
    public MyOrderStateAdapter(Context context, ArrayList<MyOrderStateBean> arrayList){
            this.context = context ;
        this.arrayList =arrayList;
        this.a = a;
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
        ViewHolder v;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.templateorder,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder)view.getTag();
        final MyOrderStateBean mingxiBean = arrayList.get(i);
        //先截取需要的网络图片第一张
        if (TextUtils.isEmpty(mingxiBean.getData().getPageInfo().getList().get(i).getItems_photo1())){

        }else{
            String shwoimgurl = Api.ossurl+mingxiBean.getData().getPageInfo().getList().get(i).getItems_photo1();
            Glide.with(context)
                    .load(shwoimgurl)
                    .placeholder(R.mipmap.projectshow)
                    .error(R.mipmap.projectshow)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(v.img);
        }
        v.tv_fangan.setText(mingxiBean.getData().getPageInfo().getList().get(i).getItems_scheme_name());
        if (TextUtils.isEmpty(mingxiBean.getData().getPageInfo().getList().get(i).getItems_name())){
            v.tv_objname.setText("");
        }else{
            if (mingxiBean.getData().getPageInfo().getList().get(i).getItems_name().length()>6){
                v.tv_objname.setText(mingxiBean.getData().getPageInfo().getList().get(i).getItems_name().substring(0,6)+"..");
            }else{
                v.tv_objname.setText(mingxiBean.getData().getPageInfo().getList().get(i).getItems_name());
            }
        }
        if (mingxiBean.getData().getPageInfo().getList().get(i).getStatus().equals("1")){
            v.tv_dingdanstate.setText("待支付");
            v.tv_orderpay.setVisibility(View.VISIBLE);
        }else  if (mingxiBean.getData().getPageInfo().getList().get(i).getStatus().equals("2")){
            v.tv_dingdanstate.setText("已支付");
            v.tv_orderpay.setVisibility(View.GONE);
        }else  if (mingxiBean.getData().getPageInfo().getList().get(i).getStatus().equals("3")){
            v.tv_dingdanstate.setText("已失效");
            v.tv_orderpay.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(mingxiBean.getData().getPageInfo().getList().get(i).getItems_status())){

        }else {
            if (mingxiBean.getData().getPageInfo().getList().get(i).getItems_status().equals("1")){
                v.tv_objstate.setText("预告中");
            }else  if (mingxiBean.getData().getPageInfo().getList().get(i).getItems_status().equals("2")){
                v.tv_objstate.setText("预约中");
            }else  if (mingxiBean.getData().getPageInfo().getList().get(i).getItems_status().equals("3")){
                v.tv_objstate.setText("认购中");
            }else  if (mingxiBean.getData().getPageInfo().getList().get(i).getItems_status().equals("4")){
                v.tv_objstate.setText("已完成");
            }
        }

        if (TextUtils.isEmpty(mingxiBean.getData().getPageInfo().getList().get(i).getFunding_start_money())){
            v.tv_danjia.setText("");
        }else {
            v.tv_danjia.setText(mingxiBean.getData().getPageInfo().getList().get(i).getFunding_start_money()+"元");
        }

        if (TextUtils.isEmpty(mingxiBean.getData().getPageInfo().getList().get(i).getBuy_num())){
            v.tv_fenshu.setText("");
        }else {
            v.tv_fenshu.setText(mingxiBean.getData().getPageInfo().getList().get(i).getBuy_num()+"份");
        }


        //点击订单详情
        v.tv_ordermore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("值1",mingxiBean.getData().getPageInfo().getList().get(i).getID()+"");
                Intent intent  = new Intent(context,OrderMoreActivity.class);
                intent.putExtra("orderid",mingxiBean.getData().getPageInfo().getList().get(i).getNumber());
                context.startActivity(intent);
            }
        });
        //点击支付
        v.tv_orderpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context,PayjifenOrderOkActivity.class);
                intent.putExtra("money",(Integer.parseInt(mingxiBean.getData().getPageInfo().getList().get(i).getBuy_num())*Integer.parseInt(mingxiBean.getData().getPageInfo().getList().get(i).getFunding_start_money()))+"");
                context.startActivity(intent);
            }
        });


        return view;
    }
    public static class ViewHolder{
        ImageView img;
        TextView tv_objname;
        TextView tv_fangan;
        TextView tv_dingdanstate;
        TextView tv_objstate;
        TextView tv_danjia;
        TextView tv_fenshu;
        TextView tv_ordermore;
        TextView tv_orderpay;

        public ViewHolder(View view){
            img = view.findViewById(R.id.myorder_img);
            tv_objname = view.findViewById(R.id.myorder_objname);
            tv_fangan = view.findViewById(R.id.myorder_fangan);
            tv_dingdanstate = view.findViewById(R.id.myorder_dingdansate);
            tv_objstate = view.findViewById(R.id.myorder_objstate);
            tv_danjia = view.findViewById(R.id.myorder_danjia);
            tv_fenshu = view.findViewById(R.id.myorder_fenshu);
            tv_ordermore = view.findViewById(R.id.order_more);
            tv_orderpay = view.findViewById(R.id.order_pay);
        }
    }
}
