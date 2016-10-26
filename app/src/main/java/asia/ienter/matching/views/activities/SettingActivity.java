package asia.ienter.matching.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.BuildConfig;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.views.adapters.SettingAdapter;
import asia.ienter.matching.views.fragments.NearByFragment;
import asia.ienter.matching.views.fragments.ProfileFragment;
import asia.ienter.matching.views.fragments.TopFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class SettingActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private SettingAdapter aboutAdapter;
    @InjectView(R.id.txtAppVersion) TextView txtAppVersion;
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

        txtAppVersion.setText(BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

    @OnClick(R.id.layoutLogout)
    public void onClickLeftBtnTopBar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm log out");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.dismiss();
                SharedPreference.getInstance().putBoolean("Login", false);
                Intent iLogin = new Intent(SettingActivity.this, LoginActivity.class);
                iLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iLogin);
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    @OnClick(R.id.layoutChangePeople)
    public void onClickChangePeople(){
        showDialogChangePeople(R.id.idSubTitle);
    }

    @OnClick(R.id.layoutChangePeople2)
    public void onClickChangePeople2(){
        showDialogChangePeople(R.id.idSubTitle2);

    }

    private void showDialogChangePeople(final int idLayout){
        new MaterialDialog.Builder(this)
                .title("Change number")
                .items(R.array.change_number_people)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        ((TextView) findViewById(idLayout)).setText(text + " >");
                        return true;
                    }
                })
                .positiveText("OK")
                .show();
    }
}
