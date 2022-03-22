package com.multimodule.apitesting.stepDefinitions.testSteps.category;

import com.multimodule.apitesting.APIUtils;
import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.HttpStatusCode;
import com.multimodule.apitesting.driver.ResponseConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteCategory {

    private TestBase base;
    private ResponseConfig response;
    private String path;

    public DeleteCategory(TestBase base)
    {
        this.base=base;
    }


    @Given("A PRE-created category with data {string}")
    public void a_pre_created_category_with_data(String string) {
        response = new ResponseConfig(RestAssured.given(
                base.request.setRequestBody(string).setContentType(ContentType.JSON).build()
        ).post("/categories"));
        response.doAssert().statusCode(HttpStatusCode.CREATED);
    }


    @When("user sends a delete category request to {string} with ID {string} where id is the value of category ID")
    public void user_sends_a_delete_category_request_to_where_id_is_the_value_of_category_id(String url, String id) {
        response= new ResponseConfig(
                RestAssured
                        .given(base.request.build())
                        .delete(url.replaceAll("\\{id}", id))
        );
    }
    @Then("deleted category status code is {string}")
    public void deleted_category_status_code_is(String string) {
        response.doAssert().statusCode(string);
    }


}
