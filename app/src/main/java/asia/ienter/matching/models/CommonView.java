package asia.ienter.matching.models;

/**
 * Created by phamquangmanh on 11/14/16.
 */
public class CommonView extends BaseView {

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    int status;
    String msg;
}
