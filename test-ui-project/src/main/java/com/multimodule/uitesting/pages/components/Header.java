package com.multimodule.uitesting.pages.components;

import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header{

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "shopping_cart_container")
    WebElement shopping_cart_container;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className="title")
    private WebElement pageTitle;

    public Header(WebDriver driver)
    {
        PageFactory.initElements(driver,this);

    }

    public void clickMenuBtn() {
         menuBtn.click();
    }

    public void clickShoppingBtn() {
         shopping_cart_container.click();
    }

    public String getNumberOfItemsInCart()
    {
        if(cartBadge.isDisplayed())
        {
            return cartBadge.getText();

        }else
        {
            throw new ElementNotVisibleException("Cart badge is not visible");
        }
    }

    public String getPageTitle()
    {
        return pageTitle.getText();
    }

}
