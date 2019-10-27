package com.github.budgettime.model;

import com.github.budgettime.Jsonable;
import org.json.JSONObject;

public class User implements Jsonable {
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

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("personal_name", getPersonalName());
        jsonObject.put("family_name", getFamilyName());
        jsonObject.put("user_id", getUserId());

        return jsonObject;
    }
}
