package com.pw.util.test;

import com.pw.util.math.PwMathUtil;
import com.pw.util.secret.bcrypt.BCrypt;
import com.pw.util.secret.encrypt.PwBCryptUtil;

import java.math.BigDecimal;

/**
 * Created by PoemWhite on 2017/5/11.
 */
public class Test {

    public static void main(String []args){

//        String date = PwDateUtil.timeStamp2Date("1519862400", "yyyy-MM-dd");
//        System.out.print(date);

        BigDecimal result = PwMathUtil.sub("-1","2");

        if(result.intValue() > 0){

        }

        System.out.println(result.toString());

        String a= "1";

        String encrypt = PwBCryptUtil.encrypt(a);
        String encrypt2 = PwBCryptUtil.encrypt(a);


        System.out.println(encrypt);
        System.out.println(encrypt2);

        System.out.println(BCrypt.checkpw(a, encrypt));
        System.out.println(BCrypt.checkpw(a, encrypt2));
    }
}
