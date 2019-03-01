package friendgoods.vidic.com.generalframework.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import friendgoods.vidic.com.generalframework.BuildConfig;

/**
*相机单利模式
*@author ZX
*created at 2018/11/19 11:49 
*/
public class CameraSingleton {

    private static CameraSingleton cameraSingleton=new CameraSingleton();
    public static final int SCAN_OPEN_PHONE = 0;
    public static final int PHONE_CAMERA = 1;
    public static final int PHONE_CROP = 2;

    private int from_type=0;//0是正常裁剪    1是直接展示不需要裁剪

    private Button btn_dianji;
    private ImageView iv_image;
    private Uri mCutUri;
    public File cutfile;//最终上传的路径

    private CameraSingleton(){
    }
    public static CameraSingleton getCameraSingleton(){
        return cameraSingleton;
    }

    //相册
    public void photoAlbum(Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, SCAN_OPEN_PHONE);
    }
    Uri imageuri ;
    //相机
    public void cameraPic(Activity activity) {
        //创建一个file，用来存储拍照后的照片
        File outputfile = new File(activity.getExternalCacheDir(),"output.png");
        try {
            if (outputfile.exists()){
                outputfile.delete();//删除
            }
            outputfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Uri imageuri ;
        if (Build.VERSION.SDK_INT >= 24){
            imageuri = FileProvider.getUriForFile(activity,
                    BuildConfig.APPLICATION_ID, //可以是任意字符串
                    outputfile);
        }else{
            imageuri = Uri.fromFile(outputfile);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        activity.startActivityForResult(intent,PHONE_CAMERA);
    }


    /**
     * 图片裁剪
     * @param uri
     * @return
     */
    @NonNull
    public Intent CutForPhoto(Uri uri,Context activity) {
        try {
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //设置裁剪之后的图片路径文件
            cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    "cutcamera.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            outputUri = Uri.fromFile(cutfile);
            mCutUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",0.1);
            intent.putExtra("aspectY",0.1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", dip2px(activity,150)); //200dp
            intent.putExtra("outputY",dip2px(activity,150));
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCutPic_state(int cut_state){
        from_type=cut_state;
    }
    /**
     * 拍照之后，启动裁剪
     * @param camerapath 路径
     * @param imgname img 的名字
     * @return
     */
    public Intent CutForCamera(String camerapath,String imgname,Context context) {
        try {

            //设置裁剪之后的图片路径文件
            cutfile = new File(Environment.getExternalStorageDirectory().getPath(),
                    "cutcamera.png"); //随便命名一个
            if (cutfile.exists()){ //如果已经存在，则先删除,这里应该是上传到服务器，然后再删除本地的，没服务器，只能这样了
                cutfile.delete();
            }
            cutfile.createNewFile();
            //初始化 uri
            Uri imageUri = null; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Intent intent = new Intent("com.android.camera.action.CROP");
            //拍照留下的图片
            File camerafile = new File(camerapath,imgname);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(context,BuildConfig.APPLICATION_ID,
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            outputUri = Uri.fromFile(cutfile);
            //把这个 uri 提供出去，就可以解析成 bitmap了
            mCutUri = outputUri;
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop",true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX",0.1);
            intent.putExtra("aspectY",0.1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", dip2px(context,150));
            intent.putExtra("outputY",dip2px(context,150));
            intent.putExtra("scale",true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data",false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //启动裁剪
    public Uri startTailor(Activity activity,Intent data){
        Uri path_uri=data.getData();
        if (from_type==0){
            activity.startActivityForResult(CutForPhoto(path_uri,activity),PHONE_CROP);
        }
        return path_uri;
    }
    //相机返回照片 准备裁剪
    public Uri returnPic(Activity activity){
        String path = activity.getExternalCacheDir().getPath();
        String name = "output.png";
        if (from_type==0){
            activity.startActivityForResult(CutForCamera(path,name,activity),PHONE_CROP);
        }
        return imageuri;
    }
    //展示裁剪后的图片
    public void setImageShow(ImageView imageShow,Context context){
        try {
            //获取裁剪后的图片，并显示出来
            Bitmap bitmap = BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(mCutUri));
            imageShow.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
