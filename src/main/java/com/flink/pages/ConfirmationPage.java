package com.flink.pages;

import com.flink.constants.PageTitles;
import com.flink.utility.commonutility.Logging;
import com.flink.utility.commonutility.SeleniumUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ConfirmationPage extends SeleniumUtility {

    @FindBy(xpath = "//h2[text()='PAYMENT SUCCESS']")
    WebElement success1MsgEle;

    @FindBy(xpath = "//p[text()='Your payment was successful. You should receive a follow-up call from our sales team.']")
    WebElement success2MsgEle;

    public ConfirmationPage() {
        super();
        if (isTitle( PageTitles.confirmationPage,10)){
            Assert.assertEquals(getTitle(), PageTitles.confirmationPage, "User is not on the confirmation page");}
        Logging.log("Submitting card details successfully");
        Logging.log("Confirmation page appear successfully");
    }

    public boolean isSuccessMessagesAppears(){
        return isElementAvailable(success1MsgEle,30) && isElementAvailable(success2MsgEle,3);
    }


}
