package me.jokey.movie.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import me.jokey.movie.application.Constant;

/**
 * Created by wz on 2017/8/17 11:52.
 * desc:
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTitleList;
    private List<Fragment> mFragmentList;

    public ViewPagerAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
        super(fm);
        this.mTitleList = titleList;
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.ACTION_CATEGORY, position);
        Fragment fragment = mFragmentList.get(position);
        fragment.setArguments(bundle);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
