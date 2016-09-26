package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IProfileActionBarCallback;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.views.activities.AboutActivity;
import asia.ienter.matching.views.activities.SettingActivity;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class ProfileFragment extends BaseFragment implements IProfileActionBarCallback {
    private ReplaceFragment fragmentHandle;
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
        Button btnGoAbout = (Button) mView.findViewById(R.id.btnAboutApp);
        btnGoAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGoAboutApp();
            }
        });
        Button btnGoSetting = (Button) mView.findViewById(R.id.btnSetting);
        btnGoSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGoSetting();
            }
        });
    }

    @Override
    protected void loadDataFromApi() {

    }

    private void handleGoSetting() {
//        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), SettingFragment.newInstance(), R.id.layoutContent,
//                R.anim.enter_from_bottom,
//                R.anim.hold,
//                R.anim.hold,
//                R.anim.exit_from_top);
        Intent iSetting = new Intent(mContext, SettingActivity.class);
        startActivity(iSetting);

    }

    private void handleGoAboutApp() {
//        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), AboutFragment.newInstance(), R.id.layoutContent,
//                R.anim.enter_from_bottom,
//                R.anim.hold,
//                R.anim.hold,
//                R.anim.exit_from_top);
        Intent iAbout = new Intent(mContext, AboutActivity.class);
        startActivity(iAbout);
    }

    @Override
    public void onGoAbout() {
        handleGoAboutApp();

    }

    @Override
    public void onGoSetting() {
        handleGoSetting();

    }
}
