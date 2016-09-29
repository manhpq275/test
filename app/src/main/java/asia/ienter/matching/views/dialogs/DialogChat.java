package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IMessagesCallback;

/**
 * Created by phamquangmanh on 9/26/16.
 */
public class DialogChat {

    private final Context context;
    private Dialog dialog;
    private ImageView closeButton;
    private Button  btnAccept;


    public DialogChat(Context context, final IMessagesCallback callback) {
        this.context = context;
        dialog = new Dialog(this.context,R.style.DialogSlideAnim);
        dialog.setContentView(R.layout.dialog_chat);
        closeButton = (ImageView) dialog.findViewById(R.id.btn_close_dialog);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        btnAccept = (Button)dialog.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onAcceptChatCallback();
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
