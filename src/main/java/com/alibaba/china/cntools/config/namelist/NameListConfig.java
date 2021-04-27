/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.alibaba.china.cntools.config.namelist;

import java.io.Serializable;
import java.util.List;

/**
 * 名单配置对象
 *
 * @author zhengpc
 * @version $Id: NameListConfig.java, v 0.1 2017/11/21 下午1:10 zhengpc Exp $
 * @date 2017/11/21
 */
public class NameListConfig implements Serializable {

    private static final long serialVersionUID = 4522534874984796027L;

    /**
     * 用户白名单
     */
    private List<String> whiteList;

    /**
     * 用户黑名单
     */
    private List<String> blackList;

    /**
     * Getter method for property <tt>whiteList</tt>.
     *
     * @return property value of whiteList
     */
    public List<String> getWhiteList() {
        return whiteList;
    }

    /**
     * Setter method for property <tt>whiteList</tt>.
     *
     * @param whiteList value to be assigned to property whiteList
     */
    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    /**
     * Getter method for property <tt>blackList</tt>.
     *
     * @return property value of blackList
     */
    public List<String> getBlackList() {
        return blackList;
    }

    /**
     * Setter method for property <tt>blackList</tt>.
     *
     * @param blackList value to be assigned to property blackList
     */
    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

}
