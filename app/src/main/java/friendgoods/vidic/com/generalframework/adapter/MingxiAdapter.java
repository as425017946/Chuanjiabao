package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.MingxiBean;
import friendgoods.vidic.com.generalframework.R;

public class MingxiAdapter extends BaseAdapter {
    ArrayList<MingxiBean> arrayList;
    Context context;
    //用于判断状态用
    int a;
    public MingxiAdapter(Context context,ArrayList<MingxiBean> arrayList,int a){
            this.context = context ;
        this.arrayList =arrayList;
        this.a = a;
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
        ViewHolder v;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.templateshouzhimingxi,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder)view.getTag();
        MingxiBean mingxiBean = arrayList.get(i);
        v.tv_time.setText(mingxiBean.getData().getPageInfo().getList().get(i).getCreate_time()+"");
        v.tv_jine.setText(mingxiBean.getData().getPageInfo().getList().get(i).getCirculating_fund()+"元");

        if (a==1){
            v.tv_sate.setText("转入");
        }else if(a==2){
            v.tv_sate.setText("转出");
        }else{
            v.tv_sate.setText("余额收益");
        }

        v.tv_card.setText(mingxiBean.getData().getPageInfo().getList().get(i).getBank_card_number()+"");

        return view;
    }
    public static class ViewHolder{
        TextView tv_time;
        TextView tv_jine;
        TextView tv_sate;
        TextView tv_card;
        public ViewHolder(View view){
            tv_time = view.findViewById(R.id.mingxi_time);
            tv_jine = view.findViewById(R.id.mingxi_jine);
            tv_sate = view.findViewById(R.id.mingxi_state);
            tv_card = view.findViewById(R.id.mingxi_card);
        }
    }
}
