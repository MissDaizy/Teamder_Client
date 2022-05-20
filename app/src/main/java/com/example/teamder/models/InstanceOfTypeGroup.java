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
    private Map<String, Object> instanceGroupAttributes;
    // List of users id's  of all members in group: ["userid1","userid2",...]
    private List<String> members;
    // List of users id's  of all pending members in group: ["userid1","userid2",...]
    private List<String> pendingMembers;

    public InstanceOfTypeGroup(String description, ArrayList<String> tags) {
        super ();
        instanceGroupAttributes = new HashMap<String, Object> ();
        members= new ArrayList<String> ();
        pendingMembers = new ArrayList<String> ();

        instanceGroupAttributes.put ("Tags", tags);
        instanceGroupAttributes.put ("Group Description", description);
    }

    public InstanceOfTypeGroup() {
    }

    public Map<String, Object> getInstanceGroupAttributes() {
        return instanceGroupAttributes;
    }

    public void setInstanceGroupAttributes(Map<String, Object> instanceGroupAttributes) {
        this.instanceGroupAttributes = instanceGroupAttributes;
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
