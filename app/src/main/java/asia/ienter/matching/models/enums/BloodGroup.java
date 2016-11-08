package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum BloodGroup {
    All(0),
    A(1),
    B(2),
    O(3),
    AB(4),
    RH_PLUS(5),
    RH_SUB(6);
    private int value;
    BloodGroup(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static BloodGroup fromInteger(int id){
        BloodGroup[] values = BloodGroup.values();
        for (BloodGroup v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.BLOOD_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
