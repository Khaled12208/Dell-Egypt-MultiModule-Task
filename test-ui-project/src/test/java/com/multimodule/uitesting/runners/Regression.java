package com.multimodule.uitesting.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;


@CucumberOptions(
        features = "src/test/resources/featuers",
        glue = {"com.multimodule.uitesting.stepDefinitions"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","pretty"},
        snippets = CAMELCASE,
        dryRun=false,
        monochrome=true,
        tags = "@Regression"

)

public class Regression extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeClass(alwaysRun = true)
    public void setupClassName(ITestContext context) {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(50);
        context.getCurrentXmlTest().getSuite().setPreserveOrder(false);
        context.getCurrentXmlTest().getSuite().setVerbose(10);

    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


}
