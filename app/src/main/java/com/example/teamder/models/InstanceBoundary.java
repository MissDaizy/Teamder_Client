package com.example.teamder.models;

import java.util.Date;
import java.util.Map;

public class InstanceBoundary {
    private GeneralId activityId;
    private String type;
    private String name;
    private Boolean active;
    private Date createdTimestamp;
    private CreatedBy createdBy;
    private Location location;
    private Map<String, Object> instanceAttributes;

    public InstanceBoundary() {
    }

    public GeneralId getActivityId() {
        return activityId;
    }

    public InstanceBoundary setActivityId(GeneralId activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getType() {
        return type;
    }

    public InstanceBoundary setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public InstanceBoundary setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public InstanceBoundary setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public InstanceBoundary setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public InstanceBoundary setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public InstanceBoundary setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Map<String, Object> getInstanceAttributes() {
        return instanceAttributes;
    }

    public InstanceBoundary setInstanceAttributes(Map<String, Object> instanceAttributes) {
        this.instanceAttributes = instanceAttributes;
        return this;
    }
}
