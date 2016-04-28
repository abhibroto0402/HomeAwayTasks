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
 * This is the test case to perform the cart validation
 * 
 * 1) The script looks for the item based on passed parameter; It adds the item
 * to the cart based on passed parameter;
 * 
 * 2) Removes the item from the cart
 * 
 * 3) Validates the empty cart message is displayed
 * 
 * @author Abhibroto Mukherjee
 *
 */
public class CartValidation {
	WebDriver driver;
	HomePage homePage;
	CheckOut checkout;
	SearchResult searchResult;

	// Instatiate the Firefox driver
	@Before
	public void setup() {
		this.driver = new FirefoxDriver();
		this.homePage = new HomePage(driver);
		this.checkout = new CheckOut(driver);
		this.searchResult = new SearchResult(driver);
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

		// Remove the item from the cart and validate the empty cart
		checkout.removeAnItem(0);
	}

}
