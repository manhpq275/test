package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import asia.ienter.matching.R;

/**
 * Created by hoangtuan on 11/30/16.
 */
public class MessageTemplateAdapter extends RecyclerView.Adapter<MessageTemplateAdapter.ViewHolder> {

    private ArrayList<String> listItem = new ArrayList<>();
    private Context mContext;
    public MessageTemplateAdapter(Context context, String templateString){
        mContext = context;
        createMessageTemplate(templateString);
    }

    private void createMessageTemplate(String string) {
        if(string.isEmpty()) {
            listItem.add("Chào bạn\nMình là Mr.Minh\nRất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
            listItem.add("Chào bạn\n" +
                    "Mình là Mr.Minh\n" +
                    "Rất vui được làm quen với bạn!");
        }
//        else{
//            listItem.addAll(Arrays.asList(string.split("|")));
//        }
    }

    @Override
    public MessageTemplateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageTemplateAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_message_template, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageTemplateAdapter.ViewHolder holder, int position) {
        final String item = listItem.get(position);
        holder.txtTextTemplate.setText(item);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public String getItemString(int position) {
        return listItem.get(position);
    }

    public void addNewTemplate(String string) {
        listItem.add(0, string);
        notifyItemRangeChanged(0, getItemCount());
    }

    public String getStringTemplate(){
        return TextUtils.join("|", listItem);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTextTemplate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTextTemplate = (TextView) itemView.findViewById(R.id.txtTextTemplate);
        }
    }
}
