package com.cheapflights.ui.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    public static int generateRandom(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;
    }

}
