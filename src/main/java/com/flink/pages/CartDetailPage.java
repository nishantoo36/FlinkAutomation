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

import java.util.ArrayList;
import java.util.List;

public class CartDetailPage extends SeleniumUtility {

    @FindBy(xpath = "//th[text()='Item']/following::tr/td[1]")
    List<WebElement> cartItemEle;

    @FindBy(xpath = "//th[text()='Item']/following::tr/td[2]")
    List<WebElement> cartItemPriceEle;

    @FindBy(id = "total")
    WebElement totalEle;

    @FindBy(className = "iconTick")
    WebElement submitBtnEle;

    @FindBy(xpath = "//span[text()='Pay with Card']")
    WebElement PayWithCardBtnEle;

    @FindBy(id = "email")
    WebElement emailEle;

    @FindBy(id = "card_number")
    WebElement cardNumberEle;

    @FindBy(id = "cc-exp")
    WebElement endDateEle;

    @FindBy(id = "cc-csc")
    WebElement csvNumEle;

    @FindBy(name = "stripe_checkout_app")
    WebElement cardDetailiFrameEle;

    @FindBy(name= "zip")
    WebElement zipCodeEle;


    public CartDetailPage() {
        super();
        if (isTitle( PageTitles.cartDetailsPage,10)) {
            Assert.assertEquals(getTitle(), PageTitles.cartDetailsPage, "User is not on the view cart page");
        }
        Logging.log("Card details page open successfully");
    }

    public List<String> getActualCartProductName() {
        List<String> actualList = new ArrayList<>();
        for (WebElement ele : cartItemEle) {
            actualList.add(getText(ele));
        }
        return actualList;
    }

    public List<Integer> getActualCartProductPrice() {
        List<Integer> actualList = new ArrayList<>();
        for (WebElement ele : cartItemPriceEle) {
            actualList.add(Integer.parseInt(getText(ele)));
        }
        return actualList;
    }

    public int getActualTotalPrice() {
        return Integer.parseInt(StringUtility.getStringByReg(RegexConstant.selectNum, getText(totalEle)).get(0));
    }

    public int getExpectedTotalPrice() {
        int total = 0;
        for (int val : DynamicConstant.expectedItemPrice) {
            total = total + val;
        }
        return total;
    }

    public void clickPayWithCardButton() throws Exception {
        Logging.log("Click on Pay with card button");
        if (isElementAvailable(PayWithCardBtnEle,10)) {
            click(PayWithCardBtnEle);
        }else {
            throw new Exception("Unable to find "+String.valueOf(PayWithCardBtnEle)+"element");
        }
    }

    public boolean isCardDetailsPopUpAppear() throws InterruptedException {
        if (isElementAvailable(cardDetailiFrameEle,10)) {
            switchToiFrameByElement(cardDetailiFrameEle);
            if (isElementAvailable(submitBtnEle,10)) {
                int totalOnSubmitButton = Integer.parseInt(StringUtility.getStringByReg(RegexConstant.selectNum, getText(submitBtnEle)).get(0));
                return totalOnSubmitButton == getExpectedTotalPrice();
            }else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void enterCardDetails(String email, String cardNumber, String endDate, String csvNumber,String zipCode){
        Logging.log("Pop-up for entering card details appeared successfully");
        Logging.log(" entering card details");
        enterText(email,emailEle);
        enterTexByJS(cardNumber,cardNumberEle);
        enterTexByJS(endDate,endDateEle);
        enterText(csvNumber,csvNumEle);
        if (isElementAvailable(zipCodeEle,3)){
            enterTexByJS(zipCode,zipCodeEle);
        }else {
            Assert.fail("Unable to find " +zipCodeEle);
        }
        Logging.log("Card details entering successfully");

    }

    public ConfirmationPage clickCreditCardPayButton(){
        Logging.log("Submitting card details");
        click(submitBtnEle);
        driver.switchTo().parentFrame();
        return new ConfirmationPage();
    }


}
