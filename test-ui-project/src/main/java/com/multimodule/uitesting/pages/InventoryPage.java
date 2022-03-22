package com.multimodule.uitesting.pages;

import com.google.common.collect.Ordering;
import com.multimodule.uitesting.uiutils.UiUtils;
import com.multimodule.uitesting.pages.components.Header;
import com.multimodule.uitesting.pages.components.Menu;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class InventoryPage extends BasePage {


    @FindAll( @FindBy(className ="inventory_item_name"))
    private List<WebElement> inventoryItemNames;

    @FindAll( @FindBy(xpath = "//button[contains(@id, 'add-to')]"))
    private List<WebElement> itemsAddToCartbtns;


    @FindAll( @FindBy( className="inventory_item_price"))
    private List<WebElement> itemsPrices;

    @FindBy(className="product_sort_container")
    private WebElement sortOptions;


    private Header header;
    private Menu menu;


    public InventoryPage(WebDriver driver) {
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

    public void clickMenu()
    {
        header.clickMenuBtn();
    }

    public void clickLogOut()
    {
        menu.clickLogout();
    }

    public void addItemByName(String item)
    {
        if (getItemNameIndex(item)!=null)
       clickElement(itemsAddToCartbtns.get(getItemNameIndex(item)));

    }
    public Header getHeader()
    {
        return header;
    }

    public void verifySorting(String sort)
    {
        List<String> names= getItemsName();
        List<Float> prices = getItemsPrice();
        if (sort.equalsIgnoreCase("Name (A to Z)"))
        {
            Assert.assertTrue(Ordering.natural().isOrdered(names));
        }
        else if(sort.equalsIgnoreCase("Name (Z to A)"))
        {
            Assert.assertTrue(Ordering.natural().reverse().isOrdered(names));

        }
        else if(sort.equalsIgnoreCase("Price (low to high)"))
        {
            Assert.assertTrue(Ordering.natural().isOrdered(prices));

        }
        else if(sort.equalsIgnoreCase("Price (high to low)"))
        {
            Assert.assertTrue(Ordering.natural().reverse().isOrdered(prices));
        }


    }

    public void sortItemsBy(String sort)
    {
        Select sortOption = new Select(sortOptions);
        sortOption.selectByVisibleText(sort);


    }


    private Integer getItemNameIndex(String item)
    {
        Integer itemIndex=null;
        for (int i=0 ; i<inventoryItemNames.size() ;i++)
        {
            if(getTxtElement(inventoryItemNames.get(i)).equalsIgnoreCase(item))
            {
                itemIndex=i;
                break;
            }

        }
        return itemIndex;
    }

    private List<String> getItemsName()
    {
        List<String> itemsName = new LinkedList<>();
        for (WebElement item : inventoryItemNames )
        {
            itemsName.add(getTxtElement(item));

        }

        return itemsName;
    }

    private List<Float> getItemsPrice()
    {
        List<Float> itemsPricesSting = new LinkedList<>();
        for (WebElement item : itemsPrices )
        {
            itemsPricesSting.add(UiUtils.getPriceAsNumeric(getTxtElement(item)));

        }

        return itemsPricesSting;
    }




}
