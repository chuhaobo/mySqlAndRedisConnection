package com.nobiplz.mySqlAndRedisConnection.excelOperation.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

public interface ExcelOperationService {

    Workbook giveExcel(FileInputStream fileInputStream,String peopleType);
}
