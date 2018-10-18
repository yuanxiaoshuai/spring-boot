package com.springboot.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
* @ClassName: Organization 
* @Description: 测试自动创建表结构和添加默认数据
* @author Alex-晓帅
* @date 2018年10月17日 上午10:45:15
 */

@Data
@Entity
@Table(name = "organization")
public class Organization implements Serializable{

  /**
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "company_name", nullable = false, length = 30)
  private String companyName;

  @Column(name = "active", nullable = false)
  private boolean active;
}
