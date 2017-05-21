package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BConsumableTypeDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.service.ConsumableService;
import com.lq.lss.core.service.StockInfoService;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.CStockCenterTransfer;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockFlow;
import com.lq.lss.model.CStockInfo;

@Service
public class StockInfoServiceImpl extends EasyUIServiceBase<CStockInfo, Integer, StockInfoDao> implements StockInfoService {

	@Override
	public List<CStockInfo> findStockSum(CStockInfo cStockInfo) {
		return modelDao.findStockSum(cStockInfo);
	}

	@Override
	public void updateStockInfoRdTx(List<CStockInfo> cStockInfos) {
		modelDao.batchUpdate(cStockInfos);
		
	}

	

}
