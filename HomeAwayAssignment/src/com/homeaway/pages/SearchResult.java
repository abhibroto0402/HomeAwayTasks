package com.homeaway.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResult {
	WebDriver driver;

	public SearchResult(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Find the product using Link
	 * 
	 * @param productLink
	 */
	public void selectProductLink(String productLink) {
		
		
		WebElement product = driver.findElement(By.linkText(productLink));
		// This element can be null only if the product was not found or
		// the browser crashed. Product not found is acceptable.
		if (product.equals(null)) {
			if (!driver.findElement(By.id("content")).getText().toLowerCase()
					.contains("nothing matched your search criteria"))
				Assert.fail("Functional Failure");
		} else
			product.click();

	}
	/**
	 * Find product and add to cart, set continueShopping to true to continue shopping, false to checkout
	 * @param productLink- String
	 * @param continueShopping boolean
	 * @throws InterruptedException 
	 */
	public void addProductToCart (String productLink, boolean continueShopping) throws InterruptedException{
		selectProductLink (productLink);
		Thread.sleep(2000);
		WebElement addToCart = driver.findElement(By.className("input-button-buy"));
		addToCart.click();
		
		Thread.sleep(3000);
		//Check if the pop-up is displayed
		if(driver.findElement(By.id("fancy_notification_content")).equals(null))
			Assert.fail("Pop-up not displayed");
		else{
			if(continueShopping){
				WebElement continueShop = driver.findElement(By.className("continue_shopping"));
				continueShop.click();
			}
			else{
				WebElement checkout = driver.findElement(By.className("go_to_checkout"));
				checkout.click();
				
				//Validate the checkout page is displayed
				if(!driver.getTitle().contains("Checkout"))
					Assert.fail("Checkout page not displayed");
				
				Thread.sleep(2000);
			}
		}
		
	}
}
