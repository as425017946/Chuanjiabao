package friendgoods.vidic.com.generalframework.activity.wode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

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
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.Bean.GaojiShwoBean;
import friendgoods.vidic.com.generalframework.Bean.GaojirenzhengBean;
import friendgoods.vidic.com.generalframework.Bean.ProvinceBean;
import friendgoods.vidic.com.generalframework.Bean.WechatBindBean;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.SettingActivity;
import friendgoods.vidic.com.generalframework.activity.VisaActivity;
import friendgoods.vidic.com.generalframework.adapter.GaojiAdapter;
import friendgoods.vidic.com.generalframework.util.Api;
import friendgoods.vidic.com.generalframework.util.PhotoUtils;
import friendgoods.vidic.com.generalframework.util.TimeUtil;
import friendgoods.vidic.com.generalframework.util.ToastUtils;
import friendgoods.vidic.com.generalframework.util.UiUtils;
import okhttp3.Call;
import okhttp3.Response;

import static friendgoods.vidic.com.generalframework.MainActivity.Userid;

/**
 * 高级认证
 */
public class GaojiRenzhengActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaoji_renzheng);
        ButterKnife.bind(this);
        setTtitle();
        fanhui();
        setinfo();
        setallinfo();
        setshowinfo();
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.touzi_title)
    TextView ttitle;
    @BindView(R.id.touzi_quxiao_title)
    TextView tbaocun;
    private void setTtitle() {
        ttitle.setText("高级认证");
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
                GaojiRenzhengActivity.this.finish();
            }
        });
    }

    /**
     * 上传信息
     */
    @BindView(R.id.gaoji_zhengmian)
    ImageView img_zhengmian;
    @BindView(R.id.gaoji_fanmian)
    ImageView img_fanmian;
    private void setinfo(){
        img_zhengmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=1;
                autoObtainCameraPermission();
            }
        });
        img_fanmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=2;
                autoObtainCameraPermission();
            }
        });
    }

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ToastUtils.shortToast( "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(GaojiRenzhengActivity.this, "com.zz.fileprovider", fileUri);
                }
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.shortToast(  "设备没有SD卡！");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(GaojiRenzhengActivity.this, "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.shortToast( "设备没有SD卡！");
                    }
                } else {

                    ToastUtils.shortToast( "请允许打开相机！！");
                }
                break;


            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    ToastUtils.shortToast( "请允许打操作SDCard！！");
                }
                break;
            default:
        }
    }

    private static final int OUTPUT_X = 1000;
    private static final int OUTPUT_Y = 1000;
    private int a=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    Log.e("显示",cropImageUri+"");
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1000, 1000, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(this, "com.zz.fileprovider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.shortToast( "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        if (a==1){
                            beginupload(cropImageUri,a);
                            showImages(bitmap,a);
                        }else if (a==2){
                            beginupload(cropImageUri,a);
                            showImages(bitmap,a);
                        }

                    }
                    break;
                default:
            }
        }
    }


    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }

    private void showImages(Bitmap bitmap,int b) {
        if (b==1){
            img_zhengmian.setImageBitmap(bitmap);
        }else{
            img_fanmian.setImageBitmap(bitmap);
        }


    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    private String zhengmian,fanmian,zhiye;
    public void beginupload(Uri bitmap, final int c) {

        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "jiaoyuvideo";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        final String objectname = TimeUtil.getTimeMis() + "";

        final String url = startpoint +"."+ endpoint+"/"+ objectname;

        PutObjectRequest put = new PutObjectRequest(startpoint, objectname, UiUtils.getImageAbsolutePath(GaojiRenzhengActivity.this,bitmap) );
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("测试图片","https://jiaoyuvideo.oss-cn-beijing.aliyuncs.com/"+objectname);
                if(c==1){
                    zhengmian=objectname;
                }else{
                    fanmian=objectname;
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
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

    /**
     * 上传信息
     */
    @BindView(R.id.gaoji_tijiao)
    Button btn_tijiao;
    @BindView(R.id.gaoji_hangyetitle)
    TextView tv_title;
    @BindView(R.id.gaoji_hangyetishi)
    TextView tv_tishi;
    @BindView(R.id.gaoji_hangyebg)
    LinearLayout l_hangye;
    private ArrayList<ProvinceBean> options1Itembank = new ArrayList<>();
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private void setallinfo(){
        //先加载需要显示的行业信息
        OkGo.post(Api.zhiyetable)
                .tag(this)
                .params("id",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GaojirenzhengBean gaojibean = gson.fromJson(s,GaojirenzhengBean.class);
                        if (gaojibean.getData().size()>0){
                            for (int i = 0; i <gaojibean.getData().size() ; i++) {
                                options1Itembank.add(new ProvinceBean(i, gaojibean.getData().get(i).getUser_job_name(), "描述部分", "其他数据"));
                            }
                        }else {

                        }
                    }
                });
        //点击行业信息
        l_hangye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OptionsPickerView pvOptions = new OptionsPickerBuilder(GaojiRenzhengActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Itembank.get(options1).getPickerViewText();
                        tv_title.setText(tx);
                        zhiye = tx;
                        tv_tishi.setVisibility(View.GONE);
                    }
                }).build();
                pvOptions.setPicker(options1Itembank);
                pvOptions.show();

            }
        });
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(zhengmian)){
                    ToastUtils.shortToast("请上传身份证正面");
                }else if (TextUtils.isEmpty(fanmian)){
                    ToastUtils.shortToast("请上传身份证反面");
                }else if(TextUtils.isEmpty(zhiye)){
                    ToastUtils.shortToast("请选择行业");
                }else {
                    OkGo.post(Api.headimg)
                            .tag(this)
                            .params("id",Userid)
                            .params("photoA",zhengmian)
                            .params("photoB",fanmian)
                            .params("job",zhiye)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Gson gson = new Gson();
                                    WechatBindBean wx = gson.fromJson(s,WechatBindBean.class);
                                    if (wx.getState()==1){
                                        ToastUtils.longToast("信息提交成功");
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                GaojiRenzhengActivity.this.finish();
                                            }
                                        },2000);
                                    }else{
                                        ToastUtils.shortToast(wx.getMessage());
                                    }
                                }
                            });
                }
            }
        });

    }

    //高级认证后查询
    @BindView(R.id.gaoji_scrollview)
    ScrollView scrollView;
    @BindView(R.id.gaoji_showinfo)
    LinearLayout linearLayout;
    private  void setshowinfo(){
        OkGo.post(Api.gaojirenzheng_show)
                .tag(this)
                .params("id",Userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("展示信息",s);
                        Gson gson = new Gson();
                        GaojiShwoBean gaojishow = gson.fromJson(s,GaojiShwoBean.class);
                        if (gaojishow.getData().getFlag2().equals(0)){
                            scrollView.setVisibility(View.VISIBLE);
                            linearLayout.setVisibility(View.GONE);
                        }else{
                            linearLayout.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);
                        }
                    }
                });
    }


}
