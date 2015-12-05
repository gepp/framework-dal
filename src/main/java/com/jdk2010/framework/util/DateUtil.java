package com.jdk2010.framework.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    /** 定义常量 **/
    public static final String DATE_SHORT_STR = "yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";

    /**
     * 使用预设格式提取字符串日期
     * 
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     * 
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据日期获取格式化日期
     * 
     * @param d
     * @param formatStr
     * @return
     */
    public static String formatDateTime(Date d, String formatStr) {
        return new SimpleDateFormat(formatStr).format(d);
    }

    /**
     * 两个时间比较
     * 
     * @param date
     * @return
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }

    /**
     * 两个时间比较(时间戳比较)
     * 
     * @param date
     * @return
     */
    public static int compareDateWithNow(long date1) {
        long date2 = dateToUnixTimestamp();
        if (date1 > date2) {
            return 1;
        } else if (date1 < date2) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 获取系统当前时间
     * 
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间
     * 
     * @return
     */
    public static String getNowTime(String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(new Date());
    }

    /**
     * 获取系统短时间201310
     * 
     * @return
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_SHORT_STR);
        return df.format(new Date());
    }

    /**
     * 将指定的日期转换成Unix时间戳
     * 
     * @param String date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     * 
     * @param String date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     * 
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }

    /**
     * 将Unix时间戳转换成日期
     * 
     * @param long timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    /**
     * 获取几天前的时间
     * 
     * @param days
     * @return
     */
    public static String previousDay(int days, String formatStr) {
        Date date = new Date(System.currentTimeMillis() - days * 3600000L * 24L);
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 获取几小时前的时间
     * 
     * @param days
     * @return
     */
    public static String previousHour(int hours, String formatStr) {
        Date date = new Date(System.currentTimeMillis() - hours * 3600000L);
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 获取几分前的时间
     * 
     * @param days
     * @return
     */
    public static String previousMinute(int hours, String formatStr) {
        Date date = new Date(System.currentTimeMillis() - hours * 60000L);
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 获取几秒前的时间
     * 
     * @param second
     * @param formatStr
     * @return
     */
    public static String previousSecond(int seconds, String formatStr) {
        Date date = new Date(System.currentTimeMillis() - seconds * 1000L);
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 计算两个日期相差多少天
     * 
     * @param d1 Date
     * @param d2 Date
     * @return long
     */
    public static long between(Date d1, Date d2) {
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal day = new BigDecimal(24L * 60 * 60 * 1000);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(day);
        return timeQuantum.longValue();
    }

    public static long between(String s1, String s2) {
        Date d1 = parse(s1, "yyyy-MM-dd HH:mm:ss");
        Date d2 = parse(s2, "yyyy-MM-dd HH:mm:ss");
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal day = new BigDecimal(24L * 60 * 60 * 1000);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(day);
        return timeQuantum.longValue();
    }

    /**
     * 计算两个日期相差多少小时
     * 
     * @param d1 Date
     * @param d2 Date
     * @return long
     */
    public static long betweenHour(Date d1, Date d2) {
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal hour = new BigDecimal(60L * 60 * 1000);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(hour);
        return timeQuantum.longValue();
    }

    public static long betweenHour(String s1, String s2) {
        Date d1 = parse(s1, "yyyy-MM-dd HH:mm:ss");
        Date d2 = parse(s2, "yyyy-MM-dd HH:mm:ss");
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal hour = new BigDecimal(60L * 60 * 1000);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(hour);
        return timeQuantum.longValue();
    }

    /**
     * 计算两个日期相差多少分钟
     * 
     * @param d1 Date
     * @param d2 Date
     * @return long
     */
    public static long betweenMinute(Date d1, Date d2) {
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal minute = new BigDecimal(60L * 1000);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(minute);
        return timeQuantum.longValue();
    }

    public static long betweenMinute(String s1, String s2) {
        Date d1 = parse(s1, "yyyy-MM-dd HH:mm:ss");
        Date d2 = parse(s2, "yyyy-MM-dd HH:mm:ss");
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal minute = new BigDecimal(60L * 1000);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(minute);
        return timeQuantum.longValue();
    }

    /**
     * 计算两个日期相差多少秒
     * 
     * @param d1 Date
     * @param d2 Date
     * @return long
     */
    public static long betweenSecond(Date d1, Date d2) {
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal second = new BigDecimal(1000L);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(second);
        return timeQuantum.longValue();
    }

    public static long betweenSecond(String s1, String s2) {
        Date d1 = parse(s1, "yyyy-MM-dd HH:mm:ss");
        Date d2 = parse(s2, "yyyy-MM-dd HH:mm:ss");
        BigDecimal timeQuantum = new BigDecimal(0);
        BigDecimal bd1 = new BigDecimal(d1.getTime());
        BigDecimal bd2 = new BigDecimal(d2.getTime());
        BigDecimal second = new BigDecimal(1000L);
        timeQuantum = bd1.subtract(bd2).divideToIntegralValue(second);
        return timeQuantum.longValue();
    }

    /**
     * 获取今年年份
     * 
     * @return
     */
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * 
     * @return
     */
    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当月第一天
     * 
     * @return
     */
    public static String getFirstDayOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return formatDateTime(c.getTime(), DATE_FULL_STR);
    }

    /**
     * 获取上月第一天
     * 
     * @return
     */
    public static Date getPreviousMonthBegin() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Calendar dateToCalender(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    // 根据生日获取生肖
    public static String getZodica(Date birthday) {
        return Zodica.date2Zodica(dateToCalender(birthday));
    }

    // 根据生日获取星座
    public static String getConstellation(Date birthday) {
        return Constellation.date2Constellation(dateToCalender(birthday));
    }

    /**
     * 返回时分秒 getHourMinute("2013-12-17 10:30:20",formatDateTime(new Date(),DATE_FULL_STR))结果5小时48分
     * 
     * @param start_time
     * @param end_time
     * @return
     */
    public static String getHourMinute(String start_time, String end_time) {
        String result = "";
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date begin = dfs.parse(start_time);
            Date end = dfs.parse(end_time);
            long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

            long day1 = between / (24 * 3600);
            long hour1 = between % (24 * 3600) / 3600;
            long minute1 = between % 3600 / 60;
            if (day1 > 3) {
                result = "3天以上";
            } else {
                String day = day1 <= 0 ? "" : day1 + "天";
                String hour = hour1 <= 0 ? "" : hour1 + "小时";
                String minute = minute1 <= 0 ? "" : minute1 + "分";
                result = day + hour + minute;
            }

            // //System.out.println(result);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public static String toFormatRQ(String time) {
        String shortstring = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (date == null)
            return shortstring;
        long now = Calendar.getInstance().getTimeInMillis();
        long deltime = (now - date.getTime()) / 1000;
        if (deltime > 365 * 24 * 60 * 60) {
            shortstring = (int) (deltime / (365 * 24 * 60 * 60)) + "年前";
        } else if (deltime > 24 * 60 * 60) {
            shortstring = (int) (deltime / (24 * 60 * 60)) + "天前";
        } else if (deltime > 60 * 60) {
            shortstring = (int) (deltime / (60 * 60)) + "小时前";
        } else if (deltime > 60) {
            shortstring = (int) (deltime / (60)) + "分钟前";
        } else if (deltime > 1) {
            shortstring = deltime + "秒前";
        } else {
            shortstring = "1秒前";
        }
        return shortstring;
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static void main(String[] args) {
        System.out.println();
    }

}

// 生肖
class Zodica {
    private static final String[] zodiacList = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };

    public static String date2Zodica(Calendar time) {
        return zodiacList[time.get(Calendar.YEAR) % 12];
    }
}

// 星座
class Constellation {
    private static final String[] constellationList = { "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "魔羯座" };
    private static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };

    public static String date2Constellation(Calendar time) {
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationList[month];
        }
        // default to return 魔羯
        return constellationList[11];
    }
}
