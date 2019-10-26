package com.github.budgettime.model;

public class User {
    private String personalName;
    private String familyName;
    private String userId;


    public User(final String personalName, final String familyName, final String userId) {
        this.personalName = personalName;
        this.familyName = familyName;
        this.userId = userId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public String getUserId() {
        return userId;
    }
}
