package com.multimodule.uitesting.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {

    // Global variables for all pages
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions actions;

    // Constructor to init all web driver and js excutor
    protected BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        this.js = ((JavascriptExecutor) driver);
        this.actions = new Actions(driver);

    }

    public abstract void verifyPageLoaded();

    protected boolean isEnabled(WebElement element)
    {
        return element.isEnabled();
    }

    protected void clickElement (WebElement element)
    {
        element.click();
    }

    protected void writeTxtElement (WebElement element, String txt)
    {
        element.sendKeys(txt);
    }

    protected String getTxtElement (WebElement element)
    {
       return element.getText();
    }


}
