/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.cache;

import com.alibaba.china.cntools.model.DiamondKey;
import com.taobao.diamond.client.Diamond;
import com.taobao.diamond.manager.ManagerListenerAdapter;

import java.util.List;

import static com.alibaba.china.cntools.base.MsgUtils.formatMsg;

/**
 * 支持从diamond读取配置的本地缓存抽象父类，之所以选择使用LocalCache是为了简化同类型的基于diamond的配置。
 *
 * @author zhengpc
 * @version $Id: SimpleLocalCacheDiamondSupport.java, v 0.1 2017/11/16 下午7:52 zhengpc Exp $
 * @date 2017/11/16
 */
public abstract class SimpleLocalCacheDiamondSupport<T> extends AbstractSimpleLocalCache<DiamondKey, T> {

    @Override
    protected T doLoad(DiamondKey diamondKey) throws Exception {
        String groupId = diamondKey.getGroupId();
        String dataId = diamondKey.getDataId();

        Diamond.addListener(dataId, groupId, new SimpleLocalCacheDiamondListener(diamondKey));

        String configValue;
        try {
            configValue = Diamond.getConfig(dataId, groupId, 10000L);
        } catch (Exception e) {
            LOGGER.error(formatMsg("get config from diamond failed,dataId={0},groupId={1}", dataId, groupId), e);
            throw e;
        }

        return dataConvert(configValue);
    }

    @Override
    protected List<CacheCfg> getCacheCfgList() {
        return null;
    }

    /**
     * String和对象类型转换
     *
     * @param configValue
     * @return
     */
    protected abstract T dataConvert(String configValue) throws Exception;

    /**
     * diamond配置变更，更新本地缓存
     *
     * @param key
     * @param configValue
     */
    private void updateLocalCache(DiamondKey key, String configValue) throws Exception {
        T dataObject = dataConvert(configValue);
        if (dataObject != null) {
            super.refresh(key, dataObject);
        }
    }

    /**
     * 自定义diamond监听器
     */
    class SimpleLocalCacheDiamondListener extends ManagerListenerAdapter {

        /**
         * 资源ID
         */
        private final DiamondKey diamondKey;

        /**
         * 构造函数
         *
         * @param diamondKey
         */
        public SimpleLocalCacheDiamondListener(DiamondKey diamondKey) {
            this.diamondKey = diamondKey;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            // diamond配置变更，更新本地缓存
            try {
                updateLocalCache(diamondKey, configInfo);
            } catch (Exception e) {
                LOGGER.error(formatMsg("update local cache failed,diamondKey={0}", diamondKey), e);
                throw new RuntimeException(e);
            }
        }
    }
}
