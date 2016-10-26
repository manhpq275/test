package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.utils.custom.CircleImageView;
import asia.ienter.matching.views.fragments.AboutFragment;
import asia.ienter.matching.views.fragments.SelectImageFragment;
import asia.ienter.matching.views.fragments.SettingFragment;

/**
 * Created by hoangtuan on 9/19/16.
 */
public class MyPageActivity extends AppCompatActivity {
    private ReplaceFragment fragmentHandle;
    private CircleImageView imgProfile;
    private LinearLayout layoutShowImage;

    private final int list_image_user[] = {
            R.mipmap.m_avatar1,
            R.mipmap.m_avatar2,
            R.mipmap.m_avatar3
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_mypage);
        fragmentHandle = new ReplaceFragment();
        imgProfile = (CircleImageView) findViewById(R.id.imgProfileUser);
        Picasso.with(getApplicationContext()).load(R.mipmap.m_avatar1)
                .resize(100, 100).into(imgProfile);
        ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
        Picasso.with(getApplicationContext()).load(R.mipmap.m_avatar3)
                .resize(300, 300).into(imgBackground);

        Button btnGoAbout = (Button) findViewById(R.id.btnAboutApp);
        btnGoAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buildSlideShowImage();
    }

    /**
     *
     */
    private void buildSlideShowImage() {
        layoutShowImage = (LinearLayout) findViewById(R.id.layoutShowImage);
        for(int i=0;i<list_image_user.length;i++){
            View slideView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_image, null);
            ImageView iv = (ImageView) slideView.findViewById(R.id.imgView);
            Picasso.with(getApplicationContext()).load(list_image_user[i]).resize(100, 100).into(iv);
            layoutShowImage.addView(slideView);
        }
        View addMore = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add_more_image, null);
        layoutShowImage.addView(addMore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }
}
