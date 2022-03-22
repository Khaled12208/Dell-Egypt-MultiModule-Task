package com.multimodule.apitesting.stepDefinitions.testSteps.products;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.Assertions;
import com.multimodule.apitesting.driver.ResponseConfig;
import com.multimodule.helpers.TestResourcesReaders;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateProductByID{

    private TestBase base;
    private ResponseConfig response;
    private String path;

    public UpdateProductByID(TestBase base) {
        this.base = base;

    }
    @Given("user wants to update a product using patch {string} with id {string}")
    public void user_wants_to_update_a_product_with_id(String path, String id) {
        this.path=path.replaceAll("\\{id}",id);
    }
    @And("update product body content type is {string}")
    public void update_product_body_content_type_is(String string) {
        base.request.setContentType(string);
    }

    @When("User send a patch request to update with {string}")
    public void user_send_a_patch_request_to_update_with(String string) throws Exception {
        String body= TestResourcesReaders.readTestResourceFileAsString(string.trim());
        response = new ResponseConfig( RestAssured
            .given(base.request.setBody(body).setContentType(ContentType.JSON).build()).patch(path));

    }

    @Then("update product response status code is {string}")
    public void updateProductData_validBody_StatusCode(String string) {
        response.doAssert().statusCode(string);


    }
    @And("update product response header contains {string} header")
    public void updateProductData_validHeaders_HeadersContains(String string) throws Exception {
        response.doAssert().containsHeaders(string);

    }

    @And("update product response body matches {string}")
    public void updateProductData_validBody_BodyMatches(String string) throws Exception {
        response.doAssert().bodySchema(string);

    }



}
