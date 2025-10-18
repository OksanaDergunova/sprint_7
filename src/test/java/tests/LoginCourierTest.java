package tests;

import data.TestData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Courier;
import models.CourierAuth;
import org.junit.Test;

public class LoginCourierTest extends BaseTest{

    @Test
    @DisplayName("Успешная авторизация курьера")
    public void loginCourierTest() {
        String login = TestData.getRandomLogin();
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        courierSteps.createCourier(courier);

        createdCourierLogin = login;
        createdCourierPass = password;

        // Пытаемся авторизоваться
        CourierAuth courierAuth = new CourierAuth(login,password);
        Response response = courierSteps.loginCourier(courierAuth);

        courierSteps.checkCourierLoginSuccessfully(response);
    }

    @Test
    @DisplayName("Ошибка авторизации с неверным паролем")
    public void loginWithWrongPasswordTest() {
        String login = TestData.getRandomLogin();
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        courierSteps.createCourier(courier);

        createdCourierLogin = login;
        createdCourierPass = password;

        // Пытаемся авторизоваться с неверным паролем
        CourierAuth wrongAuth = new CourierAuth(login, "wrong_password");
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkLoginWithWrongPass(response);
    }

    @Test
    @DisplayName("Ошибка авторизации с неверным логином")
    public void loginWithWrongLoginTest() {
        String login = TestData.getRandomLogin();
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        courierSteps.createCourier(courier);

        createdCourierLogin = login;
        createdCourierPass = password;

        // Пытаемся авторизоваться с неверным логином
        CourierAuth wrongAuth = new CourierAuth("wrong_login", password);
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkLoginWithWrongPass(response);
    }

    @Test
    @DisplayName("Ошибка авторизации без логина")
    public void loginWithoutLoginTest() {
        String login = TestData.getRandomLogin();
        String password = TestData.getRandomPassword();
        String firstName = TestData.getRandomFirstName();

        Courier courier = new Courier(login, password, firstName);
        courierSteps.createCourier(courier);

        createdCourierLogin = login;
        createdCourierPass = password;

        // Пытаемся авторизоваться с неверным паролем
        CourierAuth wrongAuth = new CourierAuth("", password);
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkInsufficientLoginError(response);
    }
}
