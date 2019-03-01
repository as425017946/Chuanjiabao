package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.XitongBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.more.XitonginfomoreActivity;

public class XitongMessageMoreAdapter extends BaseAdapter {
    Context context;
    ArrayList<XitongBean> arrayList;
    public String xiaoxizhi="";
    public XitongMessageMoreAdapter(Context context, ArrayList<XitongBean> arrayList){
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
            view = LayoutInflater.from(context).inflate(R.layout.xitongmessagesmore,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder)view.getTag();
        final XitongBean xitongBean = arrayList.get(i);
        v.tv_title.setText(xitongBean.getData().getPageInfo().getList().get(i).getItems_news_title());
        v.tv_time.setText(xitongBean.getData().getPageInfo().getList().get(i).getCreation_time()+"");
        if (xitongBean.getData().getPageInfo().getList().get(i).getItems_news_content()!=null){
            if (xitongBean.getData().getPageInfo().getList().get(i).getItems_news_content().length()>70){
                v.tv_info.setText(xitongBean.getData().getPageInfo().getList().get(i).getItems_news_content().substring(0,70)+"...");
            }else {
                v.tv_info.setText(xitongBean.getData().getPageInfo().getList().get(i).getItems_news_content());
            }
        }

        v.l_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, XitonginfomoreActivity.class);
                intent.putExtra("title",xitongBean.getData().getPageInfo().getList().get(i).getItems_news_title());
                intent.putExtra("time",xitongBean.getData().getPageInfo().getList().get(i).getCreation_time()+"");
                intent.putExtra("info",xitongBean.getData().getPageInfo().getList().get(i).getItems_news_content());
                intent.putExtra("urls",xitongBean.getData().getPageInfo().getList().get(i).getItems_news_url());
                context.startActivity(intent);

            }
        });

        v.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v.checkBox.isChecked()==true){

                    xiaoxizhi = xitongBean.getData().getPageInfo().getList().get(i).getId()+",";

                }else{
                    String[] a = xiaoxizhi.split(",");
                    String zhi = xitongBean.getData().getPageInfo().getList().get(i).getId()+"";
                    String zhi2 = i+"";
                    for (int j = 0; j <a.length ; j++) {
                        if (zhi.equals(a[j])){

                            xiaoxizhi = xiaoxizhi.substring(0,xiaoxizhi.indexOf(a[j]+","))+xiaoxizhi.substring(xiaoxizhi.indexOf(a[j]+",")+1);
                        }
                    }


                }
            }
        });


        return view;
    }
    public static class ViewHolder{
        TextView tv_title;
        TextView tv_time;
        TextView tv_info;
        LinearLayout l_chakan;
        CheckBox checkBox;
        public ViewHolder(View view){
            tv_title= view.findViewById(R.id.xitong_title);
            tv_info = view.findViewById(R.id.xitong_xinxi);
            tv_time = view.findViewById(R.id.xitong_time);
            l_chakan = view.findViewById(R.id.xitong_chakan);
            checkBox = view.findViewById(R.id.xitong_checkbox);
        }
    }
}
