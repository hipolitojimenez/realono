package com.nioos.realono.bdd;



import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Tests steps.
 * 
 * @author Hipolito Jimenez
 */
public class RealONoSteps {
	
	
	/**
	 * The page title.
	 */
	private static final String PAGE_TITLE = "Real o Inventado";
	
	
	/**
	 * AJAX wait time in seconds.
	 */
	private static final int WAIT_SECONDS = 5;
	
	
	/**
	 * Test base URL.
	 */
	private static final String BASE_URL = "http://localhost:8080/realono/";
	
	
	/**
	 * Test html page.
	 */
	private static final String HTML_URL = BASE_URL + "index.html";
	
	
	/**
	 * Chrome web driver.
	 */
	private final transient WebDriver chromeDriver = new ChromeDriver();
	
	
	/**
	 * Firefox web driver.
	 */
	private final transient WebDriver firefoxDriver = new FirefoxDriver();
	
	
	/**
	 * Internet Explorer web driver.
	 */
	private final transient WebDriver internetExplorerDriver =
		new InternetExplorerDriver();
	
	
	/**
	 * Cleanup after the tests.
	 * Close all the browsers.
	 */
	@AfterStories
	public final void afterStories() {
		chromeDriver.quit();
		firefoxDriver.close();
		internetExplorerDriver.quit();
	}
	
	
	/**
	 * Step.
	 * Given the user opens the index page.
	 */
	@Given("the user opens the index page")
	public final void givenTheUserOpensTheIndexPage() {
		getHomePage(chromeDriver);
		getHomePage(firefoxDriver);
		getHomePage(internetExplorerDriver);
	}
	
	
	/**
	 * Navigate to the home page.
	 * @param webDriver the web driver (browser).
	 */
	private void getHomePage(final WebDriver webDriver) {
		webDriver.get(HTML_URL);
		checkTitle(webDriver);
	}
	
	
	/**
	 * Checks the page title.
	 * @param driver the web driver (browser).
	 */
	private void checkTitle(final WebDriver driver) {
		final String title = driver.getTitle();
		Assert.assertEquals("Wrong page title", PAGE_TITLE, title);
	}
	
	
	/**
	 * Step.
	 * When the user waits for the next news to be loaded.
	 */
	@When("the user waits for the next news to be loaded")
	public final void whenTheUserWaitsForTheNextNewsToBeLoaded() {
		waitForNextNews(chromeDriver);
		waitForNextNews(firefoxDriver);
		waitForNextNews(internetExplorerDriver);
	}
	
	
	/**
	 * Wait for next news to be loaded (using AJAX).
	 * @param webDriver the web driver (browser).
	 */
	private void waitForNextNews(final WebDriver webDriver) {
		final WebDriverWait wait = new WebDriverWait(webDriver, WAIT_SECONDS);
		wait.until(
			ExpectedConditions.elementToBeClickable(By.className("tbutton"))
		);
	}
	
	
	/**
	 * Step.
	 * When the user clicks the $button button.
	 * @param button the button clicked.
	 */
	@When("the user clicks the $button button")
	public final void whenTheUserClicksTheButton(
			@Named("button") final String button) {
		String buttonClass = null; // NOPMD
		if ("verdadero".equals(button)) {
			buttonClass = "tbutton"; // NOPMD
		}
		if ("falso".equals(button)) {
			buttonClass = "fbutton";
		}
		clickTheButton(buttonClass, chromeDriver);
		clickTheButton(buttonClass, firefoxDriver);
		clickTheButton(buttonClass, internetExplorerDriver);
	}
	
	
	/**
	 * Test if the button can be clicked.
	 * @param buttonClass the button css class.
	 * @param webDriver the browser.
	 */
	private void clickTheButton(final String buttonClass,
			final WebDriver webDriver) {
		final WebElement button =
			webDriver.findElement(By.className(buttonClass));
		Assert.assertTrue("The button is not visible", button.isDisplayed());
		Assert.assertTrue("The button is not enabled", button.isEnabled());
		button.click();
	}
	
	
	/**
	 * Step.
	 * Then the user view the results page realono.
	 */
	@Then("the user view the results page realono")
	public final void thenTheUserViewTheResultsPageRealono() {
		checkTitle(chromeDriver);
		checkTitle(firefoxDriver);
		checkTitle(internetExplorerDriver);
	}
	
	
}
