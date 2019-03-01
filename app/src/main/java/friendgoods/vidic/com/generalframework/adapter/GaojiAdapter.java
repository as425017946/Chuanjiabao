package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.GaojirenzhengBean;
import friendgoods.vidic.com.generalframework.R;

public class GaojiAdapter extends BaseAdapter {
    Context context;
    ArrayList<GaojirenzhengBean> arrayList;
    public GaojiAdapter(ArrayList<GaojirenzhengBean> arrayList,Context context){
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
        ViewHolder v;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.gaojirenzheng_item,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        v = (ViewHolder)view.getTag();
        GaojirenzhengBean gaoji = arrayList.get(i);
        v.radioButton.setText(gaoji.getData().get(i).getUser_job_name());

        return view;
    }
    public static class ViewHolder{
        RadioButton radioButton;
        public ViewHolder(View view){
            radioButton = view.findViewById(R.id.gaoji_rdio);
        }
    }
}
