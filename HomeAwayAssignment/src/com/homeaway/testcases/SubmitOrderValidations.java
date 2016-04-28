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
 * This is the test case to perform validation related to 
 * Submitting an Order
 * 
 *@author Abhibroto
 */

public class SubmitOrderValidations {
	
	WebDriver driver;
	HomePage homePage;
	SearchResult searchResult;
	CheckOut checkout;
	
	//Instatiate the Firefox driver 
	@Before
	public void setup(){
		this.driver= new FirefoxDriver();
		this.homePage = new HomePage(driver);
		this.searchResult = new SearchResult(driver);
		this.checkout= new CheckOut(driver);
		
		// Define an implicit wait of 10 seconds for page load
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	//Close the Firefox instance
	@After
	public void teardown(){
		this.driver.quit();
	}
	
	
	
	@Test
	public void test() throws InterruptedException {
		
		driver.get(HomePage.PAGE_URL);
		driver.manage().window().maximize();
		homePage.searchProduct("iPhone 4s");
		searchResult.addProductToCart("Apple iPhone 4S 16GB SIM-Free – Black", false);
		checkout.checkPrices("USA");
	}

}
