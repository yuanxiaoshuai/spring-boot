package com.springboot.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "r_pur_alibaba_payurl")
public class Unpurinfo {
		@Id
		@Column(name = "serial",columnDefinition="bigint COMMENT '主键，自动生成'")
		private String  serial;//主键ID
		private String  purchase_id;//批次号
		private String  orderid;//订单号(对应批次里的订单号)
		private String  operid;//提交人ID
		private String  opername;//提交人姓名
		private Date  opertime;//提交时间
		private String  paypeopleid;//付款人ID
		private String  paypeoplename;//付款人姓名
		private Date  paytime;//付款时间
		private String  payurl;//付款链接
		private String  purchase_pricesum;//系统付款总金额
		private String  status;//付款状态  1 未付款   2 已付款  3 订单异常状态  4 已同步  5 作废
		private String  alireturnmessage;//异常描述
		private String  purchase_price;//商品总金额(不含运费及其他)
		private String  express_fee;//运费
		private String  payurltype;//付款链接类型  1 自动订单生成    2 手动订单生成
		private String  payurlnum;//订单数量

}
