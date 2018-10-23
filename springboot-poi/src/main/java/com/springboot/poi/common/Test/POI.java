package com.springboot.poi.common.Test;
import java.io.FileOutputStream;

import org.apache.poi.ss.util.CellRangeAddressList; 
import org.apache.poi.xssf.usermodel.XSSFDataValidation; 
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint; 
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POI{
	public static void main(String[] args) {
	    try 
	    {
	        dropDownList42007("D:\\test.xlsx");
	    } 
	    catch (Exception e) {

	        e.printStackTrace();
	    }
	}
	public static void dropDownList42007(String filePath)
	        throws Exception {
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("下拉列表测试");
	    String[] datas = new String[] {"维持","恢复","调整"};
	    
	    XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
	    
	    XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
	            .createExplicitListConstraint(datas);
	    CellRangeAddressList addressList = null;
	    XSSFDataValidation validation = null;
	    for (int i = 0; i < 100; i++) {
	        addressList = new CellRangeAddressList(i, i, 0, 2);
	        validation = (XSSFDataValidation) dvHelper.createValidation(
	                dvConstraint, addressList);
	        sheet.addValidationData(validation);
	    }
	    
	    
	    FileOutputStream stream = new FileOutputStream(filePath);
	    workbook.write(stream);
	    stream.close();
	    addressList = null;
	    validation = null;
	}
}
