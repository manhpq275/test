package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.ICommonViewCallback;
import asia.ienter.matching.interfaces.IDialogLikeCallback;
import asia.ienter.matching.models.CommonView;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.services.UserServices;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.views.adapters.MatchingProfileAdapter;
import asia.ienter.matching.views.dialogs.DialogLike;
import asia.ienter.matching.views.fragments.ReportUserFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/19/16.
 */
public class MyPageActivity extends AppCompatActivity {
    private ReplaceFragment fragmentHandle;
    private MatchingProfileAdapter adapterViewPager;
    private static final String TAG = "MyPageActivity";
    private ArrayList<UserView> listUserView = new ArrayList<>();
    @InjectView(R.id.imgMoreOption)     ImageView btnSettingOption;
    @InjectView(R.id.mainViewPager)     ViewPager viewPagerProfile;
    @InjectView(R.id.txtTitleScreen)    TextView titleScreen;
    @InjectView(R.id.layoutLike)
    LinearLayout layoutLike;
    private UserView topView=null;

    private int currentItemSelected = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_mypage);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String arrayUser = bundle.getString("UserArray");
            listUserView = new Gson().fromJson(arrayUser, new TypeToken<List<UserView>>(){}.getType());

            currentItemSelected = bundle.getInt("UserPosition");
            topView = listUserView.get(currentItemSelected);
           layoutLike.setVisibility(View.VISIBLE);
            if(bundle.getBoolean("SendLiked",false)){
                layoutLike.setVisibility(View.INVISIBLE);
            }
            fragmentHandle = new ReplaceFragment();
            adapterViewPager = new MatchingProfileAdapter(getSupportFragmentManager(), listUserView);
            viewPagerProfile.setAdapter(adapterViewPager);
            viewPagerProfile.setCurrentItem(currentItemSelected);
            titleScreen.setText(listUserView.get(currentItemSelected).getUserName());
            viewPagerProfile.addOnPageChangeListener(new ViewPagerChange());
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

    @OnClick(R.id.layoutBackActivity)
    public void onGoBackActivity(){
        onBackPressed();
    }

    @OnClick(R.id.layoutLike)
    public void onClickLike(){
        MLog.e(TAG, "Item click Like");
        new DialogLike(MyPageActivity.this, new IDialogLikeCallback() {

            @Override
            public void onSendLike() {
                if(topView.getMyLike()==1){
                    topView.setMyLike(0);
                }else{
                    topView.setMyLike(1);
                }
                UserServices.getInstance().sendLike(topView, ClientType.AndroidApp, new ICommonViewCallback() {
                    @Override
                    public void onError(ArrayList errors) {
                        if(topView.getMyLike()==1){
                            topView.setMyLike(0);
                        }else{
                            topView.setMyLike(1);
                        }
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

                UserServices.getInstance().sendLike(topView, ClientType.AndroidApp, new ICommonViewCallback() {
                    @Override
                    public void onError(ArrayList errors) {
                        if(topView.getMyLikeSpecial()==1){
                            topView.setMyLikeSpecial(0);
                        }else{
                            topView.setMyLikeSpecial(1);
                        }

                    }

                    @Override
                    public void onSuccess(CommonView item) {

                    }
                });

            }
        }).show();
        MLog.e(TAG, "Item click Like 2");
    }

    @OnClick(R.id.layoutMoreOption)
    public void onClickOption(){
        showPopupSelectImage(btnSettingOption);
    }

    private void showPopupSelectImage(View btnClick){
        PopupMenu popup = new PopupMenu(MyPageActivity.this, btnClick);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_my_page_action, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                handlePopupClick(item);
                return true;
            }
        });

        popup.show();
    }

    private void handlePopupClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.block_user:
                handleBlockUser();
                break;
            case R.id.report_user:
                handleReportUser();
                break;
            default:
                break;
        }
    }

    private void handleReportUser() {
        fragmentHandle.replaceWithAnimation(getSupportFragmentManager(), ReportUserFragment.newInstance(listUserView.get(viewPagerProfile.getCurrentItem())), R.id.layoutContent,
                R.anim.enter_from_right,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_to_right);
    }

    private void handleBlockUser() {
        new MaterialDialog.Builder(this)
                .title("Chặn người dùng này")
                .positiveText("Đồng ý")
                .negativeText("Huỷ")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Toast.makeText(MyPageActivity.this,"Đã chặn người dùng!",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }).show();
    }

    private class ViewPagerChange implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            topView = listUserView.get(position);
            currentItemSelected=position;
            titleScreen.setText(listUserView.get(position).getUserName());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
