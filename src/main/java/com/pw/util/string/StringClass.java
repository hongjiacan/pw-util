package com.pw.util.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by PoemWhite on 2017/4/24.
 */
public class StringClass {

    private StringClass() {

    }
    /**
     * 处理字符串,将null转为""
     * @param string
     * @return String
     */
    public static String getString(String string) {
        string = (string == null) ? "" : string;
        return string;
    }

    public static String getString(Object object){
        if(object==null){
            return "";
        }
        return String.valueOf(object);
    }

    public static String getTrimString(String string){
        return getString(string).trim();
    }

    public static String getTrimString(Object object){
        return getString(object).trim();
    }

    /**
     * 处理字符串,如果str是null则用str1代替
     *
     * @param oldString
     * @param newString
     * @return String
     */
    public static String getString(String oldString, String newString) {
        oldString = (oldString == null) ? newString : oldString;
        return oldString;
    }

    /**
     * 处理字符串,如果str是null则用str1代替
     * @param oldObject
     * @param newString
     * @return String
     */
    public static String getString(Object oldObject, String newString) {
        String theString = (oldObject == null) ? newString : String.valueOf(oldObject);
        return theString;
    }


    /**
     * 处理int类型字符串
     * @param string
     * @return int
     */
    public static int getInt(String string) {
        string = StringClass.getString(string);
        string = (string == "") ? "0" : string;
        int result;
        try {
            result = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return result;
    }

    /**
     * 处理float类型字符串
     * @param string
     * @return float
     */
    public static float getFloat(String string) {
        string = StringClass.getString(string);
        string = (string == "") ? "0" : string;
        float result = 0;
        try {
            result = Float.parseFloat(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return result;
    }

    /**
     * 处理double类型字符串
     * @param string
     * @return double
     */
    public static double getDouble(String string) {
        string = StringClass.getString(string);
        string = (string == "") ? "0" : string;
        double result = 0;
        try {
            result = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return result;
    }

    /**
     * 处理long类型字符串
     * @param string
     * @return
     */
    public static long getLong(String string) {
        string = StringClass.getString(string);
        string = (string == "") ? "0" : string;
        long result = 0;
        try {
            result = Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return result;
    }

    /**
     * 将字符串转为encoding的类型
     * @param string
     * @param encoding
     * @return String
     */
    public static String getEncodingString(String string,String encoding) {
        try{
            string = (string == null) ? "" : new String(string.getBytes("ISO-8859-1"), encoding);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 将字符串由oldEncoding转为newEncoding的类型
     * @param string
     * @param oldEncoding
     * @param newEncoding
     * @return String
     */
    public static String getEncodingString(String string,String oldEncoding,String newEncoding) {
        try{
            string = (string == null) ? "" : new String(string.getBytes(oldEncoding), newEncoding);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 将字符串转为GBK
     * @param string
     * @return String
     */
    public static String getGBKString(String string){
        try{
            string = (string == null) ? "" : new String(string.getBytes("ISO-8859-1"), "GBK");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 将字符串转为ISO8859_1
     * @param string
     * @return String
     */
    public static String getISOString(String string){
        try{
            string = (string == null) ? "" : new String(string.getBytes("GBK"), "ISO-8859-1");
        }catch(Exception e){
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 对UTF-8进行中文解码
     * @param string
     * @return
     */
    public static String getStringFromUtf8(String string){
        try {
            string= URLDecoder.decode(string,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace() ;
        }
        return string;
    }

    /**
     * 对中文进行UTF-8编码
     * @param string
     * @return String
     */
    public static String getUtf8String(String string){
        try {
            string= URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 将路径中的"\"全部替换成"/"
     *
     * @param path
     * @return String
     */
    public static String getFormatPath(String path) {
        path=StringClass.getTrimString(path);
        if(path.equals("")){
            return path;
        }
//    	String fileFlag="file:/";
        path = path.replaceAll("\\\\", "/");
        path = path.replaceAll("/+", "/");
//        if(path.startsWith("file://")){
//        	path=path.replaceFirst("file:/", "file://");
//        	path=fileFlag+path.substring("file://".length());
//        }
        return path;
    }


    /**
     * 判断字符串中是否包含有指定的单词
     *
     * @param longString
     *            将要被检查的字符串
     * @param shortString
     *            单词
     * @return boolean
     */
    public static boolean isWordExist(String longString, String shortString) {
        StringTokenizer tokenizer = new StringTokenizer(longString);
        while (tokenizer.hasMoreTokens()) {
            String tempString = tokenizer.nextToken();
            if (shortString.equals(tempString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回一个字符串中包含的单词列表
     *
     * @param string
     * @return ArrayList
     */
    public static ArrayList getWordList(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string);
        ArrayList list = new ArrayList();

        while (tokenizer.hasMoreTokens()) {
            String tempString = tokenizer.nextToken();
            list.add(tempString);
        }
        return list;
    }

    public static String trimHtml2Txt(String html){

        html = html.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");//去掉head
        html = html.replaceAll("\\<!--[\\s\\S]*?-->", "");//去掉注释
        html = html.replaceAll("\\<![\\s\\S]*?>", "");
        html = html.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");//去掉样式
        html = html.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");//去掉js
        html = html.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");//去掉word标签
        html = html.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");
        html = html.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)", "");
        html = html.replaceAll("\\\r\n|\n|\r", " ");//去掉换行
        html = html.replaceAll("\\<br[^>]*>(?i)", "\n");

        return html.trim();
    }

    public static boolean isInt(String string){

        try{
            Integer.parseInt(string);
            return true;
        }catch(NumberFormatException e){

        }
        return false;
    }

    public static boolean isDouble(String string){

        try{
            Double.parseDouble(string);
            return true;
        }catch(NumberFormatException e){

        }

        return false;
    }


    public static void main(String[] args){

    }
}
