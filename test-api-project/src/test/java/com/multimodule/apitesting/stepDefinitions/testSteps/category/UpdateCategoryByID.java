package com.multimodule.apitesting.stepDefinitions.testSteps.category;

import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.ResponseConfig;
import com.multimodule.helpers.TestResourcesReaders;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateCategoryByID {

    private TestBase base;
    private ResponseConfig response;
    private String path;

    public UpdateCategoryByID(TestBase base) {
        this.base = base;

    }
    @Given("user wants to update a category using patch {string} with id {string}")
    public void user_wants_to_update_a_category_using_patch_with_id(String string, String string2) {
        this.path=string.replaceAll("\\{id}",string2);
    }

    @And("update category body content type is {string}")
    public void update_category_body_content_type_is(String string) {
        base.request.setContentType(string);
    }

    @When("User send a patch request to update category with {string}")
    public void user_send_a_patch_request_to_update_category_with(String string) throws Exception {
        response = new ResponseConfig( RestAssured
                .given(base.request.setRequestBody(string).setContentType(ContentType.JSON).build()).patch(path));    }

    @Then("update category response status code is {string}")
    public void update_category_response_status_code_is(String string) {
        response.doAssert().statusCode(string);
    }

    @And("update category response header contains {string} header")
    public void update_category_response_header_contains_header(String string) {
        response.doAssert().containsHeaders(string);
    }

    @And("update category response body matches {string}")
    public void update_category_response_body_matches(String string) throws Exception {
        response.doAssert().bodySchema(string);
    }


}
