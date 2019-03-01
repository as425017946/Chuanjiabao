package friendgoods.vidic.com.generalframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.ShowyouhuiquanBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

public class YouhuiquanAdapter2 extends BaseAdapter {
    Context context;
    ArrayList<ShowyouhuiquanBean> arrayList;
    SharedPreferencesHelper sharedPreferencesHelper;
    public YouhuiquanAdapter2(Context context, ArrayList<ShowyouhuiquanBean> arrayList){
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
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.youhuiquan_items,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        final ShowyouhuiquanBean showyouhuiquanBean = arrayList.get(i);
        //只展示type为1的
        if (showyouhuiquanBean.getData().getPageInfo().getList().get(i).getType().equals("1")){
            if (showyouhuiquanBean.getData().getPageInfo().getList().get(i).getItems_name()==null){
                viewHolder.tv_time.setText("有效期到"+showyouhuiquanBean.getData().getPageInfo().getList().get(i).getEnd_day());
                viewHolder.tv_jine.setText("￥"+showyouhuiquanBean.getData().getPageInfo().getList().get(i).getReduce_money());
                viewHolder.tv_manjian.setText("满"+showyouhuiquanBean.getData().getPageInfo().getList().get(i).getAmount_money()+"可用");
                viewHolder.tv_title.setText("常规项目通用");
            }else {
                viewHolder.tv_time.setText("有效期到"+showyouhuiquanBean.getData().getPageInfo().getList().get(i).getEnd_day());
                viewHolder.tv_jine.setText("￥"+showyouhuiquanBean.getData().getPageInfo().getList().get(i).getReduce_money());
                viewHolder.tv_manjian.setText("满"+showyouhuiquanBean.getData().getPageInfo().getList().get(i).getAmount_money()+"可用");
                viewHolder.tv_title.setText(showyouhuiquanBean.getData().getPageInfo().getList().get(i).getItems_name()+"项目可用");
            }
            viewHolder.linearLayout_youhui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断是否大于了满减条件
                    if (Integer.parseInt(sharedPreferencesHelper.getSharedPreference("ordermoney","").toString())>=Integer.parseInt(showyouhuiquanBean.getData().getPageInfo().getList().get(i).getAmount_money()))
                    {
                        sharedPreferencesHelper.put("youhuiid",showyouhuiquanBean.getData().getPageInfo().getList().get(i).getId());
                        sharedPreferencesHelper.put("youhuimoney",showyouhuiquanBean.getData().getPageInfo().getList().get(i).getReduce_money());
                        if (Activity.class.isInstance(context)) {
                            // 转化为activity，然后finish就行了
                            Activity activity = (Activity) context;
                            activity.finish();
                        }
                    }else {
                        ToastUtils.shortToast("金额不足，无法使用优惠券");
                    }

                }
            });
        }

        return view;
    }
    public static class ViewHolder{
        TextView tv_time;
        TextView tv_jine;
        TextView tv_manjian;
        TextView tv_title;
        LinearLayout linearLayout_youhui;
        public ViewHolder(View view){
            tv_time = (TextView)view.findViewById(R.id.youhui_youxiaoqi);
            tv_jine = (TextView)view.findViewById(R.id.youhui_youhuijine);
            tv_manjian = (TextView)view.findViewById(R.id.youhui_manjiainjine);
            tv_title = (TextView) view.findViewById(R.id.youhui_title);
            linearLayout_youhui = (LinearLayout)view.findViewById(R.id.youhuiquan_linearyout);
        }
    }
}
