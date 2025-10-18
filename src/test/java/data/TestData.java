package data;

import java.util.Random;

//Специальные рандомные и уникальные данные для тестирования
public class TestData {

    public static String getRandomLogin() {
        return "courier_" + System.currentTimeMillis() + "_" + new Random().nextInt(1000);
    }

    public static String getRandomPassword() {
        return "pass_" + System.currentTimeMillis();
    }

    public static String getRandomFirstName() {
        return "name_" + System.currentTimeMillis();
    }
}