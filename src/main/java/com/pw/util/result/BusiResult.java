package com.pw.util.result;

import com.pw.util.support.constant.PwConstant;

/**
 * Created by PoemWhite on 2017/8/2.
 */
public class BusiResult implements IBusiResult{

    /**
     * 结果
     * 0 失败
     * 1 成功
     */
    private int code;
    private String message;
    private Object param;


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
    public Object getParam() {
        return param;
    }
    public void setParam(Object param) {
        this.param = param;
    }

    public BusiResult() {
        super();
    }

    public BusiResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BusiResult(int code, String message, Object param) {
        super();
        this.code = code;
        this.message = message;
        this.param = param;
    }

    public boolean isSuccess(){

        if(PwConstant.SUCCESS == getCode()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BusiResult [code=" + code + ", message=" + message + ", param=" + param + "]";
    }
}
