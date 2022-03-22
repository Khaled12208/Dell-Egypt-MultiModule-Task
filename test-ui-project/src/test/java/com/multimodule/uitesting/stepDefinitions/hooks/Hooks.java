package com.multimodule.uitesting.stepDefinitions.hooks;

import com.multimodule.uitesting.testbase.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;


public class Hooks extends TestBase {

    private TestBase base;

    public Hooks(TestBase base) {
        this.base = base;
    }
    @Before
    public void getStart(Scenario scenario) throws Exception {
        base.startBrowser();

    }

    @After
    public void tearDown(Scenario scenario) {

       base.tearBrowser();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) throws IOException {
        File screenshot = ((TakesScreenshot)base.getDriver()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "screenshot");

    }


}
