package com.pw.util.test.http;

import com.pw.util.http.PwHttpUtil;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by PoemWhite on 2017/7/21.
 */
public class TestPwHttpUtil {

    public static void main(String []args) throws IOException {

        final String url = "http://localhost:8080/lab/test3.action";

        String param = "uuid="+ URLEncoder.encode("&&水水水水123","UTF-8")+"&name=水水水水";

        String responseXml = PwHttpUtil.postData(url, param);

        System.out.println(responseXml);

    }
}
