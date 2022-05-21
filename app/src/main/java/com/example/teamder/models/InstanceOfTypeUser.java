package com.example.teamder.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceOfTypeUser extends InstanceBoundary {
    private HashMap<String, Object> instanceAttributes;
    // List of group id's  of all groups the user in: [groupId1,groupId2....]
    private List<String> GroupsList;
    /* List of group id's  of the groups the user manages: [groupId1,groupId2....]
    GroupId  is the id of instance returned from POST request
    */
    private List<String> GroupsManagingList;

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

    public InstanceOfTypeUser() {
    }

    @Override
    public void setDescription(String descriptionField) {
        instanceAttributes.put ("User Description",descriptionField);
    }

//    public void setGroupsList(List<String> groupsList) {
//        GroupsList = groupsList;
//    }

//    public void setGroupsManagingList(List<String> groupsManagingList) {
//        GroupsManagingList = groupsManagingList;
//    }

//    public void setInstanceAttributes(HashMap<String, Object> instanceAttributes) {
//        this.instanceAttributes = instanceAttributes;
//    }

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

}
