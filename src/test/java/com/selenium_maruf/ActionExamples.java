package com.selenium_maruf;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionExamples {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@Ignore
	@Test       //works with preface webpage
	public void doubleClickTest() {
		driver.get("https://www.primefaces.org/showcase/ui/misc/effect.xhtml");

		WebElement slides = driver.findElement(By.id("slide_header"));
		WebElement shakes = driver.findElement(By.id("shake_header"));
		WebElement size = driver.findElement(By.id("size_header"));
		WebElement pulsate = driver.findElement(By.id("pulsate_header"));
		WebElement explode = driver.findElement(By.id("explode_header"));

		Actions action = new Actions(driver);

		action.doubleClick(slides).perform();// perform makes it to perform
		action.doubleClick(shakes).perform();// perform makes it to perform
		action.doubleClick(size).perform();// perform makes it to perform
		action.doubleClick(pulsate).perform();// perform makes it to perform
		action.doubleClick(explode).perform();// perform makes it to perform

	}
	@Ignore
	@Test    // works with amazon
	public void hoverTest1() throws InterruptedException {
		driver.get("http://amazon.com");
		WebElement hello = driver.findElement(By.xpath("//span[.='Hello. Sign in']"));

		Actions action = new Actions(driver);
		// moves the mouse on top of the target element
		action.moveToElement(hello).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Your Garage")).click();

	}

	@Ignore
	@Test
	public void hoverTest2() {
		driver.get("http://amazon.com"); ;
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[.='Back to top']"))).perform();
		
	//	action.moveByOffset(5, 5).perform();
	//	action.moveToElement(null,5,5).perform();
	//	action.sendKeys(Keys.PAGE_DOWN).perform();	//performs page down
		action.sendKeys(Keys.ARROW_DOWN).perform();
		action.sendKeys(Keys.ARROW_DOWN).perform();

		action.sendKeys(Keys.ARROW_DOWN).perform();
		action.sendKeys(Keys.ARROW_RIGHT).perform();

	}
	@Ignore
	@Test
	public void draggingTest1 () {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		/*
		 * 	// drag drop operation involves 2 elements. source and target
			// source --> element which we will move
			// target  --> element where we drop the source
		 * 
		 */
		Actions action = new Actions (driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droptarget"));
		//action.dragAndDrop(source, target).perform();
		action.dragAndDropBy(source, 0, -200).perform(); //this code moves cursor 0 to left and 100 down

	//task
		/*1. move the mouse on top of the source
		 *2. click and hold mouse on source
		 *3. move to target element
		 *4. release the mouse
		 *build() method is there so we can build all those operations
 		 */
	action.moveToElement(source).clickAndHold().moveToElement(target).release().build().perform();
	}
	
	
	@Test
	public void dragSquareTest1 () {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		Actions action = new Actions (driver);
		WebElement source = driver.findElement(By.id("column-a"));
		WebElement target = driver.findElement(By.id("column-b"));
//		action.moveToElement(source).clickAndHold().moveToElement(target).release().build().perform();
		action.dragAndDrop(source, target).perform();
		
		
		
		
	}
		
		
		
		
		
		
	
}
