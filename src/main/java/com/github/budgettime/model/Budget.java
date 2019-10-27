package com.github.budgettime.model;

import com.github.budgettime.JsonableCollection;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Budget implements JsonableCollection {
    private int               budgetId;
    private List<BudgetEntry> entries;

    public Budget(final int budgetId) {
        this.entries = new ArrayList<>();
        this.budgetId = budgetId;
    }

    public void addEntry(final BudgetEntry budgetEntry) {
        this.entries.add(budgetEntry);
    }

    public List<BudgetEntry> getEntries() {
        return this.entries;
    }

    public int length() {
        return entries.size();
    }

    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        entries.forEach(budgetEntry -> {
            jsonArray.put(budgetEntry.toJsonObject());
        });
        return jsonArray;
    }
}
