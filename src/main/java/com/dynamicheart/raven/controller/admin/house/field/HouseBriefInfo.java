package com.dynamicheart.raven.controller.admin.house.field;

import java.io.Serializable;

public class HouseBriefInfo implements Serializable {
    private String groupId;

    private String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
