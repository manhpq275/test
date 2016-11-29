package asia.ienter.matching.views.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import asia.ienter.matching.R;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    protected Context mContext;
    protected ProgressBar mProgressBarLoading;
    protected ProgressBar mProgressBarLoadingMore;
    protected SwipeRefreshLayout mSwipeRefresh;
    protected RelativeLayout rlNoInternetConnection,rlNoInternetConnection2;

    protected abstract void initView();

    protected abstract void loadDataFromApi();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(mView==null) return;
        if(isVisibleToUser){
            showLoading();
            loadDataFromApi();
        }

    }

    protected void showLoading(){
        if(mProgressBarLoading!=null)       mProgressBarLoading.setVisibility(View.VISIBLE);
        if(mProgressBarLoadingMore!=null)   mProgressBarLoadingMore.setVisibility(View.GONE);
        if(mSwipeRefresh!=null)             mSwipeRefresh.setRefreshing(false);
    }

    protected void showLoadingMore(){
        if(mProgressBarLoading!=null)       mProgressBarLoading.setVisibility(View.GONE);
        if(mProgressBarLoadingMore!=null)   mProgressBarLoadingMore.setVisibility(View.VISIBLE);
        if(mSwipeRefresh!=null)             mSwipeRefresh.setRefreshing(false);
    }

    protected void showPullRefresh(){
        if(mProgressBarLoading!=null)       mProgressBarLoading.setVisibility(View.GONE);
        if(mProgressBarLoadingMore!=null)   mProgressBarLoadingMore.setVisibility(View.GONE);
        if(mSwipeRefresh!=null)             mSwipeRefresh.setRefreshing(true);
    }

    protected void hideLoading(){
        if(mProgressBarLoading!=null)       mProgressBarLoading.setVisibility(View.GONE);
        if(mProgressBarLoadingMore!=null)   mProgressBarLoadingMore.setVisibility(View.GONE);
        if(mSwipeRefresh!=null)             mSwipeRefresh.setRefreshing(false);
    }

    protected boolean hasInternet(){
        boolean result = false;
        if(rlNoInternetConnection2!=null)    rlNoInternetConnection2.setVisibility(View.GONE);
        showLoading();
        ConnectivityManager ConnectionManager=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        result =  (networkInfo != null && networkInfo.isConnected()==true ) ?  true : false;
        hideLoading();
        if(result){
            if(rlNoInternetConnection!=null)    rlNoInternetConnection.setVisibility(View.GONE);
            if(rlNoInternetConnection2!=null)    rlNoInternetConnection2.setVisibility(View.GONE);
        }else{
            if(rlNoInternetConnection!=null)    rlNoInternetConnection.setVisibility(View.VISIBLE);
            if(rlNoInternetConnection2!=null)    rlNoInternetConnection2.setVisibility(View.VISIBLE);
            rlNoInternetConnection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPullRefresh();
                }
            });
        }
        return result;
    }


}
