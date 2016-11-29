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
            "http://www.artsfon.com/large/201608/86256.jpg",
            "https://lh4.googleusercontent.com/-XplyTa1Za-I/VMSgIyAYkHI/AAAAAAAADxM/oL-rD6VP4ts/w1184-h666/Android-Lollipop-wallpapers-Google-Now-Wallpaper-2.png",
            "http://i-cdn.phonearena.com/images/articles/145581-image/January.jpg",
            "http://freedesignfile.com/upload/2016/05/Modern-material-design-background-vector-19.jpg",
            "http://www.myfreephotoshop.com/wp-content/uploads/2014/06/9113.jpg"
    };
    private CircleImageView imgProfile;
    private ImageView changeCover;
    private LinearLayout layoutShowImage;

    @InjectView(R.id.rlSearching)
    RelativeLayout rlSearching;

    @InjectView(R.id.rlResult)
    RelativeLayout rlResult;



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
        Picasso.with(mContext).load("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT87f6LTPcdzho8Q5aNfn8wPiA0MKcr45om_g4_A1S-hx2xdr7S5g")
                .resize(240, 120).into(imgProfile);
        ImageView imgBackground = (ImageView) mView.findViewById(R.id.imgBackground);
        Picasso.with(mContext).load("http://protiumdesign.com/wp-content/uploads/2015/04/Material-Design-Background-1024.jpg")
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
