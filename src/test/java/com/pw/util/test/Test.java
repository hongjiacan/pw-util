package com.pw.util.test;

import com.pw.util.date.PwDateUtil;

/**
 * Created by PoemWhite on 2017/5/11.
 */
public class Test {

    public static void main(String []args){
        String dateString = "20170710165438";

        System.out.println(PwDateUtil.switchDateFormat(dateString, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
    }
}
