package com.example.teamder.logic;

import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;

public class DataManager {
    private NewUserBoundary newUserBoundary;
    private UserBoundary userBoundary;
    private InstanceBoundary instanceBoundary;

    public DataManager() {
       newUserBoundary =new NewUserBoundary();
        userBoundary =new UserBoundary();
        instanceBoundary=new InstanceBoundary ();
    }
    public void setNewUserBoundary(NewUserBoundary newUserBoundary) {
        this.newUserBoundary = newUserBoundary;
    }

    public void setUserBoundary(UserBoundary userBoundary) {
        this.userBoundary = userBoundary;
    }


    public void setNewUserBoundaryAvatarData(String avatar) {
        newUserBoundary.setAvatar (avatar);
    }

    public void setNewUserBoundaryUsernameData(String username) {
        newUserBoundary.setUsername (username);
    }

    public NewUserBoundary getNewUserBoundary() {
        return newUserBoundary;
    }

    public UserBoundary getUserBoundary() {
        return userBoundary;
    }

    public InstanceBoundary getInstanceBoundary() {
        return instanceBoundary;
    }

    public DataManager setInstanceBoundary(InstanceBoundary instanceBoundary) {
        this.instanceBoundary = instanceBoundary;
        return this;
    }
}
