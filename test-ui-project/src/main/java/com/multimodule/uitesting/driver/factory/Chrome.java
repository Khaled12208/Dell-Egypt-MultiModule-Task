package com.multimodule.uitesting.driver.factory;

import com.multimodule.uitesting.driver.configuration.TestConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Chrome implements Driver {

    private WebDriver driver;
    private ChromeOptions options;
    private DesiredCapabilities capabilities;


    @Override
    public WebDriver getBrowser(TestConfiguration config) throws Exception {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(SetBrowserOption(config));
            return driver;

        } catch (Exception e) {
            throw new Exception(" Please Make Sure that the Chrome Browser is installed in your system : \n " + e.fillInStackTrace());

        }

    }

    private ChromeOptions SetBrowserOption(TestConfiguration config) {
        options = new ChromeOptions();
        capabilities = new DesiredCapabilities();
        if (config.getExecutionMode().toString().equalsIgnoreCase("HEADLESS")) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            options.addArguments("--start-maximized");

        }
        if (config.getWindowType().toString().equalsIgnoreCase("PRIVATE")) {
            options.addArguments("--incognito");

        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return options.merge(capabilities);

    }
}
