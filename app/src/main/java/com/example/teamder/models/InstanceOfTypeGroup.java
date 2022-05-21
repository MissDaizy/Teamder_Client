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
    private List<UserId> members;
    // List of users id's  of all pending members in group: ["userid1","userid2",...]
    private List<UserId> pendingMembers;

    public InstanceOfTypeGroup(String name, String type, UserId userId,String Description, ArrayList<String> tags,int numOfMembers) {
        super (name, type, userId);
        instanceAttributes = new HashMap<String, Object> ();
        members= new ArrayList<UserId> ();
        pendingMembers = new ArrayList<UserId> ();
        members.add (userId);

        instanceAttributes.put ("Description", Description);
        instanceAttributes.put ("Members", pendingMembers);
        instanceAttributes.put ("PendingMembers", pendingMembers);
        instanceAttributes.put ("Number Of Members", numOfMembers);
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

    public List<UserId> getMembers() {
        return members;
    }

    public void setMembers(List<UserId> members) {
        this.members = members;
    }

    public List<UserId> getPendingMembers() {
        return pendingMembers;
    }

    public void setPendingMembers(List<UserId> pendingMembers) {
        this.pendingMembers = pendingMembers;
    }
}


