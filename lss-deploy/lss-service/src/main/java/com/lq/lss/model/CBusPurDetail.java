package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CBusPurDetail extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** 自增序号 **/
    private String id;
	/** 采购流水号 **/
    private String purSerialno;
    /** 材料id**/
    private Integer materialId;
	/** 采购单价 **/
    private BigDecimal price;
	/** 单位 **/
    private String unit;
	/** 数量 **/
    private BigDecimal quantity;
	/** 总金额 **/
    private BigDecimal totalAmt;

    private String name;
    private String fname;
    private String typeId;
    private String covertRatio;
    private String accountFlag;
	
   /**
    * 获取属性:id
    * 自增序号
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * 自增序号
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
   /**
    * 获取属性:purSerialno
    * 采购流水号
    * @return purSerialno
    */
   public String getPurSerialno() {
       return purSerialno;
   }
   /**
    * 设置属性:purSerialno
    * 采购流水号
    * @param purSerialno
    */
   public void setPurSerialno(String purSerialno) {
       this.purSerialno = purSerialno;
   }
	
   /**
    * 获取属性:materialId
    * 材料id
    * @return materialId
    */
   public Integer getMaterialId() {
       return materialId;
   }
   /**
    * 设置属性:materialId
    * 材料id
    * @param materialId
    */
   public void setMaterialId(Integer materialId) {
       this.materialId = materialId;
   }
	
   /**
    * 获取属性:price
    * 采购单价
    * @return price
    */
   public BigDecimal getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 采购单价
    * @param price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
   }
	
   /**
    * 获取属性:unit
    * 单位
    * @return unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:unit
    * 单位
    * @param unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
   }
	
   /**
    * 获取属性:quantity
    * 数量
    * @return quantity
    */
   public BigDecimal getQuantity() {
       return quantity;
   }
   /**
    * 设置属性:quantity
    * 数量
    * @param quantity
    */
   public void setQuantity(BigDecimal quantity) {
       this.quantity = quantity;
   }
	
   /**
    * 获取属性:totalAmt
    * 总金额
    * @return totalAmt
    */
   public BigDecimal getTotalAmt() {
       return totalAmt;
   }
   /**
    * 设置属性:totalAmt
    * 总金额
    * @param totalAmt
    */
   public void setTotalAmt(BigDecimal totalAmt) {
       this.totalAmt = totalAmt;
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
	
	public String getCovertRatio() {
		return covertRatio;
	}
	public void setCovertRatio(String covertRatio) {
		this.covertRatio = covertRatio;
	}
	public String getAccountFlag() {
		return accountFlag;
	}
	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}
    
}