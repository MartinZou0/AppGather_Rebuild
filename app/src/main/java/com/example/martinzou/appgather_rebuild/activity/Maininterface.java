package com.example.martinzou.appgather_rebuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.example.martinzou.appgather_rebuild.R;
import com.example.martinzou.appgather_rebuild.adapter.TabFragmentPagerAdapter;
import com.example.martinzou.appgather_rebuild.application.MyApplication;
import com.example.martinzou.appgather_rebuild.entity.Classify;
import com.example.martinzou.appgather_rebuild.fragment.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10630 on 2018/3/30.
 */

public class Maininterface  extends AppCompatActivity implements View.OnClickListener{
    //实例化顶部可滑动导航栏
    private TabLayout mtablayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    //创建两个列表LIst来保存页面信息
    //标签页题目
    private List<String> mclassifylist=new ArrayList<String>();
    private List<Fragment> mTabContents=new ArrayList<Fragment>();
    //ViewPager以及适配器
    private ViewPager mviewpager;
    private TabFragmentPagerAdapter mtabFragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maininterface);
        initView();
    }
    private void initView() {
        //ViewPagr也需要实例化
        mviewpager=(ViewPager)findViewById(R.id.appviewpager);
        mtablayout=(TabLayout)findViewById(R.id.appclassifytablayout);
        //实例化侧滑菜单并设置点击事件
        drawerLayout=(DrawerLayout)findViewById(R.id.maininterface_drawerlayout);
        //侧滑菜单的点击事件
        navigationView=(NavigationView)findViewById(R.id.main_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                //设置点击item处理事件
                switch (item.getItemId()){
                    case R.id.personcenter:{
                        intent=new Intent(Maininterface.this,PersonalCenter.class);
                        startActivity(intent);
                        break;}
                    case R.id.appstore:{
                        intent=new Intent(Maininterface.this,AppStore.class);
                        startActivity(intent);
                        break;}
                    case R.id.appmanage:{break;}
                }
                return false;
            }
        });
    }

    //主界面上的点击事件
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
        }
    }

    //覆写onStart()方法
    @Override
    protected void onStart() {
        super.onStart();
       initClassifyData();
       initFragmentData();
       initTab();
    }

    //获取标签页信息
    private void initClassifyData() {
        //获取到需要的标签页面数量
        int size= MyApplication.getClassifies().size();
        mclassifylist.clear();
        //便利标签信息
        if(size>0){
            for(int i=0;i<size;i++){
                if(MyApplication.getClassifies().get(i).isSelected()){
                    mclassifylist.add(MyApplication.getClassifies().get(i).getClassname());
                }
            }
        }
    }


    private void initFragmentData() {
        //初始化Fragment，也就说有几个标签页就有几个Fragment页
        //首先将mTabcontents.clear()清空
        mTabContents.clear();
        for(Classify classifydata:MyApplication.getClassifies()){
            if(classifydata.isSelected()){
                //如果是可选择状态，也就是true,标签即可以显示
                //然后开始
                SimpleFragment fragment=SimpleFragment.newInstance(classifydata.getType());
                mTabContents.add(fragment);
            }
        }
        mtabFragmentPagerAdapter=new TabFragmentPagerAdapter(getSupportFragmentManager(),mTabContents,mclassifylist);
        //完成了这个适配器
    }

    //初始化Tab，也就是说初始化整个界面的显示
    private void initTab() {
        //viewPager加载适配器
        mviewpager.setAdapter(mtabFragmentPagerAdapter);
        //将TabLayout与VIewPager进行关联
        mtablayout.setupWithViewPager(mviewpager);
    }



    /*
    * 如果你想在Activity中得到新打开Activity关闭后返回的数据，
    * 你需要使用系统提供的startActivityForResult(Intent intent,int requestCode)方法打开新的Activity，
    * 新的Activity关闭后会向前面的Activity传回数据，为了得到传回的数据，你必须在前面的Activity中重写onActivityResult(int requestCode, int resultCode,Intent data)方法：
    * */

    //应用更改,改动信息接收方
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode,resultCode,data);
      if(resultCode==1){
          mclassifylist.clear();
          List<Classify> resultclassifylist=(List<Classify>)data.getSerializableExtra("SelectItem");
          int index=resultclassifylist.size();
          for(int i=0;i<index;i++){
              mclassifylist.add(resultclassifylist.get(i).getClassname());
          }
          initFragmentData();
          initTab();

      }
    }

}
