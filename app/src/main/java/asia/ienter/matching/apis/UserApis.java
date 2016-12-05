package asia.ienter.matching.apis;

import asia.ienter.matching.models.UserView;
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

    public String getListUserMatched(String userId,int page) {
        return String.format(AppConstants.API_HOME_GET_LIST_MATCHING_PATTERN, this.getBaseUrl(),userId,page);
    }

    public String getDetailUser(){
        return String.format(AppConstants.API_USER_GET_DETAIL,this.getBaseUrl());
    }

    public String getReportUser(){
        return String.format(AppConstants.API_USER_REPORT,this.getBaseUrl());
    }

    public String getListUserLikeMe(String userID, int page) {
        return String.format(AppConstants.API_USER_LIST_LIKE_ME,this.getBaseUrl(),userID,page);
    }
    public String getListUserMeLike(String userID, int page) {
        return String.format(AppConstants.API_USER_LIST_ME_LIKE,this.getBaseUrl(),userID,page);
    }

    public String sendLike() {
        return String.format(AppConstants.API_USER_SEND_LIKE,this.getBaseUrl());
    }

    public String uploadImage() {
        return String.format(AppConstants.API_USER_UPLOAD_IMAGE,this.getBaseUrl());
    }
}
