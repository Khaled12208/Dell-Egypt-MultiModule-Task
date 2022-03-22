package com.multimodule.uitesting.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {


    @FindBy(id = "user-name")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(tagName = "h3")
    private WebElement errorMessage;

    public LoginPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @Override
    public void verifyPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10000)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void enterCredentials(String email, String password) {
        writeTxtElement(emailField, email);
        if (password !=null) {
            writeTxtElement(passwordField, password);
        }
    }


    public void clickOnLoginBtn() {
        clickElement(loginBtn);
    }

    public String getLoginMessage() {
        return getTxtElement(errorMessage);
    }

}
