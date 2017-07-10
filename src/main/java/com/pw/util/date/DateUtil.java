package com.pw.util.date;

import com.pw.util.exception.PwRuntimeException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PoemWhite on 2017/7/10.
 */
public class DateUtil {

    /**
     * 日期字符串格式转化
     * @param dateString 日期字符串
     * @param curFormat 当前格式
     * @param targetFormat 目标格式
     * @return
     */
    public static String switchDateFormat(
            String dateString, String curFormat, String targetFormat) throws PwRuntimeException{

        Date date = parse(dateString, curFormat);

        return format(date, targetFormat);
    }

    /**
     * 日期格式的字符串转化为Date对象
     * @param dateString
     * @param format
     * @return
     * @throws PwRuntimeException
     */
    public static Date parse(String dateString, String format) throws PwRuntimeException{

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        Date date = null;

        try {
            date = formatter.parse(dateString);

        } catch (Exception e) {
            throw new PwRuntimeException("格式错误:dateString="+dateString+",format="+format);
        }

        return date;
    }

    /**
     * Date对象格式化
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format){

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(date.getTime());
    }
}
