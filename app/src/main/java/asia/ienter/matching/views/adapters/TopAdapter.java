package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.views.fragments.TopFragment;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private static final String TAG = "TopAdapter";

    ArrayList<UserView> topViewArrayList = new ArrayList<>();
    Context mContext;
    TopFragment mTopFragment;
    public TopAdapter(TopFragment mTopFragment, ArrayList<UserView> topViewArrayList) {
        this.mTopFragment = mTopFragment;
        this.mContext = mTopFragment.getContext();
        this.topViewArrayList.addAll(topViewArrayList);
    }

    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(mTopFragment.isGrid){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_top_item, parent, false);
        }else{
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_top_item_list, parent, false);
        }


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TopAdapter.ViewHolder holder, int position) {
//        holder.tvName.setText(topViewArrayList.get(position).getUserName());
//        Picasso.with(mContext).load(Config.BASE_URL+topViewArrayList.get(position).getImageUser()).error(R.mipmap.img_like).into(holder.imAvatar);
//        holder.tvYearsOld.setText(mContext.getString(R.string.yearOld).toString() +": "+ Utils.birthDayToYearsOld(topViewArrayList.get(position).getBirthDay()));
//
//        if(!mTopFragment.isGrid){
//            holder.tvHeight.setText(mContext.getString(R.string.txt_height)+": "+topViewArrayList.get(position).getHeight() + "cm");
//            holder.tvJob.setText(mContext.getString(R.string.txt_job)+": "+topViewArrayList.get(position).getJob());
//        }

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
        private TextView tvName,tvYearsOld,tvHeight,tvJob;
        private ImageView imAvatar,btnLike;
        private LinearLayout lnInfo;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvYearsOld = (TextView) view.findViewById(R.id.tvYearsOld);
            imAvatar = (ImageView) view.findViewById(R.id.imAvatar);
            btnLike = (ImageView) view.findViewById(R.id.btnLike);
            lnInfo = (LinearLayout) view.findViewById(R.id.lnInfo);

            if(mTopFragment.isGrid){
                ViewGroup.LayoutParams params = imAvatar.getLayoutParams();
                params.width = MCApp.getScreenSize().x/2;
                params.height = MCApp.getScreenSize().x/2;
                imAvatar.setLayoutParams(params);
            }else
            {

                tvJob = (TextView) view.findViewById(R.id.tvJob);
                tvHeight = (TextView) view.findViewById(R.id.tvHeight);
                ViewGroup.LayoutParams params = imAvatar.getLayoutParams();
                params.width = MCApp.getScreenSize().x;
                params.height = MCApp.getScreenSize().x;
                imAvatar.setLayoutParams(params);
            }

        }
        public void bind(final int position,final TopFragment listener) {
            //final UserView item = topViewArrayList.get(position);
            final UserView item = new UserView();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.OnItemClickRecycleView(item);
                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    setLike(btnLike,false);
                    listener.OnItemClickLike(position);
                }
            });
        }

    }

    public void onNotifyDataSetChanged(ArrayList<UserView> datas){
        topViewArrayList.clear();
        topViewArrayList.addAll(datas);
        notifyDataSetChanged();
    }

    public void setLike(ImageView btnLike,boolean isLike){
        if(isLike){
            if(mTopFragment.isGrid){
                btnLike.setImageResource(R.mipmap.btn_like);
            }else{
                btnLike.setImageResource(R.mipmap.btn_like_full);
            }
        }else{
            if(mTopFragment.isGrid){
                btnLike.setImageResource(R.mipmap.btn_liked);
            }else{
                btnLike.setImageResource(R.mipmap.btn_liked_full);
            }
        }

    }


}
