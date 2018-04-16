package com.example.martinzou.appgather_rebuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.martinzou.appgather_rebuild.R;

/**
 * Created by 10630 on 2018/4/8.
 */

public class AppStore extends AppCompatActivity {
    private String PATH=null;
    private WebView wv_appstore;
    private static String appStore_url="http://120.78.160.156/app_store-master/public_html/index/index/index.html";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_appstore);
        wv_appstore=(WebView)findViewById(R.id.wv_appstore);
        setWebView();
    }

    private void setWebView() {
        WebSettings setting=wv_appstore.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        setting.setJavaScriptEnabled(true);
        //设置可以访问文件
        setting.setAllowFileAccess(true);
        //设置支持缩放
        setting.setBuiltInZoomControls(true);
        wv_appstore.loadUrl(appStore_url);
        wv_appstore.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wv_appstore.loadUrl(url);
                return true;
            }
        });
    }


    //可退回而不是直接退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&wv_appstore.canGoBack()){
            wv_appstore.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
