package com.multimodule.helpers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TestResourcesReaders {

    private static Utils help = new Utils();

    // This Function to read files as String
    public static String readTestResourceFileAsString(String file) throws Exception {

        return new String(Files.readAllBytes(Paths.get("src/test/resources/" + file)));
    }

    public static JSONObject readTestResourceFileAsJSON(String file) throws Exception {

        return new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/" + file))));
    }

    public static FileInputStream readTestResourceFileAsInputStream(String file) throws Exception {

        return new FileInputStream(new File("src/test/resources/" + file));
    }

    public static Map<String, Object> readTestJSONResourceFileAsMap(String file) throws IOException, JSONException {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/" + file))));

        } catch (JSONException err) {
        }

        return help.jsonToMap(jsonObject);
    }

    public static XSSFSheet readExcelSheetByName(String filePath , String sheetName) throws Exception {
        FileInputStream inputStream = readTestResourceFileAsInputStream(filePath);
        XSSFWorkbook excelBook = new XSSFWorkbook(inputStream);
        return excelBook.getSheet(sheetName);
    }

    public static XSSFSheet readExcelSheetByIndex(String filePath , int sheetIndex) throws Exception {
        FileInputStream inputStream = readTestResourceFileAsInputStream(filePath);
        XSSFWorkbook excelBook = new XSSFWorkbook(inputStream);
        return excelBook.getSheetAt(sheetIndex);
    }

}
