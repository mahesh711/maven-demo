package pages;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import lib.Global;
import lib.TestData;
import lib.Utility;
import pages.LoginLogout;
public class HomePage {	
    //WebDriver driver;
    
    @FindBy(xpath="//a[contains(@id,'home')]")
    WebElement lnkHome;
    @FindBy(xpath="//a[contains(text(),'Procurement')]")
    WebElement lnkProcurement;
    @FindBy(xpath="//a[contains(text(),'Purchase Requisitions')]")
    WebElement lnkPurchaseRequisitions;
    @FindBy(xpath="//a[contains(text(),'Purchasing')]")
    WebElement lnkPurchasing;
    @FindBy(xpath="//a[contains(text(),'Payables')]")
    WebElement lnkPayables;
    @FindBy(xpath="//a[text()='Invoices']")
    WebElement lnkInvoices;
    @FindBy(xpath="//a[contains(text(),'Receivables')]")
    WebElement lnkReceivables;
    @FindBy(xpath="//a[contains(text(),'Billing')]")
    WebElement lnkBilling;
    @FindBy(xpath="//a[contains(text(),'Payments')]")
    WebElement lnkPayments;
    
    @FindBy(xpath="//a[contains(text(),'Fixed Assets')]")
    WebElement lnkFixedAssets;
    @FindBy(xpath="//a[contains(text(),'General Accounting')]")
    WebElement lnkGeneralAccounting;
    @FindBy(xpath="//a[contains(text(),'Journals')]")
    WebElement lnkJournals;
    @FindBy(xpath="//a[contains(text(),'My Receipts')]")
    WebElement lnkMyReceipts;
    
    //R13 CHHANGES
    @FindBy(xpath="//a[contains(text(),'Purchase Orders')]")
    WebElement lnkPurchaseOrders;
    
    
    public HomePage(Utility util) throws Exception{
        //this.driver = driver;
        PageFactory.initElements(util.ng_returnDriver(), this);
    } 
    
    /*----------------------------------------------------------------------------
    Function Name    	: GoToHome
    Description     	: This function used to navigate to home page.     
    Author				:
    Date of creation	:
	Date of modification:
    ----------------------------------------------------------------------------*/ 
    public void goToHome() throws Exception {    	
        if(Global.objErr == "11"){
            return;
        }    
        try {
        	TestData td = new TestData ();
    	    Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	    
    	    Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);
    	    
    	    Utility.waitForPageToLoad();
            Utility.ng_clickWebElement(lnkHome,"Home","HomeClick");
            Utility.ng_verifyPage("Home","HomeCheck");
            Utility.ng_clickSimply(lnkProcurement,"Procurement","ProcurementClick");
            Utility.ng_clickSimply(lnkPurchaseRequisitions,"Purchase Requisitions","PurchaseRequisitionsClick");
            Utility.ng_clickSimply(lnkPurchasing,"lnkPurchasing","PurchasingClick");
            Utility.ng_clickWebElement(lnkPayables, "Payables", "PayablesClick");
            Utility.ng_clickWebElement(lnkInvoices, "Invoices", "InvoicesClick");
            Utility.ng_clickWebElement(lnkReceivables, "Receivables", "ReceivablesClick");
            Utility.ng_clickWebElement(lnkBilling, "Billing", "BillingClick");
            Utility.ng_clickWebElement(lnkPayments, "Payments", "PaymentsClick");
            
            Utility.ng_clickWebElement(lnkPurchaseOrders,"PurchaseOrders","PurchaseOrdersClick");
            // Utility.ng_clickWebElement(lnkFixedAssets,"FixedAssets","FixedAssetsClick");
            Utility.ng_clickWebElement(lnkGeneralAccounting,"GeneralAccounting","GeneralAccountingClick");
            Utility.ng_clickWebElement(lnkJournals,"Journals","JournalsClick");
            Utility.ng_clickWebElement(lnkMyReceipts,"MyReceipts","MyReceiptsClick");
		} catch (Exception e) {
			Global.objErr = "11";			
		}       
    }
}
