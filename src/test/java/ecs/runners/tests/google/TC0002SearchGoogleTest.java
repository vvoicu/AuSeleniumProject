package ecs.runners.tests.google;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import ecs.apps.frontend.google.steps.GoogleSearchSteps;
import ecs.tools.ConfigUtils;
import ecs.tools.ConfigUtils.ConfigKeys;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class TC0002SearchGoogleTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public GoogleSearchSteps googleSearchSteps;

    @Test
    public void tc0002NavigateToGoogleTest() {
    	googleSearchSteps.navigateToUrl(ConfigUtils.getProperty(ConfigKeys.BASE_URL_GOOGLE));
    	googleSearchSteps.inputSearchTerm("apple");
    }

} 