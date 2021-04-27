/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.base;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串相关工具类
 *
 * @author zhengpc
 * @version $Id: StringSplitUtils.java, v 0.1 2017/11/21 下午1:16 zhengpc Exp $
 * @date 2017/11/21
 */
public class StringSplitUtils {

    /**
     * 字符串转map
     *
     * @param strValue
     * @param spr
     * @param kvSpr
     * @return
     */
    public static Map<String, String> stringToMap(String strValue, String spr, String kvSpr) {
        if (StringUtils.isAnyBlank(strValue, spr, kvSpr)) {
            return null;
        }
        return Splitter.on(spr).omitEmptyStrings().trimResults().withKeyValueSeparator(kvSpr)
            .split(strValue);
    }

    /**
     * 字符串转list
     *
     * @param strValue
     * @param spr
     * @return
     */
    public static List<String> stringToList(String strValue, String spr) {
        if (StringUtils.isAnyBlank(strValue, spr)) {
            return null;
        }
        return Splitter.on(spr).omitEmptyStrings().trimResults().splitToList(strValue);
    }

}
