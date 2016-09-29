package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogLikeCallback;
import asia.ienter.matching.interfaces.IMessagesCallback;

/**
 * Created by phamquangmanh on 9/26/16.
 */
public class DialogLike {

    private final Context context;
    private Dialog dialog;
    private ImageView closeButton;
    private Button  btnSendRequest,btnSendLike;


    public DialogLike(Context context, final IDialogLikeCallback callback) {
        this.context = context;
        dialog = new Dialog(this.context,R.style.DialogSlideAnim);
        dialog.setContentView(R.layout.dialog_like);
        closeButton = (ImageView) dialog.findViewById(R.id.btn_close_dialog);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        btnSendRequest = (Button)dialog.findViewById(R.id.btnSendRequest);
        btnSendLike = (Button)dialog.findViewById(R.id.btnLiked);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onSendRequest();
                hide();
            }
        });
        btnSendLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onSendLike();
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
