package com.alibaba.china.cntools.config.property;

import com.alibaba.china.cntools.config.DiamondConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @description:
 * @author: ilson.jhw
 * @create: 2019-11-18 15:39
 **/
public interface PropertyConfigType extends DiamondConfig {

    PropertyConfigCache propertyConfigCache = new PropertyConfigCache();

    /**
     * 获取Properties value
     *
     * @param key
     * @return
     */
    default String getPropertyValue(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        PropertyConfig propertyConfig = propertyConfigCache.getValue(getDiamondKey());
        return Optional.ofNullable(propertyConfig)
                .map(PropertyConfig::getProperties)
                .map(properties -> properties.getProperty(key))
                .orElse(null);
    }

}
