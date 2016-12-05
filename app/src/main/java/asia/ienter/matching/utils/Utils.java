package asia.ienter.matching.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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

    public static String birthDayToYearsOld(String dateInput){
        Calendar calendar = Calendar.getInstance();
        int yearBirthday = Integer.parseInt(dateInput.split("-")[0]);



        int year = calendar.get(Calendar.YEAR);





            MLog.d(Utils.class,"Date format = "+(year-yearBirthday));



        return String.valueOf(year-yearBirthday);
    }

    public static void expand(View v, int heightView) {
        //set Visible
        v.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator;
        if(heightView<0) {
            mAnimator = slideAnimator(0, v.getMeasuredHeight(), v);
        }else {
            mAnimator = slideAnimator(0, heightView, v);
        }
        mAnimator.setDuration(200);
        mAnimator.start();
    }

    public static void collapse(final View v) {
        int finalHeight = v.getHeight();

        ValueAnimator mAnimator;
        mAnimator = slideAnimator(finalHeight, 0, v);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.setDuration(200);
        mAnimator.start();
    }

    public static ValueAnimator slideAnimator(int start, int end, final View v) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public static void scrollToView(final ScrollView scrollViewParent, final View view) {
        // Get deepChild Offset
        Point childOffset = new Point();
        getDeepChildOffset(scrollViewParent, view.getParent(), view, childOffset);
        // Scroll to child.
        scrollViewParent.smoothScrollTo(0, childOffset.y);
    }

    public static void getDeepChildOffset(final ViewGroup mainParent, final ViewParent parent, final View child, final Point accumulatedOffset) {
        ViewGroup parentGroup = (ViewGroup) parent;
        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();
        if (parentGroup.equals(mainParent)) {
            return;
        }
        getDeepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }

    public static int randInt(int max) {


        long currentTime = System.currentTimeMillis() / 1000;

        String rand = String.valueOf(currentTime % max);

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = Integer.parseInt(rand);

        return randomNum;
    }

    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
