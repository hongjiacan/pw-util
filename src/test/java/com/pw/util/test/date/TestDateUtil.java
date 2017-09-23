package com.pw.util.test.date;

import com.pw.util.date.PwDateUtil;

import java.io.IOException;

/**
 * Created by PoemWhite on 2017/9/23.
 */
public class TestDateUtil {

    public static void main(String []args) throws IOException {

        String time = "20081231080059";
        String newTime = "";

        newTime = PwDateUtil.switchDateFormat(time, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");

        System.out.println(newTime);
    }
}
