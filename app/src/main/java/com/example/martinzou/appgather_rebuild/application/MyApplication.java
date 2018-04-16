package com.example.martinzou.appgather_rebuild.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.martinzou.appgather_rebuild.entity.Apps;
import com.example.martinzou.appgather_rebuild.entity.Classify;
import com.example.martinzou.appgather_rebuild.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10630 on 2018/4/3.
 */


// Android Application组件包括:
//
//         Activities: 前台界面, 直接面向User, 提供UI和操作.
//         Services: 后台任务.
//         Broadcast Receivers: 广播接收者.
//         Contexnt Providers: 数据提供者.
//咸鱼Activity初始化，生命周期最长与这个应用的生命周期相当
public class MyApplication extends Application{

    private static Context mContext;
    private static List<Apps> apps;//应用集合
    private static List<Classify> classifies;//分类集合
    private static String APPS="apps";
    private static String CLASSFY="classify";

    //get方法
    public static Context getContext() {
        return mContext;
    }

    public static List<Apps> getApps() {
        return apps;
    }

    public static List<Classify> getClassifies() {
        return classifies;
    }

    //set方法
    public static void setApps(List<Apps> apps) {
        MyApplication.apps = apps;
    }

    public static void setClassifies(List<Classify> classifies) {
        MyApplication.classifies = classifies;
    }

//遍历信息，方便查看是否正确
    public static void ToString()
    {
        for(Apps app:apps) {
            Log.d("zqh",app+"");
        }

        for(Classify classify:classifies) {
            Log.d("zqh",classify+"");
        }
    }



    //需要实现的方法

    //调用时刻：Application实例创建时调用
    //Android系统的入口是Application类的onCreate()
    //作用：应用程序级别 的资源，如全局对象、环境配置变量、
    // 图片资源初始化、推送服务的注册等
    @Override
    public void onCreate() {
        super.onCreate();
        //获得上下文
        mContext=getApplicationContext();
        getAppsMsg();
        getClassifyMsg();
        ToString();

    }

    //暂时只能这样了，等处理了用户配置文件之后才能做到多用户切换
    public void getAppsMsg() {
        //初始化APP应用信息列表
        apps=new ArrayList<Apps>();
        String data= SharedPreferencesUtil.getData("appdata",APPS);
        //处理成字符串
        //首先对其判断，如果不是第一次使用则进行文件的读取
        if(data!=null){
            //传入List
            apps.addAll(JSONObject.parseArray(data,Apps.class));
            Log.d("zsy","APPS读取成功");
        }
        else {
            Log.d("zsy","APPS访问数据不存在");
            //否则需要新建
            saveAppMsg();
            //再次判断
            getAppsMsg();
        }
    }


     /*
     * 加载默认APP
     * */
    private void saveAppMsg() {
        List<Apps>apps=new ArrayList<Apps>();
        apps.add(new Apps(1,"快递查询","file:///android_asset/kuaidi/index.html"));
        apps.add(new Apps(1,"测试应用","file:///android_asset/test/index.html"));
        apps.add(new Apps(1,"百度","https://www.baidu.com"));
        //然后需要将文件存储到本地
        //这就是为什么解析完之后一定是Json字符串
        String str= JSON.toJSONString(apps);
        if(SharedPreferencesUtil.CommitDate("appdata","apps",str)){
            Log.d("zsy","Apps数据保存成功");
        }
        else{
            Log.d("zsy","Apps数据保存失败");
        }
    }


    private void getClassifyMsg()
    {
        classifies=new ArrayList<Classify>();
        String data= SharedPreferencesUtil.getData("appdata",CLASSFY);
        if(data!=null)
        {
            classifies.addAll(JSONObject.parseArray(data,Classify.class));
            Log.d("zsy","分类标签数据读取成功");
        }
        else {
            saveClassifyMsg();
            data= SharedPreferencesUtil.getData("appdata",CLASSFY);
            classifies.addAll(JSONObject.parseArray(data,Classify.class));
        }
    }

    /*
  *c创建默认的分类标签数据
 */
    private void saveClassifyMsg()
    {
        List<Classify> classifies=new ArrayList<Classify>();
        classifies.add(new Classify("工作","1",true));
        classifies.add(new Classify("学习","2",true));
        classifies.add(new Classify("娱乐","3",true));
        classifies.add(new Classify("社交","4",true));
        classifies.add(new Classify("阅读","5",true));
        classifies.add(new Classify("购物","6",true));
        //以json格式保存
        String str=JSON.toJSONString(classifies);
        if(SharedPreferencesUtil.CommitDate("appdata","classify",str)) {
            Log.d("zqh","分类标签数据保存成功");
        }
        else{
            Log.d("zqh","分类标签数据保存失败");
        }
    }

}
