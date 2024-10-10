package eiu.cse456.flightbookingapi.helpers;

public class MyResponse {
    private boolean isError;
    private String message;

    private Object object;

    public MyResponse() {

    }

    // error without object
    public MyResponse(boolean error, String message) {
        this.isError = error;
        this.message = message;
    }

    // error with object
    public MyResponse(boolean error, String message, Object o) {
        this.isError = error;
        this.message = message;
        this.object = o;
    }

    public void setError(boolean error) {
        this.isError = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObject(Object o) {
        this.object = o;
    }

    public boolean getError() {
        return this.isError;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getObject() {
        return this.object;
    }
}
