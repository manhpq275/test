package asia.ienter.matching.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.custom.CircleImageView;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.MyPageActivity;
import asia.ienter.matching.views.activities.SettingNearByActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class NearByFragment extends BaseFragment {

    public static NearByFragment newInstance() {

        Bundle args = new Bundle();

        NearByFragment fragment = new NearByFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private final String list_image_user[] = {
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13227214_1043697852390040_8717076642059613757_n.jpg?oh=b81ac88e1bc479f105b398375a24c8ee&oe=58733E4D",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/580372_955530281206798_5678687062761502191_n.jpg?oh=4a77eb0e0a378d6889da8288496dbce8&oe=587D1D89",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/315041_339167659509733_1166522795_n.jpg?oh=9e97113e64da0d8a1d6e8aa10ede12b2&oe=587B191D",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/390468_346509578775541_800237657_n.jpg?oh=6f88958d3953e08d7f1aefc8cf4fcb72&oe=58798195",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/l/t1.0-9/1981923_630694610357035_1810204288871521189_n.jpg?oh=b84c1de152a4ce69a8f8f14c9d6d90db&oe=5837DD47"
    };
    private CircleImageView imgProfile;
    private ImageView changeCover;
    private LinearLayout layoutShowImage;

    @InjectView(R.id.rlSearching)
    RelativeLayout rlSearching;

    @InjectView(R.id.rlResult)
    RelativeLayout rlResult;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_nearby, container, false);
        mContext = this.getContext();
        ButterKnife.inject(this,mView);
        test();
        return mView;
    }

    @Override
    protected void initView() {
        rlSearching.setVisibility(View.GONE);
        rlResult.setVisibility(View.VISIBLE);
        imgProfile = (CircleImageView) mView.findViewById(R.id.imgProfileUser);
        Picasso.with(mContext).load("https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13226657_1043244802435345_3258483637215210696_n.jpg?oh=3fb1a3e064f9149b7c19b384c512ca84&oe=587A218D")
                .resize(240, 120).into(imgProfile);
        ImageView imgBackground = (ImageView) mView.findViewById(R.id.imgBackground);
        Picasso.with(mContext).load("https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13718686_1085301731562985_7217436262619398099_n.jpg?oh=733eda78106fc1ad9cbdc868fda213df&oe=5865A5A1")
                .resize(400, 300).into(imgBackground);
        changeCover = (ImageView) mView.findViewById(R.id.imgChangeCover);
        buildSlideShowImage();


    }

    @Override
    protected void loadDataFromApi() {

    }
    private void buildSlideShowImage() {
        layoutShowImage = (LinearLayout) mView.findViewById(R.id.layoutShowImage);
        for(int i=0;i<list_image_user.length;i++){
            View slideView = LayoutInflater.from(mContext).inflate(R.layout.layout_image, null);
            ImageView iv = (ImageView) slideView.findViewById(R.id.imgView);
            Picasso.with(mContext).load(list_image_user[i]).resize(100, 100).into(iv);
            layoutShowImage.addView(slideView);
        }
        View addMore = LayoutInflater.from(mContext).inflate(R.layout.layout_add_more_image, null);
        layoutShowImage.addView(addMore);
    }

    @OnClick(R.id.btnCancel)
    public void onClickCancel(){
        Toast.makeText(mContext,"Click Cancel",Toast.LENGTH_SHORT).show();
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
        test();
    }

    @OnClick(R.id.btnAgree)
    public void onClickAgree(){
        Toast.makeText(mContext,"Click Agree",Toast.LENGTH_SHORT).show();
        rlSearching.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
        test();
    }

    private void test(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                initView();
            }
        }, 5000);
    }

    public void onSettingNearBy(){
        Intent profile = new Intent(mContext, SettingNearByActivity.class);
        startActivityForResult(profile,REQUEST_FROM_NEAR_BY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NearByFragment.REQUEST_FROM_NEAR_BY) {
            if(resultCode==RESULT_CODE_PROFILE){
                ((HomeActivity)getActivity()).changeTab(3);
            }
        }
    }
    public static final int REQUEST_FROM_NEAR_BY = 10000;
    public static final int RESULT_CODE_PROFILE = 10001;
}
