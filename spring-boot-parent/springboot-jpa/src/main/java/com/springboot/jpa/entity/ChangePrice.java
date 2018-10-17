package com.springboot.jpa.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @ClassName: ChangePrice 
* @Description:  调价管理
* @author Alex-晓帅
* @date 2018年10月15日 上午9:35:17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "r_pur_change_price")
public class ChangePrice{
	
	@Id
	@Column(name = "id",columnDefinition="varchar(50) COMMENT '主键编号'")
	private String id;
	
	@Column(name = "sku",columnDefinition="varchar(50) COMMENT '产品编号'")
	private String sku;
	
	@Column(name = "supplierid",columnDefinition="varchar(50) COMMENT '供应商id'")
	private String supplierid;
	
	@Column(name = "supplier",columnDefinition="varchar(50) COMMENT '供应商'")
	private String supplier;
	
	@Column(name = "firstleveldirectoryid",columnDefinition="varchar(50) COMMENT '一级目录id'")
	private  String firstleveldirectoryid;
	
	@Column(name = "firstleveldirectoryname",columnDefinition="varchar(50) COMMENT '一级目录'")   
	private  String firstleveldirectoryname;
	
	@Column(name = "twoleveldirectoryid",columnDefinition="varchar(50) COMMENT '二级目录id'")
	private  String twoleveldirectoryid;
	
	@Column(name = "twoleveldirectoryname",columnDefinition="varchar(50) COMMENT '二级目录'")
	private  String twoleveldirectoryname;
	
	@Column(name = "costprice",columnDefinition="decimal COMMENT '成本价'")
	private  BigDecimal costprice;
	
	@Column(name = "oldpurprice",columnDefinition="decimal COMMENT '原采购价'")
	private  BigDecimal oldpurprice;
	
	@Column(name = "purprice",columnDefinition="decimal COMMENT '采购价'")
	private  BigDecimal purprice;
	
	@Column(name = "diffprice",columnDefinition="decimal COMMENT '差价'")
	private  BigDecimal diffprice;
	
	@Column(name = "pricerange",columnDefinition="varchar(50) COMMENT '调价幅度'")
	private  String pricerange;
	
	@Column(name = "natureange",columnDefinition="varchar(50) COMMENT '调价性质'")
	private  String natureange;
	
	@Column(name = "purchaseid",columnDefinition="varchar(50) COMMENT '采购员id'")
	private  String purchaseid;
	
	@Column(name = "purchase",columnDefinition="varchar(50) COMMENT '采购员'")
	private  String purchase;
	
	@Column(name = "supplieroperid",columnDefinition="varchar(50) COMMENT '供应链专员id'")
	private  String supplieroperid;
	
	@Column(name = "supplieroper",columnDefinition="varchar(50) COMMENT '供应链专员'")
	private  String supplieroper;
	
	@Column(name = "createoperid",columnDefinition="varchar(50) COMMENT '创建人id'")
	private  String createoperid;
	
	@Column(name = "createoper",columnDefinition="varchar(50) COMMENT '创建人'")
	private  String createoper;
	
	@Column(name = "createtime",columnDefinition="datetime COMMENT '创建时间'")
	private  String createtime;
	
	@Column(name = "status",columnDefinition="varchar(50) COMMENT '1.待审核  2通过   3驳回'")
	private  String status;
	
	@Column(name = "examinetime",columnDefinition="datetime COMMENT '审核时间'")
	private  Date examinetime;
	
	//降价时间
	@Column(name = "reductiontime",columnDefinition="datetime COMMENT '降价时间'")
	private  Date reductiontime;
	
	//为降级天数
	@Column(name = "reductionday",columnDefinition="varchar(50)  COMMENT '降级天数'")
	private  String reductionday;
	
	//降价金额
	@Column(name = "reductiontatolprice",columnDefinition="varchar(50)  COMMENT '降价金额'")
	private BigDecimal reductiontatolprice;
	

}
