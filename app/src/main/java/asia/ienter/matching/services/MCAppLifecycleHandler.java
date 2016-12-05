package asia.ienter.matching.services;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;

import java.util.ArrayList;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.models.ErrorView;
import asia.ienter.matching.models.enums.OnlineStatus;
import asia.ienter.matching.utils.CustomStringRequest;
import asia.ienter.matching.utils.MLog;

/**
 * Created by phamquangmanh on 11/17/16.
 */
public class MCAppLifecycleHandler implements Application.ActivityLifecycleCallbacks {
    // I use four separate variables here. You can, of course, just use two and
    // increment/decrement them instead of using four and incrementing them all.
    private int resumed;
    private int paused;
    private int started;
    private int stopped;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if(!(started > stopped))
            HomeServices.getInstance().appStatus(OnlineStatus.OFFLINE, new CustomStringRequest.IResponseStringCallback() {
                @Override
                public void onSuccess(String response) {

                    MLog.d(MCAppLifecycleHandler.class, "======>response offline: " +  response);
                }

                @Override
                public void onError(ArrayList<ErrorView> errors) {
                    MLog.d(MCAppLifecycleHandler.class, "======>response offline: " +  errors);
                }
            });

            MLog.d(MCAppLifecycleHandler.class, "onActivityDestroyed Offline : "+!(started > stopped));



    }


    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
        MLog.d(MCAppLifecycleHandler.class, "onActivityResumed Online: " + (resumed > paused));
        MLog.d(MCAppLifecycleHandler.class, "onActivityResumed Acess: " + MCApp.getUserInstance().getAccessToken() );
        HomeServices.getInstance().appStatus(OnlineStatus.ONLINE, new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {

                MLog.d(MCAppLifecycleHandler.class, "======>response online: " +  response);
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(MCAppLifecycleHandler.class, "======>response online: " +  errors);
            }
        });

    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        if(!(resumed > paused))
        HomeServices.getInstance().appStatus(OnlineStatus.AWAY, new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {

                MLog.d(MCAppLifecycleHandler.class, "======>response away: " +  response);
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(MCAppLifecycleHandler.class, "======>response away: " +  errors);
            }
        });
        MLog.d(MCAppLifecycleHandler.class, "onActivityPaused Away: " + (resumed > paused));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;


    }

    // If you want a static function you can use to check if your application is
    // foreground/background, you can use the following:
    /*
    // Replace the four variables above with these four
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;

    // And these two public static functions
    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public static boolean isApplicationInForeground() {
        return resumed > paused;
    }

     HomeServices.getInstance().appStatus(OnlineStatus.OFFLINE, new CustomStringRequest.IResponseStringCallback() {
                @Override
                public void onSuccess(String response) {

                    MLog.d(MCAppLifecycleHandler.class, "======>response : " +  response);
                }

                @Override
                public void onError(ArrayList<ErrorView> errors) {
                    MLog.d(MCAppLifecycleHandler.class, "======>response : " +  errors);
                }
            });
    */
}
