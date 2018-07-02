package com.pw.util.secret.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by PoemWhite on 2018/4/8.
 */
public class PwShaUtil {

    public static String encrypt(String origin){
        return DigestUtils.sha512Hex(origin);
    }
}
