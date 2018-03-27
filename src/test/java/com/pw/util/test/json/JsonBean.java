package com.pw.util.test.json;

import java.util.List;

/**
 * Created by PoemWhite on 2017/7/22.
 */
public class JsonBean {

    private double db;
    private String uuid;
    private String name;
    private List<String> stringList;
    private List<JsonSubBean> subList;

    public static class JsonSubBean{

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "JsonSubBean{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    public double getDb() {
        return db;
    }

    public void setDb(double db) {
        this.db = db;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<JsonSubBean> getSubList() {
        return subList;
    }

    public void setSubList(List<JsonSubBean> subList) {
        this.subList = subList;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "db=" + db +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", stringList=" + stringList +
                ", subList=" + subList +
                '}';
    }
}
