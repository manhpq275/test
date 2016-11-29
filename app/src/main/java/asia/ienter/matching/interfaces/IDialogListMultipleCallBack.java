package asia.ienter.matching.interfaces;

import java.util.ArrayList;

/**
 * Created by phamquangmanh on 11/10/16.
 */
public interface IDialogListMultipleCallBack {
    void onClickBack();
    void onClickItem(ArrayList<Integer> position);
    void onClickDone();
}
