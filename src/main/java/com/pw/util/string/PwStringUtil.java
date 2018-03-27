package com.pw.util.string;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by PoemWhite on 2017/4/24.
 */
public class PwStringUtil {

    public static Log logger = LogFactory.getLog(PwStringUtil.class);

    private PwStringUtil() {

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
        string = PwStringUtil.getString(string);
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
        string = PwStringUtil.getString(string);
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
        string = PwStringUtil.getString(string);
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
        string = PwStringUtil.getString(string);
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
            logger.error(e);
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
            logger.error(e);
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
            logger.error(e);
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
            logger.error(e);
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
            logger.error(e);
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
            logger.error(e);
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
        path= PwStringUtil.getTrimString(path);
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

    public static String getFormatpathNoFileStart(String filename1){
        filename1 = getFormatPath(filename1);
        if(filename1.equals("")){
            return "";
        }
        if(filename1.startsWith("file:/")){
            filename1=filename1.replaceFirst("file:/", "");
        }
        return filename1;
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

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInt(value) || isDouble(value);
    }

    /**
     * 取得longstring中从最后一个str开始到longstring结束的字符串
     *
     * @param longstring
     * @param shortstring
     * @return String  如果longstring不包含shortstring没有则返回""
     */
    public static String getLastString(String longstring, String shortstring) {
        int i = longstring.indexOf(shortstring);
        if (i > -1){
            return (longstring.substring((longstring.lastIndexOf(shortstring)) + 1,
                    longstring.length()));
        }else{
            return "";
        }
    }

    /**
     * 取得longstring中从开始到最后一个str字符串
     *
     * @param longstring
     * @param shortstring
     * @return String 如果longstring不包含shortstring则返回longstring
     */
    public static String getPreString(String longstring, String shortstring) {
        int i = 0;
        try{
            i = longstring.indexOf(shortstring);
        }catch(Exception e){
            return (longstring);
        }
        if (i > -1) {
            String newstring="";
            try {
                newstring=longstring.substring(0, longstring.lastIndexOf(shortstring));
            }catch(Exception e) {
                return longstring;
            }
            return newstring;
        }else {
            return longstring;
        }
    }

    // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if a CharSequence is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the CharSequence.
     * That functionality is available in isBlank().</p>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    // Trim
    //-----------------------------------------------------------------------
    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String, handling {@code null} by returning
     * {@code null}.</p>
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     *
     * <p>To trim your choice of characters, use the
     *
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("abc")         = "abc"
     * StringUtils.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed string, {@code null} if null String input
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String returning {@code null} if the String is
     * empty ("") after the trim or if it is {@code null}.
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     *
     * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed String,
     *  {@code null} if only chars &lt;= 32, empty or null String input
     * @since 2.0
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }


    public static void main(String[] args){

        String aa = null;
        String bb = "";
        String cc = " ";

        System.out.println(isEmpty(aa));
        System.out.println(isEmpty(bb));
        System.out.println(isEmpty(cc));
    }
}
