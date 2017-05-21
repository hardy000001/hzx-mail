package com.lq.lss.core.service;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockTransferDetail;

import java.util.List;

/**
 * 
 * @author lanbo
 *
 */
public interface StockTransferDetailService extends EasyUIService<CStockTransferDetail, String> {

	 List<CStockTransferDetail> queryDetailList (String tsfSerialNo);

}
