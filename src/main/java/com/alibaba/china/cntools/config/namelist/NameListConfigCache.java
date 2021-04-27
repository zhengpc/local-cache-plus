/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.namelist;

import java.util.Map;

import com.alibaba.china.cntools.cache.SimpleLocalCacheDiamondSupport;

import org.apache.commons.lang3.StringUtils;

import static com.alibaba.china.cntools.base.StringSplitUtils.stringToList;
import static com.alibaba.china.cntools.base.StringSplitUtils.stringToMap;

/**
 * 名单列表配置缓存，不同类型的名单列表控制，通过新增名单列表类型枚举{@link NameListConfigTypeEnum}即可实现。
 *
 * <p>配置样例：whitelist=xx,yy&blacklist=xx,yy
 *
 * @author zhengpc
 * @version $Id: NameListConfigCache.java, v 0.1 2017/11/21 下午1:12 zhengpc Exp $
 * @date 2017/11/21
 */
public class NameListConfigCache extends SimpleLocalCacheDiamondSupport<NameListConfig> {

    /**
     * 白名单
     */
    private static final String WHITELIST = "whitelist";

    /**
     * 黑名单
     */
    private static final String BLACKLIST = "blacklist";

    @Override
    protected NameListConfig dataConvert(String configValue) throws Exception {
        if (StringUtils.isBlank(configValue)) {
            return null;
        }

        Map<String, String> configMap = stringToMap(configValue, "&", "=");
        if (configMap == null || configMap.isEmpty()) {
            return null;
        }

        NameListConfig nameListConfig = new NameListConfig();

        // 设置用户白名单
        nameListConfig.setWhiteList(stringToList(configMap.get(WHITELIST), ","));
        // 设置用户黑名单
        nameListConfig.setBlackList(stringToList(configMap.get(BLACKLIST), ","));

        return nameListConfig;
    }

}
