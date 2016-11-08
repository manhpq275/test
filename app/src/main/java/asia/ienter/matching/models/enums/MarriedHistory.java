package asia.ienter.matching.models.enums;

import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum MarriedHistory {
    NoMarried(0),
    Married(1),
    Divorce(2),
    Separated(3);
    private int value;
    MarriedHistory(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static MarriedHistory fromInteger(int id){
        MarriedHistory[] values = MarriedHistory.values();
        for (MarriedHistory v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
            return Utils.getResourceStringByKey(AppConstants.MARRY_PREFIX + value);
    }
}
