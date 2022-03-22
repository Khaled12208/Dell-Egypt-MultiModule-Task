package com.multimodule.apitesting.driver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.multimodule.helpers.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;

import java.io.InputStream;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Assertions {


    private final Logger LOGGER = LogManager.getLogger(Assertions.class);

    private Response response;

    public Assertions(Response response) {
        this.response = response;
        LOGGER.debug("Start assertion process");
    }

    public void bodyContentData(String exceptedBodyPath) throws Exception {

        String expectation = TestResourcesReaders.readTestResourceFileAsString(exceptedBodyPath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode exceptedJson = mapper.readTree(expectation);
        JsonNode actualJson = mapper.readTree(response.getBody().asString());
        assertEquals(exceptedJson, actualJson);
    }

    public void bodySchema(String exceptedSchemaPath) throws Exception {
        InputStream expectation = TestResourcesReaders.readTestResourceFileAsInputStream(exceptedSchemaPath);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(expectation));

    }

    public void statusCode(HttpStatusCode code) {
        response.then().assertThat().statusCode(code.getValue());
    }
    public void statusCode(String code) {
        response.then().assertThat().statusCode(Integer. parseInt(code));
    }

    public void containsHeaders(Map<String, String> headers) {
        response.then().assertThat().headers(headers);

    }

    public void containsHeaders(String headerName) {

        Assert.assertTrue( response.getHeader(headerName) !=null);
    }

    public void containsCookies(Map<String, String> cookies) {
        response.then().assertThat().cookies(cookies);
    }

    public void ContentType(ContentType contentType)
    {
        response.then().assertThat().contentType(contentType);
    }

    public void emptyBody()
    {
        response.then().body("isEmpty()", Matchers.is(true));

    }

    public void assertErrorMessage(String message)
    {
        response.then().assertThat().body("message",equalTo(message));
    }


}
