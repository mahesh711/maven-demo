package pages;

import java.util.HashMap;


import java.util.concurrent.TimeUnit;
import java.io.IOException;

//import com.codoid.products.fillo.Select;
import com.mongodb.util.Util;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import lib.Utility;
import lib.TestData;
import lib.Global;
import lib.InitDriver;

public class Cloud {    
	public static String sStrPoNo;
	public static String sStrReqNo;
	public static String sStrInvcNo;
	//WebDriver driver;

	@FindBy(xpath="//a[text()='Requisition Line Entry']")
	WebElement lnkRequisitionLineEntry;
	@FindBy(xpath="//a[contains(@title,'Search: Item')]")
	WebElement elmSearchIcon;  
	@FindBy(xpath="//label[contains(text(),' Item')]//preceding-sibling::input")   
	WebElement edtItem;  
	@FindBy(xpath="//a[@title='Search: Item']//preceding-sibling::input")   
	WebElement edtItemNew;
	@FindBy(xpath="//button[text()='Search']")
	WebElement btnSearch;
	@FindBy(xpath="//button[text()='OK' and contains(@id,'DialogId::ok')]")
	WebElement btnSearchOk;
	@FindBy(xpath="//label[text()='UOM']//parent::td//following::input[1]")
	WebElement edtUOM;
	@FindBy(xpath="//label[text()='Price']//parent::td//following::input[1]")
	WebElement edtPrice;
	@FindBy(xpath="//label[text()='Currency']//parent::td//following::input[1]")
	WebElement edtCurrency;
	@FindBy(xpath="//span[text()='Add to Requisition']")
	WebElement btnAddtoRequisition;
	@FindBy(xpath="//nobr[contains(text(),'Requisition:')]")
	WebElement elmRequisitionNumber;
	@FindBy(xpath="//button[text()='Edit and Submit']")
	WebElement btnEditAndSubmit;
	@FindBy(xpath="//span[text()='Save']")
	WebElement btnSave;
	//@FindBy(xpath="//span[text()='Save']/ancestor::td//a[contains(@id,'saveMenu')]")
	@FindBy(xpath="//a[contains(@id,'saveMenu')]") 
	WebElement btnSavetriangle;
	//@FindBy(xpath="//td[contains(text(),'ave and Close')]")
	@FindBy(xpath="//span[(text()='S')]")  
	WebElement btnSavenClose;
	@FindBy(xpath="//span[text()='Check Funds']")
	WebElement btnCheckFunds;
	@FindBy(xpath="//button[text()='OK']//parent::td/child::button[2][text()='OK']")
	WebElement btnConfirmationOk;
	@FindBy(xpath="//span[text()='Manage Approvals']")
	WebElement btnManageApprovals;
	@FindBy(xpath="//span[contains(text(),'ack')]")
	WebElement btnBack;
	@FindBy(xpath="//button[contains(text(),'Sub')]")
	WebElement btnSubmit;
	@FindBy(xpath="//div[contains(text(),'was submitted.')]")
	WebElement elmConfRequisitionNumber;
	@FindBy(xpath="//span[contains(text(),'K')]//parent::button//parent::td/child::button[2]/child::span")
	WebElement btnConfOk;

	@FindBy(xpath="//img[@alt='Tasks']")
	WebElement imgTask;
	@FindBy(xpath="//a[text()='Process Requisitions']")
	WebElement lnkProcessRequisitions;
	@FindBy(xpath="//label[text()='Requisition']//parent::td//following::input[1]")
	WebElement edtRequisition;
	@FindBy(xpath="//label[text()='Buyer']//parent::td//following::input[1]")
	WebElement edtBuyer;
	@FindBy(xpath="//button[text()='Search']")
	WebElement btnSearchRequisition;
	@FindBy(xpath="//table[contains(@summary,'Search Results:')]//following-sibling::tbody/child::tr[1]")
	WebElement elmSearchRecordOne;
	@FindBy(xpath="//button[contains(text(),'Add to Document Builder')]")
	WebElement btnAddToDocumentBuilder;
	//----------------------------------------------
	//    @FindBy(xpath="//a[contains(@title,'Search: Supplier')]")
	//    WebElement elmSupplierSearchIcon;
	//    
	//    @FindBy(xpath="//label[text()='Supplier']//preceding::span[@title='At least one is required']//following::input[1]")
	//    WebElement edtSupplierNew;
	//    
	//    @FindBy(xpath="//button[text()='Search']")
	//    WebElement btnSearchSupplier;
	//    
	//    @FindBy(xpath="//span[text()='Supplier']/ancestor::table/parent::div/following::div/table//tr[1]")
	//    WebElement elmSearchSupplierRecord;
	//    
	//    @FindBy(xpath="//button[text()='OK'][contains(@id,'procurement_purchasing:0:MAt2:1:pt1:r1:0:AP1:AT5:supplier1Id::lovDialogId::ok')]")
	//    WebElement btnOkSupplier;

