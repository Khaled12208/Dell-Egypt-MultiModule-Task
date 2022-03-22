package com.multimodule.uitesting.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Menu {

    @FindBy(id="logout_sidebar_link")
    WebElement Logout;

    public Menu(WebDriver driver)
    {
        PageFactory.initElements(driver,this);

    }

    public void clickLogout()
    {
        Logout.click();
    }
}
