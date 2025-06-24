package org.IrvinCampos.cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/org/IrvinCampos/features", glue = {"stepDefinitions"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
