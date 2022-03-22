package com.multimodule.uitesting.driver.factory;

import com.multimodule.uitesting.driver.configuration.TestConfiguration;
import org.openqa.selenium.WebDriver;

public interface Driver {
    WebDriver getBrowser(TestConfiguration config) throws Exception ;
}
