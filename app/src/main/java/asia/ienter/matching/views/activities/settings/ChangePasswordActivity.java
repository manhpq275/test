package asia.ienter.matching.views.activities.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.rengwuxian.materialedittext.MaterialEditText;

import asia.ienter.matching.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by phamquangmanh on 11/15/16.
 */
public class ChangePasswordActivity extends AppCompatActivity {

    @InjectView(R.id.edtNewPass)
    MaterialEditText edtNewPass;
    @InjectView(R.id.edtNewPass2)
    MaterialEditText edtNewPass2;

    boolean error = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_change_password);
        ButterKnife.inject(this);
        initView();
    }

    @OnClick(R.id.layoutBackActivity)
    public void onClickBackButton() {
        onBackPressed();
    }

    private void initView() {
        edtNewPass.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPassword();
            }
        });
        edtNewPass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPassword();
            }
        });
    }

    private void checkPassword() {
        if (edtNewPass.getText().length() == 0) {
            edtNewPass.setError(getString(R.string.txt_error_Pass_null));
            error = true;
            return;
        } else {
            edtNewPass.setError("");
            error = false;
        }

        if (edtNewPass2.getText().length() == 0) {
            edtNewPass2.setError(getString(R.string.txt_error_Pass_null));
            error = true;
            return;
        } else {
            if (!edtNewPass2.getText().toString().equals(edtNewPass.getText().toString())) {
                edtNewPass2.setError(getString(R.string.txt_error_RePass));
                error = true;
                return;
            } else {
                edtNewPass2.setError(null);
                error = false;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }


    @OnClick(R.id.btnChangePass)
    public void onClickChangePass() {
        checkPassword();
        if(error) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Thông báo");
        builder.setMessage("Thay đổi mật khẩu thành công");

        builder.setPositiveButton("Xong", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
