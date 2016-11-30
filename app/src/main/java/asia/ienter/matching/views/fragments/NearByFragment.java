package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    private Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_nearby, container, false);

        mContext = this.getContext();
        ButterKnife.inject(this, mView);

        if(getUserVisibleHint()){
            fragmentManager = getChildFragmentManager();
            replaceFragment = new ReplaceFragment();
            animationLoading();
        }

        return mView;
    }

    @Override
    protected void initView() {
        tvSearching.setText(getString(R.string.txtSearching,MCApp.getAdvanceSearchView().getDistance()));
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
    }

    @Override
    protected void loadDataFromApi() {
        MLog.d(NearByFragment.class, "loadDataFromApi() Started");
        HomeServices.getInstance().getUserListSearch(MCApp.getUserInstance().getUserId(), MCApp.getAdvanceSearchView(), page, ClientType.AndroidApp, new IGetListUserSearch() {
            @Override
            public void onError(ArrayList errors) {
                onLoadError();
            }

            @Override
            public void onSuccess(ArrayList<UserView> items) {
                hideLoading();
                if(items==null){

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
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
        animationLoading();
    }

    @OnClick(R.id.btnAgree)
    public void onClickAgree() {
        if(userRandom.getMyLike()==1){
            userRandom.setMyLike(0);
        }else{
            userRandom.setMyLike(1);
        }
        UserServices.getInstance().sendLike(userRandom, ClientType.AndroidApp, new ICommonViewCallback() {
            @Override
            public void onError(ArrayList errors) {
                if(userRandom.getMyLike()==1){
                    userRandom.setMyLike(0);
                }else{
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
        userRandom=null;
        initView();
        loadDataFromApi();
        if(isLoading) return;
        isLoading = true;
        Handler handler = new Handler();
        try{
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userRandom==null) return;
                rlResult.setVisibility(View.INVISIBLE);
                MLog.d(NearByFragment.class,"(userRandom()) = "+userRandom.getUserID());

                if(getUserVisibleHint())

                    try{
                        if(fragment!=null)
                            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
                        fragment = ProfileFragment.newInstance(false,userRandom.getUserID());
                        fragmentManager.beginTransaction().add(R.id.tabNearBy_content,fragment).commitAllowingStateLoss();
                    }catch (IllegalStateException e){
                        System.out.print(e.getLocalizedMessage());
                    }

                isLoading = false;
                rlSearching.setVisibility(View.GONE);
                rlResult.setVisibility(View.VISIBLE);

                //    replaceFragment.replace(fragmentManager,fragment,R.id.tabNearBy_content);

            }
        }, 5000);
        }catch (IllegalArgumentException e) {
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
                tvSearching.setText(getString(R.string.txtSearching,MCApp.getAdvanceSearchView().getDistance()));
                animationLoading();
            }
        }
    }


}
