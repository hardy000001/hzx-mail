package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                                
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
public class CStockOutDetail extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 出库单据流水号**/
    private String outSerialno;
	/** 物资编号 b_material_info.code**/
    private String materialcode;
	/** 出库总长度 m**/
    private BigDecimal totalM;
	/** 出库总数量  **/
    private BigDecimal totalS;
	/** 出库总量**/
    private BigDecimal totalT;
	/** 状态  0：正常  **/
    private String status;

    private String name;
    private String unit;
    private String fname;
    private String typeId;
    private String covertRatio;
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
    * 获取属性:outSerialno
    * 出库单据流水号
    * @return outSerialno
    */
   public String getOutSerialno() {
       return outSerialno;
   }
   /**
    * 设置属性:outSerialno
    * 出库单据流水号
    * @param outSerialno
    */
   public void setOutSerialno(String outSerialno) {
       this.outSerialno = outSerialno;
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
    * 出库总长度 m
    * @return totalM
    */
   public BigDecimal getTotalM() {
       return totalM;
   }
   /**
    * 设置属性:totalM
    * 出库总长度 m
    * @param totalM
    */
   public void setTotalM(BigDecimal totalM) {
       this.totalM = totalM;
   }
	
   /**
    * 获取属性:totalS
    * 出库总数量  
    * @return totalS
    */
   public BigDecimal getTotalS() {
       return totalS;
   }
   /**
    * 设置属性:totalS
    * 出库总数量  
    * @param totalS
    */
   public void setTotalS(BigDecimal totalS) {
       this.totalS = totalS;
   }
	
   /**
    * 获取属性:totalT
    * 出库总量
    * @return totalT
    */
   public BigDecimal getTotalT() {
       return totalT;
   }
   /**
    * 设置属性:totalT
    * 出库总量
    * @param totalT
    */
   public void setTotalT(BigDecimal totalT) {
       this.totalT = totalT;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
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