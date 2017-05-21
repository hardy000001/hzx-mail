package com.lq.lss.dto;



public class CStockRemodelingDetailDto {
	private String id;
    /** 流水号 **/
    private String remSerialNo;
	/** 改制单ID **/
    private Integer rmdId;
	/** 所属规格 **/
    private String code;
	/** 改制前数量 **/
    private String codeOldnum;
    /** 改制后数量 **/
    private String codeNewnum;
	/** unit **/
    private String unit;
	/** price **/
    private String price;
	/** 总数量 **/
    private String totalS;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemSerialNo() {
		return remSerialNo;
	}
	public void setRemSerialNo(String remSerialNo) {
		this.remSerialNo = remSerialNo;
	}
	public Integer getRmdId() {
		return rmdId;
	}
	public void setRmdId(Integer rmdId) {
		this.rmdId = rmdId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeOldnum() {
		return codeOldnum;
	}
	public void setCodeOldnum(String codeOldnum) {
		this.codeOldnum = codeOldnum;
	}
	public String getCodeNewnum() {
		return codeNewnum;
	}
	public void setCodeNewnum(String codeNewnum) {
		this.codeNewnum = codeNewnum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotalS() {
		return totalS;
	}
	public void setTotalS(String totalS) {
		this.totalS = totalS;
	}
    
}
