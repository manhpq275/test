package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum MarryTime {
    All(0),
    BaThang(1),
    SauThang(2),
    Hon6Thang(3);
    private int value;
    MarryTime(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static MarryTime fromInteger(int id){
        MarryTime[] values = MarryTime.values();
        for (MarryTime v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.MARRY_TIME_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
