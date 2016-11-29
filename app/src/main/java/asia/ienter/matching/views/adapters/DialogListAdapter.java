package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.MLog;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public class DialogListAdapter extends BaseAdapter {
    Context context;
    int mSelectedItem = 0;
    String listItem[];
    ArrayList<Integer> listSelected;
    boolean multipleChoice = false;

    public DialogListAdapter(Context context, String listItem[],boolean multipleChoice) {
        this.context = context;
        this.listItem = listItem;
        this.multipleChoice = multipleChoice;
        if (this.multipleChoice) {
            listSelected = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return this.listItem.length;
    }

    @Override
    public String getItem(int position) {
        return listItem[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = View.inflate(context, R.layout.dialog_item_list, null);
        ;
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.item = (TextView) view.findViewById(R.id.list_item);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.item.setText(listItem[position]);

        MLog.d(DialogListAdapter.class, "Item select =" + mSelectedItem);
        if(multipleChoice){
            for(int i=0;i<listSelected.size();i++){
                if (position == listSelected.get(i)) {
                    viewHolder.item.setBackgroundColor(Color.LTGRAY);
                }
            }
        }else{
            if (position == mSelectedItem) {
                viewHolder.item.setBackgroundColor(Color.LTGRAY);
            }
        }

        return view;
    }

    public void setOnClickItem(int position) {
        if (multipleChoice) {
            boolean isRemove = false;
            for(int i=0;i<listSelected.size();i++){
                if(position==listSelected.get(i)){
                    listSelected.remove(i);
                    isRemove = true;
                    break;
                }
            }
            if(!isRemove) listSelected.add(position);
        } else {
            mSelectedItem = position;
        }
    }

    private class ViewHolder {
        public TextView item;
    }
}
