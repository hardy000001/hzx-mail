package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusSaleDetail;
import com.lq.lss.core.dao.BusSaleDetailDao;
import com.lq.lss.core.service.BusSaleDetailService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Service
public class BusSaleDetailServiceImpl extends EasyUIServiceBase<CBusSaleDetail, String, BusSaleDetailDao> implements BusSaleDetailService{

	
	@Override
	public List<CBusSaleDetail> querySaleDetailListById(String saleSerialno) {
		
		return  modelDao.queryDetailListById(saleSerialno);
	}


   
}