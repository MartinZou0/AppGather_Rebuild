package com.example.martinzou.appgather_rebuild.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martinzou.appgather_rebuild.R;
import com.example.martinzou.appgather_rebuild.view.TextWatcherForJudge;

public class Login extends AppCompatActivity  implements View.OnClickListener{
    //初始化
    private EditText et_loginname;//用户名输入框
    private EditText et_loginpwd;//密码输入框
    private TextWatcher textWatcherForName;//用户名输入监听器
    private TextWatcher textWatcherForPwd;//密码输入监听器监听器
    private Button login_button;//登录按钮
    private Button register_button;//注册按钮
    private Button findpwd_button;//找回密码按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar（标题栏）,采用的主题可以直接隐藏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.layout_login);
        initVIew();
    }

    private void initVIew() {
        //按钮，编辑框的初始化并建立监听事件
        login_button=(Button)findViewById(R.id.login_button);
        register_button=(Button)findViewById(R.id.register_button);
        findpwd_button=(Button)findViewById(R.id.findpwd_button);
        et_loginname=(EditText)findViewById(R.id.et_login_user);
        et_loginpwd=(EditText)findViewById(R.id.et_login_pwd);
        btnListen();//按钮事件监听
        editViewFocus();//编辑框焦点事件监听
        editViewListenInput();//编辑框输入监听
        setEt_usertel();//设置从找回密码页面传来的手机号
    }


    private void btnListen() {
        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
        findpwd_button.setOnClickListener(this);
    }


    private void editViewFocus() {

        et_loginname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });

        et_loginpwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
    }

    //监听文本框输入用户名和密码是否有输入
    /*//public void onTextChanged(CharSequence s, int start, int before, int count) {}
    * - CharSequence s：文本改变之后的内容
      - int start：文本开始改变时的起点位置，从0开始计算
      - int before：要被改变的文本字数，即已经被替代的选中文本字数
      - int count：改变后添加的文本字数，即替代选中文本后的文本字数
*/
    private void editViewListenInput() {
        textWatcherForName=new TextWatcherForJudge(){
            @Override
            public void afterTextChanged(Editable editable) {
                //见识用户名输入框，在两者都有输入的情况下才能点击登录按钮
                if(editable.toString().length()==11&&et_loginpwd.getText().toString().length()!=0){
                    login_button.setEnabled(true);
                }
            }

        };
        et_loginname.addTextChangedListener(textWatcherForName);

        textWatcherForPwd=new TextWatcherForJudge(){
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()!=0&&et_loginname.getText().toString().length()==11){
                    login_button.setEnabled(true);
                }
            }
        };
        et_loginpwd.addTextChangedListener(textWatcherForPwd);



    }

    private void setEt_usertel() {
    }



    //除了登录活动，其他都需要跳转,完成点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:{
                //对其输入进行判断
                if(et_loginname.getText().toString().length()==11&&et_loginpwd.getText().toString().length()>=6){
                    //符合要求调用登录方法
                    userLogin();
                }else if(et_loginname.getText().toString().length()!=11){
                    Toast.makeText(this,"请输入正确的电话号码",Toast.LENGTH_SHORT).show();
                    et_loginname.setText("");
                }else if(et_loginpwd.getText().toString().length()<6){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                    et_loginpwd.setText("");
                }
                break;
            }

            case R.id.register_button:{
                //活动跳转
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
                break;
            }

            case R.id.findpwd_button:{

                break;
            }
        }

    }

    /*
    * 登录验证
    * */

    private void userLogin() {
       //页面跳转
        Intent intent=new Intent(Login.this,Maininterface.class);
        startActivity(intent);

    }
}

