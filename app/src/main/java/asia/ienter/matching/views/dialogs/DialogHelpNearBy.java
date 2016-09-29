package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import asia.ienter.matching.R;

/**
 * Created by phamquangmanh on 9/26/16.
 */
public class DialogHelpNearBy {

    private final Context context;
    private Dialog dialog;
    private ImageView closeButton;


    public DialogHelpNearBy(Context context) {
        this.context = context;
        dialog = new Dialog(this.context,R.style.DialogSlideAnim);
        dialog.setContentView(R.layout.dialog_help_near_by);
        closeButton = (ImageView) dialog.findViewById(R.id.btn_close_dialog);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
