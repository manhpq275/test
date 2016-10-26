package asia.ienter.matching.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.views.activities.AboutActivity;
import asia.ienter.matching.views.fragments.AboutDetailFragment;

/**
 * Created by hoangtuan on 9/30/16.
 */
public class HobbyAdapter extends RecyclerView.Adapter<HobbyAdapter.ViewHolder> {
    private ArrayList<String> itemShow = new ArrayList<>();
    private Context mContext;
    private Activity mActivity;

    public HobbyAdapter(Activity activity){
        this.mActivity = activity;
        this.itemShow.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.action_hobby)));
    }

    @Override
    public HobbyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hobby_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HobbyAdapter.ViewHolder holder, int position) {
        String title = itemShow.get(position);
        holder.nameAction.setText(title);
        if(position == 0){
            holder.imageAction.setImageResource(R.drawable.ic_chat3);
        }else if(position == 1){
            holder.imageAction.setImageResource(R.drawable.ic_car_travel);
        }else if(position == 2){
            holder.imageAction.setImageResource(R.drawable.ic_eat_food);
        }else if(position == 3){
            holder.imageAction.setImageResource(R.drawable.ic_bottle_wine);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageAction;
        private TextView nameAction;
        private CheckBox checkBoxAction;
        public ViewHolder(View itemView) {
            super(itemView);
            imageAction = (ImageView) itemView.findViewById(R.id.imgAction);
            nameAction = (TextView) itemView.findViewById(R.id.txtNameAction);
            checkBoxAction = (CheckBox) itemView.findViewById(R.id.ckbAction);
        }
    }
}