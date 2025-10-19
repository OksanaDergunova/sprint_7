package tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierAuth;
import org.junit.After;
import org.junit.BeforeClass;
import steps.CourierSteps;
import steps.Endpoints;
import steps.OrderSteps;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected CourierSteps courierSteps = new CourierSteps();

    //данные для очистки
    protected String createdCourierLogin;
    protected String createdCourierPass;
    protected String createdOrderTrack;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @After
    public void cleanup() {
        if (createdCourierLogin != null && createdCourierPass != null) {
            deleteTestCourier(createdCourierLogin, createdCourierPass);
        }
        if(createdOrderTrack != null){
            deleteTestOrder(createdOrderTrack);
        }
        //System.out.println("удаление завершено");
    }

    @Step("Удаление тестового курьера")
    private void deleteTestCourier(String login, String pass) {
        try {
            // Сначала получаем ID курьера через логин
            CourierAuth authData = new CourierAuth(login, pass);
            Response loginResponse = courierSteps.loginCourier(authData);

            if (loginResponse.statusCode() == 200) {
                String courierId = loginResponse.jsonPath().getString("id");

                given().delete(Endpoints.CREATE_COURIER.get() + courierId);
                //System.out.println("ID - "+courierId);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при очистке тестовых данных: " + e.getMessage());
        }
    }

    @Step("Удаление тестового заказа")
    private void deleteTestOrder(String createdOrderTrack) {
        try {
            given().delete(Endpoints.ORDERS.get() + createdOrderTrack);

        } catch (Exception e) {
            System.out.println("Ошибка при очистке тестовых данных: " + e.getMessage());
        }
    }
}

