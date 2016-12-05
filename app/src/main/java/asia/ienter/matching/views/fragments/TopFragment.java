package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.ICommonViewCallback;
import asia.ienter.matching.interfaces.IDialogLikeCallback;
import asia.ienter.matching.interfaces.IGetListUserSearch;
import asia.ienter.matching.interfaces.ITopViewCallback;
import asia.ienter.matching.models.CommonView;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.services.HomeServices;
import asia.ienter.matching.services.UserServices;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.activities.AdvanceSearchActivity;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.MyPageActivity;
import asia.ienter.matching.views.adapters.TopAdapter;
import asia.ienter.matching.views.dialogs.DialogLike;
import butterknife.ButterKnife;
import butterknife.InjectView;

/** MLog.d(TAG,"loadDataFromApi()");
 * Created by phamquangmanh on 9/15/16.
 */
public class TopFragment extends BaseFragment implements ITopViewCallback {
    private static final String TAG = "TopFragment";
    ArrayList<UserView> topViewArrayList;
    TopAdapter topAdapter;
    public boolean isGrid = true;
    private ArrayList<UserView> mUsers = new ArrayList<>();
    HomeActivity homeActivity;
    @InjectView(R.id.recycleTop)  RecyclerView recycleTopView;
    int page = 0;

    public static TopFragment newInstance(Bundle args) {
        TopFragment myFragment = new TopFragment();
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_top, container, false);
        mContext = this.getContext();
        initView();
        MLog.d(TAG,"onCreateView()="+getUserVisibleHint() );

        if(getUserVisibleHint()){
            isGrid = true;
            showPullRefresh();
        }
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean visibleHint){
        MLog.d(TopFragment.class,"visibleHint=="+ visibleHint);
        super.setUserVisibleHint(visibleHint);
        if(!visibleHint) return;
        isGrid = true;
        showPullRefresh();
    }
    private boolean isLoading = true;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    @Override
    protected void initView() {

        homeActivity = (HomeActivity)getActivity();
        ButterKnife.inject(this,mView);

        //Load Progress
        mProgressBarLoading = (ProgressBar) mView.findViewById(R.id.mProgressLoading);
        mProgressBarLoadingMore = (ProgressBar) mView.findViewById(R.id.mProgressLoadingMore);
        mSwipeRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.mSwipeRefresh);
        rlNoInternetConnection = (RelativeLayout) mView.findViewById(R.id.rlNoInternetConnection);
        rlNoInternetConnection2 = (RelativeLayout) mView.findViewById(R.id.rlNoInternetConnection2);
        rlNoData = (RelativeLayout) mView.findViewById(R.id.rlNoData);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showPullRefresh();
            }
        });

        topViewArrayList = new ArrayList<>();
        recycleTopView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,2);
        recycleTopView.setLayoutManager(layoutManager);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recycleTopView.getLayoutManager();
        recycleTopView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (dy>30){
                    homeActivity.hideMenu();
                }else if (dy<-30){
                    homeActivity.showMenu();
                }



                if (!isLoading && (totalItemCount <= (lastVisibleItem + visibleThreshold))) {
                        MLog.e(TAG, "showLoadingMore");
                        showLoadingMore();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MLog.e(TAG, "Load More Data");

                                loadDataFromApi();
                               // topAdapter.onNotifyDataSetChanged(topViewArrayList);
                                isLoading = false;
                                hideLoading();
                            }
                        }, 1000);
                    isLoading = true;
                }
            }
        });
    }

