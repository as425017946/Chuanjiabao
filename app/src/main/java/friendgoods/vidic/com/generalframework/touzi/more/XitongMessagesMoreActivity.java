package friendgoods.vidic.com.generalframework.touzi.more;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.TishiBean;
import friendgoods.vidic.com.generalframework.Bean.XitongBean;
import friendgoods.vidic.com.generalframework.MainActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.LoginActivity;
import friendgoods.vidic.com.generalframework.adapter.XitongMessageAdapter;
import friendgoods.vidic.com.generalframework.adapter.XitongMessageMoreAdapter;
import friendgoods.vidic.com.generalframework.touzi.XitongMessagesActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

public class XitongMessagesMoreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xitong_messages_more);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setClean();
        setDelete();
        setinfo();
    }

    /**
     * 展示系统消息
     */
    ArrayList<XitongBean> arrayList = new ArrayList<>();
    XitongMessageMoreAdapter adapter;
    @BindView(R.id.xitong_more_listview)
    ListView listView;
    int suoyouxinxi=0;
    private void setinfo() {
        OkGo.post(Api.message_show)
                .tag(this)
                .params("page",1)
                .params("pageSize",100)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("系统",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        XitongBean xitongBean = gson.fromJson(s,XitongBean.class);
                        if (xitongBean.getState()==1){
                            if (xitongBean.getData().getPageInfo().getList().size()>0){
                                suoyouxinxi=xitongBean.getData().getPageInfo().getList().size();
                                for (int i = 0; i <xitongBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(xitongBean);
                                }
                                adapter = new XitongMessageMoreAdapter(XitongMessagesMoreActivity.this,arrayList);
                                listView.setAdapter(adapter);
                            }else{
                                suoyouxinxi=0;
                            }
                        }
                    }
                });
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tquxiao;
    private void setTtitle(){
        ttitle.setText("系统消息");
        tquxiao.setText("取消");
    }
    /**
     * 返回
     * 管理
     */
    @BindView(R.id.touzi_fanhui)
    LinearLayout lfanhui;
    @BindView(R.id.touzi_quxiao)
    LinearLayout guanli;
    private void fanhui(){
        lfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XitongMessagesMoreActivity.this.finish();


            }
        });
        guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XitongMessagesMoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 清空按钮
     */
    @BindView(R.id.xitong_clean)
    TextView clean;
    private void setClean(){
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suoyouxinxi==0){
                    ToastUtils.shortToast("没有信息可删除");
                }else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(XitongMessagesMoreActivity.this);
                    LayoutInflater inflater = LayoutInflater.from(XitongMessagesMoreActivity.this);
                    final View DialogView = inflater .inflate ( R.layout.tishibutton, null);//1、自定义布局
                    TextView title = (TextView) DialogView.findViewById(R.id.tishibutton_title);//自定义控件
                    TextView message = (TextView) DialogView.findViewById(R.id.tishibutton_message);//自定义控件
                    message.setText("是否清空所有信息？");
                    TextView back = (TextView) DialogView.findViewById(R.id.tishibutton_back);//自定义控件
                    TextView ok = (TextView) DialogView.findViewById(R.id.tishibutton_ok);//自定义控件
                    builder.setView(DialogView);
                    final AlertDialog dialog = builder.create();
                    //点击取消
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    //点击确认
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            OkGo.post(Api.message_show)
                                    .tag(this)
                                    .params("page",1)
                                    .params("pageSize",100)
                                    .params("uuid",Userid)
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
//                                            Log.e("系统",s);
                                            LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(XitongMessagesMoreActivity.this)
                                                    .setMessage("信息删除中...")
                                                    .setCancelable(false)
                                                    .setCancelOutside(false);
                                            final LoadingDailog dialog=loadBuilder.create();
                                            dialog.show();
                                            arrayList.clear();
                                            Gson gson = new Gson();
                                            XitongBean xitongBean = gson.fromJson(s,XitongBean.class);
                                            if (xitongBean.getState()==1){
                                                if (xitongBean.getData().getPageInfo().getList().size()>0){
                                                    for (int i = 0; i <xitongBean.getData().getPageInfo().getList().size() ; i++) {
                                                        deletecaozuo(xitongBean.getData().getPageInfo().getList().get(i).getId());
                                                        if (i==xitongBean.getData().getPageInfo().getList().size()){
                                                            ToastUtils.shortToast("删除成功！");
                                                            dialog.dismiss();
                                                            XitongMessagesMoreActivity.this.finish();
                                                        }
                                                    }

                                                }else{
                                                    suoyouxinxi=0;
                                                }
                                            }
                                        }
                             });
                        }
                    });
                    dialog.show();
                }



            }
        });
    }

    /**
     * 删除操作
     */
    private void deletecaozuo(int jihe){
        for (int i = 0; i <jihe ; i++) {
            final int jihe2 = i;
            OkGo.post(Api.message_delete)
                    .tag(this)
                    .params("id",jihe)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
//                                                ToastUtils.shortToast("");
                            Log.e("删除",s);
                            Gson gson = new Gson();
                            TishiBean tishiBean = gson.fromJson(s,TishiBean.class);
                            if (tishiBean.getState()==1){

                            }else{
                                ToastUtils.shortToast(tishiBean.getMessage());
                            }
                        }
                    });

        }
    }

    /**
     * 删除按钮
     */
    @BindView(R.id.xitong_delete)
    TextView delete;
    private void setDelete(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suoyouxinxi==0){
                    ToastUtils.shortToast("没有信息可删除");
                }else{


                    final AlertDialog.Builder builder = new AlertDialog.Builder(XitongMessagesMoreActivity.this);
                    LayoutInflater inflater = LayoutInflater.from(XitongMessagesMoreActivity.this);
                    final View DialogView = inflater .inflate ( R.layout.tishibutton, null);//1、自定义布局
                    TextView title = (TextView) DialogView.findViewById(R.id.tishibutton_title);//自定义控件
                    TextView message = (TextView) DialogView.findViewById(R.id.tishibutton_message);//自定义控件
                    message.setText("是否删除选中的信息？");
                    TextView back = (TextView) DialogView.findViewById(R.id.tishibutton_back);//自定义控件
                    TextView ok = (TextView) DialogView.findViewById(R.id.tishibutton_ok);//自定义控件
                    builder.setView(DialogView);
                    final AlertDialog dialog = builder.create();
                    //点击取消
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    //点击确认
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                        ToastUtils.shortToast(adapter.xiaoxizhi);

                            if (adapter.xiaoxizhi==""){
                                ToastUtils.shortToast("请选择要删除的信息");
                            }else{
                                final String[] shanchu= adapter.xiaoxizhi.split(",");
                                for (int c = 0; c <shanchu.length ; c++) {
                                    final int zhi=c;
                                    OkGo.post(Api.message_delete)
                                            .tag(this)
                                            .params("id",shanchu[c])
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onSuccess(String s, Call call, Response response) {
//                                                ToastUtils.shortToast("");
                                                    Log.e("删除",s);
                                                    Gson gson = new Gson();
                                                    TishiBean tishiBean = gson.fromJson(s,TishiBean.class);
                                                    if (tishiBean.getState()==1){
                                                        ToastUtils.shortToast("删除成功！");
                                                        setinfo();
                                                    }else{
                                                        ToastUtils.shortToast(tishiBean.getMessage());
                                                    }
                                                }
                                            });

                                }

                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }


            }
        });
    }
}
