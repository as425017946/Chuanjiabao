package friendgoods.vidic.com.generalframework.activity.wode;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.nick_name;

/**
 * 昵称页面
 */
public class NichengActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nicheng);
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
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("修改昵称");
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
                NichengActivity.this.finish();
            }
        });
    }

    /**
     * 修改昵称
     */
    @BindView(R.id.nick_edt)
    EditText edt_nick;
    @BindView(R.id.nick_img)
    ImageView img_back;
    @BindView(R.id.nick_btn)
    Button btn_tijiao;
    private void setinfo(){
        edt_nick.setText(nick_name);
        edt_nick.setSelection(edt_nick.getText().length());
        //清除按钮
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_nick.setText("");
            }
        });
        //绑定昵称
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_nick.getText().toString())){
                    ToastUtils.shortToast("请输入昵称");
                }else{
                    if (edt_nick.getText().length()>6){
                        ToastUtils.shortToast("输入的昵称长度不能大于6位");
                    }else{
                        OkGo.post(Api.nickname)
                                .tag(this)
                                .params("NickName",edt_nick.getText().toString())
                                .params("uuid",Userid)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Gson gson = new Gson();
                                        WechatBindBean wechatBindBean = gson.fromJson(s,WechatBindBean.class);
                                        if (wechatBindBean.getState()==1){
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    NichengActivity.this.finish();
                                                }
                                            },2000);
                                            nick_name = edt_nick.getText().toString();
                                            ToastUtils.longToast("昵称修改成功");
                                        }else{
                                            ToastUtils.shortToast(wechatBindBean.getMessage());
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}
