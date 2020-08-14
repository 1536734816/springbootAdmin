package com.wh.spring.admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Joy on 2019/8/19.
 */
public class DateUtils {


    public static Date addHour(Date date, int hour) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour); //把日期往后增加一小时.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static Date addDay(Date date, int day) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);//把日期往后增加一月.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);//把日期往后增加一月.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static Date addMillisecond(Date date, int millisecond) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, millisecond);//以毫秒为单位修改日期.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int second) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);//以秒为单位修改日期.整数往后推,负数往前移动
        return calendar.getTime();
    }

    public static boolean isNewDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.HOUR_OF_DAY);
        return 0 == day;
    }

    public static boolean isNewMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return 1 == day;
    }

    public static boolean isNewYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        return 1 == day && month == 0;
    }

    public static String dateToString(Date time, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }


    public static Date stringToDate(String time, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateStringToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        long ts = 0;
        try {
            date = simpleDateFormat.parse(s);
            ts = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ts;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(Date date) {

        long ts = date.getTime();
        return ts;
    }


    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);

        return date;
    }

    public static Date stampToDate(long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        return date;
    }

    public static Date initHourByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();

    }

    public static Date initDayByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();

    }


    //获取当前月月初
    public static Date initMonthByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date initYearByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean isSameDay(Date day1, Date day2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(day1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(day2);

        return calendar1.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar1.get(Calendar.DAY_OF_YEAR);
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

}
