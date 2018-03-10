package lib;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AppUtility {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	public AppUtility() throws Exception {
		this.driver =  Utility.ng_returnDriver();
		wait = new WebDriverWait(driver, 40);
		this.js = (JavascriptExecutor) this.driver;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*----------------------------------------------------------------------------
	Function Name    	: InventoryDataMove
	Description     	: InventoryDataMove
	Input Parameters 	: strKey - Paramiter name to get the data value from TestData Table 	                               
	Return Value    	: bResult 
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String InventoryDataMove(String strKey) throws Exception {
		String strVal = Utility.getTestDataValue(strKey);			
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}	
		try {
			Utility.waitForPageToLoad();
			
			int rowNumber=1;
			driver.findElement(By.xpath("//table[@id='inventoryTable']//following-sibling::tbody/child::tr["+rowNumber+"]/child::td[@class=' qty_add_all']//input")).sendKeys("1");
			rowNumber=2;
			driver.findElement(By.xpath("//table[@id='inventoryTable']//following-sibling::tbody/child::tr["+rowNumber+"]/child::td[@class=' qty_add_all']//input")).sendKeys("1");
			rowNumber=3;
			driver.findElement(By.xpath("//table[@id='inventoryTable']//following-sibling::tbody/child::tr["+rowNumber+"]/child::td[@class=' qty_add_all']//input")).sendKeys("1");
			
			js.executeScript("document.getElementById('update').click()");
			
			Utility.ng_waitImplicitly(5);
			
			WebElement oTableRecommendedParts = driver.findElement(By.xpath("//table[@id='recommended-parts']"));
			js.executeScript("arguments[0].scrollIntoView(true);", oTableRecommendedParts);
			
			int rowRecommendedPartsTable=1;		
			String strQunatity1 = driver.findElement(By.xpath("//table[@id='recommended-parts']//following-sibling::tbody/child::tr["+rowRecommendedPartsTable+"]//td[text()='Connection(SM) for Co-Brokerage']//following-sibling::td[2]")).getText();				
			Assert.assertEquals(strQunatity1, "1");
			System.out.println("Quantity in *first row* corresponding to *Connection(SM) for Co-Brokerage* is  --->"+ strQunatity1);
			Utility.writeHTMLResultLog("Quantity in *first row* corresponding to *Connection(SM) for Co-Brokerage* is  --->"+ strQunatity1, "pass");
			rowRecommendedPartsTable = rowRecommendedPartsTable+2;
			String strQunatity2 = driver.findElement(By.xpath("//table[@id='recommended-parts']//following-sibling::tbody/child::tr["+rowRecommendedPartsTable+"]//td[text()='Connection(SM) for Co-Brokerage']//following-sibling::td[2]")).getText();
			Assert.assertEquals(strQunatity2, "1");
			System.out.println("Quantity in *Second row* corresponding to *Connection(SM) for Co-Brokerage* is  --->"+ strQunatity2);
			Utility.writeHTMLResultLog("Quantity in *Second row* corresponding to *Connection(SM) for Co-Brokerage* is  --->"+ strQunatity2, "pass");
			
			rowRecommendedPartsTable = rowRecommendedPartsTable+2;
			String strQunatity3 = driver.findElement(By.xpath("//table[@id='recommended-parts']//following-sibling::tbody/child::tr["+rowRecommendedPartsTable+"]//td[text()='Connection(SM) for Co-Brokerage']//following-sibling::td[2]")).getText();
			Assert.assertEquals(strQunatity3, "1");
			System.out.println("Quantity in *third row* corresponding to *Connection(SM) for Co-Brokerage* is  --->"+ strQunatity3);
			Utility.writeHTMLResultLog("Quantity in *third row* corresponding to *Connection(SM) for Co-Brokerage* is  --->"+ strQunatity3, "pass");
			
			js.executeScript("document.getElementById('update').click()");
			
			Utility.ng_waitImplicitly(10);
			
			WebElement objAddTransaction = driver.findElement(By.xpath("//a[contains(text(),'Add to Transaction')]"));

			js.executeScript("arguments[0].scrollIntoView(true);", objAddTransaction);
			objAddTransaction.click();
			Utility.ng_waitImplicitly(5);
			
			driver.findElement(By.xpath("//a[text()='Save']")).click();
			Utility.ng_waitImplicitly(5);
			driver.findElement(By.xpath("//a[text()='Confirm']")).click();
			Utility.ng_waitImplicitly(5);
			
			driver.findElement(By.xpath("//a[text()='Calculate Tax']")).click();
			Utility.ng_waitImplicitly(5);
			
			driver.findElement(By.xpath("//input[@id='skipAuthorization_quote0']")).click();
			Utility.ng_waitImplicitly(5);
			
			driver.findElement(By.xpath("//a[text()='Import Assets']//ancestor::tbody//preceding::tbody[1]/child::tr//child::a[text()='Submit']")).click();
			Utility.ng_waitImplicitly(5);
			
			String strQuote_Number = "Quote Number";
			String strQuoteNumber = driver.findElement(By.xpath("//span[text()= '"+strQuote_Number+"']//parent::label//following-sibling::div/child::div/span")).getText();	
			System.out.println("Quote Number is -->" + strQuoteNumber);			
			Utility.writeHTMLResultLog("Quote Number is -->" + strQuoteNumber, "pass");
			
			String str_Status = "*Status";
			String strStatus = driver.findElement(By.xpath("//span[text()= '"+str_Status+"']//parent::label//following-sibling::div/child::div/span")).getText();	
			System.out.println("Status  is -->" + strStatus);			
			Utility.writeHTMLResultLog("Status  is -->" + strStatus, "pass");
			
			driver.findElement(By.xpath("//span[text()='Approvals']")).click();

			String strDesc = "Successfully entered data for Inventory and Approved";
			Utility.writeHTMLResultLog(strDesc, "pass");
			Utility.takeScreenShotAndLog("pass");
			Global.bResult = "True";
		} catch (Exception e) {
			String strDesc = "Failed to enter the data for Inventory";
			Utility.writeHTMLResultLog(strDesc, "fail");			
			Utility.takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 	
		return Global.bResult;
		
	}

}