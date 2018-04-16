package com.example.martinzou.appgather_rebuild.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 10630 on 2018/4/3.
 */

public class TabFragmentPagerAdapter  extends FragmentPagerAdapter {
    //Fragment列表以及标题字符
    private List<Fragment>fragmentList;
    private List<String>tabstitle;

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public List<String> getTabstitle() {
        return tabstitle;
    }

    public void setTabstitle(List<String> tabstitle) {
        this.tabstitle = tabstitle;
    }

    //构造函数
    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tabstitle) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabstitle = tabstitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabstitle.get(position);
    }
}
