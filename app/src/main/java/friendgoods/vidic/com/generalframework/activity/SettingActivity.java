package friendgoods.vidic.com.generalframework.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.UserInfoBean;
import friendgoods.vidic.com.generalframework.BuildConfig;
import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.wode.GuanliAdddressActivity;
import friendgoods.vidic.com.generalframework.activity.wode.NichengActivity;
import friendgoods.vidic.com.generalframework.activity.wode.PhoneActivity;
import friendgoods.vidic.com.generalframework.activity.wode.ShezhiActivity;
import friendgoods.vidic.com.generalframework.activity.wode.ShimingOkActivity;
import friendgoods.vidic.com.generalframework.activity.wode.WeChatActivity;
import friendgoods.vidic.com.generalframework.faxian.FaqixiangmuActivity;
import friendgoods.vidic.com.generalframework.touzi.EmailActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.CameraSingleton;
import friendgoods.vidic.com.generalframework.util.CircleImageView;
import friendgoods.vidic.com.generalframework.util.MyDialog;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.TimeUtil;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import friendgoods.vidic.com.generalframework.util.getPhotoFromPhotoAlbum;
import okhttp3.Call;
import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;
import static friendgoods.vidic.com.generalframework.MainActivity.Userid;
import static friendgoods.vidic.com.generalframework.MainActivity.back;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.nick_name;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_head;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_phone;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_email;
import static friendgoods.vidic.com.generalframework.activity.fragment.FragmentMy.user_wechat;

public class SettingActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private File cameraSavePath;//拍照照片路径
    private Uri uri;
    String photoPath=""; //保存拍照或者选择相册返回的路径

    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private MyApplication myApplication;
    private SharedPreferencesHelper sharedPreferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        setTtitle();
        fanhui();
        SetTiaozhuan();
        sharedPreferencesHelper = new SharedPreferencesHelper(this,"chuanjiabao");
        getinfo();
    }

    @BindView(R.id.setting_isrenzheng)
    TextView tv_renzheng;
    private void getinfo() {

        //获取用户信息并存储
        OkGo.post(Api.myinfo)
                .tag(this)
                .params("uuid",sharedPreferencesHelper.getSharedPreference("userid","").toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        UserInfoBean userInfoBean = gson.fromJson(s,UserInfoBean.class);
                        if (userInfoBean.getState()==1){
                            Log.e("用户信息",s);
                            nick_name = userInfoBean.getData().getUser_nick_name();
                            user_head = userInfoBean.getData().getUser_head_portrait();
                            user_phone = userInfoBean.getData().getUser_phone();
                            user_email = userInfoBean.getData().getUser_Email();
                            user_wechat = userInfoBean.getData().getUser_WeChat();

                            //如果没有昵称直接显示手机号，如果有就显示昵称
                            if (TextUtils.isEmpty(nick_name) && !TextUtils.isEmpty(sharedPreferencesHelper.getSharedPreference("userphone","").toString())){
                                tv_shownick.setText(sharedPreferencesHelper.getSharedPreference("userphone","").toString());
                            }else if (!TextUtils.isEmpty(nick_name) || nick_name!=null ){
                                if (nick_name.length()>8){
                                    tv_shownick.setText(nick_name.substring(0,8)+"...");
                                }else {
                                    tv_shownick.setText(nick_name);
                                }
                            }
                        }else{
                            ToastUtils.shortToast(userInfoBean.getMessage());
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
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("个人设置");
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
                SettingActivity.this.finish();
            }
        });
    }

    /**
     * 头像
     * 昵称
     * 绑定手机
     * 绑定微信
     * 绑定邮箱
     * 收货地址
     * 投资人认证
     * 高级认证
     * 其他设置
     * 退出登录
     */
    @BindView(R.id.setting_headimg)
    CircleImageView cir_headimg;
    @BindView(R.id.setting_nicheng)
    LinearLayout r_nicheng;
    @BindView(R.id.setting_shownick)
    TextView tv_shownick;
    @BindView(R.id.setting_phone)
    LinearLayout r_phone;
    @BindView(R.id.setting_showphone)
    TextView tv_showphone;
    @BindView(R.id.setting_wechat)
    LinearLayout r_wechat;
    @BindView(R.id.setting_email)
    LinearLayout r_email;
    @BindView(R.id.setting_address)
    LinearLayout r_address;
    @BindView(R.id.setting_touzirenzheng)
    LinearLayout r_touzirenzheng;
    @BindView(R.id.setting_gaojirenzheng)
    LinearLayout r_gaojirenzheng;
    @BindView(R.id.setting_qitashezhi)
    LinearLayout r_qitashezhi;
    @BindView(R.id.setting_tuichudenglu)
    LinearLayout r_tuichudenglu;
    private void SetTiaozhuan(){


        //展示头像

        if(!TextUtils.isEmpty(user_head) || user_head!=null){
            String url = Api.ossurl+user_head;
            Glide.with(this).load(url).into(cir_headimg);
        }

        cir_headimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EasyPermissions.hasPermissions(SettingActivity.this, permissions)) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(SettingActivity.this);
                LayoutInflater inflater = LayoutInflater.from(SettingActivity.this);
                final View DialogView = inflater .inflate ( R.layout.headimg, null);//1、自定义布局
                TextView ok = (TextView) DialogView.findViewById(R.id.headimg_quxiao);//自定义控件
                TextView paizhao = (TextView) DialogView.findViewById(R.id.headimg_paizhao);//自定义控件
                TextView tuku = (TextView) DialogView.findViewById(R.id.headimg_tuku);//自定义控件
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();
                //点击取消
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                //拍照
                paizhao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       goCamera();//拍照
