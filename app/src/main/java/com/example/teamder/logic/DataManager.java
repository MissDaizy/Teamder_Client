package com.example.teamder.logic;

import android.util.Log;

import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.RoleType;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.models.UserId;

public class DataManager {
    private NewUserBoundary newUserBoundary;
    private UserBoundary userBoundary;
    private InstanceOfTypeUser instanceOfTypeUser ;
    private InstanceOfTypeGroup instanceOfTypeGroup ;

    private IdConverter idConverter;

    public DataManager() {
        newUserBoundary = new NewUserBoundary ();
        userBoundary = new UserBoundary ();
        instanceOfTypeUser = new InstanceOfTypeUser ();
        instanceOfTypeGroup = new InstanceOfTypeGroup ();

        idConverter=new IdConverter ();
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

    public InstanceOfTypeUser getInstanceOfTypeUser() {
        return instanceOfTypeUser;
    }

    public void setInstanceOfTypeUser(InstanceOfTypeUser instanceOfTypeUser) {
        this.instanceOfTypeUser.setCreatedTimestamp (null);
        this.instanceOfTypeUser = instanceOfTypeUser;
    }

    public InstanceOfTypeGroup getInstanceOfTypeGroup() {
        return instanceOfTypeGroup;
    }

    public void setInstanceOfTypeGroup(InstanceOfTypeGroup instanceOfTypeGroup) {
        this.instanceOfTypeGroup = instanceOfTypeGroup;
    }

    public void setIdConverter(IdConverter idConverter) {
        this.idConverter = idConverter;
    }


    public IdConverter getIdConverter() {
        return idConverter;
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

    public String getUserBoundaryRoleType() {
        return userBoundary.getRole ();
    }

    public String getUserDomain() {
        return userBoundary.getUserId ().getDomain ();
    }

    public String getUserEmail() {
        return userBoundary.getUserId ().getEmail ();
    }

    public String getUsername() {
        return userBoundary.getUsername ();
    }

    public String getUserDescription() {
        return instanceOfTypeUser.getInstanceDescription ();
    }

    public String getPhoneNumber() {
        return instanceOfTypeUser.getPhoneNumber ();
    }

    public void updateUserRoleTypeData() {
        if (userBoundary.getRole ().equals ("PLAYER")) {
            userBoundary.setRole (RoleType.MANAGER.toString ());
            Log.d ("pttt", "user role updated to manager ");
        }
        else if(userBoundary.getRole ().equals ("MANAGER"))
            userBoundary.setRole (RoleType.PLAYER.toString ());
    }

    public void updateUsername(String usernameField) {
        userBoundary.setUsername (usernameField);
    }

    public void updatePhoneNumber(String phoneNumField) {
        instanceOfTypeUser.setPhoneNumber (phoneNumField);
    }

    public void updateDescription(String descriptionField) {
        instanceOfTypeUser.setDescription(descriptionField);
    }
}


