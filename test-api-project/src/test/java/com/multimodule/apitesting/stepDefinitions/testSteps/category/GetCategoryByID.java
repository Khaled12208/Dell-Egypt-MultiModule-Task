package com.multimodule.apitesting.stepDefinitions.testSteps.category;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.ResponseConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

public class GetCategoryByID {

    private TestBase base;
    private ResponseConfig response;

    public GetCategoryByID(TestBase base)
    {
        this.base=base;
    }

    @Given("user send a request to get category {string} with {string}")
    public void user_send_a_request_to_get_category_with(String string, String string2) {
        response= new ResponseConfig( RestAssured.given(base.request.build()).get(string.replaceAll("\\{id}",string2)));
    }

    @Then("category status code is {string}")
    public void category_status_code_is(String string) {
        response.doAssert().statusCode(string);
    }

    @And("get category response body is {string}")
    public void get_category_response_body_is(String string) throws Exception {
        response.doAssert().bodyContentData(string);
    }

    @And("there is no {string} found for this category id")
    public void get_category_error_response_body_is(String string) {
        response.doAssert().assertErrorMessage(string);

    }


}
