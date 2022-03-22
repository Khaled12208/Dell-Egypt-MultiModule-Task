package com.multimodule.uitesting.driver.configuration;

import org.openqa.selenium.Dimension;

// This Class is the Configuration Holder for executing the tests
public class TestConfiguration {

    // The Browser Configuration parameters
    private final BrowserType browser;
    private final ExecutionMode mode;
    private final ExecutionPrivacy privacy;
    private final BrowserSize size;
    private final Dimension browseDimensions;

    protected TestConfiguration(BrowserType browser, ExecutionPrivacy privacy, Dimension browseDimensions, ExecutionMode mode) {
        this.size=null;
        this.privacy = privacy;
        this.browser = browser;
        this.browseDimensions = browseDimensions;
        this.mode=mode;
    }
    protected TestConfiguration(BrowserType browser, ExecutionPrivacy Privacy, BrowserSize size, ExecutionMode mode) {
        this.size=size;
        this.browseDimensions=null;
        this.privacy = Privacy;
        this.browser = browser;
        this.mode=mode;
    }
    public BrowserSize getBrowserSize() {
        return size;
    }

    public BrowserType getBrowserType() {
        return browser;
    }

    public ExecutionPrivacy getWindowType() {
        return privacy;
    }

    public Dimension getBrowseDimensions() {
        return browseDimensions;
    }

    public ExecutionMode getExecutionMode() {
        return mode;
    }
}
