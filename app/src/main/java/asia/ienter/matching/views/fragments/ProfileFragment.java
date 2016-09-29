package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.utils.custom.CircleImageView;
import asia.ienter.matching.views.activities.AboutActivity;
import asia.ienter.matching.views.activities.SettingActivity;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class ProfileFragment extends BaseFragment {
    private ReplaceFragment fragmentHandle;
    private CircleImageView imgProfile;
    private ImageView changeCover;
    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        fragmentHandle = new ReplaceFragment();
        imgProfile = (CircleImageView) mView.findViewById(R.id.imgProfileUser);
        changeCover = (ImageView) mView.findViewById(R.id.imgChangeCover);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupSelectImage(imgProfile);
            }
        });
        changeCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupSelectImage(changeCover);
            }
        });

        ImageView imgChangeProfile = (ImageView) mView.findViewById(R.id.imgChangeProfile);
        imgChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
    }

    private void changeFragment(){
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), SelectImageFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_bottom,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_from_top);
    }

    @Override
    protected void loadDataFromApi() {

    }

    public void handleGoSetting() {
        Intent iSetting = new Intent(mContext, SettingActivity.class);
        startActivity(iSetting);

    }

    public void handleGoAboutApp() {
        Intent iAbout = new Intent(mContext, AboutActivity.class);
        startActivity(iAbout);
    }

    public void onGoAbout() {
        handleGoAboutApp();

    }

    public void onGoSetting() {
        handleGoSetting();

    }

    /**
     * Handle on item menu popup click
     * @param item
     */
    private void handlePopupClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.from_facebook:
                //pickImage();
                changeFragment();
                break;
            case R.id.from_gallery:
                //handleChangeDisplayName();
                changeFragment();
                break;
            case R.id.from_camera:
                //handleChangeSecretPassword();
                changeFragment();
                break;
            default:
                break;
        }
    }

    /**
     * show popup menu on button
     * @param btnClick
     */
    private void showPopupSelectImage(View btnClick){
        PopupMenu popup = new PopupMenu(mContext, btnClick);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                handlePopupClick(item);
                return true;
            }
        });

        popup.show();
    }
}
