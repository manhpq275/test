package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sendbird.android.BaseMessage;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.R;
import asia.ienter.matching.models.ChattingMessage;
import asia.ienter.matching.models.UserView;

/**
 * Created by hoangtuan on 11/30/16.
 */
public class ChattingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int MYSELF = 0;
    private final int OPPONENT = 1;

    private ArrayList<ChattingMessage> listMessage = new ArrayList<>();
    private Context mContext;
    private UserView userView;

    public ChattingAdapter(Context context, List<BaseMessage> list, UserView userView){
        this.mContext = context;
        this.userView = userView;
        handleCreateDataTest(list);
    }

    private void handleCreateDataTest(List<BaseMessage> list) {
        for(int i=0;i<list.size();i++){
            listMessage.add(0, new ChattingMessage(list.get(i)));
        }
    }

    public void addMessage(ChattingMessage message){
        listMessage.add(message);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == OPPONENT) {
            return new ViewHolderOpponent(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chatting_opponent, parent, false));
        }
        return new ViewHolderSefl(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chatting_self, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ChattingMessage item = listMessage.get(position);
        if(item.getTypeMessage()==MYSELF){
            ViewHolderSefl viewHolderSefl = (ViewHolderSefl)holder;
            viewHolderSefl.txtTextChat.setText(item.getContent());
            viewHolderSefl.txtCreateAt.setText(item.getCreateTime());

        }else{
            ViewHolderOpponent viewHolderOpponent = (ViewHolderOpponent)holder;
            viewHolderOpponent.txtTextChat.setText(item.getContent());
            viewHolderOpponent.txtCreateAt.setText(item.getCreateTime());
            Glide.with(mContext).load(userView.getImageUser()).asBitmap().into(viewHolderOpponent.imgIcon);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listMessage.get(position).getTypeMessage();
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    public void setDataAfterLoad(List<BaseMessage> messages) {
        handleCreateDataTest(messages);
        notifyDataSetChanged();
    }

    public class ViewHolderSefl extends RecyclerView.ViewHolder{
        public TextView txtTextChat;
        public TextView txtCreateAt;
        public ViewHolderSefl(View itemView) {
            super(itemView);
            txtTextChat = (TextView) itemView.findViewById(R.id.txtTextChat);
            txtCreateAt = (TextView) itemView.findViewById(R.id.txtCreateTime);
        }
    }

    public class ViewHolderOpponent  extends RecyclerView.ViewHolder{
        public TextView txtTextChat;
        public TextView txtCreateAt;
        public ImageView imgIcon;
        public ViewHolderOpponent(View itemView) {
            super(itemView);
            txtTextChat = (TextView) itemView.findViewById(R.id.txtTextChat);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIconUser);
            txtCreateAt = (TextView) itemView.findViewById(R.id.txtCreateTime);

        }
    }
}
