package asia.ienter.matching.views.activities.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import asia.ienter.matching.R;
import asia.ienter.matching.views.adapters.AboutAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by phamquangmanh on 11/15/16.
 */
public class ContentText extends AppCompatActivity {

    @InjectView(R.id.contentText)
    TextView tvContentText;

    @InjectView(R.id.captionText)
    TextView tvcaptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_active_years_old);
        ButterKnife.inject(this);
        Button backButton = (Button) findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getBunlde();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

    private void getBunlde(){
        Bundle extras = getIntent().getExtras();
        if(extras == null) return;
        tvcaptionText.setText(extras.getString("Title"));
        tvContentText.setText(extras.getString("Content"));
    }
}
