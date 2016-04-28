package com.homeaway.pages;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class is used for handling the profile page. Update certain field on the
 * profile page and also validation of certain fields
 * 
 * @author Abhibroto
 *
 */
public class ProfilePage {
	WebDriver driver;
	WebElement nickName, displayName, updateButton;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method updates the nick name and display name. Updates the profile
	 * 
	 * @param newFirstName
	 * @throws InterruptedException
	 */
	public void updateNickName(String newNickName) throws InterruptedException {
		this.nickName = driver.findElement(By.id("nickname"));
		nickName.clear();
		nickName.sendKeys(newNickName);
		nickName.sendKeys(Keys.TAB);
		this.displayName = driver.findElement(By.id("display_name"));
		displayName.click();
		displayName.sendKeys(newNickName);
		this.updateButton = driver.findElement(By.id("submit"));
		updateButton.click();
		Thread.sleep(5000);
		Assert.assertTrue("Updated Nickname on Profile Page", driver.getPageSource().contains(newNickName));

	}

	/**
	 * Verifies the nickname matches the expected nickname
	 * 
	 * @param nickname
	 */
	public void verifyNickname(String nickname) {
		this.nickName = driver.findElement(By.id("nickname"));
		Assert.assertTrue("Existing Nickname Match", nickName.getAttribute("value").contains(nickname));
	}
}
