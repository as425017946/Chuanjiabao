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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.ObjJinduBean;
import friendgoods.vidic.com.generalframework.Bean.WangqihejiBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import okhttp3.Call;
import okhttp3.Response;

public class WangqihejiAdapter extends BaseAdapter {
    ArrayList<WangqihejiBean> arrayList;
    Context context;
    double zhi = 0;
    public WangqihejiAdapter(Context context,ArrayList<WangqihejiBean> arrayList){
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
        final ViewHolder  viewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.templateheji,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        final WangqihejiBean wangqihejiBean = arrayList.get(i);
        //先截取需要的网络图片第一张
        String shwoimgurl = Api.ossurl + wangqihejiBean.getData().getPageInfo().getList().get(i).getItems_photo1();
        Glide.with(context)
                .load(shwoimgurl)
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img);

        viewHolder.tv_objname.setText(wangqihejiBean.getData().getPageInfo().getList().get(i).getItems_name());
        viewHolder.tv_leixing.setText(wangqihejiBean.getData().getPageInfo().getList().get(i).getItems_type());
        viewHolder.tv_dizhi.setText(wangqihejiBean.getData().getPageInfo().getList().get(i).getItems_site());
        viewHolder.tv_jieshutime.setText("结束时间:" + wangqihejiBean.getData().getPageInfo().getList().get(i).getBuy_day_end());
        //转换类型状态 1：股权 2：消费权 3：收益权
        if (wangqihejiBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("1")) {
            viewHolder.tv_shuoyiquan.setText("股权");
        } else if (wangqihejiBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("2")) {
            viewHolder.tv_shuoyiquan.setText("消费权");
        } else if (wangqihejiBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("3")) {
            viewHolder.tv_shuoyiquan.setText("收益权");
        }
        if (TextUtils.isEmpty(wangqihejiBean.getData().getPageInfo().getList().get(i).getMoney())){
            viewHolder.tv_jindu.setText("0%");
        }else {

            if (wangqihejiBean.getData().getPageInfo().getList().get(i).getMoney().length()>3){
                zhi = Double.parseDouble(wangqihejiBean.getData().getPageInfo().getList().get(i).getMoney().substring(0,3));
            }else {
                zhi = Double.parseDouble(wangqihejiBean.getData().getPageInfo().getList().get(i).getMoney());
            }
            //动画展示进度条信息
            new Thread() {
                @Override
                public void run() {
                    int a = 0;
                    while (a < zhi) {
                        a++;
                        try {
                            Thread.sleep(80);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final int j = a;
                        viewHolder.pro.setProgress(a);

                    }
                }
            }.start();
            viewHolder.tv_jindu.setText(zhi+"%");
        }
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TouziMoreActivity.class);
                intent.putExtra("objid", wangqihejiBean.getData().getPageInfo().getList().get(i).getItems_number() + "");
                context.startActivity(intent);
            }
        });


        return view;
    }
    public static class ViewHolder{
        ImageView img;
        TextView tv_objname;
        TextView tv_leixing;
        TextView tv_dizhi;
        TextView tv_jieshutime;
        TextView tv_jindu;
        ProgressBar pro;
        LinearLayout linearLayout;
        TextView tv_shuoyiquan;
        public ViewHolder(View view){
            img = view.findViewById(R.id.heji_img);
            tv_objname = view.findViewById(R.id.heji_objname);
            tv_dizhi = view.findViewById(R.id.heji_address);
            tv_leixing = view.findViewById(R.id.heji_leixing);
            tv_jieshutime = view.findViewById(R.id.heji_jieshutime);
            tv_jindu = view.findViewById(R.id.heji_jindu);
            pro = view.findViewById(R.id.heji_pro);
            linearLayout = view.findViewById(R.id.heji_info);
            tv_shuoyiquan = view.findViewById(R.id.heji_youyiquan);
        }
    }
}
