package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

import friendgoods.vidic.com.generalframework.Bean.AfterAdminBean;
import friendgoods.vidic.com.generalframework.Bean.ObjJinduBean;
import friendgoods.vidic.com.generalframework.Bean.TaocanBean;
import friendgoods.vidic.com.generalframework.Bean.XiaofeiquanyiBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.touzi.more.TouziFengxianMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import okhttp3.Call;
import okhttp3.Response;

public class XiaofeiquanyiAdapter extends BaseAdapter {
    Context context;
    ArrayList<XiaofeiquanyiBean> arrayList;
    public XiaofeiquanyiAdapter(Context context, ArrayList<XiaofeiquanyiBean> arrayList){
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
        final ViewHolder v;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.xiaofeiquanyiitem,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v= (ViewHolder) view.getTag();
        final XiaofeiquanyiBean adminBean = arrayList.get(i);

        String shwoimgurl = Api.ossurl + adminBean.getData().getPageInfo().getList().get(i).getItems_photo1();
        Glide.with(context)
                .load(shwoimgurl)
                .placeholder(R.mipmap.projectshow)
                .error(R.mipmap.projectshow)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(v.img);

        if (adminBean.getData().getPageInfo().getList().get(i).getItems_name().length()>8){
            v.tv_objname.setText(adminBean.getData().getPageInfo().getList().get(i).getItems_name().substring(0,8)+"..");
        }else{
            v.tv_objname.setText(adminBean.getData().getPageInfo().getList().get(i).getItems_name());
        }
        if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("1")){
            v.tv_jianzhu.setText("建筑未施工");
        }else if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("2")){
            v.tv_jianzhu.setText("建筑施工中");

        }else if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("3")){
            v.tv_jianzhu.setText("待营业");

        }else if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("4")){
            v.tv_jianzhu.setText("营业中");
        }

        //转换项目状态
        if (adminBean.getData().getPageInfo().getList().get(i).getItems_status().equals("1")) {
            v.tv_sate.setText("预告中");
        } else if (adminBean.getData().getPageInfo().getList().get(i).getItems_status().equals("2")) {
            v.tv_sate.setText("预约中");
        } else if (adminBean.getData().getPageInfo().getList().get(i).getItems_status().equals("3")) {
            v.tv_sate.setText("认购中");
        } else if (adminBean.getData().getPageInfo().getList().get(i).getItems_status().equals("4")) {
            v.tv_sate.setText("已完成");
        }
        v.tv_danjia.setText(adminBean.getData().getPageInfo().getList().get(i).getFunding_start_money()+"元");
        if (TextUtils.isEmpty(adminBean.getData().getPageInfo().getList().get(i).getBuy_num())){
            v.tv_fenshu.setText("");
        }else {
            v.tv_fenshu.setText(adminBean.getData().getPageInfo().getList().get(i).getBuy_num()+"份");
        }

        v.tv_dingdanhao.setText(adminBean.getData().getPageInfo().getList().get(i).getNumber()+"");
        v.tv_fanan.setText(adminBean.getData().getPageInfo().getList().get(i).getItems_scheme_name());
        if (TextUtils.isEmpty(adminBean.getData().getPageInfo().getList().get(i).getItems_comment())){
            v.tv_zhanshi.setText("暂无使用说明");
        }else {
            v.tv_zhanshi.setText(adminBean.getData().getPageInfo().getList().get(i).getItems_comment());
        }

