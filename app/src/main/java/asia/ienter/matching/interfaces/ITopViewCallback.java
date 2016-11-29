package asia.ienter.matching.interfaces;

import asia.ienter.matching.models.UserView;

/**
 * Created by phamquangmanh on 9/22/16.
 */
public interface ITopViewCallback {
    void OnItemClickRecycleView(UserView topView);
    boolean OnItemClickLike(int position);
}
