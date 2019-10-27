package com.github.budgettime.model;

import com.github.budgettime.JsonableCollection;
import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetPeriod implements JsonableCollection {
    private LocalDate         periodStart;
    private LocalDate         periodEnd;
    private List<BudgetEntry> entries;

    public BudgetPeriod(final LocalDate periodStart, final LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.entries = new ArrayList<>();
    }

    public void addEntry(final BudgetEntry budgetEntry) {
        this.entries.add(budgetEntry);
    }

    public List<BudgetEntry> getEntries() {
        return this.entries;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
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
