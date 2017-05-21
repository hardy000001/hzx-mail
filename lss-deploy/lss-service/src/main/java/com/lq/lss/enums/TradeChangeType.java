package com.lq.lss.enums;  
/** 
 * ClassName: TradeChangeType <br/> 
 * 
 * @author Eric
 * @version  
 * @since JDK 1.6 
 */
public enum TradeChangeType {
	
	IN_(1),
	OUT_(-1);
	
	int type;
	private TradeChangeType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	public static TradeChangeType getByCode(int type){
		switch (type) {
		case 1:
			return IN_;
		case -1:
			return OUT_;
		default:
			return null;
		}
	}
  
}