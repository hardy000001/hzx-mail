package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporaryDetail;

/**
 * 
 * @author lanbo
 *
 */
public interface StockCenterTransferDetailService extends EasyUIService<CStockCenterTransferDetail, String> {

	List<CStockCenterTransferDetail> findCsctdBytsfSerialno(String tsfSerialno);
 
	

}
