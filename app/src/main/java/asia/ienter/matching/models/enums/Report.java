package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by hoangtuan on 11/24/16.
 */
public enum Report {
    All(0),
    KhongDuTuoi(1),
    ThongTinSaiLech(2),
    MucDichThuongMai(3),
    QuangCao(4),
    TongTien(5),
    QuayRoi(6),
    VuKhong(7),
    ViPhamBanQuyen(8),
    ViPhamLuatPhap(9);
    private int value;
    Report(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Report fromInteger(int id){
        Report[] values = Report.values();
        for (Report v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.REPORT_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
