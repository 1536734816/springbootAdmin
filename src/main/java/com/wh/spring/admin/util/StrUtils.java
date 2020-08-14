package com.wh.spring.admin.util;

import java.util.Random;
import java.util.UUID;

public class StrUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                // 判断字符是否为空格、制表符、tab
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }


    /**
     * 获取后缀
     *
     * @param filepath filepath 文件全路径
     */
    public static String getSuffiex(String filepath) {
        if (isBlank(filepath)) {
            return null;
        }
        int index = filepath.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        return filepath.substring(index + 1);
    }

}
