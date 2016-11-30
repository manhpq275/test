package asia.ienter.matching.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.services.FacebookService;
import asia.ienter.matching.utils.SharedPreference;

/**
 * Created by hoangtuan on 9/15/16.
 */
public class SplashActivity extends AppCompatActivity {
    private final int TYPE_HOME = 1;
    private final int TYPE_LOGIN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_splash);
        SharedPreference.getInstance().putBoolean("GiftCode", true);
        MCApp.setAdvanceSearchView(new AdvanceSearchView());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!SharedPreference.getInstance().getBoolean("LoginFb", false)) {
                    handleChangeScreen(TYPE_LOGIN);
                }else{
                    FacebookService.getInstance().getUserFacebookData(SplashActivity.this, new FacebookService.ILoginFbCallback() {
                        @Override
                        public void onSuccess() {
                            handleChangeScreen(TYPE_HOME);
                        }

                        @Override
                        public void onFailed() {
                            //re-try
                            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                            handleChangeScreen(TYPE_LOGIN);
                        }
                    });
                }
            }
        }, 2000);
    }


    private void handleChangeScreen(int type){
        switch (type){
            case TYPE_HOME:
                Intent iHomeActivity = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(iHomeActivity);
                finish();
                break;
            case TYPE_LOGIN:
                Intent iLoginActivity = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(iLoginActivity);
                finish();
                break;
            default:
                break;
        }
    }

}
