package asia.ienter.matching.views.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

import asia.ienter.matching.R;
import asia.ienter.matching.services.FacebookService;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.utils.Utils;
import asia.ienter.matching.views.adapters.LoginSliderAdapter;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/6/16.
 */
public class LoginActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LoginSliderAdapter sliderAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //Setting viewpager
        viewPager = (ViewPager) findViewById(R.id.pagerSlider);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        sliderAdapter = new LoginSliderAdapter(getSupportFragmentManager());
        addBottomDots(0);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setOffscreenPageLimit(6);
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
        //((TextView)findViewById(R.id.txtTextShow)).setText(Html.fromHtml("Bằng cách tiếp tục, bạn đồng ý với <b>Điều khoản dịch vụ</b> và <b>Chính sách về Quyền riêng tư</b>"));

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
    }

    @OnClick(R.id.btnLoginApp)
    public void onClickLogin(){
        if(Utils.hasInternet(LoginActivity.this)){
//                        btnLogin.performClick();
            handleFbLogin(false);
        }else{
            showDialogFailLogin();
        }
    }

    private void handleFbLogin(boolean isLogged) {
        FacebookService.getInstance().loginFacebook(this,isLogged, new FacebookService.IFbLoginCallback() {
            @Override
            public void onSuccess() {
                FacebookService.getInstance().getUserFacebookData(LoginActivity.this, new FacebookService.ILoginFbCallback() {
                    @Override
                    public void onSuccess() {
                        SharedPreference.getInstance(getApplicationContext()).putBoolean("LoginFb", true);
                        Intent iHome = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(iHome);
                        finish();
                    }

                    @Override
                    public void onFailed() {
                        //re-try
                        showDialogFailLogin();
                    }
                });
            }

            @Override
            public void oncancel() {
                showDialogFailLogin();
            }

            @Override
            public void onError() {
                showDialogFailLogin();
            }
        });
    }


    private void addBottomDots(int currentPage) {
        int colorsActive = ContextCompat.getColor(getApplicationContext(), R.color.text_color_menu_active);
        int colorsInactive = ContextCompat.getColor(getApplicationContext(), R.color.dot_light_screen);
        dots = new TextView[6];
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

    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }
    
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
        FacebookService.getInstance().getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }
}
