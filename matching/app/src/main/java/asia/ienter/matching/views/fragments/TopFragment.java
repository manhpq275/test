package asia.ienter.matching.views.fragments;

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
import asia.ienter.matching.models.TopView;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.adapters.TopAdapter;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class TopFragment extends BaseFragment {


    private static final String TAG = "TopFragment";
    RecyclerView recycleTopView;

    ArrayList<TopView> topViewArrayList;
    TopAdapter topAdapter;
    private boolean isGrid = false;
    private ArrayList<TopView> mUsers = new ArrayList<>();

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
        recycleTopView = (RecyclerView) mView.findViewById(R.id.recycleTop);
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

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        MLog.e(TAG, "showLoadingMore");
                        showLoadingMore();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MLog.e(TAG, "Load More Data");

                                //Load data
                                int index = topViewArrayList.size();
                                int end = 15;
                                for (int i = index; i < end; i++) {
                                    TopView user = new TopView();
                                    user.setAndroid_version_name("Name " + i);
                                    user.setAndroid_image_url(android_image_urls[end-i]);
                                    topViewArrayList.add(user);
                                }
                                topAdapter.onNotifyDataSetChanged(topViewArrayList);
                                isLoading = false;
                                hideLoading();
                            }
                        }, 5000);
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
            TopView androidVersion = new TopView();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            topViewArrayList.add(androidVersion);
        }
      //  topViewArrayList.add(null);
        topAdapter = new TopAdapter(mContext,topViewArrayList);
        recycleTopView.setAdapter(topAdapter);
        hideLoading();
    }
}
