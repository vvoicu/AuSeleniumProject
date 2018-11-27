package ecs.runners.tests.swagger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import ecs.apps.frontend.swagger.steps.SwaggerSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class ExtractDocumentationTest {
	
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

	private String documentationUrl;

	@Steps
	public SwaggerSteps swaggerSteps;
	
	@Before
    public void testSetup() {
    	documentationUrl = "https://editor.swagger.io/";
    }
    
    
    @Test
    public void extractSwaggerDocumentationTest() {
    	swaggerSteps.navigateTo(documentationUrl);
    	boolean isVisible = swaggerSteps.isUiVisible();
    	
    	System.out.println("_______" + isVisible);

    	swaggerSteps.extractSwaggerList();
    }

}
