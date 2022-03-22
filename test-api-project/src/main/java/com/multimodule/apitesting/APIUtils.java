package com.multimodule.apitesting;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.Map;


public class APIUtils {


    public static int getFirstProductID(Response response)
    {
        JsonPath jsnPath = response.jsonPath();
       return jsnPath.get("data.id[0]");

    }

    public static int getPostedProductID(Response response)
    {
        JsonPath jsnPath = response.jsonPath();
        return jsnPath.get("id");

    }

    public static JSONObject generateJSONUsing(Map<String,String> map)
    {
        JSONObject requestBody = new JSONObject();
        for (Map.Entry<String,String> entry : map.entrySet())
        {
            requestBody.put(entry.getKey(), entry.getValue());

        }
        return  requestBody;
    }


}
