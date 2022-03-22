package com.multimodule.apitesting.stepDefinitions.testSteps.category;

import com.multimodule.apitesting.APIUtils;
import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.ResponseConfig;
import com.multimodule.helpers.TestResourcesReaders;
import com.multimodule.helpers.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class PostNewCategory{

    private TestBase base;
    private ResponseConfig response;

    public PostNewCategory(TestBase base)
    {
        this.base=base;
    }


    @Given("user wants to add a category using post {string}")
    public void user_wants_to_add_a_category_using_post(String string) {
        base.request.setPathParm(string);
    }

    @Given("new category body Content type is {string}")
    public void new_category_body_content_type_is(String string) {
        base.request.setContentType(string);
    }

    @When("User send a post request to set new category with unique ID and name {string}")
    public void user_send_a_post_request_to_set_new_category_with_unique_id_and_name(String string) {
        Map<String,String> body = new HashMap<>();
        body.put("name",string);
        body.put("id", RandomStringUtils.random(8, true, true));
        response= new ResponseConfig(RestAssured.given(base.request.setBody(APIUtils.generateJSONUsing(body)).build()).post());
    }

    @Then("new category response status code is {string}")
    public void new_category_response_status_code_is(String string) {
        response.doAssert().statusCode(string);
    }

    @Then("new category response header contains {string} header")
    public void new_category_response_header_contains_header(String string) {
        response.doAssert().containsHeaders(string);
    }

    @Then("new category response body  should be is empty")
    public void new_category_response_body_should_be_is_empty() {
        response.doAssert().emptyBody();
    }


    @Then("new category response body should match {string}")
    public void new_category_response_body_should_match(String string) throws Exception {
        response.doAssert().bodySchema(string);
    }

    @When("User send a post request to add category with {string}")
    public void user_send_a_post_request_to_add_category_with(String string) {
        response= new ResponseConfig(RestAssured.given(base.request.setRequestBody(string).build()).post());


    }

}
