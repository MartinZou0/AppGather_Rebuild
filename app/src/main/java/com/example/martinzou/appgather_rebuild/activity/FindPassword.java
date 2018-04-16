package com.example.martinzou.appgather_rebuild.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.martinzou.appgather_rebuild.R;

/**
 * Created by 10630 on 2018/3/28.
 */

public class FindPassword extends AppCompatActivity{

    //初始化找回密码页面控件
    //发送密码按钮
    private Button sendpwd_button;
    private EditText et_phonenumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.layout_find_password);
        initView();
        setSendpwd_button();
    }
    private void initView() {
        //实例化部件
        sendpwd_button=(Button)findViewById(R.id.sendpwd_button);
        et_phonenumber=(EditText)findViewById(R.id.et_send_tel);
    }



    private void setSendpwd_button() {
        //跳转到修改密码界面,并执行短信回调
        sendpwd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


}
