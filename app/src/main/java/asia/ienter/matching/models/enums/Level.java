package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum Level {
    All(0),
    TieuHoc(1),
    THCS(2),
    THPT(3),
    TrungCap(4),
    CaoDang(5),
    DaiHoc(6),
    TrenDaiHoc(7);
    private int value;
    Level(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Level fromInteger(int id){
        Level[] values = Level.values();
        for (Level v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.LEVEL_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
