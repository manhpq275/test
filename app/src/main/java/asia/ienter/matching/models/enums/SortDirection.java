package asia.ienter.matching.models.enums;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public enum SortDirection {
    Ascending(0),
    Descending(1);

    private int value;
    SortDirection(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static SortDirection fromInteger(int id){
        SortDirection[] values = SortDirection.values();
        for (SortDirection v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
