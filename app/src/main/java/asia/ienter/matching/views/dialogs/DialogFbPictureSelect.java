package asia.ienter.matching.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import asia.ienter.matching.R;
import asia.ienter.matching.models.FbPicture;
import asia.ienter.matching.services.FacebookService;
import asia.ienter.matching.views.adapters.FbAlbumAdapter;
import asia.ienter.matching.views.adapters.FbGridAlbumAdapter;

/**
 * Created by hoangtuan on 11/18/16.
 */
public class DialogFbPictureSelect {
    private Context context;
    private Dialog dialog;
    private TextView txtDone, tvTitle;
    private ImageView btnBack;
    private ListView lvAlbums;
    private GridView gridPicture;
    private FbGridAlbumAdapter gridAlbumAdapter;
    private FbAlbumAdapter fbAlbumAdapter;
    private IFbPictureSelectCallback dialogCallback;

    public DialogFbPictureSelect(final Context context, final IFbPictureSelectCallback callback){
        this.context = context;
        dialog = new Dialog(this.context, R.style.DialogSlideUp);
        dialog.setContentView(R.layout.dialog_listview_fb);
        txtDone = (TextView) dialog.findViewById(R.id.tvDone);
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        btnBack = (ImageView) dialog.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvAlbums.getVisibility()==View.VISIBLE) {
                    callback.onClickBack();
                    hide();
                }else{
                    lvAlbums.setVisibility(View.VISIBLE);
                    gridPicture.setVisibility(View.GONE);
                }
            }
        });
        lvAlbums = (ListView) dialog.findViewById(R.id.listDialog);
        gridPicture = (GridView) dialog.findViewById(R.id.gridDialog);
        txtDone.setVisibility(View.INVISIBLE);
        FacebookService.getInstance().getAlbums(new FacebookService.IFbGetAlbumCallback() {
            @Override
            public void onSuccess(JSONArray response) {
                fbAlbumAdapter = new FbAlbumAdapter(context, response);
                lvAlbums.setAdapter(fbAlbumAdapter);
            }

            @Override
            public void onFailed() {

            }
        });

        lvAlbums.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleShowPicOfAlbum(fbAlbumAdapter.getListPicture(position), callback);
            }
        });


        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                callback.onClickBack();
            }
        });
    }

    private void handleShowPicOfAlbum(ArrayList<FbPicture> listPicture, final IFbPictureSelectCallback callback) {
        gridPicture.setVisibility(View.VISIBLE);
        lvAlbums.setVisibility(View.GONE);
        gridAlbumAdapter = new FbGridAlbumAdapter(context, listPicture);
        gridPicture.setAdapter(gridAlbumAdapter);
        gridPicture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onClickItem(gridAlbumAdapter.getUrlPicture(position));
                hide();
            }
        });
    }

    public void show(String title){
        tvTitle.setText(title);

        if (dialog != null)
            dialog.show();
    }

    public void hide(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public interface IFbPictureSelectCallback{
        void onClickBack();
        void onClickItem(String url);
    }
}
