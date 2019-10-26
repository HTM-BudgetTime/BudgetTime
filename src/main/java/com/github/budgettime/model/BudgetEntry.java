package com.github.budgettime.model;

public class BudgetEntry
{
    private String category;
    private String type;
    private int commitsAWeek;
    public BudgetEntry(final String category, final  String type, final int commitsAWeek)
    {
        this.category=category;
        this.type=type;
        this.commitsAWeek=commitsAWeek;
    }

    public int getCommitsAWeek() {
        return commitsAWeek;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }


}