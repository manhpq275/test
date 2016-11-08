package asia.ienter.matching.services;

import com.android.volley.Request;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.apis.UserApis;
import asia.ienter.matching.interfaces.ILoginCallback;
import asia.ienter.matching.models.ErrorView;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.models.enums.ClientType;
import asia.ienter.matching.models.enums.MCErrorCode;
import asia.ienter.matching.utils.CustomStringRequest;
import asia.ienter.matching.utils.MLog;

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

    public void login(String email, String password, ClientType clientType, final ILoginCallback callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST, UserApis.getInstance().loginApiUrl(email, password, clientType), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                MLog.d(UserServices.class, response);
                Gson gson = new Gson();
                UserView userView = gson.fromJson(response, UserView.class);
                MLog.d(UserServices.class, "======>UserServices: " + new Gson().toJson(userView));

                try {
                    callback.onSuccess(userView);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    StringWriter trace = new StringWriter();
                    ex.printStackTrace(new PrintWriter(trace));
                    callback.onError(new ArrayList<>(Arrays.asList(new ErrorView(ex.getMessage(), MCErrorCode.GeneralError.getValue()))));
                }
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                callback.onError(errors);
            }
        });
        MCApp.getInstance().addToRequestQueue(baseStringRequest);
    }
}
