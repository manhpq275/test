package asia.ienter.matching.services;

import com.android.volley.Request;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.apis.BaseApi;
import asia.ienter.matching.interfaces.IBaseGetItemCallback;
import asia.ienter.matching.interfaces.IBaseGetItemsCallback;
import asia.ienter.matching.models.BaseView;
import asia.ienter.matching.models.ErrorView;
import asia.ienter.matching.models.enums.MCErrorCode;
import asia.ienter.matching.models.enums.SortBy;
import asia.ienter.matching.models.enums.SortDirection;
import asia.ienter.matching.utils.CustomStringRequest;
import asia.ienter.matching.utils.MLog;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public class BaseService<T extends BaseView> {
    private BaseApi<T> baseApi;
    private Class<T> singleType;
    private Class<T[]> arrayType;

    public BaseService(BaseApi<T> baseApi, Class<T> singleType, Class<T[]> arrayType){
        this.baseApi = baseApi;
        this.singleType = singleType;
        this.arrayType = arrayType;
    }

    public void getItem(String idOrCode, final IBaseGetItemCallback<T> callback) {
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, baseApi.getItemApiUrl(idOrCode), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                MLog.d(BaseService.class, response);
                try {
                    callback.onSuccess(parseItemView(response));
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

    public void getItems(int page, int quantity, String searchValue, SortBy sortBy, SortDirection sortDirection, final IBaseGetItemsCallback<T> callback) {
        MLog.d(BaseService.class,"baseApi = " + baseApi.getItemsApiUrl(page, quantity, searchValue, sortBy, sortDirection));
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.GET, baseApi.getItemsApiUrl(page, quantity, searchValue, sortBy, sortDirection), new CustomStringRequest.IResponseStringCallback() {
            @Override
            public void onSuccess(String response) {
                MLog.d(BaseService.class, response);
                try {
                    callback.onSuccess(parseItemViews(response));
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

    protected T parseItemView(String json) {
        return parseItemView(json, singleType);
    }

    protected <TView extends BaseView> TView parseItemView(String json, Class<TView> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    protected ArrayList<T> parseItemViews(String json) {
        return parseItemViews(json, arrayType);
    }

    protected <TView extends BaseView> ArrayList<TView > parseItemViews(String json, Class<TView[]> type) {
        Gson gson = new Gson();
        ArrayList<TView> itemViews = new ArrayList<>();
        TView[] parses = gson.fromJson(json, type);
        itemViews.addAll(Arrays.asList(parses));
        return itemViews;
    }
}