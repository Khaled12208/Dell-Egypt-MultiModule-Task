package com.multimodule.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelSheetParser {

    private final XSSFSheet sheet;
    protected final Logger LOGGER = LogManager.getLogger(ExcelSheetParser.class);

    public ExcelSheetParser(String excelFilePath, String sheetName) throws Exception {

        this.sheet=TestResourcesReaders.readExcelSheetByName(excelFilePath,sheetName);
    }

    public ExcelSheetParser(XSSFSheet sheet){

        this.sheet=sheet;
        LOGGER.debug("Sheet "+sheet.getSheetName() +"was loaded");
    }

    public String getDataByRowAndColName(String rowName, String columnName) {
        Integer rowIndex = getRowIndex(rowName);
        Integer columnIndex= getColumnIndex(columnName);
        if ((rowIndex != null) && (columnIndex !=null)) {

          return getCellValueAsString(sheet.getRow(rowIndex).getCell(columnIndex));
        }else
        {
            throw new NullPointerException("Couldn't find data at row "+rowName+" and column "+columnName);
        }

    }
    public String getDataByRowAndColNumber(Integer rowNumber, String columnName) {
        Integer columnIndex= getColumnIndex(columnName);
        if ((rowNumber != null) && (columnIndex !=null)) {

            return getCellValueAsString(sheet.getRow(rowNumber).getCell(columnIndex));
        }else
        {
            throw new NullPointerException("Couldn't find data at row "+rowNumber+" and column "+columnName);
        }

    }

    public String getDataByRowNumberAndColNumber(Integer rowNumber, Integer columnNamber) {
        if ((rowNumber != null) && (columnNamber !=null)) {
            Cell cell = sheet.getRow(rowNumber).getCell(columnNamber);
            return getCellValueAsString(cell);
        }else
        {
            throw new NullPointerException("Couldn't find data at row "+rowNumber+" and column "+columnNamber);
        }

    }


    private Integer getColumnIndex(String columnCellName)
    {
        LOGGER.debug("getting index of Column", columnCellName);

        Integer cellIndex= null;
        int numberOfRows = sheet.getLastRowNum();
        for (int i = 0; i < numberOfRows; i++) {
            int numberOfCells = sheet.getRow(i).getLastCellNum();
            for (int j = 0; j < numberOfCells; j++)
            {
                if (getCellValueAsString(sheet.getRow(i).getCell(j)).equalsIgnoreCase(columnCellName))
                {
                    cellIndex=j;
                    break;
                }

            }

        }
        return cellIndex;
    }

    private Integer getRowIndex(String rowCellName)
    {
        LOGGER.debug("getting index of Row ", rowCellName);
        Integer rowIndex= null;
        int numberOfRows = sheet.getLastRowNum();
        for (int i = 0; i < numberOfRows; i++) {
            int numberOfCells = sheet.getRow(i).getLastCellNum();
            for (int j = 0; j < numberOfCells; j++)
            {
                if (getCellValueAsString(sheet.getRow(i).getCell(j)).trim().equalsIgnoreCase(rowCellName))
                {
                    rowIndex=i;
                    break;
                }

            }

        }
        return rowIndex;
    }




    private String getCellValueAsString(Cell cell)
    {
        LOGGER.debug("Reading Cell", cell);
        String value= null;
            switch (cell.getCellType()) {
                case BOOLEAN:
                    value=  String.valueOf(cell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    value= String.valueOf(cell.getNumericCellValue());
                    break;
                case STRING:
                    value= String.valueOf(cell.getRichStringCellValue());
                    break;
                case BLANK:
                    value= "";
                break;

            }
        return value;
    }


}
