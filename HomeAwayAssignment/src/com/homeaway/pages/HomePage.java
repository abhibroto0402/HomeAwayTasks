package com.homeaway.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	WebDriver driver;
	
	// This would be used to make sure we are on the right page
	public static final String PAGE_TITLE = "ONLINE STORE | Toolsqa Dummy Test site";
	public static final String PAGE_URL = "http://store.demoqa.com";
	
	
	@FindBy (how = How.NAME, using="s")
	public static WebElement SEARCH;
	
	@FindBy(how = How.CLASS_NAME, using= "icon")
	public static WebElement CART;
	
	public HomePage (WebDriver driver){
		this.driver= driver; 
	}
}
