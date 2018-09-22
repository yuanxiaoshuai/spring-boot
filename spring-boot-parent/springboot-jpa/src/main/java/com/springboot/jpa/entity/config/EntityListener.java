package com.springboot.jpa.entity.config;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import com.springboot.jpa.entity.BastEntity;

/**
* @ClassName: EntityListener 
* @Description:  声明监听实体类
* @author Alex-晓帅
* @date 2018年9月21日 下午3:53:44
 */

public class EntityListener extends AuditingEntityListener{


	/**
	 * 
	* @Title: onPrePersist 
	* @Description: TODO(创建时添加用户信息) 
	* @return void    返回类型
	 */
	@PrePersist
	public void onPrePersist(BastEntity o) {
		String name="admin";
		o.setCreateTime(new Date());
		o.setUpdateTime(new Date());
		if (StringUtils.isEmpty(o.getCreatedBy())) {
			o.setCreatedBy(name);
		}
		if (StringUtils.isEmpty(o.getUpdatedBy())) {
			o.setUpdatedBy(name);
		}
	}
   
	/**
	 * 
	* @Title: onPreUpdate 
	* @Description: TODO(修改时添加用户信息) 
	* @return void    返回类型
	 */
	@PreUpdate
	public void onPreUpdate(BastEntity o) {
		String name="admin";
		o.setUpdateTime(new Date());
		if (!StringUtils.isEmpty(name)) {
			o.setUpdatedBy(name);
		}
	}


}
