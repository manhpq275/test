package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.views.fragments.NearByFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class SettingNearByActivity extends AppCompatActivity {
    public static final int BACK_FROM_NEARBY_SETTING = 20000;
    @InjectView(R.id.seekBar)
    SeekBar seekBar;


    @InjectView(R.id.txtRange)
    TextView tvRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_setting_nearby);
        ButterKnife.inject(this);
        tvRange.setText(getString(R.string.txt_your_range)+String.valueOf(MCApp.getUserInstance().getDistance())+"km");
        seekBar.setProgress(MCApp.getUserInstance().getDistance());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvRange.setText(getString(R.string.txt_your_range)+String.valueOf(progress)+"km");
                MCApp.getUserInstance().setDistance(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);


    }


    @OnClick(R.id.btnGoToProfile)
    public void onGoToProfile(){
        setResult(NearByFragment.RESULT_CODE_PROFILE);
        onBackPressed();

    }

    @OnClick(R.id.layoutBackActivity)
    public void onGoBackActivity(){
        setResult(SettingNearByActivity.BACK_FROM_NEARBY_SETTING);
        onBackPressed();
    }
}
