package asia.ienter.matching.interfaces;

import java.util.ArrayList;

import asia.ienter.matching.models.BaseView;
import asia.ienter.matching.models.UserView;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class ILoginCallback implements IBaseGetItemCallback<UserView> {

    @Override
    public void onError(ArrayList errors) {

    }

    @Override
    public void onSuccess(UserView item) {

    }
}
