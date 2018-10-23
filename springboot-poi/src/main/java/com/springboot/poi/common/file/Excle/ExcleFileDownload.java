package com.springboot.poi.common.file.Excle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;

/**
 * 2007
 */
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.springboot.poi.common.role.AlexRole;
import com.springboot.poi.common.util.AlexExcleUtil;

/**
 * excle 数据下载
 * Alex-晓帅
 */
public class ExcleFileDownload {
	private static CellStyle titleStyle = null;   
    private static CellStyle dataStyle = null;  

    /**
     * @param listdata 数据
     * SXSSFWorkbook  低内存占用程序
     * @param titlename head
     * @param filename 文档名称、每sheet 名称
     * @return
     */
    public  static SXSSFWorkbook getworkbook(List<Map<String,Object>> listdata,List<String> titlename,String filename) throws Exception{
        SXSSFWorkbook xWorkbook = null;
        int rowaccess=1000;
        int totle=listdata.size();//
        int mus=50000;//设置每页显示80000笔数据
        xWorkbook = new SXSSFWorkbook(rowaccess);
        //创建-设置样式
        CellStyle cs = xWorkbook.createCellStyle();
        if(totle>0){
            int avg=totle/mus;
            //此处进行sheet 分页
            for (int i = 0; i < avg+1; i++) {
                //创建(sheet)分页名称
                Sheet sh = xWorkbook.createSheet(filename+(i+1));
                //创建head 数据
                setSheetHeader(xWorkbook, sh,titlename);
                int num=i*mus;
                int index=0;
                //遍历清空、数据
                for(int m=num;m<totle;m++) {
                    if (index == mus) {
                        break;
                    }
                    for (int iBody = 0; iBody < listdata.size(); iBody++) {
                        Row   row = sh.createRow(iBody + 1);
                        Map<String,Object> result=listdata.get(iBody);
                        if(AlexRole.getRole()!=true){break;}
                        if(result!=null){
                            Map<String,Object> mapdata=(Map<String,Object>)result.get("map");
                            if(mapdata !=null ){
                                for(int k=0;k<titlename.size();k++){
                                    String[] keyvalues=titlename.get(k).split(",");
                                    if(keyvalues.length>1){
                                        String key=keyvalues[0];
                                        Cell cells = row.createCell(k);
                                        String  value=""+mapdata.get(key);
                                        if (value != null || value!="") {
                                            cells.setCellValue(value);
                                        } else {
                                            cells.setCellValue("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    index++;
                    //每当行数达到设置的值就刷新数据到硬盘,以清理内存
                    if(m%rowaccess==0){
                        ((SXSSFSheet)sh).flushRows();
                    }
                }
            }
        }else{//只下载表头
            Sheet sh = xWorkbook.createSheet(filename+"模板");
            //加载样式
            setDataCellStyles(xWorkbook,sh);
            //加载头部样式
            settitleCellStyles(xWorkbook,sh);
            //创建head 数据
            setSheetHeader(xWorkbook,sh,titlename);
            //赋值样式
            //AlexExcleUtil.toDataValidationByFormula(sh);;
            
        }
        return xWorkbook;
    }
    /**
     *  添加样式下拉框
     */
    public static void creatAppRow(Sheet sh,int naturalRowIndex,List<String> titlename){
    	 Row row = sh.createRow(naturalRowIndex-1);
    	 for(int i=0;i<titlename.size();i++){
             Cell   cell = row.createCell(i);
             String[] keyvalues=titlename.get(i).split(",");
             if(keyvalues.length>2){
            	 String name=keyvalues[2];
            	 if(name!=null && name!=""){
            		 String[] listname=name.split("-");
            		 cell.setCellValue("请选择");  
                	 cell.setCellStyle(dataStyle);  
                	 //在第一行第一个单元格，插入下拉框  
                     //得到验证对象    
                     DataValidation data_validation_list =null;//AlexExcleUtil.getDataValidationByFormula(listname,naturalRowIndex,(i+1)); //从1开始下拉框处于第几列  
                     //工作表添加验证数据    
                     sh.addValidationData(data_validation_list);
            	 }
             }
         }
    }
    /** 
     * 数据样式 
     * @param workbook 
     * @param sheet 
     */  
    public  static void setDataCellStyles(SXSSFWorkbook workbook,Sheet sheet){  
        dataStyle = workbook.createCellStyle();  
        //设置边框  
        dataStyle.setBorderBottom(CellStyle.BORDER_THIN);  
        dataStyle.setBorderLeft(CellStyle.BORDER_THIN);  
        dataStyle.setBorderRight(CellStyle.BORDER_THIN);  
        dataStyle.setBorderTop(CellStyle.BORDER_THIN);  
        //设置居中  
        dataStyle.setAlignment(CellStyle.ALIGN_LEFT);  
        //设置字体  
        Font font = workbook.createFont();  
        font.setFontName("宋体");  
        font.setFontHeightInPoints((short) 11); //设置字体大小  
        dataStyle.setFont(font);//选择需要用到的字体格式  
        //设置自动换行  
        dataStyle.setWrapText(true);  
    }  
    /** 
     * 表头样式 
     * @param workbook 
     * @param sheet 
     */  
    public static void settitleCellStyles(SXSSFWorkbook workbook,Sheet sheet){  
    	 titleStyle = workbook.createCellStyle();  
         //设置边框  
         titleStyle.setBorderBottom(CellStyle.BORDER_THIN);  
         titleStyle.setBorderLeft(CellStyle.BORDER_THIN);  
         titleStyle.setBorderRight(CellStyle.BORDER_THIN);  
         titleStyle.setBorderTop(CellStyle.BORDER_THIN);  
         //设置背景色  
         titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);  
         //设置居中  
         titleStyle.setAlignment(CellStyle.ALIGN_CENTER);  
         //设置字体  
         Font font = workbook.createFont();  
         font.setFontName("宋体");  
         font.setFontHeightInPoints((short) 11); //设置字体大小  
         font.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体显示  
         titleStyle.setFont(font);//选择需要用到的字体格式  
         //设置自动换行  
         titleStyle.setWrapText(true);
    }  
    
    /**
     * 设置表头
     * @param xWorkbook
     * @param sh
     * @param titlename
     */
    private static void setSheetHeader(SXSSFWorkbook xWorkbook, Sheet sh,List<String> titlename) {
      Row row = sh.createRow(0);
        // 设置表头
        for(int i=0;i<titlename.size();i++){
            Cell   cell = row.createCell(i);
            String[] keyvalues=titlename.get(i).split(",");
            if(keyvalues.length>1){
                cell.setCellValue(keyvalues[1]);
                cell.setCellStyle(titleStyle);
            }
        }
}

    //创建文件
    public static void createFile(HttpServletResponse response, SXSSFWorkbook workbook,String filename) throws  Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            // 设置文件名
            // 捕获内存缓冲区的数据，转换成字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            // 获取内存缓冲中的数据
            byte[] content = out.toByteArray();
            // 将字节数组转化为输入流
            InputStream in = new ByteArrayInputStream(content);
            //通过调用reset（）方法可以重新定位。
            response.reset();
            // 如果文件名是英文名不需要加编码格式，如果是中文名需要添加"iso-8859-1"防止乱码
            if(filename==null || filename==""){
                filename="alex";
            }
            String fileNames = filename + df.format(new Date()) + ".xlsx";
            //response.setHeader("Content-disposition", "attachment; filename = " + URLEncoder.encode(fileNames, "UTF-8"));
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileNames).getBytes(), "iso-8859-1"));
            response.addHeader("Content-Length", "" + content.length);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
    }
}
