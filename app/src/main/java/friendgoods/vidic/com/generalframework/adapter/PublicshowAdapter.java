package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.PublicshowBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.more.DownLoaderActivity;

public class PublicshowAdapter extends BaseAdapter {
    Context context;
    ArrayList<PublicshowBean> arrayList;
    public PublicshowAdapter(Context context,ArrayList<PublicshowBean> arrayList){
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
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.gongkai_item,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        final PublicshowBean publicshowBean = arrayList.get(i);
        viewHolder.tv_time.setText(publicshowBean.getData().get(i).getCreation_time());
        viewHolder.tv_xinxi.setText(publicshowBean.getData().get(i).getPublic_file_name());
        //显示公开资料，点击下载
        viewHolder.tv_xinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(context)
                        .setMessage("下载中...")
                        .setCancelable(false)
                        .setCancelOutside(false);

                final LoadingDailog  dialog =loadBuilder.create();
                dialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(context,DownLoaderActivity.class);
                        intent.putExtra("url",publicshowBean.getData().get(i).getPublic_file_url());
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                },2000);
//                downFile();

            }
        });
        return view;
    }
    public static class ViewHolder{
        TextView tv_xinxi;
        TextView tv_time;
        public ViewHolder(View view){
            tv_xinxi = (TextView) view.findViewById(R.id.L_gongkai_ziliao);
            tv_time = (TextView) view.findViewById(R.id.L_gongkai_time);
        }
    }
}
