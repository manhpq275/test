package asia.ienter.matching.views.adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.views.activities.MyPageActivity;
import asia.ienter.matching.views.activities.SettingActivity;
import asia.ienter.matching.views.fragments.AboutDetailFragment;
import asia.ienter.matching.views.fragments.ProfileFragment;
import asia.ienter.matching.views.fragments.SelectImageFragment;

/**
 * Created by hoangtuan on 9/21/16.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {
    private ArrayList<String> itemShow = new ArrayList<>();
    private Context mContext;
    private Activity mActivity;

    public SettingAdapter(Activity activity){
        this.mActivity = activity;
        this.itemShow.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.setting_string_array)));
    }

    @Override
    public SettingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_about_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SettingAdapter.ViewHolder holder, int position) {
        String title = itemShow.get(position);
        if(!title.isEmpty()) {
            holder.txtAboutTitle.setText(title);
            holder.layoutNoContent.setVisibility(View.GONE);
            holder.txtAboutTitle.setVisibility(View.VISIBLE);
            holder.layoutContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else{
            holder.layoutNoContent.setVisibility(View.VISIBLE);
            holder.txtAboutTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemShow.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtAboutTitle;
        private LinearLayout layoutNoContent;
        private LinearLayout layoutContent;
        public ViewHolder(View itemView) {
            super(itemView);
            txtAboutTitle = (TextView) itemView.findViewById(R.id.txtAboutTitle);
            layoutNoContent = (LinearLayout) itemView.findViewById(R.id.layoutNoContent);
            layoutContent = (LinearLayout) itemView.findViewById(R.id.layoutContent);
        }
    }
}
