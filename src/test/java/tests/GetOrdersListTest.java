package tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrderSteps;

public class GetOrdersListTest extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrdersListTest() {
        Response response = orderSteps.getOrdersList();
        orderSteps.checkOrdersListNotEmpty(response);
    }
}