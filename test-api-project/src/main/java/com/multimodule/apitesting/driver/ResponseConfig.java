package com.multimodule.apitesting.driver;

import com.multimodule.helpers.TestResourcesReaders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ResponseConfig {

    private RequestSpecification request;
    private Response response;
    private Assertions customAssert;
    private final Logger LOGGER = LogManager.getLogger(ResponseConfig.class);


    public ResponseConfig(Response response)
    {
        response.then().log().all();
        customAssert= new Assertions(response);
        this.response=response;
        LOGGER.debug("response body is "+ response.getBody().toString());

    }
    public Response getResponse()
    {
        return response;
    }
    public Assertions doAssert()
    {
        return customAssert;
    }




}
