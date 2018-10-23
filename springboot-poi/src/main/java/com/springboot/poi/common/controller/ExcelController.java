package com.springboot.poi.common.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.poi.common.ResponseMessage;
import com.springboot.poi.common.file.Excle.ExcleFileDownload;
import com.springboot.poi.common.file.Excle.ExcleFileUpload;
import com.springboot.poi.common.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * excle文档上传下载
 * alex-晓帅
 */

@Api("excle上传下载")
@RestController
@RequestMapping("/exlce")
public class ExcelController {

    @ApiOperation(value="文件下载:案例")
    @GetMapping("download")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        ResponseMessage ztmes=new ResponseMessage();
        try {
            List<User> userList =new ArrayList<User>();
            User  us=new User();
            us.setId(1000L);
            us.setUsername("袁泉");
            userList.add(us);
            User  us1=new User();
            us1.setId(2000L);
            us1.setUsername("袁晓帅");
            userList.add(us1);
            //字段转换对应
            List<String> head=new ArrayList<>();
            // 生成Excel文件
            head.add("id,编号");
            head.add("username,名称");
            head.add("static,性别,男-女");
            //下载数据
            //List<Map<String,Object>> listmap=ChangeDataUtil.ListObjectTO(JSON.toJSONString(userList));
            //下载模板
            List<Map<String,Object>> listmap=new ArrayList<>();
            ExcleFileDownload.createFile(response, ExcleFileDownload.getworkbook(listmap,head,"alex"),"alexfile");
        } catch (Exception e) {
            ztmes.setCode(102);
            ztmes.setDesc("Exception异常提示:"+e.getMessage());
            ztmes.setSuccess(false);
        }
    }

    @ApiOperation(value="文件上传:案例")
    @PostMapping("/upload")
    public @ResponseBody Object uploadExcel(@RequestParam("file") MultipartFile file) {
        ResponseMessage ztmes=new ResponseMessage();
        Object obj=null;
        try {
             //解析结果集
             List<String>  cloumnlist=new ArrayList<>();
             cloumnlist.add("id");
             cloumnlist.add("username");
             obj=ExcleFileUpload.readExcel(file,cloumnlist);
        } catch (Exception e) {
            ztmes.setCode(102);
            ztmes.setDesc("Exception异常提示:"+e.getMessage());
            ztmes.setSuccess(false);
        }
        return obj;
    }

}
