package asia.ienter.matching.apis;

import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.utils.AppConstants;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class HomeApi extends BaseApi<UserView>{
    private static HomeApi sInstance;
    public static HomeApi getInstance() {
        if (sInstance == null) sInstance = new HomeApi();
        return sInstance;
    }

    public HomeApi() {
        super("");
    }

    public String getListUserSearch(int page) {
        return String.format(AppConstants.API_HOME_GET_LIST_SEARCH_PATTERN, this.getBaseUrl(),page);
    }

    public String appStatus(){
        return String.format(AppConstants.API_HOME_LEAVE_APP_PATTERN, this.getBaseUrl());
    }

}
