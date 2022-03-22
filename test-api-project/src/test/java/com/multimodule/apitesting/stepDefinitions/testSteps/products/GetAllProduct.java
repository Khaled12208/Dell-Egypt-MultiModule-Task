package com.multimodule.apitesting.stepDefinitions.testSteps.products;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.Assertions;
import com.multimodule.apitesting.driver.HttpStatusCode;
import com.multimodule.apitesting.driver.RequestBuilder;
import com.multimodule.apitesting.driver.ResponseConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetAllProduct{

    private TestBase base;
    private ResponseConfig response;

    public GetAllProduct(TestBase base) {
        this.base = base;
    }

    @Given("User send a get request to {string}")
    public void userSendAGetRequestTo(String pathParm) {
        response= new ResponseConfig(RestAssured.given().spec(base.request.build()).get(pathParm));

    }

    @Then("status code is {string}")
    public void statusCodeIs(String string) {
        response.doAssert().statusCode(string);
    }

    @And("response body matches {string} in Api contract")
    public void responseBodyMatchesInApiContract(String string) throws Exception {
        response.doAssert().bodySchema(string);

    }
}
