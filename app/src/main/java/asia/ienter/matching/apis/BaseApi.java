package asia.ienter.matching.apis;

import asia.ienter.matching.models.BaseView;
import asia.ienter.matching.models.enums.SortBy;
import asia.ienter.matching.models.enums.SortDirection;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.Config;

/**
 * Created by phamquangmanh on 9/15/16.
 */
public abstract class BaseApi<T extends BaseView> {
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String baseUrl;

    public BaseApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getItemApiUrl(String id) {
        return String.format(AppConstants.API_GET_ITEM_PATTERN, this.baseUrl, id);
    }

    public String getItemsApiUrl(int page, int quantity, String searchValue, SortBy sortBy, SortDirection sortDirection) {
        return String.format(AppConstants.API_GET_ITEMS_PATTERN, this.baseUrl, page, quantity,searchValue, sortBy, sortDirection);
    }
}
