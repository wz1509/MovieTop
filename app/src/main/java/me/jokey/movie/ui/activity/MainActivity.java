package me.jokey.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.jokey.movie.R;
import me.jokey.movie.ui.adapter.ViewPagerAdapter;
import me.jokey.movie.ui.fragment.MovieFragment;

public class MainActivity extends BaseActivity {

    private List<String> mTitleList = Arrays.asList("正在上映", "即将上映", "Top250");

    private List<Fragment> mFragmentList = new ArrayList<Fragment>() {{
        add(new MovieFragment());
        add(new MovieFragment());
        add(new MovieFragment());
    }};

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mToolbar.setTitle("榜单");
        setSupportActionBar(mToolbar);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTitleList, mFragmentList));
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
