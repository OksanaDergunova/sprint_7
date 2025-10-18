package tests;

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

    @Parameterized.Parameters
    public static Object[][] getColorData() {
        return new Object[][] {
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {Arrays.asList()}
        };
    }

    @Test
    public void createOrderWithDifferentColorsTest() {
        Order order = new Order(
                "Иван",
                "Иванов",
                "Москва, ул. Ленина, д. 1",
                "4",
                "+79999999999",
                3,
                "2024-12-31",
                "Комментарий к заказу",
                colors
        );

        Response response = orderSteps.createOrder(order);
        orderSteps.checkOrderCreatedSuccessfully(response);
    }
}