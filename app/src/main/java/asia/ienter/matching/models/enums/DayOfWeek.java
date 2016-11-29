package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by hoangtuan on 11/24/16.
 */
public enum DayOfWeek {
    All(0),
    ThuHai(1),
    ThuBa(2),
    ThuTu(3),
    ThuNam(4),
    ThuSau(5),
    ThuBay(6),
    ChuNhat(7);
    private int value;
    DayOfWeek(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static DayOfWeek fromInteger(int id){
        DayOfWeek[] values = DayOfWeek.values();
        for (DayOfWeek v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.DAY_OF_WEEK_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
