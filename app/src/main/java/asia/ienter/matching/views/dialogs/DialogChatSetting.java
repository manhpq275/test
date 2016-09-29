package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IChatSettingCallback;
import asia.ienter.matching.interfaces.IMsgTemplateCallback;

/**
 * Created by phamquangmanh on 9/26/16.
 */
public class DialogChatSetting {

    private final Context context;
    private Dialog dialog;
    private ImageView closeButton;
    private Button  btnView,btnShare,btnHide,btnCancel;


    public DialogChatSetting(Context context, final IChatSettingCallback callback) {
        this.context = context;
        dialog = new Dialog(this.context,R.style.DialogSlideAnim2);
        dialog.setContentView(R.layout.dialog_chat_setting);
        btnView = (Button)dialog.findViewById(R.id.btnViewProfile);
        btnShare = (Button)dialog.findViewById(R.id.btnShareFacebook);
        btnHide = (Button)dialog.findViewById(R.id.btnHide);
        btnCancel = (Button)dialog.findViewById(R.id.btnCancel);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onViewProfileCallback();
                hide();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onShareFacebookCallback();
                hide();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onHideCallback();
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
