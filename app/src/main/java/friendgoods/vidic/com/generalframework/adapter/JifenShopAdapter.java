package friendgoods.vidic.com.generalframework.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.gradle.logging.internal.StdErrLoggingSystem;

import java.util.ArrayList;
import java.util.List;


import friendgoods.vidic.com.generalframework.Bean.JifenShowBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.faxian.GoodsMoreActivity;
import friendgoods.vidic.com.generalframework.faxian.JifenShoppingmallActivity;
import friendgoods.vidic.com.generalframework.util.Api;


public class JifenShopAdapter extends RecyclerView.Adapter<JifenShopAdapter.ViewHolder> {
    private static final String TAG = "JifenShopAdapter";
    Context context;
    ArrayList<JifenShowBean> arrayList;
    public JifenShopAdapter(Context context,ArrayList<JifenShowBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    //② 创建ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView img_img;
        public final TextView name;
        public final TextView num;
        public final LinearLayout ll_items;
        public ViewHolder(View v) {
            super(v);
            img_img = (ImageView)v.findViewById(R.id.jifenshop_itmes_img);
            name = (TextView) v.findViewById(R.id.jifenshop_itmes_name);
            num = (TextView)v.findViewById(R.id.jifenshop_itmes_num);
            ll_items = (LinearLayout)v.findViewById(R.id.faxian_shopping_more);
        }
    }

    @NonNull
    @Override
    public JifenShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jifenshop_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JifenShopAdapter.ViewHolder holder, final int position) {
        final JifenShowBean showBean = arrayList.get(position);
        Log.e(TAG, "onBindViewHolder: "+ arrayList.size() );
        Glide.with(context)
                .load(Api.ossurl+showBean.getData().getPageInfo().getList().get(position).getPhoto())
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.img_img);
        holder.name.setText(showBean.getData().getPageInfo().getList().get(position).getName());
       if (showBean.getData().getPageInfo().getList().get(position).getVoucher().contains(",")){
           String[] jifen = showBean.getData().getPageInfo().getList().get(position).getVoucher().split(",");
           holder.num.setText(jifen[0]+"积分");
       }else {
           holder.num.setText(showBean.getData().getPageInfo().getList().get(position).getVoucher()+"积分");
       }

        holder.ll_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,GoodsMoreActivity.class);
                intent.putExtra("number",showBean.getData().getPageInfo().getList().get(position).getNumber());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

