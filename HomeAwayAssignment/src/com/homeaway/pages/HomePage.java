package com.homeaway.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This Class is the home page class used to perform several user actions on the home page
 * @param driver
 * @author Abhibroto
 *
 */
public class HomePage {
	WebDriver driver;
	
	// This would be used to make sure we are on the right page
	public static final String PAGE_TITLE = "ONLINE STORE | Toolsqa Dummy Test site";
	public static final String PAGE_URL = "http://store.demoqa.com";
	
	
	WebElement SEARCH;
	WebElement CART;
	WebElement MyAccount, logout;
	
	public HomePage (WebDriver driver){
		this.driver= driver; 
	}
	/**
	 * Search Product using the product name
	 * @param productName
	 */
	public void searchProduct(String productName){
		
		SEARCH= driver.findElement(By.name("s"));
		SEARCH.sendKeys(productName);
		SEARCH.sendKeys(Keys.RETURN);
		if(!driver.getTitle().toLowerCase().contains("search results"))
			Assert.fail("Search Result Page Not displayed");
	}
	
	public void  clickOnCart(){
		CART= driver.findElement(By.className("cart_icon"));
		// Check if the new page is displayed
		if (!driver.getTitle().toLowerCase().contains("checkout"))
			Assert.fail("Checkout page not displayed");
	}
	
	public void clickOnMyAccount(){
		MyAccount= driver.findElement(By.className("account_icon"));
		MyAccount.click();
		//Check My account page is displayed
		if (!driver.getTitle().toLowerCase().contains("your account"))
			Assert.fail("My Account page not displayed");
	}
	
	public void logout(String username){
		this.logout = driver.findElement(By.id("account_logout"));
		logout.click();
		Assert.assertFalse("No User Name", driver.getTitle().contains(username));
		
	}
}
