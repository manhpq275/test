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

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.ITopViewCallback;
import asia.ienter.matching.models.TopView;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.activities.AdvanceSearchActivity;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.MyPageActivity;
import asia.ienter.matching.views.adapters.TopAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class TopFragment extends BaseFragment implements ITopViewCallback {


    private static final String TAG = "TopFragment";
    ArrayList<TopView> topViewArrayList;
    TopAdapter topAdapter;
    public boolean isGrid = true;
    private ArrayList<TopView> mUsers = new ArrayList<>();
    HomeActivity homeActivity;
    @InjectView(R.id.recycleTop)  RecyclerView recycleTopView;



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
            loadDataFromApi();
        }
        return mView;
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
                loadDataFromApi();
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

                                //Load data
                                int index = topViewArrayList.size();
                                int end = index + 5;
                                for (int i = index; i < end; i++) {
                                    TopView user = new TopView(""+i,"Name " + i,true);
                                    user.setAndroid_version_name("Name " + i);
                                    user.setAndroid_image_url(android_image_urls[4]);
                                    topViewArrayList.add(user);
                                }
                                topAdapter.onNotifyDataSetChanged(topViewArrayList);
                                isLoading = false;
                                hideLoading();
                            }
                        }, 1000);
                    isLoading = true;
                }
            }
        });




    }

    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };

    @Override
    protected void loadDataFromApi() {
        MLog.d(TAG,"loadDataFromApi()");
        showLoading();
        topViewArrayList.clear();
        for(int i=0;i<android_version_names.length;i++){
            TopView androidVersion = new TopView(""+i,"Name "+i,false);
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            topViewArrayList.add(androidVersion);
        }
      //  topViewArrayList.add(null);
        topAdapter = new TopAdapter(this,topViewArrayList);
        recycleTopView.setAdapter(topAdapter);
        hideLoading();
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
    public void OnItemClickRecycleView(TopView topView) {
        MLog.e(TAG,"Item click RecycleView");
        Intent profile = new Intent(mContext, MyPageActivity.class);
        profile.putExtra("ID",10);
        startActivity(profile);
    }

    @Override
    public void OnItemClickLike(int position) {
        MLog.e(TAG,"Item click Like");
        TopView topView = topViewArrayList.get(position);
        topViewArrayList.get(position).setLike(!topView.isLike());


        MLog.e(TAG,"Item click Like 2");
    }
}
