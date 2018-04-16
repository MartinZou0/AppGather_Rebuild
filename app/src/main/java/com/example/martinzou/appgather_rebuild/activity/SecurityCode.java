package com.example.martinzou.appgather_rebuild.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.martinzou.appgather_rebuild.entity.API_Register;
import com.example.martinzou.appgather_rebuild.R;
import com.example.martinzou.appgather_rebuild.util.DiaLoading;

/**
 * Created by 10630 on 2018/3/27.
 */


//验证码的发送验证也就是注册的最后一步判定
public class SecurityCode extends AppCompatActivity {
    //初始化控件
    private TextView tv_usertel;
    private EditText et_SecurityCode;//验证码输入框
    private TextWatcher textWatcherForCode;//监视器
    //创建注册实体
    private API_Register register;
    private API_Register mregister=new API_Register();

    //加载对话框
    private DiaLoading diaLoading;

    //短信回掉


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.layout_securitycode);
        initSms();
        getRegisterMsg();
        initView();
    }

     /*
     * 初始化
     * */
    private void initView() {
        //实例化部件并获得号码设置
        tv_usertel=(TextView)findViewById(R.id.tv_usertel);
        tv_usertel.setText(register.getUsername());
        et_SecurityCode=(EditText) findViewById(R.id.et_SecurityCode);
        //初始化对话框
        diaLoading=new DiaLoading(SecurityCode.this);
        ObtainSecurityCode();
        textChangeListens();

    }

    private void ObtainSecurityCode() {
    }


    private void textChangeListens() {
    }


    //获取传递锅里的注册信息
    private void getRegisterMsg() {
    }


    //初始化sms
    private void initSms() {

    }


}
