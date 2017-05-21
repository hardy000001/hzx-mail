package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CStockTemporaryDetail;

/**
 * 
 * @author ch
 *
 */
public interface StockSendDetailService extends EasyUIService<CStockSendDetail, String> {

	List<CStockSendDetail> findCStockSendDetailbySerialno(String sendSerialno);
 
	

}
