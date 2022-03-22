package com.multimodule.apitesting.stepDefinitions.testSteps.products;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.Assertions;
import com.multimodule.apitesting.driver.ResponseConfig;
import com.multimodule.helpers.TestResourcesReaders;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.restassured.RestAssured;

public class PostNewProduct{
    private TestBase base;
    private ResponseConfig response;
    private Assertions assertions;

    public PostNewProduct(TestBase base) {
        this.base = base;

    }

    @Given("user wants to add a product using post {string}")
    public void user_wants_to_add_a_product_using_post(String string) {
        base.request.setPathParm(string);

    }
    @And("new product body Content type is {string}")
    public void user_data_contentType(String string) throws Exception {
        base.request.setContentType(string);
    }

    @When("User send a post request to update with {string}")
    public void user_send_a_post_request_to_update_with(String string) throws Exception {
        String body= TestResourcesReaders.readTestResourceFileAsString(string.trim());
        response= new ResponseConfig(RestAssured.given(base.request.setBody(body).build()).post());
    }

    @Then("new product response status code is {string}")
    public void response_statuscode_is(String statusCode) {
        response.doAssert().statusCode(statusCode);
    }
    @And("new product response header contains {string} header")
    public void response_header_contains(String headerName) {
        response.doAssert().containsHeaders(headerName);
    }


    @And("new product response body  should be is empty")
    public void response_body_is_empty() {
        response.doAssert().emptyBody();
    }

    @Then("new product response body should match {string}")
    public void response_body_should_match(String string) throws Exception {
        response.doAssert().bodySchema(string);
    }


}


