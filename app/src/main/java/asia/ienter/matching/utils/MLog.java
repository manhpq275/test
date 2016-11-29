package asia.ienter.matching.utils;

import android.util.Log;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class MLog {

    public static void e(Class<?> clazz, String msg) {
        if (Config.ISLOG)
            e(clazz.getSimpleName(), msg==null?"":msg);
    }

    public static void d(Class<?> clazz, String msg) {
        if (Config.ISLOG)
            d(clazz.getSimpleName(), msg==null?"":msg);
    }

    public static void e(String tag, String msg) {
        if (Config.ISLOG)
            Log.e(tag, msg==null?"":msg);
    }

    public static void d(String tag, String msg) {
        if (Config.ISLOG)
            Log.d(tag, msg==null?"":msg);
    }

    public static void e(String msg) {
        if (Config.ISLOG)
            d("MCApp", msg==null?"":msg);
    }

    public static void d(String msg) {
        if (Config.ISLOG)
            d("MCApp", msg==null?"":msg);
    }
}
