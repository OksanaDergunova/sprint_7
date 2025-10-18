package tests;

import data.TestData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Courier;
import org.junit.Test;


public class CreateCourierTest extends BaseTest{

    @Test
    @DisplayName("Успешное создание курьера")
    public void createCourierTest() {
        String login = TestData.getRandomLogin();
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        Response response = courierSteps.createCourier(courier);

        createdCourierLogin = login;
        createdCourierPass = password;

        courierSteps.checkCourierCreatedSuccessfully(response);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createConflictCourier() {
        String login = TestData.getRandomLogin();
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier1 = new Courier(login, password, firstName);
        courierSteps.createCourier(courier1);

        Courier courier2 = new Courier(login, password, firstName);
        Response response = courierSteps.createCourier(courier2);

        createdCourierLogin = login;
        createdCourierPass = password;
        courierSteps.checkCourierConflict(response);
    }

    @Test
    @DisplayName("Нельзя создать курьера, не введя все поля")
    public void createCourierWithoutLogin() {
        String login = "";
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        Response response = courierSteps.createCourier(courier);

        courierSteps.checkInsufficientDataError(response);
    }
}

