package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.TouziSanjiBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.more.TouziRengouMoreActivity;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

public class TouziSanjiAdapter extends BaseAdapter {
    ArrayList<TouziSanjiBean> arrayList;
    Context context;
    SharedPreferencesHelper sharedPreferencesHelper;
    public TouziSanjiAdapter(Context context,ArrayList<TouziSanjiBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        sharedPreferencesHelper = new SharedPreferencesHelper(context,"chuanjiabao");
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
            view = LayoutInflater.from(context).inflate(R.layout.touzisanjipage,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder) view.getTag();
        final TouziSanjiBean touziSanjiBean = arrayList.get(i);
        v.tv_danjia.setText("￥"+touziSanjiBean.getData().get(i).getFunding_start_money()+"元");
        v.tv_objname.setText(touziSanjiBean.getData().get(i).getItems_scheme_name());
        v.tv_xiangou.setText("个人限购"+touziSanjiBean.getData().get(i).getLimitation_number()+"份");
        if (touziSanjiBean.getData().get(i).getItems_earnings_introduce().length()>140){
            v.tv_info.setText(touziSanjiBean.getData().get(i).getItems_earnings_introduce().substring(0,140)+"...");
            v.view_more.setVisibility(View.VISIBLE);
            v.linearLayout.setVisibility(View.VISIBLE);
        }else {
            v.tv_info.setText(touziSanjiBean.getData().get(i).getItems_earnings_introduce());
            v.view_more.setVisibility(View.GONE);
            v.linearLayout.setVisibility(View.GONE);
        }
        //点击更多时间
        v.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v.tv_more.getText().toString().equals("查看更多")){
                    //全部展示
                    v.tv_info.setText(touziSanjiBean.getData().get(i).getItems_earnings_introduce());
                    //换成向上的箭头
                    v.img_show.setImageDrawable(context.getResources().getDrawable(R.mipmap.upmore));
                    v.tv_more.setText("收起更多");
                }else {
                    if (touziSanjiBean.getData().get(i).getItems_earnings_introduce().length()>140){
                        v.tv_info.setText(touziSanjiBean.getData().get(i).getItems_earnings_introduce().substring(0,140)+"...");
                        v.view_more.setVisibility(View.VISIBLE);
                        v.linearLayout.setVisibility(View.VISIBLE);
                    }else {
                        v.tv_info.setText(touziSanjiBean.getData().get(i).getItems_earnings_introduce());
                        v.view_more.setVisibility(View.GONE);
                        v.linearLayout.setVisibility(View.GONE);
                    }

                    //换成向上的箭头
                    v.img_show.setImageDrawable(context.getResources().getDrawable(R.mipmap.more));
                    v.tv_more.setText("查看更多");
                }

            }
        });

        v.tv_shengyu.setText(touziSanjiBean.getData().get(i).getSurplus_num()+"");
        v.tv_zongjifen.setText("/共"+touziSanjiBean.getData().get(i).getNum()+"份");
        //点击认购按钮
        v.btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.shortToast("1");
                sharedPreferencesHelper.put("fanganid",touziSanjiBean.getData().get(i).getId());
                Intent intent = new Intent(context, TouziRengouMoreActivity.class);
                intent.putExtra("objname",touziSanjiBean.getData().get(i).getItems_scheme_name()+"");
                intent.putExtra("danjia",touziSanjiBean.getData().get(i).getFunding_start_money()+"");
                intent.putExtra("shengyu",touziSanjiBean.getData().get(i).getSurplus_num()+"");
                intent.putExtra("xiangou",touziSanjiBean.getData().get(i).getLimitation_number()+"");
//                intent.putExtra("fananid",touziSanjiBean.getData().get(i).get)
                context.startActivity(intent);
            }
        });

        return view;
    }

    public static class ViewHolder{
        TextView tv_danjia;
        TextView tv_xiangou;
        TextView tv_objname;
        TextView tv_info;
        TextView tv_shengyu;
        TextView tv_zongjifen;
        Button btn_tijiao;
        View view_more;
        ImageView img_show;
        LinearLayout linearLayout;
        TextView tv_more;
        public ViewHolder(View view){
            tv_danjia = view.findViewById(R.id.sanji_danji);
            tv_xiangou = view.findViewById(R.id.sanji_xiangou);
            tv_info = view.findViewById(R.id.sanji_info);
            tv_objname = view.findViewById(R.id.sanji_objname);
            tv_shengyu = view.findViewById(R.id.sanji_shengyu);
            tv_zongjifen = view.findViewById(R.id.sanji_zongjifen);
            btn_tijiao = view.findViewById(R.id.sanji_tijiao);
            view_more = view.findViewById(R.id.more_view_more);
            linearLayout = view.findViewById(R.id.more_linear);
            img_show = view.findViewById(R.id.more_linear_image);
            tv_more = view.findViewById(R.id.more_linear_tiext);
        }
    }
}
