package com.lq.lss.core.service;

import java.util.List;

import com.lq.easyui.service.base.EasyUIService;
import com.lq.lss.model.BConsumableType;

public interface ConsumableService extends EasyUIService<BConsumableType, Integer> {
 
	
	public List<BConsumableType> findTypeList(BConsumableType bConsumableType );
	

}
