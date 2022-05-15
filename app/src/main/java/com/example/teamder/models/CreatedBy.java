package com.example.teamder.models;

public class CreatedBy {
    private UserId userId;

    public CreatedBy() {
    }

    public UserId getUserId() {
        return userId;
    }

    public CreatedBy setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }
}
