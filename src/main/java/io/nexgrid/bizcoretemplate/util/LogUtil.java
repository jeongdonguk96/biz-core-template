package io.nexgrid.bizcoretemplate.util;

import java.util.Random;

public class LogUtil {

    // 고유 Seq를 생성한다.
    public static String generateSeqId() {
        long millis = System.currentTimeMillis();
        String randomNumber = generateRandomNumber();

        return millis + randomNumber;
    }

    // 3자리의 랜덤 숫자를 생성한다.
    private static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;

        return String.valueOf(randomNumber);
    }

}
