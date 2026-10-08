package restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

  RequestSpecification requestSpecification;
  ResponseSpecification validResponseSpecification;

  @BeforeClass
  public void beforeClass() {
    requestSpecification = new RequestSpecBuilder()
        .log(LogDetail.ALL)
        .setBaseUri("https://jsonplaceholder.typicode.com")
        // .addHeader("Content-Type", "application/json")
        .setContentType(ContentType.JSON)
        .build();

    validResponseSpecification = new ResponseSpecBuilder()
        .log(LogDetail.ALL)
        .expectStatusCode(200)
        .expectResponseTime(lessThan(3000L))
        .build();


  }

  @Test
  public void getUserByIdTest() {
    given()
        .spec(requestSpecification)
        .pathParam("id", 1)
        .when()
        .get("/users/{id}")
        .then()
        .spec(validResponseSpecification);
  }

  @Test
  public void getUserByIdResponseTest() {
    given()
        .spec(requestSpecification)
        .pathParam("id", 1)
        .when()
        .get("/users/{id}")
        .then()
        .spec(validResponseSpecification)
        .body("id", equalTo(1))
        .body("name", equalTo("Leanne Graham"))
        .body("email", notNullValue());
  }
  @Test
  public void createPostAndExtractUserIdTest() {
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("title", "Learn RestAssured");
    requestBody.put("body", "Step-by-step API test automation");
    requestBody.put("userId", 42);

    Response response = given()
        .spec(requestSpecification)
        .body(requestBody)
        .when()
        .post("/posts")
        .then()
        .log().all()
        .statusCode(201)
    .body("id", notNullValue())
    .body("title", equalTo("Learn RestAssured"))
        .extract().response();

    int userId = response.jsonPath().getInt("userId");

    Assert.assertEquals(userId, 42);

  }

  @Test
  public void testMultipleDataInResponse(){
    given()
        .spec(requestSpecification)
        .get("/users")
        .then()
        .spec(validResponseSpecification)
        .body("name", hasItem("Glenna Reichert"))
        .body("email", everyItem(containsString("@")))
        .body("", hasSize(greaterThanOrEqualTo(10)));

  }
}
