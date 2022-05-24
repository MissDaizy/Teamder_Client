package com.example.teamder.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InstanceBoundary {
    private static InstanceOfTypeUser instance;

    private GeneralId instanceId;
    private String type;
    private String name;
    private Boolean active;
    private Date createdTimestamp;
    private CreatedBy createdBy;
    private Location location;

        //dataManager.setInstanceBoundary (new InstanceBoundary ("USER",name,userDomain,userEmail,userDesc,tags);
//TODO: PUT TO USER TO CHANGE TO ROLE = MANAGER AND TO REVERSE AFTERWARDS TO PLAYER
    public InstanceBoundary(String name,String type, UserId userId) {
        Log.d ("pttt", "In instance constructor");

        createdTimestamp=new Date ();
        instanceId=new GeneralId ();
        createdBy=new CreatedBy ();
        location=new Location ();
        location.setLat (32.11);
        location.setLng (34.81);

        this.type=type;
        instanceId=null;
        createdTimestamp=null;

        this.createdBy.setUserId (userId);
        this.name=name;
        active=true;
    }

    public InstanceBoundary() {
    }

    public GeneralId getActivityId() {
        return instanceId;
    }

    public void setActivityId(GeneralId activityId) {
        this.instanceId = activityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = null;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserId userId) {
        this.createdBy.setUserId (userId);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public abstract void setDescription(String descriptionField);

    public GeneralId getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(GeneralId instanceId) {
        this.instanceId = instanceId;
    }
}
