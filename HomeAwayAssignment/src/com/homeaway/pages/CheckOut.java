package com.homeaway.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
/**
 * This class is used for validations and actions on
 * the checkout page
 * @author Abhibroto
 *
 */
public class CheckOut {
	WebDriver driver;
	WebElement checkout, calculate;
	List<WebElement> Costs, removeButtons;
	List<String> list = new ArrayList<String>();

	public CheckOut(WebDriver driver) {
		this.driver = driver;
	}
/**
 * Method verifies the total price based on the country selected
 * @param countryName
 * @throws InterruptedException
 */
	public void checkPrices(String countryName) throws InterruptedException {
		this.checkout = driver.findElement(By.xpath("//span[text()= 'Continue']"));
		checkout.click();

		Thread.sleep(5000);

		Select countryDropdown = new Select(driver.findElement(By.id("current_country")));
		countryDropdown.selectByVisibleText(countryName);

		this.calculate = driver.findElement(By.name("wpsc_submit_zipcode"));	
		calculate.click();
		
		// Get all the Cost Elements
		this.Costs = driver.findElements(By.className("pricedisplay"));
		for (WebElement we : Costs) {
			// Capture only unique costs
			if (we.getText().contains("$") && !(list.contains(we.getText().substring(1))))
				list.add(we.getText().substring(1));
		}
		double shippingCost = Double.parseDouble(list.get(0));
		double itemCost = Double.parseDouble(list.get(1));
		double tax = Double.parseDouble(list.get(2));
		double totalCost = Double.parseDouble(list.get(3));
		System.out.println("Shipping Cost: " + shippingCost);
		System.out.println("Item Cost: " + itemCost);
		System.out.println("Tax: " + tax);
		System.out.println("Total: " + totalCost);
		
		//Check the total cost matches item cost +tax +shipping cost
		if (totalCost != (itemCost + tax + shippingCost))
			Assert.fail("Total Cost Does Not Match");
	}
	/**
	 * Removes the item based on the index if 0, first item would be removed
	 * 1, then second
	 * @param index
	 * @throws InterruptedException 
	 */
	public void removeAnItem (int index) throws InterruptedException{
		
		// Fetch all the remove button elements
		this.removeButtons = driver.findElements(By.name("submit"));
		
		// Remove the Update Button from the list
		removeButtons.remove(0);
		
		// Select based on passed index
		removeButtons.get(index).click();
		
		Thread.sleep(5000);
		// If there was only one item in the cart that was removed
		// Check the empty cart message is displayed
		if(index==0 && removeButtons.size()==4){
			Assert.assertTrue("Empty Message validation", driver.getPageSource().contains("Oops, there is nothing in your cart."));
		}
	}
}
