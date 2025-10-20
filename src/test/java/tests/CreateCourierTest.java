package tests;

import data.TestData;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.Courier;
import org.junit.Test;


public class CreateCourierTest extends BaseTest{

    @Test
    @Description("Успешное создание курьера")
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
    @Description("Нельзя создать двух одинаковых курьеров")
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
    @Description("Нельзя создать курьера, не введя логин")
    public void createCourierWithoutLogin() {
        String login = "";
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        Response response = courierSteps.createCourier(courier);

        courierSteps.checkInsufficientDataError(response);
    }

    @Test
    @Description("Нельзя создать курьера, не введя пароль")
    public void createCourierWithoutPass() {
        String login = TestData.getRandomLogin();
        String password = "";
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        Response response = courierSteps.createCourier(courier);

        courierSteps.checkInsufficientDataError(response);
    }
}

