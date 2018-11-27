package ecs.apps.frontend.swagger.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ecs.tools.AbstractPage;
import ecs.tools.FieldGenerators;
import ecs.tools.FieldGenerators.Mode;

public class RequestDescriptionPage extends AbstractPage {

	private By tryNowButton = By.cssSelector(".btn.try-out__btn:not(.cancel)");
	private By executeButton = By.cssSelector("button.execute");

//	private By expandedItemContainer = By.cssSelector("div.opblock-section");
	private By curlTextarea = By.cssSelector("textarea.curl");

	// used just for logging
	private String containerLocatorId;

	public void populateExtractRequestParams(String containerLocatorId) {
		this.containerLocatorId = containerLocatorId;
		String containerLocator = "#" + containerLocatorId + " .opblock-body";
		element(By.cssSelector(containerLocator)).waitUntilVisible();
		WebElement enpointDetailsContainer = getDriver().findElement(By.cssSelector(containerLocator));

		List<WebElement> requestRows = enpointDetailsContainer
				.findElements(By.cssSelector(".opblock-section table.parameters tbody tr"));

		for (WebElement requestParamRowNow : requestRows) {
			handleRequestParamRow(requestParamRowNow);
		}

	}

	private void handleRequestParamRow(WebElement requestRowNow) {
		WebElement key = requestRowNow.findElement(By.cssSelector("td[class*='col_name']"));

		// extract key attributes
		String rowKey = key.findElement(By.cssSelector(".parameter__name")).getText();
		boolean isRequired = key.findElement(By.cssSelector(".parameter__name")).getAttribute("class")
				.contains("required");
		String rowKeyType = key.findElement(By.cssSelector(".parameter__type")).getText();

		// what kind of input the value takes
		try {
			WebElement value = requestRowNow.findElement(By.cssSelector("td[class*='col_description']"));
			WebElement valueInputGeneric = value.findElement(By.cssSelector("div.markdown + *"));
			String valueTagName = valueInputGeneric.getTagName();
			System.out.println("Value Tag: " + valueTagName);
			if (isRequired) {
				if (valueTagName.contains("input") && rowKeyType.contains("string")) {
					valueInputGeneric.sendKeys(FieldGenerators.generateRandomString(10, Mode.ALPHA));
				} else {
					valueInputGeneric.sendKeys(FieldGenerators.generateRandomString(10, Mode.NUMERIC));
				}
				if (valueTagName.contains("select")) {
					element(valueInputGeneric).selectByIndex(0);
				}
			}
		} catch (Exception e) {
			System.err.println(
					"Container: '#" + containerLocatorId + "' - Unable to locate Value element for key: " + rowKey);
		}
//		System.out.println("-----------------------------------");
//		System.out.print("Key: " + rowKey);
//		System.out.print("	||	");
//		System.out.println("value: " + value.getText());
//		System.out.println("isRequired: " + isRequired);
//		System.out.println("rowKeyType: " + rowKeyType);
//		System.out.println("-----------------------------------");

	}

	public boolean clickTryCommand() {
		try {
			element(tryNowButton).waitUntilVisible();
			element(tryNowButton).click();
			waitForPageToLoad();
			return true;
		} catch (Exception e) {
			System.out.println("No Try Button");
		}
		return false;
	}

	public boolean clickExecuteCommand() {
		try {
			element(executeButton).waitUntilVisible();
			element(executeButton).click();
			waitForPageToLoad();
			return true;
		} catch (Exception e) {
			System.out.println("No Execute Button");
		}
		return false;
	}

	public String extractCurlCommand() {
		String result = "";
		try {
			WebElement curlCommand = getDriver().findElement(curlTextarea);
			result = curlCommand.getText();
		} catch (Exception e) {
			System.out.println("No curl ...");
		}

		System.out.println("Curl: " + result);
		return result;
	}

	public void extractRequestParams() {
		// TODO Auto-generated method stub

	}
}
