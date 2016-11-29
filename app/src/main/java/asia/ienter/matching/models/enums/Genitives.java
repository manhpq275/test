package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by hoangtuan on 11/16/16.
 */
public enum Genitives {
    All(0),
    HienLanh(1),
    HaoPhong(2),
    KhiemTon(3),
    OnDinh(4),
    LacQuan(5),
    TramTinh(6),
    YeuDoi(7),
    NhietTinh(8),
    ChanThanh(9),
    ThatTha(10);
    private int value;
    Genitives(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Genitives fromInteger(int id){
        Genitives[] values = Genitives.values();
        for (Genitives v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.GENITIVE_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
