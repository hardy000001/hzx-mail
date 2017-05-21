package com.lq.lss.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CStockInventoryDetail;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.dao.StockInventoryDetailDao;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.dto.StockInventoryDetailDto;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-29 13:26:15
 */
@Service
public class StockInventoryDetailServiceImpl extends EasyUIServiceBase<CStockInventoryDetail, String, StockInventoryDetailDao> implements StockInventoryDetailService{
    
	@Resource
	private StockInfoDao stockInfoDao;
	
	@Override
	public List<StockInventoryDetailDto> queryInventoryDetailList(
			String inventorySerialno) {
		
		return modelDao.findInventoryDetail(inventorySerialno);
	}

	@Override
	public List<StockInventoryDetailDto> queryInventoryByMchcode(String deptId,
			String mchcode) {
		
		return stockInfoDao.findInventoryByMchcode(deptId, mchcode);
	}

    
}