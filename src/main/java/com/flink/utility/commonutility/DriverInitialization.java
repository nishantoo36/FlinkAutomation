package com.flink.utility.commonutility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DriverInitialization {
    public static WebDriver driver = null;
    public static String browserDetails= "";
    public DriverInitialization() {
        PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 5) ,this);
    }

    public WebDriver getDriver(){
        if(browserDetails.equalsIgnoreCase( "chrome")) {
            if (getSessionId() == null || driver == null) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }else if(browserDetails.equalsIgnoreCase("safari")){
            if (getSessionId() == null || driver == null) {
                driver = new SafariDriver();
            }
        }
        return driver;
    }

    public SessionId getSessionId() {
        try {
            return ((RemoteWebDriver) driver).getSessionId();
        }catch (NullPointerException e){
            System.out.println("Create new driver");
            return null;
        }
    }}
