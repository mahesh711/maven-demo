package lib;

import java.io.File;
import java.util.HashMap;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Global {
    public static String gTCName;
    public static String gTCID;
    public static String gTCDescription;
    public static String bResult;
    public static String objErr = "0";
    public static String objSoftAssert ="true";  // true for 
    public static String filePath;
    public static String gstrComponentName;
    public static ExtentReports report;
    public static ExtentTest test;
    public static String gstrControlFilesDir = System.getProperty ("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"TestArtifacts"+File.separator;
    public static String gstrBatchFilesDir = System.getProperty ("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"TestArtifacts"+File.separator;
    public static String gstrTestDataDir = System.getProperty ("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"TestArtifacts"+File.separator;
    public static String gstrTestResultLogDir = System.getProperty ("user.dir") + File.separator+"Reports"+File.separator+"TestResultLog";
    public static String gstrScreenshotsDir = System.getProperty ("user.dir") + File.separator+"Reports"+File.separator+"Screenshots";
    public static String gstrExtentConfigDir = System.getProperty ("user.dir");
    public static String gstrChromeDriverDir = System.getProperty ("user.dir") + File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"Selenium_Jars"+File.separator+"chromedriver_win32"+File.separator+"chromedriver.exe";
    public static HashMap objData;
    public static String gstrTimesTamp;
    public static String gstrClassName;
    public static String gstrMethodName;    
    public static Boolean gstrHighlighter = false;
    public static Boolean gstrScreenShotFlag = false;
    
    //TestData controller  - from TestData excel file on variable      
    public static Boolean gstrReadfromTestData = true;  //true : TestData,   false : Variable
    
    //Email Configuraion
    public static Boolean gstrSendEmail = false;			//true : send email, false : won't send
	public static String gstrEmailMode = "outlook";		//outlook , gmail
     
   
}
