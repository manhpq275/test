package asia.ienter.matching.models.enums;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public enum AppStatus {
    OFFLINE(0),
    SUPENDED(1),
    ONLINE(2);
    private int value;
    AppStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static AppStatus fromInteger(int id){
        AppStatus[] values = AppStatus.values();
        for (AppStatus v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }
}
