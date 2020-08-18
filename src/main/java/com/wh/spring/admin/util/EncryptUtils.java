package com.wh.spring.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class EncryptUtils {

    private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);


    /**
     * sha256加密字符串
     *
     * @param str 源数据
     * @return 加密后的字符串
     */
    public static String sha256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encodeStr.substring(0,50);
    }

    /**
     * 加密密码
     *
     * @param password 密码
     * @param salt     盐
     * @return 加密过后的密码
     */
    public static String entryptPassword(String password, String salt) {
        String pre = salt.substring(3);
        String end = salt.substring(0, 3);
        return sha256(pre + password + end).substring(0, 50);
    }


    /**
     * 二进制转成字符串
     *
     * @param b 二进制数据
     * @return 转化成的字符串
     */
    private static String byte2Hex(byte[] b) //二进制转字符串
    {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (byte value : b) {
            stmp = (Integer.toHexString(value & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString();
    }

    /**
     * 随机盐
     *
     * @return {@link String}
     */
    public static String salt() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        return sb.substring(0, 6);
    }
}
