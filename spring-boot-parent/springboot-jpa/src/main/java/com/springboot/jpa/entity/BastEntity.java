package com.springboot.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.springboot.jpa.entity.config.EntityListener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("公有实体类")
@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BastEntity implements Serializable {
		/**
		 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
		 */
		private static final long serialVersionUID = -6635040692164747229L;

		@ApiModelProperty(value="id")
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ApiModelProperty(value="创建时间")
		@CreatedDate
		private Date createTime;
		
		@ApiModelProperty(value="修改时间")
		@LastModifiedDate
		private Date updateTime;
		
		@Version
		protected Long version;
		
		@ApiModelProperty(value="创建人")
		@CreatedBy
		private String createdBy;

		@ApiModelProperty(value="修改人")
		@LastModifiedBy
		private String updatedBy;

}
