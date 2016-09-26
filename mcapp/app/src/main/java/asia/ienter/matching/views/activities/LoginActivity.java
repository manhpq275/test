package asia.ienter.matching.views.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.facebook.AccessToken;
//import com.facebook.AccessTokenTracker;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.utils.Utils;
import asia.ienter.matching.views.adapters.LoginSliderAdapter;

/**
 * Created by hoangtuan on 9/6/16.
 */
public class LoginActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LoginSliderAdapter sliderAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
//    private LoginButton btnLogin;
//    private CallbackManager callbackManager;
//    private AccessTokenTracker accessTokenTracker;
    private List<String> listPermission = Arrays.asList("email", "public_profile", "user_friends");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting before add view
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        //Setting viewpager
        viewPager = (ViewPager) findViewById(R.id.pagerSlider);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        sliderAdapter = new LoginSliderAdapter(getSupportFragmentManager());
        addBottomDots(0);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ((TextView)findViewById(R.id.txtShowTitleSlider)).setText(Html.fromHtml("Nếu bạn thích một ai đó, và tình cờ họ cũng thích bạn thì hãy..."));
        ((TextView)findViewById(R.id.txtTextShow)).setText(Html.fromHtml("Bằng cách tiếp tục, bạn đồng ý với <b>Điều khoản dịch vụ</b> và <b>Chính sách về Quyền riêng tư</b>"));

        //Setting facebook button
//        btnLogin  = (LoginButton) findViewById(R.id.btnLoginFb);
//        btnLogin.setReadPermissions(listPermission);
//        btnLogin.setText("");
//        //btnLogin.setPublishPermissions(listPermission);
//        callbackManager = CallbackManager.Factory.create();
//        //btnLogin.setFragment(this);
//        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Log.i("Result", loginResult.getAccessToken().getToken() + " qwerty " + isLoggedIn());
//                //getUserFacebookData(loginResult);
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//                showDialogFailLogin();
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//                showDialogFailLogin();
//            }
//        });
//
//        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(
//                    AccessToken oldAccessToken,
//                    AccessToken currentAccessToken) {
//                // Set the access token using
//                // currentAccessToken when it's loaded or set.
//            }
//        };

        //Set on click to login button
        Button btnLoginApp = (Button) findViewById(R.id.btnLoginApp);
        if (btnLoginApp != null) {
            btnLoginApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //btnLogin.performClick();
                    if(Utils.hasInternet(LoginActivity.this)){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        SharedPreference.getInstance().putBoolean("Login", true);
                        Intent iHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(iHomeActivity);
                        finish();
                    }else{
                        showDialogFailLogin();
                    }
                }
            });
        }
    }

    private void addBottomDots(int currentPage) {
        int colorsActive = ContextCompat.getColor(getApplicationContext(), R.color.dot_dark_screen);
        int colorsInactive = ContextCompat.getColor(getApplicationContext(), R.color.dot_light_screen);
        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }

//    public boolean isLoggedIn() {
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        return accessToken != null;
//    }
    
    private void showDialogFailLogin(){
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_cancel_login);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.btnDialogOk);
        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
