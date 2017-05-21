package com.lq.lss.enums;

/**
 * 表的code号前缀
 * @author hzx
 *
 */
public enum TradeTypeNew {
	
	
	STOCK_IN(1,"入库"),
	//出库
	STOCK_OUT(2,"出库"),
	//采购
	STOCK_CG(3,"采购"),
	//销售
	STOCK_XS(4,"销售"),
	
//	TRANSFER_IN(5),
//	TRANSFER_OUT(6),
//	REMODELING(7),
	TEMPORARY_IN(8,"中心外暂存"),
	//中心外提暂存
	TEMPORARY_OUT(9,"中心外提暂存"),
	STOCKCENTERTRANSFER_IN(11,"中心调入"),
	STOCKCENTERTRANSFER_OUT(12,"中心调出"),
	
	STOCK_SEND(13,"发料"),
	STOCK_RECEIPT(14,"收料"),
	OTHER_IN(20,"其他"),
	TRANSFER_MUTUAL(25,"相互调拨"),
	STOCK_TRANSFER_OUT(29,"中心内部调出"),
	STOCK_TRANSFER_IN(32,"中心内部调入"),
	STOCK_TEMPORARY_IN(35,"中心暂存"),
	STOCK_TEMPORARY_OUT(36,"中心提暂存");
	
	private int type;
	private String name;
	
	private TradeTypeNew(int type,String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static String getTradeName(Integer type) {
		
		if(TradeTypeNew.STOCK_IN.type==type){
			return TradeTypeNew.STOCK_IN.name;
		}
		if(TradeTypeNew.STOCK_OUT.type==type){
			return TradeTypeNew.STOCK_OUT.name;
		}
		if(TradeTypeNew.STOCK_CG.type==type){
			return TradeTypeNew.STOCK_CG.name;
		}
		if(TradeTypeNew.STOCK_XS.type==type){
			return TradeTypeNew.STOCK_XS.name;
		}
		if(TradeTypeNew.TEMPORARY_IN.type==type){
			return TradeTypeNew.TEMPORARY_IN.name;
		}
		if(TradeTypeNew.TEMPORARY_OUT.type==type){
			return TradeTypeNew.TEMPORARY_OUT.name;
		}
		if(TradeTypeNew.STOCKCENTERTRANSFER_IN.type==type){
			return TradeTypeNew.STOCKCENTERTRANSFER_IN.name;
		}
		if(TradeTypeNew.STOCKCENTERTRANSFER_OUT.type==type){
			return TradeTypeNew.STOCKCENTERTRANSFER_OUT.name;
		}
		if(TradeTypeNew.STOCK_SEND.type==type){
			return TradeTypeNew.STOCK_SEND.name;
		}
		if(TradeTypeNew.STOCK_RECEIPT.type==type){
			return TradeTypeNew.STOCK_RECEIPT.name;
		}
		if(TradeTypeNew.TRANSFER_MUTUAL.type==type){
			return TradeTypeNew.TRANSFER_MUTUAL.name;
		}
		if(TradeTypeNew.STOCK_TRANSFER_OUT.type==type){
			return TradeTypeNew.STOCK_TRANSFER_OUT.name;
		}
		if(TradeTypeNew.STOCK_TRANSFER_IN.type==type){
			return TradeTypeNew.STOCK_TRANSFER_IN.name;
		}
		if(TradeTypeNew.STOCK_TEMPORARY_IN.type==type){
			return TradeTypeNew.STOCK_TEMPORARY_IN.name;
		}
		if(TradeTypeNew.STOCK_TEMPORARY_OUT.type==type){
			return TradeTypeNew.STOCK_TEMPORARY_OUT.name;
		}
		
		return null;
	}

}
