package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.XitongBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.more.XitonginfomoreActivity;

public class XitongMessageAdapter extends BaseAdapter {
    Context context;
    ArrayList<XitongBean> arrayList;
    public XitongMessageAdapter(Context context,ArrayList<XitongBean> arrayList){
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
        ViewHolder v;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.xitongmessage,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder)view.getTag();
        final XitongBean xitongBean = arrayList.get(i);
        Log.e("*****",i+"");
        v.tv_title.setText(xitongBean.getData().getPageInfo().getList().get(i).getItems_news_title()+"");
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
        return view;
    }
    public static class ViewHolder{
        TextView tv_title;
        TextView tv_time;
        TextView tv_info;
        LinearLayout l_chakan;
        public ViewHolder(View view){
            tv_title= view.findViewById(R.id.xitong_title);
            tv_info = view.findViewById(R.id.xitong_xinxi);
            tv_time = view.findViewById(R.id.xitong_time);
            l_chakan = view.findViewById(R.id.xitong_chakan);
        }
    }
}
