package com.itacademy.aqa.webdriver;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import org.junit.Assert;
import org.junit.Test;

import static com.itacademy.aqa.webdriver.Constants.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestTest {
    @Test
    public void testRestAssured() {

        ExtractableResponse response = given()
                .log().all()
                .header("Content-Type", ContentType.TEXT)
                .body("{\"Hello\":\"World\"}")
                .when()
                .post(BASE_URL + "/post")
                .then()
                .assertThat()
                .statusCode(200).extract();

        System.out.println(response.body().asPrettyString());

    }

    @Test
    public void testVerifies404IfWrongUri() {

        ExtractableResponse response = given()
                .log().all()
                .header("Content-Type", ContentType.TEXT)
                .body("{\"Hello\":\"World\"}")
                .when()
                .post(BASE_URL + "/postman")
                .then()
                .assertThat()
                .statusCode(404).extract();

        System.out.println(response.body().asPrettyString());

    }

    @Test
    public void testResponseDataField() {

        String requestBody = "{\"Hello\":\"World\"}";

        ResponseBody responseBody = given()
                .log().all()
                .header("Content-Type", ContentType.TEXT)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .extract().as(ResponseBody.class);

        Assert.assertEquals(requestBody, responseBody.getJsonBody());
        Assert.assertEquals("https",
                responseBody.getHeaders().get("x-forwarded-proto"));
    }

    @Test
    public void testResponseBodyVerification() {

        RequestBody requestBody = new RequestBody();
        requestBody.setHello("World");

        given()
                .log().all()
                .header("Content-Type", ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .body("data.Hello", equalTo("World"));

    }
}