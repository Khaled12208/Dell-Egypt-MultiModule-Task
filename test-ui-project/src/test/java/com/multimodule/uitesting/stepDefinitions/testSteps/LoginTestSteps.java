package com.multimodule.uitesting.stepDefinitions.testSteps;


import com.multimodule.helpers.ExcelSheetParser;
import com.multimodule.helpers.TestResourcesReaders;
import com.multimodule.uitesting.pages.LoginPage;
import com.multimodule.uitesting.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



public class LoginTestSteps {
    private TestBase base;
    private LoginPage login;

    public LoginTestSteps(final TestBase base) {
        this.base = base;
    }

    @Given("A user in the login page")
    public void logInPage() {
        login = new LoginPage(base.getDriver());
        login.verifyPageLoaded();
        Assert.assertTrue(base.getDriver().getCurrentUrl().contains("saucedemo"));
    }

    @When("user enter email {string}  and password {string}")
    public void userEnterNameAndPasswordInFields(String email, String password) {
        login.enterCredentials(email, password);
    }

    @And("click on the login button")
    public void clickOnTheLoginButton() {
        login.clickOnLoginBtn();
    }

    @Then("user navigated to home page")
    public void userHasBeenLoggedOutAndInLoginPageAgain() {
        Assert.assertTrue(base.getDriver().getCurrentUrl().contains("inventory"));
    }
    @Then("system displays an error message says {string}")
    public void systemDisplaysAnErrorMessageSays(String errorMessage) {
        Assert.assertEquals(errorMessage, login.getLoginMessage());
    }

    @When("user enter login {string}  from  {string}")
    public void userEnterLoginFrom(String testCase, String excelFilePath) throws Exception {
        ExcelSheetParser excel = new ExcelSheetParser(TestResourcesReaders.readExcelSheetByIndex(excelFilePath,0));

        if (!testCase.equalsIgnoreCase("Missing password"))
        {
            login.enterCredentials(excel.getDataByRowAndColName(testCase.trim(),"username"), excel.getDataByRowAndColName(testCase.trim(),"password"));

        }else {
            excel.getDataByRowAndColName(testCase.trim(),"username");
            login.enterCredentials(excel.getDataByRowAndColName(testCase.trim(),"username"),null);

        }

    }
    @Given("user is logged in with standard account user name {string} and password {string}")
    public void user_is_logged_in_with_standard_account_user_name_and_password(String userName, String pss) {
        login=new LoginPage(base.getDriver());
        login.enterCredentials(userName,pss);
        login.clickOnLoginBtn();
    }

}
