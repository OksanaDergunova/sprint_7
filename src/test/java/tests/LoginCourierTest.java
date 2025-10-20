package tests;

import data.TestData;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.Courier;
import models.CourierAuth;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest extends BaseTest{

    private String login;
    private String password;
    private String firstName;
    private Courier courier;

    @Before
    public void before(){
        login = TestData.getRandomLogin();
        password = TestData.getRandomPassword();
        firstName = TestData.getRandomFirstName();
        courier = new Courier(login, password, firstName);
    }

    @Test
    @Description("Успешная авторизация курьера")
    public void loginCourierTest() {

        courierSteps.createCourier(courier);

        // Пытаемся авторизоваться
        CourierAuth courierAuth = new CourierAuth(login,password);
        Response response = courierSteps.loginCourier(courierAuth);

        courierSteps.checkCourierLoginSuccessfully(response);
    }

    @Test
    @Description("Ошибка авторизации с неверным паролем")
    public void loginWithWrongPasswordTest() {

        Courier courier = new Courier(login, password, firstName);
        courierSteps.createCourier(courier);

        // Пытаемся авторизоваться с неверным паролем
        CourierAuth wrongAuth = new CourierAuth(login, "wrong_password");
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkLoginWithWrongPass(response);
    }

    @Test
    @Description("Ошибка авторизации с неверным логином")
    public void loginWithWrongLoginTest() {

        courierSteps.createCourier(courier);

        // Пытаемся авторизоваться с неверным логином
        CourierAuth wrongAuth = new CourierAuth("wrong_login", password);
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkLoginWithWrongPass(response);
    }

    @Test
    @Description("Ошибка авторизации без логина")
    public void loginWithoutLoginTest() {

        courierSteps.createCourier(courier);

        // Пытаемся авторизоваться без логина
        CourierAuth wrongAuth = new CourierAuth("", password);
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkInsufficientError(response);
    }

    @Test
    @Description("Ошибка авторизации без пароля")
    public void loginWithoutPassTest() {

        courierSteps.createCourier(courier);

        // Пытаемся авторизоваться без пароля
        CourierAuth wrongAuth = new CourierAuth(login, "");
        Response response = courierSteps.loginCourier(wrongAuth);

        courierSteps.checkInsufficientError(response);
    }

    @After
    public void after(){
        createdCourierLogin = login;
        createdCourierPass = password;
    }
}
