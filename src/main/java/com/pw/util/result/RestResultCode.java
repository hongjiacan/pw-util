package com.pw.util.result;

/**
 * Created by PoemWhite on 2018/3/22.
 */
public class RestResultCode {

    public static final int Success = 1;
    public static final int Fail = 0;//业务错误

    public static final int Fail_Unauthorized = 401;//表示用户没有权限（令牌、用户名、密码错误） 同401
    public static final int Fail_Forbidden = 403;//表示用户得到授权，但是访问是被禁止的 同403
    public static final int Fail_NotFound = 404;//用户发出的请求针对的是不存在的记录，服务器没有进行操作 同404
    public static final int Fail_INTERNAL_SERVER_ERROR = 500;//服务器内部错误 同500
}
