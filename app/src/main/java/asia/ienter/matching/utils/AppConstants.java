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


    public static int VOLLEY_TIMEOUT = 30000;
    public static int VOLLEY_MAX_NUM_RETRIES = 0;

    //fb api hash key: "ga0RGNYHvNM5d0SLGQfpQWAPGJ8="  pass: hdtq2402
}
