package com.multimodule.uitesting.stepDefinitions.testSteps;


import com.multimodule.uitesting.pages.InventoryPage;
import com.multimodule.uitesting.pages.LoginPage;
import com.multimodule.uitesting.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LogoutTestSteps {
    private TestBase base;
    private LoginPage login;
    private InventoryPage inventory;

    public LogoutTestSteps(TestBase base) {
        this.base = base;
    }


    @When("user click on menu button")
    public void userClickOnMenuButton() throws InterruptedException {
       inventory = new InventoryPage(base.getDriver());
       inventory.verifyPageLoaded();
       inventory.addItemByName("Sauce Labs Bike Light");
       Thread.sleep(10000);
       inventory.clickMenu();
    }

    @And("click on logout button")
    public void clickOnLogoutButton() {
        inventory.clickLogOut();

    }
    @Then("user has been logged out and in login page again")
    public void userLoggedOutToLoginPage() {

    }
}
