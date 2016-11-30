package asia.ienter.matching.models.enums;

import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 11/30/16.
 */
public enum Status {
    Success(0),
    LoginFailed(1);
    private int value;
    Status(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Status fromInteger(int id){
        Status[] values = Status.values();
        for (Status v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        return Utils.getResourceStringByKey(AppConstants.STATUS_PREFIX + value);
    }
}
