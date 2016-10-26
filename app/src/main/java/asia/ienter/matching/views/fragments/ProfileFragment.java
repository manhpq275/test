package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        Picasso.with(mContext).load("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT87f6LTPcdzho8Q5aNfn8wPiA0MKcr45om_g4_A1S-hx2xdr7S5g")
                .resize(240, 120).into(imgProfile);
        ImageView imgBackground = (ImageView) mView.findViewById(R.id.imgBackground);
        Picasso.with(mContext).load("http://protiumdesign.com/wp-content/uploads/2015/04/Material-Design-Background-1024.jpg")
                .resize(400, 300).into(imgBackground);
        ImageView imgChangeProfile = (ImageView) mView.findViewById(R.id.imgChangeProfile);
        imgChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfileFragment();
            }
        });

        ImageView imgChangeHobby = (ImageView) mView.findViewById(R.id.imgChangeHobby);
        imgChangeHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHobbyFragment();
            }
        });
    }

    private void changeHobbyFragment() {
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), ChangeHobbyFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_right,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_to_right);
    }

    private void changeProfileFragment() {
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), ChangeAboutMeFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_right,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_to_right);
    }

    private void changeSelectImageFragment(){
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), SelectImageFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_bottom,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_from_top);
    }

    @Override
    protected void loadDataFromApi() {
        Log.i("Data", "Go here");
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
                changeSelectImageFragment();
                break;
            case R.id.from_gallery:
                //handleChangeDisplayName();
                changeSelectImageFragment();
                break;
            case R.id.from_camera:
                //handleChangeSecretPassword();
                changeSelectImageFragment();
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

    public void updateInformationView(String aboutMe) {
        ((TextView) mView.findViewById(R.id.txtAboutMe)).setText(aboutMe);
    }
}
