package ecs.tools;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;

public class AbstractPage extends PageObject {
	
	public void navigateTo(String URL) {
		getDriver().get(URL);
	}

	/**
	 * Wait for document ready state for {@link Constants}.PAGE_LOAD_MAX_RETRY time.
	 * 
	 */
	public void waitForPageToLoad() {
		int retry = 0;
		String response = "";
		do {
			try {
				Thread.sleep(Constants.WAIT_TIME_CONSTANT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			response = String.valueOf(((JavascriptExecutor) getDriver()).executeScript("return document.readyState"));
			retry++;
		} while (retry <= Constants.PAGE_LOAD_MAX_RETRY && response.equals("complete") != true);
	}

	protected void waitForScriptsToLoad() {
		int retry = 0;
		String response = "";
		do {
			try {
				Thread.sleep(Constants.WAIT_TIME_CONSTANT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			response = String.valueOf(((JavascriptExecutor) getDriver())
					.executeScript("return window.jQuery != undefined && jQuery.active == 0"));
			retry++;
		} while (retry <= Constants.PAGE_LOAD_MAX_RETRY && response.equals("complete") != true);
	}

	protected void waitForElementInvisibilityByCssLocator(String cssLocator) {
		(new WebDriverWait(getDriver(), Constants.WAIT_TIME_LARGE_SEC))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssLocator)));
	}

	protected WebElement waitForElementByCssLocator(String cssLocator) {
		return (new WebDriverWait(getDriver(), Constants.WAIT_TIME_LARGE_SEC))
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator)));
	}

	protected WebElement waitForElementByCssLocatorToBeClickable(String cssLocator) {
		return (new WebDriverWait(getDriver(), Constants.WAIT_TIME_LARGE_SEC))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssLocator)));
	}

	protected List<WebElement> waitForElementsByCssLocator(String cssLocator) {
		return (new WebDriverWait(getDriver(), Constants.WAIT_TIME_LARGE_SEC))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssLocator)));
	}

	protected void selectFromDropdownByVisibleText(String cssLocator, String textValue) {
		Select selectDropdown = new Select(waitForElementByCssLocator(cssLocator));
		selectDropdown.selectByVisibleText(textValue);
	}

	public void scrollElementIntoView(String cssSelector) {
		((JavascriptExecutor) getDriver()).executeScript(
				"$('html, body').animate({ scrollTop: $('" + cssSelector + "').offset().top -200 }, 0);");
	}

	protected void printWindowHandles() {
		Set<String> windowHandles = getDriver().getWindowHandles();

		for (String string : windowHandles) {
			System.out.println("Window: " + string);
		}
	}

	protected void jqueryType(String cssLocator, String text) {
		((JavascriptExecutor) getDriver()).executeScript("jQuery('" + cssLocator + "').val('" + text + "')");
	}

	public String getWindowHandle() {
		return getDriver().getWindowHandle();
	}

	protected void switchToExtraWindow(String parentWindow) {
		Set<String> windowHandles = getDriver().getWindowHandles();

		for (String string : windowHandles) {
			if (!string.contains(parentWindow)) {
				getDriver().switchTo().window(string);
				break;
			}
		}
	}

	protected void switchToWindow(String windowHandle) {
		getDriver().switchTo().window(windowHandle);
	}

	public void focusElement(String cssSelector) {
		((JavascriptExecutor) getDriver()).executeScript("$('" + cssSelector + "')[0].scrollIntoView(true);");
		waitForPageToLoad();
	}

	public void mouseOverElement(String cssSelector) {
		((JavascriptExecutor) getDriver()).executeScript("$('" + cssSelector + "').mouseover();");
		waitForPageToLoad();
	}

	public void clickElement(String cssSelector) {
		waitForPageToLoad();
		((JavascriptExecutor) getDriver()).executeScript("$('" + cssSelector + "').click();");
		waitForPageToLoad();
	}

	public void scrollToPageTop() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}

	public void scrollIntoViewById(String id) {
		((JavascriptExecutor) getDriver()).executeScript("$('#" + id + "').get(0).scrollIntoView();");
	}

	public void scrollIntoViewByCss(String css) {
		((JavascriptExecutor) getDriver()).executeScript("$('" + css + "').get(0).scrollIntoView();");
	}

	public void scrollToPageBottom() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	/**
	 * An overlay over the whole page is displayed if 'Order - Manage Show Bundles'
	 * tab is selected. If a menu action is triggered while the overlay is present
	 * the element will not receive the click and the test will fail. This method is
	 * now used both in menu navigation and show bundle page
	 */
	public void waitForModalOverlayToDissapear() {
		if (isElementVisible(By.cssSelector(".modal-backdrop.fade.in")))
			element(By.cssSelector(".modal-backdrop.fade.in")).waitUntilNotVisible();
	}
}
