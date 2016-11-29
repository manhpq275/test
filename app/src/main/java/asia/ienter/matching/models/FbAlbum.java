package asia.ienter.matching.models;

import java.util.ArrayList;

/**
 * Created by hoangtuan on 11/18/16.
 */
public class FbAlbum {
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCover_id() {
        return cover_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCover_id(String cover_id) {
        this.cover_id = cover_id;
    }

    public void setListPicture(ArrayList<FbPicture> listPicture) {
        this.listPicture = listPicture;
    }

    public ArrayList<FbPicture> getListPicture() {
        return listPicture;
    }

    private String id;
    private String name;
    private String cover_id;
    private ArrayList<FbPicture> listPicture = new ArrayList<>();

    public FbAlbum(String Id, String Name, String coverId){
        this.id = Id;
        this.name = Name;
        this.cover_id = coverId;
    }
}
