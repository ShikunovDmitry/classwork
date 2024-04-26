package com.itacademy.aqa.webdriver;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.itacademy.aqa.webdriver.Constants.BASE_URL;

public class HttpTest {

    @Test
    public void testCheckForValidFormData() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(BASE_URL + "/post");

        httpPost.setHeader("Content-Type","text/plain");

        HttpEntity httpEntity = new StringEntity("{\"Hello\":\"World\"}");

        httpPost.setEntity(httpEntity);

        HttpResponse response = httpClient.execute(httpPost);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        System.out.println(EntityUtils.toString(response.getEntity()));

    }

}
