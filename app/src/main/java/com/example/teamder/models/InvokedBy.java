package com.example.teamder.models;

public class InvokedBy {
    private UserId userId;

    public InvokedBy() {
    }

    public UserId getUserId() {
        return userId;
    }

    public InvokedBy setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }
}
