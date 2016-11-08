package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;


/**
 * Createdbyphamquangmanhon10/28/16.
 */
public enum Regions {
    All(0),
    HaNoi(1),
    TPHCM(2),
    DaNang(3),
    ThuaThienHue(4),
    HaiPhong(5),
    CanTho(6),
    AnGiang(7),
    BaRiaVungTau(8),
    BacGiang(9),
    BacKan(10),
    BacLieu(11),
    BacNinh(12),
    BenTre(13),
    BinhDinh(14),
    BinhDuong(15),
    BinhPhuoc(16),
    BinhThuan(17),
    CaMau(18),
    CaoBang(19),
    DakLak(20),
    DakNong(21),
    DienBien(22),
    DongNai(23),
    DongThap(24),
    GiaLai(25),
    HaGiang(26),
    HaNam(27),
    HaTinh(28),
    HaiDuong(29),
    HauGiang(30),
    HoaBinh(31),
    HungYen(32),
    KhanhHoa(33),
    KienGiang(34),
    KonTum(35),
    LaiChau(36),
    LamDong(37),
    LangSon(38),
    LaoCai(39),
    LongAn(40),
    NamDinh(41),
    NgheAn(42),
    NinhBinh(43),
    NinhThuan(44),
    PhuTho(45),
    QuangBinh(46),
    QuangNam(47),
    QuangNgai(48),
    QuangNinh(49),
    QuangTri(50),
    SocTrang(51),
    SonLa(52),
    TayNinh(53),
    ThaiBinh(54),
    ThaiNguyen(55),
    ThanhHoa(56),
    TienGiang(57),
    TraVinh(58),
    TuyenQuang(59),
    VinhLong(60),
    VinhPhuc(61),
    YenBai(62),
    PhuYen(63);
    private int value;
    Regions(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static Regions fromInteger(int id){
        Regions[] values = Regions.values();
        for (Regions v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.REGION_PREFIX + value);
        else
            return MCApp.getInstance().getString(R.string.txtAll);
    }
}
