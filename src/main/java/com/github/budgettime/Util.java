package com.github.budgettime;

import java.util.Random;

public class Util {

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
