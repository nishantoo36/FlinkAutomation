package com.flink.utility.commonutility;
import com.flink.utility.reportsutility.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Reporter;


public class Logging {
    public static void log(String args) {
        ExtentTestManager.getTest().log(LogStatus.INFO, args);
        Reporter.log(args + "\n",true);
    }
}
