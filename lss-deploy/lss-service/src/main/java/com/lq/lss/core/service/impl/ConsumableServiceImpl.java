package com.lq.lss.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BConsumableTypeDao;
import com.lq.lss.core.service.ConsumableService;
import com.lq.lss.model.BConsumableType;

@Service
public class ConsumableServiceImpl extends EasyUIServiceBase<BConsumableType, Integer, BConsumableTypeDao> implements ConsumableService {

	@Override
	public List<BConsumableType> findTypeList(BConsumableType bConsumableType) {
		return modelDao.findTypeList(bConsumableType);
		
		
	}

	

}
