package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.AfterAdminActivity;
import friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

public class AfterAdminAdapter extends BaseAdapter {
    Context context;
    ArrayList<AfterAdminBean> arrayList;
    public AfterAdminAdapter(Context context,ArrayList<AfterAdminBean> arrayList){
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_afteradmin_item,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v= (ViewHolder) view.getTag();
        final AfterAdminBean adminBean = arrayList.get(i);
        //先截取需要的网络图片第一张
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
        v.tv_fangan.setText(adminBean.getData().getPageInfo().getList().get(i).getItems_scheme_name());

        if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("1")){
            v.tv_jianzhu.setText("建筑未施工");
        }else if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("2")){
            v.tv_jianzhu.setText("建筑施工中");
//            v.tv_yuan1.setBackgroundResource(R.mipmap.touhouyuan2);
//            v.tv_xian1.setBackgroundResource(R.mipmap.touhouxian2);
//            v.tv_yuan2.setBackgroundResource(R.mipmap.touhouyuan2);
        }else if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("3")){
            v.tv_jianzhu.setText("待营业");

//            v.tv_yuan1.setBackgroundResource(R.mipmap.touhouyuan2);
//            v.tv_xian1.setBackgroundResource(R.mipmap.touhouxian2);
//            v.tv_yuan2.setBackgroundResource(R.mipmap.touhouyuan2);
//            v.tv_xian2.setBackgroundResource(R.mipmap.touhouxian2);
//            v.tv_yuan3.setBackgroundResource(R.mipmap.touhouyuan2);
        }else if (adminBean.getData().getPageInfo().getList().get(i).getItems_now().equals("4")){
            v.tv_jianzhu.setText("营业中");

//            v.tv_yuan1.setBackgroundResource(R.mipmap.touhouyuan2);
//            v.tv_xian1.setBackgroundResource(R.mipmap.touhouxian2);
//            v.tv_yuan2.setBackgroundResource(R.mipmap.touhouyuan2);
//            v.tv_xian2.setBackgroundResource(R.mipmap.touhouxian2);
//            v.tv_yuan3.setBackgroundResource(R.mipmap.touhouyuan2);;
//            v.tv_xian3.setBackgroundResource(R.mipmap.touhouxian2);
//            v.tv_yuan4.setBackgroundResource(R.mipmap.touhouyuan2);;
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

        //向下箭头点击查看更多
        v.l_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.l_moreshow.setVisibility(View.VISIBLE);
                v.l_more.setVisibility(View.GONE);
            }
        });
        //点击追加 认购
        v.btn_zhujia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adminBean.getData().getPageInfo().getList().get(i).getItems_status().equals("3")){
                    Intent intent = new Intent(context, TouziMoreActivity.class);
                    intent.putExtra("objid", adminBean.getData().getPageInfo().getList().get(i).getItems_number()+ "");
                    context.startActivity(intent);
                }else{
                    ToastUtils.shortToast("该状态无法进行追加认购");
                }

            }
        });
        //向上箭头关闭显示更多信息
        v.l_upmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.l_more.setVisibility(View.VISIBLE);
                v.l_moreshow.setVisibility(View.GONE);
            }
        });
        return view;
    }
    public static class  ViewHolder{
        LinearLayout l_more;
        Button btn_zhujia;
        LinearLayout l_upmore;
        LinearLayout l_moreshow;
        ImageView img;
        TextView tv_objname;
        TextView tv_jianzhu;
        TextView tv_sate;
        TextView tv_danjia;
        TextView tv_fenshu;
        TextView tv_dingdanhao;
        TextView tv_jindu;
        ProgressBar pro;
        TextView tv_yuan1;
        TextView tv_yuan2;
        TextView tv_yuan3;
        TextView tv_yuan4;
        TextView tv_xian1;
        TextView tv_xian2;
        TextView tv_xian3;
        TextView tv_fangan;
        public ViewHolder(View view){
            l_more = view.findViewById(R.id.touhouguanli_more);
            btn_zhujia= view.findViewById(R.id.touhouguanli_zhuijia);
            l_upmore= view.findViewById(R.id.touhouguanli_upmore);
            l_moreshow = view.findViewById(R.id.touhouguanli_moreshow);
            img = view.findViewById(R.id.after_img);
            tv_objname = view.findViewById(R.id.after_objname);
            tv_jianzhu = view.findViewById(R.id.after_jianzhu);
            tv_sate = view.findViewById(R.id.after_objstate1);
            tv_danjia = view.findViewById(R.id.after_danjia);
            tv_fenshu = view.findViewById(R.id.after_fenshu);
            tv_dingdanhao = view.findViewById(R.id.after_biaohao);
//            tv_jindu = view.findViewById(R.id.after_jindu);
//            pro = view.findViewById(R.id.after_progressBar);
//            tv_yuan1 = view.findViewById(R.id.touhou_yuan1);
//            tv_yuan2 = view.findViewById(R.id.touhou_yuan2);
//            tv_yuan3 = view.findViewById(R.id.touhou_yuan3);
//            tv_yuan4 = view.findViewById(R.id.touhou_yuan4);
//            tv_xian1 = view.findViewById(R.id.touhou_xian1);
//            tv_xian2 = view.findViewById(R.id.touhou_xian2);
//            tv_xian3 = view.findViewById(R.id.touhou_xian3);
            tv_fangan = view.findViewById(R.id.after_fananname);
        }
    }

}
