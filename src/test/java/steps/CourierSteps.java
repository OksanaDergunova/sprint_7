package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Courier;
import models.CourierAuth;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierSteps {

    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Логин курьера")
    public Response loginCourier(CourierAuth courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    //Create
    @Step("Проверка успешного создания курьера")
    public void checkCourierCreatedSuccessfully(Response response) {
        response.then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Step("Проверка конфликта при создании курьера")
    public void checkCourierConflict(Response response) {
        response.then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Проверка ошибки при неполных данных")
    public void checkInsufficientDataError(Response response) {
        response.then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    //Login
    @Step("Проверка успешной авторизации курьера")
    public void checkCourierLoginSuccessfully(Response response) {
        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Step("Запрос с несуществующей парой логин-пароль")
    public void checkLoginWithWrongPass(Response response){
        response.then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Проверка авторизации при неполных данных")
    public void checkInsufficientLoginError(Response response) {
        response.then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

}
