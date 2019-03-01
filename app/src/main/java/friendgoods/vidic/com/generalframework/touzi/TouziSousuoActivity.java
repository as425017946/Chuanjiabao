package friendgoods.vidic.com.generalframework.touzi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.GuanjianziBean;
import friendgoods.vidic.com.generalframework.Bean.SousuoShowBean;
import friendgoods.vidic.com.generalframework.Bean.TouziShowBean;
import friendgoods.vidic.com.generalframework.Bean.TouziYugaoBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.adapter.SousuoShowAdapter;
import friendgoods.vidic.com.generalframework.adapter.TouziShowAdapter;
import friendgoods.vidic.com.generalframework.adapter.TouziSousuoAdapter;
import friendgoods.vidic.com.generalframework.adapter.TouziYugaoAdapter;
import friendgoods.vidic.com.generalframework.touzi.more.TouziSousuoMoreActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import friendgoods.vidic.com.generalframework.view.XListView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 搜索界面
 */
public class TouziSousuoActivity extends BaseActivity implements XListView.IXListViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_sousuo);
        ButterKnife.bind(this);
        sousuo();
        quxiao();
        leixing();
        setonclickcity();
        guanjianzi();

        adapter = new SousuoShowAdapter(TouziSousuoActivity.this,arrayList);
    }

    /**
     * 关键字
     */
    @BindView(R.id.sousuo1)
    TextView tv_1;
    @BindView(R.id.sousuo2)
    TextView tv_2;
    @BindView(R.id.sousuo3)
    TextView tv_3;
    @BindView(R.id.sousuo4)
    TextView tv_4;
    @BindView(R.id.sousuo5)
    TextView tv_5;
    @BindView(R.id.sousuo6)
    TextView tv_6;
    @BindView(R.id.sousuo7)
    TextView tv_7;
    @BindView(R.id.sousuo8)
    TextView tv_8;
    private void guanjianzi() {
        OkGo.post(Api.guanjianzi)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GuanjianziBean guanjianziBean = gson.fromJson(s,GuanjianziBean.class);
                        if (guanjianziBean.getState()==1){
                            if (guanjianziBean.getData()!=null){
                                if (guanjianziBean.getData().size()>0){
                                    tv_1.setText(guanjianziBean.getData().get(0).getName());
                                    tv_1.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>1){
                                    tv_2.setText(guanjianziBean.getData().get(1).getName());
                                    tv_2.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>2){
                                    tv_3.setText(guanjianziBean.getData().get(2).getName());
                                    tv_3.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>3){
                                    tv_4.setText(guanjianziBean.getData().get(3).getName());
                                    tv_4.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>4){
                                    tv_5.setText(guanjianziBean.getData().get(4).getName());
                                    tv_5.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>5){
                                    tv_6.setText(guanjianziBean.getData().get(5).getName());
                                    tv_6.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>6){
                                    tv_7.setText(guanjianziBean.getData().get(6).getName());
                                    tv_7.setVisibility(View.VISIBLE);
                                }
                                if (guanjianziBean.getData().size()>7){
                                    tv_8.setText(guanjianziBean.getData().get(7).getName());
                                    tv_8.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                });
    }


    /**
     * 写入搜索信息的接口
     */
    private Handler mHandler;
    int pageSize=5,page=1;
    private void setinfo(final String state,final String type,final String btype,final String site) {

        OkGo.post(Api.sousuo)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("status",state)
                .params("type",type)
                .params("bType",btype)
                .params("site",site)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("搜信息",state+"*"+type+"*"+btype+"*"+site);
//                        Log.e("搜搜信息",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        SousuoShowBean yugaoBean = gson.fromJson(s,SousuoShowBean.class);
                        if (yugaoBean.getState()==1){
                           if (yugaoBean.getData()!=null){
                               if (yugaoBean.getData().getPageInfo().getList().size()>0){
                                   for (int i = 0; i <yugaoBean.getData().getPageInfo().getList().size() ; i++) {
                                       arrayList.add(yugaoBean);
                                   }

                                   listView1.setAdapter(adapter);
                                   if (pageSize>=yugaoBean.getData().getPageInfo().getTotal()){
                                       listView1.zhanshi(false);
                                   }else{
                                       listView1.zhanshi(true);
                                   }
                               }else{
                                   listView1.zhanshi(false);
                                   String[] empty = new String[0];
                                   ArrayAdapter<String> emptyadapter = new ArrayAdapter<String>(TouziSousuoActivity.this, R.layout.spinner_item2, empty);
                                   listView1.setAdapter(emptyadapter);
                                   ToastUtils.shortToast("暂无查询信息！");
                               }
                           }else {
                               listView1.zhanshi(false);
                           }



                        }else{
                            ToastUtils.shortToast(yugaoBean.getMessage());
                        }
                    }
                });
        mHandler = new Handler();
        listView1.setPullLoadEnable(true);
        listView1.setXListViewListener(this);
    }

    private void setinfo2(final String state,final String type,final String btype,final String site,int page2,int pageSize2) {

        OkGo.post(Api.sousuo)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("status",state)
                .params("type",type)
                .params("bType",btype)
                .params("site",site)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList.clear();
                        Gson gson = new Gson();
                        SousuoShowBean yugaoBean = gson.fromJson(s,SousuoShowBean.class);
                        if (yugaoBean.getState()==1){
//                            Log.e("长度",showBean.getData().getPageInfo().getList().size()+"");
                            if (yugaoBean.getData()!=null){
                                if (yugaoBean.getData().getPageInfo().getList().size()>0){
                                    for (int i = 0; i <yugaoBean.getData().getPageInfo().getList().size() ; i++) {
                                        arrayList.add(yugaoBean);
                                    }

                                    adapter.notifyDataSetChanged();

                                    if (pageSize>=yugaoBean.getData().getPageInfo().getTotal()){
                                        listView1.zhanshi(false);
                                    }else{
                                        listView1.zhanshi(true);
                                    }
                                }else{
                                    String[] empty = new String[0];
                                    ArrayAdapter<String> emptyadapter = new ArrayAdapter<String>(TouziSousuoActivity.this, R.layout.spinner_item2, empty);
                                    listView1.setAdapter(emptyadapter);
                                    ToastUtils.shortToast("暂无查询信息！");
                                }
                            }



                        }else{
                            ToastUtils.shortToast(yugaoBean.getMessage());
                        }
                    }
                });
    }
    /**
     * 搜索
     */
    @BindView(R.id.touzi_sousuoimg)
    LinearLayout sousuo_img;
    @BindView(R.id.sousuo_info)
    EditText edt_info;
    private void sousuo(){
        sousuo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UiUtils.isFastClick()==true){
//                    if (TextUtils.isEmpty(edt_info.getText().toString())){
//                        ToastUtils.shortToast("请输入搜索的项目名称或所在地");
//                    }else {
//                        l_city.setVisibility(View.GONE);
//                        listView1.setVisibility(View.VISIBLE);
//                        setinfo("","","",edt_info.getText().toString());
//                    }
                    //不输入查询所有
                    l_city.setVisibility(View.GONE);
                    listView1.setVisibility(View.VISIBLE);
                    setinfo("","","",edt_info.getText().toString());
                }else {
                    ToastUtils.shortToast("请勿短时间内重复点击");
                }



            }
        });
    }

    /***
     * 取消
     */
    @BindView(R.id.sousuo_quxiao)
    TextView quxiao;
    private void quxiao(){
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouziSousuoActivity.this.finish();
            }
        });
    }
    /**
     * 类型
     */
    @BindView(R.id.spinner1)
    Spinner sp1;
    @BindView(R.id.spinner2)
    Spinner sp2;
    @BindView(R.id.spinner3)
    Spinner sp3;
    @BindView(R.id.sousuo_city)
    LinearLayout l_city;
    @BindView(R.id.sousuo_list1)
    XListView listView1;
    ArrayList<SousuoShowBean> arrayList = new ArrayList<>();
    SousuoShowAdapter adapter;
    private List<String> data_list1,data_list2,data_list3;
    private ArrayAdapter<String> arr_adapter,arr_adapter2,arr_adapter3;
    String spinner1="",spinner3="";
    private void leixing(){
        //数据
        data_list1 = new ArrayList<String>();
        data_list1.add("项目状态");
        data_list1.add("预告中");
        data_list1.add("预约中");
        data_list1.add("认购中");
        data_list1.add("已完成");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list1);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp1.setAdapter(arr_adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ToastUtils.shortToast(i+"");
                 if (i!=0){
                     spinner1 = i+"";
                     l_city.setVisibility(View.GONE);
                     listView1.setVisibility(View.VISIBLE);
                     if (sp2.getSelectedItemId()==0){
                         setinfo(i+"","",spinner3,edt_info.getText().toString());
                     }else {
                         setinfo(i+"",sp2.getSelectedItem().toString(),spinner3,edt_info.getText().toString());
                     }
                 }else if (i==0){
                     spinner1 = "";
                     l_city.setVisibility(View.VISIBLE);
                     listView1.setVisibility(View.GONE);
                 }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //数据2
        data_list2 = new ArrayList<String>();
        data_list2.add("空间类型");
        data_list2.add("酒店");
        data_list2.add("民宿");
        data_list2.add("公寓");
        data_list2.add("更多");
        //适配器
        arr_adapter2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list2);
        //设置样式
        arr_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp2.setAdapter(arr_adapter2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ToastUtils.shortToast(i+"");
                if (i!=0){
                    if (sp2.getSelectedItemId()==0){
                        setinfo(spinner1,"",spinner3,edt_info.getText().toString());
                    }else {
                        setinfo(spinner1,sp2.getSelectedItem().toString()+"",spinner3,edt_info.getText().toString());
                    }

                    l_city.setVisibility(View.GONE);
                    listView1.setVisibility(View.VISIBLE);
                }else  if (i==0){
                    l_city.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //数据
        data_list3 = new ArrayList<String>();
        data_list3.add("投资类型");
        data_list3.add("股权");
        data_list3.add("收益权");
        data_list3.add("消费众筹");
        //适配器
        arr_adapter3= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list3);
        //设置样式
        arr_adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp3.setAdapter(arr_adapter3);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ToastUtils.shortToast(i+"");
                if (i!=0){
                    spinner3 = i+"";

                    setinfo(spinner1,"",i+"",edt_info.getText().toString());
                    l_city.setVisibility(View.GONE);
                    listView1.setVisibility(View.VISIBLE);
                }else  if (i==0){
                    spinner3 = "";
                    l_city.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                pageSize = 5;
                sp1.setSelection(0);
                sp2.setSelection(0);
                sp3.setSelection(0);
                setinfo("","","",edt_info.getText().toString());
                onLoad();
            }
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp2.getSelectedItemId()==0){
                    setinfo2(spinner1,"",spinner3,edt_info.getText().toString(),page ++,(pageSize+5));
                }else {
                    setinfo2(spinner1,sp2.getSelectedItem().toString(),spinner3,edt_info.getText().toString(),page ++,(pageSize+5));
                }

                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        listView1.stopRefresh();
        listView1.stopLoadMore();
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        //格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String time = formatter.format(curDate);
        listView1.setRefreshTime(time);
    }


    /**
     * 点击城市
     */
    private void setonclickcity() {
        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_1.getText().toString());
            }
        });
        tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_2.getText().toString());
            }
        });
        tv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_3.getText().toString());
            }
        });
        tv_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_4.getText().toString());
            }
        });
        tv_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_5.getText().toString());
            }
        });
        tv_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_6.getText().toString());
            }
        });
        tv_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_7.getText().toString());
            }
        });
        tv_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcity(tv_8.getText().toString());
            }
        });

    }

    private  void getcity(String zhi){
        edt_info.setText(zhi);
        edt_info.setSelection(edt_info.getText().length());
        if (sp1.getSelectedItemId()==0 && sp2.getSelectedItemId()==0 && sp3.getSelectedItemId()==0){
            setinfo("","","",zhi);
        }else if (sp1.getSelectedItemId()!=0 && sp2.getSelectedItemId()==0 && sp3.getSelectedItemId()==0){
            setinfo(spinner1+"","","",zhi);
        }else if (sp1.getSelectedItemId()!=0 && sp2.getSelectedItemId()!=0 && sp3.getSelectedItemId()==0){
            setinfo(spinner1+"",sp2.getSelectedItem().toString(),"",zhi);
        }else if (sp1.getSelectedItemId()!=0 && sp2.getSelectedItemId()!=0 && sp3.getSelectedItemId()!=0){
            setinfo(spinner1+"",sp2.getSelectedItem().toString(),sp3+"",zhi);
        }else if (sp1.getSelectedItemId()==0 && sp2.getSelectedItemId()!=0 && sp3.getSelectedItemId()==0){
            setinfo("",sp2.getSelectedItem().toString(),"",zhi);
        }else if (sp1.getSelectedItemId()==0 && sp2.getSelectedItemId()!=0 && sp3.getSelectedItemId()!=0){
            setinfo("",sp2.getSelectedItem().toString(),sp3+"",zhi);
        }else if (sp1.getSelectedItemId()==0 && sp2.getSelectedItemId()==0 && sp3.getSelectedItemId()!=0){
            setinfo("","",sp3+"",zhi);
        }

        l_city.setVisibility(View.GONE);
        listView1.setVisibility(View.VISIBLE);
    }
}
