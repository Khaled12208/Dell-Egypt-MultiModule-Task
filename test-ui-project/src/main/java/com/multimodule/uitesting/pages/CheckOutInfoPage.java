package com.multimodule.uitesting.pages;

import com.multimodule.uitesting.pages.components.Header;
import com.multimodule.uitesting.pages.components.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInfoPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    private Header header;
    private Menu menu;

    public CheckOutInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        header=new Header(driver);
        menu = new Menu(driver);
    }

    @Override
    public void verifyPageLoaded() {

    }

    public void paymentInformation(String firstName, String lastName, String postalCode) {
        writeTxtElement(firstNameField, firstName);
        writeTxtElement(lastNameField, lastName);
        writeTxtElement(postalCodeField, postalCode);
    }

    public void clickOnContinueBtn() {
        clickElement(continueBtn);
    }

}
