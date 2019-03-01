package friendgoods.vidic.com.generalframework.touzi.more;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.lidong.pdf.PDFView;
import com.lidong.pdf.listener.OnDrawListener;
import com.lidong.pdf.listener.OnLoadCompleteListener;
import com.lidong.pdf.listener.OnPageChangeListener;

import butterknife.BindView;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.BaseActivity;
import friendgoods.vidic.com.generalframework.activity.LoginActivity;

import static friendgoods.vidic.com.generalframework.touzi.TouziMoreActivity.xinxipilou;

public class DownLoaderActivity extends BaseActivity implements OnPageChangeListener
        ,OnLoadCompleteListener, OnDrawListener {

    private PDFView pdfView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_loader);

        pdfView = (PDFView) findViewById( R.id.pdfView );
//        Log.e("pdf地址",getIntent().getStringExtra("url").toString());
        displayFromFile1(getIntent().getStringExtra("url").toString(),"ceshi");
    }


    /**
     * 获取打开网络的pdf文件
     * @param fileUrl
     * @param fileName
     */
    private void displayFromFile1( String fileUrl ,String fileName) {
        showProgress(); pdfView.fileFromLocalStorage(this,this,this,fileUrl,fileName); //设置pdf文件地址

    }
    /**
     * 翻页回调
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
//        Toast.makeText( DownLoaderActivity.this , "page= " + page +
//                " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();
    }
    /**
     * 加载完成回调
     * @param nbPages 总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
//        Toast.makeText( DownLoaderActivity.this , "加载完成" + nbPages , Toast.LENGTH_SHORT).show();
        hideProgress();
    }
    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
        // Toast.makeText( MainActivity.this , "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage=" + displayedPage , Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示对话框
     */

    private void showProgress(){
//        LoadingUIHelper.showDialogForLoading(this,"报告加载中,请等待。。。",false);

    }
    /**
     * 关闭等待框
     */
    private void hideProgress(){
//        LoadingUIHelper.hideDialogForLoading();

    }

}
