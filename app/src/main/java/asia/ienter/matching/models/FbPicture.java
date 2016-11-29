package asia.ienter.matching.models;

/**
 * Created by hoangtuan on 11/21/16.
 */
public class FbPicture {
    public String getId() {
        return id;
    }

    public String getPictureUrl() {
        return source;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPictureUrl(String pictureUrl) {
        this.source = pictureUrl;
    }

    private String id;
    private String source;

    public FbPicture(String id, String url){
        this.id = id;
        this.source = url;
    }
}
