package com.pw.util.test.json;

import com.pw.util.json.PwGsonUtil;

import java.util.List;

/**
 * Created by PoemWhite on 2017/7/21.
 */
public class TestPwGsonUtil {


    public static void testJson1(){

        String json = "{\"name\":\"嚯嚯\\\"嚯\"}";

        JsonBean jsonBean = PwGsonUtil.parseJson(json, JsonBean.class);

        System.out.println(jsonBean.toString());
    }

    public static void testJson2(){

        String json = "[{\"name\":\"水水水水\",\"uuid\":\"1111\"},{\"name\":\"嚯嚯嚯\",\"uuid\":\"222\"}]";

        List<JsonBean> list = PwGsonUtil.parseJsonArray(json, JsonBean.class);

        for(int i =0;i<list.size();i++){

            JsonBean jsonBean = list.get(i);

            System.out.println(jsonBean.toString());
        }
    }

    public static void testJson3(){

        String json = "{\"uuid\":\"12345\",\"stringList\":[\"hello\",\"world\"]}";

        JsonBean jsonBean = PwGsonUtil.parseJson(json, JsonBean.class);

        System.out.println(jsonBean.toString());
    }

    public static void testJson4(){

        String json = "{\"uuid\":\"12345\",\"subList\":[{\"url\":\"www.baidu.com\"},{\"url\":\"www.google.com\"}]}";

        JsonBean jsonBean = PwGsonUtil.parseJson(json, JsonBean.class);

        System.out.println(jsonBean.toString());
    }

    public static void main(String []args){

//        testJson1();
//        testJson2();
//        testJson3();
        testJson4();
    }

}
