package friendgoods.vidic.com.generalframework.activity.wode;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.Bean.RenzhengShowBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.BuildConfig;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.LoginActivity;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.CameraSingleton;
import friendgoods.vidic.com.generalframework.util.SharedPreferencesHelper;
import friendgoods.vidic.com.generalframework.util.TimeUtil;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;
import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 实名制
 */
public class ShimingOkActivity extends BaseActivity {
    private static final String TAG = "ShimingOkActivity";
    public static String isrenzheng = "0";
    SharedPreferencesHelper sharehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiming);
        ButterKnife.bind(this);
        CameraSingleton.getCameraSingleton().setCutPic_state(1);
        setTtitle();
        fanhui();
        setinfo();
        setshowinfo();
        sharehelper = new SharedPreferencesHelper(ShimingOkActivity.this,"chuanjiabao");
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle(){
        ttitle.setText("实名认证");
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
                ShimingOkActivity.this.finish();
            }
        });
    }

    /**
     * 写入信息
     */
    //用于图片是否上传了、照片1，照片2存值
    int zhaopian = 0,zhaopian2=0,zhaopian3=0;
    //定义用于存照片的uri
    Uri uri1,uri2;
    //用于存上传图片返回的功能
    String path1="",path2="";

    @BindView(R.id.shiming_name)
    EditText edt_name;
    @BindView(R.id.shiming_card)
    EditText edt_card;
    @BindView(R.id.shiming_tijiao)
    Button btn_tijiao;
    @BindView(R.id.shiming_zhengmian)
    ImageView img1;
    @BindView(R.id.shiming_fanmian)
    ImageView img2;
    @BindView(R.id.shiming_hangyetitle)
    TextView tv_hangyetitle;
    @BindView(R.id.shiming_hangyetishi)
    TextView tv_hangyetishi;
    @BindView(R.id.shiming_check)
    CheckBox checkBox;

    String zhi="";

    private LoadingDailog loadBuilder;
    private void showMyDialog(){
        if (loadBuilder==null){
            loadBuilder=new LoadingDailog.Builder(ShimingOkActivity.this)
                    .setMessage("信息上传中...")
                    .setCancelable(false)
                    .setCancelOutside(false).create();
            loadBuilder.show();
        }

    }
    private void dissDialog(){
        if (loadBuilder!=null){
            loadBuilder.dismiss();
        }
    }

    @BindView(R.id.zhucexieyi)
    TextView tv_guizetishi;

    private void setinfo(){
        //点击传家宝投资人规则
        tv_guizetishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ShimingOkActivity.this);
                LayoutInflater inflater = LayoutInflater.from(ShimingOkActivity.this);
                final View DialogView = inflater .inflate ( R.layout.zhucetishi, null);//1、自定义布局
                TextView ok = (TextView) DialogView.findViewById(R.id.fxts_show);//自定义控件
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();
                //点击取消
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        //身份证正面
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //照片1点击了就赋值,照片2清0
                zhaopian2 = 1;
                zhaopian3 = 0;
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ShimingOkActivity.this);
                LayoutInflater inflater = LayoutInflater.from(ShimingOkActivity.this);
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
                        onClickTakePhoto(view);
//                        CameraSingleton.getCameraSingleton().cameraPic(ShimingOkActivity.this);//相机
                        dialog.dismiss();
                    }
                });
                //图库
                tuku.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickOpenGallery(view);
//                        CameraSingleton.getCameraSingleton().photoAlbum(ShimingOkActivity.this);//相册
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //照片2点击了就赋值,照片1清0
                zhaopian3 = 1;
                zhaopian2 = 0;

                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ShimingOkActivity.this);
                LayoutInflater inflater = LayoutInflater.from(ShimingOkActivity.this);
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
                        onClickTakePhoto(view);
//                        CameraSingleton.getCameraSingleton().cameraPic(ShimingOkActivity.this);//相机
                        dialog.dismiss();
                    }
                });
                //图库
                tuku.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickOpenGallery(view);