public void showPullRefresh(){
    super.showPullRefresh();
    page=0;
    isLoading = false;
    topViewArrayList.clear();
    recycleTopView.setAdapter(null);

    loadDataFromApi();
}

    @Override
    protected void loadDataFromApi() {
        if(!hasInternet()){
            recycleTopView.setVisibility(View.INVISIBLE);
            return;
        }
        MLog.d(TAG,"loadDataFromApi() Started");
        showLoading();
        page++;
        if(page==1){
            recycleTopView.setVisibility(View.INVISIBLE);
        }

        HomeServices.getInstance().getUserListSearch(MCApp.getUserInstance().getUserId(), MCApp.getAdvanceSearchView(),page, ClientType.AndroidApp, new IGetListUserSearch() {
            @Override
            public void onError(ArrayList errors) {
                hideLoading();
                onLoadError();
            }

            @Override
            public void onSuccess(ArrayList<UserView> items) {
                hideLoading();
                recycleTopView.setVisibility(View.VISIBLE);
                MLog.d(TAG,"loadDataFromApi() = "+ new Gson().toJson(items));
                if(items==null){
                    if(topViewArrayList.size()==0) {
                        onLoadNoData();
                    }
                        isLoading = true;
                        page--;
                }else{
                    if(items.size()>0){

                        topViewArrayList.addAll(items);
                        topAdapter = new TopAdapter(TopFragment.this,topViewArrayList);
                        recycleTopView.setAdapter(topAdapter);
                        MLog.d(TAG,"loadDataFromApi()1 = "+ new Gson().toJson(items));
                    }
                }
            }
        });

    }



    public void onChangeTopViewCallback() {
        isGrid = !isGrid;
        if(isGrid){
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,2);
            recycleTopView.setLayoutManager(layoutManager);
        }else{
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,1);
            recycleTopView.setLayoutManager(layoutManager);
        }
        topAdapter = new TopAdapter(this,topViewArrayList);
        recycleTopView.setAdapter(topAdapter);


    }

    public void onAdvanceSearchTopView() {
        Intent profile = new Intent(mContext, AdvanceSearchActivity.class);
        profile.putExtra("ID",10);
        startActivityForResult(profile,AppConstants.BACK_FROM_ADVANCE_SEARCH_TO_HOME);

    }




    @Override
    public void OnItemClickRecycleView(UserView topView, int position) {
        String listUser = new Gson().toJson(topViewArrayList, new TypeToken<List<UserView>>(){}.getType());
        Intent profile = new Intent(mContext, MyPageActivity.class);
        profile.putExtra("UserArray",listUser);
        profile.putExtra("UserPosition",position);
        startActivity(profile);
    }

    @Override
    public boolean OnItemClickLike(final View btnLike, int position) {
        MLog.e(TAG,"Item click Like");
        final UserView topView = topViewArrayList.get(position);
        if(topView.getMyLikeSpecial()==1||topView.getMyLike()==1){
            btnLike.setEnabled(false);
            return false;
        }
        new DialogLike(mContext, new IDialogLikeCallback() {

            @Override
            public void onSendLike() {
                if(topView.getMyLike()==1){
                    topView.setMyLike(0);
                }else{
                    topView.setMyLike(1);
                }
                setLike((ImageView)btnLike,topView.getMyLike());
                UserServices.getInstance().sendLike(topView, ClientType.AndroidApp, new ICommonViewCallback() {
                    @Override
                    public void onError(ArrayList errors) {
                        if(topView.getMyLike()==1){
                            topView.setMyLike(0);
                        }else{
                            topView.setMyLike(1);
                        }
                        setLike((ImageView)btnLike,topView.getMyLike());
                    }

                    @Override
                    public void onSuccess(CommonView item) {

                    }
                });
            }

            @Override
            public void onSendRequest() {
                if(topView.getMyLikeSpecial()==1){
                    topView.setMyLikeSpecial(0);
                }else{
                    topView.setMyLikeSpecial(1);
                }
                setLike((ImageView)btnLike,topView.getMyLikeSpecial());
                UserServices.getInstance().sendLike(topView, ClientType.AndroidApp, new ICommonViewCallback() {
                    @Override
                    public void onError(ArrayList errors) {
                        if(topView.getMyLikeSpecial()==1){
                            topView.setMyLikeSpecial(0);
                        }else{
                            topView.setMyLikeSpecial(1);
                        }
                        setLike((ImageView)btnLike,topView.getMyLikeSpecial());
                    }

                    @Override
                    public void onSuccess(CommonView item) {

                    }
                });

            }
        }).show();
        return  true;
    }

    public void setLike(ImageView btnLike, int isLike){
        if(isLike==0){
            if(isGrid){
                btnLike.setImageResource(R.mipmap.btn_like);
            }else{
                btnLike.setImageResource(R.mipmap.btn_like_full);
            }
        }else{
            if(isGrid){
                btnLike.setImageResource(R.mipmap.btn_liked);
            }else{
                btnLike.setImageResource(R.mipmap.btn_liked_full);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data){
        if (requestCode == AppConstants.BACK_FROM_ADVANCE_SEARCH_TO_HOME) {
            if (resultCode == AppConstants.BACK_FROM_ADVANCE_SEARCH_TO_HOME_RESULT_MATCHING) {
                showPullRefresh();
            }
        }

    }

}
