package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum SmokeWine {
    All(0),
    KhongSuDung(1),
    ThiThoang(2),
    DungIt(3),
    DungNhieu(4),
    ThuongXuyen(5),
    NghienNang(6);
    private int value;
    SmokeWine(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static SmokeWine fromInteger(int id){
        SmokeWine[] values = SmokeWine.values();
        for (SmokeWine v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.SMOKEWINE_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
