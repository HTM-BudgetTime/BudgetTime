package com.github.budgettime.model;

import com.github.budgettime.Jsonable;
import org.json.JSONObject;

public class User implements Jsonable {
    private String username;
    private String personalName;
    private String familyName;


    public User(final String username, final String personalName, final String familyName) {
        this.personalName = personalName;
        this.familyName = familyName;
        this.username = username;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public String getUsername() {
        return username;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("personal_name", getPersonalName());
        jsonObject.put("family_name", getFamilyName());
        jsonObject.put("user_id", getUsername());

        return jsonObject;
    }
}
