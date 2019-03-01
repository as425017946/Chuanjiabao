package friendgoods.vidic.com.generalframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.DatiBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.MyDialog;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 风险测评答题
 */
public class EvaluationActivity extends BaseActivity{

    private TextView evaluationNext;
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        evaluationNext = findViewById(R.id.evaluation_next);
        sharehelper = new SharedPreferencesHelper(EvaluationActivity.this,"chuanjiabao");
//        evaluationNext.setOnClickListener(this);
        setinfo();
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.evaluation_next:
//                IntentUtils.startActivity(this,EvaluationResultActivity.class,true);
//                break;
//
//        }
//    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("风险测评");
    }
    /**
     * 返回
     */
    @BindView(R.id.touzi_fanhui)
    LinearLayout lfanhui;
    private void fanhui(){
        lfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.show(EvaluationActivity.this, "答题尚未结束，是否退出？", new MyDialog.OnConfirmListener() {
                    @Override
                    public void onConfirmClick() {
                        EvaluationActivity.this.finish();
                    }
                });



            }
        });
    }


    /**
     * 写入信息
     */
    @BindView(R.id.fxts_dangqianye)
    TextView tv_dangqian;
    @BindView(R.id.fxts_zongye)
    TextView tv_zongye;
    @BindView(R.id.fxts_title)
    TextView tv_title;
    @BindView(R.id.evaluation_up)
    TextView tv_up;
    @BindView(R.id.evaluation_ok)
    TextView tv_ok;
    private int page=0,pageSize=20;

    /**
     * 动态添加控件到LinearLayout
     */
    @BindView(R.id.evaluation_linearlayout)
    LinearLayout linearLayout;

    //15道题+评测结果
    private int[] ti= new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int pingce = 0;

    private void setinfo(){

        OkGo.post(Api.shwodati)
                .tag(this)
                .params("page",page+1)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("答题",s);
                        Gson gson = new Gson();
                        final DatiBean datiBean = gson.fromJson(s,DatiBean.class);

                        if (datiBean.getState()==1){
                            tv_dangqian.setText("1");
                            tv_zongye.setText("/"+datiBean.getData().getPageInfo().getList().size()+"");
                            tv_title.setText(datiBean.getData().getPageInfo().getList().get(0).getTopic());
                            tv_up.setVisibility(View.GONE);
                            //动态添加信息
                            LinearLayout layout = new LinearLayout(EvaluationActivity.this); //实例化布局对象
                            RadioGroup group = new RadioGroup(EvaluationActivity.this); //实例化单选按钮组
                            if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(0).getAnswer_a())){
                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                radio.setText(datiBean.getData().getPageInfo().getList().get(0).getAnswer_a());
                                group.addView(radio);
                            } if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(0).getAnswer_b())){
                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                radio.setText(datiBean.getData().getPageInfo().getList().get(0).getAnswer_b());
                                group.addView(radio);
                            }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(0).getAnswer_c())){
                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                radio.setText(datiBean.getData().getPageInfo().getList().get(0).getAnswer_c());
                                group.addView(radio);
                            }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(0).getAnswer_d())){
                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                radio.setText(datiBean.getData().getPageInfo().getList().get(0).getAnswer_d());
                                group.addView(radio);
                            }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(0).getAnswer_e())){
                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                radio.setText(datiBean.getData().getPageInfo().getList().get(0).getAnswer_e());
                                group.addView(radio);
                            }
                            //处理点击事件
                            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                @Override
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                    ToastUtils.shortToast(checkedId+"");
                                    ti[0] = checkedId%5;;
                                    // TODO Auto-generated method stub
                                    RadioButton tempButton = (RadioButton)findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                                    // 以下就可以对这个RadioButton进行处理了

                                }
                            });
                            //将单选按钮组添加到布局中
                            layout.addView(group);
                            linearLayout.addView(layout);

                            //上一题
                            tv_up.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (UiUtils.isFastClick2()){
                                        //在展示上一题的时候先 移除本题信息
                                        linearLayout.removeViewAt((0));

                                        page--;
                                        tv_dangqian.setText((page+1)+"");
                                        tv_zongye.setText("/"+datiBean.getData().getPageInfo().getList().size()+"");
                                        tv_title.setText(datiBean.getData().getPageInfo().getList().get(page).getTopic());

                                        if (page==0){
                                            tv_up.setVisibility(View.GONE);
                                        }else{
                                            tv_up.setVisibility(View.VISIBLE);
                                            evaluationNext.setVisibility(View.VISIBLE);
                                            tv_ok.setVisibility(View.GONE);
                                        }
                                        LinearLayout layout = new LinearLayout(EvaluationActivity.this); //实例化布局对象
                                        RadioGroup group = new RadioGroup(EvaluationActivity.this); //实例化单选按钮组
                                        //展示下一道题
                                        if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_a())){
                                            RadioButton radio = new RadioButton(EvaluationActivity.this);
                                            radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_a());
                                            group.addView(radio);
                                        } if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_b())){
                                            RadioButton radio = new RadioButton(EvaluationActivity.this);
                                            radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_b());
                                            group.addView(radio);
                                        }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_c())){
                                            RadioButton radio = new RadioButton(EvaluationActivity.this);
                                            radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_c());
                                            group.addView(radio);
                                        }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_d())){
                                            RadioButton radio = new RadioButton(EvaluationActivity.this);
                                            radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_d());
                                            group.addView(radio);
                                        }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_e())){
                                            RadioButton radio = new RadioButton(EvaluationActivity.this);
                                            radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_e());
                                            group.addView(radio);
                                        }
                                        //处理点击事件
