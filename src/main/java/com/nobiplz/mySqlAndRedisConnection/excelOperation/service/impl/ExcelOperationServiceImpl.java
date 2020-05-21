package com.nobiplz.mySqlAndRedisConnection.excelOperation.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nobiplz.mySqlAndRedisConnection.excelOperation.service.ExcelOperationService;
import com.nobiplz.mySqlAndRedisConnection.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Service
public class ExcelOperationServiceImpl implements ExcelOperationService {

  //Excel导出转换接口
  @Override
  public Workbook giveExcel(FileInputStream fileInputStream,String peopleType) {
    try {
      //需要整合的部分
      String advertiseType = peopleType; //面向目标人群
      //定位需要生成多少Row的
      int rowNumber = 0;

      Workbook workbook = new HSSFWorkbook();
      Sheet sheet = workbook.createSheet("advertiseType");


      //插入Excel的标题
      Row row = sheet.createRow(rowNumber++);
      for (int i = 0 ; i < 18 ; i++){
        Cell cell = row.createCell(i);
        switch (i){
          case 0:
            cell.setCellValue("物料ID");
            break;
          case 1:
            cell.setCellValue("物料名称");
            break;
          case 2:
            cell.setCellValue("广告位ID");
            break;
          case 3:
            cell.setCellValue("广告位名称");
            break;
          case 4:
            cell.setCellValue("媒体ID");
            break;
          case 5:
            cell.setCellValue("媒体名称");
            break;
          case 6:
            cell.setCellValue("媒体物料审核ID");
            break;
          case 7:
            cell.setCellValue("跳转地址");
            break;
          case 8:
            cell.setCellValue("DeepLink");
            break;
          case 9:
            cell.setCellValue("曝光-1");
            break;
          case 10:
            cell.setCellValue("曝光-播放 3/4");
            break;
          case 11:
            cell.setCellValue("曝光-播放结束");
            break;
          case 12:
            cell.setCellValue("曝光-有效播放");
            break;
          case 13:
            cell.setCellValue("点击-1");
            break;
          case 14:
            cell.setCellValue("点击-2");
            break;
          case 15:
            cell.setCellValue("点击-3");
            break;
          case 16:
            cell.setCellValue("曝光-卡片监测");
            break;
          case 17:
            cell.setCellValue("曝光-可见曝光");
            break;
        }
      }

      //对json 的操作
      JSONObject jsonObject = ExcelUtil.excelToJson(fileInputStream);
      JSONArray jsonArray = (JSONArray) jsonObject.get("inputExcel");

      //转换得到的mapList
      List<Map<String, String>> mapList = (List) jsonArray;

      for (Map<String, String> childMap : mapList) {
        //拿到广告位名称
        String advertisementName = childMap.get("广告位名称");

        //用-解析对应的广告位名称
        String[] advertisementNameArray = advertisementName.split("-");

        //如果包含一种面向广告得面向目标人群 就是这个 进行 广告位得拼接操作
        if (advertisementNameArray[advertisementNameArray.length - 1].contains(advertiseType)) {
          //复合这个需要建立Row
          Row valueRow = sheet.createRow(rowNumber++);

          //下面得方式合成广告位名称
          String advertisePlaceName = advertisementNameArray[2] + "_" + advertisementNameArray[3] + "-" + advertisementNameArray[0] + "-" + advertisementNameArray[1];

          //建立插入实体Vaule
          for (int i = 0 ; i < 18 ; i++){
            Cell cell = valueRow.createCell(i);
            switch (i){
              case 0:
                cell.setCellValue("");
                break;
              case 1:
                cell.setCellValue("");
                break;
              case 2:
                cell.setCellValue("");
                break;
              case 3:
                cell.setCellValue(advertisePlaceName);
                break;
              case 4:
                cell.setCellValue("");
                break;
              case 5:
                cell.setCellValue(childMap.get("媒体名称"));
                break;
              case 6:
                cell.setCellValue("");
                break;
              case 7:
                cell.setCellValue(childMap.get("跳转地址"));
                break;
              case 8:
                cell.setCellValue("");
                break;
              case 9:
                cell.setCellValue(childMap.get("曝光1URL"));
                break;
              case 10:
                cell.setCellValue("");
                break;
              case 11:
                cell.setCellValue("");
                break;
              case 12:
                cell.setCellValue("");
                break;
              case 13:
                cell.setCellValue(childMap.get("点击1URL"));
                break;
              case 14:
                cell.setCellValue("");
                break;
              case 15:
                cell.setCellValue("");
                break;
              case 16:
                cell.setCellValue("");
                break;
              case 17:
                cell.setCellValue("");
                break;
            }
          }

/*          System.out.println("广告位名称: " + advertisePlaceName);
          System.out.println("媒体名称: " + childMap.get("媒体名称"));
          System.out.println("点击1: " + childMap.get("点击1URL"));
          System.out.println("曝光1: " + childMap.get("曝光1URL"));*/
        }
/*        System.out.println(advertisementName);*/
      }
      return workbook;
    } catch (Exception e) {
      throw new RuntimeException("Excel转换失败");
    }

  }
}
