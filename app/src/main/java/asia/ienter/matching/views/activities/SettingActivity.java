package asia.ienter.matching.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListCallBack;
import asia.ienter.matching.models.SettingUserView;
import asia.ienter.matching.models.enums.MaxLike;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.views.activities.settings.BlockList;
import asia.ienter.matching.views.activities.settings.ContentText;
import asia.ienter.matching.views.activities.settings.ChangePasswordActivity;
import asia.ienter.matching.views.dialogs.DialogList;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class SettingActivity extends AppCompatActivity {

    SettingUserView settingUserView;

    @InjectView(R.id.tvYearsOldActive)
    TextView tvYearsOldActive;
    @InjectView(R.id.tvMaxLike)
    TextView tvMaxLike;
    @InjectView(R.id.tvMaxSpecialLike)
    TextView tvMaxSpecialLike;
    @InjectView(R.id.cbGetEmail)
    CheckBox cbGetEmail;
    @InjectView(R.id.cbAnonymous)
    CheckBox cbAnonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_setting);

        ButterKnife.inject(this);
        Button backButton = (Button) findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

    @OnClick(R.id.tvLogout)
    public void onClickLeftBtnTopBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Đăng xuất ");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");

        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                handleLogoutFacebook(dialog);

            }
        });

        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    private void handleLogoutFacebook(final DialogInterface dialogInterface) {
        dialogInterface.dismiss();
        final MaterialDialog progress = new MaterialDialog.Builder(this)
                .title("Logging out..")
                .content("Please wait")
                .progress(true, 0)
                .show();
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
                progress.dismiss();
                SharedPreference.getInstance().putBoolean("LoginFb", false);
                Intent iLogin = new Intent(SettingActivity.this, LoginActivity.class);
                iLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iLogin);
                finish();


            }
        }).executeAsync();
    }

    @OnClick(R.id.tvDeleteAccount)
    public void onClickDeleteAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Xoá tài khoản ");
        builder.setMessage("Bạn có chắc chắn muốn xóa tài khoản?\nLưu ý: Khi bạn xóa tài khoản mọi dữ liệu \nsẽ bị xóa và không thể khôi phục.");

        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                handleLogoutFacebook(dialog);
            }
        });

        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    @OnClick(R.id.lnYearsOldActive)
    public void onClickYearsOldActive() {
        Intent iLogin = new Intent(SettingActivity.this, ContentText.class);
        startActivity(iLogin);
    }

    @OnClick(R.id.lnGetEmail)
    public void onClickGetEmail() {
        cbGetEmail.setChecked(!cbGetEmail.isChecked());
        settingUserView.setGetEmail(cbGetEmail.isChecked());
    }

    private void showDialogChangePeople(final int idLayout) {
        new MaterialDialog.Builder(this)
                .title("Change number")
                .items(R.array.change_number_people)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        ((TextView) findViewById(idLayout)).setText(text + " >");
                        return true;
                    }
                })
                .positiveText("OK")
                .show();
    }


    @OnClick(R.id.lnAnonymous)
    public void onClickAnonymous(){
        cbAnonymous.setChecked(!cbAnonymous.isChecked());
        settingUserView.setAnonymous(cbAnonymous.isChecked());
    }

    @OnClick(R.id.lnMaxLike)
    public void onClickMaxLike(){
        final MaxLike maxLikes[] = MaxLike.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[maxLikes.length];
        for (MaxLike maxLike : maxLikes) {
            listItems[i] = maxLike.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        int selectedItem =0;
        if (settingUserView != null) {
            selectedItem= settingUserView.getMaxLike();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                settingUserView.setMaxLike(settingUserView.getMaxLike());
            }

            @Override
            public void onClickItem(int position) {
                settingUserView.setMaxLike(maxLikes[position].getValue());
            }

            @Override
            public void onClickDone() {
                tvMaxLike.setText(MaxLike.fromInteger(settingUserView.getMaxLike()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_max_like), listItems);
    }

    @OnClick(R.id.lnMaxSpecialLike)
    public void onClickMaxSpecialLike(){
        final MaxLike maxLikes[] = MaxLike.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[maxLikes.length];
        for (MaxLike maxLike : maxLikes) {
            listItems[i] = maxLike.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        int selectedItem =0;
        if (settingUserView != null) {
            selectedItem= settingUserView.getMaxSpecialLike();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                settingUserView.setMaxSpecialLike(settingUserView.getMaxSpecialLike());
            }

            @Override
            public void onClickItem(int position) {
                settingUserView.setMaxSpecialLike(maxLikes[position].getValue());
            }

            @Override
            public void onClickDone() {
                tvMaxSpecialLike.setText(MaxLike.fromInteger(settingUserView.getMaxSpecialLike()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_max_special_like), listItems);
    }

    private void loadData(){
        settingUserView = new SettingUserView();
        if(settingUserView.isActiveYearsOld()){
            tvYearsOldActive.setText(getString(R.string.txt_active));
        }else{
            tvYearsOldActive.setText(getString(R.string.txt_none_active));
        }
        cbGetEmail.setChecked(settingUserView.isGetEmail());
        cbAnonymous.setChecked(settingUserView.isAnonymous());
        tvMaxSpecialLike.setText(MaxLike.fromInteger(settingUserView.getMaxSpecialLike()).toString());
        tvMaxLike.setText(MaxLike.fromInteger(settingUserView.getMaxLike()).toString());

    }

    @OnClick(R.id.tvResetSetting)
    public void onClickResetSetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Phục hồi cài đặt");
        builder.setMessage("Bạn có chắc chắn muốn phục hồi cài đặt mặc định ban đầu.");

        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                settingUserView = new SettingUserView();
                loadData();
            }
        });

        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @OnClick(R.id.tvPassWordChange)
    public void onClickPasswordChange(){
        Intent iLogin = new Intent(SettingActivity.this, ChangePasswordActivity.class);
        startActivity(iLogin);
    }

    @OnClick(R.id.tvLicense)
    public void onClickLicense(){
        Intent iLogin = new Intent(SettingActivity.this, ContentText.class);
        iLogin.putExtra("Title",getString(R.string.txtLicense));
        iLogin.putExtra("Content",getString(R.string.apache_license));
        startActivity(iLogin);
    }

    @OnClick(R.id.tvBlockList)
    public void onClickBlockList(){
        Intent iLogin = new Intent(SettingActivity.this, BlockList.class);
        startActivity(iLogin);
    }
}
