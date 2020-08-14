package com.wh.spring.admin.util;

import java.util.List;

/**
 * Created by Joy on 16/9/1.
 */
public class MathUtils {


    public static float point2(float f) {
        return pointN(f, 2);
    }

    public static double point2(double f) {
        return pointN(f, 2);
    }

    public static float point3(float f) {
        return pointN(f, 3);
    }

    public static double point3(double f) {
        return pointN(f, 3);
    }

    public static double pointN(double value, int n) {
        int sq = (int) Math.pow(10, n);
        return (double) (Math.round(value * sq)) / sq;
    }

    public static float pointN(float value, int n) {
        int sq = (int) Math.pow(10, n);
        return (float) (Math.round(value * sq)) / sq;
    }

    public static float parseFloat(Object s) {
        try {
            return Float.parseFloat(s.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static double parseDouble(Object s) {
        try {
            return Double.parseDouble(s.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static byte parseByte(Object s) {
        try {
            return Byte.parseByte(s.toString());
        } catch (Exception e) {
            return 0;
        }
    }


    public static long parseLong(Object s) {
        try {
            return Long.parseLong(s.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static int parseInt(Object s) {
        try {
            return Integer.parseInt(s.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 求平均值
     *
     * @param list 数字列表
     * @return
     */
    public static double avg(List<Double> list) {
        double sum = 0;
        for (Double d : list) {
            if (d == null)
                continue;
            sum += d;
        }
        return sum / list.size();
    }


    public static boolean isUp(List<Double> list, double d) {
        double avg = avg(list);
        return !(avg >= d);
    }

    public static double standardDiviation(List<Double> x) {
        int m = x.size();
        double sum = 0;
        for (Double aDouble : x) {//求和
            sum += aDouble;
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (Double aDouble : x) {//求方差
            dVar += (aDouble - dAve) * (aDouble - dAve);
        }
        return Math.sqrt(dVar / (m - 1));
//        return Math.sqrt(dVar/m);
    }

    public static boolean isInteger(String value) {
        return value.indexOf(".") <= 0;
    }

}
