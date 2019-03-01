package friendgoods.vidic.com.generalframework.activity.wode;

import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.MainActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import friendgoods.vidic.com.generalframework.widget.PayPasswordDialog;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.balance;

/**
 * 提现
 */
public class TixianActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setinfo();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    private void setTtitle(){
        ttitle.setText("余额提现");
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
                TixianActivity.this.finish();
            }
        });
    }

    /**
     * 绑定数据
     */
    @BindView(R.id.tixian_jine)
    EditText edt_jine;
    @BindView(R.id.tixian_tijiao)
    Button btn_tijiao;
    @BindView(R.id.tixian_showjine)
    TextView tv_showjine;
    @BindView(R.id.tixian_quanbu)
    TextView tv_qunbu;
    public String tixianjine = "";
    private void setinfo(){
        if (TextUtils.isEmpty(balance)){
            tv_showjine.setText("0.0");
        }else {
            tv_showjine.setText(balance);
        }
        tv_qunbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_jine.setText(tv_showjine.getText().toString());
                edt_jine.setSelection(edt_jine.getText().length());
            }
        });
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UiUtils.isFastClick()){
                    if (TextUtils.isEmpty(edt_jine.getText())){
                        ToastUtils.shortToast("请输入要充值的金额");
                    }else{
                        if (Double.parseDouble(tv_showjine.getText().toString())<Double.parseDouble(edt_jine.getText().toString())){
                            ToastUtils.shortToast("余额不足");
                        }else if (Double.parseDouble(edt_jine.getText().toString())==0){
                            ToastUtils.shortToast("输入的金额无效");
                        }else{
                            final PayPasswordDialog dialog=new PayPasswordDialog(TixianActivity.this,R.style.mydialog);
                            dialog.setDialogClick(new PayPasswordDialog.DialogClick() {
                                @Override
                                public void doConfirm(String password) {
                                    dialog.dismiss();
//                                    Toast.makeText(TixianActivity.this,password,Toast.LENGTH_LONG).show();
                                    tixian(password);
                                }
                            });
                            dialog.show();

                        }

                    }
                }else {
                    ToastUtils.shortToast("短时间内请勿重复点击");
                }

            }
        });
    }



    private void tixian(String zhi){
        OkGo.post(Api.chongzhi)
                .tag(this)
                .params("uuid",Userid)
                .params("money",edt_jine.getText().toString())
                .params("parameterPassWord",zhi)
                .params("newParameterPassWord",zhi)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                        if (wx.getState()==1){
                            ToastUtils.shortToast("提现成功");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    TixianActivity.this.finish();
                                }
                            },2000);
                        }else {
                            ToastUtils.shortToast(wx.getMessage());
                        }
                    }
                });
    }

}
