package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.ObjJinduBean;
import friendgoods.vidic.com.generalframework.Bean.TouziYugaoBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import okhttp3.Call;
import okhttp3.Response;

public class TouziSousuoAdapter extends BaseAdapter {
    Context context;
    ArrayList<TouziYugaoBean> arrayList;
    public TouziSousuoAdapter(Context context, ArrayList<TouziYugaoBean> arrayList){
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
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.templatelist3,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
//        final TouziYugaoBean yugaoBean = arrayList.get(i);
//        viewHolder.tv_name.setText("发起人："+yugaoBean.getData().getPageInfo().getList().get(i).getItems_contacts());
//        //转换项目状态
//        if (yugaoBean.getData().getPageInfo().getList().get(i).getItems_status().equals("1")){
//            viewHolder.tv_state.setText("预告中");
//            viewHolder.sousuoyugao.setVisibility(View.VISIBLE);
//            viewHolder.sousuoshow.setVisibility(View.GONE);
//            //        //截取天数
//            int day = (int)yugaoBean.getData().getPageInfo().getList().get(i).getDay();
//            viewHolder.tv_day.setText(day+"");
//        }else if (yugaoBean.getData().getPageInfo().getList().get(i).getItems_status().equals("2")){
//            viewHolder.tv_state.setText("预约中");
//            viewHolder.sousuoyugao.setVisibility(View.GONE);
//            viewHolder.sousuoshow.setVisibility(View.VISIBLE);
//            viewHolder.tv_shengyutime.setText("剩余时间:" + yugaoBean.getData().getPageInfo().getList().get(i).getDay() + "天");
//            OkGo.post(Api.jindu)
//                    .tag(this)
//                    .params("ID", yugaoBean.getData().getPageInfo().getList().get(i).getID())
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(String s, Call call, Response response) {
////                        Log.e("测试",s);
////                        if ("1".equals(JsonUtul.getString(s, "state"))) {
//                            Gson gson = new Gson();
//                            final ObjJinduBean objJinduBean = gson.fromJson(s, ObjJinduBean.class);
//                            if (objJinduBean.getState() == 1) {
////                                Log.e("测试信息",objJinduBean.getData().getMoney());
//                                if (TextUtils.isEmpty(objJinduBean.getData().getMoney())) {
//                                    viewHolder.tv_rengoujindu.setText("认购进度:0%");
//                                } else {
//                                    final double zhi = Double.parseDouble(objJinduBean.getData().getMoney());
//                                    //动画展示进度条信息
//                                    new Thread() {
//                                        @Override
//                                        public void run() {
//                                            int i = 0;
//                                            while (i < zhi) {
//                                                i++;
//                                                try {
//                                                    Thread.sleep(80);
//                                                } catch (InterruptedException e) {
//                                                    e.printStackTrace();
//                                                }
//                                                final int j = i;
//                                                viewHolder.bar.setProgress(i);
//
//                                            }
//                                        }
//                                    }.start();
//                                    viewHolder.tv_rengoujindu.setText("认购进度:" + objJinduBean.getData().getMoney() + "%");
//                                }
//                            } else {
//                                viewHolder.tv_rengoujindu.setText("认购进度:0%");
//                            }
//
//
////                        }
//                        }
//                    });
//        }else if (yugaoBean.getData().getPageInfo().getList().get(i).getItems_status().equals("3")){
//            viewHolder.tv_state.setText("认购中");
//            viewHolder.sousuoyugao.setVisibility(View.GONE);
//            viewHolder.sousuoshow.setVisibility(View.VISIBLE);
//            viewHolder.tv_shengyutime.setText("剩余时间:" + yugaoBean.getData().getPageInfo().getList().get(i).getDay() + "天");
//            OkGo.post(Api.jindu)
//                    .tag(this)
//                    .params("ID", yugaoBean.getData().getPageInfo().getList().get(i).getID())
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(String s, Call call, Response response) {
////                        Log.e("测试",s);
////                        if ("1".equals(JsonUtul.getString(s, "state"))) {
//                            Gson gson = new Gson();
//                            final ObjJinduBean objJinduBean = gson.fromJson(s, ObjJinduBean.class);
//                            if (objJinduBean.getState() == 1) {
////                                Log.e("测试信息",objJinduBean.getData().getMoney());
//                                if (TextUtils.isEmpty(objJinduBean.getData().getMoney())) {
//                                    viewHolder.tv_rengoujindu.setText("认购进度:0%");
//                                } else {
//                                    final double zhi = Double.parseDouble(objJinduBean.getData().getMoney());
//                                    //动画展示进度条信息
//                                    new Thread() {
//                                        @Override
//                                        public void run() {
//                                            int i = 0;
//                                            while (i < zhi) {
//                                                i++;
//                                                try {
//                                                    Thread.sleep(80);
//                                                } catch (InterruptedException e) {
//                                                    e.printStackTrace();
//                                                }
//                                                final int j = i;
//                                                viewHolder.bar.setProgress(i);
//
//                                            }
//                                        }
//                                    }.start();
//                                    viewHolder.tv_rengoujindu.setText("认购进度:" + objJinduBean.getData().getMoney() + "%");
//                                }
//                            } else {
//                                viewHolder.tv_rengoujindu.setText("认购进度:0%");
//                            }
//
//
////                        }
//                        }
//                    });
//        }else if (yugaoBean.getData().getPageInfo().getList().get(i).getItems_status().equals("4")){
//            viewHolder.tv_state.setText("已完成");
//            viewHolder.sousuoyugao.setVisibility(View.GONE);
//            viewHolder.sousuoshow.setVisibility(View.VISIBLE);
//            viewHolder.tv_shengyutime.setText("剩余时间:" + yugaoBean.getData().getPageInfo().getList().get(i).getDay() + "天");
//            OkGo.post(Api.jindu)
//                    .tag(this)
//                    .params("ID", yugaoBean.getData().getPageInfo().getList().get(i).getID())
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(String s, Call call, Response response) {
////                        Log.e("测试",s);
////                        if ("1".equals(JsonUtul.getString(s, "state"))) {
//                            Gson gson = new Gson();
//                            final ObjJinduBean objJinduBean = gson.fromJson(s, ObjJinduBean.class);
//                            if (objJinduBean.getState() == 1) {
////                                Log.e("测试信息",objJinduBean.getData().getMoney());
//                                if (TextUtils.isEmpty(objJinduBean.getData().getMoney())) {
//                                    viewHolder.tv_rengoujindu.setText("认购进度:0%");
//                                } else {
//                                    final double zhi = Double.parseDouble(objJinduBean.getData().getMoney());
//                                    //动画展示进度条信息
//                                    new Thread() {
//                                        @Override
//                                        public void run() {
//                                            int i = 0;
//                                            while (i < zhi) {
//                                                i++;
//                                                try {
//                                                    Thread.sleep(80);
//                                                } catch (InterruptedException e) {
//                                                    e.printStackTrace();
//                                                }
//                                                final int j = i;
//                                                viewHolder.bar.setProgress(i);
//
//                                            }
//                                        }
//                                    }.start();
//                                    viewHolder.tv_rengoujindu.setText("认购进度:" + objJinduBean.getData().getMoney() + "%");
//                                }
//                            } else {
//                                viewHolder.tv_rengoujindu.setText("认购进度:0%");
//                            }
//
//
////                        }
//                        }
//                    });
//        }
//
//        //先截取需要的网络图片第一张
//        String[] imgurl = yugaoBean.getData().getPageInfo().getList().get(i).getItems_photo1().split(",");
//        String shwoimgurl = Api.ossurl + imgurl[0];
//        Glide.with(context).load(shwoimgurl).into(viewHolder.img);
//        viewHolder.tv_objname.setText(yugaoBean.getData().getPageInfo().getList().get(i).getItems_name());
//        viewHolder.tv_leixing.setText(yugaoBean.getData().getPageInfo().getList().get(i).getItems_type());
//        //转换类型状态
//        if(yugaoBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("1")){
//            viewHolder.tv_shouyiquan.setText("股权");
//        }else if (yugaoBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("2")){
//            viewHolder.tv_shouyiquan.setText("消费权");
//        }else if (yugaoBean.getData().getPageInfo().getList().get(i).getFunding_type().equals("3")){
//            viewHolder.tv_shouyiquan.setText("收益权");
//        }else{
//            viewHolder.tv_shouyiquan.setText("");
//        }
//        viewHolder.tv_dizhi.setText(yugaoBean.getData().getPageInfo().getList().get(i).getItems_site());
//        if (yugaoBean.getData().getPageInfo().getList().get(i).getItems_introduce().length()>30){
//            viewHolder.tv_jianjie.setText(yugaoBean.getData().getPageInfo().getList().get(i).getItems_introduce().substring(0,30)+"...");
//        }else{
//            viewHolder.tv_jianjie.setText(yugaoBean.getData().getPageInfo().getList().get(i).getItems_introduce());
//        }
//
//        //跳转详情页面
//        viewHolder.showitem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,TouziMoreActivity.class);
//                intent.putExtra("objid",yugaoBean.getData().getPageInfo().getList().get(i).getItems_number()+"");
//                context.startActivity(intent);
//            }
//        });


        return view;
    }


    public static class ViewHolder{
        ImageView img;
        TextView tv_name;
        TextView tv_state;
        TextView tv_objname;
        TextView tv_leixing;
        TextView tv_shouyiquan;
        TextView tv_dizhi;
        TextView tv_jianjie;
        TextView tv_day;
        LinearLayout showitem;
        LinearLayout sousuoyugao;
        LinearLayout sousuoshow;
        TextView tv_shengyutime;
        TextView tv_rengoujindu;
        ProgressBar bar;
        public ViewHolder(View view){
            img = view.findViewById(R.id.tzImg);
            tv_name = view.findViewById(R.id.tzname);
            tv_state =  view.findViewById(R.id.tzstate);
            tv_objname =  view.findViewById(R.id.tzxiangmuname);
            tv_leixing =  view.findViewById(R.id.tzleixing);
            tv_shouyiquan =  view.findViewById(R.id.tzshouyiquan);
            tv_dizhi =  view.findViewById(R.id.tzaddress);
            tv_jianjie =  view.findViewById(R.id.tzjianjie);
            tv_day = view.findViewById(R.id.yugao_day);
            showitem = view.findViewById(R.id.touzishowitem3);
            sousuoyugao = view.findViewById(R.id.sousuo_yugao);
            sousuoshow = view.findViewById(R.id.sousuo_show);
            tv_shengyutime = view.findViewById(R.id.tztime);
            tv_rengoujindu = view.findViewById(R.id.tzjindu);
            bar = view.findViewById(R.id.progressBar);
        }
    }
}
