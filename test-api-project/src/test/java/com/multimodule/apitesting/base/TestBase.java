package com.multimodule.apitesting.base;

import com.multimodule.apitesting.driver.RequestBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {

    public RequestBuilder request;
    private Properties prop;

    public void initRequestBase() throws IOException {
        try(InputStream inputStream = new FileInputStream("src/test/resources/api-config.properties"))
        {
            prop = new Properties();
            prop.load(inputStream);


        }catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }catch (IOException e){
            throw new IOException();
        }
        request= new RequestBuilder().setBaseURL(prop.getProperty("baseURL.name").trim());
    }

}
