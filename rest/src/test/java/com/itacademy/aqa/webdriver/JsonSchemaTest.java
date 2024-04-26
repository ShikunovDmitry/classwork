package com.itacademy.aqa.webdriver;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.io.File;

import static com.itacademy.aqa.webdriver.Constants.BASE_URL;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.core.IsEqual.equalTo;

public class JsonSchemaTest {
    @Test
    public void testResponseBodyVerification() {

        RequestBody requestBody = new RequestBody();
        requestBody.setHello("World");

        given()
                .log().all()
                .header("Content-Type", ContentType.JSON)
                .body(requestBody)
                .queryParams("key","value")
                .when()
                .post(BASE_URL + "/post")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File("src/test/resources/jsonSchema.json")));

    }
}