	//---------------------------------
	@FindBy(xpath="//a[contains(@title,'Search: Supplier')]//preceding-sibling::input[1]")
	WebElement edtSupplier;
	@FindBy(xpath="//label[text()='Supplier Site']//parent::td//following::input[1]")
	WebElement edtSupplierSite;
	@FindBy(xpath="//button[text()='O']//parent::td/child::button[1]")
	WebElement btnOkAddToDocBuilder;
	@FindBy(xpath="//button[text()='Create']")
	WebElement btnCreate;
	@FindBy(xpath="//div[contains(text(),'(Purchase Order)')]")
	WebElement elmInfoPurchaseOrder;
	@FindBy(xpath="//div[contains(text(),'(Purchase Order)')]//following::td[3]/child::table//preceding-sibling::td/child::button")
	WebElement btnOkPurchaseOrder;
	@FindBy(xpath="//label[contains(text(),'Description')]//parent::td//following-sibling::td/child::textarea")
	WebElement edtDescription;
	@FindBy(xpath="//a[text()='Distributions']")
	WebElement lnkDistributions;
	@FindBy(xpath="//span[text()='Check Funds']")
	WebElement btnCheckFundsPurchase;
	@FindBy(xpath="//div[text()='The document passed funds check.']")
	WebElement elmConfPurchaseOrder;
	@FindBy(xpath="//div[contains(text(),'The document passed')]//following::td[3]/child::table//preceding-sibling::td/child::button")
	WebElement btnOkConfPurchaseOrder;
	@FindBy(xpath="//span[text()='Manage Approvals']")
	WebElement btnManageApprovalsPurchaseOrder;
	@FindBy(xpath="//button[contains(text(),'ancel')]//span")
	WebElement btnCancelPurchaseOrder;
	@FindBy(xpath="//button[contains(text(),'Sub')]")
	WebElement btnSubmitPurchaseOrder;
	@FindBy(xpath="//div[contains(text(),'was submitted')]")
	WebElement elmConfPurchaseOrderNumber;
	@FindBy(xpath="//div[contains(text(),'was submitted')]//following::table[1]//tbody/child::tr//preceding-sibling::td//button[text()='OK']")
	WebElement btnOkPurchaseOrderNumber;


	@FindBy(xpath="//a[text()='Create Invoice']")
	WebElement lnkCreateInvoice;
	@FindBy(xpath="//label[text()='Business Unit']//parent::td//following::input[1]")
	WebElement lstBusinessUnit;
	@FindBy(xpath="//label[text()='Number']//parent::td//following::input[1]")
	WebElement edtNumber;
	@FindBy(xpath="//label[text()='Amount']//parent::td//following::input[1]")
	WebElement edtAmount;
	@FindBy(xpath="//span[text()='Distribution Set']/parent::div/following::div/table[@summary='Invoice Lines']//td[5]//td[1]//input[1]")
	WebElement edtDistributionSet;
	@FindBy(xpath="//span[text()='Save']")
	WebElement btnSaveInvoice;  //a[@title='Search: Identifying PO']//preceding-sibling::img//preceding-sibling::input
	@FindBy(xpath="//a[@title='Search: Identifying PO']//preceding-sibling::img//preceding-sibling::input")
	WebElement edtIdentifyingPO;

	@FindBy(xpath="//a[contains(text(),'Create Transaction')]")
	WebElement btnCreateTransaction;
	@FindBy(xpath="//label[text()='Transaction Source']//parent::td//following::input[1]")
	WebElement lstTransactionSource;
	@FindBy(xpath="//label[text()='Transaction Type']//parent::td//following::input[1]")
	WebElement lstTransactionType;
	@FindBy(xpath="//a[contains(@title,'Search: Bill-to Name')]")
	WebElement elmSearchBilltoName;
	@FindBy(xpath="//label[text()='Name']//parent::td//following::input[1]")
	WebElement edtName;
	@FindBy(xpath="//button[contains(text(),'Search')]")
	WebElement btnBillToSearch;
	@FindBy(xpath="//span[text()='Name']/ancestor::table/parent::div/following::div/table//tr[1]")
	WebElement elmBillToSearchRecord;
	@FindBy(xpath="//button[contains(text(),'OK')][position()=1]")
	WebElement btnBillToSearchOk;
	@FindBy(xpath="//span[contains(text(),'Description')]/parent::div/following::div/table[@summary='Invoice Lines']//td[3]//input[1]")
	WebElement edtCTDescription;
	@FindBy(xpath="//span[contains(text(),'Memo Line')]/parent::div/following::div/table[@summary='Invoice Lines']//td[4]//td[1]//input[1]")
	WebElement lstMemoLine;
	@FindBy(xpath="//span[contains(text(),'Quantity')]/parent::div/following::div/table[@summary='Invoice Lines']//td[4]//td[3]//input[1]")
	WebElement edtQuantity;

	//@FindBy(xpath="//span[contains(text(),'Unit Price')]/parent::div/following::div/table[@summary='Invoice Lines']//td[4]//td[4]//input[1]")       
	@FindBy(xpath="//input[contains(@id,'table1:0:sellingPrice::content')]")
	WebElement edtUnitPrice;


	@FindBy(xpath="//div[contains(text(),'has been saved')]")
	WebElement elmConfTransactionRefNum;
	@FindBy(xpath="//div[contains(text(),'has been saved')]//following::table[1]//tbody/child::tr//preceding-sibling::td//button[text()='OK']")
	WebElement btnOkTransaction;



