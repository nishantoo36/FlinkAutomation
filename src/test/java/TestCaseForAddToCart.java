import com.flink.constants.DynamicConstant;
import com.flink.constants.StaticConstant;
import com.flink.pages.CartDetailPage;
import com.flink.pages.ConfirmationPage;
import com.flink.pages.HomePage;
import com.flink.pages.ProductListPage;
import com.flink.utility.commonutility.CommonPreAndPostConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestCaseForAddToCart extends CommonPreAndPostConditions {
    public TestCaseForAddToCart() {
        super();
        if (url == null) {
            url = "https://weathershopper.pythonanywhere.com/";
        }
    }

    @Test
    public void verifyEToEString() throws Exception {
        HomePage homePage = new HomePage();
        ProductListPage productListPage = homePage.selectProductByTemperature(homePage.getCurrentTemperature());
        productListPage.addLeastPriceProducts();
        CartDetailPage cartDetailPage = productListPage.clickOnCartButton();
        Assert.assertTrue(DynamicConstant.expectedItemName.equals(cartDetailPage.getActualCartProductName()),"Expected is "+ Arrays.asList(DynamicConstant.expectedItemName) +"\n Actual is "+ Arrays.asList(cartDetailPage.getActualCartProductName()));
        Assert.assertTrue(DynamicConstant.expectedItemPrice.equals(cartDetailPage.getActualCartProductPrice()),"Expected is "+ Arrays.asList(DynamicConstant.expectedItemPrice) +"\n Actual is "+ Arrays.asList(cartDetailPage.getActualCartProductPrice()));
        Assert.assertEquals(cartDetailPage.getExpectedTotalPrice(),cartDetailPage.getActualTotalPrice(),"Actual Total is not matched with expected");
        cartDetailPage.clickPayWithCardButton();
        Assert.assertTrue(cartDetailPage.isCardDetailsPopUpAppear(),"Pop-up to enter card details is not appeared");
        cartDetailPage.enterCardDetails(StaticConstant.email,StaticConstant.cardNumber,StaticConstant.endDate,StaticConstant.csvNumber,StaticConstant.zipCode);
        ConfirmationPage confirmationPage= cartDetailPage.clickCreditCardPayButton();
        Assert.assertTrue(confirmationPage.isSuccessMessagesAppears(),"Confirmation page is not appearing");
    }
}
