package com.pw.util.test.secret;

import com.pw.util.secret.bcrypt.BCrypt;
import com.pw.util.secret.encrypt.PwBCryptUtil;
import com.pw.util.secret.encrypt.PwMd5Util;

/**
 * Created by PoemWhite on 2017/8/2.
 */
public class TestEncryptUtil {

    public static void main(String []args){

        String a= "yuren";

        String encrypt = PwBCryptUtil.encrypt(a);
        String encrypt2 = PwBCryptUtil.encrypt(a);


        System.out.println(encrypt);
        System.out.println(encrypt2);

        System.out.println(PwBCryptUtil.check(a, encrypt));
        System.out.println(PwBCryptUtil.check(a, encrypt2));
    }
}
