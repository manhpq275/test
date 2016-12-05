package asia.ienter.matching.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.utils.custom.RecyclerTouchListener;
import asia.ienter.matching.views.adapters.MessageTemplateAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 11/30/16.
 */
public class TemplateMessageActivity extends AppCompatActivity {
    private MessageTemplateAdapter adapterTemplate;
    @InjectView(R.id.listTemplate) RecyclerView listTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_template_message);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        String stringTemplate = SharedPreference.getInstance().getString("TempalteStore","");
        adapterTemplate = new MessageTemplateAdapter(getApplicationContext(), stringTemplate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listTemplate.setLayoutManager(mLayoutManager);
        listTemplate.setItemAnimator(new DefaultItemAnimator());
        listTemplate.setAdapter(adapterTemplate);

        listTemplate.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), listTemplate, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", adapterTemplate.getItemString(position));
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @OnClick(R.id.btnBackFragment)
    public void onClickBackActivity() {
        onBackPressed();

    }

    @OnClick(R.id.btnAddNewTemplate)
    public void onClickAddNewTempalte() {
        new MaterialDialog.Builder(TemplateMessageActivity.this)
                .title("Nhập thêm mẫu tin nhắn mới?")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                .input("Nội dung...", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        if(input.length()>0) {
                            adapterTemplate.addNewTemplate(input.toString());
                        }
                    }
                })
                .positiveText("Lưu")
                .negativeText("Huỷ")
                .show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
        //SharedPreference.getInstance().putString("TempalteStore", adapterTemplate.getStringTemplate());
    }
}
