package com.lq.lss.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockRemodelingDetail extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private String id;
    /** 流水号 **/
    private String remSerialNo;
	/** 改制单ID **/
    private Integer rmdId;
	/** 所属规格 **/
    private String code;
	/** 改制前数量 **/
    private BigDecimal codeOldnum;
    /** 改制后数量 **/
    private BigDecimal codeNewnum;
	/** unit **/
    private String unit;
	/** price **/
    private BigDecimal price;
	/** 总数量 **/
    private BigDecimal totalS;

    private String name;
    private String fname;
    private String typeId;
    private String covertratio;
    private String accountflag;
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
   /**
    * 获取属性:rmdId
    * 改制单ID
    * @return rmdId
    */
   public Integer getRmdId() {
       return rmdId;
   }
   /**
    * 设置属性:rmdId
    * 改制单ID
    * @param rmdId
    */
   public void setRmdId(Integer rmdId) {
       this.rmdId = rmdId;
   }
	
  
	
   /**
    * 获取属性:unit
    * unit
    * @return unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:unit
    * unit
    * @param unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
   }
	
   /**
    * 获取属性:price
    * price
    * @return price
    */
   public BigDecimal getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * price
    * @param price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
   }
	
   /**
    * 获取属性:totalS
    * 总数量
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 总数量
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
  
   public String getRemSerialNo() {
	return remSerialNo;
   }
   
   public void setRemSerialNo(String remSerialNo) {
	this.remSerialNo = remSerialNo;
   }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getCodeOldnum() {
		return codeOldnum;
	}
	public void setCodeOldnum(BigDecimal codeOldnum) {
		this.codeOldnum = codeOldnum;
	}
	public BigDecimal getCodeNewnum() {
		return codeNewnum;
	}
	public void setCodeNewnum(BigDecimal codeNewnum) {
		this.codeNewnum = codeNewnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getCovertratio() {
		return covertratio;
	}
	public void setCovertratio(String covertratio) {
		this.covertratio = covertratio;
	}
	public String getAccountflag() {
		return accountflag;
	}
	public void setAccountflag(String accountflag) {
		this.accountflag = accountflag;
	}

   
   
}