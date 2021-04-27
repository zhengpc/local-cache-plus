/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.cache;

import java.io.Serializable;

/**
 * 缓存值对象
 *
 * @author zhengpc
 * @version $Id: CacheValue.java, v 0.1 2017/11/16 下午5:19 zhengpc Exp $
 * @date 2017/11/16
 */
public class CacheValue<T> implements Serializable {

    private static final long serialVersionUID = 1395830915401032915L;

    /**
     * 缓存的目标对象，允许为空
     */
    private final T dataObject;

    /**
     * 构造函数
     *
     * @param dataObject
     */
    public CacheValue(final T dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * Getter method for property <tt>dataObject</tt>.
     *
     * @return property value of dataObject
     */
    public T getDataObject() {
        return dataObject;
    }
}
