package com.lq.lss.core.dao;
                            
import java.util.List;

import org.springframework.stereotype.Repository;
import com.lq.lss.model.CStockReceiptDetail;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
@Repository
public class StockReceiptDetailDao extends LssSimpleBaseDao<CStockReceiptDetail, String>{

	@SuppressWarnings("unchecked")
	public List<CStockReceiptDetail> findReceiptDetailListById(
			String receiptSerialno) {

		return (List<CStockReceiptDetail>) findListByParams(
				"findReceiptDetailListById", receiptSerialno);
	}
}