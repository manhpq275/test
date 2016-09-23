package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.models.TopView;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private static final String TAG = "TopAdapter";

    ArrayList<TopView> topViewArrayList = new ArrayList<>();
    Context mContext;

    public TopAdapter(Context mContext, ArrayList<TopView> topViewArrayList) {
        this.mContext = mContext;
        this.topViewArrayList.addAll(topViewArrayList);
    }

    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_top_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TopAdapter.ViewHolder holder, int position) {
        holder.tv_android.setText(topViewArrayList.get(position).getAndroid_version_name());
        Picasso.with(mContext).load(topViewArrayList.get(position).getAndroid_image_url()).resize(240, 120).into(holder.img_android);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (topViewArrayList == null) return 0;
        return topViewArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_android;
        private ImageView img_android;

        public ViewHolder(View view) {
            super(view);
            tv_android = (TextView) view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
        }
    }

    public void onNotifyDataSetChanged(ArrayList<TopView> datas){
        topViewArrayList.clear();
        topViewArrayList.addAll(datas);
        notifyDataSetChanged();
    }


}
