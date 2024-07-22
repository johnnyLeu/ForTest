package com.aesys.exerciceTest.airlines;


import com.aesys.exerciceTest.utils.RestUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class AirlineTests {

    @Test
    public void createAirline(){

        String endPoint = "https://api.instantwebtools.net/v1/airlines";
        Map<String, Object> payload = Payloads.getCreateAirlinePayloadFromMap("12", "abc airline", "IT", "ABC", "slogan", "example", "sitoweb", "established");
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