//        OkGo.post(Api.taocan)
//                .tag(this)
//                .params("ID",adminBean.getData().getPageInfo().getList().get(i).getID())
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
////                        Log.e("套餐信息",s);
//                        Gson gson = new Gson();
//                        final TaocanBean taocanBean = gson.fromJson(s,TaocanBean.class);
//                        if (taocanBean.getState()==1){
//                            if (taocanBean.getData().size()>0){
//                               v.taocan1.setVisibility(View.VISIBLE);
//                               v.tv1_name.setText(taocanBean.getData().get(0).getName());
//                               if (taocanBean.getData().get(0).getIntroduction().length()>25){
//                                   v.tv1_info.setText(taocanBean.getData().get(0).getIntroduction().substring(0,25)+"...");
//                               }else{
//                                   v.tv1_info.setText(taocanBean.getData().get(0).getIntroduction());
//                               }
//
//                            }
//                            if (taocanBean.getData().size()>1){
//                                v.taocan2.setVisibility(View.VISIBLE);
//                                v.tv2_name.setText(taocanBean.getData().get(1).getName());
//                                if (taocanBean.getData().get(1).getIntroduction().length()>25){
//                                    v.tv2_info.setText(taocanBean.getData().get(1).getIntroduction().substring(0,25)+"...");
//                                }else{
//                                    v.tv2_info.setText(taocanBean.getData().get(1).getIntroduction());
//                                }
//                            }
//                            if (taocanBean.getData().size()>2){
//                                v.taocan3.setVisibility(View.VISIBLE);
//                                v.tv3_name.setText(taocanBean.getData().get(2).getName());
//                                if (taocanBean.getData().get(2).getIntroduction().length()>25){
//                                    v.tv3_info.setText(taocanBean.getData().get(2).getIntroduction().substring(0,25)+"...");
//                                }else{
//                                    v.tv3_info.setText(taocanBean.getData().get(2).getIntroduction());
//                                }
//                            }
//                            if (taocanBean.getData().size()>3){
//                                v.taocan4.setVisibility(View.VISIBLE);
//                                v.tv4_name.setText(taocanBean.getData().get(3).getName());
//                                if (taocanBean.getData().get(3).getIntroduction().length()>25){
//                                    v.tv4_info.setText(taocanBean.getData().get(3).getIntroduction().substring(0,25)+"...");
//                                }else{
//                                    v.tv4_info.setText(taocanBean.getData().get(3).getIntroduction());
//                                }
//                            }
//                            if (taocanBean.getData().size()>4){
//                                v.taocan5.setVisibility(View.VISIBLE);
//                                v.tv5_name.setText(taocanBean.getData().get(4).getName());
//                                if (taocanBean.getData().get(4).getIntroduction().length()>25){
//                                    v.tv5_info.setText(taocanBean.getData().get(4).getIntroduction().substring(0,25)+"...");
//                                }else{
//                                    v.tv5_info.setText(taocanBean.getData().get(4).getIntroduction());
//                                }
//                            }
//
//                            //点击事件
//                            v.taocan1.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                    LayoutInflater inflater = LayoutInflater.from(context);
//                                    final View DialogView = inflater .inflate ( R.layout.xiaofeiquanyitishi, null);//1、自定义布局
//                                    TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
//                                    TextView tv_info = (TextView)DialogView.findViewById(R.id.tishi);
//                                    tv_info.setText(taocanBean.getData().get(0).getIntroduction());
//                                    TextView tv_tishi = (TextView)DialogView.findViewById(R.id.xiaofeitishi);
//                                    if (taocanBean.getData().get(0).getName().length()>6){
//                                        tv_tishi.setText(taocanBean.getData().get(0).getName().substring(0,6)+"...");
//                                    }else{
//                                        tv_tishi.setText(taocanBean.getData().get(0).getName());
//                                    }
//                                    builder.setView(DialogView);
//                                    final AlertDialog dialog = builder.create();
//                                    //点击我已知晓
//                                    ok.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    dialog.show();
//                                }
//                            });
//                            v.taocan2.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                    LayoutInflater inflater = LayoutInflater.from(context);
//                                    final View DialogView = inflater .inflate ( R.layout.xiaofeiquanyitishi, null);//1、自定义布局
//                                    TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
//                                    TextView tv_info = (TextView)DialogView.findViewById(R.id.tishi);
//                                    tv_info.setText(taocanBean.getData().get(1).getIntroduction());
//                                    TextView tv_tishi = (TextView)DialogView.findViewById(R.id.xiaofeitishi);
//                                    if (taocanBean.getData().get(1).getName().length()>6){
//                                        tv_tishi.setText(taocanBean.getData().get(1).getName().substring(0,6)+"...");
//                                    }else{
//                                        tv_tishi.setText(taocanBean.getData().get(1).getName());
//                                    }
//                                    builder.setView(DialogView);
//                                    final AlertDialog dialog = builder.create();
//                                    //点击我已知晓
//                                    ok.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    dialog.show();
//                                }
//                            });
//                            v.taocan3.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                    LayoutInflater inflater = LayoutInflater.from(context);
//                                    final View DialogView = inflater .inflate ( R.layout.xiaofeiquanyitishi, null);//1、自定义布局
//                                    TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
//                                    TextView tv_info = (TextView)DialogView.findViewById(R.id.tishi);
//                                    tv_info.setText(taocanBean.getData().get(2).getIntroduction());
//                                    TextView tv_tishi = (TextView)DialogView.findViewById(R.id.xiaofeitishi);
//                                    if (taocanBean.getData().get(2).getName().length()>6){
//                                        tv_tishi.setText(taocanBean.getData().get(2).getName().substring(0,6)+"...");
//                                    }else{
//                                        tv_tishi.setText(taocanBean.getData().get(2).getName());
//                                    }
//                                    builder.setView(DialogView);
//                                    final AlertDialog dialog = builder.create();
//                                    //点击我已知晓
//                                    ok.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    dialog.show();
//                                }
//                            });
//                            v.taocan4.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                    LayoutInflater inflater = LayoutInflater.from(context);
//                                    final View DialogView = inflater .inflate ( R.layout.xiaofeiquanyitishi, null);//1、自定义布局
//                                    TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
//                                    TextView tv_info = (TextView)DialogView.findViewById(R.id.tishi);
//                                    tv_info.setText(taocanBean.getData().get(3).getIntroduction());
//                                    TextView tv_tishi = (TextView)DialogView.findViewById(R.id.xiaofeitishi);
//                                    if (taocanBean.getData().get(3).getName().length()>6){
//                                        tv_tishi.setText(taocanBean.getData().get(3).getName().substring(0,6)+"...");
//                                    }else{
//                                        tv_tishi.setText(taocanBean.getData().get(3).getName());
//                                    }
//                                    builder.setView(DialogView);
//                                    final AlertDialog dialog = builder.create();
//                                    //点击我已知晓
//                                    ok.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    dialog.show();
//                                }
//                            });
//                            v.taocan5.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                    LayoutInflater inflater = LayoutInflater.from(context);
//                                    final View DialogView = inflater .inflate ( R.layout.xiaofeiquanyitishi, null);//1、自定义布局
//                                    TextView tv_info = (TextView)DialogView.findViewById(R.id.tishi);
//                                    tv_info.setText(taocanBean.getData().get(4).getIntroduction());
//                                    TextView tv_tishi = (TextView)DialogView.findViewById(R.id.xiaofeitishi);
//                                    if (taocanBean.getData().get(4).getName().length()>6){
//                                        tv_tishi.setText(taocanBean.getData().get(4).getName().substring(0,6)+"...");
//                                    }else{
//                                        tv_tishi.setText(taocanBean.getData().get(4).getName());
//                                    }
//
//                                    TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
//                                    builder.setView(DialogView);
//                                    final AlertDialog dialog = builder.create();
//                                    //点击我已知晓
//                                    ok.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    dialog.show();
//                                }
//                            });
//
//
//                        }
//
//                    }
//                });

        return view;
    }
    public static class  ViewHolder{
        ImageView img;
        TextView tv_objname;
        TextView tv_jianzhu;
        TextView tv_sate;
        TextView tv_danjia;
        TextView tv_fenshu;
        TextView tv_dingdanhao;
        LinearLayout linearLayout;
        LinearLayout taocan;
        LinearLayout taocan1;
        LinearLayout taocan2;
        LinearLayout taocan3;
        LinearLayout taocan4;
        LinearLayout taocan5;
        TextView tv1_name;
        TextView tv1_info;
        TextView tv2_name;
        TextView tv2_info;
        TextView tv3_name;
        TextView tv3_info;
        TextView tv4_name;
        TextView tv4_info;
        TextView tv5_name;
        TextView tv5_info;
        TextView tv_fanan;
        TextView tv_zhanshi;

        public ViewHolder(View view){
            img = view.findViewById(R.id.after_img);
            tv_objname = view.findViewById(R.id.after_objname);
            tv_jianzhu = view.findViewById(R.id.after_jianzhu);
            tv_sate = view.findViewById(R.id.after_objstate1);
            tv_danjia = view.findViewById(R.id.after_danjia);
            tv_fenshu = view.findViewById(R.id.after_fenshu);
            tv_dingdanhao = view.findViewById(R.id.after_biaohao);
            linearLayout = view.findViewById(R.id.xiaofeiquanyi_linearlayout);
            taocan = view.findViewById(R.id.taocan);
            taocan1 = view.findViewById(R.id.taocan1);
            taocan2 = view.findViewById(R.id.taocan2);
            taocan3 = view.findViewById(R.id.taocan3);
            taocan4 = view.findViewById(R.id.taocan4);
            taocan5 = view.findViewById(R.id.taocan5);
            tv1_name = view.findViewById(R.id.taocan1_name);
            tv1_info = view.findViewById(R.id.taocan1_info);
            tv2_name = view.findViewById(R.id.taocan2_name);
            tv2_info = view.findViewById(R.id.taocan2_info);
            tv3_name = view.findViewById(R.id.taocan3_name);
            tv3_info = view.findViewById(R.id.taocan3_info);
            tv4_name = view.findViewById(R.id.taocan4_name);
            tv4_info = view.findViewById(R.id.taocan4_info);
            tv5_name = view.findViewById(R.id.taocan5_name);
            tv5_info = view.findViewById(R.id.taocan5_info);
            tv_fanan = view.findViewById(R.id.after_fananname);
            tv_zhanshi = view.findViewById(R.id.xiaofeiquanyi_zhanshi);
        }
    }

}
