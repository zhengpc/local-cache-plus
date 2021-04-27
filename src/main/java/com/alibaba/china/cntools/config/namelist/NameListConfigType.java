/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.namelist;

import com.alibaba.china.cntools.config.DiamondConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author zhengpc
 * @version $Id: NameListConfigType.java, v 0.1 2018/7/10 上午11:26 zhengpc Exp $
 * @date 2018/07/10
 */
public interface NameListConfigType extends DiamondConfig {

    NameListConfigCache nameListConfigCache = new NameListConfigCache();

    /**
     * 指定值是否命中白名单
     *
     * @param compareValue
     * @return
     */
    default boolean isMatchWhiteList(String compareValue) {
        if (StringUtils.isBlank(compareValue)) {
            return false;
        }

        NameListConfig nameListConfig = nameListConfigCache.getValue(getDiamondKey());
        if (nameListConfig == null) {
            return false;
        }

        List<String> whiteList = nameListConfig.getWhiteList();
        if (whiteList != null && whiteList.contains(compareValue.trim())) {
            return true;
        }

        return false;
    }

    /**
     * 指定值是否命中黑名单
     *
     * @param compareValue
     * @return
     */
    default boolean isMatchBlackList(String compareValue) {
        if (StringUtils.isBlank(compareValue)) {
            return false;
        }

        NameListConfig nameListConfig = nameListConfigCache.getValue(getDiamondKey());
        if (nameListConfig == null) {
            return false;
        }

        List<String> blackList = nameListConfig.getBlackList();
        if (blackList != null && blackList.contains(compareValue.trim())) {
            return true;
        }

        return false;
    }

}
