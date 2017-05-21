package com.lq.lss.core.dao;
                    
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.CStockReceiptRepairinfo;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-17 12:41:53
 */
@Repository
public class StockReceiptRepairinfoDao extends LssSimpleBaseDao<CStockReceiptRepairinfo, String>{

	@SuppressWarnings("unchecked")
	public List<CStockReceiptRepairinfo> queryRepairiBySerialno(
			String receiptSerialno) {

		return (List<CStockReceiptRepairinfo>) findListByParams(
				"queryRepairiBySerialno", receiptSerialno);
	}
	
	@SuppressWarnings("unchecked")
	public List<CStockReceiptRepairinfo> findRepairinfoByGroup(
			String receiptSerialno) {
		
		return (List<CStockReceiptRepairinfo>)
				findListByParams("findRepairinfoByGroup", receiptSerialno);
	}
}