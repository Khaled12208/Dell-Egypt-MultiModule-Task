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

public class CheckOutOverviewPage extends BasePage {


    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(className = "summary_subtotal_label")
    private WebElement priceBeforTax;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    private Header header;
    private Menu menu;


    public CheckOutOverviewPage(WebDriver driver) {
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


    public String getTotalPrice() {
        return getTxtElement(totalPrice);
    }

    public String getPriceBeforTaxt()
    {
        return getTxtElement(priceBeforTax);

    }


    public void clickOnFinishBtn() {
        clickElement(finishBtn);
    }
}
