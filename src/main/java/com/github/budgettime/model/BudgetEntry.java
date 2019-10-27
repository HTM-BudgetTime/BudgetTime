package com.github.budgettime.model;

import com.github.budgettime.Jsonable;
import org.json.JSONObject;

public class BudgetEntry implements Jsonable {
    private String category;
    private String description;
    private float  duration;

    public BudgetEntry(final String category, final String description, final float duration) {
        this.category = category;
        this.description = description;
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public float getDuration() {
        return duration;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", getCategory());
        jsonObject.put("description", getDescription());
        jsonObject.put("hours_budgeted", getDuration());

        return jsonObject;
    }

}
