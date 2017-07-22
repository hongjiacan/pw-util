package com.pw.util.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoemWhite on 2017/7/21.
 */
public class PwGsonUtil {


    /**
     * 将object转为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){

        return new Gson().toJson(object);
    }


    /**
     * 将Json数据解析成相应的映射对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> clazz) {

        return new Gson().fromJson(json, clazz);
    }

    /**
     * 将Json数组解析成相应的映射对象列表
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArray(String json, Class<T> clazz) {

        List<T> lst =  new ArrayList<T>();

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();

        for(final JsonElement elem : array){

            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }

}
