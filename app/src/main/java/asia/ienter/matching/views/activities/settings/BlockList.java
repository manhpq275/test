package asia.ienter.matching.views.activities.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import asia.ienter.matching.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 11/15/16.
 */
public class BlockList extends AppCompatActivity {

    @InjectView(R.id.lnQK)
    LinearLayout lnQK;

    @InjectView(R.id.lnYX)
    LinearLayout lnYX;

    @InjectView(R.id.lnTB)
    LinearLayout lnTB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_blocklist);

        ButterKnife.inject(this);
        ImageView backButton = (ImageView) findViewById(R.id.btnBack);
    }

    @OnClick(R.id.xoaQK)
    public void xoaQK(){
        lnQK.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.xoaYX)
    public void xoaYX(){
        lnYX.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.xoaTB)
    public void xoaTB(){
        lnTB.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btnBack)
    public void onClickBack(){
        onBackPressed();
    }

}
