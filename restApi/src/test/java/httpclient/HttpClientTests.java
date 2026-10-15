package httpclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientTests {

  @Test
  public void echoTest() throws IOException {
    HttpClient httpClient = HttpClients.createDefault();

    HttpPost postRequest = new HttpPost("https://postman-echo.com/post");

    HttpEntity body = new StringEntity("{\"message\":\"Hello World!\"}");

    postRequest.setEntity(body);

    HttpResponse response = httpClient.execute(postRequest);
    Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);//200

    //System.out.println(new String(response.getEntity().getContent().readAllBytes()));

    ObjectMapper mapper = new ObjectMapper();

    //JsonNode jsonNode = mapper.readValue(response.getEntity().getContent().readAllBytes(), JsonNode.class);

   // System.out.println(jsonNode.toPrettyString());

    PostmanEchoResponse postmanEchoResponse = mapper.readValue(response.getEntity().getContent(), PostmanEchoResponse.class);

    System.out.println(postmanEchoResponse.getData().toString());

  }
}
