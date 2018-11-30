package com.selenium_maruf;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Kayak {

	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().fullscreen();

	}
	
	@Test
	public void fromToInput() {
		driver.get("https://www.ebay.com/");
		driver.findElement(By.xpath("//input[@type='text']")).click();
	//	driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("backpack women");
		driver.findElement(By.xpath("//input[@type='submit']")).sendKeys("backpack women");

	}
}


