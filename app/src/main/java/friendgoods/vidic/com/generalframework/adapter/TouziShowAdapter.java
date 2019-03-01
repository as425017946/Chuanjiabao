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
import friendgoods.vidic.com.generalframework.Bean.TouziShowBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.JsonUtul;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

public class TouziShowAdapter extends BaseAdapter {

    Context context;
    ArrayList<TouziShowBean> arrayList;
    double zhi = 0;
    public TouziShowAdapter(Context context, ArrayList<TouziShowBean> arrayList) {
        this.context = context;
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.templatelist1, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        final TouziShowBean showBean = arrayList.get(i);
        viewHolder.tv_name.setText("发起人：" + showBean.getData().getPageInfo().getList().get(i).getUser_name());
        //转换项目状态
        if (showBean.getData().getPageInfo().getList().get(i).getItems_status().equals("1")) {
            viewHolder.tv_state.setText("预告中");
        } else if (showBean.getData().getPageInfo().getList().get(i).getItems_status().equals("2")) {
            viewHolder.tv_state.setText("预约中");
        } else if (showBean.getData().getPageInfo().getList().get(i).getItems_status().equals("3")) {
            viewHolder.tv_state.setText("认购中");
        } else if (showBean.getData().getPageInfo().getList().get(i).getItems_status().equals("4")) {
            viewHolder.tv_state.setText("已完成");
        }

        //先截取需要的网络图片第一张
//        String[] imgurl = showBean.getData().getPageInfo().getList().get(i).getItems_photo1().split(",");
//        String shwoimgurl = Api.ossurl + imgurl[0];
         String shwoimgurl = Api.ossurl + showBean.getData().getPageInfo().getList().get(i).getItems_photo1();
         Glide.with(context)
                .load(shwoimgurl)
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img);

        viewHolder.tv_objname.setText(showBean.getData().getPageInfo().getList().get(i).getItems_name());
        viewHolder.tv_leixing.setText(showBean.getData().getPageInfo().getList().get(i).getItems_type());
        //转换类型状态
        if(showBean.getData().getPageInfo().getList().get(i).getFunding_type()==null){
            viewHolder.tv_shouyiquan.setText("");
        }else {
            if (showBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("1")) {
                viewHolder.tv_shouyiquan.setText("股权");
            } else if (showBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("2")) {
                viewHolder.tv_shouyiquan.setText("消费权");
            } else if (showBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("3")) {
                viewHolder.tv_shouyiquan.setText("收益权");
            } else {
                viewHolder.tv_shouyiquan.setText("");
            }
        }

        viewHolder.tv_dizhi.setText(showBean.getData().getPageInfo().getList().get(i).getItems_site());
        if (showBean.getData().getPageInfo().getList().get(i).getItems_comment()==null){

        }else {
            if (showBean.getData().getPageInfo().getList().get(i).getItems_comment().length() > 30) {
                viewHolder.tv_jianjie.setText(showBean.getData().getPageInfo().getList().get(i).getItems_comment().substring(0, 30) + "...");
            } else {
                viewHolder.tv_jianjie.setText(showBean.getData().getPageInfo().getList().get(i).getItems_comment());
            }
        }


        viewHolder.tv_shengyutime.setText("剩余时间:" + showBean.getData().getPageInfo().getList().get(i).getDay() + "天");
//        Log.e("展示信息ineed",showBean.getData().getPageInfo().getList().get(i).getMoney()+"");
        if (showBean.getData().getPageInfo().getList().get(i).getMoney()==null || TextUtils.isEmpty(showBean.getData().getPageInfo().getList().get(i).getMoney())) {
            viewHolder.tv_rengoujindu.setText("认购进度:0%");
//            Log.e("展示信息ineed1",showBean.getData().getPageInfo().getList().get(i).getMoney()+"");
            viewHolder.bar.setProgress(0);
        } else {
//            Log.e("展示信息ineed2",showBean.getData().getPageInfo().getList().get(i).getMoney()+"");
            if (showBean.getData().getPageInfo().getList().get(i).getMoney().length()>3){
                zhi = Double.parseDouble(showBean.getData().getPageInfo().getList().get(i).getMoney().substring(0,3));
            }else {
                zhi = Double.parseDouble(showBean.getData().getPageInfo().getList().get(i).getMoney());
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
                        viewHolder.bar.setProgress(a);

                    }
                }
            }.start();
            viewHolder.tv_rengoujindu.setText("认购进度:" + zhi + "%");
        }

        //跳转详情页面
        viewHolder.showitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TouziMoreActivity.class);
                intent.putExtra("objid", showBean.getData().getPageInfo().getList().get(i).getItems_number() + "");
                context.startActivity(intent);
            }
        });

        return view;
    }

    public static class ViewHolder {
        ImageView img;
        TextView tv_name;
        TextView tv_state;
        TextView tv_objname;
        TextView tv_leixing;
        TextView tv_shouyiquan;
        TextView tv_dizhi;
        TextView tv_jianjie;
        TextView tv_shengyutime;
        TextView tv_rengoujindu;
        ProgressBar bar;
        LinearLayout showitem;
        public ViewHolder(View view) {
            img = view.findViewById(R.id.tzImg);
            tv_name = view.findViewById(R.id.tzname);
            tv_state = view.findViewById(R.id.tzstate);
            tv_objname = view.findViewById(R.id.tzxiangmuname);
            tv_leixing = view.findViewById(R.id.tzleixing);
            tv_shouyiquan = view.findViewById(R.id.tzshouyiquan);
            tv_dizhi = view.findViewById(R.id.tzaddress);
            tv_jianjie = view.findViewById(R.id.tzjianjie);
            tv_shengyutime = view.findViewById(R.id.tztime);
            tv_rengoujindu = view.findViewById(R.id.tzjindu);
            bar = view.findViewById(R.id.progressBar);
            showitem = view.findViewById(R.id.touzishowitem);
        }
    }
}
