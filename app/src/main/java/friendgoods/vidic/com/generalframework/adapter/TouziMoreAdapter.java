package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.TouziMoreBean;

public class TouziMoreAdapter extends BaseAdapter {
    Context context;
    ArrayList<TouziMoreBean> arrayList ;

    public TouziMoreAdapter(Context context,ArrayList<TouziMoreBean> arrayList){
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
        return view;
    }
}
