package com.multimodule.uitesting.stepDefinitions.testSteps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multimodule.helpers.TestResourcesReaders;
import com.multimodule.uitesting.uiutils.UiUtils;
import com.multimodule.uitesting.datamodels.PaymentInfo;
import com.multimodule.uitesting.pages.*;
import com.multimodule.uitesting.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PlaceOrderTestSteps{

    private TestBase base;
    private LoginPage login;
    private InventoryPage inventory;
    private CartPage cart;
    private CheckOutInfoPage info;
    private CheckOutOverviewPage overview;
    private ChekOutCompletPage complete;

    public PlaceOrderTestSteps(TestBase base) {
        this.base = base;
    }



    @Given("user is logged in with standard account user name is {string} and password {string}")
    public void user_is_logged_in_with_standard_account_user_name_x_and_password(String userName, String userPassword) {
            login = new LoginPage(base.getDriver());
            login.verifyPageLoaded();
            login.enterCredentials(userName,userPassword);
            login.clickOnLoginBtn();
    }

    @And("user in the {string} page")
    public void user_in_the_page(String pageTitle) {
        inventory = new InventoryPage(base.getDriver());
        inventory.verifyPageLoaded();
        Assert.assertTrue(inventory.getHeader().getPageTitle().equalsIgnoreCase(pageTitle));

    }

    @When("user add item using {string} to the shopping cart")
    public void user_add_item_using_to_the_shopping_cart(String items) {

        if(items!="-"||items!="")
        {
           String [] itemsArr =  items.split(";");
           for(int i=0 ; i<itemsArr.length;i++)
           {
              inventory.addItemByName(itemsArr[i].trim());
           }

        }
    }

    @And("user clicks on the shopping cart icon")
    public void user_clicks_on_the_shopping_cart_icon() {

        inventory.getHeader().clickShoppingBtn();
    }

    @And("click on the checkout button")
    public void click_on_the_checkout_button() {
        cart= new CartPage(base.getDriver());
        cart.verifyPageLoaded();
        cart.clickOnCheckoutBtn();

    }

    @And("fill payment information from file {string}")
    public void fill_payment_information_from_file(String string) throws Exception {
        info =new CheckOutInfoPage(base.getDriver());
        info.verifyPageLoaded();
        String data = TestResourcesReaders.readTestResourceFileAsString(string);
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentInfo paymentInfo = objectMapper.readValue(data, PaymentInfo.class);
        info.paymentInformation(paymentInfo.getFirstName(),paymentInfo.getLastName(),paymentInfo.getPostalCode());

    }

    @And("Click on continue button")
    public void click_on_continue_button() {
        info.clickOnContinueBtn();
    }

    @And("the items total price is {string}")
    public void the_total_price_is(String price) {
        overview=new CheckOutOverviewPage(base.getDriver());
        overview.verifyPageLoaded();
        Assert.assertTrue(overview.getPriceBeforTaxt().equalsIgnoreCase(price));

    }

    @And("the total price  after tax is {string}")
    public void theTotalPriceAfterTax(String totalAfterTax) {
        Assert.assertTrue(overview.getTotalPrice().equalsIgnoreCase(totalAfterTax));
    }
    @And("the taxes rate is {string} %")
    public void theTaxRate(String tax) {
        Float priceBeforTax= UiUtils.getPriceAsNumeric(overview.getPriceBeforTaxt());
        Float priceAfterTax=UiUtils.getPriceAsNumeric(overview.getTotalPrice());
        Float taxVlue= priceAfterTax -priceBeforTax;
        Float taxPrcentage= (taxVlue/priceBeforTax.intValue())*100;
        Assert.assertEquals(taxPrcentage.intValue(),Integer.parseInt(tax));

    }
    @And("clicks on the finish button")
    public void clicks_on_the_finish_button() {
        overview.clickOnFinishBtn();
    }
    @Then("the order has been created with thanks word {string}")
    public void the_order_has_been_created_with_thanks_word(String completeText) {
        complete= new ChekOutCompletPage(base.getDriver());
        complete.verifyPageLoaded();
        Assert.assertEquals(complete.getTextOfCompletedOrder().trim(),completeText.trim());
    }
}
