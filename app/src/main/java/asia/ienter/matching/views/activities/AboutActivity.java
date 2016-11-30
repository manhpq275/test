package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import asia.ienter.matching.R;
import asia.ienter.matching.views.adapters.AboutAdapter;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/23/16.
 */
public class AboutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AboutAdapter aboutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_about);
        ButterKnife.inject(this);
        aboutAdapter = new AboutAdapter(AboutActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.listAbout);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aboutAdapter);
    }

    @OnClick(R.id.layoutBackActivity)
    public void onClickBackActivity() {
        onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }
}
