package com.mochkaroo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {
	WebDriver dr;
	String titleExpected;
	String titleActual;
	List<WebElement> elems;
	List<String> cities = new ArrayList<>();
	List<String> countries = new ArrayList<>();
	
	//this is Azamat's code

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		dr = new ChromeDriver();
		dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dr.manage().window().fullscreen();

		dr.get("https://mockaroo.com/");
		String titleActual = dr.getTitle();
		System.out.println("Actual Title: " + titleActual);
		String expectedTitle = "Mockaroo realistic data generator";
	}

	@Test
	public void TestA() {
		Assert.assertEquals(titleActual, titleExpected, "Title matched");//actual title compared to expected title
	}

	@Test
	public void TestB() throws InterruptedException {

		elems = dr.findElements(By.xpath("//div[@class='column column-remove']"));
		for (WebElement e : elems) {
			e.click();
		}

	}

	@Test // TASK 6
	public void TestC() {
		String fn = "Field Name";  //here we have to match with text, therefore
		String t = "Type";
		String op = "Options";
		Assert.assertEquals(dr.findElement(By.xpath("//div[@class='table-header']/div[1]")).getText(), fn);
		Assert.assertEquals(dr.findElement(By.xpath("//div[@class='table-header']/div[2]")).getText(), t);
		Assert.assertEquals(dr.findElement(By.xpath("//div[@class='table-header']/div[3]")).getText(), op);
	}

	@Test // TASK 7
	public void TestD() {
		Assert.assertTrue(dr.findElement(By.id("columns_fields_blueprint")).isEnabled());
	}

	@Test // TASK 8
	public void TestE() {
		String num = dr.findElement(By.id("num_rows")).getAttribute("value");
		Assert.assertEquals(num, "1000");
	}

	@Test // TASK 9
	public void TestF() {
		// String format =
		// dr.findElement(By.id("schema_file_format")).getAttribute("value").toUpperCase();
		String format = dr.findElement(By.xpath("//div[@class='form-group']//option[1]")).getText();
		Assert.assertEquals(format, "CSV");
	}

	@Test // TASK 10
	public void TestG() {
		String line = dr.findElement(By.xpath("//option[@value='unix']")).getText();
		Assert.assertEquals(line, "Unix (LF)");
	}

	@Test // TASK 11
	public void TestH() {
		boolean box = dr.findElement(By.id("schema_include_header")).isSelected();
		boolean box2 = dr.findElement(By.id("schema_bom")).isSelected();
		Assert.assertTrue(box);
		Assert.assertFalse(box2);
	}

	@Test //TASK 12: Click on ‘Add another field’ and enter name “City”
	public void TestI() throws InterruptedException {
		dr.findElement(By.xpath("//a[@data-blueprint-id='columns_fields_blueprint']")).click();
		Thread.sleep(500);
		dr.findElement(By.xpath("(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]")).sendKeys("City");
	}

	@Test // TASK 13: Click on Choose type and assert that Choose a Type dialog box is displayed.
	public void TestJ()  {
		dr.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
		Assert.assertTrue(dr.findElement(By.xpath("//body[@class='mockaroo modal-open']")).isDisplayed());
	}
	
	@Test // TASK 14. Search for “city” and click on City on search results.
	public void TestK() throws InterruptedException  {
		Thread.sleep(500);
		dr.findElement(By.xpath("//div[@class='modal-dialog']//input[@placeholder='Find Type...']")).sendKeys("City");
		dr.findElement(By.xpath("//div[@id='type_list']//div[@class='type']")).click();
		
		}
	@Test //TASK 15: Click on ‘Add another field’ and enter name “City”
	public void TestL() throws InterruptedException {
		Thread.sleep(500);
		dr.findElement(By.xpath("//a[@data-blueprint-id='columns_fields_blueprint']")).click();
		Thread.sleep(500);
		dr.findElement(By.xpath("(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]")).sendKeys("Country");
	}

	@Test // TASK 15: Click on Choose type and assert that Choose a Type dialog box is displayed.
	public void TestM() throws InterruptedException  {
		Thread.sleep(500);
		dr.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
		Assert.assertTrue(dr.findElement(By.xpath("//body[@class='mockaroo modal-open']")).isDisplayed());
		Thread.sleep(500);
	}
	
	@Test // TASK 15-16. Search for “city” and click on City on search results.
	public void TestN() throws InterruptedException  {
		dr.findElement(By.xpath("//div[@class='modal-dialog']//input[@placeholder='Find Type...']")).clear();;
		dr.findElement(By.xpath("//div[@class='modal-dialog']//input[@placeholder='Find Type...']")).sendKeys("country");
		dr.findElement(By.xpath("//div[@id='type_list']//div[@class='type']")).click();
		Thread.sleep(500);
		dr.findElement(By.id("download")).click();
		}
	
	@Test // TASK17-18-19-20
	public void TestO() throws InterruptedException {
		Thread.sleep(800);
		String filePath="C:\\Users\\Feruza\\Downloads\\MOCK_DATA.csv";
		try (FileInputStream fis = new FileInputStream(filePath);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				CSVReader reader = new CSVReader(isr)) {
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				boolean isCity = true;
				for (String e : nextLine) {
					if (isCity) {
						cities.add(e);
					} else {
						countries.add(e);
					}
					isCity = !isCity;
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		//System.out.println(cities);
	}
	
	@Test //TASK 22 -Sort
	public void TestP() {
		Collections.sort(cities);
		
		String sh="shortestshortest";
		String lo="";
		for (String string : cities) {
			if(string.length()>lo.length()) {
				lo=string;
			}if(string.length()<sh.length()) {
				sh=string;
			}
		}
		System.out.println("longest: "+lo);
		System.out.println("shortest: "+sh);
	}
	
	@Test //TASK 23-24-25
	public void TestQ() { 
		Set<String> uniqueC = new HashSet<String>(countries);
		for (String key : uniqueC) {
		    System.out.println(key + ": " + Collections.frequency(countries, key));
		}
	}
	@Test //count unique cities
	public void TestR() {
		Set<String> citiesSet = new HashSet<String>(cities);
		List<String>cities2 =new ArrayList<String>();
		for (String city : citiesSet) {
			if (!cities2.contains(city))
				cities2.add(city);
		}
		Assert.assertEquals(citiesSet.size(), cities2.size());
		System.out.println("uniqueCities numbers: " + citiesSet.size());
		System.out.println("citySet numbers: " + cities2.size());
	}
	
	@Test
	public void TestS() {
		HashSet<String> countrySet = new HashSet<>(countries);
		List<String> uniqueCountries = new ArrayList<>();
		for (String country : countries) {
			if (!uniqueCountries.contains(country))
				uniqueCountries.add(country);
		}
		Assert.assertEquals(uniqueCountries.size(), countrySet.size());
		System.out.println("uniqueCountries number : " + uniqueCountries.size());
		System.out.println("Countries number: " + countrySet.size());
	}	
	@AfterClass 
	public void afterClass() throws InterruptedException {
		Thread.sleep(10000);
		dr.close();
	}
}