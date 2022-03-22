package com.multimodule.apitesting.driver;

import com.multimodule.helpers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Map;

public class RequestBuilder {

    private RequestSpecification request;
    private RequestSpecBuilder builder;
    private final Logger LOGGER = LogManager.getLogger(RequestBuilder.class);

    public RequestBuilder()
    {
        builder = new RequestSpecBuilder();
        builder.setRelaxedHTTPSValidation();
    }

    public RequestBuilder setAcceptContent(ContentType contentType)
    {
        LOGGER.debug("Accept content type : "+ contentType.toString());
        builder.setAccept(contentType);
        return this;
    }

    public RequestBuilder setAcceptContent(String contentType)
    {
        LOGGER.debug("Accept content type : "+ contentType.toString());
        builder.setAccept(contentType);
        return this;
    }


    public RequestBuilder setContentType(ContentType contentType)
    {
        LOGGER.debug("Set content type : "+ contentType.toString());
        builder.setContentType(contentType);
        return this;
    }

    public RequestBuilder setContentType(String contentType)
    {
        LOGGER.debug("Set content type : "+ contentType);
        builder.setContentType(contentType);
        return this;
    }


    public RequestBuilder setBaseURL(String baseURL)
    {
        LOGGER.debug("Base url is "+ baseURL);
        builder.setBaseUri(baseURL);
        return this;
    }

    public RequestBuilder setBody(String body)
    {
        LOGGER.debug("request body is " + body);
        builder.setBody(body);
        return this;
    }

    public RequestBuilder setBody(JSONObject body)
    {
        LOGGER.debug("request body is " + body);
        builder.setBody(body);
        return this;
    }

    public RequestBuilder setPathParm(String path)
    {
        LOGGER.debug("request path is " + path);
        builder.setBasePath(path);
        return this;
    }

    public RequestBuilder setHeader(Map<String,String> headers)
    {
        LOGGER.debug("request headers is "+ headers);
        builder.addHeaders(headers);
        return this;
    }

    public RequestBuilder setCookies(Map<String,String> cookies)
    {
        LOGGER.debug("request cookies is "+ cookies);
        builder.addCookies(cookies);
        return this;
    }

    public RequestBuilder setRequestBody(String requestBodyPath)
    {
            try {
                String requestBody = TestResourcesReaders.readTestResourceFileAsString(requestBodyPath);
                LOGGER.debug("request body "+ requestBody);
                builder.setBody(requestBody);

            } catch (Exception e) {
                LOGGER.error("couldn't set request body");
                e.printStackTrace();
            }

        return this;
    }

    public RequestSpecification build ()
    {
         request = builder.build().log().all();
         return request;
    }



}
