/*============================================================================================
Library File Name    :  Utility
Author               :  Sharad Mali
Created date         :  23-Sep-2017
Description          :  It lists the common utility functions that can be used in the scripts.
============================================================================================*/

package lib;

import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utility  {
	static WebDriver driver;
	static Calendar cc = null;
	public static String homePath = "";
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
    
	
	public Utility(String browser ) throws IOException {
		InitDriver initdriver =InitDriver.getInstance(browser);
		
		this.driver = initdriver.driver;
		wait = new WebDriverWait(driver, 40);
		this.js = (JavascriptExecutor) this.driver;
	}
	
	public static WebDriver ng_returnDriver() throws Exception {
		return driver;
	}
	
	/*----------------------------------------------------------------------------
	Function Name    	: invokeBrowser
	Description     	: This function invokes the application in Browser
	Input Parameters 	: strKey - Paramiter name to get the data value from TestData Table
	                    : objData - Test Data
	Return Value    	: None 
	Author		        : 
	Date of creation	:
	Date of modification:	
	----------------------------------------------------------------------------*/
	public static String ng_invokeBrowser(String strKey) throws Exception {
		String strVal = getTestDataValue(strKey);				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}				
		try {			
			driver.get(strVal);
			//driver.get("https://smali:mar-2018@adfs.elliemae.com/adfs/ls/wia");
			//driver.navigate().to(strVal);
			waitForPageToLoad();
			String strDesc = "Browser  '" + strVal + "'  Invoked Successfully.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
		} catch (Throwable error) {
			String strDesc = "Timeout waiting for Page Load Request to complete.";
			writeHTMLResultLog(strDesc, "fail");			
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 
		return Global.bResult;

	}

	/*----------------------------------------------------------------------------
	Function Name    	: verifyPage
	Description     	: This function enters a data into a text box
	Input Parameters 	: strLabel - To be printed on extent report
	                    : strKey - Paramiter name to get the data value from TestData Table                        
	Return Value    	: bResult
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String ng_verifyPage(String strLabel, String strKey) throws Exception {
		String strVal = getTestDataValue(strKey);				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}		
		try {
			waitForPageToLoad();			
			String strDesc = "Page '" + strLabel + "' is displayed successfully.";
			writeHTMLResultLog(strDesc, "info");
			takeScreenShotAndLog("pass");
			Global.bResult = "True";
		} catch (Throwable e) {
			String strDesc = "Page " + strLabel + " is not displayed properly. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 
		return Global.bResult;
	}

	/*----------------------------------------------------------------------------
	Function Name    	: enterText
	Description     	: This function enters a data into a text box
	Input Parameters 	: strObject - Object Name of Edit Box
						: strLabel - To be printed on extent report
	                    : strKey - Paramiter name to get the data value from TestData Table                        
	Return Value    	: bResult
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String ng_enterText(WebElement element, String strLabel, String strKey) throws Exception {
		
		String strVal = getTestDataValue(strKey);				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}	
		try {
			waitForPageToLoad();
			ng_waitUntilElementVisible(element,20);			
			//ng_waitUntilElementDisplayed(element,20); //mahesh
			ng_scrollIntoViewElement(element, strLabel);						
			element.click();
			if(StringUtils.isNotEmpty(ng_getTextBoxValue(element, strLabel))) {
				clearTextField(element);
		    	//ng_waitImplicitly(1);		mahesh    	
		    	//waitForPageToLoad();
			}	
			if(Global.gstrHighlighter == true) {
				highLighterMethod(element);
			}	
			
			if (strVal.equalsIgnoreCase("RANDOM")) {
				strVal = Utility.ng_RandomAlphaNum("J",5);
			}
			element.sendKeys(strVal);
			//waitForPageToLoad();
			String strDesc = "Successfully entered '" + strVal + "' in '" + strLabel + "' textbox.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
		} catch (Exception e) {
			String strDesc = "'" + strLabel + "' textbox does not exist. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		}
		return Global.bResult;
	}
	/*----------------------------------------------------------------------------
	Function Name    	: getTestDataValue
	Description     	: This function get the TestDataValue
	Input Parameters 	: strKey - Paramiter name to get the data value from TestData Table 
	                    :                        
	Return Value    	: strCellData
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String getTestDataValue(String strKey) throws Exception {
		String strCellData;
		if (Global.gstrReadfromTestData){
			strCellData = (String) Global.objData.get(strKey.toUpperCase());
			if (strCellData!= null) {
				return strCellData;
			}else {
				String strDesc = "DataValue '" + strKey +  "' not found in Test Data . " ;
				writeHTMLResultLog(strDesc, "fail");
				Global.bResult = "False";
				Global.objErr = "11";
				Global.report.endTest(Global.test);
		        Global.report.flush();
			}
		}
		else{
			strCellData =  strKey;
		}		
		return strCellData;
	}

	/*----------------------------------------------------------------------------
	Function Name    	: ng_SelectList
	Description     	: 
	Input Parameters 	: strObject - Object Name of Edit Box
						: strLabel - To be printed on extent report
	                    : strKey - Paramiter name to get the data value from TestData Table                        
	Return Value    	: bResult
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String ng_SelectList(WebElement element,String strLabel, String strKey) throws Exception {
		String strVal = getTestDataValue(strKey);	
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}	
		try {
			//waitForPageToLoad();			
			//explicitWait(element, 20);
			//ng_waitUntilElementDisplayed(element,20);
			//ng_scrollIntoViewElement(element, strLabel);			
			//ng_waitForElementEnabled(element,20);
//			if(StringUtils.isNotEmpty(ng_getTextBoxValue(element, strLabel))) {
//				clearTextField(element);
//		    	ng_waitImplicitly(1);		    	
//		    	//waitForPageToLoad();
//			}			
			//element.click();
			//waitForPageToLoad();
			ng_enterText(element, strLabel, strKey);			
			ng_waitImplicitly(1);  //mahesh 2
			element.sendKeys(Keys.ENTER);
			waitForPageToLoad();
			ng_waitImplicitly(2);
			//String triangleloc="//label[text()='"+strLabel+"']/parent::td/following::td//a[contains(@title,'Search: "+strLabel+"')]";
			
			//String triangleloc = "//a[contains(@title,'Search: "+strLabel+"')]";
						
			WebElement triangleloc = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title,'Search: "+strLabel+"')]")));			
			//WebElement trianglebox = driver.findElement(By.xpath(triangleloc));			
			triangleloc.click();
			waitForPageToLoad();
			ng_waitImplicitly(1); //mahesh
			
			//WebElement dropdownselect = driver.findElement(By.xpath("//span[text()='"+strVal+"']"));
			WebElement dropdownselect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+strVal+"']")));
			dropdownselect.click();
			waitForPageToLoad();	
			String strDesc = "Value '" + strVal + "' is selected successfully from '" + strLabel + "' list.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
		} catch (Exception e) {
			String strDesc = "Items in the  List  "+strLabel + "' are not displayed. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 	
		return Global.bResult;
	}
	
	/*----------------------------------------------------------------------------
	Function Name    	: ng_DropDown
	Description     	: 
	Input Parameters 	: strObject - Object Name of Edit Box
						: strLabel - To be printed on extent report
	                    : strKey - Paramiter name to get the data value from TestData Table                        
	Return Value    	: bResult
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String ng_DropDown(WebElement element,String strLabel, String strKey) throws Exception {
		String strVal = getTestDataValue(strKey);			
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}	
		try {
			waitForPageToLoad();
			explicitWait(element, 20);
			ng_waitUntilElementDisplayed(element,20);
			ng_scrollIntoViewElement(element, strLabel);			
			ng_waitForElementEnabled(element,20);
			
			Select objSelect = new Select(element);
			objSelect.selectByVisibleText(strVal);			
			String strDesc = "Value '" + strVal + "' is selected successfully from '" + strLabel + "' list.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
		} catch (Exception e) {
			String strDesc = "Items in the  List  "+strLabel + "' are not displayed. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 	
		return Global.bResult;
	}
	
	/*----------------------------------------------------------------------------
	Function Name    	: ng_SelectListtable
	Description     	: 
	Input Parameters 	: strObject - Object Name of Edit Box
						: strLabel - To be printed on extent report
	                    : strKey - Paramiter name to get the data value from TestData Table                        
	Return Value    	: bResult
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String ng_SelectListtable(WebElement element,String strLabel, String strKey) throws Exception {
		String strVal = getTestDataValue(strKey);		
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}	
		try {
			waitForPageToLoad();				
			if(StringUtils.isNotEmpty(ng_getTextBoxValue(element, strLabel))) {
				clearTextField(element);
		    	ng_waitImplicitly(2);		    	
		    	waitForPageToLoad();
			}					
			ng_enterText(element, strLabel, strKey);
			ng_waitImplicitly(2);
			element.sendKeys(Keys.ENTER);
			ng_waitImplicitly(5);

			String triangleloc="//label[text()='"+strLabel+"']/preceding::td/following::td//a[contains(@title,'Search: "+strLabel+"')][1]";
			//span[text()='Distribution Set']//following::a[contains(@title,'Search: Distribution Set')]
			
			WebElement trianglebox=driver.findElement(By.xpath(triangleloc));
			
			trianglebox.click();
			ng_waitImplicitly(3);
			WebElement dropdownselect=driver.findElement(By.xpath("//div[contains(@id,'dropdownPopup::dropDownContent')]//span[text()='"+strVal+"']"));
			dropdownselect.click();
			
			String strDesc = "Value '" + strVal + "' is selected successfully from '" + strLabel + "' list.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
		} catch (Exception e) {
			String strDesc = "Items in the  List  "+strLabel + "' are not displayed. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 	
		return Global.bResult;
	}
	
	/*----------------------------------------------------------------------------
	Function Name    	: clickWebElement
	Description     	: This function clicks the WebElement object
	Input Parameters 	: strObject - Object Name of Web Element
						: strLabel - To be printed on extent report
	                    : strKey - Paramiter name to get the data value from TestData Table
	Return Value    	: bResult
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String ng_clickWebElement(WebElement element, String strLabel, String strKey) throws Exception {
		String strVal = getTestDataValue(strKey);
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		try {		
			waitForPageToLoad();
			explicitWait(element,20);
			
			//ng_waitUntilElementDisplayed(element,20);
			ng_scrollIntoViewElement(element, strLabel);			
			ng_waitForElementEnabled(element,20);
			
			if(Global.gstrHighlighter == true) {
				highLighterMethod(element);
			}
			String onClickScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('click',true, false);arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onclick');}";
			if (element.getAttribute("onclick")!=null) {
				element.click();
			}			
			else {
				js.executeScript(onClickScript,element);
			}	
			waitForPageToLoad();
			String strDesc = "Successfully clicked on '" + strLabel + "'  WebElement.";			
			writeHTMLResultLog(strDesc, "pass");
			takeScreenShotAndLog("pass");			
			Global.bResult = "True";
		} catch (Exception e) {
			String strDesc = "WebElement " + strLabel +  "' is not displayed on the screen. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 								
		return Global.bResult;
	}

	/*----------------------------------------------------------------------------
	Function Name       :writeHTMLResultLog
	Description         :This function will create the test Log File
	Input Parameter    	:strDescription - Description to be printed
	                    :intPassFail
	Return Value        :None
	Author		        :
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static void writeHTMLResultLog(String strDescription, String strPassFail) throws Exception {
		if (strPassFail == "pass") {
			Global.test.log(LogStatus.PASS, strDescription);
		} else if (strPassFail == "fail") {
			Global.test.log(LogStatus.FAIL, strDescription);
		} else if (strPassFail == "info") {
			Global.test.log(LogStatus.INFO, strDescription);
		}
	}

	/*----------------------------------------------------------------------------
	Function Name       :TakeScreenShot
	Description         :This function takes the screen shot of the application
	Input Parameter    	:strDescription - Description to be printed
	Return Value        :None
	Author		        :
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static void takeScreenShotAndLog(String strPassFail) throws Exception {
		if(Global.gstrScreenShotFlag == true) {

			if (strPassFail == "pass") {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String tImage = "Run_" + Utility.getCurrentDatenTime("dd-MM-yy") + "_"
						+ Utility.getCurrentDatenTime("H-mm-ss a");
				FileUtils.copyFile(scrFile,
						new File(Global.gstrScreenshotsDir + "\\" + Global.gTCName + "\\img_" + tImage + ".jpg"));
				String image = Global.test
						.addScreenCapture(Global.gstrScreenshotsDir + "\\" + Global.gTCName + "\\img_" + tImage + ".jpg");
				Global.test.log(LogStatus.PASS, image);
			} else if (strPassFail == "fail") {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String tImage = "Run_" + Utility.getCurrentDatenTime("dd-MM-yy") + "_"
						+ Utility.getCurrentDatenTime("H-mm-ss a");
				FileUtils.copyFile(scrFile,
						new File(Global.gstrScreenshotsDir + "\\" + Global.gTCName + "\\img_" + tImage + ".jpg"));
				String image = Global.test
						.addScreenCapture(Global.gstrScreenshotsDir + "\\" + Global.gTCName + "\\img_" + tImage + ".jpg");
				Global.test.log(LogStatus.FAIL, image);
			}
		}
	}

	/*----------------------------------------------------------------------------
	Function Name    	: getCurrentDatenTime
	Description     	: Function to get Current Date and Time
	Input Parameters 	: format
	Return Value    	: Current Time
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	public static String getCurrentDatenTime(String format) {
		Calendar cal = Calendar.getInstance();
		cc = cal;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/*----------------------------------------------------------------------------
	Function Name    	: getObject
	Description     	: Find element BY using object type and value
	Input Parameters 	: key
	Return Value    	: object
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	private static By getObject(String key) throws IOException {
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		String obj = allObjects.getProperty(key);
		String[] arrOfStr = obj.split(",", 2);
		String objectType = arrOfStr[0];
		String objectValue = arrOfStr[1];
		// private By getObject(Properties p,String objectName,String objectType) throws
		// Exception{
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(objectValue);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("NAME")) {
			return By.name(objectValue);
		}
		// find by name
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(objectValue);
		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSSSELECTOR")) {
			return By.cssSelector(objectValue);
		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {
			return By.linkText(objectValue);
		} else if (objectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(objectValue);
		} else if (objectType.equalsIgnoreCase("ID")) {
			return By.id(objectValue);
		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
			return By.partialLinkText(objectValue);
		}
		return null;
	}
	
	/*----------------------------------------------------------------------------
	Function Name    	: waitForPageToLoad
	Description     	: wait For Page To Load
	Input Parameters 	: None
	Return Value    	: None
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
	// Wait 
    public static void waitForPageToLoad() {    	
    	 // Wait for Javascript to load
    	ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};   
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(expectation);
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
		
		for (int i=0; i<25; i++){ 
			try {
				Thread.sleep(1000);				
			} catch (InterruptedException e) {} 
			   	//To check page ready state.
			   	if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			   		break; 
			   	}   
		}
    }
    
    /*----------------------------------------------------------------------------
	Function Name    	: ng_getTextBoxValue
	Description     	: Get the text box value
	Input Parameters 	: 
	Return Value    	: String
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
    
    public static String ng_getTextBoxValue(WebElement element, String elementDescription) throws Exception {
        String attValue = "";
        ng_scrollIntoViewElement(element, elementDescription);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object o = js.executeScript("return arguments[0].value;", element);
        attValue = (o == null) ? "" : o.toString();        
        return attValue;	  
  	}
    
    /*----------------------------------------------------------------------------
	Function Name    	: ng_scrollIntoViewElement
	Description     	: 
	Input Parameters 	: 
	Return Value    	: 
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
    public static void ng_scrollIntoViewElement(WebElement element, String elementDescription) throws Exception {
		try {
			if (!element.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
				//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}			
		} catch (Exception e) {
										
		}
	}
    
    /*----------------------------------------------------------------------------
	Function Name    	: clearTextField
	Description     	: 
	Input Parameters 	: 
	Return Value    	: 
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
    public static void clearTextField(WebElement element) {
		//element.sendKeys(Keys.HOME, Keys.SHIFT, Keys.END, Keys.BACK_SPACE);
		element.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		clearTextFieldMulLines(element);
	}
    
    /*----------------------------------------------------------------------------
	Function Name    	: clearTextFieldMulLines
	Description     	: 
	Input Parameters 	: 
	Return Value    	: 
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
    public static void clearTextFieldMulLines(WebElement element) {
		element.sendKeys(Keys.CONTROL, Keys.HOME);
		element.sendKeys(Keys.CONTROL, Keys.SHIFT, Keys.END);
		element.sendKeys(Keys.BACK_SPACE);
	}
    
    /*----------------------------------------------------------------------------
	Function Name    	: ng_waitImplicitly
	Description     	: Method for waiting a certain amount of time.
	Input Parameters 	: 
	Return Value    	: 
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
  
    public static void ng_waitImplicitly(int time) {
		long startTime = 0;
		long endTime = 0;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();		
	}
    
    /*----------------------------------------------------------------------------
	Function Name    	: ng_clickUsingActions
	Description     	: Method clicks on a specific element using actions.
	Input Parameters 	: 
	Return Value    	: 
	Author		        : 
	Date of creation	:
	Date of modification:
	----------------------------------------------------------------------------*/
    public static String ng_clickUsingActions(WebElement element, String strLabel, String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey);				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		try {
			waitForPageToLoad();
			explicitWait(element,20);			
			ng_waitUntilElementDisplayed(element,20);
			ng_scrollIntoViewElement(element, strLabel);			
			ng_waitForElementEnabled(element,20);
			if(Global.gstrHighlighter == true) {
				highLighterMethod(element);
			}
			Actions builder = new Actions(driver);
			builder.moveToElement(element).click().build().perform();	
			waitForPageToLoad();           
			
			String strDesc = "Successfully clicked on '" + strLabel + "'  WebElement.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";			
		} catch (Exception e) {
			String strDesc = "WebElement '" + strLabel + "' is not displayed on the screen. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 	
		return Global.bResult;		
	}
    
    /*----------------------------------------------------------------------------
  	Function Name    	: ng_selectWindow
  	Description     	: Selects a particular Window on the basis of the parameters passed.
  	Input Parameters 	: 
  	Return Value    	: 
  	Author		        : 
  	Date of creation	:
	Date of modification:
  	----------------------------------------------------------------------------*/
    public static String ng_selectWindow(String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey);				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		String windowId = null;
		try {			
			waitForPageToLoad();
			//ng_waitImplicitly(5);
			
			driver.switchTo().activeElement();
			windowId = driver.getWindowHandle();
			driver.switchTo().window(windowId);
			//waitForPageToLoad();
			String strDesc = "Window has switched to: " + windowId;
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
			
		} catch (Exception e) {
			String strDesc = "Failed to switched to: " + windowId + " Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		}					
		return strVal;
	}
    
    /*----------------------------------------------------------------------------
  	Function Name    	: ng_selectFrame
  	Description     	: Selects a particular Frame on the basis of the parameters passed.
  	Input Parameters 	: 
  	Return Value    	: 
  	Author		        : 
  	Date of creation	:
	Date of modification:
  	----------------------------------------------------------------------------*/
    public static String ng_selectFrame(WebElement element,String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey);				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		String windowId = null;
		try {			
			waitForPageToLoad();
			//ng_waitImplicitly(5);
			
			driver.switchTo().frame(element);
						
			//waitForPageToLoad();
			String strDesc = "Switched for Frame";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
			
		} catch (Exception e) {
			String strDesc = "Failed to switched to frame : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		}					
		return strVal;
	}
    
    /*----------------------------------------------------------------------------
  	Function Name    	: ng_typeAndTab
  	Description     	: ng_typeAndTab
  	Input Parameters 	: 
  	Return Value    	: 
  	Author		        :
  	Date of creation	:
	Date of modification: 
  	----------------------------------------------------------------------------*/
    public static String ng_typeAndTab(WebElement element, String strLabel, String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey);					 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		try {
			//waitForPageToLoad();
		    ng_enterText(element, strLabel, strKey);
		    ng_sendTab(element, strLabel);
		    //waitForPageToLoad();		   
			Global.bResult = "True";
		} catch (Exception e) {			
			Global.bResult = "False";
			Global.objErr = "11";
		}		
		return Global.bResult;
	}
    
    /*----------------------------------------------------------------------------
  	Function Name    	: clearTextAndTab
  	Description     	: clearTextAndTab
  	Input Parameters 	: 
  	Return Value    	: 
  	Author		        : 
  	Date of creation	:
	Date of modification:
  	----------------------------------------------------------------------------*/
    public static String clearTextAndTab(WebElement element, String strLabel, String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey);   	 
    	if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		try {
			//waitForPageToLoad();
			if(Global.gstrHighlighter == true) {
				highLighterMethod(element);
			}
			//ng_waitUntilElementDisplayed(element,20);
			//ng_scrollIntoViewElement(element, strLabel);
			//ng_waitForElementEnabled(element,20);
			element.clear();
		    //clearTextField(element);
		    ng_sendTab(element, strLabel);
		    //waitForPageToLoad();
		    String strDesc = "Cleared text for " + strLabel + "' textbox.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
		} catch (Exception e) {	
			String strDesc = "Failed to clear text for " + strLabel + "' textbox. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 				
		return Global.bResult;
	}
    
    
    /*----------------------------------------------------------------------------
  	Function Name    	: ng_sendTab
  	Description     	: ng_typeAndTab
  	Input Parameters 	: 
  	Return Value    	: 
  	Author		        : 
  	Date of creation	:
	Date of modification:
  	----------------------------------------------------------------------------*/
    public static void ng_sendTab(WebElement ele, String description) {
		try {
			ele.sendKeys(Keys.TAB);
	        waitForPageToLoad();	        									
		} catch (Exception e) {
			
		}		
	}
    
    /*----------------------------------------------------------------------------
  	Function Name    	: ng_getElementText
  	Description     	: ng_getElementText
  	Input Parameters 	: 
  	Return Value    	: 
  	Author		        : 
  	Date of creation	:
	Date of modification:
  	----------------------------------------------------------------------------*/
    public static String ng_getElementText(WebElement element, String strLabel, String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey);  				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		try {			
			waitForPageToLoad();			
			//explicitWait(element,20);
			WebElement objElement = wait.until(ExpectedConditions.visibilityOf(element));
			//ng_waitUntilElementDisplayed(objElement,20);
			//ng_scrollIntoViewElement(objElement, strLabel);			

			if(Global.gstrHighlighter == true) {
				highLighterMethod(objElement);
			}
			String value = objElement.getText();
			String strDesc = "Successfully get the text "+ value +" for '" + strLabel + "'  WebElement.";
			writeHTMLResultLog(strDesc, "pass");
			takeScreenShotAndLog("pass");
			Global.bResult = "True";					
		} catch (Exception e) {
			String strDesc = "Failed to get the text for  '" + strLabel + " Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} 		
		return Global.bResult;
	}

    /*----------------------------------------------------------------------------
   	Function Name    	: explicitWait
   	Description     	: explicit Wait
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
    public static void explicitWait(WebElement elementID, long timeout) {	
		long startTime = 0;
		long endTime = 0;
		By locator = null;
		try {
			locator = getByFromWebElement(elementID);
			
			
			final By locator_Final = locator;

			startTime = System.currentTimeMillis();
			elementID = (new WebDriverWait(driver, timeout))
					.until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver wd) {
							return wd.findElement(locator_Final);
						}
					});
			try {
				//driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOf(elementID));			
				//wait.until(ExpectedConditions.visibilityOfElementLocated(locator_Final));
				//wait.until(ExpectedConditions.presenceOfElementLocated(locator_Final));
				wait.until(ExpectedConditions.elementToBeClickable(locator_Final));									
			} catch (TimeoutException te) {
				System.out.println("Time out exception for wait visibility: " + te.getMessage());
			}
			endTime = System.currentTimeMillis();			
		} catch (Exception e) {
			endTime = System.currentTimeMillis();			
		}
	}
    
    /*----------------------------------------------------------------------------
   	Function Name    	: getByFromWebElement
   	Description     	: get By From WebElement
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
    public static By getByFromWebElement(WebElement elementID) {
		By byLocator = null;
		String allStr = getNameFromObjectID(elementID);
		String elementPath = allStr.split(":")[1].trim();
		String locatorName = allStr.split(":")[0].trim();
		if (elementPath.endsWith("]")) {
			elementPath = elementPath.substring(0, elementPath.length() - 1)
					.trim();
		}
		if (locatorName.indexOf(" ") != -1
				&& !locatorName.equals("partial link text")) {
			String partOne = locatorName.split(" ")[0];
			String partTwo = locatorName.split(" ")[1];
			partTwo = Character.toUpperCase(partTwo.charAt(0))
					+ partTwo.substring(1);
			locatorName = partOne + partTwo;
		} else if (locatorName.equals("partial link text")) {
			locatorName = "partialLinkText";
		}
		try {
			switch (locatorName) {
			case "className":
				byLocator = By.className(elementPath);
				break;
			case "cssSelector":
				byLocator = By.cssSelector(elementPath);
				break;
			case "id":
				byLocator = By.id(elementPath);
				break;
			case "linkText":
				byLocator = By.linkText(elementPath);
				break;
			case "name":
				byLocator = By.name(elementPath);
				break;
			case "partialLinkText":
				byLocator = By.partialLinkText(elementPath);
				break;
			case "tagName":
				byLocator = By.tagName(elementPath);
				break;
			case "xpath":
				byLocator = By.xpath(elementPath);
				break;
			default:
				break;
			}
		} catch (NoSuchElementException elEx) {
			elEx.printStackTrace();
		}
		return byLocator;
	}
    
    public static String getNameFromObjectID(WebElement elementID) {
		return elementID.toString().split("->")[1].trim();
	}
    
    /*----------------------------------------------------------------------------
   	Function Name    	: ng_clickElementUsingJS
   	Description     	: Method clicks on a specific element using JS.
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
    public static String ng_clickElementUsingJS(WebElement element, String strLabel, String strKey) throws Exception {
    	String strVal = getTestDataValue(strKey); 				 
		if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
			return String.valueOf(true);
		}
		try {
			waitForPageToLoad();
			explicitWait(element,20);
			ng_waitUntilElementDisplayed(element,20);
			ng_scrollIntoViewElement(element, strLabel);			
			ng_waitForElementEnabled(element,20);
			if(Global.gstrHighlighter == true) {
				highLighterMethod(element);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			waitForPageToLoad();
			String strDesc = "Successfully clicked on '" + strLabel + "'  WebElement.";
			writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";			
		} catch (Exception e) {
			String strDesc = "WebElement '" + strLabel + "' is not displayed on the screen. Error Message : " + e.getMessage();
			writeHTMLResultLog(strDesc, "fail");
			takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		}		
		return Global.bResult;	
	}
    
    /*----------------------------------------------------------------------------
   	Function Name    	: clickItem
   	Description     	: clickItem - Application Utility
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        :
   	Date of creation	:
	Date of modification: 
   	----------------------------------------------------------------------------*/
    public static String clickItem(String strItenVal) throws Exception {		
		try {			
			Utility.waitForPageToLoad();
			WebElement element = driver.findElement(By.xpath("//td//span[text()='"+ strItenVal +"']"));
			explicitWait(element,20);
			ng_scrollIntoViewElement(element, strItenVal);
			if(Global.gstrHighlighter == true) {
				highLighterMethod(element);
			}
			element.click();
			Utility.waitForPageToLoad();

			String strDesc = "Successfully clicked on item : " + strItenVal;
			Utility.writeHTMLResultLog(strDesc, "pass");
			Global.bResult = "True";
			
		} catch (Exception e) {
			String strDesc = "Failed to click on " + strItenVal + " Error Message : " + e.getMessage();
			Utility.writeHTMLResultLog(strDesc, "fail");
			Utility.takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		}
		return Global.bResult;
	}
    
    /*----------------------------------------------------------------------------
   	Function Name    	: logoutFinally
   	Description     	: logoutFinally
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
    public static String logoutFinally() throws Exception {	
    	//WebDriverWait wait = new WebDriverWait(driver, 20);
		try {						
			Utility.waitForPageToLoad();						
						
		    WebElement elmUserName = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a//span[contains(@class,'xiq')]"))));		    
			highLighterMethod(elmUserName);			
			elmUserName.click();
			
			Utility.waitForPageToLoad();
			//ng_waitImplicitly(5);						
			
			WebElement elmSignOut = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"))));			
			highLighterMethod(elmSignOut);			
			elmSignOut.click();
			Utility.waitForPageToLoad();
			//ng_waitImplicitly(5);
											
		    WebElement eleWarning = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Yes']"))));
		    highLighterMethod(eleWarning);
		    if (eleWarning != null) {
		    	eleWarning.click();
		    	Utility.waitForPageToLoad();
				//ng_waitImplicitly(5);
		    }		    						
			
			WebElement elmConfirm = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='Confirm']"))));			 			
			highLighterMethod(elmConfirm);			
			elmConfirm.click();
			Utility.waitForPageToLoad();
			driver.quit();
		} catch (Exception e) {
			driver.quit();
		}
		return Global.bResult;	
	}
    
    /*----------------------------------------------------------------------------
   	Function Name    	: highLighterMethod
   	Description     	: highLighterMethod
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
    public static void highLighterMethod(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
    
    /*----------------------------------------------------------------------------
   	Function Name    	: clickWarning
   	Description     	: clickWarning
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static void clickWarning(WebElement element) throws Exception {
		
		try {
			waitForPageToLoad();
			explicitWait(element,20);
			ng_scrollIntoViewElement(element, "");
			ng_waitImplicitly(2);
			ng_waitForElementEnabled(element,20);
			if (element != null) {
				element.click();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	/*----------------------------------------------------------------------------
   	Function Name    	: quitdriver
   	Description     	: quitdriver
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static void quitdriver(){
		driver.quit();
	}
    
	/*----------------------------------------------------------------------------
   	Function Name    	: ng_waitForElementEnabled
   	Description     	: Wait until element is enabled for particular number of seconds
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static void ng_waitForElementEnabled(WebElement ele, int sTime) {
		//ng_waitImplicitly(1);
		for (int k = 0; k <= sTime; k++) {
			ng_waitImplicitly(1);
			if (ele.isEnabled()) {
				break;
			}
		}
	}
	
	/*----------------------------------------------------------------------------
   	Function Name    	: ng_waitUntilElementDisplayed
   	Description     	: Wait until element is displayed for particular number of seconds
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static void ng_waitUntilElementDisplayed(WebElement ele, int sTime) {
		//ng_waitImplicitly(1);
		for (int k = 0; k <= sTime; k++) {
			ng_waitImplicitly(1);
			if (ele.isDisplayed()) {
				break;
			}
		}
	}
	
	/*----------------------------------------------------------------------------
   	Function Name    	: ng_waitUntilElementVisible
   	Description     	: Wait until element is Visible for particular number of seconds
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static void ng_waitUntilElementVisible(WebElement ele, int sTime) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	/*----------------------------------------------------------------------------
   	Function Name    	: ng_waitUntilElementVisible
   	Description     	: Wait until element is Visible for particular number of seconds
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static String ng_RandomAlphaNum (String strChar,int length){
		String alphabet = 
		        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
		int n = alphabet.length(); //10

		String result = new String(); 
		Random r = new Random(); //11

		for (int i=0; i<length; i++) //12
		    result = result + alphabet.charAt(r.nextInt(n)); //13

		return strChar + result;
	}


	/*----------------------------------------------------------------------------
   	Function Name    	: ng_waitUntilElementVisible
   	Description     	: Wait until element is Visible for particular number of seconds
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	public static int ng_RandomNum (){
		
		int range = (100000 - 1) + 1;     
		int a= (int)(Math.random() * range) + 1;

		return a;
	}
	/*----------------------------------------------------------------------------
   	Function Name    	: getDigitsFromString
   	Description     	: Wait until element is Visible for particular number of seconds
   	Input Parameters 	: 
   	Return Value    	: 
   	Author		        : 
   	Date of creation	:
	Date of modification:
   	----------------------------------------------------------------------------*/
	 public static String ng_getDigitsFromString(String strValue){
         String str = strValue.trim();
         String digits="";
         for (int i = 0; i < str.length(); i++) {
             char chrs = str.charAt(i);              
             if (Character.isDigit(chrs))
                 digits = digits+chrs;
         }
         return digits;
     }
	 /*----------------------------------------------------------------------------
		Function Name    	: ng_DropDownByIndex
		Description     	: 
		Input Parameters 	: strObject - Object Name of Edit Box
							: strLabel - To be printed on extent report
		                    : strKey - Paramiter name to get the data value from TestData Table                        
		Return Value    	: bResult
		Author		        : 
		Date of creation	:
		Date of modification:
		----------------------------------------------------------------------------*/
		public static String ng_DropDownByIndex(WebElement element,String strLabel, String strKey) throws Exception {
			String strVal = getTestDataValue(strKey);			
			if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
				return String.valueOf(true);
			}	
			try {
				waitForPageToLoad();
				//explicitWait(element, 20);
				ng_scrollIntoViewElement(element, strLabel);	
				Select objSelect = new Select(element);
				int index=Integer.parseInt(strVal);
				objSelect.selectByIndex(index);		
				String strDesc = "Value '" + strLabel + "' is selected successfully from '" + strLabel + "' list.";
				writeHTMLResultLog(strDesc, "pass");
				Global.bResult = "True";
			} catch (Exception e) {
				String strDesc = "Items in the  List  "+strLabel + "' are not displayed. Error Message : " + e.getMessage();
				writeHTMLResultLog(strDesc, "fail");
				takeScreenShotAndLog("fail");
				Global.bResult = "False";
				Global.objErr = "11";
			} 	
			return Global.bResult;
		}
		
		
		/*----------------------------------------------------------------------------
		Function Name    	: clickWebElement
		Description     	: This function clicks the WebElement object
		Input Parameters 	: strObject - Object Name of Web Element
							: strLabel - To be printed on extent report
		                    : strKey - Paramiter name to get the data value from TestData Table
		Return Value    	: bResult
		Author		        : 
		Date of creation	:
		Date of modification:
		----------------------------------------------------------------------------*/
		public static String ng_clickOk(WebElement element, String strLabel, String strKey) throws Exception {
			String strVal = getTestDataValue(strKey);
			if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
				return String.valueOf(true);
			}
			try {
				/*waitForPageToLoad();
				explicitWait(element,20);
				ng_waitUntilElementDisplayed(element,20);
				ng_scrollIntoViewElement(element, strLabel);			
				ng_waitForElementEnabled(element,20);
				if(Global.gstrHighlighter == true) {
					highLighterMethod(element);
				}*/
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				//waitForPageToLoad();
				String strDesc = "Successfully clicked on '" + strLabel + "'  WebElement.";
				writeHTMLResultLog(strDesc, "pass");
				Global.bResult = "True";			
			} catch (Exception e) {
				String strDesc = "WebElement '" + strLabel + "' is not displayed on the screen. Error Message : " + e.getMessage();
				writeHTMLResultLog(strDesc, "fail");
				takeScreenShotAndLog("fail");
				Global.bResult = "False";
				Global.objErr = "11";
			}			
			return Global.bResult;
		}
			
		
		/*----------------------------------------------------------------------------
	  	Function Name    	: ng_getElementText_PopUp
	  	Description     	: ng_getElementText
	  	Input Parameters 	: 
	  	Return Value    	: 
	  	Author		        : 
	  	Date of creation	:
		Date of modification:
	  	----------------------------------------------------------------------------*/
	    public static String ng_getElementText_PopUp(WebElement element, String strLabel, String strKey) throws Exception {
	    	String strVal = getTestDataValue(strKey);  				 
			if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
				return String.valueOf(true);
			}
			try {			
				//waitForPageToLoad();		
				//explicitWait(element,20);mahesh
				//WebElement objElement = wait.until(ExpectedConditions.visibilityOf(element));
				//ng_waitUntilElementDisplayed(objElement,20);
				//ng_scrollIntoViewElement(objElement, strLabel);			
				String value=(String) ((JavascriptExecutor) driver).executeScript("return arguments[0].text;", element);
				
				
				if(Global.gstrHighlighter == true) {
					highLighterMethod(element);
				}
				//String value = objElement.getText();
				String strDesc = "Successfully get the text "+ value +" for '" + strLabel + "'  WebElement.";
				writeHTMLResultLog(strDesc, "pass");
				takeScreenShotAndLog("pass");
				Global.bResult = "True";					
			} catch (Exception e) {
				String strDesc = "Failed to get the text for  '" + strLabel + " Error Message : " + e.getMessage();
				writeHTMLResultLog(strDesc, "fail");
				takeScreenShotAndLog("fail");
				Global.bResult = "False";
				Global.objErr = "11";
			} 		
			return Global.bResult;
		}
	    
	    /*----------------------------------------------------------------------------
		Function Name    	: ng_clickSimply
		Description     	: This function clicks the WebElement object
		Input Parameters 	: strObject - Object Name of Web Element
							: strLabel - To be printed on extent report
		                    : strKey - Paramiter name to get the data value from TestData Table
		Return Value    	: bResult
		Author		        : 
		Date of creation	:
		Date of modification:
		----------------------------------------------------------------------------*/
		public static String ng_clickSimply(WebElement element, String strLabel, String strKey) throws Exception {
			String strVal = Utility.getTestDataValue(strKey);
			if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
				return String.valueOf(true);
			}
			try {		
				Utility.waitForPageToLoad();								
				if(Global.gstrHighlighter == true) {
					Utility.highLighterMethod(element);
				}			
				element.click();	
				Utility.waitForPageToLoad();
				String strDesc = "Successfully clicked on '" + strLabel + "'  WebElement.";			
				Utility.writeHTMLResultLog(strDesc, "pass");
				Utility.takeScreenShotAndLog("pass");			
				Global.bResult = "True";
			} catch (Exception e) {
				String strDesc = "WebElement " + strLabel +  "' is not displayed on the screen. Error Message : " + e.getMessage();
				Utility.writeHTMLResultLog(strDesc, "fail");
				Utility.takeScreenShotAndLog("fail");
				Global.bResult = "False";
				Global.objErr = "11";
			} 								
			return Global.bResult;
		}
		/*----------------------------------------------------------------------------
		Function Name    	: ng_enterTextDirect
		Description     	: This function enters a data into a text box
		Input Parameters 	: strObject - Object Name of Edit Box
							: strLabel - To be printed on extent report
		                    : strKey - Paramiter name to get the data value from TestData Table                        
		Return Value    	: bResult
		Author		        : 
		Date of creation	:
		Date of modification:
		----------------------------------------------------------------------------*/
		public static String ng_enterTextDirect(WebElement element, String strLabel, String strKey) throws Exception {
			
			String strVal = getTestDataValue(strKey);				 
			if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
				return String.valueOf(true);
			}	
			try {
				waitForPageToLoad();
				ng_scrollIntoViewElement(element, strLabel);						
				clearTextField(element);	
				if(Global.gstrHighlighter == true) {
					highLighterMethod(element);
				}	
				element.sendKeys(strVal);
				//waitForPageToLoad();
				String strDesc = "Successfully entered '" + strVal + "' in '" + strLabel + "' textbox.";
				writeHTMLResultLog(strDesc, "pass");
				Global.bResult = "True";
			} catch (Exception e) {
				String strDesc = "'" + strLabel + "' textbox does not exist. Error Message : " + e.getMessage();
				writeHTMLResultLog(strDesc, "fail");
				takeScreenShotAndLog("fail");
				Global.bResult = "False";
				Global.objErr = "11";
			}
			return Global.bResult;
		}
		
		/*----------------------------------------------------------------------------
		Function Name    	: ng_SelectListDirect
		Description     	: 
		Input Parameters 	: strObject - Object Name of Edit Box
							: strLabel - To be printed on extent report
		                    : strKey - Paramiter name to get the data value from TestData Table                        
		Return Value    	: bResult
		Author		        : 
		Date of creation	:
		Date of modification:
		----------------------------------------------------------------------------*/
		public static String ng_TypeAndEnter(WebElement element,String strLabel, String strKey) throws Exception {
			String strVal = getTestDataValue(strKey);	
			if ((strVal.contains("SKIP")) || (Global.objErr == "11")) {
				return String.valueOf(true);
			}	
			try {
				ng_enterTextDirect(element, strLabel, strKey);			
				ng_waitImplicitly(1);  
				element.sendKeys(Keys.ENTER);
				waitForPageToLoad();	
				String strDesc = "Value '" + strVal + "' is selected successfully from '" + strLabel + "' list.";
				writeHTMLResultLog(strDesc, "pass");
				Global.bResult = "True";
			} catch (Exception e) {
				String strDesc = "Items in the  List  "+strLabel + "' are not displayed. Error Message : " + e.getMessage();
				writeHTMLResultLog(strDesc, "fail");
				takeScreenShotAndLog("fail");
				Global.bResult = "False";
				Global.objErr = "11";
			} 	
			return Global.bResult;
		}
		
}
