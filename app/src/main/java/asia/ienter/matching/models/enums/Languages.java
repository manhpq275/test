package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum Languages {
    All(0),
    English(1),
    Japanese(2),
    TiengViet(3),
    TiengTrung(4),
    TiengLao(5),
    TiengThai(6),
    TiengTayBanNha(7),
    TiengNga(8),
    TiengHan(9),
    TiengIndonesia(10),
    TiengDanMach(11),
    TiengDuc(12),
    TiengHaLan(13);
    private int value;
    Languages(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Languages fromInteger(int id){
        Languages[] values = Languages.values();
        for (Languages v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.LANGUAGE_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
