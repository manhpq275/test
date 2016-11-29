package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogLikeCallback;
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
    @InjectView(R.id.btnSetting) Button btnSettingOption;
    @InjectView(R.id.mainViewPager) ViewPager viewPagerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_mypage);
        ButterKnife.inject(this);
        fragmentHandle = new ReplaceFragment();
        Button btnGoAbout = (Button) findViewById(R.id.btnAboutApp);
        btnGoAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapterViewPager = new MatchingProfileAdapter(getSupportFragmentManager());
        viewPagerProfile.setAdapter(adapterViewPager);
//        buildSlideShowImage();
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

    @OnClick(R.id.layoutLike)
    public void onClickLike(){
        MLog.e(TAG, "Item click Like");
        new DialogLike(MyPageActivity.this, new IDialogLikeCallback() {

            @Override
            public void onSendLike() {
                Toast.makeText(MyPageActivity.this, "Send Like", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSendRequest() {
                Toast.makeText(MyPageActivity.this,"Send Request",Toast.LENGTH_SHORT).show();

            }
        }).show();
        MLog.e(TAG, "Item click Like 2");
    }

    @OnClick(R.id.btnSetting)
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
        fragmentHandle.replaceWithAnimation(getSupportFragmentManager(), ReportUserFragment.newInstance(), R.id.layoutContent,
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
}
