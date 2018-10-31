package com.springboot.poi.common.config.excle;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
* @ClassName: ExcleConfig 
* @Description:  文件上传限制配置
* @author Alex-晓帅
* @date 2018年10月31日 下午1:39:04
 */
@Configuration
public class ExcleConfig {
	/**
     * 文件上传配置
     * @return
     */
   @Bean
   public MultipartConfigElement multipartConfigElement() {
           MultipartConfigFactory factory = new MultipartConfigFactory();
           //文件最大
           factory.setMaxFileSize("10240KB"); //KB,MB
           // / 设置总上传数据总大小
           factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
   }
}
