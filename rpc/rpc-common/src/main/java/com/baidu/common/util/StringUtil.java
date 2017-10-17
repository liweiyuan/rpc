package com.baidu.common.util;

/**
 * Created by tingyun on 2017/10/17.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 分割固定格式的字符串
     */
    public static String[] split(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator);
    }
}
