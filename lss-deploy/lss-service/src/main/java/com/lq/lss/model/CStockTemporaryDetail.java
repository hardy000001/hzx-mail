package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                                
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
public class CStockTemporaryDetail extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 暂存单据流水号**/
    private String temSerialno;
	/** 物资编号 b_material_info.code**/
    private String materialcode;
	/** 入库总长度 m**/
    private BigDecimal totalM;
	/** 入库总数量  **/
    private BigDecimal totalS;
	/** 入库总总量**/
    private BigDecimal totalT;
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
    * 获取属性:temSerialno
    * 暂存单据流水号
    * @return temSerialno
    */
   public String getTemSerialno() {
       return temSerialno;
   }
   /**
    * 设置属性:temSerialno
    * 暂存单据流水号
    * @param temSerialno
    */
   public void setTemSerialno(String temSerialno) {
       this.temSerialno = temSerialno;
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
    * 获取属性:totalM
    * 入库总长度 m
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 入库总长度 m
    * @param totalM
    */
   public void setTotalM(BigDecimal totalM) {
       this.totalM = totalM;
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
    * 获取属性:totalT
    * 入库总总量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 入库总总量
    * @param totalT
    */
   public void setTotalT(BigDecimal totalT) {
       this.totalT = totalT;
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