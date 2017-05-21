package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.CStockReceiptRepairinfo;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-17 12:41:53
 */
public interface StockReceiptRepairinfoService extends EasyUIService<CStockReceiptRepairinfo, String>{

    /**
     * 根据分组查询维修项
     * @param receiptSerialno
     * @return
     */
	List<CStockReceiptRepairinfo> queryRepairinfoByGroup(String receiptSerialno);

}