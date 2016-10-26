package asia.ienter.matching.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IChatSettingCallback;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.views.dialogs.DialogChatSetting;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/15/16.
 */
public class ChatActivity extends AppCompatActivity implements IChatSettingCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btnBack)
    public void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.tvUseTmp)
    public void onClickUseTmp(){
        Intent i = new Intent(this,MsgTemplateActivity.class);
        startActivityForResult(i,10000);
    }

    @OnClick(R.id.btnChatSetting)
    public void onClickChatSetting(){
       new DialogChatSetting(this,this).show();
    }

    @Override
    public void onViewProfileCallback() {
        Intent i = new Intent(this,MyPageActivity.class);
        startActivityForResult(i,10000);
    }

    @Override
    public void onShareFacebookCallback() {
        Toast.makeText(this,"Share Facebook",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onHideCallback() {
        Toast.makeText(this,"Hide callback",Toast.LENGTH_SHORT).show();

    }
}
