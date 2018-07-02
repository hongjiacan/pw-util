package com.pw.util.secret.encrypt;

import com.pw.util.secret.bcrypt.BCrypt;

/**
 * refer to http://www.mindrot.org/projects/jBCrypt/
 * Created by PoemWhite on 2018/4/8.
 */
public class PwBCryptUtil {

    public static String encrypt(String origin) {

        return BCrypt.hashpw(origin, BCrypt.gensalt());
    }

    public static Boolean check(String origin, String encrypt){

        return BCrypt.checkpw(origin, encrypt);
    }
}
