package asia.ienter.matching;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sendbird.android.SendBird;

import java.util.HashMap;

import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.models.User;
import asia.ienter.matching.models.enums.OnlineStatus;
import asia.ienter.matching.services.MCAppLifecycleHandler;
import asia.ienter.matching.utils.Config;
import asia.ienter.matching.utils.MLog;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class MCApp extends Application {

    private static MCApp sInstance;
    private static String TAG = "MatchingApplication";
    private final String APPSendBirdID = "3C81E624-46C4-4D3C-A1D7-B874145A0E80";
    private HashMap<String, Typeface> fontMap = new HashMap<String, Typeface>();
    public static Typeface typeface;
    public static Typeface typefaceHome;
    public static Point screenSize;

    private static String accessToken = "";
    private static User userInstance = new User();


    public static OnlineStatus getAppStatus() {
        return appStatus;
    }

    public static void setAppStatus(OnlineStatus appStatus) {
        MCApp.appStatus = appStatus;
    }

    private static OnlineStatus appStatus = OnlineStatus.OFFLINE;

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    private RequestQueue mRequestQueue;

    private static AdvanceSearchView advanceSearchView;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        typeface = getFont(Config.FONTS);
        typefaceHome = getFont(Config.FONTS_HOME);
        SendBird.init(APPSendBirdID, getApplicationContext());
        registerActivityLifecycleCallbacks(new MCAppLifecycleHandler());
    }

    public static User getUserInstance(){
        if(userInstance==null){
            userInstance = new User();
        }
        return userInstance;
    }

    public static void setUserInstance(User user){
        if(user.getAccessToken().isEmpty()) user.setAccessToken(accessToken);
        userInstance = user;
    }

    public static MCApp getInstance(){
        return sInstance;
    }

    public Typeface getFont(String font) {
        Typeface typeface = fontMap.get(font);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/" + font);
                fontMap.put(font, typeface);
            } catch (Exception e) {
                MLog.e(TAG, " Font Factory Could not get typeface: " + e.getMessage() + " with name: " + font);
                return null;
            }

        }
        return typeface;
    }
    public static void setScreenSize(Point size){
        screenSize = size;
    }
    public static Point getScreenSize(){
        return screenSize;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public static void setAdvanceSearchView(AdvanceSearchView advanceSearchView){
        MCApp.advanceSearchView = advanceSearchView;
    }

    public static AdvanceSearchView getAdvanceSearchView(){
        if(advanceSearchView == null) advanceSearchView = new AdvanceSearchView();
        return advanceSearchView;
    }

    public static void setAccessToken(String accessToken){
        MCApp.accessToken = accessToken;
    }

    public static String getAccessToken(){
        return accessToken;
    }


}
