package asia.ienter.matching.models;

/**
 * Created by phamquangmanh on 9/22/16.
 */
public class BaseView {
    protected String Id;
    protected String Name;

    public BaseView(String id, String name) {
        Id = id;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
