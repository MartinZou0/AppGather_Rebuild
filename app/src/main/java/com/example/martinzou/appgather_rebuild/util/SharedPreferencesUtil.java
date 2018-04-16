package com.example.martinzou.appgather_rebuild.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.martinzou.appgather_rebuild.application.MyApplication;

import java.io.File;

/**
 * Created by 10630 on 2018/4/3.
 */

//轻量级存储工具类
//SharedPreferences常用于保存一些简单的数据，如记录用户操作的配置等，使用简单。

    //这两个方法的区别在于：
//1. apply没有返回值而commit返回boolean表明修改是否提交成功
//2. apply是将修改数据原子提交到内存, 而后异步真正提交到硬件磁盘, 而commit是同步的提交到硬件磁盘，因此，在多个并发的提交commit的时候，他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。而apply只是原子的提交到内容，后面有调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率。
public class SharedPreferencesUtil {

    //存储的sharedpreferences文件名
    private static String FILENAME="appdata";
    //其实应该跟着用户来
    //拟定文件名用户账号_appdata;

    /**
     *保存数据到文件，CommitDate该方法是一个有返回值的同步的提交方式，true表示数据保存成功，false表示数据保存失败
     * @param key  键值
     * @param data 应用的信息以及分类的信息
     * @return true or false
     */

    //这个是同步进行
    public static boolean CommitDate(String filename,String key,String data){
        /*
        * 通过Activity自带的getSharedPreferences方法，得到SharedPreferences对象
        第一个参数表示保存后文件的名称(底层实现是将数据保存到文件中)。
        第二个参数表示xml文档的权限为私有，并且重新写的数据会覆盖掉原来的数据
        * */
        SharedPreferences sp= MyApplication.getContext().getSharedPreferences(filename, Context.MODE_PRIVATE);
        // 通过sp得到它的编辑器对象edit
        SharedPreferences.Editor editor=sp.edit();
        //通过编辑器将Key属性和相对应的值写入到xml文件当中
        editor.putString(key,data);
        return editor.commit();
    }




    /**
     * 采用异步操作保存数据
     * @param key
     * @param data
     */
    public static void ApplyData(String filename,String key,String data){
        SharedPreferences sp=MyApplication.getContext().getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key,data);
        editor.apply();
        //没有返回值
    }


    /**
     * 通过次方法可获取存储的数据
     * @param key
     * @return
     */
    public static String getData(String filename,String key){
        SharedPreferences sp=MyApplication.getContext().getSharedPreferences(filename,Context.MODE_PRIVATE);
        // 得到文件中的key标签值，第二个参数表示如果没有这个标签的话，返回的默认值
        //获得的是Json风格字符串
        String str=sp.getString(key,"");
        if(!str.isEmpty()){
            //如果不为空则返回读取的数据
            return str;
        }
        else{
            return null;
        }
    }

}
