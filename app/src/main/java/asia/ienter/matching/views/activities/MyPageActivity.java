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

    private final String list_image_user[] = {
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13227214_1043697852390040_8717076642059613757_n.jpg?oh=b81ac88e1bc479f105b398375a24c8ee&oe=58733E4D",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/580372_955530281206798_5678687062761502191_n.jpg?oh=4a77eb0e0a378d6889da8288496dbce8&oe=587D1D89",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/315041_339167659509733_1166522795_n.jpg?oh=9e97113e64da0d8a1d6e8aa10ede12b2&oe=587B191D",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/390468_346509578775541_800237657_n.jpg?oh=6f88958d3953e08d7f1aefc8cf4fcb72&oe=58798195",
            "https://scontent-hkg3-1.xx.fbcdn.net/v/l/t1.0-9/1981923_630694610357035_1810204288871521189_n.jpg?oh=b84c1de152a4ce69a8f8f14c9d6d90db&oe=5837DD47"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_mypage);
        fragmentHandle = new ReplaceFragment();
        imgProfile = (CircleImageView) findViewById(R.id.imgProfileUser);
        Picasso.with(getApplicationContext()).load("https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13226657_1043244802435345_3258483637215210696_n.jpg?oh=3fb1a3e064f9149b7c19b384c512ca84&oe=587A218D")
                .resize(240, 120).into(imgProfile);
        ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
        Picasso.with(getApplicationContext()).load("https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13718686_1085301731562985_7217436262619398099_n.jpg?oh=733eda78106fc1ad9cbdc868fda213df&oe=5865A5A1")
                .resize(400, 300).into(imgBackground);

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
