package com.multimodule.uitesting.stepDefinitions.testSteps;

import com.multimodule.uitesting.pages.InventoryPage;
import com.multimodule.uitesting.pages.LoginPage;
import com.multimodule.uitesting.testbase.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortProductsTestSteps{
    private TestBase base;
    private LoginPage login;
    private InventoryPage inventory;

    public SortProductsTestSteps (TestBase base)
    {
        this.base = base;
    }

    @Given("user is logged in with standard account user {string} and password {string}")
    public void user_is_logged_in_with_standard_account_user_and_password(String userName, String userPassword) {
        login = new LoginPage(base.getDriver());
        login.verifyPageLoaded();
        login.enterCredentials(userName,userPassword);
        login.clickOnLoginBtn();
    }

    @When("select  one of the sorting type {string}")
    public void select_one_of_the_sorting_type(String sortType) throws InterruptedException {
        inventory = new InventoryPage(base.getDriver());
        inventory.verifyPageLoaded();
        inventory.sortItemsBy(sortType);
    }

    @Then("the items should be sorted based the selection {string}")
    public void the_items_should_be_sorted_based_the_selection(String sortType) {

        inventory.verifySorting(sortType);

    }

}
