package com.flink.utility.commonutility;

import com.flink.utility.reportsutility.ExtentTestManager;
import org.testng.annotations.*;
import java.lang.reflect.Method;

public class CommonPreAndPostConditions extends SeleniumUtility {
    public static String url = null;
    public static String actualData = "FLICK FLOW";

    public CommonPreAndPostConditions() {
        super();
    }

    @BeforeMethod
    @Parameters("browser")
    public void startTest(Method method,String browser) {
        ExtentTestManager.startTest(method.getName(), "Test started for " + actualData+" on browser "+browser);
    }

    @BeforeTest
    @Parameters("browser")
    public void openTheUrl(String browser) {
        DriverInitialization.browserDetails=browser;
        openUrl(url);
    }

    @AfterMethod
    public void closeBrowser() {
        Logging.log("Closing the browser");
        super.closeBrowser();
    }


}
