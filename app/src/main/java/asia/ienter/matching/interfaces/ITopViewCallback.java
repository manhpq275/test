package asia.ienter.matching.interfaces;

import asia.ienter.matching.models.TopView;

/**
 * Created by phamquangmanh on 9/22/16.
 */
public interface ITopViewCallback {
    void OnItemClickRecycleView(TopView topView);
    void OnItemClickLike(int position);
}
