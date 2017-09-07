package me.jokey.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import butterknife.BindView;
import me.jokey.movie.R;
import me.jokey.movie.ui.fragment.CategoryFragment;
import me.jokey.movie.ui.fragment.MovieFragment;
import me.jokey.movie.util.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;

    private Fragment mCategoryFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (BottomNavigationView.OnNavigationItemSelectedListener) item -> {
        switch (item.getItemId()) {
            case R.id.navigation_movie:
                addDefaultFragment(0);
                return true;
            case R.id.navigation_dashboard:
                addDefaultFragment(1);
                return true;
            case R.id.navigation_notifications:
                addDefaultFragment(2);
                return true;
        }
        return false;
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(mNavigationView);

        addDefaultFragment(0);
    }

    private void addDefaultFragment(int index) {
        //开启事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                if (mCategoryFragment == null) {
                    mCategoryFragment = new CategoryFragment();
                    fragmentTransaction.add(R.id.content, mCategoryFragment);
                } else  // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(mCategoryFragment);
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (mCategoryFragment != null) fragmentTransaction.hide(mCategoryFragment);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (mCategoryFragment == null && fragment instanceof MovieFragment)
            mCategoryFragment = fragment;

    }


}
