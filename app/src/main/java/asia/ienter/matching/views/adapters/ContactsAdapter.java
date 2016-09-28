package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.models.TopView;
import asia.ienter.matching.views.fragments.MessagesFragment;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private static final String TAG = "TopAdapter";

    ArrayList<TopView> topViewArrayList = new ArrayList<>();
    Context mContext;
    MessagesFragment mMessagesFragment;
    public ContactsAdapter(MessagesFragment mMessagesFragment, ArrayList<TopView> topViewArrayList) {
        this.mMessagesFragment = mMessagesFragment;
        this.mContext = mMessagesFragment.getContext();
        this.topViewArrayList.addAll(topViewArrayList);
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_top_item, parent, false);



        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(topViewArrayList.get(position).getAndroid_version_name());
        Picasso.with(mContext).load(topViewArrayList.get(position).getAndroid_image_url()).resize(240, 120).into(holder.imAvatar);
        holder.bind(position,mMessagesFragment);
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
        private TextView tvName;
        private ImageView imAvatar,btnLike;
        private LinearLayout lnInfo;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            imAvatar = (ImageView) view.findViewById(R.id.imAvatar);
            btnLike = (ImageView) view.findViewById(R.id.btnLike);
            lnInfo = (LinearLayout) view.findViewById(R.id.lnInfo);


        }
        public void bind(final int position,final MessagesFragment listener) {
            final TopView item = topViewArrayList.get(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.OnItemClickRecycleView(item);
                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    setLike(btnLike,true);
                    listener.OnItemClickLike(position);
                }
            });
        }

    }

    public void onNotifyDataSetChanged(ArrayList<TopView> datas){
        topViewArrayList.clear();
        topViewArrayList.addAll(datas);
        notifyDataSetChanged();
    }

    public void setLike(ImageView btnLike,boolean isLike){
        if(isLike){
            if(mMessagesFragment.isGrid){
                btnLike.setImageResource(R.mipmap.btn_like);
            }else{
                btnLike.setImageResource(R.mipmap.btn_like_full);
            }
        }else{
            if(mMessagesFragment.isGrid){
                btnLike.setImageResource(R.mipmap.btn_liked);
            }else{
                btnLike.setImageResource(R.mipmap.btn_liked_full);
            }
        }

    }


}
