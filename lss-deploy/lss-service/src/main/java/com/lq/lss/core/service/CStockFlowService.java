package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.dto.CStockFlowDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockFlow;

/**
 *
 * @author  作者: Eric
 * @date 创建时间: 2016-10-08 09:26:28
 */
public interface CStockFlowService extends EasyUIService<CStockFlow, String>{

  
	  /**
     * 交易流水处理
     * @param tradeType
     * @param orderNo
     * @return
     */
	public ResultDto<String> saveStockFlowRdTx(TradeType tradeType,String orderNo);
	

}