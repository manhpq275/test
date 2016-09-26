package asia.ienter.matching.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.views.fragments.MessagesFragment;
import asia.ienter.matching.views.fragments.ProfileFragment;
import asia.ienter.matching.views.fragments.TopFragment;
import butterknife.InjectView;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class HomeActivity extends FragmentActivity {

    private static final String TAG = "HomeActivity";

    private int mTabSelect;
    private FragmentManager fragmentManager;

    @InjectView(R.id.menuBar)   LinearLayout lnMenuBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initView();

        initService();

        getBundleData();

        changeTab(0);
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.home_content_fragment);
        if (f instanceof MessagesFragment || f instanceof ProfileFragment) {
            super.onBackPressed();
        } else if (f instanceof TopFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void initService() {
    }

    private void initView() {
    }

    public void getBundleData() {
    }

    public void showMenu(){
        if(lnMenuBar==null) return;
        lnMenuBar.animate()
                .setDuration(500)
                .translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        lnMenuBar.setVisibility(View.VISIBLE);

                    }
                });
    }

    private void hideMenu(){
        if(lnMenuBar==null) return;
        lnMenuBar.animate()
                .setDuration(500)
                .translationY(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        lnMenuBar.setVisibility(View.VISIBLE);

                    }
                });
    }

    public void changeTab(int position) {
        mTabSelect = position;
        changBackgroundTab(mTabSelect);
        fragmentManager = getSupportFragmentManager();
        ReplaceFragment replaceFragment = new ReplaceFragment();
        MLog.d(TAG,"changeTab()");
        switch (position) {
            case 0:
                replaceFragment.replace(fragmentManager, new TopFragment(), R.id.home_content_fragment);
                break;
            case 1:
                replaceFragment.replace(fragmentManager, new MessagesFragment(), R.id.home_content_fragment);
                break;
            case 2:
                replaceFragment.replace(fragmentManager, new ProfileFragment(), R.id.home_content_fragment);
                break;
        }
    }

    private void changBackgroundTab(int mTabSelect) {
    }
}
