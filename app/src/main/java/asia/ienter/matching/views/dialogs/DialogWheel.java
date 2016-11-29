package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListCallBack;
import asia.ienter.matching.interfaces.IDialogListMultipleCallBack;
import asia.ienter.matching.models.enums.MaxLike;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.adapters.DialogListAdapter;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class DialogWheel {
    private  Context context;
    private Dialog dialog;
    WheelPicker wpLeft,wpRight;
    List<Integer> listItem;
    TextView tvDone,tvTitle,tvCancel;
    int itemSelectedLeft=0,itemSelectedRight=0;

    public DialogWheel(Context context,int itemSelectedLeft,int itemSelectedRight, final IDialogListMultipleCallBack callback) {
        this.context = context;
        dialog = new Dialog(this.context, R.style.DialogSlideAnim);
        dialog.setContentView(R.layout.dialog_wheelview);
        tvTitle = (TextView)dialog.findViewById(R.id.tvTitle);
        tvDone = (TextView)dialog.findViewById(R.id.tvDone);
        tvCancel = (TextView)dialog.findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        wpLeft = (WheelPicker)dialog.findViewById(R.id.wpLeft);
        wpRight = (WheelPicker)dialog.findViewById(R.id.wpRight);
        this.itemSelectedLeft = itemSelectedLeft;
        this.itemSelectedRight = itemSelectedRight;
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                callback.onClickBack();
            }
        });
        wpLeft.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                wpLeft.setSelectedItemPosition(position);
                MLog.d(DialogWheel.class,"position ="+position+" ---- wpRight.getSelectedItemPosition = "+wpRight.getSelectedItemPosition());

                if(position>wpRight.getSelectedItemPosition()){
                    wpRight.setSelectedItemPosition(position);
                }
            }
        });
        wpRight.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                wpRight.setSelectedItemPosition(position);
                MLog.d(DialogWheel.class,"position ="+position+" ---- wpLeft.getSelectedItemPosition = "+wpLeft.getSelectedItemPosition());
                if(position<wpLeft.getSelectedItemPosition()){
                    wpLeft.setSelectedItemPosition(position);
                }
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> list= new ArrayList<>();
                list.add(0,listItem.get(wpLeft.getSelectedItemPosition()));
                list.add(1,listItem.get(wpRight.getSelectedItemPosition()));
                callback.onClickItem(list);
                callback.onClickDone();
                hide();
            }
        });


    }
    public void show(String title, List<Integer> dataList){
        listItem = dataList;
        wpLeft.setData(dataList);
        wpRight.setData(dataList);


        wpLeft.setSelectedItemPosition(dataList.indexOf(itemSelectedLeft));
        wpRight.setSelectedItemPosition(dataList.indexOf(itemSelectedRight));
        tvTitle.setText(title);
        if (dialog != null)
            dialog.show();
    }

    public void hide(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }




}
