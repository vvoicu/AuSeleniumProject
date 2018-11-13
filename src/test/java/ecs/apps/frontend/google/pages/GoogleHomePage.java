package ecs.apps.frontend.google.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;

public class GoogleHomePage extends PageObject {

	@FindBy(css = "input[name='q']")
	private WebElement searchTermInput;

	public void inputSearchTerm(String searchTerm) {
		element(searchTermInput).waitUntilVisible();
		searchTermInput.sendKeys(searchTerm);
		searchTermInput.sendKeys(Keys.ENTER);
	}
}
