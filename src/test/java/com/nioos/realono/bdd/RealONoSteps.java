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



public class RealONoSteps {
	
	
	private static final String BASE_URL = "http://localhost:8080/realono/";
	
	
	private static final String HTML_URL = BASE_URL + "index.html";
	
	
	private WebDriver chromeDriver = new ChromeDriver();
	
	
	private WebDriver firefoxDriver = new FirefoxDriver();
	
	
	private WebDriver internetExplorerDriver = new InternetExplorerDriver();
	
	
	@AfterStories
	public void afterStories() {
		chromeDriver.quit();
		firefoxDriver.close();
		internetExplorerDriver.quit();
	}
	
	
	@Given("the user opens the index page")
	public void givenTheUserOpensTheIndexPage() {
		getHomePage(chromeDriver);
		getHomePage(firefoxDriver);
		getHomePage(internetExplorerDriver);
	}
	
	
	private void getHomePage(WebDriver webDriver) {
		webDriver.get(HTML_URL);
		checkTitle(webDriver);
	}
	
	
	private void checkTitle(WebDriver driver) {
		String title = driver.getTitle();
		String expectedTitle = "Real o Inventado";
		Assert.assertEquals("Wrong page title", expectedTitle, title);
	}
	
	
	@When("the user waits for the next news to be loaded")
	public void whenTheUserWaitsForTheNextNewsToBeLoaded() {
		waitForNextNews(chromeDriver);
		waitForNextNews(firefoxDriver);
		waitForNextNews(internetExplorerDriver);
	}
	
	
	private void waitForNextNews(WebDriver webDriver) {
		WebDriverWait wait = new WebDriverWait(webDriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("tbutton")));
	}
	
	
	@When("the user clicks the $button button")
	public void whenTheUserClicksTheButton(@Named("button") String button) {
		String buttonClass = null;
		if (button.equals("verdadero")) {
			buttonClass = "tbutton";
		}
		if (button.equals("falso")) {
			buttonClass = "fbutton";
		}
		clickTheButton(buttonClass, chromeDriver);
		clickTheButton(buttonClass, firefoxDriver);
		clickTheButton(buttonClass, internetExplorerDriver);
	}
	
	
	private void clickTheButton(String buttonClass, WebDriver webDriver) {
		WebElement button = webDriver.findElement(By.className(buttonClass));
		Assert.assertTrue("The button is not visible", button.isDisplayed());
		Assert.assertTrue("The button is not enabled", button.isEnabled());
		button.click();
	}
	
	
	@Then("the user view the results page realono")
	public void thenTheUserViewTheResultsPageRealono() {
		checkTitle(chromeDriver);
		checkTitle(firefoxDriver);
		checkTitle(internetExplorerDriver);
	}
	
	
}
