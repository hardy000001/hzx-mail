package com.lq.lss.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.StockCenterTransferDetailDao;
import com.lq.lss.core.dao.StockLeaseDetailDao;
import com.lq.lss.core.service.StockCenterTransferDetailService;
import com.lq.lss.core.service.StockLeaseDetailService;
import com.lq.lss.model.CStockCenterTransferDetail;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporaryDetail;

/**
 * 
 * @author ch
 *
 */
@Service
public class StockCenterTransferDetailServiceImpl extends EasyUIServiceBase<CStockCenterTransferDetail, String, StockCenterTransferDetailDao> implements StockCenterTransferDetailService {

	@Resource
	StockCenterTransferDetailDao sctddao;
	@Override
	public List<CStockCenterTransferDetail> findCsctdBytsfSerialno(String tsfSerialno) {
		// TODO Auto-generated method stub
		return sctddao.findByCStockCenterTransferDetail(tsfSerialno);
	}

	


}
