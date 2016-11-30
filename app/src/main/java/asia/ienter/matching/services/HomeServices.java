package asia.ienter.matching.services;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.apis.HomeApi;
import asia.ienter.matching.interfaces.IGetListUserSearch;
import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.models.ErrorView;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.AppStatus;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.models.enums.MCErrorCode;
import asia.ienter.matching.utils.CustomStringRequest;
import asia.ienter.matching.utils.MLog;

//import asia.ienter.matching.apis.UserApis;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class HomeServices extends BaseService<UserView> {

    private static HomeServices sInstance;

    public HomeServices() {
        super(HomeApi.getInstance(), UserView.class, UserView[].class);
    }


    public static HomeServices getInstance() {
        if (sInstance == null) sInstance = new HomeServices();

        return sInstance;
    }

    public void getUserListSearch(final String userID, final AdvanceSearchView advanceSearchView, int page, ClientType clientType, final IGetListUserSearch callback) {
        MLog.d(HomeServices.class, "======>API : " +  HomeApi.getInstance().getListUserSearch(page));

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                HomeApi.getInstance().getListUserSearch(page), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(new JSONObject(response).getString("data"));
                    MLog.d(HomeServices.class,jsonObject.getString("UserList").toString());
                    ArrayList<UserView> userViews = new Gson().fromJson(jsonObject.getString("UserList").toString(), new TypeToken<List<UserView>>(){}.getType());

                    try {
                        callback.onSuccess(userViews);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        StringWriter trace = new StringWriter();
                        ex.printStackTrace(new PrintWriter(trace));
                        callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                    }
                    MLog.d(HomeServices.class,"LIST = " + gson.toJson(userViews));
                } catch (JSONException e) {
                    e.printStackTrace();
                        callback.onSuccess(null);
                }

            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(HomeServices.class, "======>Error: " + errors);

                callback.onError(errors);
            }
        }){

            @Override
            public HashMap<String,String> getParams(){
                HashMap<String,String> params = new HashMap<>();
                params.put("UserID",String.valueOf(userID));
                params.put("IsImage",String.valueOf((advanceSearchView.isAvatar()==false)?0:1));
                if(advanceSearchView.getMaxYearOld()!=AdvanceSearchView.DEFAULT_YEARS_OLD){
                    params.put("FromYearOld",String.valueOf(advanceSearchView.getMinYearOld()));
                    params.put("ToYearOld",String.valueOf(advanceSearchView.getMaxYearOld()));
                }
                if(advanceSearchView.getMaxHeight()!=AdvanceSearchView.DEFAULT_HEIGHT){
                    params.put("FromHeight",String.valueOf(advanceSearchView.getMinHeight()));
                    params.put("ToHeight",String.valueOf(advanceSearchView.getMaxHeight()));
                }
                if(advanceSearchView.getMaxWeight()!=AdvanceSearchView.DEFAULT_WEIGHT){
                    params.put("ToWeight",String.valueOf(advanceSearchView.getMaxWeight()));
                    params.put("FromWeight",String.valueOf(advanceSearchView.getMinWeight()));
                }

                params.put("HomeTown",String.valueOf(advanceSearchView.getHomeLand()));


                MLog.d(HomeServices.class, "======>Params: " + params);
                return params;
            }

        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void appStatus(final int UserID, final String accessToken, final CustomStringRequest.IResponseStringCallback callback){
        MLog.d(HomeServices.class, "======>API : " +  HomeApi.getInstance().appStatus());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.PUT,
                HomeApi.getInstance().appStatus(), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                MLog.d(HomeServices.class, "leaveApp response : " +  response);

                callback.onSuccess(response);
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                MLog.d(HomeServices.class, "leaveApp errors : " +  errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                    params.put("AccessToken", accessToken);
                    params.put("UserID", String.valueOf(UserID));
                    params.put("Status", String.valueOf(AppStatus.OFFLINE.getValue()));
                    return params;
            }
        };
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }


}
