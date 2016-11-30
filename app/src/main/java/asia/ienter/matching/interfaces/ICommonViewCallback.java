package asia.ienter.matching.interfaces;

import java.util.ArrayList;

import asia.ienter.matching.models.CommonView;

/**
 * Created by phamquangmanh on 11/30/16.
 */
public interface ICommonViewCallback {
    public void onError(ArrayList errors);

    public void onSuccess(CommonView item);
}
