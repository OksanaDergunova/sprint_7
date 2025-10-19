package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrderSteps;

public class GetOrdersListTest extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @Description("Получение списка заказов")
    public void getOrdersListTest() {
        Response response = orderSteps.getOrdersList();
        orderSteps.checkOrdersListNotEmpty(response);
    }
}