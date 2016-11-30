package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.models.FbPicture;

/**
 * Created by hoangtuan on 11/21/16.
 */
public class FbGridAlbumAdapter extends BaseAdapter {

    private ArrayList<FbPicture> listItems;
    private Context mContext;

    public FbGridAlbumAdapter(Context context, ArrayList<FbPicture> list){
        this.mContext = context;
        this.listItems = list;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_fb_grid, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgFbPicture);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ViewGroup.LayoutParams params = viewHolder.imageView.getLayoutParams();
        params.width = MCApp.getScreenSize().x/3;
        params.height = MCApp.getScreenSize().x/3;
        viewHolder.imageView.setLayoutParams(params);
        //Picasso.with(mContext).load(listItems.get(position).getPictureUrl()).resize(MCApp.getScreenSize().x / 3, MCApp.getScreenSize().x / 3).into(viewHolder.imageView);
        Glide.with(mContext).load(listItems.get(position).getPictureUrl()).asBitmap().into(viewHolder.imageView);
        return convertView;
    }

    public String getUrlPicture(int position) {
        return listItems.get(position).getPictureUrl();
    }

    private class ViewHolder{
        public ImageView imageView;
    }
}
