package com.homeaway.testcases;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.homeaway.pages.CheckOut;
import com.homeaway.pages.HomePage;
import com.homeaway.pages.SearchResult;

/**
 * This is the test case to perform validation related to Submitting an Order
 * 
 * 1) Open the Shopping portal on fireforx browser
 * 
 * 2) Search for iPhone 4s by typing in the search box
 * 
 * 3) Select the correct item using the link description
 * 
 * 4) Add the item to the cart
 * 
 * 5) Checkout
 * 
 * 6) Verify the Cost and checks if the total cost is correct summation of other
 * costs
 * 
 * @author Abhibroto
 */

public class SubmitOrderValidations {

	WebDriver driver;
	HomePage homePage;
	SearchResult searchResult;
	CheckOut checkout;

	// Instatiate the Firefox driver
	@Before
	public void setup() {
		this.driver = new FirefoxDriver();
		this.homePage = new HomePage(driver);
		this.searchResult = new SearchResult(driver);
		this.checkout = new CheckOut(driver);

		// Define an implicit wait of 10 seconds for page load
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	// Close the Firefox instance
	@After
	public void teardown() {
		this.driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		// Open the Shopping portal and maximize
		driver.get(HomePage.PAGE_URL);
		driver.manage().window().maximize();

		// Enter the search string
		homePage.searchProduct("iPhone 4s");

		// Add product to the cart
		searchResult.addProductToCart("Apple iPhone 4S 16GB SIM-Free – Black", false);

		// Check the displayed costs
		checkout.checkPrices("USA");
	}

}
