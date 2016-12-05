package asia.ienter.matching.views.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.ICommonViewCallback;
import asia.ienter.matching.interfaces.IGetListUserSearch;
import asia.ienter.matching.models.CommonView;
import asia.ienter.matching.models.User;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.services.HomeServices;
import asia.ienter.matching.services.LocationService;
import asia.ienter.matching.services.UserServices;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.utils.Utils;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.SettingNearByActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class NearByFragment extends BaseFragment {

    public static final int REQUEST_FROM_NEAR_BY = 10000;
    public static final int RESULT_CODE_PROFILE = 10001;

    int page = 1;
    UserView userRandom = null;

    FragmentManager fragmentManager;
    ReplaceFragment replaceFragment;
    boolean isLoading = false;
    boolean hasGPS = false;

    public static NearByFragment newInstance() {

        Bundle args = new Bundle();

        NearByFragment fragment = new NearByFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @InjectView(R.id.rlSearching)
    RelativeLayout rlSearching;

    @InjectView(R.id.rlResult)
    RelativeLayout rlResult;

    @InjectView(R.id.tvSearching)
    TextView tvSearching;

    @InjectView(R.id.tvReload)
    TextView tvReload;

    @InjectView(R.id.rlHasGPS)
    RelativeLayout rlHasGPS;

    @InjectView(R.id.rlNoGPS)
    RelativeLayout rlNoGPS;

    private Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = this.getContext();
        mView = inflater.inflate(R.layout.fragment_nearby, container, false);
        ButterKnife.inject(this, mView);
        return mView;
    }

    @Override
    protected void initView() {
        LocationManager service = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
        hasGPS = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!hasGPS) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.txt_noGPS))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.txtGotoSetting), new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton(getString(R.string.txtCancel), new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            onLoadNoData();
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
        mView.setBackgroundColor(Color.parseColor("#FF4081"));
        fragmentManager = getChildFragmentManager();
        replaceFragment = new ReplaceFragment();
        rlNoInternetConnection = (RelativeLayout) mView.findViewById(R.id.rlNoInternetConnection);
        rlNoInternetConnection2 = (RelativeLayout) mView.findViewById(R.id.rlNoInternetConnection2);
        rlNoData = (RelativeLayout) mView.findViewById(R.id.rlNoData);
        tvSearching.setText(getString(R.string.txtSearching, MCApp.getAdvanceSearchView().getDistance()));
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
    }


    @Override
    protected void loadDataFromApi() {
        Intent intent = new Intent(mContext, LocationService.class);
        mContext.startService(intent);
        MLog.d(NearByFragment.class, "loadDataFromApi() Started");
        HomeServices.getInstance().getListNearBySearch(page, ClientType.AndroidApp, new IGetListUserSearch() {
            @Override
            public void onError(ArrayList errors) {
                onLoadError();
            }

            @Override
            public void onSuccess(ArrayList<UserView> items) {
                hideLoading();
                if (items == null) {
                    return;
                }
                MLog.d(NearByFragment.class, "loadDataFromApi() = " + new Gson().toJson(items));
                {
                    if (items.size() > 0) {
                        userRandom = items.get(Utils.randInt(items.size()));
                    }
                }

            }
        });


    }


    @OnClick(R.id.btnCancel)
    public void onClickCancel() {
        isLoading = false;
        rlNoData.setVisibility(View.GONE);
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
        animationLoading();
    }

    @OnClick(R.id.btnAgree)
    public void onClickAgree() {
        if (userRandom.getMyLike() == 1) {
            userRandom.setMyLike(0);
        } else {
            userRandom.setMyLike(1);
        }
        UserServices.getInstance().sendLike(userRandom, ClientType.AndroidApp, new ICommonViewCallback() {
            @Override
            public void onError(ArrayList errors) {
                if (userRandom.getMyLike() == 1) {
                    userRandom.setMyLike(0);
                } else {
                    userRandom.setMyLike(1);
                }
            }

            @Override
            public void onSuccess(CommonView item) {

            }
        });
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);

        animationLoading();
    }

    private void animationLoading() {
        userRandom = null;
        initView();
        if (!hasGPS) return;
        if (!hasInternet()) return;
        loadDataFromApi();
        if (isLoading) return;
        isLoading = true;
        Handler handler = new Handler();
        try {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (userRandom == null) {
                        onLoadNoData();
                        return;
                    }

                    rlResult.setVisibility(View.INVISIBLE);
                    MLog.d(NearByFragment.class, "(userRandom()) = " + userRandom.getUserID());

                    if (getUserVisibleHint())

                        try {
                            if (fragment != null)
                                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
                            fragment = ProfileFragment.newInstance(false, userRandom.getUserID());
                            fragmentManager.beginTransaction().add(R.id.tabNearBy_content, fragment).commitAllowingStateLoss();
                        } catch (IllegalStateException e) {
                            System.out.print(e.getLocalizedMessage());
                        }

                    isLoading = false;
                    rlSearching.setVisibility(View.GONE);
                    rlResult.setVisibility(View.VISIBLE);

                    //    replaceFragment.replace(fragmentManager,fragment,R.id.tabNearBy_content);

                }
            }, 5000);
        } catch (IllegalArgumentException e) {
            System.out.print(e.getLocalizedMessage());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // TODO: Add code to remove fragment here
        super.onSaveInstanceState(outState);
    }

    public void onSettingNearBy() {
        Intent profile = new Intent(mContext, SettingNearByActivity.class);
        startActivityForResult(profile, REQUEST_FROM_NEAR_BY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NearByFragment.REQUEST_FROM_NEAR_BY) {
            if (resultCode == RESULT_CODE_PROFILE) {
                ((HomeActivity) getActivity()).changeTab(3);
            } else if (resultCode == SettingNearByActivity.BACK_FROM_NEARBY_SETTING) {
                tvSearching.setText(getString(R.string.txtSearching, MCApp.getAdvanceSearchView().getDistance()));
                animationLoading();
            }
        }
    }


    @Override
    public void onResume() {
        MLog.d(NearByFragment.class, "onResume");
        super.onResume();
        if (getUserVisibleHint()) {
            animationLoading();
        }
    }

    @Override
    protected void onLoadError() {
        mView.setBackgroundColor(Color.TRANSPARENT);
        super.onLoadError();
        rlSearching.setVisibility(View.GONE);
        rlResult.setVisibility(View.GONE);
    }

    @Override
    protected void onLoadNoData() {
        mView.setBackgroundColor(Color.TRANSPARENT);
        super.onLoadNoData();
        rlNoInternetConnection2.setVisibility(View.GONE);
        rlSearching.setVisibility(View.GONE);
        rlResult.setVisibility(View.GONE);
    }

    @Override
    protected void showPullRefresh() {
        super.showPullRefresh();
        onClickCancel();
    }

    @OnClick(R.id.tvReload)
    public void onReload() {
        onClickCancel();
    }

}
