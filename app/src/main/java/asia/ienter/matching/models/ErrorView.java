package asia.ienter.matching.models;

/**
 * Created by phamquangmanh on 10/27/16.
 */
public class ErrorView {
    private String field;
    private int errorCode;

    public ErrorView(String field, int errorCode) {
        this.field = field;
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public int getErrorCode(){
        return errorCode;
    }

    @Override
    public String toString() {
        return "Error{" +
                "field='" + field + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
