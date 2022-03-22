package com.multimodule.uitesting.uiutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UiUtils {


    public static String getProp(String key) throws IOException {
        Properties prop;
        try(InputStream inputStream = new FileInputStream("src/test/resources/browser-config.properties"))
        {
            prop = new Properties();
            prop.load(inputStream);
            return prop.getProperty(key);

        }catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }catch (IOException e){
            throw new IOException();
        }
    }

    public static Float getPriceAsNumeric(String price)
    {
        Pattern p = Pattern.compile("([0-9]+[.][0-9]+)");
        Matcher m = p.matcher(price);
        Float n = null;
        while (m.find()) {
             n =Float.parseFloat(m.group());
        }
        return n;
    }

}
