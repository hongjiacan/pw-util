package com.pw.util.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by PoemWhite on 2017/7/21.
 */
public class PwHttpUtil {

    public static Log logger = LogFactory.getLog(PwHttpUtil.class);

    private final static int CONNECT_TIMEOUT = 10000; // 10s
    private final static String DEFAULT_ENCODING = "UTF-8";

    public static String postData(String urlStr, String data) throws IOException {
        return postData(urlStr, data, CONNECT_TIMEOUT);
    }

    public static String postData(String urlStr, String data, int timeout) throws IOException{
        return postData(urlStr, data, null, timeout);
    }

    public static String postData(String urlStr, String data, String contentType, int timeout) throws IOException{
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);//需要输出
            conn.setDoInput(true);//需要输入
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(timeout);
            if(contentType != null){
                conn.setRequestProperty("content-type", contentType);
            }
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if(data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
            throw e;
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
    }
}
