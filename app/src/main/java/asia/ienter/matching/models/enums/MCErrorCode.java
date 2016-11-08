package asia.ienter.matching.models.enums;

import com.google.gson.annotations.SerializedName;

import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public enum MCErrorCode {
    @SerializedName("401")
    Unauthorized(401),

    @SerializedName("1")
    ConnectionError(1),

    @SerializedName("1000")
    GeneralError(1000),

    @SerializedName("1005")
    ItemNotFound(1005);

    private int value;

    MCErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MCErrorCode fromInteger(int id){
        MCErrorCode[] values = MCErrorCode.values();
        for (MCErrorCode v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }

    @Override
    public String toString() {
        if (value > 0)
            return Utils.getResourceStringByKey(AppConstants.ERROR_PREFIX + value);
        else
            return Utils.getResourceStringByKey(AppConstants.ERROR_PREFIX + MCErrorCode.ConnectionError.getValue());
    }
}
