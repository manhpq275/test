package asia.ienter.matching.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.models.ErrorView;

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
    public static int converDpToPx(Context context, float dips) {
        return (int) (dips * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static String getResourceStringByKey(String key) {
        int text_id = MCApp.getInstance().getResources().getIdentifier(key, "string", MCApp.getInstance().getPackageName());
        try {
            return MCApp.getInstance().getString(text_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    public static ArrayList<ErrorView> parseErrorViews(String json) {
        Gson gson = new Gson();
        ArrayList<ErrorView> errorViews = new ArrayList<>();
        ErrorView[] parses = gson.fromJson(json, ErrorView[].class);
        errorViews.addAll(Arrays.asList(parses));
        return errorViews;
    }

}
