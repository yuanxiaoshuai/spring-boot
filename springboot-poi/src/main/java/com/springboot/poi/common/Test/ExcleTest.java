package com.springboot.poi.common.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.poi.common.ResponseMessage;
import com.springboot.poi.common.file.Excle.ExcleFileDownload;
import com.springboot.poi.common.file.Excle.ExcleFileUpload;
import com.springboot.poi.common.model.User;

public class ExcleTest {
	//下载数据
	/**
	 * @ApiOperation(value="文件下载:案例")
     *@GetMapping("download")
	* @Title: download 
	* @author Alex-xiaoshuai
	* @date   2018年10月18日 下午5:05:06 
	* @throws
	 */
	public  void  download(HttpServletRequest request, HttpServletResponse response){
		 try {
			List<User> userList =new ArrayList<User>();
	        User  us=new User();
	        us.setId("1000");
	        us.setName("袁泉");
	        userList.add(us);
	        User  us1=new User();
	        us1.setId("2000");
	        us1.setName("袁晓帅");
	        userList.add(us1);
	        //字段转换对应
	        List<String> head=new ArrayList<>();
	        // 生成Excel文件
	        head.add("id,编号");
	        head.add("username,名称");
	        //下载数据
	        //List<Map<String,Object>> listmap=ChangeDataUtil.ListObjectTO(JSON.toJSONString(userList));
	        //下载模板
	        List<Map<String,Object>> listmap=new ArrayList<>();
	        //网络下载
	        //支持spring boot  网络下载
	        ExcleFileDownload.createFile(response, ExcleFileDownload.getworkbook(listmap,head,"alex"),"alexfile");
		 } catch (Exception e) {
			// throw new IOException("不支持的文件类型");
			 e.printStackTrace();
	     }
	}
	    /**
	     * @ApiOperation(value="文件上传:案例")
	    * @PostMapping("/upload")
	    * @author Alex-xiaoshuai
	    * @date   2018年10月18日 下午5:08:57 
	    * @throws
	     */
	    //public @ResponseBody Object uploadExcel(@RequestParam("file") MultipartFile file) {
		public  Object  uploadExcel(MultipartFile file){
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