//                        CameraSingleton.getCameraSingleton().cameraPic(SettingActivity.this);//相机
                        dialog.dismiss();
                    }
                });
                //图库
                tuku.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goPhotoAlbum();
//                        CameraSingleton.getCameraSingleton().photoAlbum(SettingActivity.this);//相册
                        dialog.dismiss();
                    }
                });
                dialog.show();
            } else {
                //没有打开相关权限、申请权限
                EasyPermissions.requestPermissions(SettingActivity.this, "需要获取您的相册、照相使用权限", 1, permissions);
            }

            }
        });
        r_nicheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, NichengActivity.class);
                startActivity(intent);
            }
        });
        r_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, PhoneActivity.class);
                startActivity(intent);
            }
        });
        r_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, WeChatActivity.class);
                startActivity(intent);
            }
        });


        r_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });
        r_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, GuanliAdddressActivity.class);
                startActivity(intent);
            }
        });
        r_touzirenzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ShimingOkActivity.class);
                startActivity(intent);
            }
        });
        r_gaojirenzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        r_qitashezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ShezhiActivity.class);
                startActivity(intent);
            }
        });
        r_tuichudenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDialog.show(SettingActivity.this, "是否退出登录？", new MyDialog.OnConfirmListener() {
                    @Override
                    public void onConfirmClick() {
                        //移除本地存储数据
                        sharedPreferencesHelper.remove("userphone");
                        sharedPreferencesHelper.remove("userid");
                        //退出登录使用，0代表没有点击过退出登录按钮，1代表点击了退出登录
                        back = "3";
                        SettingActivity.this.finish();
                    }
                });



            }



        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        tv_shownick.setText(nick_name);
        if(!TextUtils.isEmpty(user_phone)){
            tv_showphone.setText(user_phone.substring(0,3)+"****"+user_phone.substring((user_phone.length()-3),user_phone.length()));
        }
        if (sharedPreferencesHelper.getSharedPreference("isrenzheng","").toString().equals("0")){
            tv_renzheng.setText("未实名认证");
        }else {
            tv_renzheng.setText("已实名认证");
        }
    }

    /**上传图片**/
    public void beginupload(String photourl) {
        LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(SettingActivity.this)
                .setMessage("头像上传中...")
                .setCancelable(false)
                .setCancelOutside(false);
        final LoadingDailog dialog=loadBuilder.create();
        dialog.show();
        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "jiaoyuvideo";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        final String objectname =df.format(new Date())+ ".png";

        final String url = startpoint +"."+ endpoint+"/"+ objectname;

        PutObjectRequest put = new PutObjectRequest(startpoint, objectname, photourl );
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {

//                arrayList.remove(i);
                Log.e("测试图片","https://jiaoyuvideo.oss-cn-beijing.aliyuncs.com/"+objectname);
                sethead(objectname);
                dialog.dismiss();

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ToastUtils.shortToast("图片上传失败导致信息无法发布");

                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    ToastUtils.shortToast("图片上传失败导致信息无法发布");
                }
                if (serviceException != null) {
                }
            }
        });

    }
    /**
     * 上传头像
     */
    private void sethead(String img){
        OkGo.post(Api.headimg)
                .tag(this)
                .params("uuid",Userid)
                .params("userHeadPortrait",img)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ToastUtils.shortToast("头像上传成功");
                    }
                });
    }

    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(SettingActivity.this, "friendgoods.vidic.com.generalframework.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        SettingActivity.this.startActivityForResult(intent, 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }
    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }


    /**
     * 授权照相机代码
     */
    Uri showimg;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            Log.d("拍照返回图片路径:", photoPath);
            Glide.with(SettingActivity.this).load(photoPath).into(cir_headimg);
            beginupload(photoPath);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            Glide.with(SettingActivity.this).load(photoPath).into(cir_headimg);
            beginupload(photoPath);
        }

    }

}