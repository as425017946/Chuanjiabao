package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.XiaofeizhongchouBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;

public class XiaofeizhongchouAdapter extends BaseAdapter {
    ArrayList<XiaofeizhongchouBean> arrayList;
    Context context;
    public XiaofeizhongchouAdapter(Context context,ArrayList<XiaofeizhongchouBean> arrayList){
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
            view = LayoutInflater.from(context).inflate(R.layout.xiaofeizhongchou,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        XiaofeizhongchouBean xiaofeizhongchouBean = arrayList.get(i);
        //先截取需要的网络图片第一张
        String[] imgurl = xiaofeizhongchouBean.getData().getPageInfo().getList().get(i).getItems_photo1().split(",");
        String shwoimgurl = Api.ossurl + imgurl[0];
        Glide.with(context).load(shwoimgurl).into( viewHolder.imageView);

        return view;
    }
    public static class ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            imageView= view.findViewById(R.id.xiaofeizhongchou_img);
        }
    }
}
