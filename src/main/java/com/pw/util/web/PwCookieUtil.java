package com.pw.util.web;

import com.pw.util.string.PwStringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * CookieUtil
 * Created by PoemWhite on 2017/10/27.
 */
public class PwCookieUtil {

    /**
     * 根据名字获取cookie的值
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValueByName(HttpServletRequest request, String name){

        Cookie cookie = getCookieByName(request,name);
        if(cookie!=null){
            return PwStringUtil.trimToNull(cookie.getValue());
        }
        return null;
    }

    /**
     * 根据名字获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name){

        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)){
            return (Cookie) cookieMap.get(name);
        }
        return null;
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request){

        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies){
            for (Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
