package com.homeaway.testcases;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.homeaway.pages.AccountPage;
import com.homeaway.pages.CheckOut;
import com.homeaway.pages.HomePage;
import com.homeaway.pages.ProfilePage;
import com.homeaway.pages.SearchResult;
/**
 * This is the test script to perform Account Update validations
 * 1) Script Logs in to an existing account
 * 2) Navigates to the profile page
 * 3) Updates the nickname to a random name
 * 4) Updates the display name to the above name
 * 5) Navigates back to the home page
 * 6) Logout
 * 7) Logs back in using the same account
 * 8) Navigates to the profile page and validates the changes are still existing
 * 9) Reverts back the nickname and display name to initial values for the next run
 * @author Abhibroto 
 *
 */
public class MyAccountValidations {
	WebDriver driver;
	HomePage homePage;
	CheckOut checkout;
	SearchResult searchResult;
	AccountPage accountPage;
	ProfilePage profilePage;

	// Instantiate the Firefox driver
	@Before
	public void setup() {
		this.driver = new FirefoxDriver();
		this.homePage = new HomePage(driver);
		this.accountPage = new AccountPage(driver);
		this.profilePage = new ProfilePage(driver);
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

		// Click On MyAccount
		homePage.clickOnMyAccount();

		// Login to Account
		accountPage.login("userLogin1", "Password1");

		// Navigate to edit profile page
		accountPage.editProfileNavigation("userLogin1");

		// Edit the First Name to some random name and save
		Random rn = new Random();
		String nickName = "userLogin" + rn.nextInt(10);
		profilePage.updateNickName(nickName);

		// Go back to home page
		driver.get(HomePage.PAGE_URL);

		// Logout
		homePage.logout(nickName);

		// Click On MyAccount
		homePage.clickOnMyAccount();

		// Login to Account
		accountPage.login("userLogin1", "Password1");

		// Navigate to edit profile page
		accountPage.editProfileNavigation(nickName);

		// Verify Nickname
		profilePage.verifyNickname(nickName);
		
		// Revert back changes for future use
		profilePage.updateNickName("userLogin1");

	}

}
