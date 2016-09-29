package asia.ienter.matching.views.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    protected Context mContext;
    protected ProgressBar mProgressBarLoading;
    protected ProgressBar mProgressBarLoadingMore;
    protected SwipeRefreshLayout mSwipeRefresh;
    protected View mViewData;

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
}
