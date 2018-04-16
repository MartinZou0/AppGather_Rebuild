package com.example.martinzou.appgather_rebuild.entity;

import java.io.Serializable;

/**
 * Created by 10630 on 2018/4/3.
 */


//定义应用信息实体类
    //保存应用的信息以及应用的URL
//任何类型只要实现了Serializable接口，
// 就可以被保存到文件中，
// 或者作为数据流通过网络发送到别的地方。
// 也可以用管道来传输到系统的其他程序中。
// 这样子极大的简化了类的设计。
/*1.什么情况下需要序列化
    a）当你想把的内存中的对象状态保存到一个文件中或者数据库中时候；
    b）当你想用套接字在网络上传送对象的时候；
    c）当你想通过RMI传输对象的时候；*/
public class Apps implements Serializable{

    //定义应用信息
    private int CategoryID;//分类ID
    private String AppName;//应用名
    private String url;//应用访问地址

    //构造函数
    public Apps(){}

    public Apps(int categoryID, String appName, String url) {
        CategoryID = categoryID;
        AppName = appName;
        this.url = url;
    }

    //定义getSet方法
    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //对方法进行覆写，直接将应用数据读取出来，以字符串形式
    @Override
    public String toString() {
        return "Apps{" + "CategoryID=" + CategoryID +
                ", AppName='" + AppName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
