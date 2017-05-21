package com.lq.lss.dto;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 
 */
public class CStockLeaseDetailDto{

	
	/** id **/
    private String id;
	/** 租赁单据流水号 **/
    private String lsSerialno;
	/** 物资编号 b_material_info.code **/
    private String materialcode;
	/** 总长度 m **/
    private String totalM;
	/** 总数量   **/
    private String totalS;
	/** 总量 **/
    private String totalT;
	/** 状态  0：正常   **/
    private String status;
	/** 租赁的单价  **/
    private BigDecimal price;
	/** 单位 **/
    private String unit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLsSerialno() {
		return lsSerialno;
	}
	public void setLsSerialno(String lsSerialno) {
		this.lsSerialno = lsSerialno;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	

}