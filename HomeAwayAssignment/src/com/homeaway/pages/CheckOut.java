package com.homeaway.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckOut {
	WebDriver driver;
	WebElement checkout, calculate;
	List<WebElement> Costs;
	List<String> list = new ArrayList<String>();

	public CheckOut(WebDriver driver) {
		this.driver = driver;
	}

	public void checkPrices(String countryName) throws InterruptedException {
		this.checkout = driver.findElement(By.xpath("//span[text()= 'Continue']"));
		checkout.click();

		Thread.sleep(5000);

		Select countryDropdown = new Select(driver.findElement(By.id("current_country")));
		countryDropdown.selectByVisibleText(countryName);

		this.calculate = driver.findElement(By.name("wpsc_submit_zipcode"));
		if (calculate.equals(null))
			Assert.fail("Could not locate Calculate button");
		else {
			calculate.click();
			this.Costs = driver.findElements(By.className("pricedisplay"));
			for (WebElement we : Costs) {
				if (we.getText().contains("$") && !(list.contains(we.getText().substring(1))))
					list.add(we.getText().substring(1));
			}
			System.out.println(list);

			double shippingCost = Double.parseDouble(list.get(0));
			double itemCost = Double.parseDouble(list.get(1));
			double tax = Double.parseDouble(list.get(2));
			double totalCost = Double.parseDouble(list.get(3));
			System.out.println("Shipping Cost: " + shippingCost);
			System.out.println("Item Cost: " + itemCost);
			System.out.println("Tax: " + tax);
			System.out.println("Total: " + totalCost);
			if (totalCost != (itemCost + tax + shippingCost))
				Assert.fail("Total Cost Does Not Match");

		}

	}
}
