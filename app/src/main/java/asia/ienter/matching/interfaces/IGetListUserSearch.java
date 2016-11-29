package asia.ienter.matching.interfaces;

import java.util.ArrayList;

import asia.ienter.matching.models.UserView;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public interface IGetListUserSearch extends IBaseGetItemsCallback<UserView> {

    @Override
    public void onError(ArrayList errors);


    @Override
    public void onSuccess(ArrayList<UserView> items);
}
