package asia.ienter.matching.interfaces;

import java.util.ArrayList;

import asia.ienter.matching.models.BaseView;
import asia.ienter.matching.models.ErrorView;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public interface IBaseGetItemCallback<T extends BaseView>  {
    void onSuccess(T item);
    void onError(ArrayList<ErrorView> errors);
}
