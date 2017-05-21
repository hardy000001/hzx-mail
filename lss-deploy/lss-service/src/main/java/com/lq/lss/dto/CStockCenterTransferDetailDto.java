package com.lq.lss.dto;

import java.math.BigDecimal;

public class CStockCenterTransferDetailDto {
	/** id**/
    private String id;
	/** 调拨单据流水号**/
    private String tsfSerialno;
	/** 物资编号 b_material_info.code**/
    private String materialcode;
	/** 调拨总长度 m**/
    private String totalM;
	/** 调拨总数量  **/
    private String totalS;
	/** 调拨总量**/
    private String totalT;
	/** unit**/
    private String unit;
    
    private String code;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTsfSerialno() {
		return tsfSerialno;
	}
	public void setTsfSerialno(String tsfSerialno) {
		this.tsfSerialno = tsfSerialno;
	}
	public String getMaterialcode() {
		return materialcode;
	}
	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}
	public String getTotalM() {
		return totalM;
	}
	public void setTotalM(String totalM) {
		this.totalM = totalM;
	}
	public String getTotalS() {
		return totalS;
	}
	public void setTotalS(String totalS) {
		this.totalS = totalS;
	}
	public String getTotalT() {
		return totalT;
	}
	public void setTotalT(String totalT) {
		this.totalT = totalT;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
    
    
    
}
