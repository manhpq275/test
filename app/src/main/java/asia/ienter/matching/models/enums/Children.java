package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum Children {
    ChuaCoCon(0),
    MotCon(1),
    HaiCon(2),
    NhieuHonHai(3);
    private int value;
    Children(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Children fromInteger(int id){
        Children[] values = Children.values();
        for (Children v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
            return Utils.getResourceStringByKey(AppConstants.CHILDREN_PREFIX + value);
    }
}
