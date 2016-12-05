package asia.ienter.matching.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.services.LocationService;
import asia.ienter.matching.services.UserServices;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.utils.UIHelper;
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

    private int mTabSelect=-1;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    ReplaceFragment replaceFragment;
    private boolean isShowMenu =true;
    private ProfileFragment fragmentProfile = ProfileFragment.newInstance(true, null);
    Context context;
    @InjectView(R.id.menuBar)   LinearLayout lnMenuBar;

    @InjectView(R.id.tv_tabMatching)   TextView tv_tabMatching;
    @InjectView(R.id.tv_tabNearBy)   TextView tv_tabNearBy;
    @InjectView(R.id.tv_tabMessage)   TextView tv_tabMessage;
    @InjectView(R.id.tv_tabProfile)   TextView tv_tabProfile;

    @InjectView(R.id.img_tabMatching)   ImageView img_tabMatching;
    @InjectView(R.id.img_tabNearBy)   ImageView img_tabNearBy;
    @InjectView(R.id.img_tabMessage)   ImageView img_tabMessage;
    @InjectView(R.id.img_tabProfile)   ImageView img_tabProfile;

    @InjectView(R.id.leftBtnTopBar) ImageView leftBtnTopBar;
    @InjectView(R.id.rightBtnTopBar) ImageView rightBtnTopBar;
    @InjectView(R.id.rightBtnTopBarText) TextView rightBtnTopBarText;


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
        if (f instanceof MessagesFragment ) {
            super.onBackPressed();
        } else if (f instanceof TopFragment) {
            UIHelper.killApp(false);
        } else if (f instanceof ProfileFragment) {
            UIHelper.killApp(false);
        }else {
            super.onBackPressed();
        }
    }

    private void initService() {
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
        loadData();
    }

    private void initView() {
        this.context = this;
        replaceFragment = new ReplaceFragment();
        fragmentManager = getSupportFragmentManager();
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
        if(mTabSelect==position) return;
        mTabSelect = position;
        changeBackgroundTab(mTabSelect);
        MLog.d(TAG, "changeTab()");
        rightBtnTopBarText.setVisibility(View.GONE);
        rightBtnTopBar.setVisibility(View.VISIBLE);
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
                rightBtnTopBarText.setVisibility(View.VISIBLE);
                rightBtnTopBar.setVisibility(View.GONE);
                fragment =  MessagesFragment.newInstance(null);
                replaceFragment.replace(fragmentManager,fragment, R.id.home_content_fragment);
                break;
            case TAB_PROFILE:
                fragment =  fragmentProfile;
                replaceFragment.replace(fragmentManager, fragment, R.id.home_content_fragment);
                break;
        }
    }

    private void changeBackgroundTab(int mTabSelect) {

        leftBtnTopBar.setVisibility(View.VISIBLE);
        switch (mTabSelect) {
            case TAB_MATCHING:
                setTabActive(tv_tabMatching,true);
                setTabActive(tv_tabNearBy,false);
                setTabActive(tv_tabMessage,false);
                setTabActive(tv_tabProfile,false);
                img_tabMatching.setImageResource(R.mipmap.btn_matching_active);
                img_tabMessage.setImageResource(R.mipmap.btn_message_no_active);
                img_tabNearBy.setImageResource(R.mipmap.btn_nearby_no_active);
                img_tabProfile.setImageResource(R.mipmap.btn_profile_no_active);
                leftBtnTopBar.setImageResource(R.mipmap.btn_gridview);
                rightBtnTopBar.setImageResource(R.mipmap.btn_search_advance);
                break;
            case TAB_NEAR_BY:
                setTabActive(tv_tabMatching,false);
                setTabActive(tv_tabNearBy,true);
                setTabActive(tv_tabMessage,false);
                setTabActive(tv_tabProfile,false);
                img_tabMatching.setImageResource(R.mipmap.btn_matching_no_active);
                img_tabMessage.setImageResource(R.mipmap.btn_message_no_active);
                img_tabNearBy.setImageResource(R.mipmap.btn_nearby_active);
                img_tabProfile.setImageResource(R.mipmap.btn_profile_no_active);
                leftBtnTopBar.setImageResource(R.mipmap.btn_help);
                rightBtnTopBar.setImageResource(R.mipmap.btn_setting);
                break;
            case TAB_MESSAGES:
                img_tabMatching.setImageResource(R.mipmap.btn_matching_no_active);
                img_tabMessage.setImageResource(R.mipmap.btn_message_active);
                img_tabNearBy.setImageResource(R.mipmap.btn_nearby_no_active);
                img_tabProfile.setImageResource(R.mipmap.btn_profile_no_active);
                setTabActive(tv_tabMatching,false);
                setTabActive(tv_tabNearBy,false);
                setTabActive(tv_tabMessage,true);
                setTabActive(tv_tabProfile,false);
                leftBtnTopBar.setVisibility(View.INVISIBLE);
                rightBtnTopBar.setImageResource(R.mipmap.btn_lock);
                break;
            case TAB_PROFILE:
                img_tabMatching.setImageResource(R.mipmap.btn_matching_no_active);
                img_tabMessage.setImageResource(R.mipmap.btn_message_no_active);
                img_tabNearBy.setImageResource(R.mipmap.btn_nearby_no_active);
                img_tabProfile.setImageResource(R.mipmap.btn_profile_active);
                setTabActive(tv_tabMatching,false);
                setTabActive(tv_tabNearBy,false);
                setTabActive(tv_tabMessage,false);
                setTabActive(tv_tabProfile,true);
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
        MLog.e(TAG, "Left Btn TopBar Onclick");
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
        if(((TopFragment) fragment).isGrid){
            leftBtnTopBar.setImageResource(R.mipmap.btn_gridview);
        }else{
            leftBtnTopBar.setImageResource(R.mipmap.btn_listview);
        }
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

    @OnClick(R.id.rightBtnTopBarText)
    public void onClickRightBtnTopBarText(){
        MLog.e(TAG, "Right Btn TopBar Onclick");
        fragment = getSupportFragmentManager().findFragmentById(R.id.home_content_fragment);

        if (fragment==null) return;
        if (fragment instanceof MessagesFragment){
            isLock = !isLock;
            ((MessagesFragment)fragment).onLockClick(isLock);
            if(isLock){
                rightBtnTopBarText.setText(getString(R.string.btn_done));
            }else{
                rightBtnTopBarText.setText(getString(R.string.txtEdit));
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
        MLog.e(TAG, "change tab Profile");
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

    public void loadData(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.CALL_CAMERA) {
                try {
                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), ProfileFragment.imageUri);
                    fragment = getSupportFragmentManager().findFragmentById(R.id.home_content_fragment);
                    final String imageurl = getRealPathFromURI(ProfileFragment.imageUri);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ProfileFragment)fragment).addViewPicture(ProfileFragment.imageUri, imageurl);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == AppConstants.CALL_GALLERY) {
                final Intent dataGet = data;
                final Uri selectedImage = dataGet.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                if(c!=null) {
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    final String picturePath = c.getString(columnIndex);
                    final Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                    UserServices.getInstance().RequestMultiPart(new File(picturePath));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ProfileFragment)fragment).addViewPicture( selectedImage, picturePath);
                        }
                    });
                    c.close();
                }
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor!=null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String result = cursor.getString(column_index);
            cursor.close();
            return result;
        }
        return "";
    }

}
