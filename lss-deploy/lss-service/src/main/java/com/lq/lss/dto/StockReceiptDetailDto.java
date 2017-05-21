package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
public class StockReceiptDetailDto{

	
	/** id**/
    private String id;
	/** 收料单据流水号**/
    private String receiptSerialno;
	/** 物资编号 b_material_info.code**/
    private String materialcode;
	/** 入库总长度 m**/
    private String totalM;
	/** 入库总数量  **/
    private String totalS;
	/** 入库总量**/
    private String totalT;
    /** 单位id**/
    private String unit;
    private String name;
 
	
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
    * 获取属性:receiptSerialno
    * 收料单据流水号
    * @return receiptSerialno
    */
   public String getReceiptSerialno() {
       return receiptSerialno;
   }
   /**
    * 设置属性:receiptSerialno
    * 收料单据流水号
    * @param receiptSerialno
    */
   public void setReceiptSerialno(String receiptSerialno) {
       this.receiptSerialno = receiptSerialno;
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
   public String getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 入库总长度 m
    * @param totalM
    */
   public void setTotalM(String totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 入库总数量  
    * @return totalS
    */
   public String getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 入库总数量  
    * @param totalS
    */
   public void setTotalS(String totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 入库总量
    * @return totalT
    */
   public String getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 入库总量
    * @param totalT
    */
   public void setTotalT(String totalT) {
       this.totalT = totalT;
   }
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}