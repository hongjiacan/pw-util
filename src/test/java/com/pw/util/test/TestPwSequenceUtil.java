package com.pw.util.test;

import com.pw.util.uuid.PwSequenceUtil;

/**
 * Created by PoemWhite on 2017/7/21.
 */
public class TestPwSequenceUtil {

    public static void main(String []args){

        System.out.println(PwSequenceUtil.uuid());
        System.out.println(PwSequenceUtil.uuid2());

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        System.out.println(PwSequenceUtil.uuid16());

                        //休眠两秒
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }
}
