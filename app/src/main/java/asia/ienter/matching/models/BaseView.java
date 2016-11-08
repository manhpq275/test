package asia.ienter.matching.models;

/**
 * Created by phamquangmanh on 9/22/16.
 */
public class BaseView {
    private String id;
    private String name;

    public BaseView(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

