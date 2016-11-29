package asia.ienter.matching.models;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public class ErrorView {
    private String msg;
    private int status;

    public ErrorView(String msg,int status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "Error{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
