package com.flink.utility.commonutility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class SeleniumUtility extends DriverInitialization {
    public SeleniumUtility() {
        super();
    }

    public void enterText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }

    public void enterTexByJS(String value, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[1].value = arguments[0]; ", value, element);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void click(WebElement element) {
        element.click();
    }

    public void openUrl(String url) {
        if(getSessionId()==null){
            getDriver();
        }
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void switchToiFrameByElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void closeBrowser() {
        driver.quit();
    }


    public String getTitle() {
        return driver.getTitle();
    }

    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    protected boolean isElementAvailable(WebElement element, int timeoutVal) {
        boolean ret = false;
        try {
            fluentWait(timeoutVal).until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed()) {
                ret = true;
                return ret;
            }
        } catch (
                TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (
                NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
        return ret;
    }

    protected boolean isTitle(String title ,int timeoutVal) {
        try {
            fluentWait(timeoutVal).until(ExpectedConditions.titleIs(title));
            return true;
        } catch (
                TimeoutException e) {
            e.printStackTrace();
            return false;
        }

    }



    protected Wait fluentWait(int timeoutVal) {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeoutVal))
                .pollingEvery(Duration.ofMillis(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait;
    }
}
