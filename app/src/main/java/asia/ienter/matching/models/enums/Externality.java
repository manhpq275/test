package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum Externality {
    All(0),
    CanDoi(1),
    HoiMap(2),
    HoiGay(3),
    Map(4),
    Gay(5);
    private int value;
    Externality(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Externality fromInteger(int id){
        Externality[] values = Externality.values();
        for (Externality v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.EXTERNALITY_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
