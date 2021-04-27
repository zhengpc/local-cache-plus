/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.diamond;

import com.alibaba.china.cntools.config.DiamondConfig;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhengpc
 * @version $Id: DiamondConfigType.java, v 0.1 2018/7/10 上午11:47 zhengpc Exp $
 * @date 2018/07/10
 */
public interface DiamondConfigType extends DiamondConfig {

    DiamondConfigCache diamondConfigCache = new DiamondConfigCache();

    /**
     * @return
     */
    String getDefaultValue();

    /**
     * 根据配置类型获取配置值
     *
     * @return
     */
    default String getConfigValue() {
        return getConfigValue(getDefaultValue());
    }

    /**
     * 根据配置类型获取配置值，如果未能从Diamond获取到，则返回默认值
     *
     * @return
     */
    default String getConfigValue(String defaultValue) {
        String configValue = diamondConfigCache.getValue(getDiamondKey());
        if (StringUtils.isBlank(configValue)) {
            configValue = defaultValue;
        }

        return configValue;
    }

}
