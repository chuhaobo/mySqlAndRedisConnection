package com.nobiplz.mySqlAndRedisConnection.excelOperation.controller;

import com.nobiplz.mySqlAndRedisConnection.excelOperation.service.ExcelOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@Api(value = "ecxcel操作相关接口", description = "excel操作相关接口")
@RequestMapping("/excelOperation")
public class excelOperationContreller {

  @Autowired
  ExcelOperationService excelOperationService;

  @ApiOperation(value = "excel 处理", httpMethod = "POST")
  @RequestMapping(value = "/getExcel", method = RequestMethod.POST)
  public void getExcel(MultipartFile file,
                       HttpServletResponse httpServletResponse,
                       String peopleType) {
    try {
      //传空文件 给爷爬
      if (file.isEmpty()) {
        return;
      }
      Workbook workbook = excelOperationService.giveExcel((FileInputStream) file.getInputStream(),peopleType);
      httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + "xxxx"+".xls");
      OutputStream outputStream = httpServletResponse.getOutputStream();
      workbook.write(outputStream);
      outputStream.flush();
      outputStream.close();
    } catch (Exception e) {
      throw new RuntimeException("excel转换失败");
    }
  }
}
