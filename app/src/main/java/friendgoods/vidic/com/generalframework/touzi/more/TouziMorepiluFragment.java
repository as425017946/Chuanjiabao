package friendgoods.vidic.com.generalframework.touzi.more;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.PrivateRenBean;
import friendgoods.vidic.com.generalframework.Bean.PublicshowBean;
import friendgoods.vidic.com.generalframework.Bean.TishiBean;
import friendgoods.vidic.com.generalframework.Bean.TouziKejianBean;
import friendgoods.vidic.com.generalframework.Bean.TouzirenBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.PrivateshowAdapter;
import friendgoods.vidic.com.generalframework.adapter.PublicshowAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
//import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.number10;
//import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.uudi;
import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.xianmguname;
import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.zhiid;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouziMorepiluFragment extends Fragment {
//
//    private Context context;
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_touzi_morexinxipilou, container, false);
//        ButterKnife.bind(this,view);
//        return view;
//    }
//
//    PublicshowAdapter publicshowAdapter;
//    ArrayList<PublicshowBean> arrayList = new ArrayList<>();
//    PrivateshowAdapter privateshowAdapter;
//    ArrayList<PrivateRenBean> arrayList2 = new ArrayList<>();
//    @BindView(R.id.gongkai_listview)
//    ListView listView;
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setinfo();
//        publicshowAdapter = new PublicshowAdapter(context,arrayList);
//        privateshowAdapter = new PrivateshowAdapter(context,arrayList2);
//    }
//
//    /**
//     * 写入信息
//     */
//    @BindView(R.id.gongkaiziliao)
//    Button btn_gongkai;
//    @BindView(R.id.touzirenkejian)
//    Button btn_kejian;
//    @BindView(R.id.L_gongkai)
//    LinearLayout l_gongkai;
//    @BindView(R.id.L_kejian)
//    LinearLayout l_kejian;
//    @BindView(R.id.L_kejian_no)
//    LinearLayout l_no;
//    @BindView(R.id.gongkai_listview2)
//    ListView listView2;
//    private void setinfo() {
//        getprivate();
//        getpublic();
//        //点击公开资料
//        btn_gongkai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_gongkai.setBackgroundResource(R.mipmap.infobutton_click);
//                btn_gongkai.setTextColor(Color.parseColor("#FFFFFFFF"));
//                btn_kejian.setBackgroundResource(R.mipmap.infobutton_unclick);
//                btn_kejian.setTextColor(Color.parseColor("#6CBF85"));
//                l_gongkai.setVisibility(View.VISIBLE);
//                l_kejian.setVisibility(View.GONE);
//            }
//        });
//        //点击投资人可见
//        btn_kejian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_kejian.setBackgroundResource(R.mipmap.infobutton_click);
//                btn_kejian.setTextColor(Color.parseColor("#FFFFFFFF"));
//                btn_gongkai.setBackgroundResource(R.mipmap.infobutton_unclick);
//                btn_gongkai.setTextColor(Color.parseColor("#6CBF85"));
//                l_kejian.setVisibility(View.VISIBLE);
//                l_gongkai.setVisibility(View.GONE);
//
//                //判断当前用户是否认购了该项目
//                OkGo.post(Api.isrengou)
//                        .tag(this)
//                        .params("uuid",Userid)
//                        .params("schemeId",zhiid)
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("是否购买了",s);
//                                Gson gson1 = new Gson();
//                                TouzirenBean touzirenBean = gson1.fromJson(s,TouzirenBean.class);
//                                if (touzirenBean.getState()==1){
//                                    if (touzirenBean.getData()==null){
//                                        listView2.setVisibility(View.GONE);
//                                        l_no.setVisibility(View.VISIBLE);
//                                    }else {
//                                        l_no.setVisibility(View.GONE);
//                                        listView2.setVisibility(View.VISIBLE);
//
//                                    }
//
//                                }
//                            }
//                        });
//
//            }
//        });
//    }
//
//    /**
//     * 获取投资人的信息
//     */
//    private void getprivate() {
//        OkGo.post(Api.touzirenshow)
//                .tag(this)
//                .params("number",number10)
//                .params("uuid",uudi)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("投资人信息",s);
//                        Gson gson = new Gson();
//                        PrivateRenBean privateRenBean = gson.fromJson(s,PrivateRenBean.class);
//                        if (privateRenBean.getState()==1){
//                            if (privateRenBean.getData().size()>0){
//                                for (int i = 0; i < privateRenBean.getData().size(); i++) {
//                                    arrayList2.add(privateRenBean);
//                                }
//                                listView2.setAdapter(privateshowAdapter);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        ToastUtils.shortToast("获取数据异常");
//                    }
//                });
//    }
//
//    /**
//     * 获取公开资料的信息
//     */
//    private void getpublic() {
//        Log.e("订单编号",number10);
//        OkGo.post(Api.publicshow)
//                .tag(this)
//                .params("number",number10)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("全部信息",s);
//                        Gson gson = new Gson();
//                        PublicshowBean publicshowBean = gson.fromJson(s,PublicshowBean.class);
//                        if (publicshowBean.getState()==1){
//                            if (publicshowBean.getData().size()>0){
//                                for (int i = 0; i < publicshowBean.getData().size() ; i++) {
//                                    arrayList.add(publicshowBean);
//                                }
//                                listView.setAdapter(publicshowAdapter);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        ToastUtils.shortToast("获取数据异常");
//                    }
//                });
//    }

}
