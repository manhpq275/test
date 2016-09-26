package asia.ienter.matching.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by phamquangmanh on 9/15/16.
 */
public class ReplaceFragment {
    public void replace(FragmentManager manager, Fragment fragmentClazz,
                        int layoutId) {
        String backStateName = fragmentClazz.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(backStateName);
            transaction.replace(layoutId, fragmentClazz);
            transaction.commit();
        }
    }
}
