package asia.ienter.matching.utils;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import asia.ienter.matching.MCApp;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public class Utils {
    public static boolean hasInternet(Activity activity){
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(activity.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo !=null && netInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }
    }
}
