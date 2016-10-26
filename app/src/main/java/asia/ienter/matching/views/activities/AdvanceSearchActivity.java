package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.views.adapters.AdvanceSearchAdapter;
import asia.ienter.matching.views.adapters.SettingAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class AdvanceSearchActivity extends AppCompatActivity {

    @InjectView(R.id.checkBox) CheckBox checkBox;
    @InjectView(R.id.checkBox2) CheckBox checkBox2;
    @InjectView(R.id.switchButton) Switch switchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_advance_search);

        ButterKnife.inject(this);
        Button backButton = (Button) findViewById(R.id.btnBack);
        Button btnMatching = (Button) findViewById(R.id.btnMatching);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                switchButton.setChecked(false);
                ((TextView) findViewById(R.id.idSubTitle)).setText("");
                ((TextView) findViewById(R.id.idSubTitle2)).setText("");
                Toast.makeText(MCApp.getAppContext(),"Reset Filter",Toast.LENGTH_LONG).show();
            }
        });
        btnMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
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
