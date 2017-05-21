package com.lq.lss.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.CStockCompensateDetailDao;
import com.lq.lss.core.service.CStockCompensateDetailService;
import com.lq.lss.dto.CStockCompensateDetailDto;
import com.lq.lss.model.CStockCompensateDetail;
import com.lq.lss.model.CompensateDetail;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-12-28 15:32:42
 */
@Service
public class CStockCompensateDetailServiceImpl extends EasyUIServiceBase<CStockCompensateDetail, String, CStockCompensateDetailDao> implements CStockCompensateDetailService{

    @Resource
	private CStockCompensateDetailDao cStockCompensateDetailDao;

	@Override
	public List<CompensateDetail> findStockCompensateDetailbyHtcode(String htcode) {
		// TODO Auto-generated method stub
		return cStockCompensateDetailDao.findStockCompensateDetailbyHtcode( htcode);
	}

	@Override
	public List<CStockCompensateDetailDto> findCStockCompensateDetail(String compensateSerialno) {
		// TODO Auto-generated method stub
		return cStockCompensateDetailDao.findCStockCompensateDetail(compensateSerialno);
	}
	
	
	
	@Override
	public List<CStockCompensateDetail> findCStockCompensateDetailByIds(String compensateSerialno) {
		// TODO Auto-generated method stub
		return cStockCompensateDetailDao.findCStockCompensateDetailByIds(compensateSerialno);
	}
}