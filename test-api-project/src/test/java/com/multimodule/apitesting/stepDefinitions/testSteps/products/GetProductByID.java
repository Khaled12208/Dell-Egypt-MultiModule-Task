package com.multimodule.apitesting.stepDefinitions.testSteps.products;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.Assertions;
import com.multimodule.apitesting.driver.ResponseConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en_scouse.An;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetProductByID{

    private TestBase base;
    private ResponseConfig response;
    public GetProductByID(TestBase base) {
        this.base = base;

    }

    @Given("user send a request to get {string} with valid {string}")
    public void userSendARequestToGetWithValid(String path, String id) {
        response= new ResponseConfig( RestAssured.given(base.request.build()).get(path.replaceAll("\\{id}",id)));

    }
    @Then("Positive status code is {string}")
    public void theResponseCode(String code) {
        response.doAssert().statusCode(code);
    }

    @And("get product response body is {string}")
    public void theProductResponseBodyIs(String responseBodyPath) throws Exception {
        response.doAssert().bodyContentData(responseBodyPath);
    }


    @Given("user send a request to get {string} with invalid {string}")
    public void userSendARequestToGetCategoryWithInvalid(String path, String id) {
        response= new ResponseConfig(RestAssured.given(base.request.build()).get(path.replaceAll("\\{id}",id)));
    }

    @Then("Negative status code is {string}")
    public void userSendARequestToGetCategoryWithInvalidResponseCode(String code) throws Exception {
        response.doAssert().statusCode(code);

    }

    @And("there is no {string} found for this id")
    public void userSendARequestToGetCategoryWithInvalidResponseBody(String string) {
        response.doAssert().assertErrorMessage(string);

    }


}
