package com.multimodule.uitesting.driver.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Objects.nonNull;

// This Class is the Configuration Builder

public class TestConfigurationBuilder {


    private BrowserType browser;
    private ExecutionMode mode;
    private ExecutionPrivacy privacy;
    private Dimension browseDimensions;
    private BrowserSize size;
    private Properties prop;

    protected static final Logger logger = LogManager.getLogger(TestConfigurationBuilder.class);

    public TestConfigurationBuilder() {
        super();
        try {
            initConfigFromProperties();
        } catch (IOException e) {
            this.browser=BrowserType.CHROME;
            this.browseDimensions=null;
            this.size=BrowserSize.MAX;
            this.privacy=ExecutionPrivacy.PUBLIC;
        }

    }

    public TestConfigurationBuilder setExecutionMode(ExecutionMode executionMode) {
        this.mode= executionMode;
        logger.debug("Execution mode is " + mode.toString());
        return this;
    }
    public TestConfigurationBuilder setPrivacy(ExecutionPrivacy executionPrivacy) {
        this.privacy = executionPrivacy;
        logger.debug("Execution privacy is " + privacy.toString());
        return this;
    }

    public TestConfigurationBuilder setBrowserCustomDimensions(Integer browserWidth,Integer browserHeight ) {
         this.browseDimensions = new Dimension(browserWidth,browserHeight);
        logger.debug("Execution Dimensions is " + browseDimensions.toString());
        return this;
    }
    public TestConfigurationBuilder setBrowserSize(BrowserSize windowSize) {
        this.size=windowSize;
        logger.debug("Execution Dimensions is " + size.toString());
        return this;
    }


    public TestConfigurationBuilder setBrowser(BrowserType browserName ) {
        this.browser= browserName;
        logger.debug("Execution Browser is " + browser.toString());
        return this;
    }

    public TestConfiguration Build()
    {
        if (nonNull(browseDimensions))
        {
            return new TestConfiguration(browser, privacy,browseDimensions, mode);

        }else{
            return new TestConfiguration(browser, privacy,size, mode);
        }
    }

    private void initConfigFromProperties() throws IOException {
        try(InputStream inputStream = new FileInputStream("src/test/resources/browser-config.properties"))
        {
            prop = new Properties();
            prop.load(inputStream);
            setBrowserWithProp(prop.getProperty("driver.browser.name","chrome"));
            setPrivacyWithProp(prop.getProperty("driver.browser.privacy","public"));
            setExecutionModeWithProp(prop.getProperty("driver.browser.mode","headfull"));
            setExecutionWindowSizeWithProp(
            prop.getProperty("driver.browser.size","null"),
            prop.getProperty("driver.browser.width","null"),
            prop.getProperty("driver.browser.height","null") );

        }catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }catch (IOException e){
            throw new IOException();
        }
    }

    private void setBrowserWithProp(String browserName)
    {
        if(browserName.trim().equalsIgnoreCase("chrome"))
        {
            setBrowser(BrowserType.CHROME);
        }else if (browserName.trim().equalsIgnoreCase("edge"))
        {
            setBrowser(BrowserType.EDGE);

        }
    }

    private void setPrivacyWithProp(String privacyType)
    {
        if(privacyType.trim().equalsIgnoreCase("public"))
        {
            setPrivacy(ExecutionPrivacy.PUBLIC);
        }else if (privacyType.trim().equalsIgnoreCase("private"))
        {
            setPrivacy(ExecutionPrivacy.PRIVATE);
        }
    }
    private void setExecutionModeWithProp(String executionMode)
    {
        if(executionMode.trim().equalsIgnoreCase("headfull"))
        {
            setExecutionMode(ExecutionMode.HEADFULL);
        }else if (executionMode.trim().equalsIgnoreCase("headless"))
        {
            setExecutionMode(ExecutionMode.HEADLESS);
        }
    }

    private void setExecutionWindowSizeWithProp(String size, String width, String height)
    {
        if(size.equalsIgnoreCase("null") && (width.equalsIgnoreCase("null") || height.equalsIgnoreCase("null")))
        {
            setBrowserSize(BrowserSize.MAX);

        }else if(!(width.equalsIgnoreCase("null") && height.equalsIgnoreCase("null")))
        {
            setBrowserCustomDimensions(Integer.parseInt(width.trim()),Integer.parseInt(height.trim()));


        }else if ( !(size.equalsIgnoreCase("null")) && ((width.equalsIgnoreCase("null") || height.equalsIgnoreCase("null")))){
        if(size.equalsIgnoreCase("max")||size.equalsIgnoreCase("full"))
        {
            setBrowserSize(BrowserSize.MAX);

        }else {
            setBrowserSize(BrowserSize.MIN);
        }


        }

    }


}
