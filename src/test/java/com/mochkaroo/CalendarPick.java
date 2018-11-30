package com.mochkaroo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPick {

	public static void main(String[] args) {

		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get("http://orbitz.com");
	    
	    String year="2019";
        String month="Jan";
        String day="14";
        
//        driver.findElement(By.xpath("//input[@id='package-departing-hp-package']")).click();
//        
//        while(!driver.findElement(By.xpath("//caption[@class='datepicker-cal-month-header']")).getText().contains(month)) {
//            driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']")).click();
//        }//here looping w
//        
//        List<WebElement> dayPick = driver.findElements(By.xpath("//button[@class='datepicker-cal-date']"));
//        
//        for (int i = 0; i < dayPick.size(); i++) {
//            if(dayPick.get(i).getText().contains(day)) {
//                dayPick.get(i).click();
//                break;
//            }
//        }

        WebElement footer = driver.findElement(By.xpath("//div[@class='links-list']"));
		String open = Keys.chord(Keys.CONTROL, Keys.ENTER);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@id='other-links-links']/li/a"));
		for(WebElement each : list) {
			each.sendKeys(open);
			
		}

	}}

