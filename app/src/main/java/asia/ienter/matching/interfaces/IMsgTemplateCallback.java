package asia.ienter.matching.interfaces;

/**
 * Created by phamquangmanh on 9/29/16.
 */
public interface IMsgTemplateCallback {
    void onClickItem(int position);
    void onAcceptCallback() ;

    void onEditCallback() ;

    void onCancelCallback();
}
