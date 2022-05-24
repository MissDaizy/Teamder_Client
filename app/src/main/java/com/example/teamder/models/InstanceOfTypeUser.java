package com.example.teamder.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InstanceOfTypeUser extends InstanceBoundary {
    private static InstanceOfTypeUser singleInstanceTypeUser;

    private HashMap<String, Object> instanceAttributes;
    // List of group id's  of all groups the user in: [groupId1,groupId2....]
    private ArrayList<String> GroupsList;
    /* List of group id's  of the groups the user manages: [groupId1,groupId2....]
    GroupId  is the id of instance returned from POST request
    */
    private ArrayList<String> GroupsManagingList;

    public InstanceOfTypeUser(String name, String type, UserId userId,String description, ArrayList<String> tags,String phoneNum) {
        super (name, type, userId);
        Log.d ("pttt", "In instance of type user constructor");
        instanceAttributes = new HashMap<String, Object> ();
        GroupsList= new ArrayList<String> ();
        GroupsManagingList = new ArrayList<String> ();

        instanceAttributes.put ("Tags", tags);
        instanceAttributes.put ("User Description", description);
        instanceAttributes.put ("Groups",GroupsList);
        instanceAttributes.put ("GroupsManaging",GroupsManagingList);
        instanceAttributes.put ("Phone Number",phoneNum);
    }

    private InstanceOfTypeUser() {
    }

    public static synchronized InstanceOfTypeUser getSingleInstanceTypeUser(){
        if(singleInstanceTypeUser == null){
            singleInstanceTypeUser = new InstanceOfTypeUser();
        }
        return singleInstanceTypeUser;
    }

    @Override
    public void setDescription(String descriptionField) {
        instanceAttributes.put ("User Description",descriptionField);
    }

    public void setGroupsList(ArrayList<String> groupsList) {
        GroupsList = groupsList;
    }

    public void setGroupsManagingList(ArrayList<String> groupsManagingList) {
        GroupsManagingList = groupsManagingList;
    }

    public void setInstanceAttributes(HashMap<String, Object> instanceAttributes) {
        this.instanceAttributes = instanceAttributes;
    }

    public void setPhoneNumber(String updatedPhoneNum) {
        instanceAttributes.put ("Phone Number",updatedPhoneNum);
    }

    public List<String> getGroupsList() {
        return GroupsList;
    }

    public String getInstanceDescription() {
        if(instanceAttributes.containsKey ("User Description"))
            return instanceAttributes.get ("User Description").toString ();
        return "No Description";
    }

    public String getPhoneNumber() {
        if(instanceAttributes.containsKey ("Phone Number"))
            return instanceAttributes.get ("Phone Number").toString ();
        return "No Phone Number";
    }

    public List<String> getGroupsManagingList() {
        return GroupsManagingList;
    }


    public HashMap<String, Object> getInstanceAttributes() {
        return instanceAttributes;
    }

    public void addGroupToUsersList(GeneralId instanceId) {
        List<GeneralId> groupsList=(ArrayList<GeneralId>) instanceAttributes.get ("Groups");
        List<GeneralId> groupsManaging=(ArrayList<GeneralId>) instanceAttributes.get ("GroupsManaging");

        instanceAttributes.put ("Groups",groupsList);
        instanceAttributes.put ("GroupsManaging",groupsManaging);
    }
}
