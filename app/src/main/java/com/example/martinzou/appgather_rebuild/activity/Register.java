package com.example.martinzou.appgather_rebuild.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martinzou.appgather_rebuild.R;

/**
 * Created by 10630 on 2018/3/26.
 */

//编写注册活动
public class Register extends AppCompatActivity {
    //实例化
    private EditText et_registername;
    private EditText et_registertel;
    private EditText et_registerpwd;
    private Button button_register;

    //还需要存放注册信息以便发送至服务器端
     //在API中实现

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //启用注册界面
        setContentView(R.layout.layout_register);
        //包含对于文本框输入情况的判定
        initVIew();
    }

    /*
    * 初始化
    * */

    private void initVIew() {
        //实例化控件并确定点击事件
        et_registername=(EditText)findViewById(R.id.et_registername);
        et_registertel=(EditText)findViewById(R.id.et_registertel);
        et_registerpwd=(EditText)findViewById(R.id.et_login_pwd);
        button_register=(Button)findViewById(R.id.register_action);
        //对于输入框情况的判定
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_registername.getText().toString().length()==0||et_registername.getText().toString().length()>20){
                    //清空
                    et_registername.setText("");
                    Toast.makeText(Register.this,"请输入规范用户昵称",Toast.LENGTH_SHORT).show();
                }else if(et_registername.getText().toString().length()!=0&&et_registername.getText().toString().length()<20){
                    if(et_registerpwd.getText().toString().length()>=6&&et_registerpwd.getText().toString().length()<=15&&et_registertel.getText().toString().length()<11){
                        et_registertel.setText("");
                        Toast.makeText(Register.this,"请输入正确的电话号码", Toast.LENGTH_SHORT).show();
                    }
                    //至少为6位
                    if(et_registerpwd.getText().toString().length()<6&&et_registertel.getText().toString().length()==11){
                        et_registerpwd.setText("");
                        Toast.makeText(Register.this,"请输入至少6位密码",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //判断到最后进行注册操作
                    alertDialogForSend();
                }
            }
        });
    }

    private void alertDialogForSend() {
//        需要补写对话框
        //获取电话号啊
        final String tel=et_registertel.getText().toString();
        //创建提示框
        AlertDialog.Builder sentDialog=new AlertDialog.Builder(Register.this);
        sentDialog.setTitle("确认手机号码");
        sentDialog.setMessage("\"我们将发送验证码短信到这个号码:\\n+86  \"+tel");
        sentDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               //跳转到验证码输入界面
                //Intent intent=new Intent(Register.this,))
            }
        });

    }


}