//                                        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//                                            @Override
//                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
////                                                ToastUtils.shortToast(checkedId+"");
//                                                ti[page]  = checkedId%5;
////                                                ToastUtils.shortToast(ti[page]+"第二");
//                                                // TODO Auto-generated method stub
//                                                RadioButton tempButton = (RadioButton)findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
//                                                // 以下就可以对这个RadioButton进行处理了
//
//                                            }
//                                        });
                                        //将单选按钮组添加到布局中
                                        layout.removeView(group);
                                        layout.addView(group);
                                        linearLayout.addView(layout);
                                    }else {
                                        ToastUtils.shortToast("短时间内请勿重复点击");
                                    }

                                }
                            });
                            //下一题
                            evaluationNext.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (UiUtils.isFastClick2()){
                                        if (ti[page]==0){
                                            ToastUtils.shortToast("请至少选一项答案");
                                        }else{
//                                        Log.e("风险提示0",page+"");

                                            //在展示下一题的时候先 移除本题信息
                                            linearLayout.removeViewAt((0));

                                            page++;
//                                        Log.e("风险提示",page+"");
                                            tv_dangqian.setText((page+1)+"");
                                            tv_zongye.setText("/"+datiBean.getData().getPageInfo().getList().size()+"");
                                            tv_title.setText(datiBean.getData().getPageInfo().getList().get(page).getTopic());
                                            if (page==0){
                                                tv_up.setVisibility(View.GONE);
                                            }else{
                                                tv_up.setVisibility(View.VISIBLE);
                                            }
                                            if ((page+1)>=datiBean.getData().getPageInfo().getList().size()){
                                                tv_up.setVisibility(View.VISIBLE);
                                                evaluationNext.setVisibility(View.GONE);
                                                tv_ok.setVisibility(View.VISIBLE);
                                            }else{
                                                evaluationNext.setVisibility(View.VISIBLE);
                                                tv_ok.setVisibility(View.GONE);
                                            }

                                            //完成答题
                                            tv_ok.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (ti[page]==0){
                                                        ToastUtils.shortToast("请至少选一项答案");
                                                    }else{

                                                        //上传用户答案
                                                        Log.e("答题",ti[0]%5+"第2题"+ti[1]%5+"第三题*"+ti[2]%5+"第四题*"+ti[3]%5
                                                        +"第五题*"+ti[4]%5+"第六题*"+ti[5]%5+"第七题*"+ti[6]%5
                                                        +"第八题*"+ti[7]%5+"第九题*"+ti[8]%5+"第10题*"+ti[9]%5
                                                        +"第十一题*"+ti[10]%5+"第十2题*"+ti[11]%5+"第十3题*"+ti[12]%5
                                                        +"第十4题*"+ti[13]%5+"第十5题*"+ti[14]%5
                                                        +"uuid*"+Userid);
                                                        OkGo.post(Api.usercheckd)
                                                                .tag(this)
                                                                .params("uuid",Userid)
                                                                .params("flag1",ti[0]%5)
                                                                .params("flag2",ti[1]%5)
                                                                .params("flag3",ti[2]%5)
                                                                .params("flag4",ti[3]%5)
                                                                .params("flag5",ti[4]%5)
                                                                .params("flag6",ti[5]%5)
                                                                .params("flag7",ti[6]%5)
                                                                .params("flag8",ti[7]%5)
                                                                .params("flag9",ti[8]%5)
                                                                .params("flag10",ti[9]%5)
                                                                .params("flag11",ti[10]%5)
                                                                .params("flag12",ti[11]%5)
                                                                .params("flag13",ti[12]%5)
                                                                .params("flag14",ti[13]%5)
                                                                .params("flag15",ti[14]%5)
                                                                .execute(new StringCallback() {
                                                                    @Override
                                                                    public void onError(Call call, Response response, Exception e) {
                                                                        super.onError(call, response, e);
                                                                        ToastUtils.shortToast(e+"");
                                                                    }

                                                                    @Override
                                                                    public void onSuccess(String s, Call call, Response response) {
                                                                        Gson gson = new Gson();
                                                                        WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                                                                        if (wechatBindBean.getState()==1){
                                                                            sharehelper.put("flag5","1");
                                                                        }else {
                                                                            ToastUtils.shortToast(wechatBindBean.getMessage());
                                                                        }
                                                                    }
                                                                });



                                                        //谨慎型
                                                        if (ti[10]==1 || ti[10]==2 && ti[11]==1 || ti[11]==2  && ti[12]==1 || ti[12]==2
                                                                && ti[13]==1 || ti[13]==2  && ti[14]==1 || ti[14]==2 ){
                                                            setusertype(1);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","1");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }
                                                        //稳健型
                                                        if (ti[10]==3 || ti[11]==3  || ti[12]==3 || ti[13]==3 || ti[14]==3 ){
                                                            setusertype(2);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","2");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }
                                                        //进取型
                                                        if (ti[10]==1 || ti[10]==2 && ti[11]==4 || ti[11]==5  && ti[12]==4 || ti[12]==5
                                                                && ti[13]==4 || ti[13]==5  && ti[14]==4 || ti[14]==5){
                                                            setusertype(3);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","3");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==4 || ti[10]==5 && ti[11]==1 || ti[11]==2  && ti[12]==4 || ti[12]==5
                                                                && ti[13]==4 || ti[13]==5  && ti[14]==4 || ti[14]==5){
                                                            setusertype(3);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","3");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==4 || ti[10]==5 && ti[11]==4 || ti[11]==5  && ti[12]==1 || ti[12]==2
                                                                && ti[13]==4 || ti[13]==5  && ti[14]==4 || ti[14]==5){
                                                            setusertype(3);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","3");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==4 || ti[10]==5 && ti[11]==4 || ti[11]==5  && ti[12]==4 || ti[12]==5
                                                                && ti[13]==1 || ti[13]==2  && ti[14]==4 || ti[14]==5){
                                                            setusertype(3);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","3");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==4 || ti[10]==5 && ti[11]==4 || ti[11]==5  && ti[12]==4 || ti[12]==5
                                                                && ti[13]==4 || ti[13]==5  && ti[14]==1 || ti[14]==2){
                                                            setusertype(3);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","3");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }

                                                        //激进型
                                                        if (ti[10]==5 || ti[11]==5  || ti[12]==5 || ti[13]==5 || ti[14]==5){
                                                            setusertype(4);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","4");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==4 || ti[11]==5  || ti[12]==5 || ti[13]==5 || ti[14]==5){
                                                            setusertype(4);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","4");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==5 || ti[11]==4  || ti[12]==5 || ti[13]==5 || ti[14]==5){
                                                            setusertype(4);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","4");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else if (ti[10]==5 || ti[11]==5  || ti[12]==4 || ti[13]==5 || ti[14]==5){
                                                            setusertype(4);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","4");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }
                                                        if (ti[10]==5 || ti[11]==5  || ti[12]==5 || ti[13]==4 || ti[14]==5){
                                                            setusertype(4);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","4");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }else{
                                                            setusertype(2);
                                                            Intent intent = new Intent(EvaluationActivity.this,EvaluationResultActivity.class);
                                                            intent.putExtra("ceping","2");
                                                            EvaluationActivity.this.finish();
                                                            startActivity(intent);
                                                        }


                                                    }
                                                }
                                            });

                                            LinearLayout layout = new LinearLayout(EvaluationActivity.this); //实例化布局对象
                                            RadioGroup group = new RadioGroup(EvaluationActivity.this); //实例化单选按钮组
                                            layout.removeView(group);
                                            //展示下一道题
                                            if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_a())){
                                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                                radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_a());
                                                group.addView(radio);
                                            } if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_b())){
                                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                                radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_b());
                                                group.addView(radio);
                                            }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_c())){
                                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                                radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_c());
                                                group.addView(radio);
                                            }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_d())){
                                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                                radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_d());
                                                group.addView(radio);
                                            }if (!TextUtils.isEmpty(datiBean.getData().getPageInfo().getList().get(page).getAnswer_e())){
                                                RadioButton radio = new RadioButton(EvaluationActivity.this);
                                                radio.setText(datiBean.getData().getPageInfo().getList().get(page).getAnswer_e());
                                                group.addView(radio);
                                            }
                                            //处理点击事件
                                            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                    //                                    ToastUtils.shortToast(checkedId+"");
                                                    ti[page]  = checkedId;
                                                    // TODO Auto-generated method stub
                                                    RadioButton tempButton = (RadioButton)findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                                                    // 以下就可以对这个RadioButton进行处理了

                                                }
                                            });
                                            //将单选按钮组添加到布局中
                                            layout.addView(group);
                                            linearLayout.addView(layout);
//                                        linearLayout.addView(layout);


                                        }


                                    }else {
                                        ToastUtils.shortToast("短时间内请勿重复点击");
                                    }

                                }
                            });

                        }else {
                            ToastUtils.shortToast("服务器请求失败，请稍后重试");
                        }
                    }
                });
    }

    private void setusertype(int a){
        //上传用户选择后的类型信息
        OkGo.post(Api.usertype)
                .tag(this)
                .params("id",Userid)
                .params("type",a)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
}