	@FindBy(xpath="//a[contains(text(),'Create Payment')]")
	WebElement lnkCreatePayment;
	@FindBy(xpath="//label[text()='Business Unit']//parent::td//following::input[1]")
	WebElement lstBusinessUnitPayment;
	@FindBy(xpath="//label[text()='Supplier or Party']//parent::td//following::input[1]")
	WebElement lstSupplierOrParty;
	@FindBy(xpath="//label[text()='Supplier Site']//parent::td//following::input[1]")
	WebElement lstSupplierSite;
	@FindBy(xpath="//label[text()='Type']//parent::td//following::select")
	WebElement lstType;
	@FindBy(xpath="//label[text()='Description']//parent::td//following::textarea[1]")
	WebElement edtDescriptionPayment;
	@FindBy(xpath="//label[text()='Disbursement Bank Account']//parent::td//following::input[1]")
	WebElement lstDisbursementBankAccount;
	@FindBy(xpath="//label[text()='Payment Method']//parent::td//following::input[1]")
	WebElement lstPaymentMethod;
	@FindBy(xpath="//label[text()='Payment Process Profile']//parent::td//following::input[1]")
	WebElement lstPaymentProcessProfile;
	@FindBy(xpath="//label[text()='Payment Document']//parent::td//following::input[1]")
	WebElement lstPaymentDocument;
	@FindBy(xpath="//img[@title='Select and Add']")
	WebElement imgSelectAndAdd;
	@FindBy(xpath="//label[text()='Invoice Number']//parent::td//following::input[1]")
	WebElement edtInvoiceNumber;
	@FindBy(xpath="//button[contains(@id,'coVOId::search')]")
	WebElement btnSearchInvoice;
	@FindBy(xpath="//table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[2]")
	WebElement elmRecordInvoiveToPay;
	@FindBy(xpath="//button[contains(text(),'App')]")
	WebElement btnApplyInvoicetoPay;
	@FindBy(xpath="//button[contains(@id,'dialog1::ok')]")
	WebElement btnOkInvoicetoPay;
	@FindBy(xpath="//button[contains(text(),'ave and Close')]")
	WebElement btnSaveAndClose;
	@FindBy(xpath="//div[contains(text(),'has been created')]")
	WebElement elmConfPaymentNum;
	@FindBy(xpath="//button[text()='OK']")
	WebElement elmOkConfPayment;


	//R12 AND R13 Journals
	@FindBy(xpath="//a[(text()='Create Journal')]")
	WebElement lnkCreateJournal;
	@FindBy(xpath="//label[text()='Journal Batch']//following::input[1]")
	WebElement elmJournalBatch;
	@FindBy(xpath="//label[text()='Journal Batch']//following::textarea[1]")
	WebElement elmBatchDescription;
	@FindBy(xpath="//label[text()='Journal']//following::input[1]")
	WebElement elmJournal;
	@FindBy(xpath="//label[text()='Journal']//following::textarea[1]")
	WebElement elmDescription;
	@FindBy(xpath="//label[text()='Category']//following::input[1]")
	WebElement elmCategory;
	@FindBy(xpath="//label[text()='Account']//preceding-sibling::input")
	WebElement elmDebitAccount;
	@FindBy(xpath="//label[text()='Account']//parent::span//following::input[1]")
	WebElement elmDebit;
	@FindBy(xpath="//label[text()='Description']//preceding-sibling::input")
	WebElement elmJLineDescription;
	@FindBy(xpath="//label[text()='Account']//preceding-sibling::input")
	WebElement elmCreditAccount;
	@FindBy(xpath="//label[text()='Account']//parent::span//following::input[2]")
	WebElement elmCredit;
	@FindBy(xpath="//span[text()='Save']//parent::a")  //span[text()='Save']
	WebElement btnJSave;
	@FindBy(xpath="//span[text()='Post']//parent::a")
	WebElement btnJPost;
	@FindBy(xpath="//span[text()='Complete']//parent::a") //span[text()='Complete']
	WebElement btnJComplete;
	@FindBy(xpath="//span[text()='ancel']//parent::a")
	WebElement btnJCancel;
	@FindBy(xpath="//a[contains(@id,'home')]")
	WebElement lnkHome;
	@FindBy(xpath="//a[contains(text(),'General Accounting')]")
	WebElement lnkGeneralAccounting;
	@FindBy(xpath="//a[contains(text(),'Journals')]")
	WebElement lnkJournals;
	@FindBy(xpath="//a[text()='Manage Journals']")
	WebElement lnkManageJournals;
	@FindBy(xpath="//label[text()=' Journal Batch']//preceding-sibling::input")
	WebElement eleJournalNo;
	@FindBy(xpath="//button[text()='Search']")
	WebElement btnSearchMJ;
	@FindBy(xpath="//div[contains(text(),'journal requires approval')]")
	WebElement elmConfJournal;
	@FindBy(xpath="//span[(text()='K')]")
	WebElement btnOkJournal;
	@FindBy(xpath="//label[contains(text(),'Completion Status')]//following::td[text()='Complete']")
	WebElement eleCompletionStatus;
	@FindBy(xpath="//table[@summary='Search Results']")
	WebElement eleTable;

	//Receiving Recipt
	@FindBy(xpath="//label[contains(text(),'Items Due')]//preceding-sibling::select")
	WebElement edtItemsDue; 
	
	@FindBy(xpath="//label[contains(text(),'Purchase Order')]//preceding-sibling::input")
	WebElement edtPurOrder;  
	@FindBy(xpath="//label[contains(text(),'Requester')]//preceding-sibling::input")
	WebElement edtRequester; 
	@FindBy(xpath="//button[text()='Search']")
	WebElement btnPOSearch; 
	@FindBy(xpath="//button[contains(text(),'Receive')]")
	WebElement btnReceive; 
	@FindBy(xpath="//table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[2]")
	WebElement eleCod; 
	@FindBy(xpath="//button[text()='Show Receipt Quantity']")
	WebElement btnReceiptQuantity;
	@FindBy(xpath="//button[contains(text(),'Sub')]")
	WebElement btnPOSubmit;
	@FindBy(xpath="//span[text()='K']//parent::button")
	WebElement btnOk;
	@FindBy(xpath="//span[contains(text(),'You created the')]")
	WebElement eleGetConf;  

