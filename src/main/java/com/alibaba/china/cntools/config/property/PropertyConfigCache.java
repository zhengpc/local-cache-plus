package com.alibaba.china.cntools.config.property;

import java.io.StringReader;
import java.util.Properties;

import com.alibaba.china.cntools.cache.SimpleLocalCacheDiamondSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: ilson.jhw
 * @create: 2019-11-18 11:44
 **/
public class PropertyConfigCache extends SimpleLocalCacheDiamondSupport<PropertyConfig> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyConfigCache.class);

    @Override
    protected PropertyConfig dataConvert(String configValue) {
        if (StringUtils.isBlank(configValue)) {
            return null;
        }
        try {
            final Properties properties = new Properties();
            properties.load(new StringReader(configValue));
            return new PropertyConfig(properties);
        } catch (Exception e) {
            LOGGER.error("dataConvert failed", e);
        }
        return null;
    }

}
