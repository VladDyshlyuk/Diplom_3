package org.example.api;

import io.restassured.response.Response;
import org.example.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String API_AUTH_REGISTER = "api/auth/register";
    private static final String API_AUTH_USER = "api/auth/user";
    private static final String API_AUTH_LOGIN = "api/auth/login";

    public Response create(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(API_AUTH_REGISTER);
    }


    public Response auth(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(API_AUTH_LOGIN);
    }

    public Response delete(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .delete(API_AUTH_USER);
    }


}