	//pur order Invc
	@FindBy(xpath="//label[text()='Amount']//preceding-sibling::input//parent::span//parent::span//parent::td[@title='Amount']/child::span//child::input")
	WebElement edtAmountLine;
	@FindBy(xpath="//img[contains(@alt,'Go')]")
	WebElement btnGo; 
	@FindBy(xpath="//button[contains(text(),'App')]")
	WebElement btnApply;
	@FindBy(xpath="//span[text()='K']//parent::button")
	WebElement btnOK; 
	@FindBy(xpath="//div/span/span/span/input[@type='checkbox']")
	WebElement chkMatch;
	@FindBy(xpath="//button[text()='Cancel']//preceding-sibling::button[text()='OK'][position()=1]")
	WebElement btnOKPOP;
	@FindBy(xpath="//a[text()='Invoice Actions']")
	WebElement lstInvoiceActions;
	@FindBy(xpath="//td[text()='Validate']")   
	WebElement lstValidate;
	//span[contains(text(),'ave and Close')]
	@FindBy(xpath="//span[contains(text(),'ave and Close')]")   
	WebElement btnSaveAndCloseInvc;



	public Cloud(Utility util) throws Exception{
		//this.driver = driver;    	
		PageFactory.initElements(util.ng_returnDriver(), this);
	}       
	/*----------------------------------------------------------------------------
    Function Name    	: createItemBasedRequisition
    Description     	: This function used to Create item based requisition  
    Author				:     
    Date of creation	:
	Date of modification:
    ----------------------------------------------------------------------------*/ 
	public void createItemBasedRequisition() throws Exception {    	
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();
			Utility.ng_clickWebElement(lnkRequisitionLineEntry,"Requisition Line Entry","RequisitionLineEntryClick");
			//Utility.waitForPageToLoad();
			Utility.ng_verifyPage("Requisition", "RequisitionCheck");
			                                                                                                                                //Utility.ng_clickWebElement(elmSearchIcon, "Search Icon", "SearchIconClick");//Utility.ng_selectWindow("WindowSelect");//Utility.waitForPageToLoad(); //Utility.ng_enterText(edtItem, "Item", "ItemSet");                            
			Global.gstrReadfromTestData = false;
			Utility.ng_typeAndTab(edtItemNew, "Item", "14493");
			Global.gstrReadfromTestData = true;

		                                                                                                                                   	//			Utility.ng_clickWebElement(btnSearch, "Search", "SearchClick");			
			                                                                                                                               //			String strItenVal = (String) Global.objData.get("ItemSet");
			                                                                                                                              //			if(!strItenVal.contains("SKIP")) {
			                                                                                                                             //				//Utility.ng_waitImplicitly(3);
			                                                                                                                            //				Utility.clickItem(strItenVal);
			                                                                                                                           //			}
			                                                                                                                          //Utility.ng_clickWebElement(btnSearchOk, "Search Ok", "SearchOkClick");			
			                                                                                                                         //Utility.ng_SelectList(edtUOM, "UOM","UOMSet");
			                                                                                                                        //Utility.ng_typeAndTab(edtUOM, "UOM","UOMSet");	
			Utility.ng_waitImplicitly(1);                                                                                                                       //Utility.ng_SelectList(edtCurrency, "Currency","CurrencySet");
			Utility.ng_typeAndTab(edtPrice, "Price", "PriceSet");    	                                                          
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickSimply(btnAddtoRequisition, "Add to Requisition", "AddtoRequisitionClick");
			Utility.waitForPageToLoad();
			Utility.ng_waitImplicitly(1);	
			Utility.ng_clickUsingActions(btnEditAndSubmit, "Edit And Submit", "EditAndSubmitClick");
			Utility.ng_waitImplicitly(2);
			Utility.waitForPageToLoad();
			Utility.ng_scrollIntoViewElement(btnSave, "Save");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(btnSave, "Save", "SaveClick");
			Utility.ng_waitImplicitly(2);
			Utility.ng_clickWebElement(btnCheckFunds, "CheckFunds", "CheckFundsClick");
			Utility.ng_waitImplicitly(2);
			Utility.ng_clickSimply(btnConfirmationOk, "Confirmation Ok", "ConfirmationOkClick");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(btnManageApprovals, "Manage Approvals", "ManageApprovalsClick"); //ng_clickWebElement
			Utility.ng_clickUsingActions(btnSubmit, "Submit", "SubmitClick");	//ng_clickUsingActions
			Utility.ng_getElementText(elmConfRequisitionNumber, "ConfRequisition Number", "ConfRequisitionNumberGet");
			String s=elmConfRequisitionNumber.getText();
			Utility.ng_clickSimply(btnConfOk, "Confirmation Ok", "ConfOk"); 	
			sStrReqNo=Utility.ng_getDigitsFromString(s);
		    System.out.println(sStrReqNo);
		} catch (Exception e) {
			Global.objErr = "11";
		}       
	}

	/*----------------------------------------------------------------------------
    Function Name    	: createPurchaseOrder
    Description     	: Create Purchase Order from Requisition 
    Author				:   
    Date of creation	:
	Date of modification: 
    ----------------------------------------------------------------------------*/ 
	public void createPurchaseOrder() throws Exception {    	
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(lnkProcessRequisitions, "Process Requisitions", "ProcessRequisitionsClick");
			Utility.waitForPageToLoad();
			Utility.ng_verifyPage("Process Requisitions", "ProcessRequisitionsCheck");
			
			if(sStrReqNo!=null)
			{
			Global.gstrReadfromTestData = false;
			Utility.ng_enterText(edtRequisition, "Requisition", sStrReqNo);
			System.out.println(sStrReqNo);
			Global.gstrReadfromTestData = true;
			}
			else
			{
				Utility.ng_enterText(edtRequisition, "ReqNoTakenFromExcel", "RequisitionSet");	
			}
			
			Utility.clearTextAndTab(edtBuyer, "Buyer", "BuyerSet");
			Utility.ng_clickSimply(btnSearchRequisition, "Search Requisition", "SearchRequisitionClick");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickSimply(elmSearchRecordOne, "Search Record One", "SearchRecordOneClick");
			Utility.ng_clickWebElement(btnAddToDocumentBuilder, "Add To Document Builder", "AddToDocumentBuilderClick");
			Utility.waitForPageToLoad();
			Utility.ng_typeAndTab(edtSupplier, "Supplier", "SupplierSet");
			Utility.ng_TypeAndEnter(edtSupplierSite, "Supplier Site", "SupplierSiteSelect");  //ng_SelectList
			//Utility.waitForPageToLoad();
			Utility.ng_clickSimply(btnOkAddToDocBuilder, "Ok AddToDocBuilder", "OkAddToDocBuilderClick");
			Utility.ng_clickWebElement(btnCreate, "Create", "CreateClick");
			Utility.waitForPageToLoad();
			Utility.ng_getElementText(elmInfoPurchaseOrder, "Info Purchase Order", "InfoPurchaseOrderGet");
			String s=elmInfoPurchaseOrder.getText();
			Utility.ng_clickSimply(btnOkPurchaseOrder, "Ok Purchase Order", "OkPurchaseOrderClick");
			sStrPoNo=Utility.ng_getDigitsFromString(s);
			System.out.println(sStrPoNo);
			Utility.ng_enterText(edtDescription, "Description", "DescriptionSet");
			Utility.ng_clickWebElement(lnkDistributions, "Distributions", "DistributionsClick");
			Utility.ng_clickWebElement(btnCheckFundsPurchase, "Check Funds Purchase", "CheckFundsPurchaseClick");
			Utility.ng_getElementText(elmConfPurchaseOrder, "Conf Purchase Order", "ConfPurchaseOrderGet");
			Utility.ng_clickSimply(btnOkConfPurchaseOrder, "Ok Conf Purchase Order", "OkConfPurchaseOrderClick");
			Utility.ng_clickWebElement(btnManageApprovalsPurchaseOrder, "Manage Approvals Purchase Order", "ManageApprovalsPurchaseOrderClick");
			Utility.ng_clickWebElement(btnCancelPurchaseOrder, "Cancel Purchase Order", "CancelPurchaseOrderClick");
			Utility.ng_clickWebElement(btnSubmitPurchaseOrder, "Submit Purchase Order", "SubmitPurchaseOrderClick");
			Utility.ng_getElementText(elmConfPurchaseOrderNumber, "Conf Purchase Order Number", "ConfPurchaseOrderNumberGet");
			Utility.ng_clickSimply(btnOkPurchaseOrderNumber, "Ok PurchaseOrderNumber", "OkPurchaseOrderNumberClick");

		} catch (Exception e) {
			Global.objErr = "11";
		}      
	}

	/*----------------------------------------------------------------------------
    Function Name    	: createPaymentInvioce
    Description     	: Create Payment Invioce  
    Author				: 
    Date of creation	:
	Date of modification: 
    ----------------------------------------------------------------------------*/ 
	public void createPaymentInvoice() throws Exception {    	
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();  
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);
			Utility.waitForPageToLoad();
			Utility.ng_waitImplicitly(3);
			Utility.ng_clickSimply(imgTask, "Task", "TaskClick");
			Utility.ng_clickWebElement(lnkCreateInvoice, "Create Invoice", "CreateInvoiceClick");
			Utility.waitForPageToLoad();
			Utility.ng_verifyPage("Create Invoice", "CreateInvoiceCheck");
			Utility.ng_SelectList(lstBusinessUnit, "Business Unit", "BusinessUnitSelect");
			Utility.ng_waitImplicitly(2);
			Utility.ng_typeAndTab(edtSupplier, "Supplier", "SupplierSet");
			Utility.ng_typeAndTab(edtSupplierSite, "Supplier Site", "SupplierSiteSelect");
			Global.gstrReadfromTestData = false;
			String strVal = Utility.ng_RandomAlphaNum("I",5);
			Utility.ng_enterText(edtNumber, "Number", strVal);
			Global.gstrReadfromTestData = true;
			Utility.ng_enterText(edtAmount, "Amount", "AmountSet");    	    
			Utility.ng_SelectList(edtDistributionSet, "Distribution Set", "DistributionSet");
			Utility.ng_clickWebElement(btnSaveInvoice, "Save Invoice", "SaveInvoiceClick");
			
		} catch (Exception e) {
			Global.objErr = "11";			
		}      
	}

	/*----------------------------------------------------------------------------
    Function Name    	: createInvioce
    Description     	: Create Invioce    
    Author				:
    Date of creation	:
	Date of modification:
    ----------------------------------------------------------------------------*/ 
	public void createInvioce() throws Exception {    	
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();
			Utility.ng_waitImplicitly(3);
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");
			Utility.ng_clickWebElement(btnCreateTransaction, "Create Transaction", "CreateTransactionClick");
			Utility.waitForPageToLoad();
			Utility.ng_verifyPage("Create Transaction", "CreateTransactionCheck");
			Utility.ng_SelectList(lstTransactionSource, "Transaction Source", "TransactionSourceSelect");
			Utility.ng_SelectList(lstTransactionType, "Transaction Type", "TransactionTypeSelect");
			Utility.ng_clickWebElement(elmSearchBilltoName, "Search Billto Name", "SearchBilltoNameClick");
			Utility.ng_enterText(edtName, "Name", "NameSet");
			Utility.ng_clickWebElement(btnBillToSearch, "BillToSearch", "BillToSearchClick");
			Utility.ng_clickSimply(elmBillToSearchRecord, "Bill To Search Record", "BillToSearchRecordClick");
			Utility.ng_clickSimply(btnBillToSearchOk, "BillToSearch Ok", "BillToSearchOkClick");
			//Utility.ng_SelectListtable(lstMemoLine, "Memo Line", "MenoLineSet");
			Utility.ng_SelectList(lstMemoLine, "Memo Line", "MenoLineSet");
			Utility.ng_enterText(edtCTDescription, "Description", "DescriptionSet");
			Utility.ng_typeAndTab(edtQuantity, "Quantity", "QuantitySet");
			Utility.ng_waitImplicitly(5);
			Utility.ng_enterText(edtUnitPrice, "Unit Price", "SellingPriceSet");
			Utility.ng_clickUsingActions(btnSavetriangle, "SaveTriangle", "SaveClick");
			Utility.ng_clickUsingActions(btnSavenClose, "Save and Close", "SaveClick");
			Utility.ng_getElementText(elmConfTransactionRefNum, "Conf Transaction Ref Num", "ConfTransactionRefNumGet");
			Utility.ng_clickSimply(btnOkTransaction, "Ok Confirmation", "OkConfirmationClick");

		} catch (Exception e) {
			Global.objErr = "11";
		}       
	}

	

	/*----------------------------------------------------------------------------
    Function Name    	: createPaymentInvioce
    Description     	: Create Payment Invioce  
    Author				: 
    Date of creation	:
	Date of modification: 
    ----------------------------------------------------------------------------*/ 
	public void createPrePaymentInvoice() throws Exception {    	
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();
			Utility.ng_waitImplicitly(3);
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");
			Utility.ng_clickWebElement(lnkCreateInvoice, "Create Invoice", "CreateInvoiceClick");
			Utility.waitForPageToLoad();
			Utility.ng_verifyPage("Create Invoice", "CreateInvoiceCheck");
			Utility.ng_SelectList(lstBusinessUnit, "Business Unit", "BusinessUnitSelect");
			Utility.ng_typeAndTab(edtSupplier, "Supplier", "SupplierSet");
			Utility.ng_typeAndTab(edtSupplierSite, "Supplier Site", "SupplierSiteSelect");
			Utility.ng_enterText(edtNumber, "Number", "NumberSet");
			Utility.ng_enterText(edtAmount, "Amount", "AmountSet");
			Utility.ng_SelectList(edtDistributionSet, "Distribution Set", "DistributionSet");
			Utility.ng_clickWebElement(btnSaveInvoice, "Save Invoice", "SaveInvoiceClick");
			
		} catch (Exception e) {
			Global.objErr = "11";			
		}      
	}

	/*----------------------------------------------------------------------------
    Function Name    	: createAndPostJournal
    Description     	: create And Post Journal R12   
    ----------------------------------------------------------------------------*/ 
	public void createAndPostJournal()
	{
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();	
			//Global.gstrReadfromTestData = false;
			Utility.ng_waitImplicitly(3);
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");			
			Utility.waitForPageToLoad();
			Utility.ng_clickWebElement(lnkCreateJournal,"CreateJournal","CreateJournalClick");
			Global.gstrReadfromTestData = false;
			String JBN=Utility.ng_RandomAlphaNum("JBN",4);;
			Utility.ng_enterText(elmJournalBatch, "JournalBatch", JBN);
			Global.gstrReadfromTestData = true;
			Utility.ng_enterText(elmBatchDescription, "Description", "BatchDescriptionSet");			
			Utility.ng_typeAndTab(elmJournal, "Journal", "JournalSet");			
			Utility.ng_typeAndTab(elmDescription, "Description", "DescriptionSet"); 		
			Utility.ng_scrollIntoViewElement(elmCategory, "Category");
			Utility.ng_typeAndTab(elmCategory, "Category", "CategorySet");			
			Utility.ng_typeAndTab(elmDebitAccount, "Account", "DebitAccountSet");			
			Utility.ng_typeAndTab(elmDebit, "Debit", "DebitSet");			
			Utility.ng_sendTab(elmJLineDescription, "Tab");
			Utility.ng_enterText(elmCreditAccount, "CreditAccount", "CreditAccountSet"); 
			Utility.ng_typeAndTab(elmCredit, "Credit", "CreditSet");
			//Utility.ng_sendTab(elmJLineDescription, "Tab");
			//Utility.ng_scrollUpWindow();
			Utility.ng_clickWebElement(btnJSave,"Save","SaveClick");
			Utility.ng_clickWebElement(btnJComplete,"Complete","CompleteClick");
			Utility.ng_getElementText(eleCompletionStatus, "CompletionStatus", "ConfCompletionStatus");
			Utility.ng_clickSimply(btnJPost, "Post", "PostClick");
			//Utility.ng_getElementText(eleCompletionStatus, "CompletionStatus", "ConfCompletionStatus");
			Utility.ng_getElementText(elmConfJournal, "Conf Journal", "ConfJournalGet");
			Utility.ng_clickSimply(btnOkJournal, "Ok Conf Journal", "OkConfJournalClick");
			Utility.ng_clickWebElement(btnJCancel, "Cancel", "OkConfJournalClick");
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");
			Utility.ng_clickWebElement(lnkManageJournals, "Home", "HomeClick");  
			Utility.waitForPageToLoad();
			Global.gstrReadfromTestData = false;
			Utility.ng_enterText(eleJournalNo, "Home",JBN);
			Global.gstrReadfromTestData = true;
			Utility.ng_clickWebElement(btnSearchMJ, "Search", "HomeClick");
			Utility.waitForPageToLoad();
			WebElement eleSearchTable=eleTable.findElement(By.linkText(JBN));
			String JBNCheck=Utility.ng_getElementText(eleSearchTable, "Confirms Journal Batch No In Search Table", "ConfJournalGet");
			System.out.println("No is  Present"+JBNCheck);
			
		} catch (Exception e) {
			Global.objErr = "11";			
		}   
	}

	/*----------------------------------------------------------------------------
    Function Name    	: createReceivingReceipt
    Description     	: Create Receiving Receipt
    ----------------------------------------------------------------------------*/ 
	public void createReceivingReceipt()
	{
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();	
			//Global.gstrReadfromTestData = false;
			//Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");	edtItemsDue  ng_DropDown
			//edtRequester.clear();
			Utility.clearTextField(edtRequester);
			Utility.ng_waitImplicitly(1);
			Utility.waitForPageToLoad();
			//Utility.ng_DropDownByIndex(edtItemsDue, "Any Time", "ItemsIndexDueSet");
			Select se=new Select(edtItemsDue);
			se.selectByIndex(8);
			Utility.ng_waitImplicitly(2);
			//string No=3021953;
			if(sStrPoNo!=null)
			{
				Global.gstrReadfromTestData = false;
				Utility.ng_enterText(edtPurOrder, "PurchaseOrder", sStrPoNo);
				 Global.gstrReadfromTestData = true;
			}
			else
			{
				Utility.ng_enterText(edtPurOrder, "PoNoTakenFromExcel", "PurchaseOrderSet");
			}	
			Utility.ng_clickWebElement(btnPOSearch, "Search", "SearchClick");
			Utility.waitForPageToLoad();
			Utility.ng_waitImplicitly(2);
			Utility.ng_clickUsingActions(eleCod, "CodBu", "CodBuClick");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(btnReceive, "Receive", "ReceiveClick");
			Utility.waitForPageToLoad();
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(btnReceiptQuantity, "ReceiptQuantity", "ReceiptQuantityClick");
			Utility.ng_clickWebElement(btnPOSubmit, "POSubmit", "POSubmitClick");  //Any time  btnOk,eleGetConf
			Utility.ng_getElementText(eleGetConf, "Conf Confirmation", "ConfirmationGet");
			Utility.ng_clickSimply(btnOk, "OK", "OKClick");

		} catch (Exception e) {
			Global.objErr = "11";			
		}   
	}//edtIdentifyingPO

	/*----------------------------------------------------------------------------
  Function Name    	: createPurOrderMatchedInvoice
  Description     	: Create Purchase Order Matched Invoice
  ----------------------------------------------------------------------------*/ 
	public void createPurOrderMatchedInvoice()
	{
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();  
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");
			Utility.ng_clickWebElement(lnkCreateInvoice, "Create Invoice", "CreateInvoiceClick");
			Utility.ng_verifyPage("Create Invoice", "CreateInvoiceCheck");
			if(sStrPoNo!=null)
			{
			Global.gstrReadfromTestData = false;
			Utility.ng_typeAndTab(edtIdentifyingPO, "IdentifyingPO", sStrPoNo);
			Global.gstrReadfromTestData = true;
			}
			else
			{
				Utility.ng_typeAndTab(edtIdentifyingPO, "PoNoTakenFromExcel", "IdentifyingPOSet");
			}
			Utility.ng_waitImplicitly(2);
			Utility.waitForPageToLoad();
			
			//Utility.ng_typeAndTab(edtIdentifyingPO, "IdentifyingPO", "IdentifyingPOSet");
			//Utility.waitForPageToLoad();
			//Utility.ng_SelectList(lstBusinessUnit, "Business Unit", "BusinessUnitSelect");
			//Utility.ng_typeAndTab(edtSupplier, "Supplier", "SupplierSet");
			//Utility.ng_typeAndTab(edtSupplierSite, "Supplier Site", "SupplierSiteSelect");
			//Global.gstrReadfromTestData = false;
			sStrInvcNo ="InvNo"+Utility.ng_RandomNum();
			Global.gstrReadfromTestData = false;
			Utility.ng_typeAndTab(edtNumber, "Number", sStrInvcNo);
			Global.gstrReadfromTestData = true;
			Utility.ng_typeAndTab(edtAmount, "Amount", "AmountSet"); 
			Utility.ng_clickWebElement(btnGo, "GoButton", "GoButtonClick");  //btnApply,btnOK, chkMatch
			Utility.ng_waitImplicitly(2);
			Utility.ng_clickWebElement(chkMatch, "SelectAllMatch", "MatchClick");
			                                                                      //Utility.ng_clickWebElement(btnApply, "Apply", "ApplyClick");
			Utility.ng_clickSimply(btnOK, "OK", "OkClick");
			                                                                      //Utility.ng_clickWebElement(btnOK, "OKPopUp", "OkClick");
			                                                                      //Utility.ng_clickWebElement(btnApply, "Apply", "ApplyClick");
			                                                                       //Utility.ng_clickWebElement(btnOK, "OK", "OkClick");
			Utility.ng_clickWebElement(btnSaveInvoice, "Save Invoice", "SaveInvoiceClick");
			Utility.ng_clickWebElement(lstInvoiceActions, "InvoiceActions", "InvoiceActionsClick");
			Utility.ng_clickElementUsingJS(lstValidate, "Validate", "ValidateClick");
			//Utility.ng_SelectList(edtDistributionSet, "Distribution Set", "DistributionSet");
			//Utility.ng_enterText(edtAmountLine, "Amount", "AmountSet");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(btnSaveAndCloseInvc, "SaveAndClose", "SaveAndCloseInvcClick");

		} catch (Exception e) {
			Global.objErr = "11";			
		}   
	}//edtIdentifyingPO
	/*----------------------------------------------------------------------------
    Function Name    	: createPaymentQuickCheck
    Description     	: createPaymentQC  
    Author				:
    Date of creation	:
	Date of modification:
    ----------------------------------------------------------------------------*/ 
	public void createPaymentQuickCheck() throws Exception {    	
		if(Global.objErr == "11"){
			return;
		}    
		try {
			TestData td = new TestData ();
			Global.objData = (HashMap) td.readTestData (Global.gTCID, Global.gstrClassName, Global.gstrMethodName);	        	    
			Global.test.log(LogStatus.INFO,"Class and Method : "+ Global.gstrClassName +" . "+Global.gstrMethodName);

			Utility.waitForPageToLoad();
			Utility.ng_clickWebElement(imgTask, "Task", "TaskClick");
			Utility.ng_clickWebElement(lnkCreatePayment, "Create Payment", "CreatePaymentClick");
			Utility.waitForPageToLoad();
			Utility.ng_verifyPage("Create Payment", "CreatePaymentCheck");
			Utility.ng_typeAndTab(lstBusinessUnitPayment, "Business Unit", "BusinessUnitPaymentSelect");  //ng_SelectList
			Utility.ng_typeAndTab(lstSupplierOrParty, "Supplier or Party", "SupplierOrPartySelect");
			Utility.ng_waitImplicitly(1);
			Utility.ng_TypeAndEnter(lstSupplierSite, "Supplier Site", "SupplierSiteSelect");
			Utility.ng_waitImplicitly(1);
			Utility.ng_sendTab(lstSupplierSite, "Supplier Site");
			//Utility.ng_DropDown(lstType, "Type", "TypeSelect");
			//Select se=new Select(edtItemsDue);
			//se.selectByIndex(1);
			//Utility.ng_enterText(edtDescriptionPayment, "Description Payment", "DescriptionPaymentSet");
			Utility.ng_waitImplicitly(1);
			Utility.ng_typeAndTab(lstDisbursementBankAccount, "Disbursement Bank Account", "DisbursementBankAccountSelect");
			Utility.ng_waitImplicitly(1);
			Utility.ng_typeAndTab(lstPaymentMethod, "Payment Method", "PaymentMethodSelect");
			Utility.ng_waitImplicitly(1);
			Utility.ng_TypeAndEnter(lstPaymentProcessProfile, "Payment Process Profile", "PaymentProcessProfileSelect");
			Utility.ng_waitImplicitly(3);
			Utility.ng_typeAndTab(lstPaymentDocument, "Payment Document", "PaymentDocumentSelect");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(imgSelectAndAdd, "Select AndAdd", "SelectAndAddClick");
			Utility.ng_waitImplicitly(1);
			/*if(sStrInvcNo!=null)
			{
			Global.gstrReadfromTestData = false;
			Utility.ng_enterText(edtInvoiceNumber, "InvoiceNumberTakenFromExcel", sStrInvcNo);
			Utility.ng_clickSimply(btnSearchInvoice, "Search Invoice", "SearchInvoiceClick");
			Global.gstrReadfromTestData = true;
			}
			else
			{
				Utility.ng_enterText(edtInvoiceNumber, "InvoiceNumberTakenFromExcel", sStrInvcNo);	
				Utility.ng_clickSimply(btnSearchInvoice, "Search Invoice", "SearchInvoiceClick");
			}*/
			Utility.ng_waitImplicitly(3);
			Utility.ng_clickWebElement(elmRecordInvoiveToPay, "Invoive To Pay Record", "InvoiveToPayRecordClick");
			//Utility.ng_clickWebElement(btnApplyInvoicetoPay, "Apply Invoice to Pay", "ApplyInvoicetoPayClick");
			Utility.ng_clickSimply(btnOkInvoicetoPay, "Ok Invoice to Pay", "OkInvoicetoPayClick");
			Utility.ng_waitImplicitly(1);
			Utility.ng_clickWebElement(btnSaveAndClose, "Save And Close", "SaveAndCloseClick");
			Utility.ng_getElementText(elmConfPaymentNum, "Conf Payment Number", "ConfPaymentNumGet");
			Utility.ng_clickSimply(elmOkConfPayment, "Ok Conf Payment", "OkConfPayment");

		} catch (Exception e) {
			Global.objErr = "11";
		}     
	}
	
	
	
}


