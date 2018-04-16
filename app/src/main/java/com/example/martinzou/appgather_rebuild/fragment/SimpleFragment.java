package com.example.martinzou.appgather_rebuild.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.martinzou.appgather_rebuild.R;
import com.example.martinzou.appgather_rebuild.adapter.AppViewAdapter;
import com.example.martinzou.appgather_rebuild.application.MyApplication;
import com.example.martinzou.appgather_rebuild.entity.Apps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10630 on 2018/4/3.
 */

//定义显示的Fragment
public class SimpleFragment extends Fragment {
    public static final String BUNDLE_TITLE="title";
    //显示Fragment,每个Fragment理由一个ListView用来显示应用列表
    private String mtitle="DefaultValur";
    private List<Apps>AppURL=new ArrayList<Apps>();
    private AppViewAdapter appViewAdapter;
    private ListView appListView;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        //获得从Activity传递过来的Arguments
        Bundle arguments=getArguments();
        if(arguments!=null){
            //获取到标题
            mtitle=arguments.getString(BUNDLE_TITLE);
        }
        //实例化一个ListView
        inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.applistview,null);
        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.ll_list);
        appListView=(ListView)view.findViewById(R.id.lv_appview);
        linearLayout.removeView(appListView);
        AppURL.clear();
        setApps(mtitle);
        //获得要本页面显示的应用
        //getContext()：这个是View类中提供的方法，在继承了View的类中才可以调用，
        // 返回的是当前View运行在哪个Activity Context中。
        appViewAdapter=new AppViewAdapter(AppURL,getContext());
        appListView.setAdapter(appViewAdapter);
        return appListView;
    }

    //mtype的定义为这个标签页的标号，选择在那个标签页进行显示
    //每个Fragment其实也就对应着一个Type，所以便利已有的已有的应用集
    //所有定义为这个标签页显示的必须要
    private void setApps(String mtype) {
        //返回应用信息序列
        int size= MyApplication.getApps().size();
        //遍历应用信息序列
        for(int i=0;i<size;i++){
                if(mtype.equals(MyApplication.getApps().get(i).getCategoryID()+"")){
                    AppURL.add(MyApplication.getApps().get(i));
                }
        }
    }

    public static SimpleFragment newInstance(String title){
        //新建Bundle对象
        Bundle bundle=new Bundle();
        bundle.putString(BUNDLE_TITLE,title);
        SimpleFragment fragment=new SimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
