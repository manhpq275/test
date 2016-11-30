package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IGetListUserSearch;
import asia.ienter.matching.interfaces.IMessagesCallback;
import asia.ienter.matching.interfaces.ITopViewCallback;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.services.UserServices;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.activities.ChatActivity;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.MyPageActivity;
import asia.ienter.matching.views.adapters.MessageListAdapter;
import asia.ienter.matching.views.dialogs.DialogChat;
import asia.ienter.matching.views.dialogs.DialogLikeMe;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class MessagesFragment extends BaseFragment implements ITopViewCallback,IMessagesCallback {

    private static final String TAG = "MessagesFragment";
    private static final int TAB_CONTACT = 1;
    private static final int TAB_INVITETION = 2;
    private static final int TAB_MYREQUEST = 3;
    ArrayList<UserView> topViewArrayList;
    ArrayList<UserView> topViewArrayList_like_me;
    ArrayList<UserView> topViewArrayList_me_like;
    MessageListAdapter adapter;
    private int pageContact = 0;
    private int pageLikeMe =0;
    private int pageMeLike=0;
    private HomeActivity homeActivity;

    public int getTabSelected() {
        return tabSelected;
    }

    public void setTabSelected(int tabSelected) {
        this.tabSelected = tabSelected;
    }

    private int tabSelected = -1;
    @InjectView(R.id.recycleTop)
    RecyclerView recycleTopView;

    @InjectView(R.id.tab1)
    TextView tab1;

    @InjectView(R.id.tab2)
    TextView tab2;

    @InjectView(R.id.tab3)
    TextView tab3;

    public static MessagesFragment newInstance(Bundle args) {
        MessagesFragment myFragment = new MessagesFragment();
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_messages, container, false);
        mContext = this.getContext();
        ButterKnife.inject(this,mView);
        initView();
        MLog.d(TAG,"onCreateView()");
        if(getUserVisibleHint()){
            onTabChange(TAB_CONTACT);
        }
        return mView;
    }
    private boolean isLoading = true;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    @Override
    protected void initView() {
        homeActivity = (HomeActivity)getActivity();
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
        topViewArrayList_like_me = new ArrayList<>();
        topViewArrayList_me_like = new ArrayList<>();
        recycleTopView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,1);
        recycleTopView.setLayoutManager(layoutManager);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recycleTopView.getLayoutManager();

        recycleTopView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();


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

    @Override
    protected void loadDataFromApi() {
        MLog.d(TAG,"loadDataFromApi() Started");
        showLoading();




        switch (getTabSelected()){
            case TAB_CONTACT:

                pageContact++;
                if(pageContact==1){
                    recycleTopView.setVisibility(View.INVISIBLE);
                }
                UserServices.getInstance().getUserListMatched(MCApp.getUserInstance().getUserId(), pageContact, ClientType.AndroidApp, new IGetListUserSearch() {
                    @Override
                    public void onError(ArrayList errors) {

                    }

                    @Override
                    public void onSuccess(ArrayList<UserView> items) {
                        hideLoading();
                        recycleTopView.setVisibility(View.VISIBLE);
                        MLog.d(TAG,"loadDataFromApi() = "+ new Gson().toJson(items));
                        if(items==null){
                            isLoading = true;
                            pageContact--;
                        }else{
                            if(items.size()>0){
                                MLog.d(TAG,"List = "+new Gson().toJson(items));
                                topViewArrayList.addAll(items);
                                adapter = new MessageListAdapter(MessagesFragment.this,topViewArrayList);
                                recycleTopView.setAdapter(adapter);
                            }
                        }


                        hideLoading();
                    }
                });
                break;
            case TAB_INVITETION:

                pageLikeMe++;
                if(pageLikeMe==1){
                    recycleTopView.setVisibility(View.INVISIBLE);
                }
                UserServices.getInstance().getUserListLikeMe(MCApp.getUserInstance().getUserId(), pageLikeMe, ClientType.AndroidApp, new IGetListUserSearch() {
                    @Override
                    public void onError(ArrayList errors) {

                    }

                    @Override
                    public void onSuccess(ArrayList<UserView> items) {
                        hideLoading();
                        recycleTopView.setVisibility(View.VISIBLE);
                        MLog.d(TAG,"loadDataFromApi() = "+ new Gson().toJson(items));
                        if(items==null){
                            isLoading = true;
                            pageLikeMe--;
                        }else{
                            if(items.size()>0){
                                MLog.d(TAG,"List = "+new Gson().toJson(items));
                                topViewArrayList_like_me.addAll(items);
                                adapter = new MessageListAdapter(MessagesFragment.this,topViewArrayList_like_me);
                                recycleTopView.setAdapter(adapter);
                            }
                        }


                    }
                });
                break;
            case TAB_MYREQUEST:

                pageMeLike++;
                if(pageMeLike==1){
                    recycleTopView.setVisibility(View.INVISIBLE);
                }
                UserServices.getInstance().getUserListMeLike(MCApp.getUserInstance().getUserId(), pageMeLike, ClientType.AndroidApp, new IGetListUserSearch() {
                    @Override
                    public void onError(ArrayList errors) {

                    }

                    @Override
                    public void onSuccess(ArrayList<UserView> items) {
                        hideLoading();
                        recycleTopView.setVisibility(View.VISIBLE);
                        MLog.d(TAG,"loadDataFromApi() = "+ new Gson().toJson(items));
                        if(items==null){
                            isLoading = true;
                            pageMeLike--;
                        }else{
                            if(items.size()>0){
                                MLog.d(TAG,"List = "+new Gson().toJson(items));
                                topViewArrayList_me_like.addAll(items);
                                adapter = new MessageListAdapter(MessagesFragment.this,topViewArrayList_me_like);
                                recycleTopView.setAdapter(adapter);
                            }
                        }
                    }
                });
                break;
        }










    }

    DialogChat dialogChat;
    @Override
    public void OnItemClickRecycleView(UserView topView, int position) {
        MLog.e(TAG,"Item click RecycleView");
        if(tabSelected==1){
            dialogChat =  new DialogChat(mContext,this);
            dialogChat.show();
        }else {
            Intent profile = new Intent(mContext, MyPageActivity.class);
            profile.putExtra("ID",10);
            startActivity(profile);

        }

    }

    @Override
    public boolean OnItemClickLike(View btnLike,int position) {
        MLog.e(TAG,"Item click Like");
        UserView topView = topViewArrayList.get(position);

        new DialogLikeMe(mContext,this).show();
        MLog.e(TAG,"Item click Like 2");
        return isAccept;
    }

    boolean isAccept = false;
    @Override
    public void onAcceptCallback() {
        isAccept =true;
    }

    @Override
    public void onAcceptChatCallback() {
        dialogChat.hide();
        // goto chatactivity
        Intent i = new Intent(mContext, ChatActivity.class);
        startActivity(i);

    }

    @Override
    public void onCancelCallback() {
        isAccept =false;
    }

    private void onTabChange(int tabSelected){
        if(getTabSelected()==tabSelected) return;

        isLoading=false;
        switch (tabSelected){
            case 1:
                tab1.setBackgroundResource(R.drawable.dr_bg_left_conner_active);
                tab2.setBackgroundResource(R.drawable.dr_bg_mid_conner);
                tab3.setBackgroundResource(R.drawable.dr_bg_right_conner);
                break;
            case 2:
                tab1.setBackgroundResource(R.drawable.dr_bg_left_conner);
                tab2.setBackgroundResource(R.drawable.dr_bg_mid_conner_active);
                tab3.setBackgroundResource(R.drawable.dr_bg_right_conner);
                break;
            case 3:
                tab1.setBackgroundResource(R.drawable.dr_bg_left_conner);
                tab2.setBackgroundResource(R.drawable.dr_bg_mid_conner);
                tab3.setBackgroundResource(R.drawable.dr_bg_right_conner_active);
                break;
        }

        setTabSelected(tabSelected);
        loadDataFromApi();
    }


    @OnClick(R.id.tab1)
    public void onClickTab1(){
        onTabChange(TAB_CONTACT);
    }

    @OnClick(R.id.tab2)
    public void onClickTab2(){
        onTabChange(TAB_INVITETION);
    }

    @OnClick(R.id.tab3)
    public void onClickTab3(){
        onTabChange(TAB_MYREQUEST);
    }

    public void onLockClick(boolean isLock) {
        if(isLock){
            tab1.setEnabled(false);
            tab2.setEnabled(false);
        }else{
            tab1.setEnabled(true);
            tab2.setEnabled(true);
        }
    }

    @Override
    public void showPullRefresh(){
        super.showPullRefresh();
        isLoading=false;

        switch (getTabSelected()){
            case TAB_CONTACT:
                pageContact=0;
                topViewArrayList.clear();
                recycleTopView.setAdapter(null);
                if(!hasInternet()){
                    recycleTopView.setVisibility(View.INVISIBLE);
                    return;
                }
                loadDataFromApi();
                break;
            case TAB_INVITETION:
                pageLikeMe=0;
                topViewArrayList_like_me.clear();
                recycleTopView.setAdapter(null);
                if(!hasInternet()){
                    recycleTopView.setVisibility(View.INVISIBLE);
                    return;
                }
                loadDataFromApi();
                break;
            case TAB_MYREQUEST:
                pageMeLike=0;
                topViewArrayList_me_like.clear();
                recycleTopView.setAdapter(null);
                if(!hasInternet()){
                    recycleTopView.setVisibility(View.INVISIBLE);
                    return;
                }
                loadDataFromApi();
                break;
        }






    }
}
