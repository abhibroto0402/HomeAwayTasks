package com.homeaway.testcases;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.homeaway.pages.HomePage;

public class MyAccountValidations {
	WebDriver driver;
	HomePage homePage;

	// Instatiate the Firefox driver
	@Before
	public void setup() {
		this.driver = new FirefoxDriver();
		this.homePage = new HomePage(driver);
		// Define an implicit wait of 10 seconds for page load
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	// Close the Firefox instance
	@After
	public void teardown() {
		this.driver.quit();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
