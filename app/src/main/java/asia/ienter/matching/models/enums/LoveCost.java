package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum LoveCost {
    ChiaDeu(0),
    DanOng(1),
    PhuNu(2),
    TuyCoUngBien(3);
    private int value;
    LoveCost(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static LoveCost fromInteger(int id){
        LoveCost[] values = LoveCost.values();
        for (LoveCost v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
            return Utils.getResourceStringByKey(AppConstants.LOVE_COST_PREFIX + value);
    }
}
