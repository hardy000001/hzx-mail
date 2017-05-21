package com.lq.lss.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class CStockInDetail extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private String id;
	/** 入库单据流水号 **/
    private String inSerialno;
	/** 物资编号 b_material_info.code **/
    private String materialcode;
    
	/** 换算单位 **/
    private String convertFlag;
	/** 换算系数 **/
    private BigDecimal covertRatio;
    
	/** 入库总长度 m **/
    private BigDecimal totalM;
	/** 入库总数量   **/
    private BigDecimal totalS;
	/** 入库总总量 **/
    private BigDecimal totalT;
	/** 状态  0：正常   **/
    private String status;
    /** 物资名称   **/
    private String name;
    /** 物资单位   **/
    private String unit;
    /** 父类名称   **/
    private String fname;
    /** 父类ID   **/
    private String typeId;
    
    private String accountFlag;
    
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
    * 获取属性:inSerialno
    * 入库单据流水号
    * @return inSerialno
    */
   public String getInSerialno() {
       return inSerialno;
   }
   /**
    * 设置属性:inSerialno
    * 入库单据流水号
    * @param inSerialno
    */
   public void setInSerialno(String inSerialno) {
       this.inSerialno = inSerialno;
   }
	
   /**
    * 获取属性:materialcode
    * 物资编号 b_material_info.code
    * @return materialcode
    */
   public String getMaterialCode() {
       return materialcode;
   }
   /**
    * 设置属性:materialcode
    * 物资编号 b_material_info.code
    * @param materialcode
    */
   public void setMaterialCode(String materialcode) {
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
    * 获取属性:convertFlag
    * 换算单位
    * @return convertFlag
    */
   public String getConvertFlag() {
       return convertFlag;
   }
   /**
    * 设置属性:convertFlag
    * 换算单位
    * @param convertFlag
    */
   public void setConvertFlag(String convertFlag) {
       this.convertFlag = convertFlag;
   }
	
   /**
    * 获取属性:covertRatio
    * 换算系数
    * @return covertRatio
    */
   public BigDecimal getCovertRatio() {
       return covertRatio;
   }
   /**
    * 设置属性:covertRatio
    * 换算系数
    * @param covertRatio
    */
   public void setCovertRatio(BigDecimal covertRatio) {
       this.covertRatio = covertRatio;
   }
   
   /**
    * 获取属性:accountFlag
    * 记账单位
    * @return accountFlag
    */
   public String getAccountFlag() {
       return accountFlag;
   }
   /**
    * 设置属性:accountFlag
    * 记账单位
    * @param accountFlag
    */
   public void setAccountFlag(String accountFlag) {
       this.accountFlag = accountFlag;
   }
	
   
	
   /**
    * 获取属性:status
    * 状态  0：正常  
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态  0：正常  
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
   
   /**
    * 获取属性:name
    * 材料名称
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * 材料名称
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
   
   /**
    * 获取属性:Unit
    * 材料单位
    * @return Unit
    */
   public String getUnit() {
       return unit;
   }
   /**
    * 设置属性:Unit
    * 材料单位
    * @param Unit
    */
   public void setUnit(String unit) {
       this.unit = unit;
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

}