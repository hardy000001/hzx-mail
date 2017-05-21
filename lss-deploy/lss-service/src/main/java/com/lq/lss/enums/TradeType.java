package com.lq.lss.enums;

/**
 * 表的code号前缀
 * @author Eric
 *
 */
public enum TradeType {
	
	//1入库，2出库，3采购，4销售,
	//21：商户，22：客户,23:合同
	//11 中心调入     12  中心调出  13 发料 14收料  25相互调拨 29中心内部调出审核 33中心内部调出反审核
	//入库
	STOCK_IN(1),
	//出库
	STOCK_OUT(2),
	//采购
	STOCK_CG(3),
	//销售
	STOCK_XS(4),
	
	TRANSFER_IN(5),
	TRANSFER_OUT(6),
	REMODELING(7),
	//中心外暂存
	TEMPORARY_IN(8),
	//中心外提暂存
	TEMPORARY_OUT(9),
	STOCK_TRS(10),
	//中心外调入
	STOCKCENTERTRANSFER_IN(11),
	//中心外调出
	STOCKCENTERTRANSFER_OUT(12),
	
	//发料
	STOCK_SEND(13),
	//收料
	STOCK_RECEIPT(14),
	OTHER_IN(20),
	//商户基本信息
	MCH_BASEINFO(21),
	//客户基本信息
	CUSTOMER_INFO(22),
	//合同
	BUS_HT(23),
	
	
	//收付款单
	BUS_ACCOUNT(30),
	//合同报停
	BUS_STOP_FLOW(31),
	
	//调拨期初
	TRANSFER_BEGIN(24),
	//相互调拨
	TRANSFER_MUTUAL(25),
	
	//赔偿
	STOCK_COMPENSATE(27),
	//盘点
	STOCK_INVENTORY(28),
    //收料反审
	ANTI_STOCK_RECEIPT(40),
	//出库反审核
	ANTI_STOCK_OUT(41),
	//入库反审核
	ANTI_AUDIT_STOCK_IN(50),
	//中心内部调出审核
	STOCK_TRANSFER_OUT(29),
	//中心内部调入
	STOCK_TRANSFER_IN(32),
	//中心内部调出反审核
	ANTI_STOCK_TRANSFER_OUT(33),
	//中心内部调入反审核
	ANTI_STOCK_TRANSFER_IN(34),
	//暂存单
	STOCK_TEMPORARY_IN(35),
	//提暂存单
	STOCK_TEMPORARY_OUT(36),
	//发料反审核
	ANTI_AUDIT_STOCK_SEND(37),
	//暂存单反审核
	ANTI_STOCK_TEMPORARY_IN(38),
	//提暂存单反审核
	ANTI_STOCK_TEMPORARY_OUT(39),
	
	//中心外调入单反审核
	ANTI_STOCK_CENTERTRANSFER_IN(42),
	//中心外调出单反审核
	ANTI_STOCK_CENTERTRANSFER_OUT(43);
	private int type;

	private TradeType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
}
