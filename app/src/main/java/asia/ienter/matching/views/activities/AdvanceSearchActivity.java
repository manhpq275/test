package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.views.adapters.AdvanceSearchAdapter;
import asia.ienter.matching.views.adapters.SettingAdapter;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class AdvanceSearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdvanceSearchAdapter aboutAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_advance_search);
        aboutAdapter = new AdvanceSearchAdapter(AdvanceSearchActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.listSetting);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aboutAdapter);
        Button backButton = (Button) findViewById(R.id.btnBack);
        Button btnMatching = (Button) findViewById(R.id.btnMatching);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
