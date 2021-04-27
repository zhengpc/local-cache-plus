/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.diamond;

import com.alibaba.china.cntools.cache.SimpleLocalCacheDiamondSupport;

/**
 * 基于Diamond的配置缓存
 *
 * @author zhengpc
 * @version $Id: DiamondConfigCache.java, v 0.1 2017/12/6 下午7:59 zhengpc Exp $
 * @date 2017/12/06
 */
public class DiamondConfigCache extends SimpleLocalCacheDiamondSupport<String> {

    @Override
    protected String dataConvert(String configValue) throws Exception {
        return configValue;
    }

}
