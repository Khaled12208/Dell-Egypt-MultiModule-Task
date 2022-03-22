package com.multimodule.uitesting.driver.factory;


import com.multimodule.uitesting.driver.configuration.TestConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class DriverFactory {

    private TestConfiguration configuration;
    private WebDriver driver;
    protected final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

    public DriverFactory(TestConfiguration configuration) {
        super();
        this.configuration = configuration;

    }

    // Function to return webdriver
    public WebDriver getWebDriver() throws Exception {

        if (configuration.getBrowserType() != null) {
            switch (configuration.getBrowserType()) {
                case CHROME:
                    driver = new Chrome().getBrowser(configuration);
                    driver = setupConfig(driver);
                    LOGGER.debug("invoking Chrome web browser");
                    break;
                case EDGE:
                    driver = new Edge().getBrowser(configuration);
                    driver = setupConfig(driver);
                    LOGGER.debug("invoking Edge web browser");
                    break;
            }
            return driver;
        } else {
            LOGGER.error("couldn't invoke a web browser because browser type is set to be: ",configuration.getBrowserType());
            throw new Exception(" Unable to create browser driver, make sure to set a browser type");

        }

    }

    private WebDriver setupConfig(WebDriver driver) throws Exception {

        if (configuration.getBrowserSize() == null && configuration.getBrowseDimensions() != null) {
            driver.manage().window().setSize(configuration.getBrowseDimensions());
        } else if (configuration.getBrowserSize() != null && configuration.getBrowseDimensions() == null) {

            if (configuration.getBrowserSize().toString().equalsIgnoreCase("MIN")) {
                driver.manage().window().minimize();
            } else {
                driver.manage().window().maximize();
            }
        } else if (configuration.getBrowserSize() == null && configuration.getBrowseDimensions() == null) {

            LOGGER.error("couldn't find a valid browser size configuration please check the browser-config.properties ");
            throw new Exception(" Browser widows size conflict, Please Make sure to provide one way for windows size setting ");

        } else {
            LOGGER.error(" Browser configuration conflict, please check the browser-config.properties");
            throw new Exception(" Browser widows size conflict, Please Make sure to provide one way for windows size setting ");
        }
        return driver;
    }

}
