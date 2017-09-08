//package me.jokey.movie.ui.fragment;
//
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import butterknife.BindView;
//import me.jokey.movie.R;
//import me.jokey.movie.ui.activity.BaseActivity;
//import me.jokey.movie.ui.adapter.ViewPagerAdapter;
//
///**
// * Created by wz on 2017/9/7 13:58.
// * desc:
// */
//public class CategoryFragment extends BaseFragment {
//
//    private List<String> mTitleList = Arrays.asList("正在上映", "即将上映", "Top250");
//
//    private List<Fragment> mFragmentList = new ArrayList<Fragment>() {{
//        add(new MovieFragment());
//        add(new MovieFragment());
//        add(new MovieFragment());
//    }};
//
//    @BindView(R.id.tabLayout)
//    TabLayout mTabLayout;
//    @BindView(R.id.toolbar)
//    Toolbar mToolbar;
//    @BindView(R.id.viewPager)
//    ViewPager mViewPager;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_category;
//    }
//
//    @Override
//    protected void initView(LayoutInflater inflater) {
//        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);
//            ((BaseActivity) getActivity()).getSupportActionBar().setTitle("榜单");
//        }
//
//        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTitleList, mFragmentList));
//        mViewPager.setCurrentItem(0);
//        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
//        mTabLayout.setupWithViewPager(mViewPager);
//    }
//
//}
