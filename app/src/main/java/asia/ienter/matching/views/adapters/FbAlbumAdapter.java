package asia.ienter.matching.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.models.FbAlbum;
import asia.ienter.matching.models.FbPicture;

/**
 * Created by hoangtuan on 11/18/16.
 */
public class FbAlbumAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<FbAlbum> listItem = new ArrayList<>();

    public FbAlbumAdapter(Context context, JSONArray data){
        this.mContext = context;
        parsingData(data);
    }

    private void parsingData(JSONArray data) {
        try {
            int lengh = data.length();
            for(int i=0;i<lengh;i++){
                JSONObject object = data.getJSONObject(i);
                String coverThumbnail = object.getJSONObject("cover_photo").getString("source");
                FbAlbum item = new FbAlbum(object.getString("id"), object.getString("name"), coverThumbnail);
                ArrayList<FbPicture> listPicture = new ArrayList<>();
                JSONArray jsArrayPicture = object.getJSONObject("photos").getJSONArray("data");
                for(int j=0;j<jsArrayPicture.length();j++){
                    listPicture.add(new Gson().fromJson(jsArrayPicture.getString(j), FbPicture.class));
                }
                item.setListPicture(listPicture);
                listItem.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return listItem.size();
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
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_fb_albums, parent, false);
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.imgFbThumbnail);
            holder.albumsName = (TextView) convertView.findViewById(R.id.txtFbAlbumName);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        FbAlbum item = listItem.get(position);
        holder.albumsName.setText(item.getName());
        //Picasso.with(mContext).load(item.getCover_id()).resize(80, 80).into(holder.thumbnail);
        Glide.with(mContext).load(item.getCover_id()).asBitmap().into(holder.thumbnail);
        return convertView;
    }

    public ArrayList<FbPicture> getListPicture(int position) {
        return listItem.get(position).getListPicture();
    }

    private class ViewHolder{
        private ImageView thumbnail;
        private TextView albumsName;
    }
}
