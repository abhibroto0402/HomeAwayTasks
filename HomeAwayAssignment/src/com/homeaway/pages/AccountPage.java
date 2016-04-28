package com.homeaway.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * This class is used to perform user actions on the account page such as login and navigate to edit profile
 * @author Abhibroto
 *
 */
public class AccountPage {
	WebDriver driver;
	WebElement userName, passwd, loginbutton;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}
/**
 * 
 * @param username
 * @param password
 * @throws InterruptedException
 */
	public void login(String username, String password) throws InterruptedException {
		this.userName = driver.findElement(By.id("log"));
		this.passwd = driver.findElement(By.id("pwd"));
		this.loginbutton = driver.findElement(By.id("login"));
		userName.sendKeys(username);
		passwd.sendKeys(password);
		loginbutton.click();
		Thread.sleep(5000);
		Assert.assertTrue("Login validation", driver.getPageSource().contains(username));

	}

	/**
	 * This method helps to navigate to the profile page
	 * @param userName
	 * @throws InterruptedException 
	 */
	public void editProfileNavigation(String userName) throws InterruptedException {
		List<WebElement> we = new ArrayList<WebElement>();
		we = driver.findElements(By.className("ab-item"));
		for (WebElement me : we){
			if(me.getText().contains(userName)){
				me.click();
				break;
			}
		}
		Thread.sleep(5000);
		Assert.assertTrue("Edit Profile Page", driver.getTitle().contains("Profile"));
		
		
	}

}
