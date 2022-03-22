package com.multimodule.apitesting.stepDefinitions.testSteps.category;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.ResponseConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

public class GetAllCategories {


    private TestBase base;
    private ResponseConfig response;
    private String path;

    public GetAllCategories(TestBase base)
    {
        this.base=base;
    }

    @Given("User send a get Categories request to {string}")
    public void user_send_a_get_categories_request_to(String pathParm) {
        response= new ResponseConfig(RestAssured.given().spec(base.request.build()).get(pathParm));
    }

    @Then("Categories status code is {string}")
    public void categories_status_code_is(String string) {
        response.doAssert().statusCode(string);
    }

    @And("Categories response body matches {string} in Api contract")
    public void categories_response_body_matches_in_api_contract(String string) throws Exception {
        response.doAssert().bodySchema(string);
    }

}
