package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusPurDetail;
import com.lq.lss.core.dao.BusPurDetailDao;
import com.lq.lss.core.service.BusPurDetailService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-08-31 10:21:24
 */
@Service
public class BusPurDetailServiceImpl extends EasyUIServiceBase<CBusPurDetail, String, BusPurDetailDao> implements BusPurDetailService{

	@Override
	public List<CBusPurDetail> queryPurDetailListById(String purSerialno) {
		
		return modelDao.queryPurDetailListById(purSerialno);
	}

   
}