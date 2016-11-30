package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListMultipleCallBack;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.custom.RippleView;
import asia.ienter.matching.views.adapters.DialogListAdapter;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class DialogListMultiple {
    private Context context;
    private Dialog dialog;
    String listItems[];
    ListView lv;
    TextView tvDone, tvTitle;
    RippleView btnBack;
    DialogListAdapter listAdapter;
    ArrayList<Integer> listItemSelected;

    public DialogListMultiple(Context context, ArrayList<Integer> itemSelecteds, final IDialogListMultipleCallBack callback) {
        this.context = context;
        this.listItemSelected = itemSelecteds;
        dialog = new Dialog(this.context, R.style.DialogSlideUp);
        dialog.setContentView(R.layout.dialog_listview);
        lv = (ListView) dialog.findViewById(R.id.listDialog);
        tvDone = (TextView) dialog.findViewById(R.id.tvDone);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        btnBack = (RippleView) dialog.findViewById(R.id.btnBackFragment);
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
                MLog.d(DialogListMultiple.class,"tvDone listItemSelected = "+listItemSelected.size());

                callback.onClickItem(listItemSelected);
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
                boolean isRemove = false;
                for(int i=0;i<listItemSelected.size();i++){
                    if(position==listItemSelected.get(i)){
                        listItemSelected.remove(i);
                        isRemove = true;
                        break;
                    }
                }
                if(!isRemove) listItemSelected.add(position);
                for(int i=0;i<listItemSelected.size();i++){
                    MLog.d(DialogListMultiple.class,"onClick listItemSelected = "+listItemSelected.get(i));
                }

            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                callback.onClickBack();
            }
        });

    }

    public void show(String title, String dataString[]) {
        this.listItems = dataString;
        tvTitle.setText(title);
        listAdapter = new DialogListAdapter(context, listItems, true);

        for (int i = 0; i < listItemSelected.size(); i++) {

            MLog.d(DialogListMultiple.class,"Show listItemSelected.size() = "+listItemSelected.get(i));
            listAdapter.setOnClickItem(listItemSelected.get(i));
        }
        lv.setAdapter(listAdapter);
        if (dialog != null)
            dialog.show();
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
