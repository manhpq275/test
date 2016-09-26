package asia.ienter.matching;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Size;

import java.util.HashMap;

import asia.ienter.matching.utils.Config;
import asia.ienter.matching.utils.MLog;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class MCApp extends Application {

    private static MCApp sInstance;
    private static String TAG = "MatchingApplication";

    private HashMap<String, Typeface> fontMap = new HashMap<String, Typeface>();
    public static Typeface typeface;
    public static Typeface typefaceHome;
    public static Point screenSize;

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

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
}
