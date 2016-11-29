package asia.ienter.matching.utils;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public class AppConstants {
    public static final String ROOT_DATA_API_URL = "https://i-enter.asia/data/";

    //Common APIs
    public static final String API_GET_ITEM_PATTERN = ROOT_DATA_API_URL + "%s/getitem/%s" ;
    public static final String API_GET_ITEMS_PATTERN = ROOT_DATA_API_URL + "%s/getitems/?page=%s&quantity=%s&searchValue=%s&sortBy=%s&sortDirection=%s";

    public static final String  ERROR_PREFIX = "error_";
    public static final String API_USER_LOGIN_PATTERN = "%s/login";


    public static final String ONLINE_PREFIX = "online_";
    public static final String REGION_PREFIX = "region_";
    public static final String  EXTERNALITY_PREFIX = "externality_";
    public static final String BLOOD_PREFIX = "bloodGroup_";
    public static final String LEVEL_PREFIX = "level_";
    public static final String SMOKEWINE_PREFIX = "smokeWine_";
    public static final String MARRY_TIME_PREFIX = "marryTime_";
    public static final String  MARRY_PREFIX = "married_";
    public static final String CHILDREN_PREFIX = "children_";
    public static final String LOVE_COST_PREFIX = "loveCost_";
    public static final String LANGUAGE_PREFIX = "language_" ;
    public static final String GENITIVE_PREFIX = "genitive_";
    public static final String MAX_LIKE_PREFIX = "maxLike_";
    public static final String DAY_OF_WEEK_PREFIX = "dayOfWeek_";
    public static final String REPORT_PREFIX = "report_";


    public static int VOLLEY_TIMEOUT = 30000;
    public static int VOLLEY_MAX_NUM_RETRIES = 0;
    public static int CALL_CAMERA = 1;
    public static int CALL_GALLERY = 2;

    //fb api hash key: "ga0RGNYHvNM5d0SLGQfpQWAPGJ8="  pass: hdtq2402

    public static final String API_HOME_GET_LIST_MATCHING_PATTERN = "%s/getlistusermatched/%s?page=%s";

    public static final String FACEBOOK_FIELDS = "id,name,first_name,last_name,birthday,email,gender,about, education, cover, picture, work, hometown, languages";
    public static final String API_HOME_LEAVE_APP_PATTERN =  "%s/leaveapp";

    public static final String API_USER_CHANGE_PASSWORD = "%s/changepassword";
}
