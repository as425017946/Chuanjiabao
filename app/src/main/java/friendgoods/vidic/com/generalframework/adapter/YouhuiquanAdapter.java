package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.ShowyouhuiquanBean;
import friendgoods.vidic.com.generalframework.R;

public class YouhuiquanAdapter extends BaseAdapter {
    Context context;
    ArrayList<ShowyouhuiquanBean> arrayList;
    public YouhuiquanAdapter(Context context,ArrayList<ShowyouhuiquanBean> arrayList){
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.youhuiquan_items,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        ShowyouhuiquanBean showyouhuiquanBean = arrayList.get(i);
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

        }

        return view;
    }
    public static class ViewHolder{
        TextView tv_time;
        TextView tv_jine;
        TextView tv_manjian;
        TextView tv_title;
        public ViewHolder(View view){
            tv_time = (TextView)view.findViewById(R.id.youhui_youxiaoqi);
            tv_jine = (TextView)view.findViewById(R.id.youhui_youhuijine);
            tv_manjian = (TextView)view.findViewById(R.id.youhui_manjiainjine);
            tv_title = (TextView) view.findViewById(R.id.youhui_title);
        }
    }
}
