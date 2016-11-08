package asia.ienter.matching.interfaces;

import java.util.ArrayList;

import asia.ienter.matching.models.BaseView;
import asia.ienter.matching.models.ErrorView;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public interface IBaseGetItemsCallback<T extends BaseView> {
    void onSuccess(ArrayList<T> items);
    void onError(ArrayList<ErrorView> errors);
}

