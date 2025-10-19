package tests;

import data.TestData;
import jdk.jfr.Description;
import models.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.restassured.response.Response;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();
    private final List<String> colors;

    public CreateOrderTest(List<String> colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getColorData() {
        return new Object[][] {
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {Arrays.asList()}
        };
    }

    @Test
    @Description("Создание заказа с разными цветами")
    public void createOrderWithDifferentColorsTest() {
        Order order = new Order(
                TestData.getRandomFirstName(),
                TestData.getRandomLastName(),
                TestData.getRandomAddress(),
                TestData.getRandomMetroStation(),
                TestData.getRandomPhone(),
                TestData.getRandomRentTime(),
                TestData.getRandomDeliveryDate(),
                TestData.getRandomComment(),
                colors
        );

        Response response = orderSteps.createOrder(order);
        orderSteps.checkOrderCreatedSuccessfully(response);

        createdOrderTrack = response.jsonPath().getString("track");
    }

}