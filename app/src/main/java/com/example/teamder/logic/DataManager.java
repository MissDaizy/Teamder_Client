package com.example.teamder.logic;

import android.util.Log;

import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.models.UserId;

public class DataManager {
    private NewUserBoundary newUserBoundary;
    private UserBoundary userBoundary;
    private InstanceBoundary instanceBoundary;

    private IdConverter idConverter;

    public DataManager() {
        newUserBoundary = new NewUserBoundary ();
        userBoundary = new UserBoundary ();
        instanceBoundary = new InstanceBoundary ();
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
        return this.userBoundary;
    }

    public InstanceBoundary getInstanceBoundary() {
        return instanceBoundary;
    }

    public void setInstanceBoundary(InstanceBoundary instanceBoundary) {
        this.instanceBoundary = instanceBoundary;
    }

    public String getUserDomainFromUserId(UserBoundary userBoundary) {

        UserId userId = new UserId();
        String boundaryDomain = idConverter.getUserDomainFromUserEntityId(userBoundary.getUserId ().toString ());
        userId.setDomain(boundaryDomain);
        Log.d ("pttt", " userID string: "+userBoundary.getUserId ().toString ());
        Log.d ("pttt", "getUserDomainFromUserId: "+"Domain: "+boundaryDomain);

        return boundaryDomain;
    }

    public String getUserEmailFromUserId(UserBoundary userBoundary) {

        UserId userId = new UserId();
        String boundaryEmail = idConverter.getUserEmailFromUserEntityId(userBoundary.getUserId ().toString ());
        userId.setEmail(boundaryEmail);
        Log.d ("pttt", " userID string: "+userBoundary.getUserId ().toString ());
        Log.d ("pttt", "getUserEmailFromUserId: "+"Email: "+boundaryEmail);

        return boundaryEmail;
    }
    public String getUserIdFromUserBoundary() {
        String userId=idConverter.getUserEntityIdFromDomainAndEmail(
                this.userBoundary.getUserId().getDomain(),
                this.userBoundary.getUserId().getEmail());;

        return userId;
    }
}

