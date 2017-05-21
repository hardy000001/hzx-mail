package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                            
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
public class CStockCompensateDetail extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 赔偿单据流水号**/
    private String compensateSerialno;
	/** 物资编号 b_material_info.code**/
    private String materialcode;
	/** 入库总数量  **/
    private BigDecimal totalS;
    
    private BigDecimal totalM;
	/** 赔偿单价**/
    private BigDecimal price;
	/** unit**/
    private String unit;

    private String name;
    private String fname;
    private String typeId;
    private String covertratio;
    private String accountflag;
    private String subtotal;
	
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
    * 获取属性:compensateSerialno
    * 赔偿单据流水号
    * @return compensateSerialno
    */
   public String getCompensateSerialno() {
       return compensateSerialno;
   }
   /**
    * 设置属性:compensateSerialno
    * 赔偿单据流水号
    * @param compensateSerialno
    */
   public void setCompensateSerialno(String compensateSerialno) {
       this.compensateSerialno = compensateSerialno;
   }
	
   /**
    * 获取属性:materialcode
    * 物资编号 b_material_info.code
    * @return materialcode
    */
   public String getMaterialcode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资编号 b_material_info.code
    * @param materialcode
    */
   public void setMaterialcode(String materialcode) {
       this.materialcode = materialcode;
   }
	
   /**
    * 获取属性:totalS
    * 入库总数量  
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 入库总数量  
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:price
    * 赔偿单价
    * @return price
    */
   public BigDecimal getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 赔偿单价
    * @param price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
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
public BigDecimal getTotalM() {
	return totalM;
}
public void setTotalM(BigDecimal totalM) {
	this.totalM = totalM;
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
public String getSubtotal() {
	return subtotal;
}
public void setSubtotal(String subtotal) {
	this.subtotal = subtotal;
}

   
}