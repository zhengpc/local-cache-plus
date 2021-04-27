/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.gray;

import com.alibaba.china.cntools.config.DiamondConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author zhengpc
 * @version $Id: UserGrayConfigType.java, v 0.1 2018/7/10 上午11:44 zhengpc Exp $
 * @date 2018/07/10
 */
public interface UserGrayConfigType extends DiamondConfig {

    UserGrayConfigCache userGrayConfigCache = new UserGrayConfigCache();

    /**
     * 当前用户是否命中灰度
     *
     * @param userId
     * @return
     */
    default boolean isUserHited(Long userId) {
        if (userId == null) {
            return false;
        }

        UserGrayConfig userGrayConfig = userGrayConfigCache.getValue(getDiamondKey());
        if (userGrayConfig == null) {
            return false;
        }

        // 百分比转化为万分比
        int percent = Optional.ofNullable(userGrayConfig.getPercent())
                .map(String::valueOf)
                .map(BigDecimal::new)
                .map(value -> value.multiply(new BigDecimal(100)))
                .map(BigDecimal::intValue)
                .orElse(0);
        if (userId % 10000 < percent) {
            List<String> blackList = userGrayConfig.getBlackList();
            if (blackList != null && blackList.contains(userId.toString())) {
                return false;
            } else {
                return true;
            }
        } else {
            List<String> whiteList = userGrayConfig.getWhiteList();
            if (whiteList != null && whiteList.contains(userId.toString())) {
                return true;
            }
        }

        return false;
    }

}
