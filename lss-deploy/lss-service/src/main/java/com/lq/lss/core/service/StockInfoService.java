package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockFlow;
import com.lq.lss.model.CStockInfo;

/**
 * 
 * @author Eric
 *
 */

public interface StockInfoService extends EasyUIService<CStockInfo, Integer> {
 
	
	public List<CStockInfo> findStockSum(CStockInfo cStockInfo );
	
	/**
     * 修改信息
     * @param cStockInfos
     * @return
     */
	void updateStockInfoRdTx( List<CStockInfo> cStockInfos);
}
