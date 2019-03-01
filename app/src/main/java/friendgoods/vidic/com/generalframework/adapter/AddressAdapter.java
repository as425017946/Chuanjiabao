package friendgoods.vidic.com.generalframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.Bean.AddressBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.GuanliAdddressActivity;
import friendgoods.vidic.com.generalframework.touzi.more.TouziMoreUpdateAddressActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

public class AddressAdapter extends BaseAdapter {
    Context context;
    ArrayList<AddressBean> arrayList ;
    CallBack cb;
    public AddressAdapter(Context context,ArrayList<AddressBean> arrayList,CallBack cb){
        this.context = context;
        this.arrayList = arrayList;
        this.cb=cb;
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

    private static final String TAG = "AddressAdapter";
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        View closeView = null;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_guanli_address_item,viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        if (closeView == null){
            closeView = view;
        }
        final View finalCloseView = closeView;// listView的itemView
        viewHolder = (ViewHolder)view.getTag();
        final AddressBean addressBean = arrayList.get(i);
        viewHolder.tv_name.setText(addressBean.getData().get(i).getAdd_name());
        if (addressBean.getData().get(i).getFlag().equals("1")){
            viewHolder.tv_moren.setText("[默认]");
        }else{
            viewHolder.tv_moren.setText("");
        }
        if (addressBean.getData().get(i).getAdd_dizhi()!=null){
            if (addressBean.getData().get(i).getAdd_dizhi().length()>15){
                String xinxi = addressBean.getData().get(i).getAdd_dizhi().substring(0,15);
                viewHolder.tv_dizhi.setText(addressBean.getData().get(i).getArea_diqu()+xinxi+"...");
            }else{
                viewHolder.tv_dizhi.setText(addressBean.getData().get(i).getArea_diqu()+addressBean.getData().get(i).getAdd_dizhi());
            }
        }
        viewHolder.tv_phone.setText(addressBean.getData().get(i).getAdd_mobile());

        //点击后进入编辑地址页面
        viewHolder.l_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.longToast(addressBean.getData().get(i).getId()+"***"+i);
                Intent intent = new Intent(context, TouziMoreUpdateAddressActivity.class);
                intent.putExtra("name",addressBean.getData().get(i).getAdd_name());
                intent.putExtra("phone",addressBean.getData().get(i).getAdd_mobile());
                intent.putExtra("falg",addressBean.getData().get(i).getFlag());
                intent.putExtra("diqu",addressBean.getData().get(i).getArea_diqu());
                intent.putExtra("dizhi",addressBean.getData().get(i).getAdd_dizhi());
                intent.putExtra("id",addressBean.getData().get(i).getId()+"");
                context.startActivity(intent);
            }
        });
        //设置默认地址
        viewHolder.btnmoren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                ((SwipeMenuLayout)(finalCloseView)).quickClose();// 关闭侧滑菜单
                OkGo.post(Api.updateAddress)
                        .tag(this)
                        .params("id",addressBean.getData().get(i).getId())
                        .params("uuid",Userid)
                        .params("addName",addressBean.getData().get(i).getAdd_name())
                        .params("addMobile",addressBean.getData().get(i).getAdd_mobile())
                        .params("areaDiqu",addressBean.getData().get(i).getArea_diqu())
                        .params("addDiqu",addressBean.getData().get(i).getAdd_dizhi())
                        .params("flag",1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Gson gson = new Gson();
                                WechatBindBean wechatBindBean =gson.fromJson(s,WechatBindBean.class);
                                if(wechatBindBean.getState()==1){
                                    ToastUtils.longToast("设置成功！");
                                    notifyDataSetChanged();
                                    cb.click_moren(i);
                                }else {
                                    ToastUtils.shortToast(wechatBindBean.getMessage());
                                }
                            }
                        });
            }
        });
        viewHolder.btndelete.setTag(i);
        //移除listview被选中的item
        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.e(TAG, "onClick: "+v.getTag() );
                ((SwipeMenuLayout)(finalCloseView)).quickClose();// 关闭侧滑菜单
                OkGo.post(Api.delectAddress)
                        .tag(this)
                        .params("id",addressBean.getData().get(i).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Gson gson = new Gson();
                                WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                                if (wx.getState()==1){
                                    ToastUtils.shortToast("删除成功");
                                    cb.click_moren(i);
                                }else{
                                    ToastUtils.shortToast(wx.getMessage());
                                }
                            }
                        });

            }
        });

        return view;
    }



    public static class ViewHolder{
        TextView tv_name;
        TextView tv_moren;
        TextView tv_dizhi;
        TextView tv_phone;
        Button  btnmoren;
        LinearLayout btndelete;
        LinearLayout l_content;
        public ViewHolder(View view){
            tv_name = view.findViewById(R.id.guanli_more_name);
            tv_moren = view.findViewById(R.id.guanli_more_moren);
            tv_dizhi = view.findViewById(R.id.guanli_more_dizhi);
            tv_phone = view.findViewById(R.id.guanli_more_phone);
            btnmoren = view.findViewById(R.id.btnTop);
            btndelete = view.findViewById(R.id.btndelete);
            l_content = view.findViewById(R.id.content);
        }
    }
    public interface CallBack{
        void click_moren(int position);
    }

}
