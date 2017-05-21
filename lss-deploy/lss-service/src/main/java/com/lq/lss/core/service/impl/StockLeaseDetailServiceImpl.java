package com.lq.lss.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.StockLeaseDetailDao;
import com.lq.lss.core.service.StockLeaseDetailService;
import com.lq.lss.model.CStockLeaseDetail;
import com.lq.lss.model.CStockTemporaryDetail;

/**
 * 
 * @author ch
 *
 */
@Service
public class StockLeaseDetailServiceImpl extends EasyUIServiceBase<CStockLeaseDetail, String, StockLeaseDetailDao> implements StockLeaseDetailService {

	@Resource
	StockLeaseDetailDao slddao;
	@Override
	public List<CStockLeaseDetail> findCsldBylsSerialno(String lsSerialno) {
		// TODO Auto-generated method stub
		return slddao.findByCStockLeaseDetail(lsSerialno);
	}

	


}
