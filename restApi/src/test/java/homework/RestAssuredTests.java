package homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class RestAssuredTests {

    @Test
    public void verifyStatusCode() {
        given().
            when().get("http://jsonplaceholder.typicode.com/posts").
                then().statusCode(200).
                extract().response().print();
    }

    @Test
    public void verifyHeader() {
        Response response = given().
                when().get("http://jsonplaceholder.typicode.com/posts").
                then().statusCode(200).header("content-type", "application/json; charset=utf-8").
                extract().response();

        Assert.assertTrue(response.getHeaders().hasHeaderWithName("content-type"));
        Assert.assertEquals(response.getHeader("content-type"), "application/json; charset=utf-8");
    }

    @Test
    public void verifyBody() {
        given().
                when().get("http://jsonplaceholder.typicode.com/posts").
                then().statusCode(200).assertThat().body("size()",is(100));
    }

    @Test
    public void createPost() {

        int id = given().
                body(new User()).
                contentType(ContentType.JSON).
                when().post("http://jsonplaceholder.typicode.com/posts").
                then().assertThat().statusCode(201).body("isEmpty()", Matchers.is(false)).
                extract().path("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = "createPost")
    public void updatePost() {

        given().
                body("{\n" +
                        "    id: 1,\n" +
                        "    title: 'foo',\n" +
                        "    body: 'bar',\n" +
                        "    userId: 1\n" +
                        "  }\n").
                when().put("http://jsonplaceholder.typicode.com/posts/1").
                then().assertThat().statusCode(200).body("id", is(1)).extract().response().print();
    }

    @Test()
    public void DeletePost() {

        given().
                when().delete("http://jsonplaceholder.typicode.com/posts/1").
                then().assertThat().statusCode(200);
    }

    @Test
    public void getUsers() {

        Response response = given().
                when().get("http://jsonplaceholder.typicode.com/users").
                then().statusCode(200).
                assertThat().body("size()", is(10)).
                body("find{it.name.equals('Leanne Graham')}.id", is(1)).
                extract().response();

        Assert.assertEquals(response.path("find{it.name.equals('Leanne Graham')}.username"), "Bret");
    }
}