//                        CameraSingleton.getCameraSingleton().photoAlbum(ShimingOkActivity.this);//相册
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UiUtils.isFastClick()){
                    if(TextUtils.isEmpty(edt_name.getText().toString())){
                        ToastUtils.shortToast("请输入姓名");
                    }else if (TextUtils.isEmpty(edt_card.getText().toString())){
                        ToastUtils.shortToast("请输入身份证号");
                    }else{
                        if (UiUtils.isLegalId(edt_card.getText().toString())==false){
                            ToastUtils.shortToast("输入的身份证号不正确");
                        }else {
                            if (uri1==null){
                                ToastUtils.shortToast("请上传身份证正面");
                            }else {
                                if (uri2==null){
                                    ToastUtils.shortToast("请上传身份证反面");
                                }else {
                                    if (tv_hangyetitle.getText().toString().equals("请选择行业背景")){
                                        ToastUtils.shortToast("请选择行业背景");
                                    }else {
                                        if (checkBox.isChecked()==false){
                                            ToastUtils.shortToast("请勾选传家宝投资人规则");
                                        }else {
                                            showMyDialog();
                                            huidiao();
                                        }
                                    }
                                }
                            }

                        }
                    }
                }else {
                    ToastUtils.shortToast("短时间内请勿多次点击");
                }


            }
        });
    }

    private void xuanzebeijing(){
        //行业背景选择
        tv_hangyetitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ShimingOkActivity.this);
                LayoutInflater inflater = LayoutInflater.from(ShimingOkActivity.this);
                final View DialogView = inflater .inflate (R.layout.bankinfo, null);//1、自定义布局
                Button ok = (Button) DialogView.findViewById(R.id.bank_ok);//自定义控件
                final RadioGroup radioGroup = (RadioGroup)DialogView.findViewById(R.id.radiogrop1);
                builder.setView(DialogView);
                final android.support.v7.app.AlertDialog dialog = builder.create();

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        View checkView = radioGroup.findViewById(i);

                        if (!checkView.isPressed()){
                            return;
                        }
                        switch (i){
                            case R.id.radiobutton1:
                                zhi= "互联网 it 电子 通信";
                                break;
                            case R.id.radiobutton2:
                                zhi= "广告 传媒 文化 体育";
                                break;
                            case R.id.radiobutton3:
                                zhi= "金融";
                                break;
                            case R.id.radiobutton4:
                                zhi= "教育 培训";
                                break;
                            case R.id.radiobutton5:
                                zhi= "制药 医疗";
                                break;
                            case R.id.radiobutton6:
                                zhi= "交通 物流 贸易 零售";
                                break;
                            case R.id.radiobutton7:
                                zhi= "专业服务";
                                break;
                            case R.id.radiobutton8:
                                zhi= "房地产 建筑";
                                break;
                            case R.id.radiobutton9:
                                zhi= "汽车";
                                break;
                            case R.id.radiobutton10:
                                zhi= "机械 制造";
                                break;
                            case R.id.radiobutton11:
                                zhi= "消费品";
                                break;
                            case R.id.radiobutton12:
                                zhi= "服务业";
                                break;
                            case R.id.radiobutton13:
                                zhi= "能源 化工 ";
                                break;
                            case R.id.radiobutton14:
                                zhi= "其他";
                                break;
                        }
//                        Log.e("值",zhi);
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if (zhi==""){
                            tv_hangyetitle.setText("请选择行业背景");
                            tv_hangyetishi.setVisibility(View.VISIBLE);
                        }else {
                            tv_hangyetitle.setText(zhi);
                            tv_hangyetishi.setVisibility(View.GONE);
                        }

