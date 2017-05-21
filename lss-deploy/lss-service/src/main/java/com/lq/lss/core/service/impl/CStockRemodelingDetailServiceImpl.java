package com.lq.lss.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.CStockRemodelingDetailDao;
import com.lq.lss.core.service.CStockRemodelingDetailService;
import com.lq.lss.model.CStockRemodelingDetail;
@Service
public class CStockRemodelingDetailServiceImpl extends EasyUIServiceBase<CStockRemodelingDetail, String, CStockRemodelingDetailDao> implements CStockRemodelingDetailService{
	@Resource
	CStockRemodelingDetailDao csdao;
	@Override
	public List<CStockRemodelingDetail> fidnCsrddById(String remSerialNo) {
		// TODO Auto-generated method stub
		return (List<CStockRemodelingDetail>) csdao.findByCStockRemodeling(remSerialNo);
	}

}
