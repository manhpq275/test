package asia.ienter.matching.models.enums;

import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum MaxLike {
    KhongGioiHan(0),
    Muoi(10),
    MuoiLam(15),
    HaiMuoi(20),
    BaMuoi(30);
    private int value;
    MaxLike(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static MaxLike fromInteger(int id){
        MaxLike[] values = MaxLike.values();
        for (MaxLike v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
            return Utils.getResourceStringByKey(AppConstants.MAX_LIKE_PREFIX + value);
    }
}