//                        Log.e("值2",zhi+"");
                    }
                });
                dialog.show();
            }
        });
    }


    /**
     * 上传图片回调
     */
    private void huidiao(){
        Log.e(TAG, "huidiao: "+zhaopian );
        if (zhaopian==0){
            beginupload(uri1,0);
        }else if (zhaopian==1){
            beginupload(uri2,1);
        }else if (zhaopian==2){
            upinfo();
        }
    }

    /**
     * 上传信息
     */
    private void upinfo(){
        Log.e(TAG, "upinfo: "+Userid+"身份证正面"+ path1+"身份证反面"+path2
        +"行业背景"+tv_hangyetitle.getText().toString()+"名字"+edt_name.getText().toString()
        +"身份证号"+edt_card.getText().toString());
        OkGo.post(Api.touzirenzheng)
                .tag(this)
                .params("uuid",Userid)
                .params("photoA",path1)
                .params("photoB",path2)
                .params("job",tv_hangyetitle.getText().toString())
                .params("name",edt_name.getText().toString())
                .params("code",edt_card.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                        if (wx.getState()==1){
                            dissDialog();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ShimingOkActivity.this.finish();
                                }
                            },2000);
                            sharehelper.put("isrenzheng","1");
                            ToastUtils.longToast("信息上传成功");
                        }else{
                            dissDialog();
                            ToastUtils.shortToast(wx.getMessage());
                        }
                    }
                });
    }


    //展示信息
    @BindView(R.id.shiming_noshiming)
    TextView tv_noshiming;
    @BindView(R.id.llguize)
    LinearLayout llguize;
    private void setshowinfo(){
        OkGo.post(Api.touzirenzheng_show)
                .tag(this)
                .params("uuid",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: "+s );
                        Gson gson = new Gson();
                        RenzhengShowBean rzbean = gson.fromJson(s,RenzhengShowBean.class);
                        if (rzbean.getState()==1){
                           if (rzbean.getData().getFlag2().equals("1")){
                               sharehelper.put("isrenzheng","1");
                               llguize.setVisibility(View.GONE);
                               btn_tijiao.setVisibility(View.GONE);
                               tv_noshiming.setVisibility(View.GONE);
                               ttitle.setText("已完成认证");
                               edt_name.setText(rzbean.getData().getUser_name());
//                               edt_name.setSelection(edt_name.getText().length());
                               edt_name.setEnabled(false);
//                               edt_card.setText(rzbean.getData().getUser_detail_code().substring(0,3)+"****"+rzbean.getData().getUser_detail_code().substring((rzbean.getData().getUser_detail_code().length()-4),rzbean.getData().getUser_detail_code().length()));
                               edt_card.setText(rzbean.getData().getUser_detail_code());
                               edt_card.setSelection(edt_card.getText().length());
                               edt_card.setEnabled(false);
                               Glide.with(ShimingOkActivity.this)
                                       .load(Api.ossurl+rzbean.getData().getUser_photo_a())
                                       .placeholder(R.mipmap.projectshow)
                                       .error(R.mipmap.projectshow)
                                       .diskCacheStrategy(DiskCacheStrategy.NONE)
                                       .into(img1);
                               Glide.with(ShimingOkActivity.this)
                                       .load(Api.ossurl+rzbean.getData().getUser_photo_b())
                                       .placeholder(R.mipmap.projectshow)
                                       .error(R.mipmap.projectshow)
                                       .diskCacheStrategy(DiskCacheStrategy.NONE)
                                       .into(img2);
                                tv_hangyetishi.setVisibility(View.GONE);
                                tv_hangyetitle.setText(rzbean.getData().getUser_job()+"");
                                tv_hangyetitle.setFocusable(false);
                           }else {
                               sharehelper.put("isrenzheng","0");
                               xuanzebeijing();
                               tv_hangyetitle.setFocusable(true);
                               llguize.setVisibility(View.VISIBLE);
                               tv_noshiming.setVisibility(View.VISIBLE);
                               btn_tijiao.setVisibility(View.VISIBLE);
                               tv_hangyetishi.setVisibility(View.VISIBLE);
                           }
                        }else {
                            ToastUtils.shortToast("请求失败");
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }
                });
    }

