package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockTemporaryDetail;




public interface StockTemporaryDetailService extends EasyUIService<CStockTemporaryDetail, String>{
	
	
	
	public List<CStockTemporaryDetail> findCstdpBytemSerialno(String temSerialno);
}
