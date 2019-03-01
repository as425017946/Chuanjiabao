package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.TuijianBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;

public class TuijianAdapter extends BaseAdapter{
    ArrayList<TuijianBean> arrayList;
    Context context;
    public TuijianAdapter(Context context,ArrayList<TuijianBean> arrayList){
        this.arrayList = arrayList;
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.tuijianobj,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder) view.getTag();
        final TuijianBean tuijianBean = arrayList.get(i);
        //先截取需要的网络图片第一张
//        String[] imgurl = tuijianBean.getData().getPageInfo().getList().get(i).getItems_photo1().split(",");
        if (tuijianBean.getData().getPageInfo().getList().get(i).getItems_photo1()!=null){
            String shwoimgurl = Api.ossurl + tuijianBean.getData().getPageInfo().getList().get(i).getItems_photo1();
            Glide.with(context)
                    .load(shwoimgurl)
                    .placeholder(R.mipmap.projectshow)
                    .error(R.mipmap.projectshow)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(v.img);
        }else {
            Glide.with(context)
                    .load("")
                    .placeholder(R.mipmap.projectshow)
                    .error(R.mipmap.projectshow)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(v.img);
        }

        v.tv_objname.setText(tuijianBean.getData().getPageInfo().getList().get(i).getItems_name());
        v.tv_danjia.setText(tuijianBean.getData().getPageInfo().getList().get(i).getFunding_start_money()+"/"+tuijianBean.getData().getPageInfo().getList().get(i).getNum()+"份");
        v.tv_address.setText(tuijianBean.getData().getPageInfo().getList().get(i).getItems_site());
        v.tv_leixing.setText(tuijianBean.getData().getPageInfo().getList().get(i).getItems_type());

        //转换项目状态
        if (tuijianBean.getData().getPageInfo().getList().get(i).getItems_status().equals("1")) {
            v.tv_state.setText("预告中");
        } else if (tuijianBean.getData().getPageInfo().getList().get(i).getItems_status().equals("2")) {
            v.tv_state.setText("预约中");
        } else if (tuijianBean.getData().getPageInfo().getList().get(i).getItems_status().equals("3")) {
            v.tv_state.setText("认购中");
        } else if (tuijianBean.getData().getPageInfo().getList().get(i).getItems_status().equals("4")) {
            v.tv_state.setText("已完成");
        }
        v.ll_tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TouziMoreActivity.class);
                intent.putExtra("objid", tuijianBean.getData().getPageInfo().getList().get(i).getItems_number() + "");
                context.startActivity(intent);
            }
        });

        return view;
    }

    public static class ViewHolder{
        ImageView img;
        TextView tv_objname;
        TextView tv_danjia;
        TextView tv_address;
        TextView tv_leixing;
        TextView tv_state;
        LinearLayout ll_tuijian;
        public ViewHolder(View view){
            img = view.findViewById(R.id.tuijian_img);
            tv_objname = view.findViewById(R.id.tuijian_objname);
            tv_danjia = view.findViewById(R.id.tuijian_danjia);
            tv_address = view.findViewById(R.id.tuijian_address);
            tv_state = view.findViewById(R.id.tuijian_state);
            tv_leixing = view.findViewById(R.id.tuijian_leixing);
            ll_tuijian = view.findViewById(R.id.tuijina_ll);
        }
    }
}
