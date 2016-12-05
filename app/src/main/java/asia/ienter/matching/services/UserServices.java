package asia.ienter.matching.services;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.apis.UserApis;
import asia.ienter.matching.interfaces.ICommonViewCallback;
import asia.ienter.matching.interfaces.IGetListUserSearch;
import asia.ienter.matching.interfaces.ILoginCallback;
import asia.ienter.matching.models.CommonView;
import asia.ienter.matching.models.ErrorView;
import asia.ienter.matching.models.User;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.AppStatus;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.models.enums.MCErrorCode;
import asia.ienter.matching.models.enums.Status;
import asia.ienter.matching.utils.Config;
import asia.ienter.matching.utils.CustomStringRequest;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.MultipartRequest;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.utils.Utils;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class UserServices extends BaseService<UserView> {

    private static UserServices sInstance;

    public UserServices() {
        super(UserApis.getInstance(), UserView.class, UserView[].class);
    }


    public static UserServices getInstance() {
        if (sInstance == null) sInstance = new UserServices();

        return sInstance;
    }

    public interface IGetUserInformationCallBack{
        void onSuccess();
    }

    public interface IGetUserInformationFromIDCallBack{
        void onSuccess(User userInfomation);
    }

    public interface ILoginServerCallback{
        void onSuccess();
        void onFailed();
    }

    public void changePassword(final int userID,final String accessToken, ClientType clientType, final ILoginCallback callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.PUT,
                UserApis.getInstance().changePassword(),
                new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                MLog.d(UserServices.class, response);

            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("AccessToken", accessToken);
                params.put("UserID", String.valueOf(userID));
                params.put("Status", String.valueOf(AppStatus.OFFLINE.getValue()));
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void handleLoginToServer(GraphResponse response, final MaterialDialog progress,final ILoginServerCallback callback) {
        final JSONObject jsonObject = response.getJSONObject();
        //Log.i("Data", jsonObject.toString());
        CustomStringRequest request = new CustomStringRequest(StringRequest.Method.POST, Config.BASE_URL + "login", new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("Data", response);
                try {
                    JSONObject jsonResult = new JSONObject(response);
                    SharedPreference.getInstance().putString("sUserID", jsonResult.getJSONObject("data").getString("UserID"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailed();
                }
                UserServices.getInstance().getUserInformation(new UserServices.IGetUserInformationCallBack() {
                    @Override
                    public void onSuccess() {
                        progress.dismiss();
                        callback.onSuccess();
                        SharedPreference.getInstance().putBoolean("LoginFb", true);
                    }
                });
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                SharedPreference.getInstance().putBoolean("LoginFb", false);
                callback.onFailed();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                try {
                    params.put("AccessToken", AccessToken.getCurrentAccessToken().getToken());
                    params.put("UserName", jsonObject.getString("name"));
                    params.put("FirstName", jsonObject.getString("first_name"));
                    params.put("LastName", jsonObject.getString("last_name"));
                    params.put("BirthDay",jsonObject.has("birthday")?jsonObject.getString("birthday"):"owner");
                    params.put("FacebookID",jsonObject.getString("id"));
                    params.put("Email", jsonObject.getString("email"));
                    params.put("Gender", jsonObject.getString("gender").equals("male")?"1":"0");
                    params.put("FcmRegisteredID", "owner");
                    return params;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(request, "");
    }


    public void handleSetInformation(JSONObject jsonObject, final FacebookService.ILoginFbCallback callback){
        try {
            final User userInformation = new User();
            userInformation.setFacebookId(jsonObject.has("id") ? jsonObject.getString("id") : "");
            userInformation.setUserName(jsonObject.has("name") ? jsonObject.getString("name") : "");
            userInformation.setFirstName(jsonObject.has("first_name") ? jsonObject.getString("first_name") : "");
            userInformation.setLastName(jsonObject.has("last_name") ? jsonObject.getString("last_name") : "");
            userInformation.setBirthday(jsonObject.has("birthday") ? jsonObject.getString("birthday") : "-1/-1/-1");
            userInformation.setEmail(jsonObject.has("email") ? jsonObject.getString("email") : "");
            userInformation.setGender((jsonObject.has("gender")&& jsonObject.getString("gender").equals("male"))?1:0);
            userInformation.setOther(jsonObject.has("about") ? jsonObject.getString("about") : "");
            //userInformation.setLiteracy(jsonObject.has("id")?jsonObject.getString("id"):"");
            userInformation.setHometown(jsonObject.has("hometown") ? jsonObject.getJSONObject("hometown").getString("name"):"");
            if(jsonObject.has("education")){
                JSONArray jsonEducation = jsonObject.getJSONArray("education");
                userInformation.setLiteracy(jsonEducation.getJSONObject(jsonEducation.length()-1).getJSONObject("school").getString("name"));
            }
            if(jsonObject.has("cover")){
                userInformation.setUserCover(jsonObject.getJSONObject("cover").getString("source"));
            }
            if(jsonObject.has("picture")){
                userInformation.setUserProfilePic(jsonObject.getJSONObject("picture").getJSONObject("data").getString("url"));
            }
            MCApp.setUserInstance(userInformation);
            MLog.d(UserServices.class,"User = "+ new Gson().toJson(userInformation));
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailed();
        }
    }

    public void getUserInformation(final IGetUserInformationCallBack callBack){
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.PUT, Config.BASE_URL + "getdetailuser", new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("data", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Gson gson = new Gson();
                    User mainUser = MCApp.getUserInstance();
                    User newUser = gson.fromJson(jsonObject.getJSONObject("data").toString(), User.class);
                    if(!mainUser.getUserProfilePic().isEmpty()) {
                        newUser.setUserProfilePic(mainUser.getUserProfilePic());
                    }
                    if(!mainUser.getUserCover().isEmpty()) {
                        newUser.setUserCover(mainUser.getUserCover());
                    }
                    if(!mainUser.getOther().isEmpty() && newUser.getOther().isEmpty()) {
                        newUser.setOther(mainUser.getOther());
                    }

                    MCApp.setUserInstance(newUser);
                    SendBirdController.getInstance().connectUser(MCApp.getUserInstance().getUserId(), new SendBirdController.IConnectUserCallback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
                    callBack.onSuccess();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    StringWriter trace = new StringWriter();
                    ex.printStackTrace(new PrintWriter(trace));
                    //callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                }
            }
            @Override
            public void onError(ArrayList<ErrorView> errors) {
                //callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.i("Access Token", FacebookService.getInstance().getTokenString());
                params.put("AccessToken",FacebookService.getInstance().getTokenString());
                params.put("MyUserID", SharedPreference.getInstance(MCApp.getAppContext()).getString("sUserID", ""));
                params.put("OtherUserID",SharedPreference.getInstance(MCApp.getAppContext()).getString("sUserID", ""));
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void updateUserInformation(final User user){
        CustomStringRequest request = new CustomStringRequest(StringRequest.Method.PUT, Config.BASE_URL + "updateuserprofile", new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                MLog.d(UserServices.class, "updateUserInformation success = "+response);
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(UserServices.class, "updateUserInformation onError");
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Gson gson = new Gson();
                Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
                Map<String,String> params = gson.fromJson(gson.toJson(user), stringStringMap);
                params.put("AccessToken", FacebookService.getInstance().getTokenString());

                MLog.d(UserServices.class,"updateUserInformation Params =" + new Gson().toJson(params));
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(request);
    }

    public void updateUserToServer(final User user) {
        MCApp.setUserInstance(user);
        updateUserInformation(user);
    }

    public void getUserListMatched(final String userID, int page, ClientType clientType, final IGetListUserSearch callback) {
        MLog.d(UserServices.class, "======>API : " +  UserApis.getInstance().getListUserMatched(userID,page));

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET,
                UserApis.getInstance().getListUserMatched(userID,page), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(new JSONObject(response).getString("data"));
                    MLog.d(UserServices.class,"UserList = "+ jsonObject.getString("UserList").toString());
                    ArrayList<UserView> userViews = new Gson().fromJson(jsonObject.getString("UserList").toString(), new TypeToken<List<UserView>>(){}.getType());

                    try {
                        callback.onSuccess(userViews);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        StringWriter trace = new StringWriter();
                        ex.printStackTrace(new PrintWriter(trace));
                        callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                    }
                    MLog.d(UserServices.class,"LIST = " + gson.toJson(userViews));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onSuccess(null);
                }

            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(UserServices.class, "======>Error: " + errors);

                callback.onError(errors);
            }
        }){



        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void getUserInformation(final String userIDView, final IGetUserInformationFromIDCallBack callBack){
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.PUT, UserApis.getInstance().getDetailUser(), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("data", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Gson gson = new Gson();
                    User newUser = gson.fromJson(jsonObject.getJSONObject("data").toString(), User.class);
                    callBack.onSuccess(newUser);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    StringWriter trace = new StringWriter();
                    ex.printStackTrace(new PrintWriter(trace));
                    //callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                }
            }
            @Override
            public void onError(ArrayList<ErrorView> errors) {
                //callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.i("Access Token", FacebookService.getInstance().getTokenString());
                params.put("AccessToken",FacebookService.getInstance().getTokenString());
                params.put("MyUserID", SharedPreference.getInstance(MCApp.getAppContext()).getString("sUserID", ""));
                params.put("OtherUserID",userIDView);
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void makeReportViolation(final String ReportedUserId, final String contentViolation, final IReportUserCallback callBack){
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.PUT, UserApis.getInstance().getReportUser(), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("data", response);
                callBack.onSuccess();
            }
            @Override
            public void onError(ArrayList<ErrorView> errors) {
                callBack.onError();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("AccessToken",FacebookService.getInstance().getTokenString());
                params.put("MyUserID", MCApp.getUserInstance().getUserId());
                params.put("OtherUserID",ReportedUserId);
                params.put("Violation","1");
                params.put("Content",contentViolation);
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public interface IReportUserCallback{
        void onSuccess();
        void onError();
    }

    public void getUserListLikeMe(final String userID, int page, ClientType clientType, final IGetListUserSearch callback) {
        MLog.d(UserServices.class, "======>API : " +  UserApis.getInstance().getListUserLikeMe(userID,page));

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET,
                UserApis.getInstance().getListUserLikeMe(userID,page), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(new JSONObject(response).getString("data"));
                    MLog.d(UserServices.class,"UserList = "+ jsonObject.getString("UserList").toString());
                    ArrayList<UserView> userViews = new Gson().fromJson(jsonObject.getString("UserList").toString(), new TypeToken<List<UserView>>(){}.getType());

                    try {
                        callback.onSuccess(userViews);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        StringWriter trace = new StringWriter();
                        ex.printStackTrace(new PrintWriter(trace));
                        callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                    }
                    MLog.d(UserServices.class,"LIST = " + gson.toJson(userViews));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onSuccess(null);
                }

            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(UserServices.class, "======>Error: " + errors);

                callback.onError(errors);
            }
        }){



        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void getUserListMeLike(final String userID, int page, ClientType clientType, final IGetListUserSearch callback) {
        MLog.d(UserServices.class, "======>API : " +  UserApis.getInstance().getListUserMeLike(userID,page));

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET,
                UserApis.getInstance().getListUserMeLike(userID,page), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(new JSONObject(response).getString("data"));
                    MLog.d(UserServices.class,"UserList = "+ jsonObject.getString("UserList").toString());
                    ArrayList<UserView> userViews = new Gson().fromJson(jsonObject.getString("UserList").toString(), new TypeToken<List<UserView>>(){}.getType());

                    try {
                        callback.onSuccess(userViews);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        StringWriter trace = new StringWriter();
                        ex.printStackTrace(new PrintWriter(trace));
                        callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                    }
                    MLog.d(UserServices.class,"LIST = " + gson.toJson(userViews));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onSuccess(null);
                }

            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(UserServices.class, "======>Error: " + errors);

                callback.onError(errors);
            }
        }){



        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }


    public void sendLike(final UserView userView, ClientType clientType, final ICommonViewCallback callback) {
        MLog.d(UserServices.class,"sendLike = "+ new Gson().toJson(userView));
        MLog.d(UserServices.class,"sendLikeAPI = "+ UserApis.getInstance().sendLike());
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                UserApis.getInstance().sendLike(), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                    MLog.d(UserServices.class,"response = "+ response);
                    CommonView commonView = new Gson().fromJson(response,CommonView.class);

                    if(Status.fromInteger(commonView.getStatus())== Status.LoginFailed){
                        ArrayList<ErrorView> errors = new ArrayList<>();
                        errors.add(new Gson().fromJson(response,ErrorView.class));
                        callback.onError(errors);
                    }else{
                        try {
                            callback.onSuccess(commonView);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            StringWriter trace = new StringWriter();
                            ex.printStackTrace(new PrintWriter(trace));
                            callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                        }
                        MLog.d(UserServices.class,"LIST = " + gson.toJson(commonView));
                    }
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(UserServices.class, "======>Error: " + errors);

                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("AccessToken",FacebookService.getInstance().getTokenString());
                params.put("MyUserID", MCApp.getUserInstance().getUserId());
                params.put("OtherUserID",userView.getUserID());
                params.put("MyLike",String.valueOf(userView.getMyLike()));
                params.put("MySpecialLike",String.valueOf(userView.getMyLikeSpecial()));
                MLog.d(UserServices.class,"sendLikeParams = "+ params);
                return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void uploadImagetoServer(final File bitmap){

    }
    public void RequestMultiPart(File file) {

        Map<String, String> params = new HashMap<>();
        params.put("AccessToken",FacebookService.getInstance().getTokenString());
        params.put("UserID", MCApp.getUserInstance().getUserId());
        params.put("Options","2");
        String filename = MCApp.getUserInstance().getUserName()+".png";
        String boundary = "----------------------------"+MCApp.getUserInstance().getUserId();
        final String reqUrl = UserApis.getInstance().uploadImage();
        MultipartRequest imageUploadReq = new MultipartRequest(reqUrl,params,file,filename,"file",
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        MLog.d("Multipart Request Url: ", reqUrl);
                        MLog.d("Multipart ERROR", "error => " + error.toString());
                    }


                },
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        MLog.d("MediaSent Response", response);
                    }
                }
        ) {

        };

        imageUploadReq.setBoundary(boundary);

        MCApp.getInstance().addToRequestQueue(imageUploadReq);

    }
}
