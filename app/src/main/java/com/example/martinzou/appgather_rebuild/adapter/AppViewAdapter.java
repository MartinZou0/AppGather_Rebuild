package com.example.martinzou.appgather_rebuild.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.martinzou.appgather_rebuild.R;
import com.example.martinzou.appgather_rebuild.entity.Apps;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 10630 on 2018/4/4.
 */


//不同点是LayoutInflater是用来找res/layout/下的xml布局文件，
// 并且实例化；而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)。
    /*
    * 具体作用：
1、对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；

2、对于一个已经载入的界面，就可以使用Activiyt.findViewById()方法来获得其中的界面元素。
    * */
public class AppViewAdapter extends BaseAdapter {
    private List<Apps> appurllist;
    private LayoutInflater inflater;

    public AppViewAdapter(List<Apps> urllist, Context context) {
        this.appurllist = urllist;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return appurllist.size();
    }

    @Override
    public Object getItem(int i) {
        return appurllist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //getView : 获取该条目要显示的界面

    /*
    * i是指当前dataset的位置，通过getCount和getItem来使用。
    * 如果list向下滑动的话那么就是最低端的item的位置，如果是向上滑动的话那就是最上端的item的位置。
    * conert是指可以重用的视图，即刚刚出队的视图。
    * parent应该就是list。
    * */

    //View中的setTag(Onbect)表示给View添加一个格外的数据，以后可以用getTag()将这个数据取出来
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHold viewHold;
        // 若无可重用的 view 则进行加载
        if(view==null){
            viewHold=new ViewHold();
            //https://blog.csdn.net/u012702547/article/details/52628453
            //imflate方法解释
            view=inflater.inflate(R.layout.appviewitem,null);
            //用ViewHolder来获取,并初始化
            viewHold.tv_appName=(TextView) view.findViewById(R.id.tv_appName);
            viewHold.m_webview=(WebView)view.findViewById(R.id.webview_app);
            view.setTag(viewHold);
        }else{
            // 否则进行重用
            viewHold=(ViewHold)view.getTag();
        }
        //经过这上面的判断则必然会生成一个包含信息的VIewHolder
        //然后利用这个ViewHlder进行对于控件的设置
        WebSettings webSettings=viewHold.m_webview.getSettings();
        //设置WebView属性
        webSettings.setAllowFileAccess(true);
        //设置可访问文件
         webSettings.setBuiltInZoomControls(true);
        //设置支持缩放
        webSettings.setJavaScriptEnabled(true);
        //设置执行javascript脚本
        webSettings.setDomStorageEnabled(true);


        viewHold.tv_appName.setText(appurllist.get(i).getAppName());
        viewHold.m_webview.loadUrl(appurllist.get(i).getUrl());
        Log.d("zsy","又在加载");



        //调用自己的WebView，而不是调用默认浏览器
        viewHold.m_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.d("zsy","又在加载");
                return true;
                //返回值为true指的是直接在本webview中打开
            }
        });

        return view;
    }


    /*要想使用 ListView 就需要编写一个 Adapter 将数据适配到 ListView上，
    而为了节省资源提高运行效率，一般自定义类 ViewHolder 来减少 findViewById() 的使用
    以及避免过多地 inflate view，从而实现目标。*/

    class ViewHold{
        WebView m_webview;
        TextView tv_appName;
    }
}
