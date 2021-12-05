package com.flink.pages;

import com.flink.constants.DynamicConstant;
import com.flink.constants.PageTitles;
import com.flink.utility.commonutility.Logging;
import com.flink.utility.commonutility.SeleniumUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ProductListPage extends SeleniumUtility {

    @FindBy(xpath = "//p[contains(text(),'almond') or contains(text(),'ALMOND') or contains(text(),'Almond')]")
    List<WebElement> almondProductEle;

    @FindBy(xpath = "//p[contains(text(),'almond') or contains(text(),'ALMOND') or contains(text(),'Almond')]/following-sibling::button")
    List<WebElement> almondProductBtnEle;

    @FindBy(xpath = "//p[contains(text(),'almond') or contains(text(),'ALMOND') or contains(text(),'Almond')]/following-sibling::p")
    List<WebElement> almondProductPriceEle;

    @FindBy(xpath = "//p[contains(text(),'aloe') or contains(text(),'ALOE') or contains(text(),'Aloe')]")
    List<WebElement> aloeProductEle;

    @FindBy(xpath = "//p[contains(text(),'aloe') or contains(text(),'ALOE') or contains(text(),'Aloe')]/following-sibling::button")
    List<WebElement> aloeProductBtnEle;

    @FindBy(xpath = "//p[contains(text(),'aloe') or contains(text(),'ALOE') or contains(text(),'Aloe')]/following-sibling::p")
    List<WebElement> aloeProductPriceEle;

    @FindBy(xpath = "//p[contains(text(),'SPF-50') or contains(text(),'spf-50') or contains(text(),'Spf-50')]")
    List<WebElement> spf50ProductEle;

    @FindBy(xpath = "//p[contains(text(),'SPF-50') or contains(text(),'spf-50') or contains(text(),'Spf-50')]/following-sibling::button")
    List<WebElement> spf50ProductBtnEle;

    @FindBy(xpath = "//p[contains(text(),'SPF-50') or contains(text(),'spf-50') or contains(text(),'Spf-50')]/following-sibling::p")
    List<WebElement> spf50ProductPriceEle;

    @FindBy(xpath = "//p[contains(text(),'SPF-30') or contains(text(),'spf-30') or contains(text(),'Spf-30')]")
    List<WebElement> spf30ProductEle;

    @FindBy(xpath = "//p[contains(text(),'SPF-30') or contains(text(),'spf-30') or contains(text(),'Spf-30')]/following-sibling::button")
    List<WebElement> spf30ProductBtnEle;

    @FindBy(xpath = "//p[contains(text(),'SPF-30') or contains(text(),'spf-30') or contains(text(),'Spf-30')]/following-sibling::p")
    List<WebElement> spf30ProductPriceEle;

    @FindBy(id = "cart")
    WebElement cartButtonEle;

    WebElement element = null;
    String itemName = "";

    public ProductListPage() {
        super();
        if (isTitle( DynamicConstant.productListPageTitle,10)) {
            Assert.assertEquals(getTitle(), DynamicConstant.productListPageTitle, "User is not on the product list page");
        }
        Logging.log("Product List page open successfully");
    }

    public void addLeastPriceAlmondProductInCart() {
        element = CommonMethods.findElementWithLeastValue(almondProductPriceEle);
        itemName = getText(almondProductEle.get(DynamicConstant.productIndex));
        DynamicConstant.expectedItemName.add(itemName);
        click(almondProductBtnEle.get(DynamicConstant.productIndex));
    }

    public void addLeastPriceAloeProductInCart() {
        element = CommonMethods.findElementWithLeastValue(aloeProductPriceEle);
        itemName = getText(aloeProductEle.get(DynamicConstant.productIndex));
        DynamicConstant.expectedItemName.add(itemName);
        click(aloeProductBtnEle.get(DynamicConstant.productIndex));
    }

    public void addLeastPriceSpf50ProductInCart() {
        element = CommonMethods.findElementWithLeastValue(spf50ProductPriceEle);
        itemName = getText(spf50ProductEle.get(DynamicConstant.productIndex));
        DynamicConstant.expectedItemName.add(itemName);
        click(spf50ProductBtnEle.get(DynamicConstant.productIndex));
    }

    public void addLeastPriceSpf30ProductInCart() {
        element = CommonMethods.findElementWithLeastValue(spf30ProductPriceEle);
        itemName = getText(spf30ProductEle.get(DynamicConstant.productIndex));
        DynamicConstant.expectedItemName.add(itemName);
        click(spf30ProductBtnEle.get(DynamicConstant.productIndex));
    }

    public void addLeastPriceProducts(){
        DynamicConstant.expectedItemPrice.clear();
        DynamicConstant.expectedItemName.clear();
        if (DynamicConstant.productListPageTitle.equals(PageTitles.moisturizersPage)){
            Logging.log("Adding almond and aloe least price product");
            addLeastPriceAlmondProductInCart();
            addLeastPriceAloeProductInCart();
            Logging.log("Almond and aloe least price product added successfully");
        }else if (DynamicConstant.productListPageTitle.equals(PageTitles.sunscreensPage)){
            Logging.log("Adding Spf30 and Spf50 least price product");
            addLeastPriceSpf50ProductInCart();
            addLeastPriceSpf30ProductInCart();
            Logging.log("Spf30 and Spf50 least price product added successfully");
        }
    }

    public CartDetailPage clickOnCartButton(){
        Logging.log("Click on Cart button");
        click(cartButtonEle);
        return new CartDetailPage();
    }



}
