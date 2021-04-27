/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.cache;

import java.util.List;

import com.alibaba.china.cntools.cache.CacheCfg.AutoExpireCfg;
import com.alibaba.china.cntools.cache.CacheCfg.AutoRefreshCfg;
import com.alibaba.china.cntools.cache.CacheCfg.CapacityCfg;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alibaba.china.cntools.base.MsgUtils.formatMsg;

/**
 * 本地缓存抽象父类
 *
 * @author zhengpc
 * @version $Id: AbstractSimpleLocalCache.java, v 0.1 2017/11/16 下午5:23 zhengpc Exp $
 * @date 2017/11/16
 */
public abstract class AbstractSimpleLocalCache<K, V> implements SimpleLocalCache<K, V> {

    /**
     * 日志
     */
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Guava Cache
     */
    private final LoadingCache<K, CacheValue<V>> loadingCache = buildLoadingCache();

    /**
     * 未命中时用来加载对象的方法
     *
     * @param key
     * @return
     */
    protected abstract V doLoad(final K key) throws Exception;

    /**
     * 刷新指定key的缓存
     *
     * @param key
     * @param value
     */
    protected void refresh(final K key, final V value) {
        loadingCache.put(key, new CacheValue<V>(value));
    }

    /**
     * 构建LoadingCache实例
     */
    private LoadingCache buildLoadingCache() {
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder();

        // 获取缓存失效配置
        List<CacheCfg> cacheCfgList = getCacheCfgList();
        if (cacheCfgList != null && !cacheCfgList.isEmpty()) {
            for (CacheCfg cacheCfg : cacheCfgList) {
                if (cacheCfg == null) {
                    continue;
                }
                // 应用缓存配置
                cacheCfg.applyCacheCfg(cacheBuilder);
            }
        }

        return cacheBuilder.build(new CacheLoader<K, CacheValue<V>>() {
            @Override
            public CacheValue<V> load(final K key) throws Exception {
                V dataObject = null;
                try {
                    dataObject = doLoad(key);
                } catch (Exception e) {
                    LOGGER.error(formatMsg("缓存未命中，加载过程中发生异常，key={0}", key), e);
                    throw e;
                }
                return new CacheValue(dataObject);
            }
        });
    }

    @Override
    public V getValue(final K key) {
        // 如果缓存未命中会自动触发对象加载
        try {
            return loadingCache.getUnchecked(key).getDataObject();
        } catch (Exception e) {
            LOGGER.error(formatMsg("获取缓存对象发生异常，key={0}", key), e);
        }

        return null;
    }

    /**
     * 返回默认的缓存自动失效配置，子类可以覆盖
     *
     * @return
     */
    protected List<CacheCfg> getCacheCfgList() {
        List<CacheCfg> cacheCfgList = Lists.newArrayList();

        cacheCfgList.add(new AutoExpireCfg(60 * 60, 30 * 60));
        cacheCfgList.add(new CapacityCfg(Integer.MAX_VALUE));
        cacheCfgList.add(new AutoRefreshCfg(60 * 60 * 2));

        return cacheCfgList;
    }

}
