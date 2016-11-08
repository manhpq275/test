package asia.ienter.matching.utils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Arrays;

import asia.ienter.matching.models.ErrorView;
import asia.ienter.matching.models.enums.MCErrorCode;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public class CustomStringRequest extends StringRequest {
    public CustomStringRequest(int method, String url,
                               final IResponseStringCallback callback) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String responseData = error.getMessage();
                MLog.e(CustomStringRequest.class, responseData);
                try {
                    ArrayList<ErrorView> errorViews = Utils.parseErrorViews(responseData);
                    if (errorViews == null || errorViews.size() == 0) {
                        callback.onError(new ArrayList<>(Arrays.asList(new ErrorView("", MCErrorCode.ConnectionError.getValue()))));
                    } else {
                        callback.onError(errorViews);
                    }
                } catch (Exception ex) {
                    callback.onError(new ArrayList<>(Arrays.asList(new ErrorView("", MCErrorCode.ConnectionError.getValue()))));
                }
            }
        });
        setRetryPolicy(new DefaultRetryPolicy(AppConstants.VOLLEY_TIMEOUT, AppConstants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            volleyError = new VolleyError(new String(volleyError.networkResponse.data));
        }
        return volleyError;
    }

    public interface IResponseStringCallback {
        void onSuccess(String response);
        void onError(ArrayList<ErrorView> errors);
    }
}

