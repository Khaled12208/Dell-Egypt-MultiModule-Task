package com.multimodule.apitesting.stepDefinitions.hooks;

import com.multimodule.apitesting.base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import java.io.IOException;


public class Hooks extends TestBase {

    private TestBase base;

    public Hooks(TestBase base) {
        this.base = base;
    }
    @Before
    public void getStart(Scenario scenario) throws Exception {
        base.initRequestBase();
    }


}
