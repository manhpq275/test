package asia.ienter.matching.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.SharedPreference;

/**
 * Created by hoangtuan on 9/15/16.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!SharedPreference.getInstance().getBoolean("Login", false)) {
                    Intent iMainActivity = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(iMainActivity);
                    finish();
                }else{
                    Intent iHomeActivity = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(iHomeActivity);
                    finish();
                }
            }
        }, 2000);
    }
}
