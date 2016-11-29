package asia.ienter.matching.apis;

import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.utils.AppConstants;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class UserApis extends BaseApi<UserView>{
    private static UserApis sInstance;
    public static UserApis getInstance() {
        if (sInstance == null) sInstance = new UserApis();
        return sInstance;
    }

    public UserApis() {
        super("");
    }


    public String changePassword() {
        return String.format(AppConstants.API_USER_CHANGE_PASSWORD, this.getBaseUrl());
    }
}
