package com.example.teamder.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceOfTypeGroup extends InstanceBoundary {
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
    private List<String> members;
    // List of users id's  of all pending members in group: ["userid1","userid2",...]
    private List<String> pendingMembers;

    public InstanceOfTypeGroup(String name, String type, UserId userId,String Description, ArrayList<String> tags,int numOfMembers) {
        super (name, type, userId);
        instanceAttributes = new HashMap<String, Object> ();
        members= new ArrayList<String> ();
        pendingMembers = new ArrayList<String> ();

        instanceAttributes.put ("Description", Description);
        instanceAttributes.put ("Members", pendingMembers);
        instanceAttributes.put ("PendingMembers", pendingMembers);
        instanceAttributes.put ("Tags", tags);
    }

    public InstanceOfTypeGroup() {
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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getPendingMembers() {
        return pendingMembers;
    }

    public void setPendingMembers(List<String> pendingMembers) {
        this.pendingMembers = pendingMembers;
    }
}


