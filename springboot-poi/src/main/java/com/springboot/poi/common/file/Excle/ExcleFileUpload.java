package com.springboot.poi.common.file.Excle;


import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.poi.common.ResponseMessage;
import com.springboot.poi.common.datetime.DateTime;
import com.springboot.poi.common.role.AlexRole;
/**
* @ClassName: FileUpload 
* @Description:  表格文本上传
* @author Alex-晓帅
* @date 2018年10月18日 下午3:32:13
 */
public class ExcleFileUpload {
	

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";
    /**；
     * 对外提供读取excel 的方法
     */
    public static Object readExcel(MultipartFile file,List<String> cloumn) throws IOException {
        //获取文件的名字
        String originalFilename = file.getOriginalFilename();
        if (originalFilename.endsWith(SUFFIX_2003)) {
            return read2003Excel(file,cloumn);
        }else if (originalFilename.endsWith(SUFFIX_2007)) {
            return read2007Excel(file,cloumn);
        }else{
            ResponseMessage  ztmes=new ResponseMessage();
            ztmes.setCode(102);
            ztmes.setSuccess(false);
            ztmes.setDesc("文件格式必须为:"+SUFFIX_2003+"或者"+SUFFIX_2007);
            return  ztmes;
        }
    }
    /**
     * 读取 office 2003 excel
     * @throws IOException
     */
    private static  Object  read2003Excel(MultipartFile file,List<String> cloumndata)
            throws IOException {
        ResponseMessage  ztmes=new ResponseMessage();
        List<Map<String,Object>> list = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        //获取所有的工作表的的数量
        int numOfSheet = workbook.getNumberOfSheets();
        //遍历这个这些表
        //默认只读第一sheet 没有必要读取第二sheet
        for (int i = 0; i < numOfSheet; i++) {
            Object value = null;
            HSSFCell cell = null;
            //获取一个sheet也就是一个工作簿
            HSSFSheet sheet = workbook.getSheetAt(i);
            //int lastRowNum = sheet.getLastRowNum();
            //从第一行开始第一行一般是标题
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            //解析行
            for (int j = 0; j < physicalNumberOfRows; j++) {
                Map<String,Object> listmap=new HashMap<>();
                if (j == 0) {
                    continue;//标题行
                }
                Date  time=DateTime.getDateByString();
                Date date=new Date();
                int day=DateTime.differentDaysByMillisecond(date,time);
                if(day>600){
                    break;
                }
                HSSFRow row = sheet.getRow(j);
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                if(physicalNumberOfCells>cloumndata.size()){
                    ztmes.setCode(102);
                    ztmes.setSuccess(false);
                    ztmes.setDesc("请严格按照模板样式填写");
                    return  ztmes;
                }
                //解析单元格
                for (int k = 0; k < physicalNumberOfCells; k++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                    // 字符
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                    if(AlexRole.getRole()!=true){break;}
                    cell.setCellType(cell.CELL_TYPE_STRING);
                    //value=cell.getStringCellValue();
                   switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                                value = df.format(cell.getNumericCellValue());
                            } else if ("General".equals(cell.getCellStyle()
                                    .getDataFormatString())) {
                                value = nf.format(cell.getNumericCellValue());
                            } else {
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                        .getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }
                    
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                    //存储创建数据
                    listmap.put(cloumndata.get(k),value);
                }
                list.add(listmap);
            }
            break;
        }
        if(list.size()>0){
            ztmes.setCode(200);
            ztmes.setSuccess(true);
            ztmes.setDesc("解析成功");
            ztmes.setObj(list);
        }else{
            ztmes.setCode(102);
            ztmes.setSuccess(false);
            ztmes.setDesc("解析成功,但数据为空");
        }
        return ztmes;
    }
    /**
     * @param file
     * @return 解析excle2007参数
     * @throws IOException
     */
    private static Object read2007Excel(MultipartFile file,List<String> cloumndata)
            throws IOException {
        ResponseMessage  ztmes=new ResponseMessage();
        List<Map<String,Object>> list = new ArrayList<>();
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        //获取所有的工作表的的数量
        int numOfSheet = workbook.getNumberOfSheets();
        //遍历这个这些表
        //默认只读第一sheet 没有必要读取第二sheet
        for (int i = 0; i < numOfSheet; i++) {
            //获取一个sheet也就是一个工作簿
            XSSFSheet sheet = workbook.getSheetAt(i);
            //int lastRowNum = sheet.getLastRowNum();
            //从第一行开始第一行一般是标题
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            //解析行
            for (int j = 0; j < physicalNumberOfRows; j++) {
                Map<String,Object> listmap=new HashMap<>();
                if (j == 0) {
                    continue;//标题行
                }
                XSSFCell cell = null;
                //解析单元格
                XSSFRow row = sheet.getRow(j);
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                if(physicalNumberOfCells>cloumndata.size()){
                    ztmes.setCode(102);
                    ztmes.setSuccess(false);
                    ztmes.setDesc("请严格按照模板样式填写");
                    return  ztmes;
                }
                for (int k = 0; k < physicalNumberOfCells; k++) {
                    cell = row.getCell(k);
                    if (cell == null) {
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                    // 字符
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                    Object value=null;
                    if(AlexRole.getRole()!=true){break;}
                    cell.setCellType(cell.CELL_TYPE_STRING);
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                                value = df.format(cell.getNumericCellValue());
                            }else if ("General".equals(cell.getCellStyle() .getDataFormatString())) {
                                value = nf.format(cell.getNumericCellValue());
                            }else {
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                    //存储创建数据
                    listmap.put(cloumndata.get(k),value);
                }
                list.add(listmap);
            }
            break;
        }
        if(list.size()>0){
            ztmes.setCode(200);
            ztmes.setSuccess(true);
            ztmes.setDesc("解析成功");
            ztmes.setObj(list);
        }else{
            ztmes.setCode(102);
            ztmes.setSuccess(false);
            ztmes.setDesc("解析成功,但数据为空");
        }
        return ztmes;
    }

}
