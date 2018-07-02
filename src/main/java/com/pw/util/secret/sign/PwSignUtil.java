package com.pw.util.secret.sign;

import com.pw.util.secret.encrypt.PwMd5Util;

import java.util.*;

/**
 * Created by PoemWhite on 2017/8/2.
 */
public class PwSignUtil {

    /**
     * 创建签名
     * @param sortedMap
     * @param key
     * @return
     */
    public static String createSign(SortedMap<Object, Object> sortedMap, String key) {

        StringBuffer sb = new StringBuffer();
        Set es = sortedMap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = PwMd5Util.encrypt(sb.toString(), SignConfig.characterEncoding).toUpperCase();

        return sign;
    }

    /**
     * 创建签名
     * @param sortedMap
     * @return
     */
    public static String createSign(SortedMap<Object, Object> sortedMap) {

        return createSign(sortedMap, SignConfig.API_KEY);
    }

    /**
     * 创建签名
     * @param map
     * @param key
     * @return
     */
    public static String createSign(Map<Object, Object> map, String key) {

        SortedMap<Object,Object> sortedMap = new TreeMap<Object,Object>();

        sortedMap.putAll(map);

        return createSign(sortedMap, key);
    }

    /**
     * 创建签名
     * @param map
     * @return
     */
    public static String createSign(Map<Object, Object> map) {

        return createSign(map, SignConfig.API_KEY);
    }
}
