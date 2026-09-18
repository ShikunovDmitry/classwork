package restassured;

import httpclient.PostmanEchoResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
  @Test
    public void postmanEcho() {
    Response response = given().log().all()
        .body("{\"message\":\"Hello World!\"}")
        .post("https://postman-echo.com/post")
        .then()
        .statusCode(200).log().all().extract().response();

    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    System.out.println(response.prettyPrint());
  }

  @Test
  public void postmanEcho2() {
    String data = given()
        .body("{\"message\":\"Hello World!\"}")
        .post("https://postman-echo.com/post")
        .then()
        .statusCode(200).extract().path("data");

    System.out.println(data);
  }

  @Test
  public void postmanEcho3() {
    PostmanEchoResponse postmanEchoResponse = given()
        .body("{\"message\":\"Hello World!\"}")
        .post("https://postman-echo.com/post")
        .then()
        .statusCode(200).extract().body().as(PostmanEchoResponse.class);

    Assert.assertTrue(postmanEchoResponse.getData().toString().contains("Hello World!"));
  }

  //here we pass Java object as a body
  @Test
  public void postmanEcho4() {
    RequestBody body = new RequestBody();
    body.setMessage("Hello World!");
    PostmanEchoResponse postmanEchoResponse = given()
        .body(body)
        .post("https://postman-echo.com/post")
        .then()
        .statusCode(200).extract().body().as(PostmanEchoResponse.class);

    Assert.assertTrue(postmanEchoResponse.getData().toString().contains("Hello World!"));
  }

  //Give - preconditions
  //When - action
  //Then - assert

  //Given: I am on the Login page
  //When: user enter userName and password and click the login button
  //Then: login page is opened
}
