package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IMessagesCallback;
import asia.ienter.matching.interfaces.IMsgTemplateCallback;
import asia.ienter.matching.interfaces.ITopViewCallback;
import asia.ienter.matching.models.TopView;
import asia.ienter.matching.views.adapters.MsgTemplateAdapter;
import asia.ienter.matching.views.adapters.SettingAdapter;
import asia.ienter.matching.views.dialogs.DialogTmpMessage;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class MsgTemplateActivity extends AppCompatActivity implements IMsgTemplateCallback {
    private RecyclerView recyclerView;
    private MsgTemplateAdapter aboutAdapter;

    @InjectView(R.id.rlContent)
    RelativeLayout rlContent;

    @InjectView(R.id.rlEditTmp)
    RelativeLayout rlEditTmp;

    @InjectView(R.id.btnCancel)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_msg_template);
        ButterKnife.inject(this);

        aboutAdapter = new MsgTemplateAdapter(MsgTemplateActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.lstMessage);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aboutAdapter);
        Button backButton = (Button) findViewById(R.id.btnBack);
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

    @OnClick(R.id.tvNewTmp)
    public void onClickNewTmp(){
        rlContent.setVisibility(View.GONE);
        rlEditTmp.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.btnCancel)
    public void onClickCancel(){
        rlContent.setVisibility(View.VISIBLE);
        rlEditTmp.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        Toast.makeText(this,"Cancel ",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnSaveNewTmp)
    public void onClickSaveNewTmp(){
        rlContent.setVisibility(View.VISIBLE);
        rlEditTmp.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        Toast.makeText(this,"Save new message template",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClickItem(int position) {
        new DialogTmpMessage(this,this).show();
    }

    @Override
    public void onAcceptCallback() {
        Toast.makeText(MsgTemplateActivity.this,"Accept use tmp",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onEditCallback() {
        onClickNewTmp();
    }

    @Override
    public void onCancelCallback() {
        Toast.makeText(MsgTemplateActivity.this,"Cancel use tmp",Toast.LENGTH_SHORT).show();
    }
}
