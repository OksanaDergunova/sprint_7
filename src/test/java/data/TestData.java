package data;

import net.datafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestData {
    private static final Faker faker = new Faker(new Locale("ru"));

    // Генерация данных для курьера
    public static String getRandomLogin() {
        return faker.name().firstName().toLowerCase() +
                faker.name().lastName().toLowerCase() +
                "_" + faker.random().hex(8);
    }

    public static String getRandomPassword() {
        return faker.regexify("[A-Za-z0-9!@#$%^&*()]{8,16}");
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    // Генерация данных для заказа
    public static String getRandomAddress() {
        return faker.address().streetAddress() + ", " + faker.address().city();
    }

    public static String getRandomMetroStation() {
        return String.valueOf(faker.number().numberBetween(1, 20));
    }

    public static String getRandomPhone() {
        return "+7" + faker.number().digits(10);
    }

    public static int getRandomRentTime() {
        return faker.number().numberBetween(1, 10);
    }

    public static String getRandomDeliveryDate() {
        LocalDate futureDate = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));
        return futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getRandomComment() {
        return faker.text().text(10, 50);
    }

}