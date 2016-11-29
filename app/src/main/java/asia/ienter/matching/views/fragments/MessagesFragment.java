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

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IMessagesCallback;
import asia.ienter.matching.interfaces.ITopViewCallback;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.activities.ChatActivity;
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
    ArrayList<UserView> topViewArrayList;
    MessageListAdapter adapter;
    public boolean isGrid = true;
    private ArrayList<UserView> mUsers = new ArrayList<>();

    public int getTabSelected() {
        return tabSelected;
    }

    public void setTabSelected(int tabSelected) {
        this.tabSelected = tabSelected;
    }

    private int tabSelected = 2;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_messages, container, false);
        mContext = this.getContext();
        ButterKnife.inject(this,mView);
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

                            //Load data
//                            int index = topViewArrayList.size();
//                            int end = index + 5;
//                            for (int i = index; i < end; i++) {
//                                TopView user = new TopView(""+i,"Name " + i,2);
//                                user.setAndroid_version_name("Name " + i);
//                                user.setAndroid_image_url(android_image_urls[4]);
//                                topViewArrayList.add(user);
//                            }
//                            adapter.onNotifyDataSetChanged(topViewArrayList);
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
        MLog.d(TAG,"loadDataFromApi()");
        showLoading();
        topViewArrayList.clear();
//        for(int i=0;i<android_version_names.length;i++){
//            TopView androidVersion = new TopView(""+i,"Name "+i,0);
//            androidVersion.setAndroid_version_name(android_version_names[i]);
//            androidVersion.setAndroid_image_url(android_image_urls[i]);
//            topViewArrayList.add(androidVersion);
//        }
        //  topViewArrayList.add(null);
        adapter = new MessageListAdapter(this,topViewArrayList);
        recycleTopView.setAdapter(adapter);
        hideLoading();
    }

    DialogChat dialogChat;
    @Override
    public void OnItemClickRecycleView(UserView topView) {
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
    public boolean OnItemClickLike(int position) {
        MLog.e(TAG,"Item click Like");
        UserView topView = topViewArrayList.get(position);
//        int isLiked = topView.isLike();
//        if(isLiked==0) isLiked = 1;
//        if(isLiked==1) isLiked = 0;
//        topViewArrayList.get(position).setLike(isLiked);

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
        adapter = new MessageListAdapter(this,topViewArrayList);
        recycleTopView.setAdapter(adapter);
    }


    @OnClick(R.id.tab1)
    public void onClickTab1(){
        onTabChange(1);
    }

    @OnClick(R.id.tab2)
    public void onClickTab2(){
        onTabChange(2);
    }

    @OnClick(R.id.tab3)
    public void onClickTab3(){
        onTabChange(3);
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
}
