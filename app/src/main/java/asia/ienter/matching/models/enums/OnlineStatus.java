package asia.ienter.matching.models.enums;

import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 12/2/16.
 */
public enum OnlineStatus {
    OFFLINE(0),
    AWAY(1),
    ONLINE(2);
    private int value;
    OnlineStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static OnlineStatus fromInteger(int id){
        OnlineStatus[] values = OnlineStatus.values();
        for (OnlineStatus v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        return Utils.getResourceStringByKey(AppConstants.ONLINE_STATUS_PREFIX + value);
    }
}
