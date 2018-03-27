package com.pw.util.result;

/**
 * Created by PoemWhite on 2018/3/22.
 */
public class RestResult implements IRestResult{

    private boolean success;
    private int code;
    private String message;

    public RestResult() {
    }

    public RestResult(boolean success) {
        this.success = success;
    }

    public RestResult(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public RestResult(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
