package asia.ienter.matching.interfaces;

import java.util.ArrayList;

import asia.ienter.matching.models.UserView;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public interface ILoginCallback extends IBaseGetItemCallback<UserView> {

    public void onError(ArrayList errors);

    public void onSuccess(UserView item);
}
