package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class OrderSteps {

    @Step("Создание заказа")
    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получение списка заказов")
    public Response getOrdersList() {
        return given()
                .get("/api/v1/orders");
    }

    @Step("Проверка успешного создания заказа")
    public void checkOrderCreatedSuccessfully(Response response) {
        response.then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Step("Проверка наличия списка заказов в ответе")
    public void checkOrdersListNotEmpty(Response response) {
        response.then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}