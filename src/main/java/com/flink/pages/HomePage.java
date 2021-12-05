package com.flink.pages;

import com.flink.constants.DynamicConstant;
import com.flink.constants.PageTitles;
import com.flink.constants.RegexConstant;
import com.flink.utility.commonutility.Logging;
import com.flink.utility.commonutility.SeleniumUtility;
import com.flink.utility.commonutility.StringUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends SeleniumUtility {

    @FindBy(xpath = "//button[text()='Buy moisturizers']")
    WebElement moisturizersEle;

    @FindBy(xpath = "//button[text()='Buy sunscreens']")
    WebElement sunscreensEle;

    @FindBy(id = "temperature")
    WebElement temperatureEle;

    @FindBy(css = " span.octicon.octicon-info")
    WebElement infoIconEle;

    public HomePage(){
        super();
        if (isTitle( PageTitles.homePage,10)) {
            Assert.assertEquals(getTitle(), PageTitles.homePage, "User is not on home page");
        }
        Logging.log("Home page open successfully");
    }

    public ProductListPage selectProductByTemperature(int curTemp){
        if(getTempForMoisturizers()>curTemp){
            Logging.log("Opening Moisturize product list page");
            click(moisturizersEle);
            DynamicConstant.productListPageTitle = PageTitles.moisturizersPage;
        }else if(getTempForSunscreens()<curTemp) {
            Logging.log("Opening Sunscreen product list page");
            click(sunscreensEle);
            DynamicConstant.productListPageTitle = PageTitles.sunscreensPage;
        }
        return new ProductListPage();
    }

    private int getTempForMoisturizers(){
        return Integer.parseInt(StringUtility.getStringByReg(RegexConstant.selectNum,getAttributeValue(infoIconEle,"data-content")).get(0));
    }

    private int getTempForSunscreens(){
        return Integer.parseInt(StringUtility.getStringByReg(RegexConstant.selectNum,getAttributeValue(infoIconEle,"data-content")).get(1));
    }

    public int getCurrentTemperature(){
        return Integer.parseInt(StringUtility.getStringByReg(RegexConstant.selectNum,getText(temperatureEle)).get(0));
    }


}
