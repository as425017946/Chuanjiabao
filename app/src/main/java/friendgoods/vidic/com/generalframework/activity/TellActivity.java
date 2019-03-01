package friendgoods.vidic.com.generalframework.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.util.IntentUtils;
import friendgoods.vidic.com.generalframework.util.ToastUtils;

/**
 * 联系我们
 */
public class TellActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout tellOpinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell);
        ButterKnife.bind(this);
        tellOpinion = findViewById(R.id.tell_opinion);
        tellOpinion.setOnClickListener(this);
        setTtitle();
        fanhui();
        setinfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tell_opinion:
                IntentUtils.startActivity(this, FeedbackActivity.class, true);
                break;
        }
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;

    private void setTtitle() {
        ttitle.setText("联系我们");
    }

    /**
     * 返回
     */
    @BindView(R.id.touzi_fanhui)
    LinearLayout lfanhui;

    private void fanhui() {
        lfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TellActivity.this.finish();
            }
        });
    }

    /**
     * 在线客服
     * 电话客服
     */
    @BindView(R.id.tell_tell)
    RelativeLayout rl_tell;
    @BindView(R.id.tell_tell2)
    TextView tv_tell;
    @BindView(R.id.tell_weixinhao)
    RelativeLayout ll_weixinhao;
    @BindView(R.id.tell_gongzhonghao)
    RelativeLayout ll_gongzhonghao;
    int bodadianhua=0;
    private void setinfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(TellActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) TellActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        2);
            }else {
                bodadianhua=2;
//                showToast("权限已申请");
            }
        }
        rl_tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bodadianhua!=0){
                    Intent intent = new Intent();

                    intent.setAction(Intent.ACTION_CALL);

                    intent.setData(Uri.parse("tel:"+ "022-25288258"));

                    startActivity(intent);
                }else {
                    ToastUtils.shortToast("请在设置里面打开拨打电话的权限");
                }
            }
        });
        ll_weixinhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("copy from demo", "weixinhao");
                mClipboardManager.setPrimaryClip(clipData);
                ToastUtils.shortToast("复制成功！");
            }
        });
        ll_gongzhonghao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("copy from demo", "weixinhao");
                mClipboardManager.setPrimaryClip(clipData);
                ToastUtils.shortToast("复制成功！");
            }
        });

    }

    /**
     * 回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==2){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                ToastUtils.shortToast("权限已开启");
                bodadianhua = 2;
            } else {
                bodadianhua = 0;
                ToastUtils.shortToast("拨打电话权限已拒绝");
            }
        }
    }
}