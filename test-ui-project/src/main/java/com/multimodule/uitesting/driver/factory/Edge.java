package com.multimodule.uitesting.driver.factory;

import com.multimodule.uitesting.driver.configuration.TestConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Edge implements Driver {

    private WebDriver driver;
    private EdgeOptions options;
    private DesiredCapabilities capabilities;

    @Override
    public WebDriver getBrowser(TestConfiguration config) throws Exception {
        try {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(SetBrowserOption(config));
            return driver;

        } catch (Exception e) {
            throw new Exception(" Please Make Sure that the Edge Browser is installed in your system : \n " + e.fillInStackTrace());
        }

    }

    private EdgeOptions SetBrowserOption(TestConfiguration config) {
        options = new EdgeOptions();
        capabilities = new DesiredCapabilities();
        if (config.getExecutionMode().toString().equalsIgnoreCase("HEADLESS")) {
            options.addArguments("--headless");

        }
        if (config.getWindowType().toString().equalsIgnoreCase("PRIVATE")) {
            options.addArguments("inprivate");

        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return options.merge(capabilities);

    }
}
