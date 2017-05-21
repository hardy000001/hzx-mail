package com.lq.lss.core.service;

import java.util.List;
import java.util.Map;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockReceiptDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
public interface StockReceiptDetailService extends EasyUIService<CStockReceiptDetail, String>{

  
	/**
     * 查询明细
     * @param receiptSerialno 收料单流水号
     * @return
     */
	List<CStockReceiptDetail> queryReceiptDetailListById(String receiptSerialno);
	
	/**
	 * 根据id得到收料明细信息
	 * @param receiptSerialno
	 * @return
	 */
	public List<Map<String, Object>> getReceiptDetailListById(String receiptSerialno);

}