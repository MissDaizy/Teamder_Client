package com.example.teamder.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceOfTypeUser extends InstanceBoundary {
    private static InstanceOfTypeUser single_user_instance = null;

    private HashMap<String, Object> instanceAttributes;
    // List of group id's  of all groups the user in: [groupId1,groupId2....]
    private List<String> GroupsList;
    /* List of group id's  of the groups the user manages: [groupId1,groupId2....]
    GroupId  is the id of instance returned from POST request
    */
    private List<String> GroupsManagingList;

    public InstanceOfTypeUser(String name, String type, UserId userId,String description, ArrayList<String> tags) {
        super (name, type, userId);
        Log.d ("pttt", "In instance of type user constructor");
        instanceAttributes = new HashMap<String, Object> ();
        GroupsList= new ArrayList<String> ();
        GroupsManagingList = new ArrayList<String> ();

        instanceAttributes.put ("Tags", tags);
        instanceAttributes.put ("User Description", description);
        instanceAttributes.put ("Groups",GroupsList);
        instanceAttributes.put ("GroupsManaging",GroupsManagingList);
    }

    private InstanceOfTypeUser() {
    }

    public List<String> getGroupsList() {
        return GroupsList;
    }

    public InstanceOfTypeUser setGroupsList(List<String> groupsList) {
        GroupsList = groupsList;
        return this;
    }

    public List<String> getGroupsManagingList() {
        return GroupsManagingList;
    }

    public InstanceOfTypeUser setGroupsManagingList(List<String> groupsManagingList) {
        GroupsManagingList = groupsManagingList;
        return this;
    }

    public static InstanceOfTypeUser getInstance()
    {
        if (single_user_instance == null)
            single_user_instance = new InstanceOfTypeUser();

        return single_user_instance;
    }
}
