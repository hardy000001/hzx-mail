package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-25 14:50:04
 */
public class StockTotalflowDto{

	
	/** 单据号**/
    private String orderNo;
	/** 自增序号**/
    private Integer id;
	/** 所属租赁中心的ID**/
    private Integer deptid;
	/** 源商户号**/
    private String fromMchcode;
	/** 目的商户号**/
    private String toMchcode;
	/** 交易类型**/
    private String orderType;
    /** 查询类型，1：租赁，2：中心,3:调拨   4:内部相互调拨**/
    private int queryType;
    /** 合同编号**/
    private String htcode;
    protected String createDate;  //创建日期
	protected String dealDate;    //交易日期
	
   /**
    * 获取属性:orderNo
    * 单据号
    * @return orderNo
    */
   public String getOrderNo() {
       return orderNo;
   }
   /**
    * 设置属性:orderNo
    * 单据号
    * @param orderNo
    */
   public void setOrderNo(String orderNo) {
       this.orderNo = orderNo;
   }
	
   /**
    * 获取属性:id
    * 自增序号
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 自增序号
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:deptid
    * 所属租赁中心的ID
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 所属租赁中心的ID
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
	
   /**
    * 获取属性:fromMchcode
    * 源商户号
    * @return fromMchcode
    */
   public String getFromMchcode() {
       return fromMchcode;
   }
   /**
    * 设置属性:fromMchcode
    * 源商户号
    * @param fromMchcode
    */
   public void setFromMchcode(String fromMchcode) {
       this.fromMchcode = fromMchcode;
   }
	
   /**
    * 获取属性:toMchcode
    * 目的商户号
    * @return toMchcode
    */
   public String getToMchcode() {
       return toMchcode;
   }
   /**
    * 设置属性:toMchcode
    * 目的商户号
    * @param toMchcode
    */
   public void setToMchcode(String toMchcode) {
       this.toMchcode = toMchcode;
   }
	
   /**
    * 获取属性:orderType
    * 交易类型
    * @return orderType
    */
   public String getOrderType() {
       return orderType;
   }
   /**
    * 设置属性:orderType
    * 交易类型
    * @param orderType
    */
   public void setOrderType(String orderType) {
       this.orderType = orderType;
   }
	public Integer getQueryType() {
		return queryType;
	}
	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
	public String getHtcode() {
		return htcode;
	}
	public void setHtcode(String htcode) {
		this.htcode = htcode;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
   
   

}