package com.alibaba.china.cntools.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhengpc
 */
public class DiamondKey {

    /**
     *
     */
    private String groupId;

    /**
     *
     */
    private String dataId;

    public DiamondKey(String groupId, String dataId) {
        this.groupId = groupId;
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getGroupId()).append(getDataId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        DiamondKey key = (DiamondKey) obj;
        return new EqualsBuilder().append(getGroupId(), key.getGroupId()).append(getDataId(), key.getDataId()).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
