package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CStockOutDetail;
import com.lq.lss.core.dao.StockOutDetailDao;
import com.lq.lss.core.service.StockOutDetailService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-08 09:26:28
 */
@Service
public class StockOutDetailServiceImpl extends EasyUIServiceBase<CStockOutDetail, String, StockOutDetailDao> implements StockOutDetailService{

	@Override
	public List<CStockOutDetail> queryStockOutListById(String outSerialno) {
		
		return modelDao.queryStockOutListById(outSerialno);
	}

   
}