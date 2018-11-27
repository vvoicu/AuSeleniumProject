package ecs.apps.frontend.swagger.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ecs.tools.AbstractPage;

public class SwaggerDocumentationPage extends AbstractPage {

	@FindBy(css = "div.swagger-ui")
	private WebElement swaggerUiContainer;

	private By summaryElements = By.cssSelector("span .opblock");
//	private By summaryElements = By.cssSelector(".opblock > div[class*='opblock-summary']");

	public boolean isUiVisible() {
		return element(swaggerUiContainer).waitUntilVisible().isVisible();
	}

	public int extractListSize() {
		return getDriver().findElements(summaryElements).size();
	}

	public boolean expandItemRow(int rowIndex) {
		waitForPageToLoad();
		List<WebElement> apiList = getDriver().findElements(summaryElements);
		if (apiList.size() > 0 && apiList.size() > rowIndex) {
			System.out.println("----------- API ENDPOINT ----------- ");
			System.out.println("URL: " + apiList.get(rowIndex).getText());
			apiList.get(rowIndex).findElement(By.cssSelector("div[class*='opblock-summary']")).click();
			waitForPageToLoad();
			return true;
		}
		return false;
	}

	public String extractContainerId(int rowIndex) {
		waitForPageToLoad();
		List<WebElement> apiList = getDriver().findElements(summaryElements);
		return apiList.get(rowIndex).getAttribute("id");
	}

}
