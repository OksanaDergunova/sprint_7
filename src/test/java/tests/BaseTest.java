package tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierAuth;
import org.junit.After;
import org.junit.BeforeClass;
import steps.CourierSteps;
import steps.OrderSteps;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected CourierSteps courierSteps = new CourierSteps();
    protected OrderSteps orderSteps = new OrderSteps();

    //данные для очистки
    protected String createdCourierLogin;
    protected String createdCourierPass;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @After
    public void cleanup() {
        if (createdCourierLogin != null && createdCourierPass != null) {
            deleteTestCourier(createdCourierLogin, createdCourierPass);
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

                given().delete("/api/v1/courier/" + courierId);
                //System.out.println("ID - "+courierId);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при очистке тестовых данных: " + e.getMessage());
        }
    }
}

