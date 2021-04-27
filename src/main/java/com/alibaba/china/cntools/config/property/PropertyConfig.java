package com.alibaba.china.cntools.config.property;

import java.io.Serializable;
import java.util.Properties;

/**
 * @description: diamond 配置文件类型
 * @author: ilson.jhw
 * @create: 2019-11-18 11:30
 **/
public class PropertyConfig implements Serializable {

    private static final long serialVersionUID = 5934043788387826121L;

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public PropertyConfig() {
    }

    public PropertyConfig(Properties properties) {
        this.properties = properties;
    }
}
