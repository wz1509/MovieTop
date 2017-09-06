package me.jokey.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import butterknife.BindView;
import me.jokey.movie.R;
import me.jokey.movie.ui.fragment.TopFragment;
import me.jokey.movie.util.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;

    private Fragment mTopFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (BottomNavigationView.OnNavigationItemSelectedListener) item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
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
                if (mTopFragment == null) {
                    mTopFragment = new TopFragment();
                    fragmentTransaction.add(R.id.content, mTopFragment);
                } else  // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(mTopFragment);
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (mTopFragment != null) fragmentTransaction.hide(mTopFragment);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (mTopFragment == null && fragment instanceof TopFragment) mTopFragment = fragment;
    }


}
