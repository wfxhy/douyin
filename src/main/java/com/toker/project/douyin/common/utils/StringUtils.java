package com.toker.project.douyin.common.utils;

import org.springframework.lang.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/17 12:00
 * @modified mdmbct
 * @since 1.0
 */
public class StringUtils {


    public static String utf8Encode(String text) {
        try {
            return URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isEmpty(@Nullable Object str) {
        return (str == null || "".equals(str));
    }
}