//    /**
//     * 授权照相机代码
//     */
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK){
//            switch (requestCode){
//                case CameraSingleton.SCAN_OPEN_PHONE://相册
//                    Uri pic = CameraSingleton.getCameraSingleton().startTailor(ShimingOkActivity.this,data);
//                    if (zhaopian2==1){
//                        img1.setImageURI(pic);
//                        uri1 = pic;
//                    }
//                    if (zhaopian3==1){
//                        img2.setImageURI(pic);
//                        uri2 = pic;
//                    }
//                    break;
//                case CameraSingleton.PHONE_CAMERA: //相机返回的 uri
//                    Uri pic2=CameraSingleton.getCameraSingleton().returnPic(ShimingOkActivity.this);
//                    if (zhaopian2==1){
//                        img1.setImageURI(pic2);
//                        uri1 = pic2;
//                    }
//                    if (zhaopian3==1){
//                        img2.setImageURI(pic2);
//                        uri2 = pic2;
//                    }
//                    break;
//                case CameraSingleton.PHONE_CROP:
//                    if (zhaopian2==1){
//                        CameraSingleton.getCameraSingleton().setImageShow(img1,ShimingOkActivity.this);
//                    }
//                    if (zhaopian3==1){
//                        CameraSingleton.getCameraSingleton().setImageShow(img1,ShimingOkActivity.this);
//                    }
//
//                    break;
//                default:
//                    break;
//            }
//        }
//    }


    /**
     * 授权照相机代码
     */

    //打开相机的返回码
    private static final int CAMERA_REQUEST_CODE = 1;
    //选择图片的返回码
    private static final int IMAGE_REQUEST_CODE = 2;
    //剪切图片的返回码
    public static final int CROP_REREQUEST_CODE = 3;
//    private ImageView iv;

    //相机
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 100;

    public static final int REQUEST_CODE_PERMISSION_GALLERY = 101;

    //照片图片名
    private String photo_image;
    //截图图片名
    private String crop_image;

    //拍摄的图片的真实路径
    private String takePath;
    //拍摄的图片的虚拟路径
    private Uri imageUri;
    private Uri cropUri;
    //    private File tempFile = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    /**
     * 拍照
     *
     * @param view
     */
    public void onClickTakePhoto(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission(REQUEST_CODE_PERMISSION_CAMERA);
            return;
        }
        openCamera();
    }

    private void openCamera() {
        if (isSdCardExist()) {
            Intent cameraIntent = new Intent(
                    "android.media.action.IMAGE_CAPTURE");

            photo_image = new SimpleDateFormat("yyyy_MMdd_hhmmss").format(new Date()) + ".jpg";
            imageUri = getImageUri(photo_image);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT
                    , imageUri);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "SD卡不存在", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 打开图库
     * 不需要用FileProvider
     *
     * @param view
     */
    public void onClickOpenGallery(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission(REQUEST_CODE_PERMISSION_GALLERY);
            return;
        }
        openGallery();
    }

    private void openGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    /**
     * @param path 原始图片的路径
     */
    public void cropPhoto(String path) {
        crop_image = new SimpleDateFormat("yyyy_MMdd_hhmmss").format(new Date()) + "_crop" +
                ".jpg";
        File cropFile = createFile(crop_image);
        File file = new File(path);


        Intent intent = new Intent("com.android.camera.action.CROP");
        //TODO:访问相册需要被限制，需要通过FileProvider创建一个content类型的Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //TODO:访问相册需要被限制，需要通过FileProvider创建一个content类型的Uri
            imageUri = FileProvider.getUriForFile(getApplicationContext(),
                    BuildConfig.APPLICATION_ID + ".provider", file);
            cropUri = Uri.fromFile(cropFile);
            //TODO:cropUri 是裁剪以后的图片保存的地方。也就是我们要写入此Uri.故不需要用FileProvider
            //cropUri = FileProvider.getUriForFile(getApplicationContext(),
            //    BuildConfig.APPLICATION_ID + ".provider", cropFile);
        } else {
            imageUri = Uri.fromFile(file);
            cropUri = Uri.fromFile(cropFile);
        }

        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
//        //设置宽高比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
        //设置裁剪图片宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);
        intent.putExtra("scale", true);
        //裁剪成功以后保存的位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_REREQUEST_CODE);


    }


    /**
     * 获得一个uri。该uri就是将要拍摄的照片的uri
     *
     * @return
     */
    private Uri getImageUri(String name) {
        if (isSdCardExist()) {
            File file = createFile(name);
            if (file != null) {
                takePath = file.getAbsolutePath();
                Log.e("zmm", "图片的路径---》" + takePath);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return FileProvider.getUriForFile(getApplicationContext(),
                            BuildConfig.APPLICATION_ID + ".provider", file);
                } else {
                    return Uri.fromFile(file);
                }

            }
        }
        return Uri.EMPTY;
    }

    public File createFile(String name) {
        if (isSdCardExist()) {
            File[] dirs = ContextCompat.getExternalFilesDirs(this, null);
            if (dirs != null && dirs.length > 0) {
                File dir = dirs[0];
                return new File(dir, name);
            }
        }

        return null;
    }

    Uri shangchaunImg;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE://拍照成功并且返回
                    Log.e("zmm", "选择的图片的虚拟地址是------------>" +takePath);
                    cropPhoto(takePath);  //注视后不裁剪图，释放就裁剪
