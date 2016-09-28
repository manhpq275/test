package asia.ienter.matching.models;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class TopView extends BaseView{
    private String android_version_name;
    private String android_image_url;

    private int likeStatus;

    public TopView(String id, String name, int likeStatus) {
        super(id, name);
        this.likeStatus = likeStatus;
    }

    public String getAndroid_version_name() {
        return android_version_name;
    }

    public void setAndroid_version_name(String android_version_name) {
        this.android_version_name = android_version_name;
    }

    public String getAndroid_image_url() {
        return android_image_url;
    }

    public void setAndroid_image_url(String android_image_url) {
        this.android_image_url = android_image_url;
    }

    public int isLike() {
        return likeStatus;
    }

    public void setLike(int like) {
        likeStatus = like;
    }
}
