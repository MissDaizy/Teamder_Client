package com.example.teamder.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceOfTypeGroup extends InstanceBoundary {
    private static InstanceOfTypeGroup singleInstanceTypeGroup;

    /*
    Map of instanceGroupAttributes:
     "instanceAttributes": {
        "Description": "can be set to any value......",
        "Members": ["userid1","userid2",...],
		"PendingMembers": ["userid1","userid2",...],
		"Tags": ["tag1","tag2",...]
    }
    */
    private HashMap<String, Object> instanceAttributes;
    // List of users id's  of all members in group: ["userid1","userid2",...]
    private ArrayList<String> members;
    // List of users id's  of all pending members in group: ["userid1","userid2",...]
    private ArrayList<String> pendingMembers;

    public InstanceOfTypeGroup(String name,String groupName, String type, UserId userId,String Description, ArrayList<String> tags,int numOfMembers) {
        super (groupName, type, userId);
        instanceAttributes = new HashMap<String, Object> ();
        members= new ArrayList<String> ();
        pendingMembers = new ArrayList<String> ();
        members.add (name);

        instanceAttributes.put ("Description", Description);
        instanceAttributes.put ("Members", members);
        instanceAttributes.put ("PendingMembers", pendingMembers);
        instanceAttributes.put ("GroupCapacity", numOfMembers);
        instanceAttributes.put ("Tags", tags);
    }

    private InstanceOfTypeGroup() {
    }

    public static synchronized InstanceOfTypeGroup getSingleInstanceTypeGroup(){
        if(singleInstanceTypeGroup == null){
            singleInstanceTypeGroup = new InstanceOfTypeGroup();
        }
        return singleInstanceTypeGroup;
    }

    @Override
    public void setDescription(String descriptionField) {
        instanceAttributes.put ("Description",descriptionField);
    }


    public HashMap<String, Object> getInstanceGroupAttributes() {
        return instanceAttributes;
    }


    public void setInstanceGroupAttributes(HashMap<String, Object> instanceGroupAttributes) {
        this.instanceAttributes = instanceGroupAttributes;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public ArrayList<String> getPendingMembers() {
        return pendingMembers;
    }

    public void setPendingMembers(ArrayList<String> pendingMembers) {
        this.pendingMembers = pendingMembers;
    }
}


