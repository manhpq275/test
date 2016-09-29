package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IMessagesCallback;
import asia.ienter.matching.interfaces.IMsgTemplateCallback;

/**
 * Created by phamquangmanh on 9/26/16.
 */
public class DialogTmpMessage {

    private final Context context;
    private Dialog dialog;
    private ImageView closeButton;
    private Button  btnAccept,btnCancel;


    public DialogTmpMessage(Context context, final IMsgTemplateCallback callback) {
        this.context = context;
        dialog = new Dialog(this.context,R.style.DialogSlideAnim2);
        dialog.setContentView(R.layout.dialog_tmp_message);
        btnAccept = (Button)dialog.findViewById(R.id.btnAccept);
        btnCancel = (Button)dialog.findViewById(R.id.btnCancel);
        Button btnEdit = (Button)dialog.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onEditCallback();
                hide();
            }
        });
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onAcceptCallback();
                hide();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onCancelCallback();
                hide();
            }
        });
    }

    public void show() {
        if (dialog != null)
            dialog.show();
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
