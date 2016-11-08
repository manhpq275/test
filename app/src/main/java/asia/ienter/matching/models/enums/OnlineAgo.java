package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public enum OnlineAgo {
    All(0),
    OneDay(1),
    ThreeDay(2),
    Weekend(3);
    private int value;
    OnlineAgo(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static OnlineAgo fromInteger(int id){
        OnlineAgo[] values = OnlineAgo.values();
        for (OnlineAgo v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.ONLINE_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
