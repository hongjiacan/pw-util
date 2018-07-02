package com.pw.util.date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by PoemWhite on 2017/9/6.
 *
 */
public class PwWebDateTimeUtil {

    public static Log logger = LogFactory.getLog(PwWebDateTimeUtil.class);

    /**
     * 计算time2-time1得到的天数差
     *
     * @param time1
     *            String格式日期
     * @param time2
     *            String格式日期
     * @return int
     */
    public float timeCalculate(String time1, String time2) {
        time1 = getTime(time1);
        time2 = getTime(time2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        float day = 0;
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = format.parse(time1);
            date2 = format.parse(time2);
            long millisecond = date2.getTime() - date1.getTime();
            day = millisecond / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            logger.error(e);
        }
        return (day);
    }

    /**
     * 计算time2-time1的天数差
     *
     * @param time1
     *            Date格式日期
     * @param time2
     *            Date格式日期
     * @return double
     */
    public float timeCalculate(Date time1, Date time2) {
        float day = 0;
        try {
            Date date1, date2;
            date1 = time1;
            date2 = time2;
            long millisecond = date2.getTime() - date1.getTime();
            day = millisecond / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            logger.error(e);
        }
        return (day);
    }

    /**
     * 计算小时差
     *
     * @param time1
     * @param time2
     * @return double
     */
    public double timeCalculateHour(String time1, String time2) {
        time1 = getTime(time1);
        time2 = getTime(time2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        double hour = 0;
        try {
            Date date1, date2;
            date1 = format.parse(time1);
            date2 = format.parse(time2);
            double millisecond = date2.getTime() - date1.getTime();
            hour = millisecond / (60 * 60 * 1000);
        } catch (ParseException e) {
            logger.error(e);
        }
        return hour;
    }



    private String getTime(String time) {
        if (this.getString(time).split(" ").length < 2) {
            time = time + " 00:00:00";
        }
        return time;
    }


    public String getTrimString(String string) {
        if (string == null) {
            return string;
        }
        return string.trim();
    }

    /**
     * 处理字符串,将null转为""
     *
     * @param string
     * @return String
     */
    public String getString(String string) {
        string = (string == null || string.equals("null")) ? "" : string;
        return getTrimString(string);
    }
}
