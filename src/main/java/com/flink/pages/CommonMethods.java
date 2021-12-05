package com.flink.pages;

import com.flink.constants.DynamicConstant;
import com.flink.constants.RegexConstant;
import com.flink.utility.commonutility.SeleniumUtility;
import com.flink.utility.commonutility.StringUtility;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonMethods extends SeleniumUtility {

    public CommonMethods(){
        super();
    }

    public static WebElement findElementWithLeastValue(List<WebElement> elements){
        List<Integer> priceList = new ArrayList<>();

        for (WebElement ele:elements){
            String priceDetails = StringUtility.getStringByReg(RegexConstant.selectNum,ele.getText()).get(0);
            int val = Integer.parseInt(priceDetails);
            priceList.add(val);
        }
        DynamicConstant.productIndex = priceList.indexOf(Collections.min(priceList));
        DynamicConstant.expectedItemPrice.add(priceList.get(DynamicConstant.productIndex));
        return elements.get(DynamicConstant.productIndex);
    }
}
