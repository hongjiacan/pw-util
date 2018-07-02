package com.pw.util.date;

import com.pw.util.exception.PwRuntimeException;
import com.pw.util.string.PwStringUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PoemWhite on 2017/7/10.
 */
public class PwDateUtil {

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

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String timeStamp2Date(String timestampString, String formats) {
        if (PwStringUtil.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 对 日期字符串 进行月份变更计算
     * @param dateString yyyy-MM-dd
     * @param months
     * @return
     */
    public static String addDayByMonth(String dateString, int months){

        String format = "yyyy-MM-dd";

        Calendar calendar = Calendar.getInstance();

        Date date = parse(dateString,format);

        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);

        return format(calendar.getTime(),format);
    }

    /**
     * 对 日期字符串 进行天数变更计算
     * @param dateString yyyy-MM-dd
     * @param days
     * @return
     */
    public static String addDayByDay(String dateString, int days){

        String format = "yyyy-MM-dd";

        Calendar calendar = Calendar.getInstance();

        Date date = parse(dateString,format);

        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);

        return format(calendar.getTime(),format);
    }

    /**
     * 对 日期字符串 进行小时变更计算
     * @param dateString yyyy-MM-dd HH:mm:ss
     * @param hours
     * @return
     */
    public static String addDateByHour(String dateString, int hours){

        String format = "yyyy-MM-dd HH:mm:ss";

        Calendar calendar = Calendar.getInstance();

        Date date = parse(dateString,format);

        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);

        return format(calendar.getTime(),format);
    }

    /**
     * 对 日期字符串 进行分钟变更计算
     * @param dateString
     * @param minutes
     * @return
     */
    public static String addDateByMinute(String dateString, int minutes){

        String format = "yyyy-MM-dd HH:mm:ss";

        Calendar calendar = Calendar.getInstance();

        Date date = parse(dateString,format);

        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);

        return format(calendar.getTime(),format);
    }

    /**
     * 对日期字符串进行比较
     * @param before
     * @param after
     * @param format
     * @return
     */
    public static boolean before(String before, String after, String format){

        Date beforeDate = parse(before,format);
        Date afterDate = parse(after,format);

        return beforeDate.before(afterDate);
    }

    /**
     * 对日期字符串进行比较(默认yyyy-MM-dd HH:mm:ss格式)
     * @param before
     * @param after
     * @return
     */
    public static boolean before(String before, String after){

        String format = "yyyy-MM-dd HH:mm:ss";

        return before(before,after,format);
    }


    /**
     * 返回某一天是周几
     * @param dateString
     * @param format
     * @return
     */
    public static String getWeekDay(String dateString, String format){

        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

        int dayOfWeek = getDayOfWeek(dateString,format);

        dayOfWeek--;

        return weekDays[dayOfWeek];
    }

    /**
     * 某一天是周几
     * @param dateString
     * @param format
     * @return 从周日开始算起(周日返回1,周一返回2,依次类推)
     */
    public static int getDayOfWeek(String dateString, String format){

        Date date = parse(dateString,format);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 所在月的天数
     * @param dateString
     * @param format
     * @return
     */
    public static int getDaysOfMonth(String dateString, String format) {

        Date date = parse(dateString,format);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);//获取年份

        int month = calendar.get(Calendar.MONTH);//获取月份

        return getDaysOfMonth(year, month);
    }

    /**
     * 取得月份的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(year, month, 1);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算小时差
     * @param before
     * @param after
     * @return
     */
    public static double timeCalculateHours(Date before, Date after) {
        double hour = 0;
        double millisecond = after.getTime() - before.getTime();
        hour = millisecond / (60 * 60 * 1000);
        return hour;
    }

    /**
     * 计算分钟差
     * @param before
     * @param after
     * @return
     */
    public static double timeCalculateMinutes(Date before, Date after) {
        double minutes = 0;
        double millisecond = after.getTime() - before.getTime();
        minutes = millisecond / (60 * 1000);
        return minutes;
    }

    /**
     * 计算秒差
     * @param before
     * @param after
     * @return
     */
    public static double timeCalculateSeconds(Date before, Date after) {
        double millisecond = after.getTime() - before.getTime();
        double seconds = millisecond / 1000;
        return seconds;
    }

    /**
     * 计算分钟差
     * @param dateStringBefore
     * @param dateStringAfter
     * @param format
     * @return
     */
    public static double timeCalculateMinutes(String dateStringBefore, String dateStringAfter, String format) {

        Date before = parse(dateStringBefore,format);

        Date after = parse(dateStringAfter,format);

        return timeCalculateMinutes(before, after);
    }
}
