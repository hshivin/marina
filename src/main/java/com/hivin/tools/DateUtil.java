package com.hivin.tools;


import com.hivin.Params.Dict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 相差m月，第d天
     *
     * @param m
     * @param d
     * @return
     */
    public static Date getBeforeMonth(int m, int d) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (d == 0) {
            m += 1;
        }
        calendar.set(Calendar.DAY_OF_MONTH, d);
        calendar.add(Calendar.MONTH, m);
        date = calendar.getTime();
        return date;
    }

    public static Integer getApiBatch() {
        String dateFormat = Dict.FormatDate.YMD;
        String thisMondayStr = DateUtil.getNextMultiWeeksDayDate(0, Calendar.MONDAY, dateFormat);

        return Integer.valueOf(thisMondayStr.replace("-", ""));
    }

    /**
     * 得到与当前时间相差d天
     *
     * @param d
     * @return
     */
    public static Date getDayDifference(int d) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, d);
        date = calendar.getTime();
        return date;
    }

    public static Date getDayDifference(Date date, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, d);
        date = calendar.getTime();
        return date;
    }

    public static Date getDifferenceDay(Date date, int d) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, d);
        date = calendar.getTime();
        return date;
    }

    public static String formatDateToStandard(Date date) {
        String format = "YYYY-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String formatDateToString(Date date, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 得到与当前时间相差小时
     *
     * @param h
     * @return
     */
    public static Date getHourDifference(int h) {
        Date date = new Date();
        getHourDifference(date, h);
        return date;
    }

    public static Date getHourDifference(String time, int h) {
        Date date = stringToDate(time, "YYYY-MM-dd HH:mm:ss");
        getHourDifference(date, h);
        return date;
    }

    public static Date getHourDifference(Date date, int h) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, h);
        date = cal.getTime();
        return date;
    }


    /**
     * 将字符串转换为date类型
     *
     * @param strDate
     * @param dateFormat
     * @return
     */
    public static Date stringToDate(String strDate, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }


    public static Date stringToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(Dict.FormatDate.YMDHMS);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将date转换为指定date格式
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date formatDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date reDate = null;
        try {
            reDate = stringToDate(sdf.format(date), dateFormat);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reDate;
    }

    /**
     * 等待时间 毫秒为基础
     *
     * @param num
     */
    public static void waitTime(int num) {
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String exchange_(String date) {
        date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
        System.out.println(date);
        return date;
    }

    /**
     * 等待时间 毫秒为基础
     *
     * @param num
     */
    public static void waitMinute(int num) {
        try {
            Thread.sleep(num * 60 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 等待时间 毫秒为基础
     *
     * @param num
     */
    public static void waitSecound(int num) {
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 按指定格式将毫秒转换为时间
     *
     * @param ms
     * @param format
     * @return
     */
    public static String ms2Time(String ms, String format) {
        SimpleDateFormat sdf2date = new SimpleDateFormat(format);
        int len = ms.length();
        if (len < 13) {
            for (int i = 0; i < 13 - len; i++) {
                ms = ms + "0";
            }
        }
        long timelong = Long.parseLong(ms);
        String result = "";
        try {
            result = sdf2date.format(timelong * 1L);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * @param weekCount  - 向前或向后推移的星期数
     * @param weekDay    - e.g. Calendar.SUNDAY
     * @param dateFormat
     * @return
     */
    public static String getNextMultiWeeksDayDate(int weekCount, int weekDay, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            weekCount = weekCount - 1;
        }
        cal.add(Calendar.WEEK_OF_YEAR, weekCount);
        cal.set(Calendar.DAY_OF_WEEK, weekDay);
        return ms2Time(String.valueOf(cal.getTimeInMillis()), dateFormat);
    }
}
