package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BConsumableInfoDao;
import com.lq.lss.core.service.ConsumableInfoService;
import com.lq.lss.model.BConsumableInfo;

@Service
public class ConsumableInfoServiceImpl extends EasyUIServiceBase<BConsumableInfo, Integer, BConsumableInfoDao> implements ConsumableInfoService {
	
	@Override
	public List<BConsumableInfo> queryConsumableInfoList(Integer typeid) {
		return modelDao.findTypeList(typeid);
	}


}
