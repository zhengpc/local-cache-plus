/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.gray;

import com.alibaba.china.cntools.cache.SimpleLocalCacheDiamondSupport;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static com.alibaba.china.cntools.base.StringSplitUtils.stringToList;
import static com.alibaba.china.cntools.base.StringSplitUtils.stringToMap;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;

/**
 * 用户维度灰度配置缓存，不同类型的用户灰度控制，通过新增用户灰度类型枚举{@link UserGrayConfigTypeEnum}即可实现。
 *
 * <p>配置样例：whitelist=xx,yy&blacklist=xx,yy&percent=100
 *
 * @author zhengpc
 * @version $Id: UserGrayConfigCache.java, v 0.1 2017/11/16 下午7:17 zhengpc Exp $
 * @date 2017/11/16
 */
public class UserGrayConfigCache extends SimpleLocalCacheDiamondSupport<UserGrayConfig> {

    /**
     * 白名单
     */
    private static final String WHITELIST = "whitelist";

    /**
     * 黑名单
     */
    private static final String BLACKLIST = "blacklist";

    /**
     * 灰度百分比
     */
    private static final String PERCENT = "percent";

    @Override
    protected UserGrayConfig dataConvert(String configValue) throws Exception {
        if (StringUtils.isBlank(configValue)) {
            return null;
        }

        Map<String, String> configMap = stringToMap(configValue, "&", "=");
        if (configMap == null || configMap.isEmpty()) {
            return null;
        }

        UserGrayConfig userGrayConfig = new UserGrayConfig();

        // 设置用户白名单
        userGrayConfig.setWhiteList(stringToList(configMap.get(WHITELIST), ","));
        // 设置用户黑名单
        userGrayConfig.setBlackList(stringToList(configMap.get(BLACKLIST), ","));
        // 设置灰度流量比例
        userGrayConfig.setPercent(toDouble(configMap.get(PERCENT), 0));

        return userGrayConfig;
    }

}
