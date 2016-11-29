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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogLikeCallback;
import asia.ienter.matching.interfaces.IGetListUserSearch;
import asia.ienter.matching.interfaces.ITopViewCallback;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.services.HomeServices;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.activities.AdvanceSearchActivity;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.MyPageActivity;
import asia.ienter.matching.views.adapters.TopAdapter;
import asia.ienter.matching.views.dialogs.DialogLike;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
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
        MLog.d(TAG,"onCreateView()");
        if(getUserVisibleHint()){
            isGrid = true;
            showPullRefresh();
        }
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean visibleHint){
        super.setUserVisibleHint(visibleHint);
        if(!visibleHint) return;
        isGrid = true;
        showPullRefresh();
    }
    private boolean isLoading;
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


        rlNoInternetConnection = (RelativeLayout)mView.findViewById(R.id.rlNoInternetConnection);
        rlNoInternetConnection2 = (RelativeLayout)mView.findViewById(R.id.rlNoInternetConnection2);

    }

public void showPullRefresh(){
    super.showPullRefresh();
    page=0;
    isLoading = false;
    topViewArrayList.clear();
    if(!hasInternet()){
        recycleTopView.setVisibility(View.INVISIBLE);
        return;
    }
    loadDataFromApi();
}

    @Override
    protected void loadDataFromApi() {
        MLog.d(TAG,"loadDataFromApi()");
        showLoading();
        page++;
        if(page==1){
            recycleTopView.setVisibility(View.INVISIBLE);
        }

        HomeServices.getInstance().getUserListMatching(1,page, ClientType.AndroidApp, new IGetListUserSearch() {
            @Override
            public void onError(ArrayList errors) {

            }

            @Override
            public void onSuccess(ArrayList<UserView> items) {
                hideLoading();
                recycleTopView.setVisibility(View.VISIBLE);
                if(items==null){
                        isLoading = true;
                        page--;
                }else{
                    if(items.size()>0){
                        for(int i=0;i<items.size();i++){
                            topViewArrayList.add(items.get(i));
                        }
                        topAdapter = new TopAdapter(TopFragment.this,topViewArrayList);
                        recycleTopView.setAdapter(topAdapter);
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
        startActivityForResult(profile,10);

    }


    @Override
    public void OnItemClickRecycleView(UserView topView) {
        MLog.e(TAG,"Item click RecycleView");
        Intent profile = new Intent(mContext, MyPageActivity.class);
        profile.putExtra("ID",10);
        startActivity(profile);
    }

    @Override
    public boolean OnItemClickLike(int position) {
        MLog.e(TAG,"Item click Like");
        UserView topView = topViewArrayList.get(position);
        new DialogLike(mContext, new IDialogLikeCallback() {

            @Override
            public void onSendLike() {
                Toast.makeText(mContext,"Send Like",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSendRequest() {
                Toast.makeText(mContext,"Send Request",Toast.LENGTH_SHORT).show();

            }
        }).show();
        MLog.e(TAG,"Item click Like 2");
        return  true;
    }
}
