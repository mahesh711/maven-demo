package RunManager;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import com.relevantcodes.extentreports.ExtentReports;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import lib.Global;
import lib.InitDriver;
import lib.Utility;
import lib.SendReportInEmail;
import lib.InitScript;
/*----------------------------------------------------------------------------
Function Name    	: HybridExecuteTest - Run Manager
Description     	: This will control the execution.
Input Parameters 	: None
Return Value    	: None
<<<<<<< HEAD
Author		        : 
=======
Author		        : Sharad Mali
>>>>>>> refs/remotes/origin/master
Date of creation	: 
Date of modification:	
----------------------------------------------------------------------------*/
public class HybridExecuteTest  {   
    @Test
    @Parameters({ "browser" })
    public void runManager(String browser) throws Exception {    	    	 
        try {        	
        	//Create HTML file
        	Global.gstrTimesTamp = "_"+ Utility.getCurrentDatenTime("dd-MM-yy")+"_"+ Utility.getCurrentDatenTime("H-mm-ss a");
        	Global.filePath = Global.gstrTestResultLogDir + File.separator + "Batch" + Global.gstrTimesTamp  +".html";
            File myFile = new File(Global.filePath);
            if (! myFile.exists() ) {
                myFile.createNewFile();
                Global.report = new ExtentReports(Global.filePath, true);
                Global.report.addSystemInfo("Browser", "Chrome Version 64.0.3282.186"); //MAHESH
                Global.report.addSystemInfo("URL", "https://ebkk-dev1.fs.us8.oraclecloud.com");
                Global.report.loadConfig(new File(Global.gstrExtentConfigDir + File.separator + "extent-config.xml"));
            }            
            //Read Groups
            String strGroupQuery = "Select * from Groups where Run='Y'";
            List<String> arrGroupList = InitScript.readGroupData(Global.gstrControlFilesDir + "COD R12 Cloud ERP Master Driver File.xlsx",strGroupQuery);            
            for(String strGroupName : arrGroupList) {            	
            	String strGroupSheetQuery = "Select * from "+ strGroupName +" where Run='Y'";            
                List<String> arrTCList = InitScript.readGroupData(Global.gstrControlFilesDir + "COD R12 Cloud ERP Master Driver File.xlsx",strGroupSheetQuery);                         	
                for(String strTCID : arrTCList) {                 	
                	strGroupSheetQuery = "Select * from "+ strGroupName +" where TestID='"+strTCID+"'";            
                	String strBatchName = InitScript.readGroupSheetData(Global.gstrControlFilesDir + "COD R12 Cloud ERP Master Driver File.xlsx",strGroupSheetQuery);
                	//Driver initialization
                	Utility util = new Utility(browser);
                	//Read Batch
                	String strBatchQuery = "Select * from BatchSheet where BatchTestFile='"+ strBatchName +"'";
                	List<String> arrBatchList = InitScript.readBatchData(Global.gstrBatchFilesDir + "COD R12 Cloud ERP BatchSheet.xlsx",strBatchQuery);   
                	for(String strComponentName : arrBatchList) {
                		Global.gstrComponentName = strComponentName;
                		if (Global.gstrComponentName != "") {
							Global.gstrClassName = Global.gstrComponentName.split("\\.")[0];
							Global.gstrMethodName = Global.gstrComponentName.split("\\.")[1];
							Class<?> cls = Class.forName("pages." + Global.gstrClassName);
							Object clsInstance = (Object) cls.getConstructor(Utility.class).newInstance(util);
							Method method = cls.getMethod(Global.gstrMethodName);							
							method.invoke(clsInstance);		//Call components														
						}                		
                	}
					if(Global.objErr == "11") {												
						Utility.logoutFinally();																	
						Global.report.endTest(Global.test);
				        Global.report.flush();						
//				        Path source = Paths.get(Global.filePath);
//						Files.move(source, source.resolveSibling(Global.gTCName + Global.gstrTimesTamp +"_Fail.html"));												
				        Global.objErr = "0";
						Global.bResult = "True";
						InitDriver.driver.quit();
						
					} 
					else {	
						
						Global.report.endTest(Global.test);
				        Global.report.flush();
				        Thread.sleep(500);
				        InitDriver.driver.quit();
				        
				        //Utility.quitdriver();				        
//				        Path source = Paths.get(Global.filePath);
//						Files.move(source, source.resolveSibling(Global.gTCName + Global.gstrTimesTamp +"_Pass.html"));
					}											
				}																			
			}
				
			 
		} catch (Exception e) {
			String strDesc = "There is an issue in Runmanager. Please check... " + e.getMessage();
			Utility.writeHTMLResultLog(strDesc, "fail");
			Utility.takeScreenShotAndLog("fail");
			Global.bResult = "False";
			Global.objErr = "11";
		} finally {
	        if (Global.gstrSendEmail == true) {
	        	if ((Global.gstrEmailMode).equalsIgnoreCase("gmail")){
	        		SendReportInEmail.sendGmailReport();
	        	}else {
	        		SendReportInEmail.sendOutlookReport();
	        	}	        	
	        }
	        
//	        Global.report.endTest(Global.test);
//	        Global.report.flush();
		}        
    }

}
