package com.multimodule.uitesting.testbase;

import com.multimodule.uitesting.uiutils.UiUtils;
import com.multimodule.uitesting.driver.configuration.TestConfigurationBuilder;
import com.multimodule.uitesting.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class TestBase {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void startBrowser() throws Exception {
         driver.set(new DriverFactory(new TestConfigurationBuilder().Build()).getWebDriver());
         driver.get().get(UiUtils.getProp("driver.base.url"));
    }

    public  void tearBrowser() {
        // this time out is for browser to terminate
        this.driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        this.driver.get().quit();
    }

    public WebDriver getDriver(){
        return driver.get();
    }

}
