package com.itacademy.aqa.webdriver;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static com.itacademy.aqa.webdriver.Constants.BASE_URL;
import static io.restassured.RestAssured.given;

public class RestTest {
    @Test
    public void testRestAssured(){

        ExtractableResponse response = given()
                .header("Content-Type", ContentType.TEXT)
                .body("{\"Hello\":\"World\"}")
                .post(BASE_URL + "/post")
                .then()
                .assertThat()
                .statusCode(200).extract();

        System.out.println(response.body().asPrettyString());

    }
}