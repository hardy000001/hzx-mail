package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CStockReceiptRepairinfo;
import com.lq.lss.core.dao.StockReceiptRepairinfoDao;
import com.lq.lss.core.service.StockReceiptRepairinfoService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-17 12:41:53
 */
@Service
public class StockReceiptRepairinfoServiceImpl extends EasyUIServiceBase<CStockReceiptRepairinfo, String, StockReceiptRepairinfoDao> implements StockReceiptRepairinfoService{

	@Override
	public List<CStockReceiptRepairinfo> queryRepairinfoByGroup(
			String receiptSerialno) {
		
		return modelDao.findRepairinfoByGroup(receiptSerialno);
	}

	

	
}