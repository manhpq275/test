package asia.ienter.matching.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.views.dialogs.DialogGift;
import asia.ienter.matching.views.dialogs.DialogHelpNearBy;
import asia.ienter.matching.views.fragments.MessagesFragment;
import asia.ienter.matching.views.fragments.NearByFragment;
import asia.ienter.matching.views.fragments.ProfileFragment;
import asia.ienter.matching.views.fragments.TopFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class HomeActivity extends FragmentActivity {

    private static final String TAG = "HomeActivity";
    private static final int TAB_MATCHING = 0;
    private static final int TAB_NEAR_BY = 1;
    private static final int TAB_MESSAGES = 2;
    private static final int TAB_PROFILE = 3;

    private int mTabSelect;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private boolean isShowMenu =true;

    Context context;
    @InjectView(R.id.menuBar)   LinearLayout lnMenuBar;
    @InjectView(R.id.tabMatching)   TextView tabMatching;
    @InjectView(R.id.tabNearBy)   TextView tabNearBy;
    @InjectView(R.id.tabMessage)   TextView tabMessage;
    @InjectView(R.id.tabProfile)   TextView tabProfile;
    @InjectView(R.id.leftBtnTopBar) ImageView leftBtnTopBar;
    @InjectView(R.id.rightBtnTopBar) ImageView rightBtnTopBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        initView();

        initService();

        getBundleData();

        changeTab(TAB_MATCHING);
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
        this.context = this;
        this.setScreenSize();
        if(SharedPreference.getInstance().getBoolean("GiftCode", true)) {
            new DialogGift(this).show();
            SharedPreference.getInstance().putBoolean("GiftCode", false);
        }

    }

    public void getBundleData() {
    }

    public void showMenu(){
        if(lnMenuBar==null) return;
        if(isShowMenu) return;
        lnMenuBar.animate()
                .setDuration(300)
                .translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        lnMenuBar.setVisibility(View.VISIBLE);

                    }
                });
        isShowMenu = true;
    }

    public void hideMenu(){
        if(lnMenuBar==null) {
            MLog.e(TAG,"Menubar Null");
            return;
        }
        if(!isShowMenu) return;
        lnMenuBar.animate()
                .setDuration(300)
                .translationY(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        lnMenuBar.setVisibility(View.VISIBLE);

                    }
                });
        isShowMenu = false;
    }

    public void changeTab(int position) {
        mTabSelect = position;
        changeBackgroundTab(mTabSelect);
        fragmentManager = getSupportFragmentManager();
        ReplaceFragment replaceFragment = new ReplaceFragment();
        MLog.d(TAG,"changeTab()");
        switch (position) {
            case TAB_MATCHING:
                fragment = TopFragment.newInstance(null);
                replaceFragment.replace(fragmentManager, fragment, R.id.home_content_fragment);
                break;
            case TAB_NEAR_BY:
                fragment =  NearByFragment.newInstance();
                replaceFragment.replace(fragmentManager, fragment, R.id.home_content_fragment);
                break;
            case TAB_MESSAGES:
                fragment =  new MessagesFragment();
                replaceFragment.replace(fragmentManager,fragment, R.id.home_content_fragment);
                break;
            case TAB_PROFILE:
                fragment =  new ProfileFragment();
                replaceFragment.replace(fragmentManager, fragment, R.id.home_content_fragment);
                break;
        }
    }

    private void changeBackgroundTab(int mTabSelect) {

        leftBtnTopBar.setVisibility(View.VISIBLE);
        switch (mTabSelect) {
            case TAB_MATCHING:
                setTabActive(tabMatching,true);
                setTabActive(tabNearBy,false);
                setTabActive(tabMessage,false);
                setTabActive(tabProfile,false);
                leftBtnTopBar.setImageResource(R.mipmap.btn_gridview);
                rightBtnTopBar.setImageResource(R.mipmap.btn_search_advance);
                break;
            case TAB_NEAR_BY:
                setTabActive(tabMatching,false);
                setTabActive(tabNearBy,true);
                setTabActive(tabMessage,false);
                setTabActive(tabProfile,false);
                leftBtnTopBar.setImageResource(R.mipmap.btn_help);
                rightBtnTopBar.setImageResource(R.mipmap.btn_setting);
                break;
            case TAB_MESSAGES:
                setTabActive(tabMatching,false);
                setTabActive(tabNearBy,false);
                setTabActive(tabMessage,true);
                setTabActive(tabProfile,false);
                leftBtnTopBar.setVisibility(View.INVISIBLE);
                rightBtnTopBar.setImageResource(R.mipmap.btn_lock);
                break;
            case TAB_PROFILE:
                setTabActive(tabMatching,false);
                setTabActive(tabNearBy,false);
                setTabActive(tabMessage,false);
                setTabActive(tabProfile,true);
                leftBtnTopBar.setImageResource(R.mipmap.btn_about);
                rightBtnTopBar.setImageResource(R.mipmap.btn_setting);
                break;
        }
    }



    private void setTabActive(TextView tab,boolean isActive){
        if(isActive){
            tab.setTextColor(ContextCompat.getColor(context, R.color.text_color_menu_active));
        }else{
            tab.setTextColor(ContextCompat.getColor(context, R.color.text_color_menu_no_active));
        }
    }
    @OnClick(R.id.leftBtnTopBar)
    public void onClickLeftBtnTopBar(){
        MLog.e(TAG,"Left Btn TopBar Onclick");
        fragment = getSupportFragmentManager().findFragmentById(R.id.home_content_fragment);

        if (fragment==null) return;
        if(fragment instanceof TopFragment){
            OnClickTopViewChange(fragment);
        }else if (fragment instanceof NearByFragment){
            OnClickNearByHelp();
        }else if (fragment instanceof ProfileFragment){
            OnClickProfileAbout();
        }else return;

    }

    private void OnClickTopViewChange(Fragment fragment){
        ((TopFragment)fragment).onChangeTopViewCallback();
    }

    private void OnClickNearByHelp(){
        new DialogHelpNearBy(this).show();
    }
    private void OnClickProfileAbout(){
        ((ProfileFragment)fragment).onGoAbout();
    }


    boolean isLock =false;
    @OnClick(R.id.rightBtnTopBar)
    public void onClickRightBtnTopBar(){
        MLog.e(TAG, "Right Btn TopBar Onclick");
        fragment = getSupportFragmentManager().findFragmentById(R.id.home_content_fragment);

        if (fragment==null) return;
        if(fragment instanceof TopFragment){
            ((TopFragment)fragment).onAdvanceSearchTopView();
        }else if (fragment instanceof NearByFragment){
            ((NearByFragment)fragment).onSettingNearBy();
        }else if (fragment instanceof ProfileFragment){
            ((ProfileFragment)fragment).onGoSetting();
        }else if (fragment instanceof MessagesFragment){
            isLock = !isLock;
            ((MessagesFragment)fragment).onLockClick(isLock);
            if(isLock){
                rightBtnTopBar.setImageResource(R.mipmap.btn_unlock);
            }else{
                rightBtnTopBar.setImageResource(R.mipmap.btn_lock);
            }
        }else return;

    }

    @OnClick(R.id.tabMatching)
    public void onClickTabMatching(){
        MLog.e(TAG,"change tab MAtching");
        changeTab(TAB_MATCHING);
    }

    @OnClick(R.id.tabNearBy)
    public void onClickTabNearBy(){
        MLog.e(TAG,"change tab NearBy");
        changeTab(TAB_NEAR_BY);
    }

    @OnClick(R.id.tabMessage)
    public void onClickTabMessages(){
        MLog.e(TAG,"change tab Messages");
        changeTab(TAB_MESSAGES);
    }

    @OnClick(R.id.tabProfile)
    public void onClickTabProfile(){
        MLog.e(TAG,"change tab Profile");
        changeTab(TAB_PROFILE);
    }

    private void setScreenSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        MCApp.setScreenSize(size);
    }


    public void changeInformation(String aboutMe) {
        fragment = getSupportFragmentManager().findFragmentById(R.id.home_content_fragment);
        if (fragment==null) return;
        if(fragment instanceof ProfileFragment){
            ((ProfileFragment)fragment).updateInformationView(aboutMe);
        }
    }
}