////                    cropPhoto(getMediaUriFromPath(this,takePath),false);
//                    decodeImage(imageUri,b);  //需要裁剪把此行注释
                    break;

                case IMAGE_REQUEST_CODE://选择图片成功返回
                    if (data != null && data.getData() != null) {
                        imageUri = data.getData();
                        decodeImage(imageUri); //需要裁剪把此行注释
                    }
                    break;
                case CROP_REREQUEST_CODE:
                    Log.e("zmm", "裁剪以后的地址是------------>" + cropUri);
                    shangchaunImg  = cropUri;
                    decodeImage(cropUri);
                    break;
            }
        }
    }
    // 图片裁剪
    private void cropPhoto(Uri uri, boolean fromCapture) {
        Intent intent = new Intent("com.android.camera.action.CROP"); //打开系统自带的裁剪图片的intent
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);

//        // 设置裁剪区域的宽高比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);


        // 设置裁剪区域的宽度和高度
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);

        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        // 图片输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        // 若为false则表示不返回数据
        intent.putExtra("return-data", false);

        // 指定裁剪完成以后的图片所保存的位置,pic info显示有延时
        if (fromCapture) {
            // 如果是使用拍照，那么原先的uri和最终目标的uri一致
            cropUri = uri;
        } else { // 从相册中选择，那么裁剪的图片保存在take_photo中
            String time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
            String fileName = "photo_" + time;
            File mCutFile = new File(Environment.getExternalStorageDirectory() + "/take_photo", fileName + ".jpeg");
            if (!mCutFile.getParentFile().exists()) {
                mCutFile.getParentFile().mkdirs();
            }
            cropUri = getUriForFile(this, mCutFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        Toast.makeText(this, "剪裁图片", Toast.LENGTH_SHORT).show();
        // 以广播方式刷新系统相册，以便能够在相册中找到刚刚所拍摄和裁剪的照片
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(uri);
        this.sendBroadcast(intentBc);

        startActivityForResult(intent, CROP_REREQUEST_CODE); //设置裁剪参数显示图片至ImageVie
    }
    // 从file中获取uri
    // 7.0及以上使用的uri是contentProvider content://com.rain.takephotodemo.FileProvider/images/photo_20180824173621.jpg
    // 6.0使用的uri为file:///storage/emulated/0/take_photo/photo_20180824171132.jpg
    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(),  BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
    /**
     * 根据uri拿到bitmap
     *
     * @param imageUri 这个Uri是
     */
    private void decodeImage(final Uri imageUri) {
        try {
            Bitmap bitmapFormUri = getBitmapFormUri(this, imageUri);
            if (zhaopian2==1){
                img1.setImageBitmap(bitmapFormUri);
                uri1 = imageUri;
            }
            if (zhaopian3==1){
                img2.setImageBitmap(bitmapFormUri);
                uri2 = imageUri;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }
    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
    /**
     * 检查权限
     *
     * @param requestCode
     */
    private void checkPermission(int requestCode) {

        boolean granted = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission_group.CAMERA);
        if (granted) {//有权限
            if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
                openCamera();//打开相机
            } else {
                openGallery();//打开图库
            }
            return;
        }
        //没有权限的要去申请权限
        //注意：如果是在Fragment中申请权限，不要使用ActivityCompat.requestPermissions,
        // 直接使用Fragment的requestPermissions方法，否则会回调到Activity的onRequestPermissionsResult
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest
                        .permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean flag = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PERMISSION_GRANTED) {
                    flag = false;
                    break;
                }
            }
            //权限通过以后。自动回调拍照
            if (flag) {
                if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
                    openCamera();//打开相机
                } else {
                    openGallery();//打开图库
                }
            } else {
                Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 检查SD卡是否存在
     */
    public boolean isSdCardExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public void beginupload(Uri bitmap,final int show) {
//        Log.e("图片地址",bitmap+"");


        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "jiaoyuvideo";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        final String objectname = TimeUtil.getTimeMis() + ".png";

        final String url = startpoint +"."+ endpoint+"/"+ objectname;

        PutObjectRequest put = new PutObjectRequest(startpoint, objectname, UiUtils.getImageAbsolutePath(ShimingOkActivity.this,bitmap) );
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("测试图片"+show,"https://jiaoyuvideo.oss-cn-beijing.aliyuncs.com/"+objectname);

                if (show==0){
                    path1 = objectname;
                    zhaopian = 1;
                }
                if (show==1){
                    path2 = objectname;
                    zhaopian = 2;
                }
                huidiao();
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ToastUtils.shortToast("图片上传失败");
                dissDialog();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                }
            }
        });

    }

}
