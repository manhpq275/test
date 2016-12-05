package asia.ienter.matching.services;

import android.app.Activity;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.MLog;

/**
 * Created by hoangtuan on 11/17/16.
 */
public class FacebookService {

    private static FacebookService sInstance;
    private List<String> listPermission;
    public AccessToken accessToken;
    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }

    private CallbackManager mCallbackManager;
    public static FacebookService getInstance() {
        if (sInstance == null) sInstance = new FacebookService();

        return sInstance;
    }

    private FacebookService(){
        listPermission = Arrays.asList(MCApp.getAppContext().getResources().getStringArray(R.array.facebook_permissions));
    }

    public String getTokenString(){
        return AccessToken.getCurrentAccessToken().getToken();
    }

    public void loginFacebook(Activity activity,boolean isLogged,  final IFbLoginCallback callback){
        if(!isLogged) {
            LoginManager.getInstance().logInWithReadPermissions(activity, listPermission);
            mCallbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    accessToken = AccessToken.getCurrentAccessToken();
                    callback.onSuccess();
                }

                @Override
                public void onCancel() {
                    callback.oncancel();
                }

                @Override
                public void onError(FacebookException error) {
                    callback.onError();
                    MLog.d("Facebook login", error.toString());
                }
            });
        }else{
            callback.onSuccess();
        }
    }

    public void getProfilePicture(final IFbGetProfilePicCallback callback){
        if(!AccessToken.getCurrentAccessToken().isExpired()) {
            Bundle params = new Bundle();
            params.putBoolean("redirect", false);
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/picture?type=large",
                    params,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            callback.onSuccess(parseGraphResponse(response.getJSONObject()));
                        }
                    }
            ).executeAsync();
        }
    }

    //LoginResult loginResult
    public void getUserFacebookData(Activity activity, final ILoginFbCallback callback) {
        final MaterialDialog progress = new MaterialDialog.Builder(activity)
                .title("Đăng nhập vào tài khoản...")
                .content("Xin hãy đợi một lát")
                .progress(true, 0)
                .show();

        Bundle params = new Bundle();
        params.putString("Type","large");
        params.putString("fields", AppConstants.FACEBOOK_FIELDS);
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        MLog.d(FacebookService.class,"response="+ response.toString());
                        MCApp.getUserInstance().setAccessToken(AccessToken.getCurrentAccessToken().getToken());
                        MLog.d(FacebookService.class,"User="+ new Gson().toJson(MCApp.getUserInstance()));
                        if(!response.toString().isEmpty() && response.getJSONObject()!=null) {
                            UserServices.getInstance().handleSetInformation(response.getJSONObject(), callback);
                            UserServices.getInstance().handleLoginToServer(response, progress, new UserServices.ILoginServerCallback() {
                                @Override
                                public void onSuccess() {
                                    FacebookService.getInstance().getProfilePicture(new FacebookService.IFbGetProfilePicCallback() {
                                        @Override
                                        public void onSuccess(String urlPicture) {
                                            MCApp.getUserInstance().setUserProfilePic(urlPicture);
                                            callback.onSuccess();
                                        }
                                    });
                                }
                                @Override
                                public void onFailed() {
                                    progress.dismiss();
                                    callback.onFailed();
                                }
                            });
                        }else{
                            callback.onFailed();
                        }
                    }
                }
        ).executeAsync();
    }

    private String parseGraphResponse(JSONObject response) {
        String result = "";
        try {
            result = response.getJSONObject("data").getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * get all picture from facebook of user
     */
    public void getAlbums(final IFbGetAlbumCallback callback){
        //me?fields=albums.fields(id,name,cover_photo,photos.fields(name,picture,source))
        Bundle params = new Bundle();
        params.putBoolean("redirect", false);
        new GraphRequest(

                AccessToken.getCurrentAccessToken(),
                "/me?fields=albums.fields(id,name,cover_photo{source},photos.fields(name,picture,source))",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.i("Result", response.toString());
                        try {
                            if(response.getJSONObject().has("albums")) {
                                callback.onSuccess(response.getJSONObject().getJSONObject("albums").getJSONArray("data"));
                            }else{
                                callback.onFailed();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onFailed();
                        }
                    }
                }
        ).executeAsync();
    }

    public void deleteCacheFacebook(){
        AccessToken.setCurrentAccessToken(null);
        Profile.setCurrentProfile(null);
    }

    public interface IFbGetProfilePicCallback{
        void onSuccess(String urlPicture);
    }

    public interface IFbGetAlbumCallback{
        void onSuccess(JSONArray response);
        void onFailed();
    }

    public interface IFbLoginCallback{
        void onSuccess();
        void oncancel();
        void onError();
    }

    public interface ILoginFbCallback{
        void onSuccess();
        void onFailed();
    }
}
