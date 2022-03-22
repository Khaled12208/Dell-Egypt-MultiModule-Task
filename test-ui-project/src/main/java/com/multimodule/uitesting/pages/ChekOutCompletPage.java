package com.multimodule.uitesting.pages;

import com.multimodule.uitesting.pages.components.Header;
import com.multimodule.uitesting.pages.components.Menu;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChekOutCompletPage extends BasePage{


    @FindBy(className = "complete-text")
    private WebElement textCompleted;

    private Header header;
    private Menu menu;


    public ChekOutCompletPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
        header=new Header(driver);
        menu = new Menu(driver);


    }

    @Override
    public void verifyPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10000)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public String getTextOfCompletedOrder() {
        return getTxtElement(textCompleted);
    }

}
