package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperWithWebDriverBase extends HelperBase {

	private WebDriver driver;

	public HelperWithWebDriverBase(ApplicationManager manager) {
		super(manager);
		driver = manager.getWebDriverHelper().getDriver();
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected void type(By locator, String name) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(name);
	}

	protected WebElement findElement(By linkText) {
		try {
			return driver.findElement(linkText);
		} catch (Exception e) {
			return null;
		}
	}

	protected List<WebElement> findElements(By linkText) {
		return driver.findElements(linkText);
	}

	protected void openUrl(String string) {
		driver.get(manager.getProperty("baseUrl") + string);
	}

	protected void openAbsoluteUrl(String string) {
		driver.get(string);
	}

	protected void click(By linkText) {
		findElement(linkText).click();
	}

}
