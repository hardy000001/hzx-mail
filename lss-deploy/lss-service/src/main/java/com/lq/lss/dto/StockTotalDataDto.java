package com.lq.lss.dto;


/**
 * @author  作者: hzx
 * @date 创建时间: 2016-11-25 14:50:04
 */
public class StockTotalDataDto{

	private String orderNo;
	private String typeid; 
    private String totalM;
    
    
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTotalM() {
		return totalM;
	}
	public void setTotalM(String totalM) {
		this.totalM = totalM;
	}  
	
    

}