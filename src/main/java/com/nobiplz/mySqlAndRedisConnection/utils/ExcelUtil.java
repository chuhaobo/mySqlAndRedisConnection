package com.nobiplz.mySqlAndRedisConnection.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * excel工具类
 */
public class ExcelUtil {

  public static JSONObject excelToJson(FileInputStream fileInputStream) {
    try {
      //FileInputStream inp = new FileInputStream("/Users/left/Downloads/annual_budget_template.xlsx");
      Workbook workbook = WorkbookFactory.create(fileInputStream);
      //获取sheet数
      int sheetNum = workbook.getNumberOfSheets();
      JSONObject jsonObject = new JSONObject();
      for (int s = 0; s < sheetNum; s++) {
        // Get the Sheet of s.
        Sheet sheet = workbook.getSheetAt(s);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        if (rownum <= 1) {
          continue;
        }
        //获取第一行
        Row row1 = sheet.getRow(0);
        //获取最大列数
        int colnum = row1.getPhysicalNumberOfCells();
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i < rownum; i++) {
          Row row = sheet.getRow(i);
          if (row != null) {
//                    List<Object> list = new ArrayList<>();
            JSONObject rowObj = new JSONObject();
            //循环列
            for (int j = 0; j < colnum; j++) {
              Cell cellData = row.getCell(j);
              if (cellData != null) {
                //判断cell类型
                switch (cellData.getCellType()) {
                  case Cell.CELL_TYPE_NUMERIC: {
                    rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getNumericCellValue());
                    break;
                  }
                  case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cellData)) {
                      //转换为日期格式YYYY-mm-dd
                      rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getDateCellValue());
                    } else {
                      //数字
                      rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getNumericCellValue());
                    }
                    break;
                  }
                  case Cell.CELL_TYPE_STRING: {
                    rowObj.put(row1.getCell(j).getStringCellValue(), cellData.getStringCellValue());
                    break;
                  }
                  default:
                    rowObj.put(row1.getCell(j).getStringCellValue(), "");
                }
              } else {
                rowObj.put(row1.getCell(j).getStringCellValue(), "");

              }
            }
            jsonArray.add(rowObj);
          }
        }
        jsonObject.put(sheet.getSheetName(), jsonArray);
      }
      return jsonObject;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("轉換異常");
    }
  }
}
