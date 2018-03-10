package pages;

import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import lib.AppUtility;
import lib.Global;
import lib.TestData;
import lib.Utility;
import pages.LoginLogout;

public class Move {
	
	 @FindBy(xpath="//*[@title='Accounts Tab']")
	 WebElement lnkAccountTab;
	 @FindBy(xpath="//input[@id='phSearchInput']")
	 WebElement edtPhSearchInput;
	 @FindBy(xpath="//input[@id='phSearchButton']")
	 WebElement btnPhSearchButton;
	 @FindBy(xpath="//a[text()='James Treanor, Jr.']")
	 WebElement lnkJamesTreanor;
	 @FindBy(xpath="//input[@value='Create Quote']")
	 WebElement btnCreateQuote;
	 @FindBy(xpath="//iframe[@id='edit_quote']")
	 WebElement frmEditQuote;
	 @FindBy(xpath="//a[text()='Save']")
	 WebElement lnkSave;
	 @FindBy(xpath="//a[text()='Save']//ancestor::tbody//preceding::tbody[1]/child::tr//child::a[text()='Add Line Item']")
	 WebElement btnAddLineItem;
	 @FindBy(xpath="//a[text()='Configure HeatMap']")
	 WebElement lnkConfigureHeatMap;	 
	 @FindBy(xpath="//a[@id='update']")
	 WebElement btnUpdate;
	 @FindBy(xpath="//table[@id='inventoryTable']")
	 WebElement tblInventoryTable;
	 
	 
	public Move(Utility util) throws Exception{
        //this.driver = driver;
        PageFactory.initElements(util.ng_returnDriver(), this);
    } 
	
	/*----------------------------------------------------------------------------
    Function Name    	: orderPlacement
    Description     	: This function used to perform order Placement.    
    Author				:
    Date of creation	:
	Date of modification:
    ----------------------------------------------------------------------------*/ 
    public void orderPlacement() throws Exception {    	
        if(Global.objErr == "11"){
            return;
        }    
        try {
        	TestData td = new TestData ();
    	    Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	    
    	    Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);
    	    
    	    Utility.waitForPageToLoad();
    	    Utility.ng_clickWebElement(lnkAccountTab, "AccountTab", "AccountTabClick");
    	    Utility.ng_enterText(edtPhSearchInput, "PhSearchInput", "PhSearchInputSet");
    	    Utility.ng_clickWebElement(btnPhSearchButton, "Ph Search Button", "PhSearchButtonClick");
    	    Utility.ng_clickWebElement(lnkJamesTreanor, "James Treanor", "JamesTreanorClick");
    	    Utility.ng_clickWebElement(btnCreateQuote, "Create Quote", "CreateQuoteClick");
    	    Utility.ng_waitImplicitly(4);
    	    Utility.ng_selectFrame(frmEditQuote,"EditQuoteCheck");   
    	    Utility.ng_clickWebElement(lnkSave, "Save", "SaveClick");
    	    Utility.ng_waitImplicitly(4);
    	    Utility.ng_clickWebElement(btnAddLineItem, "Add Line Item", "AddLineItemClick");
    	    Utility.ng_clickWebElement(lnkConfigureHeatMap, "Configure HeatMap", "ConfigureHeatMapClick");
    	    Utility.ng_waitImplicitly(4);
    	    Utility.ng_clickElementUsingJS(btnUpdate, "Update", "UpdateClick");
    	    Utility.ng_scrollIntoViewElement(tblInventoryTable, "InventoryTable");
    	    AppUtility.InventoryDataMove("InventoryDataSet");    	     
		} catch (Exception e) {
			Global.objErr = "11";			
		}       
    }
}
