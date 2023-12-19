package com.haenu.shortlink.toolkit;

import java.util.Random;

/**
 * 分组ID随机数生成工具类
 */
public class RandomStringGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    public static String generateRandomString() {
        return generateRandomString(6);
    }

    public static String generateRandomString(int LENGTH) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }


}
