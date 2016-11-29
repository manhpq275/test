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

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.utils.Config;
import asia.ienter.matching.views.fragments.MessagesFragment;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private static final String TAG = "TopAdapter";

    ArrayList<UserView> topViewArrayList = new ArrayList<>();
    Context mContext;
    MessagesFragment mTopFragment;
    public MessageListAdapter(MessagesFragment mTopFragment, ArrayList<UserView> topViewArrayList) {
        this.mTopFragment = mTopFragment;
        this.mContext = mTopFragment.getContext();
        this.topViewArrayList.addAll(topViewArrayList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(mTopFragment.getTabSelected() == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contact_item_list, parent, false);

        }else{
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_top_item_list, parent, false);

        }

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(topViewArrayList.get(position).getName());
        if(mTopFragment.getTabSelected() == 1){
            Picasso.with(mContext).load(Config.BASE_URL+topViewArrayList.get(position).getImageUser()).into(holder.imAvatar);



        }else{
            Picasso.with(mContext).load(Config.BASE_URL+topViewArrayList.get(position).getImageUser()).into(holder.imAvatar);

        }
       holder.bind(position,mTopFragment);
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

            if(mTopFragment.getTabSelected() > 1) {
                ViewGroup.LayoutParams params = imAvatar.getLayoutParams();
                params.width = MCApp.getScreenSize().x;
                params.height = MCApp.getScreenSize().x;
                imAvatar.setLayoutParams(params);
            }

        }
        public void bind(final int position,final MessagesFragment listener) {
            final UserView item = topViewArrayList.get(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                        listener.OnItemClickRecycleView(item);
                }
            });

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(mTopFragment.getTabSelected() == 2){
//                        if(item.isLike() == 0)                        setLike(btnLike,1);
//                        if(item.isLike() == 1)                        setLike(btnLike,0);
                        listener.OnItemClickLike(position);
                    }

                }
            });
            if(mTopFragment.getTabSelected() == 3){
//                if(item.isLike() == 1)                        setLike(btnLike,1);
//                if(item.isLike() == 2)                        setLike(btnLike,2);
            }else if(mTopFragment.getTabSelected() == 2){
//                if(item.isLike() == 0)                        setLike(btnLike,0);
//                if(item.isLike() == 1)                        setLike(btnLike,1);
            }
        }

    }

    public void onNotifyDataSetChanged(ArrayList<UserView> datas){
        topViewArrayList.clear();
        topViewArrayList.addAll(datas);
        notifyDataSetChanged();
    }

    public void setLike(ImageView btnLike,int isLike){
        if(isLike==0){
                btnLike.setImageResource(R.mipmap.btn_like_full);
        }else if (isLike==1){
                btnLike.setImageResource(R.mipmap.btn_liked_full);
        }else if (isLike==2){
                btnLike.setImageResource(R.mipmap.btn_wating);
        }


    }


}
