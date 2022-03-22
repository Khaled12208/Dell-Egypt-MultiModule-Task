package com.multimodule.apitesting.stepDefinitions.testSteps.products;

import com.multimodule.apitesting.APIUtils;
import com.multimodule.apitesting.base.TestBase;
import com.multimodule.apitesting.driver.Assertions;
import com.multimodule.apitesting.driver.ResponseConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteProduct{

    private TestBase base;
    private ResponseConfig response;
    private int productID;
    private Assertions assertions;

    public DeleteProduct(TestBase base) {
        this.base = base;
    }

    @Given("A PRE-created product with data {string}")
    public void a_pre_created_product_with_data(String string) {
       response = new ResponseConfig(RestAssured.given(
                base.request.setRequestBody(string).setContentType(ContentType.JSON).build()
        ).post("/products"));

    }

    @Given("The user wants to delete this product using the ID")
    public void the_user_wants_to_delete_this_product_using_the_id() {
        productID= APIUtils.getPostedProductID(response.getResponse());
    }

    @When("user sends a delete request to {string} where id is the value of product ID")
    public void user_sends_a_delete_request_to_where_id_is_the_value_of_product_id(String path) {
        response= new ResponseConfig(
                RestAssured
                        .given(base.request.build())
                        .delete(path.replaceAll("\\{id}", String.valueOf(this.productID)))
        );
    }

    @Then("deleted product status code is {string}")
    public void deleted_product_status_code_is(String string) {

        response.doAssert().statusCode(string);
    }

    @Then("if the user try it to get this product the status code is {string}")
    public void if_the_user_try_it_to_get_this_product(String code) {
        response= new ResponseConfig( RestAssured.given(base.request.build()).get("/products/"+productID));
        response.doAssert().statusCode(code);
    }
}
