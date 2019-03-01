package friendgoods.vidic.com.generalframework.touzi.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import friendgoods.vidic.com.generalframework.BaseActivity;
import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.touzi.TouziSousuoActivity;

public class TouziSousuoMoreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzi_sousuo_more);
        ButterKnife.bind(this);
        quxiao();
        leixing();
    }
    /***
     * 取消
     */
    @BindView(R.id.sousuo_quxiao)
    TextView quxiao;
    private void quxiao(){
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouziSousuoMoreActivity.this.finish();
            }
        });
    }
    /**
     * 类型
     */
    @BindView(R.id.spinner1)
    Spinner sp1;
    @BindView(R.id.spinner2)
    Spinner sp2;
    @BindView(R.id.spinner3)
    Spinner sp3;
    private List<String> data_list1,data_list2,data_list3;
    private ArrayAdapter<String> arr_adapter,arr_adapter2,arr_adapter3;
    private void leixing(){
        //数据
        data_list1 = new ArrayList<String>();
        data_list1.add("项目状态");
        data_list1.add("上线");
        data_list1.add("未上线");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list1);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp1.setAdapter(arr_adapter);

        //数据2
        data_list2 = new ArrayList<String>();
        data_list2.add("空间类型");
        data_list2.add("上线");
        data_list2.add("未上线");
        //适配器
        arr_adapter2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list2);
        //设置样式
        arr_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp2.setAdapter(arr_adapter2);

        //数据
        data_list3 = new ArrayList<String>();
        data_list3.add("投资类型");
        data_list3.add("上线");
        data_list3.add("未上线");
        //适配器
        arr_adapter3= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list3);
        //设置样式
        arr_adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp3.setAdapter(arr_adapter3);


    }
}
