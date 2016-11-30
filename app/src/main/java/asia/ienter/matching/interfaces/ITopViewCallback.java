package asia.ienter.matching.interfaces;

import android.view.View;

import asia.ienter.matching.models.UserView;

/**
 * Created by phamquangmanh on 9/22/16.
 */
public interface ITopViewCallback {
    void OnItemClickRecycleView(UserView topView, int position);
    boolean OnItemClickLike(View btnLike, int position);
}
