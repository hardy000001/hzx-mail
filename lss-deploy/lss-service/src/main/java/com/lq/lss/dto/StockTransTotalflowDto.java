package com.lq.lss.dto;

import java.math.BigDecimal;


/**
 *
 * @author  作者: eric
 * @date 创建时间: 2016-11-25 14:50:04
 */
public class StockTransTotalflowDto{

	
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
    
	/** 合同号 **/
    private String htCode;  
	/** 交易类型**/
    private String orderType;
    
	/** 费用**/
    private BigDecimal totalFee;
    
	/** 期初费用**/
    private BigDecimal beginFee;
    
	/** 运输费用**/
    private BigDecimal totalTransportFee;
	/** 装卸费用**/
    private BigDecimal totalZxFee;
	/** 杂费用**/
    private BigDecimal totalOtherFee;
    
    
    
    /**
     * 获取属性:totalOtherFee
     * 杂费用
     * @return totalOtherFee
     */
    public BigDecimal getTotalOtherFee() {
        return totalOtherFee;
    }
    /**
     * 设置属性:totalOtherFee
     * 杂费用
     * @param totalOtherFee
     */
    public void setTotalOtherFee(BigDecimal totalOtherFee) {
        this.totalOtherFee = totalOtherFee;
    }
    
    /**
     * 获取属性:totalTransportFee
     * 运输费用
     * @return totalTransportFee
     */
    public BigDecimal getTotalTransportFee() {
        return totalTransportFee;
    }
    /**
     * 设置属性:totalTransportFee
     * 运输费用
     * @param totalTransportFee
     */
    public void setTotalTransportFee(BigDecimal totalTransportFee) {
        this.totalTransportFee = totalTransportFee;
    }
    
    
    /**
     * 获取属性:totalZxFee
     * 装卸费用
     * @return totalZxFee
     */
    public BigDecimal getTotalZxFee() {
        return totalZxFee;
    }
    /**
     * 设置属性:totalZxFee
     * 装卸费用
     * @param totalZxFee
     */
    public void setTotalZxFee(BigDecimal totalZxFee) {
        this.totalZxFee = totalZxFee;
    }


    /**
     * 获取属性:beginFee
     * 运输费用
     * @return beginFee
     */
    public BigDecimal getBeginFee() {
        return beginFee;
    }
    /**
     * 设置属性:beginFee
     * 运输费用
     * @param beginFee
     */
    public void setBeginFee(BigDecimal beginFee) {
        this.beginFee = beginFee;
    }
    
    
    /**
     * 获取属性:totalFee
     * 运输费用
     * @return totalFee
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    /**
     * 设置属性:totalFee
     * 运输费用
     * @param totalFee
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }


 
	
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
    * 获取属性:htCode
    * 合同号
    * @return htCode
    */
   public String getHtCode() {
       return htCode;
   }
   /**
    * 设置属性:htCode
    * 合同号
    * @param htCode
    */
   public void setHtCode(String htCode) {
       this.htCode = htCode;
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
	
   

}