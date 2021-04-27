package com.alibaba.china.cntools.config;

import com.alibaba.china.cntools.model.DiamondKey;

public interface DiamondConfig {

    /**
     * @return
     */
    String getConfigGroup();

    /**
     * @return
     */
    String getConfigType();

    default DiamondKey getDiamondKey() {
        return new DiamondKey(getConfigGroup(), getConfigType());
    }

}
