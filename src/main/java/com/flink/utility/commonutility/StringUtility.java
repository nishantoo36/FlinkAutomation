package com.flink.utility.commonutility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtility {
    public static List<String> getStringByReg(String pattern , String data ) {
        List<String> val = new ArrayList<>();
        Pattern reg = Pattern.compile(pattern);
        Matcher m = reg.matcher(data);
        while(m.find()){
            val.add(m.group());
        }
      return val;
    }
}
