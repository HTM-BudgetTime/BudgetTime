package com.github.budgettime;

import com.github.budgettime.model.BudgetEntry;

import java.util.Random;

public class TestUtil {

    public static BudgetEntry randomBudgetEntry() {
        Random random      = new Random();
        float  randomHours = Math.round(random.nextFloat() * 40.0 * 100) / 100f;

        return new BudgetEntry("Category", "Description", randomHours);
    }

}
