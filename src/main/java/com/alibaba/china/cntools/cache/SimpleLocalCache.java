/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.cache;

/**
 * 本地缓存
 *
 * @author zhengpc
 * @version $Id: SimpleLocalCache.java, v 0.1 2017/11/16 下午5:24 zhengpc Exp $
 * @date 2017/11/16
 */
public interface SimpleLocalCache<K, V> {

    /**
     * 从cache取内容。如果未命中会触发主动加载。
     *
     * @param key
     * @return key对应的缓存内容
     */
    V getValue(final K key);

}
