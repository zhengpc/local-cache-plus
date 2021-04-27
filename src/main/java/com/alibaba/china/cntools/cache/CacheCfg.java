/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;

/**
 * 缓存配置对象，包括缓存的自动刷新及自动失效设置，缓存的容量设置等
 *
 * @author zhengpc
 * @version $Id: CacheCfg.java, v 0.1 2017/11/29 下午12:57 zhengpc Exp $
 * @date 2017/11/29
 */
public interface CacheCfg {

    /**
     * 应用缓存配置
     *
     * @param cacheBuilder
     */
    void applyCacheCfg(CacheBuilder cacheBuilder);

    /**
     * 缓存刷新策略
     */
    class AutoRefreshCfg implements CacheCfg {

        /**
         * 对象创建多久后自动刷新，单位秒
         */
        private long refreshAfterWriteSecs;

        /**
         * 构造函数
         *
         * @param refreshAfterWriteSecs
         */
        public AutoRefreshCfg(long refreshAfterWriteSecs) {
            this.refreshAfterWriteSecs = refreshAfterWriteSecs;
        }

        @Override
        public void applyCacheCfg(CacheBuilder cacheBuilder) {
            cacheBuilder.refreshAfterWrite(refreshAfterWriteSecs, TimeUnit.SECONDS);
        }

    }

    /**
     * 缓存失效策略
     */
    class AutoExpireCfg implements CacheCfg {

        /**
         * 对象创建多久后失效，单位秒
         */
        private long expireAfterWriteSecs;

        /**
         * 对象多久未被访问后失效，单位秒
         */
        private long expireAfterAccessSecs;

        /**
         * 构造函数
         *
         * @param expireAfterWriteSecs
         * @param expireAfterAccessSecs
         */
        public AutoExpireCfg(long expireAfterWriteSecs, long expireAfterAccessSecs) {
            this.expireAfterWriteSecs = expireAfterWriteSecs;
            this.expireAfterAccessSecs = expireAfterAccessSecs;
        }

        @Override
        public void applyCacheCfg(CacheBuilder cacheBuilder) {
            cacheBuilder.expireAfterWrite(expireAfterWriteSecs, TimeUnit.SECONDS);
            cacheBuilder.expireAfterAccess(expireAfterAccessSecs, TimeUnit.SECONDS);
        }

    }

    /**
     * 缓存容量配置
     */
    class CapacityCfg implements CacheCfg {

        /**
         * 最大可缓存的实例个数
         */
        private long maximumSize;

        /**
         * 构造函数
         *
         * @param maximumSize
         */
        public CapacityCfg(long maximumSize) {
            this.maximumSize = maximumSize;
        }

        @Override
        public void applyCacheCfg(CacheBuilder cacheBuilder) {
            cacheBuilder.maximumSize(maximumSize);
        }
    }

}
