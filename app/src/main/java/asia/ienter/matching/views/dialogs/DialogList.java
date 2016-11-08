package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListCallBack;
import asia.ienter.matching.views.adapters.DialogListAdapter;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class DialogList {
    private  Context context;
    private Dialog dialog;
    String listItems [];
    ListView lv;
    TextView tvDone,tvTitle;
    ImageView btnBack;
    DialogListAdapter listAdapter;
    int itemSelected=0;

    public DialogList(Context context,int itemSeleted, final IDialogListCallBack callback) {
        this.context = context;
        this.itemSelected = itemSeleted;
        dialog = new Dialog(this.context, R.style.DialogSlideUp);
        dialog.setContentView(R.layout.dialog_listview);
        lv = (ListView) dialog.findViewById(R.id.listDialog);
        tvDone = (TextView) dialog.findViewById(R.id.tvDone);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        btnBack = (ImageView) dialog.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickBack();
                hide();
            }
        });
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickDone();
                hide();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adpterView, View view, int position,
                                    long id) {
                listAdapter.setOnClickItem(position);
                listAdapter.notifyDataSetChanged();
                callback.onClickItem(position);

            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                callback.onClickBack();
            }
        });

    }
    public void show(String title, String dataString[]){
        this.listItems = dataString;
        tvTitle.setText(title);
        listAdapter = new DialogListAdapter(context,listItems);
        listAdapter.setOnClickItem(this.itemSelected);
        lv.setAdapter(listAdapter);
        if (dialog != null)
            dialog.show();
    }

    public void hide(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }




}
