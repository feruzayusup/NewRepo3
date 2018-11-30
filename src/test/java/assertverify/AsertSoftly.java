package assertverify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AsertSoftly {

	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test1() {
		int i1 = 100;
		int j1 = 200;
		
		System.out.println("First Assertion");
		softAssert.assertEquals(i1, j1);
		
		System.out.println("Second Assertion");
		softAssert.assertNotEquals(i1, j1);
		
		System.out.println("Third Assertion");
		softAssert.assertTrue(i1>j1);
	}	
		
		
		@Test
	   public void test2() {
	        int i = 100;
	        int j = 200;
	        
	        System.out.println("First Assertion: ");
	        softAssert.assertEquals(i, j, "I and J are NOT equal-this is checking is they are equal");
	        
	        System.out.println("Second Assertion: ");
	        softAssert.assertNotEquals(i, j, "I and J are equal-asserting if they are not equal");
	        
	        System.out.println("Third Assertion : ");
	        softAssert.assertTrue(i > j, "I is NOT bigger than J- asserting if i greater than j");
	        
	        softAssert.assertAll();
	        
	    }

	}
	

