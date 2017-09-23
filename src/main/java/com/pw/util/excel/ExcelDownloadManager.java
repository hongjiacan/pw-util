package com.pw.util.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class ExcelDownloadManager {

    private Map<String, Map<String, Integer>> processInfos = new HashMap();
    private static ExcelDownloadManager manager;

    private ExcelDownloadManager() {

    }

    public static ExcelDownloadManager getInstance() {
        if (manager == null) {
            manager = new ExcelDownloadManager();
        }
        return manager;
    }

    public Map<String, Integer> get(String id) {
        return processInfos.get(id);
    }

    public void put(String id, Map<String, Integer> info) {
        processInfos.put(id, info);
    }

    public void remove(String id) {
        processInfos.remove(id);
    }

    public void destroy() {
        manager = null;
    }
}
